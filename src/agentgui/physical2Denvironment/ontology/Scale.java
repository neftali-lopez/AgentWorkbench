package agentgui.physical2Denvironment.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
   * The scale used for displaying the environment model, mapping a number of real world units to a number of pixels.
* Protege name: Scale
* @author ontology bean generator
* @version 2010/11/17, 20:10:06
*/
public class Scale implements Concept {

   /**
   * The number of real world units. Default 1.
* Protege name: realWorldUnitValue
   */
   private float realWorldUnitValue;
   public void setRealWorldUnitValue(float value) { 
    this.realWorldUnitValue=value;
   }
   public float getRealWorldUnitValue() {
     return this.realWorldUnitValue;
   }

   /**
   * The number of pixels. Default 10.
* Protege name: pixelValue
   */
   private float pixelValue;
   public void setPixelValue(float value) { 
    this.pixelValue=value;
   }
   public float getPixelValue() {
     return this.pixelValue;
   }

   /**
   * The real world unit's name. Default "m" (meters).
* Protege name: realWorldUntiName
   */
   private String realWorldUntiName;
   public void setRealWorldUntiName(String value) { 
    this.realWorldUntiName=value;
   }
   public String getRealWorldUntiName() {
     return this.realWorldUntiName;
   }

}