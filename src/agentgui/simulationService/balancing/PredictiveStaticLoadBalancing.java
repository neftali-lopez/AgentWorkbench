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

package agentgui.simulationService.balancing;

import jade.core.Location;
import jade.core.ServiceException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import agentgui.core.agents.AgentClassElement4SimStart;
import agentgui.core.gui.projectwindow.Distribution;
import agentgui.simulationService.agents.LoadExecutionAgent;
import agentgui.simulationService.balancing.BaseLoadBalancing;
import agentgui.simulationService.balancing.StaticLoadBalancingBase;
import agentgui.simulationService.load.LoadAgentMap;
import agentgui.simulationService.load.LoadInformation.NodeDescription;
import agentgui.simulationService.ontology.PlatformLoad;


/**
 * This class is for static distribution of Agents based on given metrics
 * @see StaticLoadBalancingBase
 * @see BaseLoadBalancing
 * @see Distribution
 * 
 * @author Hanno Monschan - DAWIS - ICB - University of Duisburg - Essen
 */
public class PredictiveStaticLoadBalancing extends StaticLoadBalancingBase{

	private static final long serialVersionUID = -6884445863598676300L;
	/** The PlatformLoad in the different container. */
	public Hashtable<String, PlatformLoad> loadContainer = null;
	/** The current LoadAgentMap. */
	public LoadAgentMap loadContainerAgentMap = null;
	/** The benchmark value /results in the different container. */
	public Hashtable<String, Float> loadContainerBenchmarkResults = new Hashtable<String, Float>();
	
	private String remoteContainerName;
	private boolean remoteOnly = true;
	private boolean changeContainer = false;
	public double loadCpuIdeal,loadMemIdeal,loadCpuCrit,loadMemCrit;
	
	private static final double LOAD_CPU_IDEAL_PERCENT = 80 ;//default 80
	private static final double LOAD_MEM_IDEAL_PERCENT = 80 ;//default 80
	private static final double LOAD_CPU_CRIT_PERCENT  = 90 ;//default 90
	private static final double LOAD_MEM_CRIT_PERCENT  = 90 ;//default 90
	private static final double LOAD_CPU_IDEAL = (LOAD_CPU_IDEAL_PERCENT/100) ;
	private static final double LOAD_MEM_IDEAL = (LOAD_MEM_IDEAL_PERCENT/100) ;
	private static final double LOAD_CPU_CRIT  = (LOAD_CPU_CRIT_PERCENT/100) ;
	private static final double LOAD_MEM_CRIT  = (LOAD_MEM_CRIT_PERCENT/100) ;
	
