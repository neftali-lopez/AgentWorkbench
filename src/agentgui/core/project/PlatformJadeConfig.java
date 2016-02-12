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
package agentgui.core.project;

import jade.core.Profile;
import jade.core.ProfileImpl;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import agentgui.core.application.Application;
import agentgui.core.application.Language;
import agentgui.core.config.GlobalInfo;
import agentgui.core.network.NetworkAddresses;
import agentgui.core.network.PortChecker;

/**
 * With this class, the Profile of a new JADE-Container can be configured.
 * To use this class, just create a new instance of it and go throw 
 * configurations like in the example below.<br>
 * After configuration you can use the method 'getNewInstanceOfProfilImpl()'
 * which returns a new Instance of 'jade.core.Profile'. This can be used to 
 * create a new JADE-Container.<br>
 * <br>
 * EXAMPLE:<br><br
 * <blockquote><code>
 *  PlatformJadeConfig pjc = new PlatformJadeConfig();<br>
 *	pjc.setLocalPort(1099);<br>
 *	pjc.addService(PlatformJadeConfig.SERVICE_AgentGUI_LoadService);<br>
 *  pjc.addService(PlatformJadeConfig.SERVICE_AgentGUI_SimulationService);<br>
 *  pjc.addService(PlatformJadeConfig.SERVICE_NotificationService);<br>
 *  <br>
 *	Profile profile = pjc.getNewInstanceOfProfilImpl();<br>
 * </code></blockquote>
 *  
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg-Essen
 */
public class PlatformJadeConfig implements Serializable {
	
	private static final long serialVersionUID = -9062155032902746361L;
	
	private static final boolean debug = false;
	
	/**The enumeration MTP_Creation describes the possibilities, how the MTP-address can be configured. */
	public static enum MTP_Creation {
		ConfiguredByJADE,
		ConfiguredByIPandPort
	}
	public static String MTP_IP_AUTO_Config = "Auto-Configuration";
	
	
	// --- Services 'Activated automatically' ---------------------------------
	public static final String SERVICE_MessagingService = jade.core.messaging.MessagingService.class.getName();
	public static final String SERVICE_AgentManagementService = jade.core.management.AgentManagementService.class.getName();
	
	// --- Services 'Active by default' ---------------------------------------
	public static final String SERVICE_AgentMobilityService = jade.core.mobility.AgentMobilityService.class.getName();
	public static final String SERVICE_NotificationService = jade.core.event.NotificationService.class.getName(); 
	
	// --- Services 'Inactive by default' -------------------------------------
	public static final String SERVICE_MainReplicationService = jade.core.replication.MainReplicationService.class.getName();
	public static final String SERVICE_FaultRecoveryService = jade.core.faultRecovery.FaultRecoveryService.class.getName();
	public static final String SERVICE_AddressNotificationService = jade.core.replication.AddressNotificationService.class.getName();
	public static final String SERVICE_TopicManagementService = jade.core.messaging.TopicManagementService.class.getName();
	public static final String SERVICE_PersistentDeliveryService = jade.core.messaging.PersistentDeliveryService.class.getName();
	public static final String SERVICE_UDPNodeMonitoringServ = jade.core.nodeMonitoring.UDPNodeMonitoringService.class.getName();
	public static final String SERVICE_BEManagementService = jade.imtp.leap.nio.BEManagementService.class.getName();
	
	// --- Agent.GUI-Services -------------------------------------------------
	public static final String SERVICE_DebugService = jade.debugging.DebugService.class.getName();
	public static final String SERVICE_AgentGUI_LoadService = agentgui.simulationService.LoadService.class.getName();
	public static final String SERVICE_AgentGUI_SimulationService = agentgui.simulationService.SimulationService.class.getName();
	
	// --- Add-On-Services ----------------------------------------------------
	public static final String SERVICE_InterPlatformMobilityService = jade.core.migration.InterPlatformMobilityService.class.getName();
	
