package mas.display;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.script.Window;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.SubscriptionInitiator;

/**
 * This class controls a DisplayAgentGUI instance and receives position updates from the TopicManagementHelper   
 *   
 * @author nils
 *
 */
public class DisplayAgent extends Agent {
	
	// SVG Namespace
	public static final String svgNs=SVGDOMImplementation.SVG_NAMESPACE_URI;
	
	private DisplayAgentGUI myGUI = null;
	private DisplayAgent myAgent = this;
	
	public HashSet<String> knownAgents = null;  // Hashmap storing animated agents, key=localName
	
	public void setup(){
		Object[] args = getArguments();
		// GUI object passed as argument -> embedded mode
		if((args.length>0) && (args[0] instanceof DisplayAgentGUI)){
			myGUI = (DisplayAgentGUI)args[0];
		// No GUI passed -> stand alone mode
		}else{
			myGUI = new DisplayAgentGUI(this);
			JFrame frame = new JFrame ("DisplayAgent GUI stand alone mode");
			frame.setContentPane(myGUI);
			
			frame.pack();
			frame.setVisible(true);
			
		}
		
				
		knownAgents=new HashSet<String>();
				
		try {
			TopicManagementHelper tmh = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
			AID positionTopic = tmh.createTopic("position");
			tmh.register(positionTopic);
			addBehaviour(new PositionUpdateReceiver(MessageTemplate.MatchTopic(positionTopic)));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Display agent "+getLocalName()+"ready.");
		

		
		
	}
	
	public void addAgent(String name, String x, String y){
		knownAgents.add(name);
		if(myGUI!=null)
			myGUI.addAgent(name, x, y);
	}
	
	public void updateAgent(String name, String x, String y){
		if(myGUI!=null)
			myGUI.updateAgent(name, x, y);
	}
	
	public void removeAgent(String name){
		if(myGUI!=null)
			myGUI.removeAgent(name);
	}
	
	public void takeDown(){
		if(myGUI!=null){
			myGUI.setVisible(false);
		}
		
	}
	
	public DisplayAgentGUI getGUI(){
		return myGUI;
	}
	
	public void setGUI(DisplayAgentGUI gui){
		this.myGUI = gui;
	}		
}
