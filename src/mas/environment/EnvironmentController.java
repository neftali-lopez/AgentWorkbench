package mas.environment;

import gui.projectwindow.simsetup.EnvironmentSetupObjectSettings;

import jade.content.lang.Codec.CodecException;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.OntologyException;

import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import sim.setup.SimulationSetup;
import sim.setup.SimulationSetups;
import sim.setup.SimulationSetups.SimulationSetupsChangeNotification;

import application.Language;
import application.Project;

import mas.environment.ontology.ActiveObject;
import mas.environment.ontology.EnvironmentOntology;
import mas.environment.ontology.Movement;
import mas.environment.ontology.Physical2DEnvironment;
import mas.environment.ontology.Physical2DObject;
import mas.environment.ontology.PlaygroundObject;
import mas.environment.ontology.Position;
import mas.environment.ontology.Scale;
import mas.environment.ontology.Size;
import mas.environment.utils.EnvironmentWrapper;
/**
 * Hepler class for managing an environment ontology object
 * @author Nils
 *
 */
public class EnvironmentController extends Observable implements Observer{
	/**
	 * Observable event code: New environment instance assigned
	 */
	public static final int ENVIRONMENT_CHANGED = 0;
	/**
	 * Observable event code: Scale settings changed
	 */
	public static final int SCALE_CHANGED = 1;
	/**
	 * Observable event code: New environment object created
	 */
	public static final int OBJECTS_CHANGED = 2;
	/**
	 * Observable event code: SVG document changed
	 */
	public static final int SVG_CHANGED = 3;
	/**
	 * The path where environment and SVG files are stored
	 */
	private String baseFilePath = null;
	/**
	 * Path for saving the SVG
	 */
	private String currentSVGPath = null;
	/**
	 * Path for saving the environment
	 */
	private String currentEnvironmentPath = null;
	/**
	 * The environment instance encapsulated by this EnvironmentController
	 */
	private Physical2DEnvironment environment;
	/**
	 * Wrapper for easier handling of the environment
	 */
	private EnvironmentWrapper envWrap = null;
	/**
	 * The SVG document representing this environment; 
	 */
	private Document svgDoc = null;
	/**
	 * The project the controlled environment belongs to
	 */
	private Project project;
	/**
	 * The Physical2DObject currently selected for editing
	 */
	private Physical2DObject selectedObject = null;
	/**
	 * Constructor
	 * @param project The Agent.GUI project
	 */
	public EnvironmentController(Project project){
		this.project = project;
		this.project.setEnvironmentController(this);
		this.project.addObserver(this);
		this.baseFilePath = this.project.getProjectFolderFullPath()+this.project.getSubFolderEnvSetups()+File.separator;
		SimulationSetup currentSetup = project.simSetups.getCurrSimSetup(); 
		// Load SVG file if specified
		if(currentSetup.getSvgFileName() != null && currentSetup.getSvgFileName().length() >0){
			currentSVGPath = project.getProjectFolderFullPath()+project.getSubFolderEnvSetups()+File.separator+currentSetup.getSvgFileName();
			setSvgDoc(loadSVG(new File(currentSVGPath)));
		}
		// Load environment file if specified
		if(currentSetup.getEnvironmentFileName() != null && currentSetup.getEnvironmentFileName().length() >0){
			currentEnvironmentPath = project.getProjectFolderFullPath()+project.getSubFolderEnvSetups()+File.separator+currentSetup.getEnvironmentFileName();
			setEnvironment(loadEnvironment(new File(currentEnvironmentPath)));
		}
		// If SVG present and environment not, create a new blank environment 
		if(this.svgDoc != null && this.environment == null){
			setEnvironment(initEnvironment());
		}
		
	}
	
	
	/**
	 * Setting the selected Physical2DObject
	 * @param id The id of the selected object
	 */
	public void setSelectedObject(String id) {
		this.selectedObject = envWrap.getObjectById(id);
	}
	/**
	 * @return The current projects Environment
	 */
	public Physical2DEnvironment getEnvironment() {
		return environment;
	}
	/**
	 * @return EnvironmentWrapped containing the current project's environment 
	 */
	public EnvironmentWrapper getEnvWrap() {
		return envWrap;
	}
	private Physical2DEnvironment initEnvironment(){
		Physical2DEnvironment newEnv = null;
		if(svgDoc != null){
			Element svgRoot = svgDoc.getDocumentElement();
			float width = Float.parseFloat(svgRoot.getAttributeNS(null, "width"));
			float height = Float.parseFloat(svgRoot.getAttributeNS(null, "height"));
			
			Size rootPgSize = new Size();
			rootPgSize.setWidth(width);
			rootPgSize.setHeight(height);
			
			Position rootPgPos = new Position();
			rootPgPos.setXPos(0);
			rootPgPos.setYPos(0);
			
			PlaygroundObject rootPg = new PlaygroundObject();
			rootPg.setPosition(rootPgPos);
			rootPg.setSize(rootPgSize);
			rootPg.setId("RootPlayground");
			
			Scale defaultScale = new Scale();
			defaultScale.setPixelValue(10);
			defaultScale.setRealWorldUnitValue(1);
			defaultScale.setRealWorldUntiName("m");
			
			newEnv = new Physical2DEnvironment();
			newEnv.setRootPlayground(rootPg);
			newEnv.setScale(defaultScale);
			newEnv.setProjectName(project.getProjectName());
			
			String envFileName = project.getProjectName()+"_"+project.simSetupCurrent+".xml";
			project.simSetups.getCurrSimSetup().setEnvironmentFileName(envFileName);
		}
		return newEnv;
	}
	
