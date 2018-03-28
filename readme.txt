#################################    ASSUMPTION ###############
(1)data should be in this format:-
{"ItemName":    "HoneywellBangaloreIE1FLT3V0S6C2.C300.231.cpu_free_avg","Value": "3.44820107272321E11","Time": "11/03/2017 05:20:12","Quality":        "Good"}



(2) Date format everywhere should be as per sample data:- "MM/dd/YYYY HH:mm:ss"			
#################################  DATA COLUMS######################### 	

Event Data should have only below column names in messages:-

PlantCode	AssetCode	InstanceCode	EventDate	EventTimestamp	cpu_free_avg	cpu_free_min	cee_c300_crcycle_overrun	c300_lan_a_pause_counts	c300_lan_b_pause_counts	cf9_rx_pause_counts	cf9_tx_pause_counts	c300_lan_a_rx_rate	c300_lan_b_rx_rate	c300_lan_a_tx_rate	c300_lan_b_tx_rate	"switch_300_input_rate
"	"switch_300_output_rate
"
################################ Transformation ##############################
ItemName a.b.c.d
"fields" : [ {
    "name" : "PlantCode",     // from a
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "AssetCode",    // from b
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "InstanceCode",    // from c 
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "ColumnNameExtracted",   // from d
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  },


Store column name "d" with data value as "Value"

Average data for column name d for given window group by a b c and dataTime(till minute only) minute level.


################################# PARAMETERS IN application-test.properties ##############
namespaceName=pdapoceventhub   
eventHubName=pointhistory
sasKeyName=sendlisten
sasKey=kFkLwCAIJ9f8e2eamIqx9Cn6X/locJw9xT8A8RMJx3Y=
startDateRecever=2017-10-21
timeIntervalRecevr=2345
maxEventCountRecevr=800   // Num of mesages in single request
numRequestRecevr=5000   // NUmer of requests
numSendEvents=10     // Java client to Send events , How many test event should be send
recvEventsTest=TRUE    //   use Recever mode and save stream in HDFS
sendEventsTest=TRUE   // USE Java client to send events
delayInMillieconds=20000   // SENDEr CLIENT to use delay
hiveDatabase=default	//HIVE DATABASE
hiveFinalTable=pdapoc  //HIVE TABLE
HDFSLocationStream=/tmp/progresall  // STREAM RAW LOCATION
HDFSLocationFinalStream=/tmp/progresfinal  // STREAM FINAL LOCATION

########################### BUILD #########################################

[type in shell :-] git clone <above url>

go into clone folder eventhubstreaming/streamingPOC 
[type in shell ] mvn clean install -U

########################## SENDER MODE #################################
To send dummy events 

set property in application-test.properties
sendEventsTest=TRUE

Command:-
java -cp target/streamingPOC-jar-with-dependencies.jar   honeywell.datafactory.api.streamingEventhub.streamingpoc  file application-test.properties

######################## RECEIVER MODE #################################################
TO receive events and save to HDFS

change these properties:-

hiveDatabase=default			// hive database location	
hiveFinalTable=pdapoc           /// hive final table location 
HDFSLocationStream=/tmp/progressall  // STREAM RAW LOCATION
HDFSLocationFinalStream=/tmp/progressfinal  // STREAM FINAL LOCATION

Command:-
spark-submit --master local[2] --class honeywell.datafactory.api.streamingEventhub.streamingpoc  target/streamingPOC-jar-with-dependencies.jar  --files application-test.properties



##################################FINAl Column Schema########################################
{
  "type" : "struct",
  "fields" : [ {
    "name" : "PlantCode",
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "AssetCode",
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "InstanceCode",
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "ColumnNameExtracted",
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "EventDate",
    "type" : "date",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "EventTimestamp",
    "type" : "timestamp",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "cpu_free_avg",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "cpu_free_min",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "cee_c300_crcycle_overrun",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_a_pause_counts",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_b_pause_counts",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "cf9_rx_pause_counts",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "cf9_tx_pause_counts",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_a_rx_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_b_rx_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_a_tx_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "c300_lan_b_tx_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "switch_300_input_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "switch_300_output_rate",
    "type" : "float",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "EnqueTime",
    "type" : "timestamp",
    "nullable" : true,
    "metadata" : { }
  }, {
    "name" : "Flag",
    "type" : "string",
    "nullable" : true,
    "metadata" : { }
  } ]
}



##################### Contact ####################################
Ashutosh Pathak
ashutosh.pathak@Honeywell.com



############################ IGNORE BELOW #############################################
 spark-submit --master local[2] --class honeywell.datafactory.api.streamingEventhub.streamingpoc  target/streamingPOC-jar-with-dependencies.jar abc.txt zcdfd
 
 ### to run sender
 java -cp target/streamingPOC-jar-with-dependencies.jar   honeywell.datafactory.api.streamingEventhub.streamingpoc  asasv application-test.properties

 mvn -X install:install-file  -Dfile=azure-documentdb-1.11.0.jar -DgroupId=com.microsoft.azure -DartifactId=azure-documentdb -Dversion=1.11.0 -Dpackaging=jar
 
 
  mvn install:install-file -Dfile=lib/azure-eventhubs-eph-0.15.0.jar -DgroupId=com.microsoft.azure -DartifactId=azure-eventhubs-eph -Dversion=0.15.0 -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=./lib
  
##java based stream ingestion  
spark-submit --master local[2] --class honeywell.datafactory.api.streamingEventhub.streamingpoc  target/streamingPOC-jar-with-dependencies.jar  --config application-test.properties

## for streaming ingestion

spark-submit --verbose --master local[4] --class honeywell.datafactory.api.streamingEventhub.IngestionMain target/streamingPOC-jar-with-dependencies.jar  --files application-test.properties
