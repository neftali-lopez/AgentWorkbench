/**
 * @author Hanno - Felix Wagner, 24.03.2010
 * Copyright 2010 Hanno - Felix Wagner
 * 
 * This file is part of ContMAS.
 *
 * ContMAS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ContMAS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ContMAS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package contmas.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import contmas.agents.*;
import contmas.interfaces.OntRepProvider;
import contmas.main.HarbourSetup;
import contmas.ontology.*;

/**
 * @author Hanno - Felix Wagner
 *
 */
public class listenForStartAgentReq extends SequentialBehaviour implements OntRepProvider{
	/**
	 * 
	 */
	private static final long serialVersionUID=6436880516223032996L;
	public ContainerHolder ontRep=null;
	public Boolean populate=true;
	public Boolean randomize=false;
	public ACLMessage START_AGENT_REQUEST_MSG=null;
	public ACLMessage START_AGENT_RESPONSE_MSG=null;

	/**
	 * @param a
	 */
	public listenForStartAgentReq(Agent a){
		super(a);

		Behaviour b=new receiveStartAgentRequest(a);//AchieveREListener (send agree?)
		this.setDataStore(b.getDataStore());
		this.addSubBehaviour(b);

	}

	public void nextStep(){
		Behaviour b;
		if(this.randomize){
			b=new requestRandomBayMap(this.myAgent);//GetRandomBayMap
			this.addSubBehaviour(b);
			b.setDataStore(this.getDataStore());
		}
		if(this.populate){
			b=new requestPopulatedBayMap(this.myAgent);//PopulateBayMap
			this.addSubBehaviour(b);
			b.setDataStore(this.getDataStore());
		}
		b=new startAgent(this.myAgent);//Start the Agent and send inform
		this.addSubBehaviour(b);
		b.setDataStore(this.getDataStore());
	}

	/* (non-Javadoc)
	 * @see contmas.agents.OntRepProvider#getOntologyRepresentation(jade.core.AID)
	 */
	@Override
	public ContainerHolder getOntologyRepresentation(AID request){
		return null;
	}

	/* (non-Javadoc)
	 * @see contmas.agents.OntRepProvider#getOntologyRepresentation()
	 */
	@Override
	public ContainerHolder getOntologyRepresentation(){
		return this.ontRep;
	}

	class startAgent extends OneShotBehaviour{

		/**
		 * 
		 */
		private static final long serialVersionUID=2215461542689039091L;

		/**
		 * @param a
		 */
		public startAgent(Agent a){
			super(a);
		}

		/* (non-Javadoc)
		 * @see jade.core.behaviours.Behaviour#action()
		 */
		@Override
		public void action(){
			ACLMessage request=listenForStartAgentReq.this.START_AGENT_REQUEST_MSG;
			StartNewContainerHolder act=(StartNewContainerHolder) ContainerAgent.extractAction(this.myAgent,request);
			AgentContainer c=this.myAgent.getContainerController();
			AgentController a=null;
			ContainerHolder ontRep=listenForStartAgentReq.this.getOntologyRepresentation();
			Domain harbourMap=((HarborMasterAgent)myAgent).getHarbourArea();

			Phy_Position defaultPos=new Phy_Position();
			defaultPos.setPhy_x(0.0F);
			defaultPos.setPhy_y(0.0F);
			if(ontRep.getLives_in().getIs_in_position()!=null){
				defaultPos=ContainerHolderAgent.findDomain(ontRep.getLives_in().getId(),harbourMap).getIs_in_position();
			}
			if(ontRep.getIs_in_position2()==null){
				ontRep.setIs_in_position2(defaultPos);
			}
			HarbourSetup.reduceCH(ontRep);

			HarbourSetup.removeBacklink(harbourMap,true); //remove lies_in

			HarbourSetup.inflateCH(ontRep,harbourMap);

//			HarbourSetup.cleanDomainsOf(ontRep,((HarborMasterAgent)myAgent).getHarbourArea());
			
			try{
				if(ontRep instanceof Ship){
					a=c.acceptNewAgent(act.getName(),new ShipAgent((Ship) ontRep));
				}else if(ontRep instanceof Crane){
					a=c.acceptNewAgent(act.getName(),new CraneAgent((Crane) ontRep));
				}else if(ontRep instanceof Apron){
					a=c.acceptNewAgent(act.getName(),new ApronAgent((Apron) ontRep));
				}else if(ontRep instanceof StraddleCarrier){
					a=c.acceptNewAgent(act.getName(),new StraddleCarrierAgent((StraddleCarrier) ontRep));
				}else if(ontRep instanceof Yard){
					a=c.acceptNewAgent(act.getName(),new YardAgent((Yard) ontRep));
				}
				a.start();
				ACLMessage inform=listenForStartAgentReq.this.START_AGENT_RESPONSE_MSG;
				this.myAgent.send(inform);
			}catch(StaleProxyException e){

				if (e.getMessage().startsWith("Name-clash Agent")){
					((ContainerAgent) myAgent).echoStatus(e.getMessage(),ContainerAgent.LOGGING_NOTICE);
					ACLMessage response=listenForStartAgentReq.this.START_AGENT_RESPONSE_MSG;
					response.setPerformative(ACLMessage.FAILURE);
					response.setContent(e.getMessage());
					this.myAgent.send(response);

				} else {
					e.printStackTrace();
				}
			}


			//Restart
			Agent tmp=this.myAgent;
			this.myAgent.removeBehaviour(this);
			tmp.addBehaviour(new listenForStartAgentReq(tmp));
		}
	}
}