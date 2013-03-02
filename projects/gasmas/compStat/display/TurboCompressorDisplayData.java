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
package gasmas.compStat.display;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The Class TurboCompressorDisplayData.
 */
public class TurboCompressorDisplayData extends JPanel implements ParameterListener {

	private static final long serialVersionUID = 1L;

	private JPanel jPanelSpeedPanel = null;
	private JPanel jPanelGeneralInfo = null;
	
	private JLabel jLabelID;
	private JLabel jLabelAlias = null;
	private JLabel jLabelDrive = null;
	
	private JLabel jLabelSpeedMin = null;
	private JLabel jLabelSpeedMax = null;

	private JTextField jTextFieldID;
	private JTextField jTextFieldAlias = null;
	private JTextField jTextFieldSpeedMin = null;
	private JTextField jTextFieldSpeedMax = null;
	
	private JComboBox jComboBoxDrive = null;
	private JButton jButtonDriveNew = null;
	
	private Calc9ParameterDisplay nIsolineCoeff;
	private Calc9ParameterDisplay adiabaticEffCoeff;

	private JPanel jPanelTop = null;
	
	/**
	 * This is the default constructor
	 */
	public TurboCompressorDisplayData() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * @return void
	 */
	private void initialize() {
		
		GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		gridBagConstraints20.gridx = 0;
		gridBagConstraints20.anchor = GridBagConstraints.WEST;
		gridBagConstraints20.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints20.gridy = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.gridy = 0;
		
		
		
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.gridx = 0;
		gridBagConstraints19.gridwidth = 1;
		gridBagConstraints19.anchor = GridBagConstraints.WEST;
		gridBagConstraints19.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints19.gridy = 2;
		
		this.setSize(531, 410);
		this.setLayout(new GridBagLayout());
		this.add(getAdiabaticEffCoeff(), gridBagConstraints19);
		this.add(getJPanelTop(), gridBagConstraints);
		this.add(getNisolineCoeff(), gridBagConstraints20);
	}

