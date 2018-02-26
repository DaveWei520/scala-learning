package com.gome.bigData.akka

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/**
  * 定义Actor时混入ActorLoggin
  */
object Example_02 extends App {

  class MyActor extends Actor with ActorLogging {

    override def receive: Receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unknown message")
    }
  }

  //创建ActorSystem对象
  val system = ActorSystem("MyActorSystem")
  //返回ActorSystem的LoggingAdapter
  val systemLog = system.log
  //创建MyActor，指定actor名称为myactor
  val myactor = system.actorOf(Props[MyActor],name = "myactor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor!"test"
  myactor!123

  //关闭ActorSystem，停止程序的运行
  system.terminate()
}