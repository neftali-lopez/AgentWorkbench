package agentgui.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Simple_Integer
* @author ontology bean generator
* @version 2019/02/25, 13:33:15
*/
public class Simple_Integer implements Concept {

   /**
* Protege name: IntegerValue
   */
   private int integerValue;
   public void setIntegerValue(int value) { 
    this.integerValue=value;
   }
   public int getIntegerValue() {
     return this.integerValue;
   }

}
