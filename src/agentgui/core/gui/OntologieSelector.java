/**
 * ***************************************************************
 * Agent.GUI is a framework to develop Multi-agent based simulation 
 * applications based on the JADE - Framework in compliance with the 
 * FIPA specifications. 
 * Copyright (C) 2010 Christian Derksen and DAWIS
 * http://www.dawis.wiwi.uni-due.de
 * http://sourceforge.net/projects/agentgui/
 * http://www.agentgui.org 
 *
 * GNU Lesser General Public License
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */
package agentgui.core.gui;

import jade.content.onto.Ontology;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import agentgui.core.application.Language;
import agentgui.core.gui.components.ClassElement2Display;
import agentgui.core.gui.components.JListClassSearcher;
import agentgui.core.jade.ClassSearcher;

/**
 * This GUI allows the selection of a class, which extend the 'jade.content.onto.Ontology'
 * and represents herewith the head class of an ontology 
 * 
 * @see Ontology
 *
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class OntologieSelector extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Boolean canceled = false;  //  @jve:decl-index=0:
	private String ontologySelected = null;
	
	private JPanel jContentPane = null;
	private JListClassSearcher jListOntologies = null;
	private JButton jButtonSelect = null;
	private JButton jButtonCancel = null;
	private JLabel jLabelOntoList = null;
	private JPanel jPanelShowOptions = null;
	private ButtonGroup buttonGroup = null;  //  @jve:decl-index=0:
	private JRadioButton jRadioButtonShowAll = null;	
	private JRadioButton jRadioButtonShowNoneJade = null;
	
	private JLabel jLabelShow = null;
	private JTextField jTextFieldSearch = null;

	/**
	 * Constructor equal to JDialog plus the current instance of project 
	 * @param owner
	 * @param titel
	 * @param modal
	 */
	public OntologieSelector(Frame owner, String titel, boolean modal) {
		super(owner, titel, modal);
		
		// --- Dialog aufbauen ---------------------------- 
		initialize();
		
		// --- Anzeige einstellen / filtern ---------------
		this.filterOntology();
		
		// --- Dialog zentrieren --------------------------
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		int top = (screenSize.height - this.getHeight()) / 2; 
	    int left = (screenSize.width - this.getWidth()) / 2; 
	    this.setLocation(left, top);	

		// --- Anzeigesprache festlegen -------------------
		this.setTitle(Language.translate("Auswahl - Ontologien"));
	    jLabelOntoList.setText(Language.translate("Suche"));

	    jLabelShow.setText(Language.translate("Ansicht:"));
	    jRadioButtonShowAll.setText(Language.translate("Alle Ontologien"));
		jRadioButtonShowNoneJade.setText(Language.translate("Nur Nicht-JADE-Ontologien"));
	    
	    jButtonSelect.setText(Language.translate("Hinzuf�gen"));
	    jButtonCancel.setText(Language.translate("Abbrechen"));
	}

	/**
	 * This method initializes this
	 * @return void
	 */
	private void initialize() {
		this.setSize(721, 500);
		this.setResizable(false);
		this.setTitle("Auswahl - Ontologien");
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				canceled = true;
				setVisible(false);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {

			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.insets = new Insets(20, 20, 0, 0);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 1;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.insets = new Insets(5, 20, 0, 20);

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.insets = new Insets(5, 20, 0, 20);
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.anchor = GridBagConstraints.CENTER;
			gridBagConstraints4.insets = new Insets(5, 20, 7, 20);
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 4;
			gridBagConstraints2.weightx = 0.5;
			gridBagConstraints2.insets = new Insets(10, 0, 25, 0);
			
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 4;
			gridBagConstraints1.weightx = 0.5;
			gridBagConstraints1.insets = new Insets(10, 0, 25, 0);
			
			
			jLabelOntoList = new JLabel();
			jLabelOntoList.setText("Suche");
			jLabelOntoList.setFont(new Font("Dialog", Font.BOLD, 12));
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabelOntoList, gridBagConstraints);
			jContentPane.add(getJTextFieldSearch(), gridBagConstraints12);
			jContentPane.add(getJListOntologies(), gridBagConstraints11);
			jContentPane.add(getJPanelShowOptions(), gridBagConstraints4);
			jContentPane.add(getJButtonSelect(), gridBagConstraints1);
			jContentPane.add(getJButtonCancel(), gridBagConstraints2);		}
		return jContentPane;
	}

	/**
	 * This method initializes jListOntologies	
	 * @return javax.swing.JList	
	 */
	private JListClassSearcher getJListOntologies() {
		if (jListOntologies == null) {
			jListOntologies = new JListClassSearcher(ClassSearcher.CLASSES_ONTOLOGIES);
			jListOntologies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListOntologies.jListLoading.addMouseListener( new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					if (me.getClickCount() == 2 ) {
						jButtonSelect.doClick();	
					}
				}
			});
		}
		return jListOntologies;
	}

	/**
	 * This will filter the list of Agents depending on 
	 * the content of the Input-Parameter 
	 * @param filter4
	 */
	private void filterOntology() {
		
		String actCMD = buttonGroup.getSelection().getActionCommand();
		if (actCMD.equals("NoneJadeOntologiesOnly")) {
			jButtonSelect.setEnabled(true);
			jListOntologies.setModelFiltered(jTextFieldSearch.getText(), "jade.");	
		} else {
			jButtonSelect.setEnabled(false);
			jListOntologies.setModelFiltered(jTextFieldSearch.getText());	
		}
	}
	/**
	 * This method initializes jTextFieldSearch	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSearch() {
		if (jTextFieldSearch == null) {
			jTextFieldSearch = new JTextField();
			jTextFieldSearch.setPreferredSize(new Dimension(250, 26));
			jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					filterOntology();
				}
			});
		}
		return jTextFieldSearch;
	}
	
	/**
	 * This method initializes jButtonSelect	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSelect() {
		if (jButtonSelect == null) {
			jButtonSelect = new JButton();
			jButtonSelect.setText("Hinzuf�gen");
			jButtonSelect.setForeground(new Color(0, 153, 0));
			jButtonSelect.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonSelect.setActionCommand("AddOntology");
			jButtonSelect.setPreferredSize(new Dimension(100, 26));
			jButtonSelect.addActionListener(this);
		}
		return jButtonSelect;
	}

	/**
	 * This method initializes jButtonCancel	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			jButtonCancel = new JButton();
			jButtonCancel.setText("Abbrechen");
			jButtonCancel.setForeground(new Color(153, 0, 0));
			jButtonCancel.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonCancel.setActionCommand("Cancel");
			jButtonCancel.setPreferredSize(new Dimension(100, 26));
			jButtonCancel.addActionListener(this);
		}
		return jButtonCancel;
	}

	public boolean isCanceled(){
		return canceled;
	}
	/**
	 * @return the ontologySelected
	 */
	public String getNewOntologySelected() {
		return ontologySelected;
	}
	
	/**
	 * This method initializes jPanelShowOptions	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowOptions() {
		if (jPanelShowOptions == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints6.gridy = 0;
			
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints5.gridy = 0;
			
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints3.gridy = -1;
			
			jLabelShow = new JLabel();
			jLabelShow.setText("Ansicht:");
			jLabelShow.setFont(new Font("Dialog", Font.BOLD, 12));
			
			jPanelShowOptions = new JPanel();
			jPanelShowOptions.setLayout(new GridBagLayout());
			jPanelShowOptions.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanelShowOptions.setPreferredSize(new Dimension(366, 36));
			jPanelShowOptions.add(getJRadioButtonShowAll(), gridBagConstraints3);
			jPanelShowOptions.add(getJRadioButtonShowNoneJade(), gridBagConstraints5);
			jPanelShowOptions.add(jLabelShow, gridBagConstraints6);
			
			// --- Button-Group definieren --------------------------
			buttonGroup = new ButtonGroup();
			buttonGroup.add(jRadioButtonShowAll);
			buttonGroup.add(jRadioButtonShowNoneJade);
		}
		return jPanelShowOptions;
	}

	/**
	 * This method initializes jRadioButtonShowAll	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonShowAll() {
		if (jRadioButtonShowAll == null) {
			jRadioButtonShowAll = new JRadioButton();
			jRadioButtonShowAll.setText("Alle Ontologien");
			jRadioButtonShowAll.setActionCommand("AllOntologies");
			jRadioButtonShowAll.setMnemonic(KeyEvent.VK_UNDEFINED);
			jRadioButtonShowAll.addActionListener(this);
		}
		return jRadioButtonShowAll;
	}
	/**
	 * This method initializes jRadioButtonShowNoneJade	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonShowNoneJade() {
		if (jRadioButtonShowNoneJade == null) {
			jRadioButtonShowNoneJade = new JRadioButton();
			jRadioButtonShowNoneJade.setText("Nur Nicht-JADE-Ontologien");
			jRadioButtonShowNoneJade.setActionCommand("NoneJadeOntologiesOnly");
			jRadioButtonShowNoneJade.setSelected(true);
			jRadioButtonShowNoneJade.addActionListener(this);
		}
		return jRadioButtonShowNoneJade;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String ActCMD = ae.getActionCommand();
		String MsgHead, MsgText;
		
		if ( ActCMD == "AddOntology" ) {
			if ( jListOntologies.getSelectedValue()!=null ) {
				ClassElement2Display classSelected = (ClassElement2Display) jListOntologies.getSelectedValue();
				ontologySelected = classSelected.toString();
				this.setVisible(false);
			} else {
				MsgHead = Language.translate("Fehlende Ontologie-Auswahl !");
				MsgText = Language.translate("Bitte w�hlen Sie die Ontologie aus, die Ihrem Projekt hinzugef�gt werden soll !");			
				JOptionPane.showInternalMessageDialog( this.getContentPane(), MsgText, MsgHead, JOptionPane.WARNING_MESSAGE);
			}
		} else if ( ActCMD == "Cancel" ) {
			this.canceled = true;
			this.setVisible(false);
		} else if ( ActCMD == "AllOntologies" ) {
			this.filterOntology();
		} else if ( ActCMD == "NoneJadeOntologiesOnly" ) {
			this.filterOntology();
		} else {
			System.out.println( "Unknown ActionCommand: " + ActCMD );
		};
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
