package agentgui.core.application;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import agentgui.core.gui.ProjectNewOpen;
import agentgui.core.gui.ProjectWindow;
import agentgui.core.ontologies.Ontologies4Project;
import agentgui.core.sim.setup.SimulationSetups;


public class ProjectsLoaded {

	// --- Listing of the open projects -------------------
	private ArrayList<Project> ProjectsOpen = new ArrayList<Project>();
	
	/**
	 * Adding (Creating or Opening) a new Project to the Application
	 * @param addNew
	 * @return Project
	 */
	public Project add ( boolean addNew ) {

		String ActionTitel = null;
		String ProjectNameTest = null;
		String ProjectFolderTest = null;
		String LocalTmpProjectName = null;
		String LocalTmpProjectFolder = null;
		
		// ------------------------------------------------
		// --- Define a new Project-Instance -------------- 
		Project NewPro = new Project();
		
		// ------------------------------------------------
		// --- Startbedingenen f�r "New" oder "Open" ------
		if ( addNew == true ){
			// --------------------------------------------
			// --- Anlegen eines neuen Projekts -----------
			ActionTitel = Language.translate("Neues Projekt anlegen");
			
			// --- Neuen, allgemeinen Projektnamen finden -----		
			String ProjectNamePrefix = Language.translate("Neues Projekt");
			ProjectNameTest = ProjectNamePrefix;
			int Index = Application.Projects.getIndexByName(ProjectNameTest);
			int i = 2;
			while ( Index != -1 ) {
				ProjectNameTest = ProjectNamePrefix + " " + i;
				Index = Application.Projects.getIndexByName( ProjectNameTest );
				i++;
			}
			ProjectFolderTest = ProjectNameTest.toLowerCase().replace(" ", "_");
		}
		else {
			// --------------------------------------------
			// --- �ffnen eine vorhandenen Projekts -------
			ActionTitel = Language.translate("Projekt �ffnen");			
		}
		Application.MainWindow.setStatusBar(ActionTitel + " ...");
		
		// ------------------------------------------------
		// --- Benutzer-Dialog �ffnen ---------------------
		ProjectNewOpen NewProDia = new ProjectNewOpen( Application.MainWindow, Application.RunInfo.AppTitel() + ": " + ActionTitel, true, addNew );
		NewProDia.setVarProjectName( ProjectNameTest );
		NewProDia.setVarProjectFolder( ProjectFolderTest );
		NewProDia.setVisible(true);
		// === Hier geht's weiter, wenn der Dialog wieder geschlossen ist ===
		if ( NewProDia.isCanceled() == true ) {
			Application.setStatusBar( Language.translate("Fertig") );
			return null;
		} else {
			LocalTmpProjectName = NewProDia.getVarProjectName();
			LocalTmpProjectFolder = NewProDia.getVarProjectFolder(); 
		}
		NewProDia.dispose();
		NewProDia = null;	

		// ------------------------------------------------
		// --- ClassLoader entladen -----------------------
		if(ProjectsOpen.size()!=0) {
			Application.ProjectCurr.resourcesRemove();
		}
		
		// ------------------------------------------------
		// --- Projektvariablen setzen --------------------
		NewPro.setProjectName( LocalTmpProjectName );
		NewPro.setProjectFolder( LocalTmpProjectFolder );

		if ( addNew == true ) {			
			// --- Standardstruktur anlegen ---------------
			NewPro.createDefaultProjectStructure();
		} 
		else {
			// --- XML-Datei einlesen ---------------------
			JAXBContext pc;
			Unmarshaller um = null;
			String XMLFileName = NewPro.getProjectFolderFullPath() + Application.RunInfo.getFileNameProject();			
			try {
				pc = JAXBContext.newInstance( NewPro.getClass() );
				um = pc.createUnmarshaller();
				NewPro = (Project) um.unmarshal( new FileReader( XMLFileName ) );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			// --- Folder auf aktuellen Projektordner einstellen ---
			NewPro.setProjectFolder( LocalTmpProjectFolder );	
			
			// --- check/create default folders -------------------- 
			NewPro.checkCreateSubFolders();
		}
		
		// --- ClassLoader/CLASSPATH laden ----------------
		NewPro.resourcesLoad();
		
		// --- Das Ontologie-Objekt beladen --------------- 
		NewPro.ontologies4Project = new Ontologies4Project(NewPro);

		// --- ggf. AgentGUI - DefaultProfile �bernehmen --
		if( NewPro.JadeConfiguration.isUseDefaults()==true) {
			NewPro.JadeConfiguration = Application.RunInfo.getJadeDefaultPlatformConfig();
		}
		
		// --- Gibt es bereits ein Simulations-Setup? -----
		if (NewPro.simSetups.size()==0) {
			NewPro.simSetups = new SimulationSetups(NewPro, "default");
			NewPro.simSetups .setupCreateDefault();			
		}
		
		// --- Neues Projektfenster �ffnen ----------------
		NewPro.ProjectGUI = new ProjectWindow( NewPro );
		
		// --- Projekt als aktuelle markieren ------------- 
		NewPro.ProjectUnsaved = false;
				
		// --- Objekt an die Projektauflistung h�ngen -----
		ProjectsOpen.add( NewPro );
		Application.ProjectCurr = NewPro;

		// --- Anzeige anpassen ---------------------------
		Application.Projects.setProjectMenuItems();		
		Application.MainWindow.setCloseButtonPosition( true );
		Application.setTitelAddition( NewPro.getProjectName() );
		Application.setStatusBar( Language.translate("Fertig") );	
		NewPro.setMaximized();
		if (addNew==true) {
			NewPro.save();   // --- Erstmalig speichern ---	
		}		
		
		// --- Hier erfolgt gerade eine Testausgabe zu debugging - Zwecken. Wird bald wieder gel�scht ----
		System.out.println( "");
		System.out.println( "+++ Versuch ....  ohhjjjeeeee .... START +++");
//		@SuppressWarnings("unused")
//		DynForm df = new DynForm(NewPro, "monitormas.agents.ControlAgent");
		System.out.println( "+++ Versuch ....  ohhjjjeeeee .... ENDE +++");
		
		return NewPro;
	}

	/**
	 * Trys to close all current opened projects
	 * @return
	 */
	public boolean closeAll() {		
		// --- Alle "aktuellen" Projekte schlie�en --------
		while ( Application.ProjectCurr != null ) {
			if ( Application.ProjectCurr.close() == false  ) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns the Project-Object
	 * @param String ProjectName
	 * @return
	 */
	public Project get ( String ProjectName ) {
		int Index = getIndexByName( ProjectName );
		if ( Index == -1 ) {
			// --- Falls der Verzeichnisname genommen wurde ----
			Index = getIndexByFolderName( ProjectName );
		}
		return get(Index);
	}
	/**
	 * Returns the Project-Object
	 * @param int Index
	 * @return
	 */
	public Project get( int Index ) {
		return ProjectsOpen.get(Index);
	}

	/**
	 * Removes a single Project
	 * @param String Project2Remove
	 */
	public void remove( Project Project2Remove ) {
		ProjectsOpen.remove(Project2Remove);		
	}
	/**
	 * Removes all Projects from the (Array) ProjectList
	 */
	public void removeAll( ) {
		ProjectsOpen.clear();
		Application.ProjectCurr = null;
		Application.Projects.setProjectMenuItems();		
	}

	/**
	 * Identifies a Project by his name and returns the Array-/Window-Index
	 * @param ProjectName
	 * @return
	 */
	public int getIndexByName ( String ProjectName ) {
		int Index = -1;
		for(int i=0; i<this.count(); i++) {
			if( ProjectsOpen.get(i).getProjectName().equalsIgnoreCase(ProjectName) ) {
				Index = i;
				break;
			}	
		}
		return Index;
	}
	/**
	 * Identifies a Project by his Root-Folder-Name and returns the Array-/Window-Index
	 * @param ProjectName
	 * @return
	 */
	public int getIndexByFolderName ( String ProjectName ) {
		int Index = -1;
		for(int i=0; i<this.count(); i++) {
			if( ProjectsOpen.get(i).getProjectFolder().toLowerCase().equalsIgnoreCase( ProjectName.toLowerCase() ) ) {
				Index = i;
				break;
			}	
		}
		return Index;
	}
	/**
	 * Counts the actual open projects
	 */
	public int count() {
		return ProjectsOpen.size();		
	}

	/**
	 * Create's the Window=>MenuItems depending on the open projects 
	 */
	public void setProjectMenuItems() {
		
		boolean SetFontBold = true;
		
		JMenu WindowMenu = Application.MainWindow.jMenuMainWindow;
		WindowMenu.removeAll();
		if (this.count() == 0 ){
			WindowMenu.add( new JMenuItmen_Window( Language.translate("Kein Projekt ge�ffnet !"), -1, SetFontBold ) );
		}
		else {
			for(int i=0; i<this.count(); i++) {
				String ProjectName = ProjectsOpen.get(i).getProjectName();
				if ( ProjectName.equalsIgnoreCase( Application.ProjectCurr.getProjectName() ) ) 
					SetFontBold = true;
				else 
					SetFontBold = false;
				WindowMenu.add( new JMenuItmen_Window( ProjectName, i, SetFontBold) );
			}		
		}
	}	
	
	/**
	 * Creates a single MenueItem for the Window-Menu depending on the open projects  
	 * @author derksen
	 */
	// --- Unterklasse f�r die Men�elemente "Fenster" => Projekte --------
	private class JMenuItmen_Window extends JMenuItem  {
 
		private static final long serialVersionUID = 1L;
		
		private JMenuItmen_Window( String ProjectName, int WindowIndex, boolean setBold  ) {
			
			final int WinIdx = WindowIndex;
			int WinNo = WindowIndex + 1;
			
			if ( WinNo <= 0 ) {
				this.setText( ProjectName );
			}
			else {
				this.setText( WinNo + ": " + ProjectName );
			}		
			if ( setBold ) {
				Font cfont = this.getFont();
				if ( cfont.isBold() == true ) {
					this.setForeground( Application.RunInfo.ColorMenuHighLight() );	
				}
				else {
					this.setFont( cfont.deriveFont(Font.BOLD) );
				}
			}
			this.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					Application.Projects.setFocus( WinIdx );							
				}
			});		
		}
	}
	private void setFocus( int Index ) {
		this.get(Index).setFocus(true);		
	}


		
}