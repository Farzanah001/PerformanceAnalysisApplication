package com.application.performanceanalysis

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class PeerEvaluation {
    var userDeets=ArrayList<UserData>()
    var scan=Scanner(System.`in`)
    fun addUsers() {
        userDeets.add(UserData(101,"Akon","20-01-2016"))
        userDeets.add(UserData(102,"Bkon","06-06-2019"))
        userDeets.add(UserData(103,"Ckon","20-07-2017"))
        userDeets.add(UserData(104,"Dkon","01-09-2020"))
        userDeets.add(UserData(105,"Ekon","09-08-2018"))
    }

    fun viewUsers(serDeets: ArrayList<UserData>) {
        println("User Id\tUser Name")
        for(i in 1 until userDeets.size){
            println("${userDeets[i].userId}\t\t${userDeets[i].name}")
        }
        println("\n\nEnter the User ID of the User to Review Them:")
        var userId=scan.nextInt()
        displayRelevantCycles(userDeets,SelfEvaluation.cycles,userId)
    }

    private fun displayRelevantCycles(userDeets: ArrayList<UserData>, cycles: ArrayList<CycleData>, userId:Int) {
        var changeDateFormat=SimpleDateFormat("dd-MM-yyyy")
        var userDOJ: Date?=null
        var cycleStartingDate:Date?=null
        var userCycleMap=HashMap<Int,String>()
        for(i in userDeets){
            if(userId==i.userId)
                userDOJ=changeDateFormat.parse(i.dateOfJoining)
        }
        println("Available Cycles for User $userId")
        println("\nCycle Number\tCycle Name")
        for((i,e) in SelfEvaluation.cycles.withIndex()){
            cycleStartingDate=changeDateFormat.parse(e.cycleStartingFrom)
            var dateDiff=userDOJ?.compareTo(cycleStartingDate)
            if (dateDiff != null) {
                if(dateDiff<0){
                    println("$i\t\t\t\t${e.cycleName}")
                    userCycleMap[userId] = e.cycleName //userCycleMap.put(userId,i.cycleName)
                }
            }
        }
        println("Enter the Cycle Number to Start Rating:")
        var currentCycleNumber=scan.nextInt()

        showRatingFactors(currentCycleNumber)
    }

    private fun showRatingFactors(currentCycleNumber: Int) {
        //shows the rating factors like goals, assignments, and projects and its weightage
        //call getUserRating()



    }

    fun getUserRating(){
        //get the rating for all individual goals/Assignments and Projects
        //do not get these factors for the cycles that disables certain factors (like a cycle disables goals and projects, only take assignments into account)
        //call calculateFinalScore()

    }

    fun calculateFinalScore(){

    }

}