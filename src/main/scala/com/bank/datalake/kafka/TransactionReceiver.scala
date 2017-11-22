package com.bank.datalake.kafka

import com.bank.datalake.model.Transaction
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver


class TransactionReceiver(ratePerSec: Int) extends Receiver[Transaction](StorageLevel.MEMORY_ONLY){

  override def onStart(): Unit = {
    new Thread("Test Source") {
      override def run(): Unit ={
        receive()
      }
    }.start
  }

  override def onStop(): Unit = {
    println("Stop..")
  }

  def receive():Unit = {
    var count:Int = 1

    while(!isStopped()){
      store(new Transaction("1", count.toString, System.currentTimeMillis().toString, "transaction" + count, scala.util.Random.nextInt(10000)))

      if(count == Int.MinValue)
        count = 1
      else
        count += 1

       Thread.sleep((1000.toDouble/ratePerSec).toInt)
    }
  }
}
