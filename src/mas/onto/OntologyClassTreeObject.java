package mas.onto;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import reflection.ReflectClass;
import reflection.ReflectClass.Slot;



public class OntologyClassTreeObject extends Object {

	/**
	 * The instances of this class represents the UserObjects
	 * which are located in the Nodes of the OntologyTree 
	 */
	private OntologyClass ontologyClass = null;
	private Class<?> ontologySubClass = null;
	private String objectTitle = null;
	private boolean isAConcept = false;
	private boolean isAAgentAction = false;
	private boolean isAPredicate = false;
	
	/**
	 * Defines the Class belonging to the represented node
	 * @param Clazz
	 */
	public OntologyClassTreeObject (OntologyClass ontologyClazz, Class<?> clazz) {
		ontologyClass = ontologyClazz;
		ontologySubClass = clazz;
	}
	/**
	 * In case, that there is no class, a node can be
	 * represented by a simple String. E.g. in case of
	 * 'Concept' or 'AgentAction' there is no class
	 * related to this node. 
	 * @param ontologyClazz, nodeTitle
	 */
	public OntologyClassTreeObject (OntologyClass ontologyClazz, String nodeTitle) {
		ontologyClass = ontologyClazz;
		objectTitle = nodeTitle;
	}
	/**
	 * returns the title of this node object
	 */
	public String toString() {
		if ( ontologySubClass == null) {
			if ( objectTitle == null ) {
				return "-";	
			} else {
				return objectTitle;
			}			
		} else {
			if ( objectTitle == null ) {
				return getClassTextSimple();
			} else {
				return objectTitle;	
			}
		}
	}
	/**
	 * Returns TRUE if the current Instance has an 
	 * real Ontology-Class 
	 * @return boolean
	 */
	public boolean isClass() {
		if (ontologySubClass == null) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Returns the simple class name from a class-reference
	 * @return String
	 */
	public String getClassTextSimple() {
		String Reference = ontologySubClass.getName();
		return Reference.substring( Reference.lastIndexOf(".")+1 );
	}
	/**
	 * Refurns the full class-reference of the current ontology-sub-class
	 * @return
	 */
	public String getClassReference() {
		if (ontologySubClass==null) {
			if (ontologyClass==null) {
				return this.toString();
			} else {
				return ontologyClass.getOntologySourcePackage() + "." + this.toString();	
			}
		} else {
			return ontologySubClass.getName();	
		}
	}
	/**
	 * This method returns the instance of the OntologyClass which
	 * belongs to the current Tree-Object. 
	 * Here, the basic informaions about the Ontology are available!
	 * @return
	 */
	public OntologyClass getOntologyClass() {
		return this.ontologyClass;
	}
	/**
	 * returns the Class of the current ontology node
	 * @return
	 */
	public Class<?> getOntologySubClass() {
		return ontologySubClass;
	}
	/**
	 * Allows to set the node title, even if there is a 
	 * concrete class (with reference and title)
	 * @param OntologyTitle
	 */
	public void setObjectTitle(String ontologyTitle){
		objectTitle = ontologyTitle;
	}
	/**
	 * This returns the 'DefaultTableModel' for a single
	 * class out of the ontology-classes 
	 * @return
	 */
	public DefaultTableModel getTableModel4Slot() {
		
		// --- TableModel aufbauen ----------------------------------
		DefaultTableModel tm4s = new DefaultTableModel();
		
		// --- Spaltenünerschriften einstellen ----------------------
		tm4s.addColumn("Name");
		tm4s.addColumn("Cardinality");
		tm4s.addColumn("Type");
		tm4s.addColumn("Other Facets");		

		if ( ontologySubClass == null ) {
			return tm4s;
		}
		
		// --- Nach den entsprechenden Slots im Vokabular filtern ---
		Hashtable<String, String> OntoSlotHash = ontologyClass.ontologieVocabulary.getSlots( ontologySubClass );
		ReflectClass RefCla = new ReflectClass( ontologySubClass, OntoSlotHash );
		
		Vector<String> v = new Vector<String>( OntoSlotHash.keySet() );
	    Collections.sort(v);
	    Iterator<String> it = v.iterator();
	    while (it.hasNext()) {
	    	
	    	// --- Wort/Slot  der Ontologie ermitteln --------------- 
	    	String Key = it.next();
	    	String Word = OntoSlotHash.get(Key);
	    	
	    	// --- Slot untersuchen ... -----------------------------
	    	Slot CurrSlot = RefCla.getSlot(Word);
	    	
	    	// --- Zeile an TableModel anfügen ----------------------	    	
	    	Vector<String> rowData = new Vector<String>(); 
	    	rowData.add( Word );
	    	rowData.add( CurrSlot.Cardinality );
	    	rowData.add( CurrSlot.VarType );
	    	rowData.add( CurrSlot.OtherFacts );
	    	tm4s.addRow(rowData);
	    }		
		return tm4s;		
	}
	/**
	 * @param isAConcept the isAConcept to set
	 */
	public void setIsConcept(boolean isConcept) {
		this.isAConcept = isConcept;
	}
	/**
	 * @return the isAConcept
	 */
	public boolean isConcept() {
		return isAConcept;
	}
	/**
	 * @param isAAgentAction the isAAgentAction to set
	 */
	public void setIsAgentAction(boolean isAgentAction) {
		this.isAAgentAction = isAgentAction;
	}
	/**
	 * @return the isAAgentAction
	 */
	public boolean isAgentAction() {
		return isAAgentAction;
	}
	/**
	 * @param isAPredicate the isAPredicate to set
	 */
	public void setIsPredicate(boolean isPredicate) {
		this.isAPredicate = isPredicate;
	}
	/**
	 * @return the isAPredicate
	 */
	public boolean isPredicate() {
		return isAPredicate;
	}
}