	public PredictiveStaticLoadBalancing(LoadExecutionAgent agent) {
		super(agent);
		
		if(currDisSetup.isUseUserThresholds()){
			loadCpuIdeal = currDisSetup.getUserThresholds().getThCpuH()/100;
			loadMemIdeal = currDisSetup.getUserThresholds().getThMemoH()/100;
			loadCpuCrit  = currDisSetup.getUserThresholds().getThCpuH()/100;
			loadMemCrit  = currDisSetup.getUserThresholds().getThMemoH()/100;
		}else{
			loadCpuIdeal = LOAD_CPU_IDEAL;
			loadMemIdeal = LOAD_MEM_IDEAL;
			loadCpuCrit  = LOAD_CPU_CRIT;
			loadMemCrit  = LOAD_MEM_CRIT;
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see agentgui.simulationService.balancing.BaseLoadBalancingInterface#doBalancing()
	 */
	@Override
	public void doBalancing() {	
		/** Start predictive, static distribution of agents on remote containers*/
		this.PredictiveDistribution(true);	
	}
	
	@SuppressWarnings("unchecked")
	public void PredictiveDistribution(boolean verbose){
		boolean debug = verbose;
		if(debug){
			System.out.println("START of predictive, static distribution.");
		}
		// ----------------------------------------------------------------------------------
		// --- Distribution is based on predictive metrics, thus: 						-----
		// --- => 1. Start the remote containers 										-----
		// --- => 2. Get the metrics of remote container (location)                    	-----
		// --- => 3. Determine the distribution of all agents in ('this.currAgentList')	----- 
		// --- => 4. distribute the agents according to calculation						-----
		// ---   (Agents defined in visualization-setup will be distributed as well)	-----
		// ----------------------------------------------------------------------------------

		// --- Just in case, that we don't have enough information ---
		if (currNumberOfContainer==0) {
			if (currNumberOfAgents!=0) {
				int noAgentsMax = currThresholdLevels.getThNoThreadsH();
				currNumberOfContainer = (int) Math.ceil(((float)currNumberOfAgents / (float)noAgentsMax)) + 1;
			}
		}	

		if (currNumberOfContainer<=1) {
			// --- Just start all defined agents ---------------------
			this.startAgentsFromCurrAgentList();
			return;
		}
		
		/*
		 * ### 1. start containers
		 */
		/** All location names */
		Vector<String> locationNames = null;
		/** All container/locations */
		Hashtable<String, Location> newContainerLocations = this.startNumberOfRemoteContainer(currNumberOfContainer - 1, true, null);
		/** The local container*/
		Location localContainer = newContainerLocations.get(this.currProject.getProjectFolder());
		/** The actual remote container*/
		Location remoteContainer = null;
		/** The CPU benchmark values (MFLOPS  * No of CPUs) of each container*/
		Hashtable<Location, Double> cpu = null;
		/** The RAM benchmark values MB of each container*/
		Hashtable<Location, Double> mem = null;
		
		
		/*
		 * ### 2. get CPU  and MEM Benchmark of each container
		 */
		
		if (newContainerLocations!=null) {
			
			locationNames = new Vector<String>(newContainerLocations.keySet());
			cpu = new Hashtable<Location, Double>();
			mem = new Hashtable<Location, Double>();
			
			// iterate over location names
			Iterator<String> locationNamesIt = locationNames.iterator();
			while (locationNamesIt.hasNext()) {
				
				remoteContainer = newContainerLocations.get(locationNamesIt.next());
				
				// --- Get the benchmark-result for this node/container -------------
				NodeDescription containerDesc;
				try {
					containerDesc = loadHelper.getContainerDescription(remoteContainer.getName());
					
					Double mflopsBenchmarkValueTotal = (double) containerDesc.getBenchmarkValue().getBenchmarkValue() * containerDesc.getPlPerformace().getCpu_numberOf();
					mflopsBenchmarkValueTotal = Math.round(mflopsBenchmarkValueTotal*100)/100.0;
					cpu.put(remoteContainer, mflopsBenchmarkValueTotal);
					
					Double memBenchmarkValueTotal =  (double) containerDesc.getPlPerformace().getMemory_totalMB();					
					mem.put(remoteContainer, memBenchmarkValueTotal);
					if(debug){
						System.out.println(remoteContainer.getName()+ ", Benchmark:"+ cpu.get(remoteContainer) + " MFLOPS, " + mem.get(remoteContainer) + " MB");					
					}
				} catch (ServiceException e) {
					System.out.println("No container description available.");
					e.printStackTrace();
				}
				
			}
		}	
		
		/*
		 * ### 3. calculate predictive distribution and assign agents to container-list
		 */
		/** A list of agents*/
		ArrayList<AgentClassElement4SimStart> agentList = new ArrayList<AgentClassElement4SimStart>();
		/** a mapping of agents to containers*/
		Hashtable<Location, ArrayList<AgentClassElement4SimStart>> agentContainerList = new Hashtable<Location,ArrayList<AgentClassElement4SimStart>>();
		/** Holds the temp object for getting the metrics*/
		LoadBalancingInterface lba = null;
		/** temp for sum of CPU metrics*/
		Hashtable<Location, Double> cpuContainerSum = new Hashtable<Location, Double>();
		/** temp for sum of MEM metrics*/
		Hashtable<Location, Double> memContainerSum = new Hashtable<Location, Double>();
		
		
		if (currAgentList!=null && locationNames != null) {
			
			Iterator<String> it = locationNames.iterator();
			remoteContainerName = it.next();
			
			for (Iterator<AgentClassElement4SimStart> iterator = currAgentList.iterator(); iterator.hasNext();) {
				
				AgentClassElement4SimStart agent = iterator.next();
				remoteContainer = newContainerLocations.get(remoteContainerName);
				ArrayList<AgentClassElement4SimStart> agentListLocal = null;
				
				if(agent.getElementClass().getSuperclass().getName().equals(LoadBalancingInterface.class.getName()) == true){
					//Loadbalancing Agents to be distributed
					
					//still enough "space" on container ?
					if(cpuContainerSum.get(remoteContainer) == null || 
					   (cpu.get(remoteContainer)*loadCpuCrit) >= cpuContainerSum.get(remoteContainer)&&
					   (mem.get(remoteContainer)*loadMemCrit) >= memContainerSum.get(remoteContainer)){
						

						try {
							lba = (LoadBalancingInterface) Class.forName(agent.getElementClass().getName()).newInstance();
						} catch (InstantiationException e) {
							System.out.println("Object for "+ agent.getElementClass().getName() + "could not be istantiated");
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							System.out.println("Could not access class of agent");
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							System.out.println("Could not find class of agent");
							e.printStackTrace();
						}			
						//update sum CPU metrics
						if(cpuContainerSum.get(remoteContainer)!=null){
							cpuContainerSum.put(remoteContainer, cpuContainerSum.get(remoteContainer) + lba.getPredictMetricCPU());
						}else{
							cpuContainerSum.put(remoteContainer, lba.getPredictMetricCPU());
						}
						//update sum MEM metrics
						if(memContainerSum.get(remoteContainer)!=null){
							memContainerSum.put(remoteContainer, memContainerSum.get(remoteContainer) + lba.getPredictMetricMEM());
						}else{
							memContainerSum.put(remoteContainer, lba.getPredictMetricMEM());
						}
						
						//ideal workload ?
						if((cpu.get(remoteContainer)*loadCpuIdeal) <= cpuContainerSum.get(remoteContainer) ||
					       (mem.get(remoteContainer)*loadMemIdeal) <= memContainerSum.get(remoteContainer)){
							//change container
							changeContainer = true;	
						}
						
					}else{
						
						changeContainer = true;
					}		
					
				}else{
					//Simulation Agent and "unknown" agents always kept locally
					remoteContainer = localContainer;
					
				} 
				
				if(changeContainer == true){
					
					if(it.hasNext() == true && it.next().equals(localContainer.getName()) == false){
						//save mapping, clear agent list and add actual agent for next iteration
						agentContainerList.put(remoteContainer, (ArrayList<AgentClassElement4SimStart>)agentList.clone());
						agentList.clear();
						agentList.add(agent);
						remoteContainerName = it.next();
						
						if(debug){
							System.out.println("#### IDEAL workload distribution for " + remoteContainer.getName());
							System.out.println("#### REACHED  " + (100/cpu.get(remoteContainer) * cpuContainerSum.get(remoteContainer)) + "% CPU");
							System.out.println("#### REACHED  " + (100/mem.get(remoteContainer) * memContainerSum.get(remoteContainer)) + "% ofMemory");
						}
					}else{//not enough containers left
												
						if(remoteOnly == true){
							if(debug){
								System.out.println("#### NOT ENOUGH REMOTE CONTAINERS #### STILL USING " + remoteContainer.getName());
							}							
						}else{
							remoteContainer = localContainer;
							if(debug){
								System.out.println("#### NOT ENOUGH REMOTE CONTAINERS #### USING LOCAL CONTAINER");

							}
						}	
						System.out.println("#### WARNING reached  " + (100/cpu.get(remoteContainer) * cpuContainerSum.get(remoteContainer)) + "% CPU," + (100/mem.get(remoteContainer) * memContainerSum.get(remoteContainer)) + "% Memory");						
					}
					//reset
					changeContainer = false;
					
				}
//				else{
					if(remoteContainer.getName().equals(localContainer.getName()) == true){
						agentListLocal = agentContainerList.get(localContainer);
						if( agentListLocal != null){
							agentListLocal.add(agent);
							agentContainerList.put(localContainer, agentListLocal);
						}else{
							agentList.add(agent);
							agentContainerList.put(localContainer, (ArrayList<AgentClassElement4SimStart>)agentList.clone());	
							agentList.clear();
						}
					
					}else{
						agentList.add(agent);
						agentContainerList.put(remoteContainer, (ArrayList<AgentClassElement4SimStart>)agentList.clone());	
					}
					
//				}				
			} 

		}
		
		/*
		 * ### 4. start agents on locations
		 */
		/** all container/locations agents are mapped to*/
		Vector<Location> loc = new Vector<Location>(agentContainerList.keySet());
		/** iterator for containers*/
		Iterator<Location> containerIt = loc.iterator();
		
		while (containerIt.hasNext() == true) {		
			
			Location remoteLocation = containerIt.next();	
			
			for (Iterator<AgentClassElement4SimStart> it = agentContainerList.get(remoteLocation).iterator(); it.hasNext();) {
				
				// --- Get the agent, which has to be started ------------
				AgentClassElement4SimStart agent2Start = it.next();
				// --- Check for start arguments -------------------------
				Object[] startArgs = this.getStartArguments(agent2Start);	
				// --- finally start the agent -----------------------				
				this.startAgent(agent2Start.getStartAsName(), agent2Start.getAgentClassReference(), startArgs, remoteLocation);
				if(debug){
					System.out.println("Agent "+ agent2Start.getStartAsName() + " started on "+ newContainerLocations.get(remoteLocation.getName()));
				}
									
			}
		} // --- end while
		
		if(debug){
			System.out.println("FINISHED (Predictive, static distribution)");
		}
		
	}
}