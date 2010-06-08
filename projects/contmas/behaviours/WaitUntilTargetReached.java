/**
 * @author Hanno - Felix Wagner, 10.05.2010
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

import mas.movement.MoveToPointBehaviour;
import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.SimpleBehaviour;
import contmas.agents.ContainerAgent;
import contmas.agents.ContainerHolderAgent;
import contmas.agents.PositionReporter;
import contmas.interfaces.MoveableAgent;
import contmas.ontology.Phy_Position;

class WaitUntilTargetReached extends SimpleBehaviour{
			WaitUntilTargetReached(Agent a,DataStore ds){
				super(a);
				this.setDataStore(ds);
			}

			private static final long serialVersionUID=5033799889547692668L;
			private Boolean isDone;
			private Phy_Position targetPosition;
			private MoveToPointBehaviour movingBehaviour;
			
			void setTargetPosition(Phy_Position targetPosition){
				this.targetPosition=targetPosition;
			}
			
			void setMovingBehaviour(MoveToPointBehaviour movingBehaviour){
				this.movingBehaviour=movingBehaviour;
			}

			@Override
			public void action(){
				isDone=true;
				if(myAgent instanceof MoveableAgent){
					MoveableAgent myMoveableAgent=(MoveableAgent) this.myAgent;

					isDone=movingBehaviour.done();
					if(isDone){
						((ContainerAgent)myAgent).echoStatus("I am in target position",ContainerAgent.LOGGING_INFORM);
						((ContainerHolderAgent)myAgent).wakeSleepingBehaviours(null);
					}else{
//						((ContainerAgent)myAgent).echoStatus("i have not yet reached target drop position: block",curTOC);
						if(myAgent instanceof PositionReporter){
							((PositionReporter)myAgent).reportPosition();
						}
						block(200);
					}
				}
			}

			@Override
			public boolean done(){
				return isDone;
			}
		}