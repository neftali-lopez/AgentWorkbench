// file: MonitorMASOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package monitormas.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: MonitorMASOntology.java
 * @author ontology bean generator
 * @version 2010/05/16, 22:23:22
 */
public class MonitorMASOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "MonitorMAS";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new MonitorMASOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String ENVIRONMENTINFO="EnvironmentInfo";
    public static final String MOVE2WORKPLACE="Move2Workplace";
    public static final String TIMEDETECTOR_TIME_RECEIVE_START="Time_Receive_Start";
    public static final String TIMEDETECTOR_TIME_RECEIVE_END="Time_Receive_End";
    public static final String TIMEDETECTOR_TIME_TRANSMIT_START="Time_Transmit_Start";
    public static final String TIMEDETECTOR_TIME_TRANSMIT_END="Time_Transmit_End";
    public static final String TIMEDETECTOR="TimeDetector";

  /**
   * Constructor
  */
  private MonitorMASOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema timeDetectorSchema = new ConceptSchema(TIMEDETECTOR);
    add(timeDetectorSchema, monitormas.ontology.TimeDetector.class);

    // adding AgentAction(s)
    AgentActionSchema move2WorkplaceSchema = new AgentActionSchema(MOVE2WORKPLACE);
    add(move2WorkplaceSchema, monitormas.ontology.Move2Workplace.class);
    AgentActionSchema environmentInfoSchema = new AgentActionSchema(ENVIRONMENTINFO);
    add(environmentInfoSchema, monitormas.ontology.EnvironmentInfo.class);

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    timeDetectorSchema.add(TIMEDETECTOR_TIME_TRANSMIT_END, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    timeDetectorSchema.add(TIMEDETECTOR_TIME_TRANSMIT_START, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    timeDetectorSchema.add(TIMEDETECTOR_TIME_RECEIVE_END, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    timeDetectorSchema.add(TIMEDETECTOR_TIME_RECEIVE_START, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