	/**
	 * This method initializes jPanelGeneralInfo	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGeneralInfo() {
		if (jPanelGeneralInfo == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints12.gridy = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 0.1;
			gridBagConstraints11.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints11.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 2;
			gridBagConstraints10.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.weightx = 0.0;
			gridBagConstraints8.gridwidth = 2;
			gridBagConstraints8.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints8.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.insets = new Insets(5, 0, 0, 0);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.gridy = -1;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			
			jLabelID = new JLabel();
			jLabelID.setText("ID");
			jLabelAlias = new JLabel();
			jLabelAlias.setText("Alias");
			jLabelDrive = new JLabel();
			jLabelDrive.setText("Drive");

			jPanelGeneralInfo = new JPanel();
			jPanelGeneralInfo.setSize(new Dimension(450, 250));
			jPanelGeneralInfo.setBorder(BorderFactory.createEmptyBorder());
			jPanelGeneralInfo.setLayout(new GridBagLayout());
			jPanelGeneralInfo.add(jLabelID, gridBagConstraints2);
			jPanelGeneralInfo.add(getJTextFieldID(), gridBagConstraints3);
			jPanelGeneralInfo.add(jLabelAlias, gridBagConstraints5);
			jPanelGeneralInfo.add(getJTextFieldAlias(), gridBagConstraints8);
			jPanelGeneralInfo.add(jLabelDrive, gridBagConstraints10);
			jPanelGeneralInfo.add(getJComboBoxDrive(), gridBagConstraints11);
			jPanelGeneralInfo.add(getJButtonDriveNew(), gridBagConstraints12);
		}
		return jPanelGeneralInfo;
	}
	/**
	 * Gets the JTextField for the ID.
	 * @return the JTextField for the ID
	 */
	private JTextField getJTextFieldID() {
		if (jTextFieldID==null) {
			jTextFieldID = new JTextField();	
		}
		return jTextFieldID;
	}
	/**
	 * This method initializes jTextFieldAlias	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAlias() {
		if (jTextFieldAlias == null) {
			jTextFieldAlias = new JTextField();
		}
		return jTextFieldAlias;
	}
	/**
	 * This method initializes jComboBoxDrive	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxDrive() {
		if (jComboBoxDrive == null) {
			jComboBoxDrive = new JComboBox();
		}
		return jComboBoxDrive;
	}
	/**
	 * This method initializes jButtonDriveNew	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDriveNew() {
		if (jButtonDriveNew == null) {
			jButtonDriveNew = new JButton();
			jButtonDriveNew.setText(" + ");
			
		}
		return jButtonDriveNew;
	}
	
	/**
	 * This method initializes jPanelSpeedPanel	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSpeedPanel() {
		if (jPanelSpeedPanel == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.weightx = 0.0;
			gridBagConstraints9.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridy = -1;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = -1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridx = 0;
			
			jLabelSpeedMin = new JLabel();
			jLabelSpeedMin.setText("Speed Min");
			jLabelSpeedMax = new JLabel();
			jLabelSpeedMax.setText("Speed Max");
			
			jPanelSpeedPanel = new JPanel();
			jPanelSpeedPanel.setSize(new Dimension(450, 250));
			jPanelSpeedPanel.setBorder(BorderFactory.createEmptyBorder());
			jPanelSpeedPanel.setLayout(new GridBagLayout());
			jPanelSpeedPanel.add(jLabelSpeedMax, gridBagConstraints1);
			jPanelSpeedPanel.add(jLabelSpeedMin, gridBagConstraints4);
			jPanelSpeedPanel.add(getJTextFieldSpeedMin(), gridBagConstraints7);
			jPanelSpeedPanel.add(getJTextFieldSpeedMax(), gridBagConstraints9);
		}
		return jPanelSpeedPanel;
	}
	/**
	 * This method initializes jTextFieldSpeedMin	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSpeedMin() {
		if (jTextFieldSpeedMin == null) {
			jTextFieldSpeedMin = new JTextField();
		}
		return jTextFieldSpeedMin;
	}
	/**
	 * This method initializes jTextFieldSpeedMax	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSpeedMax() {
		if (jTextFieldSpeedMax == null) {
			jTextFieldSpeedMax = new JTextField();
		}
		return jTextFieldSpeedMax;
	}
	
	/**
	 * Gets the n isoline coeff.
	 * @return the n isoline coeff
	 */
	private Calc9ParameterDisplay getNisolineCoeff() {
		if (nIsolineCoeff==null) {
			nIsolineCoeff = new Calc9ParameterDisplay("Coefficients for the isolines of speed");
			nIsolineCoeff.addCalcParameterListener(this);
		}
		return nIsolineCoeff;
	}
	/**
	 * Gets the adiabatic eff coeff.
	 * @return the adiabatic eff coeff
	 */
	private Calc9ParameterDisplay getAdiabaticEffCoeff() {
		if (adiabaticEffCoeff==null) {
			adiabaticEffCoeff = new Calc9ParameterDisplay("Coefficients for the isolines of the adiabatic efficiency");
			adiabaticEffCoeff.addCalcParameterListener(this);
		}
		return adiabaticEffCoeff;
	}
	
	@Override
	public void parameterChanged(ParameterDisplay display, int noOfParameter, Float value) {

	}
	/**
	 * This method initializes jPanelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTop() {
		if (jPanelTop == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints6.gridwidth = 1;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints14.insets = new Insets(5, 5, 0, 5);
			gridBagConstraints14.gridx = -1;
			gridBagConstraints14.gridy = -1;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.gridx = -1;
			gridBagConstraints13.gridy = -1;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.setBorder(BorderFactory.createEmptyBorder());
			jPanelTop.add(getJPanelGeneralInfo(), gridBagConstraints13);
			jPanelTop.add(getJPanelSpeedPanel(), gridBagConstraints14);
		}
		return jPanelTop;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
