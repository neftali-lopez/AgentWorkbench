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
package org.awb.env.networkModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import org.awb.env.networkModel.helper.GraphEdgeDirection;

import de.enflexit.common.SerialClone;

/**
 * This class represents a component of the modelled network. It contains its' ontology representation, its' AbstractGraphElementPrototype, the nodes and edges representing it in the environment graph and an
 * ID for easier access.
 * 
 * @see NetworkModel
 * 
 * @author Nils Loose - DAWIS - ICB University of Duisburg - Essen
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class NetworkComponent implements Serializable, Comparable<NetworkComponent> {

	private static final long serialVersionUID = 537431665305238609L;

	/** The NetworkComponent's ID. */
	protected String id;
	/** The NetworkComponent's type. */
	protected String type;
	/** The IDs of the nodes and edges that are part of this NetworkComponent. */
	protected HashSet<String> graphElementIDs = new HashSet<String>();
	/** Specifies if the NetworkComponent is directed or undirected. */
	protected boolean directed;
	/** The NetworkComponent's AbstractGraphElementPrototype class name. */
	protected String prototypeClassName;
	/** The NetworkComponent's AbstractGraphElementPrototype class name. */
	protected String agentClassName;
	/** The direction(s) of the edge(s). */
	protected HashMap<String, GraphEdgeDirection> edgeDirections;
	
	/**	The data model for this NetworkComponent.*/
	@XmlTransient
	protected Object dataModel;
	/** The data model for this NetworkComponent encoded as Base64 String*/
	@XmlElementWrapper(name = "dataModelsBase64")
	@XmlElement(name="dataModelBase64")
	protected Vector<String> dataModelBase64;
	
	/**
	 * Instantiates a new network component.
	 * Depreciated because of the JAXB context
	 */
	@Deprecated
	public NetworkComponent() {
	}
	
	/**
	 * Instantiates a new network component.
	 *
	 * @param id the id
	 * @param type the type
	 * @param prototypeClassName the prototype class name
	 * @param agentClassName the agent class name
	 * @param graphElements the graph elements
	 * @param directed the directed
	 */
	public NetworkComponent(String id, String type, String prototypeClassName, String agentClassName, HashSet<GraphElement> graphElements, boolean directed ) {
		this.id = id;
		this.type = type;
		this.prototypeClassName = prototypeClassName;
		this.agentClassName = agentClassName;
		this.directed = directed;
		this.setGraphElements(graphElements);
	}
	
	/**
	 * Returns a copy of the current NetworkComponent.
	 * @return the copy
	 */
	public NetworkComponent getCopy() {
		NetworkComponent copy = null;	
		try {
			copy = SerialClone.clone(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return copy;
	}
	
	/**
	 * Gets the id.
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Sets the id and changes graphEleemntIDs
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets the type.
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the graph element IDs present in the component as a HashSet.
	 * @return the graphElements
	 */
	public HashSet<String> getGraphElementIDs() {
		return graphElementIDs;
	}
	/**
	 * Sets the graph element IDs as a HashSet.
	 * @param graphElementIDs the graphElements to set
	 */
	public void setGraphElementIDs(HashSet<String> graphElementIDs) {
		this.graphElementIDs = graphElementIDs;
	}
	/**
	 * Sets the id HashSet elements IDs by extracting the IDs from an GraphElement HashSet.
	 * @param graphElements the new graph elements
	 */
	public void setGraphElements(HashSet<GraphElement> graphElements) {
		if (graphElements==null) {
			graphElementIDs = null;
		} else {
			HashSet<String> newGraphElementIDs = new HashSet<String>();
			for (GraphElement graphElement : graphElements) {
				newGraphElementIDs.add(graphElement.getId());
			}
			graphElementIDs = newGraphElementIDs;	
		}
	}

	/**
	 * Checks if is directed.
	 * @return the directed
	 */
	public boolean isDirected() {
		return directed;
	}
	/**
	 * Sets the directed.
	 * @param directed the directed to set
	 */
	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	/**
	 * Returns the graph element prototype class name.
	 * @return the prototypeClassName
	 */
	public String getPrototypeClassName() {
		// --- Consider changes due to the bundle refactoring -------
		if (prototypeClassName!=null && prototypeClassName.startsWith("agentgui.envModel.graph.prototypes")==true) {
			prototypeClassName = prototypeClassName.replace("agentgui.envModel.graph.prototypes", "org.awb.env.networkModel.prototypes");
		}
		return prototypeClassName;
	}
	/**
	 * Sets the graph element prototype class name.
	 * @param prototypeClassName the prototypeClassName to set
	 */
	public void setPrototypeClassName(String prototypeClassName) {
		this.prototypeClassName = prototypeClassName;
	}

	/**
	 * Gets the agent class name.
	 * @return the agentClassName
	 */
	public String getAgentClassName() {
		return agentClassName;
	}
	/**
	 * Sets the agent class name.
	 * @param agentClassName the agentClassName to set
	 */
	public void setAgentClassName(String agentClassName) {
		this.agentClassName = agentClassName;
	}

	/**
	 * Returns the current edge directions.
	 * @return the edgeDirections
	 */
	public HashMap<String, GraphEdgeDirection> getEdgeDirections() {
		return edgeDirections;
	}
	/**
	 * Sets the edge directions.
	 * @param edgeDirections the edgeDirections to set
	 */
	public void setEdgeDirections(HashMap<String, GraphEdgeDirection> edgeDirections) {
		this.edgeDirections = edgeDirections;
	}

	/**
	 * Gets the data model instance.
	 * @return the data model instance
	 */
	@XmlTransient
	public Object getDataModel() {
		return dataModel;
	}
	/**
	 * Sets the data model instance.
	 * @param dataModel the new data model instance
	 */
	public void setDataModel(Object dataModel) {
		this.dataModel = dataModel;
	}
	
	/**
	 * Returns the data model Base64 encoded.
	 * @return the dataModelBase64
	 */
	@XmlTransient
	public Vector<String> getDataModelBase64() {
		return dataModelBase64;
	}
	/**
	 * Sets the data model Base64 encoded.
	 * @param dataModelBase64 the dataModelBase64 to set
	 */
	public void setDataModelBase64(Vector<String> dataModelBase64) {
		this.dataModelBase64 = dataModelBase64;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(NetworkComponent ncToComp) {
		
		// --- Check the pure string case first ------------- 
		String n1NumberString = this.getId().replaceAll("\\D+","");
		String n2NumberString = ncToComp.getId().replaceAll("\\D+","");
		if (n1NumberString.isEmpty() && n2NumberString.isEmpty()) {
			return this.getId().compareTo(ncToComp.getId());
		}
		// --- In case of available numbers -----------------
		if (n1NumberString==null || n1NumberString.isEmpty()==true) n1NumberString = "0";		
		if (n2NumberString==null || n2NumberString.isEmpty()==true) n2NumberString = "0";
		Long n1 = Long.parseLong(n1NumberString);
		Long n2 = Long.parseLong(n2NumberString);
		return n1.compareTo(n2);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getId() + " - " + this.getType();
	}

	
	
}
