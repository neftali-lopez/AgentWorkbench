// file: EnvironmentOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package mas.environment.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: EnvironmentOntology.java
 * @author ontology bean generator
 * @version 2010/10/20, 18:27:57
 */
public class EnvironmentOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "Environment";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new EnvironmentOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String SIZE_WIDTH="width";
    public static final String SIZE_HEIGHT="height";
    public static final String SIZE="Size";
    public static final String PLAYGROUNDOBJECT_CHILDOBJECTS="childObjects";
    public static final String PLAYGROUNDOBJECT="PlaygroundObject";
    public static final String PHYSICAL2DENVIRONMENT_PROJECTNAME="projectName";
    public static final String PHYSICAL2DENVIRONMENT_ROOTPLAYGROUND="rootPlayground";
    public static final String PHYSICAL2DENVIRONMENT_SCALE="scale";
    public static final String PHYSICAL2DENVIRONMENT="Physical2DEnvironment";
    public static final String SCALE_PIXELVALUE="pixelValue";
    public static final String SCALE_REALWORLDUNTINAME="realWorldUntiName";
    public static final String SCALE_REALWORLDUNITVALUE="realWorldUnitValue";
    public static final String SCALE="Scale";
    public static final String PASSIVEOBJECT_CONTROLLINGOBJECTID="controllingObjectID";
    public static final String PASSIVEOBJECT="PassiveObject";
    public static final String PHYSICAL2DOBJECT_SIZE="size";
    public static final String PHYSICAL2DOBJECT_PARENTPLAYGROUNDID="parentPlaygroundID";
    public static final String PHYSICAL2DOBJECT_POSITION="position";
    public static final String PHYSICAL2DOBJECT_ID="id";
    public static final String PHYSICAL2DOBJECT="Physical2DObject";
    public static final String MOVEMENT_YPOSCHANGE="yPosChange";
    public static final String MOVEMENT_XPOSCHANGE="xPosChange";
    public static final String MOVEMENT="Movement";
    public static final String POSITION_YPOS="yPos";
    public static final String POSITION_XPOS="xPos";
    public static final String POSITION="Position";
    public static final String ACTIVEOBJECT_MOVEMENT="movement";
    public static final String ACTIVEOBJECT_MAXSPEED="maxSpeed";
    public static final String ACTIVEOBJECT_PAYLOAD="payload";
    public static final String ACTIVEOBJECT="ActiveObject";
    public static final String STATICOBJECT="StaticObject";

  /**
   * Constructor
  */
  private EnvironmentOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema staticObjectSchema = new ConceptSchema(STATICOBJECT);
    add(staticObjectSchema, mas.environment.ontology.StaticObject.class);
    ConceptSchema activeObjectSchema = new ConceptSchema(ACTIVEOBJECT);
    add(activeObjectSchema, mas.environment.ontology.ActiveObject.class);
    ConceptSchema positionSchema = new ConceptSchema(POSITION);
    add(positionSchema, mas.environment.ontology.Position.class);
    ConceptSchema movementSchema = new ConceptSchema(MOVEMENT);
    add(movementSchema, mas.environment.ontology.Movement.class);
    ConceptSchema physical2DObjectSchema = new ConceptSchema(PHYSICAL2DOBJECT);
    add(physical2DObjectSchema, mas.environment.ontology.Physical2DObject.class);
    ConceptSchema passiveObjectSchema = new ConceptSchema(PASSIVEOBJECT);
    add(passiveObjectSchema, mas.environment.ontology.PassiveObject.class);
    ConceptSchema scaleSchema = new ConceptSchema(SCALE);
    add(scaleSchema, mas.environment.ontology.Scale.class);
    ConceptSchema physical2DEnvironmentSchema = new ConceptSchema(PHYSICAL2DENVIRONMENT);
    add(physical2DEnvironmentSchema, mas.environment.ontology.Physical2DEnvironment.class);
    ConceptSchema playgroundObjectSchema = new ConceptSchema(PLAYGROUNDOBJECT);
    add(playgroundObjectSchema, mas.environment.ontology.PlaygroundObject.class);
    ConceptSchema sizeSchema = new ConceptSchema(SIZE);
    add(sizeSchema, mas.environment.ontology.Size.class);

    // adding AgentAction(s)

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    activeObjectSchema.add(ACTIVEOBJECT_PAYLOAD, passiveObjectSchema, 0, ObjectSchema.UNLIMITED);
    activeObjectSchema.add(ACTIVEOBJECT_MAXSPEED, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    activeObjectSchema.add(ACTIVEOBJECT_MOVEMENT, movementSchema, ObjectSchema.MANDATORY);
    positionSchema.add(POSITION_XPOS, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    positionSchema.add(POSITION_YPOS, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    movementSchema.add(MOVEMENT_XPOSCHANGE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    movementSchema.add(MOVEMENT_YPOSCHANGE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    physical2DObjectSchema.add(PHYSICAL2DOBJECT_ID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    physical2DObjectSchema.add(PHYSICAL2DOBJECT_POSITION, positionSchema, ObjectSchema.MANDATORY);
    physical2DObjectSchema.add(PHYSICAL2DOBJECT_PARENTPLAYGROUNDID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    physical2DObjectSchema.add(PHYSICAL2DOBJECT_SIZE, sizeSchema, ObjectSchema.MANDATORY);
    passiveObjectSchema.add(PASSIVEOBJECT_CONTROLLINGOBJECTID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    scaleSchema.add(SCALE_REALWORLDUNITVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    scaleSchema.add(SCALE_REALWORLDUNTINAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    scaleSchema.add(SCALE_PIXELVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    physical2DEnvironmentSchema.add(PHYSICAL2DENVIRONMENT_SCALE, scaleSchema, ObjectSchema.MANDATORY);
    physical2DEnvironmentSchema.add(PHYSICAL2DENVIRONMENT_ROOTPLAYGROUND, playgroundObjectSchema, ObjectSchema.MANDATORY);
    physical2DEnvironmentSchema.add(PHYSICAL2DENVIRONMENT_PROJECTNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    playgroundObjectSchema.add(PLAYGROUNDOBJECT_CHILDOBJECTS, physical2DObjectSchema, 0, ObjectSchema.UNLIMITED);
    sizeSchema.add(SIZE_HEIGHT, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);
    sizeSchema.add(SIZE_WIDTH, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);

    // adding name mappings

    // adding inheritance
    staticObjectSchema.addSuperSchema(physical2DObjectSchema);
    activeObjectSchema.addSuperSchema(physical2DObjectSchema);
    passiveObjectSchema.addSuperSchema(physical2DObjectSchema);
    playgroundObjectSchema.addSuperSchema(physical2DObjectSchema);

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
