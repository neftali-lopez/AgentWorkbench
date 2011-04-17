package agentgui.graphEnvironment.controller;

import jade.content.Concept;

import javax.swing.JDialog;

import agentgui.core.application.Application;
import agentgui.core.application.Language;
import agentgui.core.application.Project;
import agentgui.core.ontologies.gui.OntologyInstanceViewer;
import agentgui.graphEnvironment.environmentModel.GraphEdge;
import agentgui.graphEnvironment.environmentModel.GraphElement;
import agentgui.graphEnvironment.environmentModel.GraphNode;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
/**
 * GUI component for setting the properties of an ontology object representing a grid component
 * @author Nils
 *
 */
public class ComponentSettingsDialog extends JDialog implements ActionListener{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1745551171293051322L;
	private JPanel jPanelContent = null;
	private JButton jButtonApply = null;
	private JButton jButtonAbort = null;
	/**
	 * The simulation project
	 */
	private Project project = null;
	/**
	 * The graph node containing the ontology object
	 */
	private GraphElement element = null;
	
	private GraphEnvironmentControllerGUI parentGUI = null;
	
	private OntologyInstanceViewer oiv = null;
	/**
	 * Constructor
	 * @param project The simulation project
	 * @param parentGUI The GraphEnvironmentControllerGUI that opened the dialog
	 * @param element The GraphElement containing the ontology object
	 */
	public ComponentSettingsDialog(Project project, GraphEnvironmentControllerGUI parentGUI, GraphElement element){
		super(Application.MainWindow, Dialog.ModalityType.APPLICATION_MODAL);
		this.project = project;
		this.parentGUI = parentGUI;
		this.element = element;
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setContentPane(getJPanelContent());
        if(element instanceof GraphEdge){
        	this.setTitle("GridComponent "+element.getId());
        }else if(element instanceof GraphNode){
        	this.setTitle("PropagationPoint PP"+element.getId());
        }
        this.setSize(new Dimension(450, 450));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(getJButtonApply())){
			oiv.save();
			element.setOntologyRepresentation((Concept) oiv.getConfigurationInstances()[0]);
			parentGUI.componentSettingsChanged();
			this.dispose();
		}else if(e.getSource().equals(getJButtonAbort())){
			parentGUI.componentSettingsChangeAborted();
			this.dispose();
		}
	}

	/**
	 * This method initializes jPanelContent	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelContent() {
		if (jPanelContent == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.weightx = 1.0D;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 1;
			jPanelContent = new JPanel();
			jPanelContent.setLayout(new GridBagLayout());
			jPanelContent.add(getJButtonApply(), gridBagConstraints);
			jPanelContent.add(getJButtonAbort(), gridBagConstraints1);
			
		
			if(element instanceof GraphEdge){
				oiv = new OntologyInstanceViewer(project, parentGUI.getController().getGraphElementSettings().get(((GraphEdge) element).getType()).getAgentClass());
			}else if(element instanceof GraphNode){
				String[] ontoClassName = new String[1];
				ontoClassName[0] = parentGUI.getController().getGraphElementSettings().get("node").getAgentClass();
				oiv = new OntologyInstanceViewer(project, ontoClassName);

				if(element.getOntologyRepresentation() != null){
					Object[] ontoObject = new Object[1];
					ontoObject[0] = element.getOntologyRepresentation();
					oiv.setConfigurationInstances(ontoObject);
				}
				
			}
			
			oiv.setAllowViewEnlargement(false);
			jPanelContent.add(oiv, gridBagConstraints11);
			
		}
		return jPanelContent;
	}

	/**
	 * This method initializes jButtonApply	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonApply() {
		if (jButtonApply == null) {
			jButtonApply = new JButton();
			jButtonApply.setText(Language.translate("‹bernehmen"));
			jButtonApply.addActionListener(this);
		}
		return jButtonApply;
	}

	/**
	 * This method initializes jButtonAbort	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAbort() {
		if (jButtonAbort == null) {
			jButtonAbort = new JButton();
			jButtonAbort.setText(Language.translate("Abbrechen"));
			jButtonAbort.addActionListener(this);
		}
		return jButtonAbort;
	}

}
