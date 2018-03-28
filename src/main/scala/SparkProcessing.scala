package  com.honeywell.faa


/*
import java.io.File
import java.io.FileReader
import java.util.Properties
import java.util.ArrayList

import org.apache.spark.SparkContext
import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.streaming.eventhubs.EventHubsUtils
import org.apache.spark.SparkConf

import org.apache.log4j.Logger

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.Datase
import org.apache.spark.sql.Row

import java.sql.Timestamp
import org.joda.time.DateTime
import org.joda.time._
import org.joda.time.format._

import com.typesafe.config.{ Config, ConfigFactory, ConfigValueFactory }
import scala.collection.JavaConversions._
import com.typesafe.config._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{ OutputMode, Trigger }
import org.apache.spark.sql.types._
import com.google.gson.Gson
import scala.reflect._
import scala.reflect.runtime.universe._

case class SensorData2(PlantCode: String, AssetCode: String, InstanceCode: String, ColumnNameExtracted: Option[String] = None, EventDate: java.sql.Date, EventTimestamp: java.sql.Timestamp, cpu_free_avg: Option[Float] = None, cpu_free_min: Option[Float] = None, cee_c300_crcycle_overrun: Option[Float] = None, c300_lan_a_pause_counts: Option[Float] = None, c300_lan_b_pause_counts: Option[Float] = None, cf9_rx_pause_counts: Option[Float] = None, cf9_tx_pause_counts: Option[Float] = None, c300_lan_a_rx_rate: Option[Float] = None, c300_lan_b_rx_rate: Option[Float] = None, c300_lan_a_tx_rate: Option[Float] = None, c300_lan_b_tx_rate: Option[Float] = None, switch_300_input_rate: Option[Float] = None, switch_300_output_rate: Option[Float] = None, EnqueTime: Option[java.sql.Timestamp] = None, Flag: Option[String] = None, PartitionYear: Option[String] = None) extends Serializable

case class EventData(ItemName: String, Value: String, Time: String, Quality: String, EnqueueTime: String, SeqNo: Long, Offset: String) extends Serializable

class ScalaSparkProcessing(val spark: SparkSession, val dsRow: Dataset[Row], val savingPath: String, val hiveDatabase: String, val hiveFinalTable: String) extends Serializable {

  def startProcessing() {
    //debug
    //var a = spark.read.text("file:////home/ashutosh/workspace/eventhubstreaming/streamingPOC/out.txt")
    //var dsRow =a
    dsRow.show();
    import spark.implicits._
    var c = dsRow.map(x ⇒ new Gson().fromJson(x(0).toString, classOf[EventData])).toDF.rdd.map(x ⇒ lambda39(x)).toDF
    c.show()
    spark.sql("show tables").show()
    c.registerTempTable("t1")
    spark.sql("select * from t1 ").show(20, false)

    var d = spark.sql("select PlantCode, AssetCode , InstanceCode , first(ColumnNameExtracted) as ColumnNameExtracted, first(EventDate) as EventDate ,first(date_format(EventTimestamp, \"yyyy-MM-dd HH:mm Z\") ) as EventTimestamp, avg(cpu_free_avg) as cpu_free_avg ,avg(cpu_free_min) as cpu_free_min, avg(cee_c300_crcycle_overrun)  as cee_c300_crcycle_overrun, avg(c300_lan_a_pause_counts) as c300_lan_a_pause_counts, avg(c300_lan_b_pause_counts) as c300_lan_b_pause_counts, avg(cf9_rx_pause_counts) as cf9_rx_pause_counts, avg(cf9_tx_pause_counts) as cf9_tx_pause_counts, avg(c300_lan_a_rx_rate) as c300_lan_a_rx_rate, avg(c300_lan_b_rx_rate) as c300_lan_b_rx_rate, avg(c300_lan_a_tx_rate) as c300_lan_a_tx_rate, avg(c300_lan_b_tx_rate) as c300_lan_b_tx_rate, avg(switch_300_input_rate) as switch_300_input_rate, avg(switch_300_output_rate) as switch_300_output_rate, first(EnqueTime) as EnqueTime, first(Flag) as Flag,first(PartitionYear) as PartitionYear from t1 group by PlantCode, AssetCode , InstanceCode , date_format(EventTimestamp, \"yyyy-MM-dd HH:mm Z\")  sort by  EventTimestamp ")

    d.show(20, false)
    d.write.mode("append").parquet(savingPath);
    d.write.format("parquet").mode(org.apache.spark.sql.SaveMode.Append).partitionBy("PartitionYear").saveAsTable(hiveDatabase + "." + hiveFinalTable)

  }

  def lambda39(x: Row) = {
    var c = x(0).toString.split("\\.")
    println(x)
    println(c(0).toString)
    var eventTime = try {
      DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss").parseDateTime(x(2).toString).withZone(DateTimeZone.UTC)
    } catch {
      case _: Throwable ⇒ DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss Z").parseDateTime(x(2).toString).withZone(DateTimeZone.UTC)

    }
    var enqueueTime: Option[org.joda.time.DateTime] = None
    var newEventTime = eventTime.plusMinutes(1)
    var PartitionYear = eventTime.year.get.toString
    var flag = "OnTime"
    try {

      enqueueTime = Some(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").parseDateTime(x(4).toString).withZone(DateTimeZone.UTC))

      var getnewEventTime = eventTime.plusMinutes(1)
      // flag as delay if event enqued time is more than eventtime plus 1 Minute(spark job time)
      //Event is inserted late in Event Hub
      if (enqueueTime.get.isAfter(getnewEventTime)) {
        flag = "Delayed"
      }

    } catch { case _: Throwable ⇒ println("Got Date format ISSUE") }

    var eventDate = new java.sql.Date(eventTime.getMillis)
    var timeStamp = new Timestamp(eventTime.getMillis)
    var enqueTimeStamp = new Timestamp(enqueueTime.getOrElse(eventTime).getMillis)

    var obj = SensorData2(c(0), c(1), c(2), Option(c(3)), eventDate, timeStamp)

    var myMap = obj.getClass.getDeclaredFields.map(_.getName).zip(obj.productIterator.to).toMap

    //println(myMap)

    val mutableMap = myMap ++ List((c(3) -> x(1)))

    println(mutableMap)

    def getMap(mutableMap: Map[String, Any], mapKey: String): Option[Float] = {

      mutableMap.get(mapKey).get match {
        case floatStr: Any ⇒ floatStr match {
          case str: String ⇒ Option(str.toFloat)
          case _ ⇒ None
        }

        case _ ⇒ None

      }
    }

    SensorData2(c(0), c(1), c(2), Option(c(3)), eventDate, timeStamp, getMap(mutableMap, "cpu_free_avg"), getMap(mutableMap, "cpu_free_min"), getMap(mutableMap, "cee_c300_crcycle_overrun"), getMap(mutableMap, "c300_lan_a_pause_counts"), getMap(mutableMap, "c300_lan_b_pause_counts"), getMap(mutableMap, "cf9_rx_pause_counts"), getMap(mutableMap, "cf9_tx_pause_counts"), getMap(mutableMap, "c300_lan_a_rx_rate"), getMap(mutableMap, "c300_lan_b_rx_rate"), getMap(mutableMap, "c300_lan_a_tx_rate"), getMap(mutableMap, "c300_lan_b_tx_rate"), getMap(mutableMap, "switch_300_input_rate"), getMap(mutableMap, "switch_300_output_rate"), Option(enqueTimeStamp), Option(flag), Option(PartitionYear))

  }

}

*/