	/** Array of services, which will be started with JADE in every case */
	private static final String[] autoServices = {SERVICE_MessagingService, SERVICE_AgentManagementService};
	private static final String AUTOSERVICE_TextAddition = "Startet automatisch !";
	 
	
	// --- Runtime variables -------------------------------------------------- 
	@XmlTransient
	private Project currProject = null;
	@XmlTransient
	private DefaultListModel<String> listModelServices = null;
	
	@XmlElement(name="useLocalPort")	
	private Integer useLocalPort = Application.getGlobalInfo().getJadeLocalPort();
	
	@XmlElement(name="mtpCreation")
	private MTP_Creation mtpCreation = MTP_Creation.ConfiguredByJADE;
	@XmlElement(name="mtpIpAddress")
	private String mtpIpAddress = MTP_IP_AUTO_Config;
	@XmlElement(name="mtpPort")
	private Integer useLocalPortMTP = Application.getGlobalInfo().getJadeLocalPortMTP();
	
	@XmlElementWrapper(name = "serviceList")
	@XmlElement(name="service")			
	private HashSet<String> useServiceList = new HashSet<String>();
	
	
	/**
	 * Constructor of this class.
	 */
	public PlatformJadeConfig() {
	}

	/**
	 * Returns the current project.
	 * @return the project
	 */
	public Object getProject() {
		return this.currProject;
	}
	/**
	 * Sets the current project.
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.currProject = project;
	}
	
	/**
	 * This method returns the TextAddition if a Service is an automatically starting service of JADE.
	 * @return the auto service text addition
	 */
	public static String getAutoServiceTextAddition() {
		return " " + Language.translate(AUTOSERVICE_TextAddition) + " ";
	}
	
