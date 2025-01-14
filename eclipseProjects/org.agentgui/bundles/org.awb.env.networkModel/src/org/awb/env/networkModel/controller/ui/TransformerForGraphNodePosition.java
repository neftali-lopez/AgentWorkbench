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
package org.awb.env.networkModel.controller.ui;

import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;
import org.awb.env.networkModel.GraphNode;
import org.awb.env.networkModel.NetworkModel;
import org.awb.env.networkModel.controller.GraphEnvironmentController;
import org.awb.env.networkModel.maps.MapSettings;
import org.awb.env.networkModel.maps.MapSettings.MapScale;
import org.awb.env.networkModel.settings.LayoutSettings;
import org.awb.env.networkModel.settings.LayoutSettings.CoordinateSystemXDirection;
import org.awb.env.networkModel.settings.LayoutSettings.CoordinateSystemYDirection;

/**
 * The Class TransformerForGraphNodePosition justifies a {@link GraphNode} position in the visualization viewer 
 * according to the {@link LayoutSettings}, where the position of the used coordinate system is specified 
 * by a {@link CoordinateSystemXDirection} and a {@link CoordinateSystemYDirection}.
 *
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class TransformerForGraphNodePosition<V, E> implements Transformer<GraphNode, Point2D> {

	private GraphEnvironmentController graphController;
	private LayoutSettings layoutSettings;
	private MapSettings mapSettings;

	
	/**
	 * Instantiates a new transformer for node position that dynamically 'asks' the graph controller 
	 * about the current and possibly changed settings in the {@link NetworkModel} (dynamic approach).
	 * 
	 * @param graphController the current graph controller
	 */
	public TransformerForGraphNodePosition(GraphEnvironmentController graphController) {
		this.graphController = graphController;
	}
	/**
	 * Instantiates a new transformer for node position that statically uses the specified {@link LayoutSettings}.
	 * In contrast to the other class constructor, this will not consider changes in a {@link NetworkModel}.
	 *  
	 * @param layoutSettings the layout settings
	 */
	public TransformerForGraphNodePosition(LayoutSettings layoutSettings) {
		this.layoutSettings = layoutSettings;
	}
	
	
	/**
	 * Returns the current or initially specified layout settings.
	 * @return the layout settings
	 */
	private LayoutSettings getLayoutSettings() {
		// --- Try to get current settings from GraphEnvironmentController ---- 
		if (this.graphController!=null) {
			return this.graphController.getNetworkModel().getLayoutSettings();
		}
		// --- Return the specified LayoutSettings (not dynamic) --------------
		return layoutSettings;
	}
	
	/**
	 * Returns the current MapSettings.
	 * @return the map settings
	 */
	private MapSettings getMapSettings() {
		// --- Try to get current settings from GraphEnvironmentController ----
		if (this.graphController!=null) {
			return this.graphController.getNetworkModel().getMapSettings();
		}
		// --- Return the specified MapSettings (not dynamic) -----------------
		return mapSettings;
	}
	
	
	/**
	 * Transforms the specified {@link GraphNode}s position into the position for the visualization.
	 *
	 * @param graphNode the graph node with its position 
	 * @return the point 2 D
	 */
	@Override
	public Point2D transform(GraphNode graphNode) {
		return this.transform(graphNode.getPosition());
	}
	
	/**
	 * Transforms the specified position into the position for the visualization.
	 *
	 * @param npGraphNode the node position of a graph node
	 * @return the point 2 D
	 */
	public Point2D transform(Point2D npGraphNode) {
		
		Point2D visualNodePosition = new Point2D.Double();
		
		LayoutSettings layoutSettings = this.getLayoutSettings();
		switch (layoutSettings.getCoordinateSystemYDirection()) {
		case ClockwiseToX:
			switch (layoutSettings.getCoordinateSystemXDirection()) {
			case East:
				visualNodePosition = npGraphNode;
				break;
			case North:
				visualNodePosition.setLocation(npGraphNode.getY(), npGraphNode.getX() * (-1));
				break;
			case West:
				visualNodePosition.setLocation(npGraphNode.getX() * (-1), npGraphNode.getY() * (-1));
				break;
			case South:
				visualNodePosition.setLocation(npGraphNode.getY() * (-1), npGraphNode.getX());
				break;
			}
			break;
			
		case CounterclockwiseToX:
			switch (layoutSettings.getCoordinateSystemXDirection()) {
			case East:
				visualNodePosition.setLocation(npGraphNode.getX(), npGraphNode.getY() * (-1));
				break;
			case North:
				visualNodePosition.setLocation(npGraphNode.getY() * (-1), npGraphNode.getX() * (-1));
				break;
			case West:
				visualNodePosition.setLocation(npGraphNode.getX() * (-1), npGraphNode.getY());
				break;
			case South:
				visualNodePosition.setLocation(npGraphNode.getY(), npGraphNode.getX());
				break;
			}
			break;
		}
		
		// --- Scale the node position ? ------------------
		MapSettings mapSettings = this.getMapSettings();
		if (mapSettings!=null) {
			MapScale mapScale = mapSettings.getMapScale(); 
			if (mapScale.getScaleDivider()!=1.0) {
				// --- Scale the UTM coordinates ----------
				double scaledX = visualNodePosition.getX() / mapScale.getScaleDivider();
				double scaledY = visualNodePosition.getY() / mapScale.getScaleDivider();
				visualNodePosition = new Point2D.Double(scaledX, scaledY);
			}
		}
		
		return visualNodePosition;
	}
	
	/**
	 * Inverse transforms the specified position into the position for the GraphNode.
	 *
	 * @param visualNodePosition the visual node position
	 * @return the point 2 D
	 */
	public Point2D inverseTransform(Point2D visualNodePosition) {
		
		Point2D npGraphNode = new Point2D.Double();
		
		LayoutSettings layoutSettings = this.getLayoutSettings();
		switch (layoutSettings.getCoordinateSystemYDirection()) {
		case ClockwiseToX:
			switch (layoutSettings.getCoordinateSystemXDirection()) {
			case East:
				npGraphNode = visualNodePosition;
				break;
			case North:
				npGraphNode.setLocation(visualNodePosition.getY() * (-1), visualNodePosition.getX());
				break;
			case West:
				npGraphNode.setLocation(visualNodePosition.getX() * (-1), visualNodePosition.getY() * (-1));
				break;
			case South:
				npGraphNode.setLocation(visualNodePosition.getY(), visualNodePosition.getX() * (-1));
				break;
			}
			break;
			
		case CounterclockwiseToX:
			switch (layoutSettings.getCoordinateSystemXDirection()) {
			case East:
				npGraphNode.setLocation(visualNodePosition.getX(), visualNodePosition.getY()* (-1));
				break;
			case North:
				npGraphNode.setLocation(visualNodePosition.getY() * (-1), visualNodePosition.getX() * (-1));
				break;
			case West:
				npGraphNode.setLocation(visualNodePosition.getX() * (-1), visualNodePosition.getY());
				break;
			case South:
				npGraphNode.setLocation(visualNodePosition.getY(), visualNodePosition.getX());
				break;
			}
			break;
		}
		
		// --- Scale the node position ? ------------------
		MapSettings mapSettings = this.getMapSettings();
		if (mapSettings!=null) {
			MapScale mapScale = mapSettings.getMapScale(); 
			if (mapScale.getScaleDivider()!=1.0) {
				// --- Scale the UTM coordinates ----------
				double unScaledX = npGraphNode.getX() * mapScale.getScaleDivider();
				double unScaledY = npGraphNode.getY() * mapScale.getScaleDivider();
				npGraphNode = new Point2D.Double(unScaledX, unScaledY);
			}
		}
		return npGraphNode;
	}
	
}
