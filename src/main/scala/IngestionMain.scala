package com.honeywell.faa

// Java
/*
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.streaming.eventhubs.EventHubsUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
*/
/**
 * @author ashutosh
 *
 */
/*
object IngestionMain {

  object LOG extends Serializable {
    @transient lazy val log = Logger.getLogger(getClass.getName)
  }

  case class SensorData(id: BigInt, occupancy: BigInt, power: Float, temperature: Float, yr: String, mon: String, day: String, ts: java.sql.Timestamp)

  case class SensorDataFinal(captureTime: java.sql.Timestamp, occupancy: Option[BigInt], sensorid: BigInt, power: Option[Float], temperature: Option[Float], floorid: Option[String], yr: String, mon: String, day: String, hr: String, min: String)

  //var progressDir = "wasb://sal04sbx@salsbx01sparkstg.blob.core.windows.net/user/h217119/"
  //var progressDir = "wasb://sal04sbx@salsbx01sparkstg.blob.core.windows.net/tmp/"
  //var progressDir = "hdfs://hn1-sal04s.sentience.local/tmp/"

  def main(args: Array[String]): Unit = {

    //val spark = SparkSession.builder.appName("POC streaming").getOrCreate()

    var conf = scala.io.Source.fromFile("application-test.properties").
      getLines.
      toList.
      filter(_.trim.size > 0).
      filterNot("#;" contains _(0)).
      map(_ split ("=", 2) toList).
      map(_ :+ "true" take 2).
      map {
        s: List[String] ⇒ (s(0), s(1).split("=").map(_.trim).toList)
      }.toMap

    for ((k, v) ← conf) {
      if (v.size == 1)
        println("%s: %s" format (k, v(0)))
      else
        for (i ← 0 until v.size)
          println("%s(%s): %s" format (k, i + 1, v(i)))

    }

    var checkpointDir = conf.get("spark.checkpointdir").head.mkString
    var progressDir = conf.get("spark.progressDir").head.mkString
    var policyName = conf.get("spark.sasKeyName").head.mkString
    var policykey = conf.get("spark.sasKey").head.mkString
    var eventHubNamespace = conf.get("spark.namespaceName").head.mkString
    var eventHubName = conf.get("spark.eventHubName").head.mkString
    var batchDuration = conf.get("spark.batchDuration").head.mkString
    val numPartitions = conf.get("spark.partitioncount").head.mkString
    val consumergroup = conf.get("spark.consumergroup").head.mkString
    val checkpointInterval = conf.get("spark.checkpointinterval").head.mkString
    val maxRate = conf.get("spark.maxRate").head.mkString

    val eventhubParameters = Map[String, String](
      "eventhubs.policyname" -> policyName,
      "eventhubs.policykey" -> policykey,
      "eventhubs.namespace" -> eventHubNamespace,
      "eventhubs.name" -> eventHubName,
      "eventhubs.partition.count" -> numPartitions,
      "eventhubs.consumergroup" -> consumergroup,
      "eventhubs.checkpoint.dir" -> checkpointDir,
      "eventhubs.checkpoint.interval" -> checkpointInterval,
      "eventhubs.maxRate" -> maxRate)

    //val spark = SparkSession.builder.getOrCreate

    val sparkConfiguration: SparkConf = EventHubsUtils.initializeSparkStreamingConfigurations

    sparkConfiguration.setAppName("StreamingPOC")

    sparkConfiguration.set("hive.metastore.warehouse.dir", "/hive/warehouse/")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConfiguration).enableHiveSupport().getOrCreate()

    val sc = sparkSession.sparkContext
    // val sc = new sparkContext()

    sc.getConf.getAll.foreach(println)

    print("reading#window#event#DATA")
    val inputStream = sparkSession.readStream.format("eventhubs").options(eventhubParameters)
      .load()

    val ssc = StreamingContext.getOrCreate(checkpointDir,
      () ⇒ createStreamingContext(checkpointDir, batchDuration.toInt, eventHubNamespace, eventHubName,
        eventhubParameters, progressDir))

    ssc.start()
    ssc.awaitTermination()

  }

  private def createStreamingContext(
    sparkCheckpointDir: String,
    batchDuration: Int,
    namespace: String,
    eventHunName: String,
    eventhubParams: Map[String, String],
    progressDir: String) = {

    val sc = SparkSession.builder().enableHiveSupport().getOrCreate().sparkContext
    val sqlcontext = new SQLContext(sc)
    // val sqlcontext = new  HiveContext(sc)
    // import hiveContext.implicits._
    // import hiveContext.sql

    //sys.exit(1)
    //for production 
    //var progressDir = "/home/SENTIENCE/h217119/"

    val Holder = LOG

    print("reading#window#event#DATA3")

    val ssc = new StreamingContext(sc, Seconds(batchDuration))

    ssc.checkpoint(sparkCheckpointDir)
    val inputDirectStream = EventHubsUtils.createDirectStreams(
      ssc,
      namespace,
      progressDir,
      Map(eventHunName -> eventhubParams))

    inputDirectStream.map(receivedRecord ⇒ (new String(receivedRecord.getBody), 1)).
      reduceByKeyAndWindow((v1, v2) ⇒ v1 + v2, (v1, v2) ⇒ v1 - v2, Seconds(batchDuration * 3),
        Seconds(batchDuration)).print()

    inputDirectStream.foreachRDD { rdd ⇒
      //working below
      rdd.map(eventData ⇒ { new String(eventData.getBody) }).collect.toList.foreach(println)

      if (!rdd.isEmpty()) {

        val input = rdd.map(eventData ⇒ { new String(eventData.getBody) })

        //val input = new String(eventData.getBody)
        val dfs = sqlcontext.read.json(input)
        dfs.show(5)
        //dfs.registerTempTable("sensorData")
        Holder.log.info("reading#event#DATA2")
        sqlcontext.sql("show databases ").show()
        sqlcontext.sql("use area_one").collect()
        sqlcontext.sql("set hive.enforce.bucketing = true").collect()
        sqlcontext.sql("set hive.exec.dynamic.partition.mode=nonstrict").collect()

      }

    }
    ssc
  }

}

*/