	/**
	 * Returns if a service generally starts while JADE is starting.
	 * @param serviceReference the service reference
	 * @return true, if is auto service
	 */
	public static boolean isAutoService(String serviceReference) {
		for (int i = 0; i < autoServices.length; i++) {
			if (autoServices[i].equalsIgnoreCase(serviceReference)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This Method returns a new Instance of {@link ProfileImpl} that is used to start JADE-Container.
	 * @return jade.core.Profile
	 */
	public ProfileImpl getNewInstanceOfProfilImpl(){
		ProfileImpl profile = new ProfileImpl();
		if (debug) {
			this.setProfileDumOptions(profile);
		}
		this.setProfileLocalHost(profile);
		this.setProfileLocalPort(profile);
		this.setProfileLocalPortMTP(profile);
		this.setProfileServices(profile);
		return profile;
	}
	/**
	 * Adds the local configured DUMP_OPTIONS to the input instance of Profile.
	 * @param profile the profile to work on
	 */
	private void setProfileDumOptions(Profile profile){
		profile.setParameter(Profile.DUMP_OPTIONS, "true");
	}	
	/**
	 * Adds the configured 'LOCAL_HOST' to the input instance of Profile 
	 * in case that the localhost hosts the server.master too. In that case
	 * the platform URL should exactly match the configuration.
	 * @param profile the profile to work on
	 */
	private void setProfileLocalHost(Profile profile){
		if (Application.getGlobalInfo().getJadeUrlConfigurationForMaster().isLocalhost()) {
			profile.setParameter(Profile.LOCAL_HOST, Application.getGlobalInfo().getServerMasterURL());	
		}
	}
	/**
	 * Adds the configured 'LOCAL_PORT' to the input instance of Profile.
	 * @param profile the profile to work on
	 */
	private void setProfileLocalPort(Profile profile){
		Integer freePort = new PortChecker(this.useLocalPort).getFreePort();
		profile.setParameter(Profile.LOCAL_PORT, freePort.toString());
	}
	/**
	 * Adds the configured 'jade_mtp_http_port' to the input instance of Profile.
	 * @param profile the new profile local port mtp
	 */
	private void setProfileLocalPortMTP(ProfileImpl profile) {
		
		// --------------------------------------------------------------------
		// --- Check MTP settings if a project is defined ---------------------
		// --------------------------------------------------------------------
		if (this.currProject!=null) {
			// ----------------------------------------------------------------
			// --- Use the project settings for the MTP configuration ---------
			// ----------------------------------------------------------------
			if (this.getMtpCreation()==MTP_Creation.ConfiguredByIPandPort) {
				// --- Determine the IP address to use ------------------------
				String ipAddress = null;
				if (this.getMtpIpAddress()==null || this.getMtpIpAddress().equals("") || this.getMtpIpAddress().equals(MTP_IP_AUTO_Config)) {
					// --- Auto configuration of the IP address ---------------
					NetworkAddresses networkAddresses = new NetworkAddresses();
					InetAddress inetAddress = networkAddresses.getPreferredInetAddress();
					if (inetAddress!=null) {
						ipAddress = inetAddress.getHostAddress();
					}
				} else {
					// --- Use configured IP address --------------------------
					ipAddress = this.getMtpIpAddress();
				}
				
				// --- Set the MTP address ------------------------------------ 
				if (ipAddress!=null) {
					Integer freePort = new PortChecker(this.getLocalPortMTP(), ipAddress).getFreePort();
					profile.setParameter(Profile.MTPS, "jade.mtp.http.MessageTransportProtocol(http://" + ipAddress + ":" + freePort + "/acc)");
					profile.setParameter(Profile.LOCAL_HOST, ipAddress);
//					profile.setParameter(Profile.CONTAINER_NAME, ipAddress);
//					profile.setParameter(Profile.PLATFORM_ID, ipAddress);
//					profile.setParameter(Profile.PRIVILEDGE_LOGICAL_NAME, ipAddress);
				}
			}
			
		} else {
			// ----------------------------------------------------------------
			// --- Use the global settings for the MTP configuration ----------
			// ----------------------------------------------------------------
			GlobalInfo globalInfo = Application.getGlobalInfo();
			MTP_Creation mtpCreation = globalInfo.getOwnMtpCreation();
			String mtpIpAddress = globalInfo.getOwnMtpIP();
			Integer mtpPort = globalInfo.getOwnMtpPort();
			
			if (mtpCreation==MTP_Creation.ConfiguredByIPandPort) {
				
				String ipAddress = null;
				if (mtpIpAddress==null || mtpIpAddress.equals("") || mtpIpAddress.equals(MTP_IP_AUTO_Config)) {
					// --- Auto configuration of the IP address ---------------
					NetworkAddresses networkAddresses = new NetworkAddresses();
					InetAddress inetAddress = networkAddresses.getPreferredInetAddress();
					if (inetAddress!=null) {
						ipAddress = inetAddress.getHostAddress();
					}
				} else {
					// --- Use configured IP address --------------------------
					ipAddress = mtpIpAddress;
				}	
				
				// --- Set the MTP address ------------------------------------ 
				if (ipAddress!=null) {
					Integer freePort = new PortChecker(mtpPort, ipAddress).getFreePort();
					profile.setParameter(Profile.MTPS, "jade.mtp.http.MessageTransportProtocol(http://" + ipAddress + ":" + freePort + "/acc)");
					profile.setParameter(Profile.LOCAL_HOST, ipAddress);
//					profile.setParameter(Profile.CONTAINER_NAME, ipAddress);
//					profile.setParameter(Profile.PLATFORM_ID, ipAddress);
//					profile.setParameter(Profile.PRIVILEDGE_LOGICAL_NAME, ipAddress);
				}
			}
			
			if (globalInfo.getJadeUrlConfigurationForMaster().isLocalhost()) {
				// --- Set MTP port in case of SERVER on local machines -------
				switch (globalInfo.getExecutionMode()) {
				case SERVER:
				case SERVER_MASTER:
				case SERVER_SLAVE:
					// --------------------------------------------------------
					// --- See if the configure port for MTP is free ----------
					// --- May happen if a slave is executed, while ----------- 
					// --- a master is already running ------------------------
					// --------------------------------------------------------
					Integer freePort = new PortChecker(globalInfo.getServerMasterPort4MTP(), globalInfo.getServerMasterURL()).getFreePort();
					profile.setParameter("jade_mtp_http_port", freePort.toString());	
					break;

				default:
					break;
				}	
			}
			
		}
		
	}
	
	/**
	 * Adds the local configured SERVICES to the input instance of Profile.
	 * @param profile the profile to work on
	 */
	private void setProfileServices(Profile profile){
		String serviceListString = this.getServiceListArgument();
		if (serviceListString.equalsIgnoreCase("")==false || serviceListString!=null) {
			profile.setParameter(Profile.SERVICES, serviceListString);
		}
	}	
	/**
	 * This method walks through the HashSet of configured Services and returns them
	 * as a String separated with a semicolon (';').
	 *
	 * @return String
	 */
	public String getServiceListArgument() {
		String serviceListString = "";
		Iterator<String> it = useServiceList.iterator();
		while (it.hasNext()) {
			String singeleService = it.next();
			if (singeleService.endsWith(";")==true) {
				serviceListString += singeleService;
			} else {
				serviceListString += singeleService + ";";	
			}
		}
		return serviceListString;
	}
	
	/**
	 * Can be used in order to add a class reference to an extended JADE-BaseService.
	 *
	 * @param serviceClassReference the service class reference
	 */
	public void addService(String serviceClassReference) {
		
		if (this.isUsingService(serviceClassReference)==false && serviceClassReference.contains(getAutoServiceTextAddition())==false) {
			
			// --- add to the local HashSet -------------------------
			this.useServiceList.add(serviceClassReference);
			// --- add to the DefaultListModel ----------------------
			this.getListModelServices().addElement(serviceClassReference);
			// --- sort the ListModel -------------------------------
			this.sortListModelServices();
			// --- if set, set project changed and unsaved ----------
			if (this.currProject!=null) {
				this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
			}
		}
	}

	/**
	 * Can be used in order to remove a class reference to an extended JADE-BaseService.
	 *
	 * @param serviceClassReference the service class reference
	 */
	public void removeService(String serviceClassReference) {
		
		if (this.isUsingService(serviceClassReference)==true) {
			// --- remove from the local HashSet --------------------
			this.useServiceList.remove(serviceClassReference);
			// --- remove from the DefaultListModel -----------------
			this.getListModelServices().removeElement(serviceClassReference);
			// --- if set, set project changed and unsaved ----------
			if (this.currProject!=null) {
				this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
			}
		}
	}
	
	/**
	 * This method will remove all Services from the current Profile.
	 */
	public void removeAllServices() {
		this.useServiceList.clear();
		this.listModelServices.removeAllElements();
		// --- if set, set project changed and unsaved ----------
		if (currProject!=null) {
			this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
		}
	}
	
	/**
	 * Checks if a Service is configured for this instance.
	 * The requested Service can be given with the actual class of the service
	 *
	 * @param requestedService the requested service
	 * @return boolean
	 */
	public boolean isUsingService(String requestedService) {
		if ( useServiceList.contains(requestedService) == true ) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Counts the number of services which are currently configured.
	 * @return the number of services used
	 */
	public Integer countUsedServices() {
		return this.useServiceList.size();
	}
	
	
	/**
	 * With this class the LocalPort, which will be used from a JADE-Container can be set.
	 * @param port2Use the new local port
	 */
	public void setLocalPort(int port2Use){
		useLocalPort = port2Use;
		// --- if set, set project changed and unsaved ----------
		if (currProject!=null) {
			this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
		}
	}
	/**
	 * Returns the current Port which is  configured for a JADE-Container.
	 * @return the local port on which JADE is running
	 */
	@XmlTransient
	public Integer getLocalPort() {
		return useLocalPort;
	}

	/**
	 * Sets the use local port to use for the JADE MTP
	 * @param newMTPport the new MTP port to use
	 */
	public void setLocalPortMTP(Integer newMTPport) {
		this.useLocalPortMTP = newMTPport;
		// --- if set, set project changed and unsaved ----------
		if (currProject!=null) {
			this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
		}
	}
	/**
	 * Returns the current Port which is configured for the MTP of the JADE main container.
	 * @return the local MTP port 
	 */
	@XmlTransient
	public Integer getLocalPortMTP() {
		return useLocalPortMTP;
	}

	/**
	 * Sets how the MTP settings have to be created.
	 * @param mtpCreation the new {@link MTP_Creation}
	 */
	public void setMtpCreation(MTP_Creation mtpCreation) {
		this.mtpCreation = mtpCreation;
		// --- if set, set project changed and unsaved ----------
		if (currProject!=null) {
			this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
		}
	}
	/**
	 * Returns how the MTP settings have to be created.
	 * @return the mtp usage
	 */
	@XmlTransient
	public MTP_Creation getMtpCreation() {
		return mtpCreation;
	}
	
	/**
	 * Sets the MTP IP-address.
	 * @param mtpIpAddress the new MTP IP-address
	 */
	public void setMtpIpAddress(String mtpIpAddress) {
		this.mtpIpAddress = mtpIpAddress;
		// --- if set, set project changed and unsaved ----------
		if (currProject!=null) {
			this.currProject.setChangedAndNotify(Project.CHANGED_JadeConfiguration);
		}
	}
	/**
	 * Returns the MTP IP-address.
	 * @return the MTP IP-address.
	 */
	@XmlTransient
	public String getMtpIpAddress() {
		return mtpIpAddress;
	}

	/**
	 * Gets the list model services.
	 * @return the listModelServices
	 */
	@XmlTransient
	public DefaultListModel<String> getListModelServices() {
		if (listModelServices==null) {
			listModelServices = new DefaultListModel<String>();
			Iterator<String> it = this.useServiceList.iterator();
			while (it.hasNext()) {
				listModelServices.addElement(it.next());
			}
			this.sortListModelServices();
		}
		return listModelServices;
	}

	/**
	 * This method will sort the current list model for the chosen services.
	 */
	private void sortListModelServices() {
		
		if (useServiceList.size()>1) {
			Vector<String> sorty = new Vector<String>(useServiceList);
			Collections.sort(sorty);
			this.listModelServices.removeAllElements();
			for (int i = 0; i < sorty.size(); i++) {
				this.listModelServices.addElement(sorty.get(i));
			}
		}
	}
	
	/**
	 * This Method compares the current instance with another instances
	 * of this class and returns true, if they are logical identical.
	 *
	 * @param jadeConfig2 the jade config2
	 * @return boolean
	 */
	public boolean isEqual(PlatformJadeConfig jadeConfig2) {
		
		if (this.countUsedServices()!=jadeConfig2.countUsedServices()) return false;

		Iterator<String> it = this.useServiceList.iterator();
		while(it.hasNext()) {
			String currService = it.next();
			if (jadeConfig2.isUsingService(currService)==false) return false;
		}

		if (jadeConfig2.getLocalPort().equals(this.getLocalPort())==false) return false;
		if (jadeConfig2.getLocalPortMTP().equals(this.getLocalPortMTP())==false) return false;
		if (jadeConfig2.getMtpCreation()!=this.getMtpCreation()) return false;
		return true;
	}

	/**
	 * This Method returns a String which shows the current
	 * configuration of this instance.
	 *
	 * @return String
	 */
	public String toString() {
		String bugOut = ""; 
		bugOut += "LocalPort:" + useLocalPort + ";";
		bugOut += "Services:" + getServiceListArgument();
		return bugOut;
	}


}
