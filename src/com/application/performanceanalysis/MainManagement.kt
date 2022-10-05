package com.application.performanceanalysis

import java.util.*
import kotlin.system.exitProcess

object MainManagement {
    var scan = Scanner(System.`in`)

    @JvmStatic
    fun runApplication() {

        SelfEvaluation.addCycles()
        PeerEvaluation.addUsers()
        RatingFactors.addGoalsData()
        RatingFactors.addAssignmentData()
        RatingFactors.addProjectsData()

        do {
            var rawChoice = ""
            var choice = 0
            println("PERFORMANCE ANALYSIS")
            println("--------------------")
            println("1. Self Evaluation")
            println("2. Peer Evaluation")
            println("3. Exit")
            println("\n\nEnter Your Choice:")
            rawChoice = scan.next()
            try {
                choice = rawChoice.toInt()
            } catch (e: Exception) {
                println("Enter Valid Input. Choose a Choice Number from the Menu Displayed (1-3)")
            }

            when (choice) {
                1 -> {
                    SelfEvaluation.viewCycles()
                }

                2 -> {
                    PeerEvaluation.viewUsers()
                }

                3 -> {
                    println("Thank You!")
                    exitProcess(0)
                }

                else -> {
                    println("Invalid Input")
                    runApplication()
                }
            }
        } while (true)
    }
}

fun main() = MainManagement.runApplication()