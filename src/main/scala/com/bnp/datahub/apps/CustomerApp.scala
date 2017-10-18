
import com.bnp.datahub.jobs.CustomerJob
import org.apache.spark.sql.SparkSession

object CustomerApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("AccountApp")
      .config("spark.cassandra.connection.host", "localhost")
      .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    val filePath = "/Users/xsun/Downloads/v360/dataCsv/Customer.csv"

    CustomerJob.start(sparkSession, filePath)
  }
}
