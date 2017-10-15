import com.bnp.datahub.jobs.Job
import org.apache.spark.sql.SparkSession

object AccountApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("AccountApp")
                                  .config("spark.cassandra.connection.host", "localhost")
                                  .getOrCreate()

    val filePath = "/Users/xsun/Downloads/v360/dataCsv/Account.csv"

    Job.start(sparkSession, filePath)
  }
}
