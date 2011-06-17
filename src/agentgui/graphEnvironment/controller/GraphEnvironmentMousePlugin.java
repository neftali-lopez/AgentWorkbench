/**
 * ***************************************************************
 * Agent.GUI is a framework to develop Multi-agent based simulation 
 * applications based on the JADE - Framework in compliance with the 
 * FIPA specifications. 
 * Copyright (C) 2010 Christian Derksen and DAWIS
 * http://sourceforge.net/projects/agentgui/
 * http://www.dawis.wiwi.uni-due.de/ 
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

package agentgui.graphEnvironment.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import agentgui.graphEnvironment.networkModel.GraphEdge;
import agentgui.graphEnvironment.networkModel.GraphNode;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;

/**
 * Handling mouse interaction with graph visualizations in a BasicGraphGUI.
 * @author Nils
 * @author Satyadeep
 */
public class GraphEnvironmentMousePlugin extends PickingGraphMousePlugin<GraphNode, GraphEdge> {
	/**
	 * The parent BasicGraphGUI
	 */
	private BasicGraphGUI myGUI = null;
	
	/**
	 * Constructor
	 * @param parentGUI The parent BasicGraphGUI
	 */
	public GraphEnvironmentMousePlugin(BasicGraphGUI parentGUI) {
		super();
//		this.locked = true;
		this.myGUI = parentGUI;
	}

	@Override
	public void mouseClicked(MouseEvent e){
		// Left click or Right click

		if(e.getButton()==MouseEvent.BUTTON1 || e.getButton()==MouseEvent.BUTTON3){			
			
			Object pickedObject = null;
			
			Point point = e.getPoint();
			GraphElementAccessor<GraphNode, GraphEdge>ps = myGUI.getVisView().getPickSupport();
			
			// Get the graph node / PropagationPoint at the clicked coordinates
			GraphNode pickedPP = ps.getVertex(myGUI.getVisView().getGraphLayout(), point.getX(), point.getY());
			
			if(pickedPP != null){		// A node / PropagationPoint was clicked
				pickedObject = pickedPP;
			}else{			// No node / PropagationPoint was clicked
				
				// Get the graph edge / GridComponent at the clicked coordinates
				GraphEdge pickedGC = ps.getEdge(myGUI.getVisView().getGraphLayout(), point.getX(), point.getY());
				
				if(pickedGC != null){	// An edge / GridComponent was clicked
					pickedObject = pickedGC;
				}
			}
			if(pickedObject != null) // only when node or edge is clicked
			{
				//Double click
				if (e.getClickCount()==2){
					myGUI.handleObjectDoubleClick(pickedObject);
				}
				//Shift + Left click				
				else if(e.getButton()==MouseEvent.BUTTON1 && e.isShiftDown())
					myGUI.handleObjectShiftLeftClick(pickedObject);
				//Shift + Right click
				else if(e.getButton()==MouseEvent.BUTTON3 && e.isShiftDown())
					myGUI.handleObjectShiftRightClick(pickedObject);
				//Left click
				else if(e.getButton()==MouseEvent.BUTTON1)
					myGUI.handleObjectSelection(pickedObject);
				//Right click
				else if(e.getButton()==MouseEvent.BUTTON3)
					myGUI.handleObjectRightClick(pickedObject);		
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		super.mouseDragged(e);
		
		// Update the GraphNode's position attribute 
		Iterator<GraphNode> pickedNodes= myGUI.getVisView().getPickedVertexState().getPicked().iterator();
		while(pickedNodes.hasNext()){
			GraphNode pickedNode = pickedNodes.next();
			pickedNode.setPosition(myGUI.getVisView().getGraphLayout().transform(pickedNode));
		}
	}
}
