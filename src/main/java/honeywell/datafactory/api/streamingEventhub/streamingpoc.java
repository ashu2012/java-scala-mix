package honeywell.datafactory.api.streamingEventhub;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.name.Names;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubRuntimeInformation;
import com.microsoft.azure.eventhubs.PartitionReceiver;

import javassist.bytecode.Descriptor.Iterator;

import com.microsoft.azure.eventhubs.EventHubException;

/**
 * @author ashutosh
 *
 */


public class streamingpoc implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(streamingpoc.class);

	static String namespaceName;
	static String eventHubName;
	static String sasKeyName;
	static String sasKey;
	static String startDate;
	static String timeInterval;
	static int maxEventCountRecev;
	static int numRecvRequest;

	static String startDateRecever;
	static int timeIntervalRecevr;
	static int maxEventCountRecevr;
	static int numRequestRecevr;
	static int numSendEvents;

	static boolean recvEventsTest;
	static boolean sendEventsTest;

	private static ConnectionStringBuilder connStr;
	private static EventHubClient ehClient;
	private static EventHubRuntimeInformation eventHubInfo;
	private static String partitionId;
	
	static String hiveDatabase;
	static String hiveFinalTable;
	static String HDFSLocationStream;
	static String HDFSLocationFinalStream;
	

	/*
	 * String namespaceName = "pdapoceventhub"; String eventHubName =
	 * "pointhistory"; String sasKeyName = "sendlisten"; String sasKey =
	 * "kFkLwCAIJ9f8e2eamIqx9Cn6X/locJw9xT8A8RMJx3Y="; String startDate; String
	 * timeInterval ; int maxEventCountRecev ; int numRecvRequest ;
	 */
	
	PartitionReceiver offsetReceiver = null;
	PartitionReceiver datetimeReceiver = null;
	static final String cgName = EventHubClient.DEFAULT_CONSUMER_GROUP_NAME;

	public static void main(String[] args)
			throws EventHubException, ExecutionException, InterruptedException, IOException {
		System.out.println("Hello User ! Process started ");
		checkArgument(args.length > 1, "Please provide the path of input file as first parameter.");
		AbstractModule module = new AbstractModule() {
			@Override
			protected void configure() {
				try {
					Properties props = new Properties();
					props.load(new FileInputStream("application-test.properties"));
					Names.bindProperties(binder(), props);
				} catch (IOException e) {
					LOGGER.error("Could not load config: ", e);
					System.exit(1);
				}
			}
		};

		LOGGER.info("Loading properties to Test class");

		final dbconfig instance = Guice.createInjector(module).getInstance(dbconfig.class);
		// System.out.println("myprop = " + instance.getMyprop());

		
		namespaceName = dbconfig.getNamespaceName();
		eventHubName = dbconfig.getEventHubName();
		sasKeyName = dbconfig.getSasKeyName();
		sasKey = dbconfig.getSasKey();
		startDateRecever = dbconfig.getStartDateRecever();
		timeIntervalRecevr = dbconfig.getTimeIntervalRecevr();
		maxEventCountRecevr = dbconfig.getMaxEventCountRecevr();
		numRequestRecevr = dbconfig.getNumRequestRecevr();
		numSendEvents = dbconfig.getNumRequestRecevr();
		recvEventsTest = dbconfig.isRecvEventsTest();
		sendEventsTest = dbconfig.isSendEventsTest();

		hiveDatabase= dbconfig.hiveDatabase();
		hiveFinalTable =dbconfig.hiveFinalTable();
		HDFSLocationStream= dbconfig.HDFSLocationStream();
		HDFSLocationFinalStream= dbconfig.HDFSLocationFinalStream();
		
		
		new streamingpoc().initalizeSpark(args[1]);
	}

	public void initalizeSpark(String inputFilePath)
			throws EventHubException, ExecutionException, InterruptedException, IOException {

		if (sendEventsTest) {
			try {
				eventHubSend sender = new eventHubSend();
				sender.loadsampledata();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		if (recvEventsTest) {
			buildstreamingContext();
			testReceiverDateTime();
			// testReceiverStartOfStreamFilters();
		}

		LOGGER.info("done completed");

	}

	public void buildstreamingContext()
			throws EventHubException, ExecutionException, InterruptedException, IOException {
		connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
		ehClient = EventHubClient.createFromConnectionStringSync(connStr.toString());

		eventHubInfo = ehClient.getRuntimeInformation().get();
		partitionId = eventHubInfo.getPartitionIds()[0]; // get first
															// partition's id

	}

	public void testReceiverDateTime() throws EventHubException, ExecutionException, InterruptedException, IOException {

		LocalDate localDate = LocalDate.parse(startDateRecever);
		LocalDateTime localDateTime = localDate.atStartOfDay();
		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
		String warehouseLocation = new File("spark-warehouse").getAbsolutePath();
		/*
        SparkSession spark = SparkSession
        		  .builder()
        		  .appName(streamingpoc.class.getName())
        		  .config("spark.sql.warehouse.dir", warehouseLocation)
        		  .config("spark.network.timeout",10000000 )
        		  .config("spark.rpc.askTimeout",10000000 )
        		  .enableHiveSupport()
        		  .getOrCreate();
    	*/
		final PartitionReceiver receiver = ehClient.createEpochReceiverSync(EventHubClient.DEFAULT_CONSUMER_GROUP_NAME,
				partitionId, instant, // Instant.EPOCH,
				timeIntervalRecevr);

		System.out.println("date-time receiver created...");
		Gson gson = new Gson();
		GsonBuilder gson_builder = new GsonBuilder();
		Gson gsonbuilder =  gson_builder.create();
		
		FileWriter fw = new FileWriter("out.txt");
		try {
			int receivedCount = 0;
			while (receivedCount++ < numRequestRecevr) {
				receiver.receive(maxEventCountRecevr).thenAccept(new Consumer<Iterable<EventData>>() {
					public void accept(Iterable<EventData> receivedEvents) {
						ArrayList<String> al = new ArrayList<String>();
						int batchSize = 0;
						if (receivedEvents != null) {
							for (EventData receivedEvent : receivedEvents) {
								System.out.print(String.format("Offset: %s, SeqNo: %s, EnqueueTime: %s",
										receivedEvent.getSystemProperties().getOffset(),
										receivedEvent.getSystemProperties().getSequenceNumber(),
										receivedEvent.getSystemProperties().getEnqueuedTime()));

								if (receivedEvent.getBytes() != null) {
									String data = new String(receivedEvent.getBytes(), Charset.defaultCharset());
									String jsonStr = gson.toJson(data);
									String jsonStr2= gson.fromJson(jsonStr, String.class);
									JsonElement element = gsonbuilder.fromJson(jsonStr2, JsonElement.class);
									JsonObject object = element.getAsJsonObject();
									object.addProperty("EnqueueTime", receivedEvent.getSystemProperties().getEnqueuedTime().toString());
									object.addProperty("SeqNo", receivedEvent.getSystemProperties().getSequenceNumber());
									object.addProperty("Offset", receivedEvent.getSystemProperties().getOffset().toString());
									
									al.add(object.toString());
									try {
										fw.write(jsonStr2);
										fw.write("\n");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									System.out.println(String.format("| Message Payload: %s",object.toString()));
									
									batchSize++;
								}
							}
						
							//process events
							/*
							Dataset<Row> listDS = spark.createDataset(al, Encoders.STRING()).toDF(); 
							listDS.write().mode("append").parquet(HDFSLocationStream);
							System.out.println(listDS.toJavaRDD().take(10));
							spark.sql("show databases").show();
							//spark.sql("use  da_3rdparty_stg").show();
							spark.sql("show  tables").show();
							// Register the DataFrame as a SQL temporary view
							//listDS.createOrReplaceTempView("pdaeventhub");
							//Dataset<Row> listDS1 = spark.sql("SELECT * FROM pdaeventhub");
							//listDS1.show();
							doSparkWork(spark,listDS);
							*/
						}

						System.out.println(String.format("ReceivedBatch Size: %s", batchSize));

					}
				}).get();
			}
		} finally {
			// cleaning up receivers is paramount;
			// max number of concurrent receivers per consumergroup per
			// partition is 5
			fw.close();
			receiver.close().whenComplete(new BiConsumer<Void, Throwable>() {
				public void accept(Void t, Throwable u) {
					if (u != null) {
						// wire-up this error to diagnostics infrastructure
						System.out.println(String.format("closing failed with error: %s", u.toString()));
					}
					/*
					 * try { // ehClient.closeSync(); } catch (EventHubException
					 * sbException) { // wire-up this error to diagnostics
					 * infrastructure System.out.println(String.
					 * format("closing failed with error: %s",
					 * sbException.toString())); }
					 */
				}

			}).get();

		}
	}
	
	//Scala magic to do transformation and saving
	public void doSparkWork(SparkSession spark , Dataset<Row> dsRow ){
		ScalaSparkProcessing scalaObj = new ScalaSparkProcessing(spark, dsRow, HDFSLocationFinalStream ,hiveDatabase,hiveFinalTable);
		scalaObj.startProcessing();
	}

	public void testReceiverStartOfStreamFilters() throws EventHubException {
		offsetReceiver = ehClient.createReceiverSync(cgName, partitionId, PartitionReceiver.START_OF_STREAM, false);

		System.out.println("offsetReceiver created...");

		// Iterable<EventData> startingEventsUsingOffsetReceiver =
		// offsetReceiver.receiveSync(100);

		offsetReceiver.receive(10).thenAccept(new Consumer<Iterable<EventData>>() {
			public void accept(Iterable<EventData> receivedEvents) {
				int batchSize = 0;
				if (receivedEvents != null) {
					for (EventData receivedEvent : receivedEvents) {
						System.out.print(String.format("Offset: %s, SeqNo: %s, EnqueueTime: %s",
								receivedEvent.getSystemProperties().getOffset(),
								receivedEvent.getSystemProperties().getSequenceNumber(),
								receivedEvent.getSystemProperties().getEnqueuedTime()));

						if (receivedEvent.getBytes() != null)
							System.out.println(String.format("| Message Payload: %s",
									new String(receivedEvent.getBytes(), Charset.defaultCharset())));
						batchSize++;
					}
				}

				System.out.println(String.format("ReceivedBatch Size: %s", batchSize));

			}
		});

		offsetReceiver.close();

	}
}
