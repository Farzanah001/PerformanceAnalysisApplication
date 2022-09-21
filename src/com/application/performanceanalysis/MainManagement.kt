package com.application.performanceanalysis

import java.util.*
import kotlin.system.exitProcess

object MainManagement {
    var scan= Scanner(System.`in`)
    @JvmStatic
    fun runApplication() {
        //val self=SelfEvaluation()
        val peer=PeerEvaluation()
        //val data=DataManagement()
        do{
            println("PERFORMANCE ANALYSIS")
            println("--------------------")
            println("1. Self Evaluation")
            println("2. Peer Evaluation")
            println("3. Exit")
            println("\n\nEnter Your Choice:")
            var choice=scan.nextInt()

            when(choice){
               1->{
                   SelfEvaluation.addCycles()
                   SelfEvaluation.viewCycles()
                   //println(SelfEvaluation.cycles.size)
               }
               2->{
                   peer.addUsers()
                   //println(peer.userDeets.size)
                   peer.viewUsers()
               }
               3-> {
                   println("Thank You!")
                   exitProcess(0)
               }
               else->{
                   println("Invalid Input")
               }
            }
        }while(choice<=2)
    }
}

fun main(){
    MainManagement.runApplication()
}