package agentgui.ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Simple_Long
* @author ontology bean generator
* @version 2019/02/25, 13:33:15
*/
public class Simple_Long implements Concept {

//////////////////////////// User code
public Long getLongValue(){
	   try{
		   return Long.parseLong(getStringLongValue());
	   }catch(NumberFormatException ex){
		   return null;
	   }
   }
   public void setLongValue(long value){
	   setStringLongValue(""+value);
   }
   public void setLongValue(Long value){
	   setStringLongValue(value.toString());
   }
   /**
* Protege name: StringLongValue
   */
   private String stringLongValue;
   public void setStringLongValue(String value) { 
    this.stringLongValue=value;
   }
   public String getStringLongValue() {
     return this.stringLongValue;
   }

}