	/**
	 * This method loads a SVG document from a SVG file
	 * @param svgFile The file containing the document
	 * @return The SVG document, or null if not successful
	 */
	private Document loadSVG(File svgFile){
		Document doc = null;
		
		if(svgFile.exists()){
			doc = SVGDOMImplementation.getDOMImplementation().createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);
			System.out.println(Language.translate("Lade SVG-Datei")+" "+svgFile.getName());
			SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
			
			try {
				doc = factory.createDocument(svgFile.toURI().toURL().toString());
			} catch (MalformedURLException e) {
				System.err.println(Language.translate("Fehler beim Laden der SVG-Datei"));
			} catch (IOException e) {
				System.err.println(Language.translate("Fehler beim Laden der SVG-Datei"));
			}			
		}else{
			System.err.println(Language.translate("SVG-Datei")+" "+svgFile.getPath()+" "+Language.translate("nicht gefunden"));
		}
		
		return doc;
	}
	
	public void setSVGFile(File file){
		setSvgDoc(loadSVG(file));
		if(!file.getParentFile().getAbsolutePath().equals(project.getProjectFolderFullPath()+project.getSubFolderEnvSetups())){
			file = new File(currentSVGPath);
			saveSVG(file);
		}
		project.simSetups.getCurrSimSetup().setSvgFileName(file.getName());
		setEnvironment(initEnvironment());
	}
	/**
	 * Saving the current SVG document to a file
	 * @param svgFile The file
	 */
	private void saveSVG(File svgFile){
		if(svgDoc != null){
			try {
				System.out.println(Language.translate("Speichere SVG nach ")+" "+svgFile.getName());
				if(!svgFile.exists()){
					svgFile.createNewFile();
				}
				FileWriter fw = new FileWriter(svgFile);
				PrintWriter writer = new PrintWriter(fw);
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				writer.write("<!DOCTYPE svg PUBLIC '");
				writer.write(SVGConstants.SVG_PUBLIC_ID);
				writer.write("' '");
				writer.write(SVGConstants.SVG_SYSTEM_ID);
				writer.write("'>\n\n");
				SVGTranscoder t = new SVGTranscoder();
				t.transcode(new TranscoderInput(svgDoc), new TranscoderOutput(writer));
				writer.close();
				
			} catch (IOException e) {
				System.err.println(Language.translate("Fehler beim Erzeugen der Datei")+" "+svgFile.getAbsolutePath());
			} catch (TranscoderException e) {
				System.err.println(Language.translate("Fehler beim speichern des SVG-Dokuments"));
			}
		}
		
	}
	
	/**
	 * This method prepares the SVG document for Agent.GUI visualization use
	 * - Adds a border if not already there
	 * @param doc The SVG document to prepare
	 * @return The prepared SVG document
	 */
	private Document prepareSVG(Document doc){
		Element svgRoot = doc.getDocumentElement();
		if(doc.getElementById("border") == null){
			Element border = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
			float width = Float.parseFloat(svgRoot.getAttributeNS(null, "width"))-1;
			float height = Float.parseFloat(svgRoot.getAttributeNS(null, "height"))-1;
			border.setAttributeNS(null, "id", "border");
			border.setAttributeNS(null, "width", ""+(int)width);
			border.setAttributeNS(null, "height", ""+(int)height);
			border.setAttributeNS(null, "fill", "none");
			border.setAttributeNS(null, "stroke", "black");
			border.setAttributeNS(null, "stroke-width", "1");
			svgRoot.appendChild(border);	
		}	
		return doc;
	}
	/**
	 * Getter method for svgDoc
	 * @return The SVG document assigned to the current environment 
	 */
	public Document getSvgDoc() {
		return svgDoc;
	}
	/**
	 * Prepares the SVG document and assigns it to the current environment
	 * @param doc
	 */
	private void setSvgDoc(Document doc){
		if(doc != null){
			this.svgDoc = prepareSVG(doc);		
		}
		this.svgDoc = doc;
		setChanged();
		notifyObservers(new Integer(SVG_CHANGED));
	}
	/**
	 * Saves SVG and environment to the default paths
	 */
	public void save(){
		saveSVG(new File(currentSVGPath));
		saveEnvironment(new File(currentEnvironmentPath));
	}
	/**
	 * Saves the current environment to a file
	 * @param envFile The file to save to
	 */
	private void saveEnvironment(File envFile){
		
		if(environment != null){
			try {
				System.out.println(Language.translate("Speichere Umgebung nach ")+" "+envFile.getName());
				if(!envFile.exists()){
					envFile.createNewFile();
				}
				XMLCodec codec = new XMLCodec();
				String xmlRepresentation = codec.encodeObject(EnvironmentOntology.getInstance(), environment, true);
				if(!envFile.exists()){
					envFile.createNewFile();
				}
				FileWriter fw = new FileWriter(envFile);
				fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				fw.append(xmlRepresentation);
				fw.close();
			} catch (CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Loading a Physical2DEnvironment from a XML File
	 * @param envFile The XML file
	 * @return The environment
	 */
	private Physical2DEnvironment loadEnvironment(File envFile){
		Physical2DEnvironment env = null;
		if(envFile.exists()){
			try {
				System.out.println(Language.translate("Lade Umgebungsdatei")+" "+envFile.getName());
				BufferedReader br = new BufferedReader(new FileReader(envFile));
				StringBuffer xmlString = new StringBuffer();
				boolean keep = false;
				String line;
				while((line = br.readLine()) != null){
					// Read Physical2DEnvironment and child nodes only, skip metadata
					if(line.contains("<Physical2DEnvironment")){
						keep=true;
					}
					if(keep){
						xmlString.append(line);
					}
					if(line.contains("</Physical2DEnvironment"));
				}
				br.close();
				
				XMLCodec codec = new XMLCodec();
				env = (Physical2DEnvironment) codec.decodeObject(EnvironmentOntology.getInstance(), xmlString.toString());
			} catch (FileNotFoundException e) {
				System.err.println(Language.translate("Umgebungsdatei")+" "+envFile.getName()+" "+Language.translate("nicht gefunden"));
			} catch (IOException e) {
				System.err.println(Language.translate("Fehler beim Lesen der Umgebungsdatei")+" "+envFile.getName());
			} catch (CodecException e) {
				System.err.println("Fehler beim Parsen der Umgebungsdatei!");
			} catch (OntologyException e) {
				System.err.println("Fehler beim Parsen der Umgebungsdatei!");
			}
		}else{
			System.err.println(Language.translate("Umgebungsdatei")+" "+envFile.getPath()+" "+Language.translate("nicht gefunden"));
		}
		return env;
	}
	/**
	 * This method sets the environment and notifies the observers
	 * @param env The environment
	 */
	private void setEnvironment(Physical2DEnvironment env){
		this.environment = env;
		if(env != null){
			this.envWrap = new EnvironmentWrapper(env);
		}else{
			this.envWrap = null;
		}
		setChanged();
		notifyObservers(new Integer(ENVIRONMENT_CHANGED));
	}
	
	public void createOrChange(HashMap<String, Object> settings){
		if(selectedObject == null){
			// Create mode
			Physical2DObject newObject = createObject(settings);
			envWrap.addObject(newObject);
			setChanged();
			notifyObservers(new Integer(OBJECTS_CHANGED));
		}else{
			// Change mode
			if(selectedObject.getClass().equals(settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_ONTO_CLASS))){
				// Same object type, just change attributes
				selectedObject.setId((String) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_ID));
				selectedObject.setPosition((Position) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_POSITION));
				selectedObject.setSize((Size) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_SIZE));
				if(selectedObject instanceof ActiveObject){
					((ActiveObject)selectedObject).setMaxSpeed(Float.parseFloat((String) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_AGENT_MAX_SPEED)));
				}
			}else{
				envWrap.removeObject(selectedObject);
				envWrap.addObject(createObject(settings));
			}
		}
		setChanged();
		notifyObservers(new Integer(OBJECTS_CHANGED));
		
	}
	/**
	 * This method creates a new Physical2DObject according to the given settings
	 * 
	 * @param settings The object properties
	 * @return The created Physical2DObject
	 */
	public Physical2DObject createObject(HashMap<String, Object> settings){
		Physical2DObject newObject = null;
		
		try {
			Class<?> ontologyClass = (Class<?>) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_ONTO_CLASS);
			newObject = (Physical2DObject) ontologyClass.newInstance();
			newObject.setId(settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_ID).toString());
			newObject.setPosition((Position) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_POSITION));
			newObject.setSize((Size) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_SIZE));
			newObject.setParentPlaygroundID(environment.getRootPlayground().getId());
			if(newObject instanceof ActiveObject){
//				((ActiveObject)newObject).setClassName(settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_AGENT_CLASSNAME).toString());
				((ActiveObject)newObject).setMovement(new Movement());
				((ActiveObject)newObject).setMaxSpeed(Float.parseFloat((String) settings.get(EnvironmentSetupObjectSettings.SETTINGS_KEY_AGENT_MAX_SPEED)));	 
				
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newObject;
	}
	
	
	/**
	 * Removing the currently selected object from the environment
	 */
	public void removeObject(){
		envWrap.removeObject(selectedObject);
		setChanged();
		notifyObservers(new Integer(OBJECTS_CHANGED));
	}
	/**
	 * @return the selectedObject
	 */
	public Physical2DObject getSelectedObject() {
		return selectedObject;
	}
	
	/**
	 * Adds the object with the given objectID to the playground with the given playgroundID
	 * @param objectId
	 * @param playgroundId
	 * @return 
	 */
	public boolean moveObjectToPlayground(String objectId, String playgroundId){
		Physical2DObject object = envWrap.getObjectById(objectId);
		PlaygroundObject target = (PlaygroundObject) envWrap.getObjectById(playgroundId);
		if(playgroundContainsObject(object, target)){
			PlaygroundObject oldParent = (PlaygroundObject) envWrap.getObjectById(object.getParentPlaygroundID());
			oldParent.removeChildObjects(object);
			target.addChildObjects(object);
			object.setParentPlaygroundID(target.getId());
			setChanged();
			notifyObservers(new Integer(OBJECTS_CHANGED));
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Checks if the given PlaygroundObject completely contains the given Physical2DObject
	 * @param object The Physical2DObject
	 * @param playground The PlaygroundObject
	 * @return The result
	 */
	private boolean playgroundContainsObject(Physical2DObject object, PlaygroundObject playground){
		float objTopLeftX = object.getPosition().getXPos() - object.getSize().getWidth()/2;
		float objTopLeftY = object.getPosition().getYPos() - object.getSize().getHeight()/2;
		Rectangle2D.Float objRect = new Rectangle2D.Float(objTopLeftX, objTopLeftY, object.getSize().getWidth(), object.getSize().getHeight());
		
		float pgTopLeftX = playground.getPosition().getXPos() - playground.getSize().getWidth()/2;
		float pgTopLeftY = playground.getPosition().getYPos() - playground.getSize().getHeight()/2;
		Rectangle2D.Float pgRect = new Rectangle2D.Float(pgTopLeftX, pgTopLeftY, playground.getSize().getWidth(), playground.getSize().getHeight());
		
		return pgRect.contains(objRect);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 == project && arg1.toString().equals("SimSetups")){
			handleSetupChange((SimulationSetupsChangeNotification) arg1);
		}
	}
	
	private void handleSetupChange(SimulationSetupsChangeNotification sscn){
		
		switch(sscn.getUpdateReason()){
			
			case SimulationSetups.SIMULATION_SETUP_COPY:
				setDefaultFileNames();
				this.currentEnvironmentPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getEnvironmentFileName();
				this.currentSVGPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getSvgFileName();
				saveEnvironment(new File(this.currentEnvironmentPath));
				saveSVG(new File(this.currentSVGPath));
			break;
			
			case SimulationSetups.SIMULATION_SETUP_ADD_NEW:
				setDefaultFileNames();
				this.currentEnvironmentPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getEnvironmentFileName();
				this.currentSVGPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getSvgFileName();
				setEnvironment(null);
				setSvgDoc(null);
			break;
			
			case SimulationSetups.SIMULATION_SETUP_REMOVE:
				File envFile = new File(this.currentEnvironmentPath);
				File svgFile = new File(this.currentSVGPath);
				
				if(envFile.exists()){
					envFile.delete();
				}
				if(svgFile.exists()){
					svgFile.delete();
				}
			// No, there's no break missing here. After deleting a setup another one is loaded.
			
			case SimulationSetups.SIMULATION_SETUP_LOAD:
				this.currentEnvironmentPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getEnvironmentFileName();
				this.currentSVGPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getSvgFileName();
				setEnvironment(loadEnvironment(new File(this.currentEnvironmentPath)));
				setSvgDoc(loadSVG(new File(this.currentSVGPath)));
			break;
			
			case SimulationSetups.SIMULATION_SETUP_RENAME:
				File oldEnvFile = new File(this.currentEnvironmentPath);
				File oldSVGFile = new File(this.currentSVGPath);
				
				setDefaultFileNames();
				if(oldEnvFile.exists()){
					File newEnvFile = new File(this.baseFilePath+project.simSetups.getCurrSimSetup().getEnvironmentFileName());
					oldEnvFile.renameTo(newEnvFile);
				}
				
				if(oldSVGFile.exists()){
					File newSvgFile = new File(this.baseFilePath+project.simSetups.getCurrSimSetup().getSvgFileName());
					oldSVGFile.renameTo(newSvgFile);
				}
				
				this.currentEnvironmentPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getEnvironmentFileName();
				this.currentSVGPath = this.baseFilePath + project.simSetups.getCurrSimSetup().getSvgFileName();
			break;
		}
		
	}
	
	private void setDefaultFileNames(){
		String baseFileName = project.getProjectName()+"_"+project.simSetupCurrent;
		project.simSetups.getCurrSimSetup().setEnvironmentFileName(baseFileName+".xml");
		project.simSetups.getCurrSimSetup().setSvgFileName(baseFileName+".svg");
		
	}
	
}
