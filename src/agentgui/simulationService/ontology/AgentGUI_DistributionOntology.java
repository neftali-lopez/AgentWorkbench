// file: AgentGUI_DistributionOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package agentgui.simulationService.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: AgentGUI_DistributionOntology.java
 * @author ontology bean generator
 * @version 2010/11/19, 15:30:25
 */
public class AgentGUI_DistributionOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "AgentGUI-Distribution";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new AgentGUI_DistributionOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String CLIENTTRIGGER_CLIENTLOAD="clientLoad";
    public static final String CLIENTTRIGGER_TRIGGERTIME="triggerTime";
    public static final String CLIENTTRIGGER_CLIENTBENCHMARKVALUE="clientBenchmarkValue";
    public static final String CLIENTTRIGGER="ClientTrigger";
    public static final String CLIENTUNREGISTER="ClientUnregister";
    public static final String CLIENTREMOTECONTAINERREQUEST_REMOTECONFIG="RemoteConfig";
    public static final String CLIENTREMOTECONTAINERREQUEST="ClientRemoteContainerRequest";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEPID="remotePID";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEPERFORMANCE="remotePerformance";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEOS="remoteOS";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEBENCHMARKRESULT="remoteBenchmarkResult";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTECONTAINERNAME="remoteContainerName";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEADDRESS="remoteAddress";
    public static final String CLIENTREMOTECONTAINERREPLY="ClientRemoteContainerReply";
    public static final String CLIENTREGISTER_CLIENTADDRESS="clientAddress";
    public static final String CLIENTREGISTER_CLIENTTIME="clientTime";
    public static final String CLIENTREGISTER_CLIENTPERFORMANCE="clientPerformance";
    public static final String CLIENTREGISTER_CLIENTOS="clientOS";
    public static final String CLIENTREGISTER="ClientRegister";
    public static final String SHOWMONITORGUI="ShowMonitorGUI";
    public static final String SLAVEREGISTER_SLAVEOS="slaveOS";
    public static final String SLAVEREGISTER_SLAVETIME="slaveTime";
    public static final String SLAVEREGISTER_SLAVEADDRESS="slaveAddress";
    public static final String SLAVEREGISTER_SLAVEPERFORMANCE="slavePerformance";
    public static final String SLAVEREGISTER="SlaveRegister";
    public static final String SLAVETRIGGER_SLAVEBENCHMARKVALUE="slaveBenchmarkValue";
    public static final String SLAVETRIGGER_TRIGGERTIME="triggerTime";
    public static final String SLAVETRIGGER_SLAVELOAD="slaveLoad";
    public static final String SLAVETRIGGER="SlaveTrigger";
    public static final String SLAVEUNREGISTER="SlaveUnregister";
    public static final String REGISTERRECEIPT="RegisterReceipt";
    public static final String PLATFORMTIME_TIMESTAMPASSTRING="TimeStampAsString";
    public static final String PLATFORMTIME="PlatformTime";
    public static final String BENCHMARKRESULT_BENCHMARKVALUE="benchmarkValue";
    public static final String BENCHMARKRESULT="BenchmarkResult";
    public static final String REMOTECONTAINERCONFIG_JADEHOST="jadeHost";
    public static final String REMOTECONTAINERCONFIG_JVMMEMALLOCINITIAL="jvmMemAllocInitial";
    public static final String REMOTECONTAINERCONFIG_HOSTEXCLUDEIP="hostExcludeIP";
    public static final String REMOTECONTAINERCONFIG_JADEJARINCLUDELIST="jadeJarIncludeList";
    public static final String REMOTECONTAINERCONFIG_JVMMEMALLOCMAXIMUM="jvmMemAllocMaximum";
    public static final String REMOTECONTAINERCONFIG_JADEISREMOTECONTAINER="jadeIsRemoteContainer";
    public static final String REMOTECONTAINERCONFIG_JADEPORT="jadePort";
    public static final String REMOTECONTAINERCONFIG_JADECONTAINERNAME="jadeContainerName";
    public static final String REMOTECONTAINERCONFIG_JADESERVICES="jadeServices";
    public static final String REMOTECONTAINERCONFIG_JADESHOWGUI="jadeShowGUI";
    public static final String REMOTECONTAINERCONFIG="RemoteContainerConfig";
    public static final String PLATFORMLOAD_LOADMEMORYJVM="loadMemoryJVM";
    public static final String PLATFORMLOAD_LOADEXCEEDED="loadExceeded";
    public static final String PLATFORMLOAD_LOADNOTHREADS="loadNoThreads";
    public static final String PLATFORMLOAD_LOADCPU="loadCPU";
    public static final String PLATFORMLOAD_LOADMEMORYSYSTEM="loadMemorySystem";
    public static final String PLATFORMLOAD="PlatformLoad";
    public static final String OSINFO_OS_ARCH="os_arch";
    public static final String OSINFO_OS_NAME="os_name";
    public static final String OSINFO_OS_VERSION="os_version";
    public static final String OSINFO="OSInfo";
    public static final String PLATFORMADDRESS_URL="url";
    public static final String PLATFORMADDRESS_IP="ip";
    public static final String PLATFORMADDRESS_PORT="port";
    public static final String PLATFORMADDRESS_HTTP4MTP="http4mtp";
    public static final String PLATFORMADDRESS="PlatformAddress";
    public static final String PLATFORMPERFORMANCE_CPU_SPEEDMHZ="cpu_speedMhz";
    public static final String PLATFORMPERFORMANCE_CPU_MODEL="cpu_model";
    public static final String PLATFORMPERFORMANCE_CPU_VENDOR="cpu_vendor";
    public static final String PLATFORMPERFORMANCE_MEMORY_TOTALMB="memory_totalMB";
    public static final String PLATFORMPERFORMANCE_CPU_NUMBEROF="cpu_numberOf";
    public static final String PLATFORMPERFORMANCE="PlatformPerformance";

  /**
   * Constructor
  */
  private AgentGUI_DistributionOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema platformPerformanceSchema = new ConceptSchema(PLATFORMPERFORMANCE);
    add(platformPerformanceSchema, agentgui.simulationService.ontology.PlatformPerformance.class);
    ConceptSchema platformAddressSchema = new ConceptSchema(PLATFORMADDRESS);
    add(platformAddressSchema, agentgui.simulationService.ontology.PlatformAddress.class);
    ConceptSchema osInfoSchema = new ConceptSchema(OSINFO);
    add(osInfoSchema, agentgui.simulationService.ontology.OSInfo.class);
    ConceptSchema platformLoadSchema = new ConceptSchema(PLATFORMLOAD);
    add(platformLoadSchema, agentgui.simulationService.ontology.PlatformLoad.class);
    ConceptSchema remoteContainerConfigSchema = new ConceptSchema(REMOTECONTAINERCONFIG);
    add(remoteContainerConfigSchema, agentgui.simulationService.ontology.RemoteContainerConfig.class);
    ConceptSchema benchmarkResultSchema = new ConceptSchema(BENCHMARKRESULT);
    add(benchmarkResultSchema, agentgui.simulationService.ontology.BenchmarkResult.class);
    ConceptSchema platformTimeSchema = new ConceptSchema(PLATFORMTIME);
    add(platformTimeSchema, agentgui.simulationService.ontology.PlatformTime.class);

    // adding AgentAction(s)
    AgentActionSchema registerReceiptSchema = new AgentActionSchema(REGISTERRECEIPT);
    add(registerReceiptSchema, agentgui.simulationService.ontology.RegisterReceipt.class);
    AgentActionSchema slaveUnregisterSchema = new AgentActionSchema(SLAVEUNREGISTER);
    add(slaveUnregisterSchema, agentgui.simulationService.ontology.SlaveUnregister.class);
    AgentActionSchema slaveTriggerSchema = new AgentActionSchema(SLAVETRIGGER);
    add(slaveTriggerSchema, agentgui.simulationService.ontology.SlaveTrigger.class);
    AgentActionSchema slaveRegisterSchema = new AgentActionSchema(SLAVEREGISTER);
    add(slaveRegisterSchema, agentgui.simulationService.ontology.SlaveRegister.class);
    AgentActionSchema showMonitorGUISchema = new AgentActionSchema(SHOWMONITORGUI);
    add(showMonitorGUISchema, agentgui.simulationService.ontology.ShowMonitorGUI.class);
    AgentActionSchema clientRegisterSchema = new AgentActionSchema(CLIENTREGISTER);
    add(clientRegisterSchema, agentgui.simulationService.ontology.ClientRegister.class);
    AgentActionSchema clientRemoteContainerReplySchema = new AgentActionSchema(CLIENTREMOTECONTAINERREPLY);
    add(clientRemoteContainerReplySchema, agentgui.simulationService.ontology.ClientRemoteContainerReply.class);
    AgentActionSchema clientRemoteContainerRequestSchema = new AgentActionSchema(CLIENTREMOTECONTAINERREQUEST);
    add(clientRemoteContainerRequestSchema, agentgui.simulationService.ontology.ClientRemoteContainerRequest.class);
    AgentActionSchema clientUnregisterSchema = new AgentActionSchema(CLIENTUNREGISTER);
    add(clientUnregisterSchema, agentgui.simulationService.ontology.ClientUnregister.class);
    AgentActionSchema clientTriggerSchema = new AgentActionSchema(CLIENTTRIGGER);
    add(clientTriggerSchema, agentgui.simulationService.ontology.ClientTrigger.class);

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_NUMBEROF, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_MEMORY_TOTALMB, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_VENDOR, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_MODEL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_SPEEDMHZ, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_HTTP4MTP, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_PORT, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_IP, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_URL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    osInfoSchema.add(OSINFO_OS_VERSION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    osInfoSchema.add(OSINFO_OS_NAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    osInfoSchema.add(OSINFO_OS_ARCH, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADMEMORYSYSTEM, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADCPU, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADNOTHREADS, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADEXCEEDED, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADMEMORYJVM, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADESHOWGUI, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADESERVICES, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADECONTAINERNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEPORT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEISREMOTECONTAINER, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JVMMEMALLOCMAXIMUM, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEJARINCLUDELIST, (TermSchema)getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_HOSTEXCLUDEIP, (TermSchema)getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JVMMEMALLOCINITIAL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEHOST, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    benchmarkResultSchema.add(BENCHMARKRESULT_BENCHMARKVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformTimeSchema.add(PLATFORMTIME_TIMESTAMPASSTRING, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_SLAVELOAD, platformLoadSchema, ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_TRIGGERTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_SLAVEBENCHMARKVALUE, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVETIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEOS, osInfoSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTOS, osInfoSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTECONTAINERNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEBENCHMARKRESULT, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEOS, osInfoSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEPID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    clientRemoteContainerRequestSchema.add(CLIENTREMOTECONTAINERREQUEST_REMOTECONFIG, remoteContainerConfigSchema, ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_CLIENTBENCHMARKVALUE, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_TRIGGERTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_CLIENTLOAD, platformLoadSchema, ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
