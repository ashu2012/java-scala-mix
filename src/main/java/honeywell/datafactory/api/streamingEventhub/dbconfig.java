package honeywell.datafactory.api.streamingEventhub;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class dbconfig {

	private static String namespaceName;
	private static String eventHubName;
	private static String sasKeyName;
	private static String sasKey;
	private static String partitioncount;
	private static String consumergroup;
	private static String checkpointdir;
	private static String checkpointinterval;
	private static String maxRate;
	private static String batchDuration;
	private static String appName;
	private static String progressDir ;
	private static String startDateRecever;
	private static int timeIntervalRecevr ;
	private static int maxEventCountRecevr ;
	private static int numRequestRecevr ;
	private static int numSendEvents;
	private static int delayInMillieconds;
	private static boolean recvEventsTest ;
	private static boolean sendEventsTest ;
	private static String hiveDatabase;
	private static String hiveFinalTable;
	private static String HDFSLocationStream ;
	private static String HDFSLocationFinalStream ;
	private static String eventHubSendDummyEventsFile ;



	@Inject
	public dbconfig(@Named("spark.namespaceName") String namespaceName, @Named("spark.eventHubName") String eventHubName,
			@Named("spark.sasKeyName") String sasKeyName, @Named("spark.sasKey") String sasKey,
			@Named("spark.partitioncount") String partitioncount, @Named("spark.consumergroup") String consumergroup,
			@Named("spark.checkpointdir") String checkpointdir, @Named("spark.progressDir") String progressDir,@Named("spark.checkpointinterval") String checkpointinterval,
			@Named("spark.maxRate") String maxRate, @Named("spark.batchDuration") String batchDuration,
			@Named("spark.appName") String appName, @Named("startDateRecever") String startDateRecever,@Named("timeIntervalRecevr") int timeIntervalRecevr , @Named("maxEventCountRecevr") int maxEventCountRecevr,@Named("numRequestRecevr") int numRequestRecevr,@Named("numSendEvents") int numSendEvents ,@Named("recvEventsTest") boolean recvEventsTest,@Named("sendEventsTest") boolean sendEventsTest , @Named("delayInMillieconds") int delayInMillieconds,
			@Named("hiveDatabase") String hiveDatabase,@Named("hiveFinalTable") String hiveFinalTable,@Named("HDFSLocationStream") String HDFSLocationStream,@Named("HDFSLocationFinalStream") String HDFSLocationFinalStream ,
					@Named("eventHubSendDummyEventsFile") String	eventHubSendDummyEventsFile
			) {
		
		dbconfig.namespaceName = namespaceName;
		dbconfig.eventHubName = eventHubName;
		dbconfig.sasKeyName = sasKeyName;
		dbconfig.sasKey = sasKey;
		dbconfig.partitioncount = partitioncount;
		dbconfig.consumergroup = consumergroup;
		dbconfig.checkpointdir = checkpointdir;
		dbconfig.progressDir = progressDir;
		dbconfig.checkpointinterval = checkpointinterval;
		dbconfig.maxRate = maxRate;
		dbconfig.batchDuration = batchDuration;
		dbconfig.appName = appName;
		dbconfig.startDateRecever= startDateRecever;
		dbconfig.timeIntervalRecevr =timeIntervalRecevr;
		dbconfig.maxEventCountRecevr = maxEventCountRecevr;
		dbconfig.numRequestRecevr =numRequestRecevr;
		dbconfig.numSendEvents=numSendEvents;
		
		dbconfig.recvEventsTest= recvEventsTest;
		dbconfig.sendEventsTest = sendEventsTest;
		
		dbconfig.hiveDatabase= hiveDatabase;
		dbconfig.hiveFinalTable = hiveFinalTable;
		dbconfig.HDFSLocationStream= HDFSLocationStream;
		dbconfig.HDFSLocationFinalStream = HDFSLocationFinalStream;

		dbconfig.eventHubSendDummyEventsFile = eventHubSendDummyEventsFile ;
		
	}

	public static String getNamespaceName() {
		return namespaceName;
	}

	public static String getEventHubName() {
		return eventHubName;
	}

	public static String getSasKeyName() {
		return sasKeyName;
	}

	public static String getSasKey() {
		return sasKey;
	}

	public static String getPartitioncount() {
		return partitioncount;
	}

	public static String getConsumergroup() {
		return consumergroup;
	}

	public static String getCheckpointdir() {
		return checkpointdir;
	}

	public static String getProgressDir() {
		return progressDir;
	}
	
	public static String getCheckpointinterval() {
		return checkpointinterval;
	}

	public static String getMaxRate() {
		return maxRate;
	}

	public static String getBatchDuration() {
		return batchDuration;
	}

	public static String getAppName() {
		return appName;
	}

	public static String getStartDateRecever() {
		return startDateRecever;
	}

	public static int getTimeIntervalRecevr() {
		return timeIntervalRecevr;
	}

	public static int getMaxEventCountRecevr() {
		return maxEventCountRecevr;
	}

	public static int getNumRequestRecevr() {
		return numRequestRecevr;
	}
	
	public static int getDelayInMillieconds() {
		return delayInMillieconds;
	}

	public static int getNumSendEvents() {
		return numSendEvents;
	}

	public static boolean isRecvEventsTest() {
		return recvEventsTest;
	}

	public static boolean isSendEventsTest() {
		return sendEventsTest;
	}

	public static String hiveDatabase() {
		return hiveDatabase;
	}
	public static String hiveFinalTable() {
		return hiveFinalTable;
	}
	public static String HDFSLocationStream() {
		return HDFSLocationStream;
	}
	public static String HDFSLocationFinalStream() {
		return HDFSLocationFinalStream;
	}

	public static String eventHubSendDummyEventsFile(){ return eventHubSendDummyEventsFile ; }

}