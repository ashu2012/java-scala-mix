package honeywell.datafactory.api.streamingEventhub;



import java.io.*;
import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import com.microsoft.azure.eventhubs.EventHubRuntimeInformation;
import com.microsoft.azure.eventhubs.PartitionReceiver;

import java.io.IOException;

import org.apache.avro.RandomData;
import org.joda.time.*;

/**
 * @author ashutosh
 *
 */
public class eventHubSend {
	

    final String namespaceName = dbconfig.getNamespaceName();
    final String eventHubName = dbconfig.getEventHubName();
    final String sasKeyName = dbconfig.getSasKeyName();
    final String sasKey = dbconfig.getSasKey() ;
    static int numSendEvents = dbconfig.getNumSendEvents();
    static int delayInMillieconds= dbconfig.getDelayInMillieconds();
    final static String fileNameForEventLoad= dbconfig.eventHubSendDummyEventsFile();
    
	private static ConnectionStringBuilder connStr;
    private static  EventHubClient ehClient ;
    private  static EventHubRuntimeInformation eventHubInfo;
    private  static String partitionId ;
    
    PartitionReceiver offsetReceiver = null;
    PartitionReceiver datetimeReceiver = null;
    static final String cgName = EventHubClient.DEFAULT_CONSUMER_GROUP_NAME;
    
    
	public  void loadsampledata() 
			throws EventHubException, ExecutionException, InterruptedException, IOException
    {
		connStr= new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
		System.out.println(connStr.toString());
		//String connStr2="Endpoint=sb://pdapoceventhub.servicebus.windows.net;EntityPath=pointhistory;SharedAccessKeyName=sendlisten;SharedAccessKey=kFkLwCAIJ9f8e2eamIqx9Cn6X/locJw9xT8A8RMJx3Y=;OperationTimeout=PT1M;RetryPolicy=Default" ;
		
		String connStr2="Endpoint=sb://"+namespaceName+".servicebus.windows.net;EntityPath="+eventHubName+";SharedAccessKeyName="+sasKeyName+";SharedAccessKey="+sasKey+";OperationTimeout=PT1M;RetryPolicy=Default" ;
		System.out.println(connStr2);
	    EventHubClient ehClient = EventHubClient.createFromConnectionStringSync(connStr2);
	    
	    System.out.println(connStr2);

        BufferedReader br = new BufferedReader(new FileReader(fileNameForEventLoad));

        String str;
        while ((str = br.readLine()) != null){
            System.out.println(str);

            System.out.println(String.format("Dummy Event:- %s",str));
            byte[] payloadBytes = str.getBytes("UTF-8");
            EventData sendEvent = new EventData(payloadBytes);

            //Pause for x seconds
            Thread.sleep(delayInMillieconds);
            ehClient.sendSync(sendEvent);
         }

		ehClient.close();
    }
}
