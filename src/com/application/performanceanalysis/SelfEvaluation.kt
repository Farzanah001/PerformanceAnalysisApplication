package com.application.performanceanalysis

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object SelfEvaluation {
    var cycles=ArrayList<CycleData>()
    var scan=Scanner(System.`in`)
    var totalRating:Int=0
    //var peer=PeerEvaluation()

    @JvmStatic
    fun addCycles(){
        cycles.add(CycleData("Cycle 1","01-01-2014","31-12-2016",true,true,true))
        cycles.add(CycleData("Cycle 2","01-01-2017","31-12-2017",true,false,false))
        cycles.add(CycleData("Cycle 3","01-01-2018","31-12-2018",true,true,false))
        cycles.add(CycleData("Cycle 4","01-01-2015","31-12-2019",true,false,false))
        cycles.add(CycleData("Cycle 5","01-01-2020","31-12-2020",false,false,false))
        cycles.add(CycleData("Cycle 6","01-01-2021","31-12-2021",false,false,true))
    }


    fun viewCycles() {

        //display only the cycle that is applicable for this user(Akon)

        var changeDateFormat = SimpleDateFormat("dd-MM-yyyy")
        var cycleStartingDate: Date

        //var individualUserPair = Pair<Int, String>(0, "")
        var userDOJ: Date = changeDateFormat.parse(PeerEvaluation.userDeets[0].dateOfJoining)
        println("\nCycle Number\tCycle Name")
        for ((i, e) in cycles.withIndex()) {
            cycleStartingDate = changeDateFormat.parse(e.cycleStartingFrom)
            var dateDiff = userDOJ?.compareTo(cycleStartingDate)
            if (dateDiff != null) {
                if (dateDiff < 0) {
                    println("${i + 1}\t\t\t\t${e.cycleName}")
//                    var individualUserId = individualUserPair.copy(first = PeerEvaluation.userDeets[0].userId)
//                    var individualUserCycleName = individualUserPair.copy(second = e.cycleName)
                }
            }
        }
        println("Enter the Cycle Number to Start Rating")
        var selfCycleRatingNumber=scan.nextInt()
//    println("$individualUserPair")
        displayRatingFactors(selfCycleRatingNumber)
    }

    private fun displayRatingFactors(selfCycleRatingNumber:Int){
        //display all the rating factors that are enabled for that cycle
        //call getSelfRating()
        var currentCycleName:String="";
        if(selfCycleRatingNumber==1) currentCycleName="Cycle 1"
        else if(selfCycleRatingNumber==2) currentCycleName="Cycle 2"
        else if(selfCycleRatingNumber==3) currentCycleName="Cycle 3"
        else if(selfCycleRatingNumber==4) currentCycleName="Cycle 4"
        else if(selfCycleRatingNumber==5) currentCycleName="Cycle 5"
        else if(selfCycleRatingNumber==6) currentCycleName="Cycle 6"
        else{
            println("Invalid Input!! Enter Valid Input.")
            viewCycles()
        }
        getSelfRating(currentCycleName)
    }

    private fun getSelfRating(currentCycleName:String){
        //get rating inputs
        //call calculateFinalScore()
        for((i,e) in cycles.withIndex()){
            if(e.cycleName == currentCycleName){
                if(e.isGoalsEnabled){
                    inner1@for(j in PeerEvaluation.userDeets){
                        for(k in PeerEvaluation.userDeets[i-1].goals.goalName) {
                            println(k)
                            trackTotalRating()
                        }
                        break@inner1
                    }
                }
                if(e.isAssignmentsEnabled){
                    inner2@for(j in PeerEvaluation.userDeets){
                        for(k in PeerEvaluation.userDeets[i-1].assignments.assignmentName) {
                            println(k)
                            trackTotalRating()
                        }
                        break@inner2
                    }
                }
                if(e.isProjectsEnabled){
                    inner3@for(j in PeerEvaluation.userDeets){
                        for(k in PeerEvaluation.userDeets[i-1].projects.projectName) {
                            println(k)
                            trackTotalRating()
                        }
                        break@inner3
                    }
                }
            }
        }
    }

    private fun trackTotalRating(){
        println("Give Your Rating(1-5):")
        var getRating=scan.nextInt()
        totalRating+=getRating
        println("Total Rating:$totalRating")
    }
    fun calculateFinalScore(){

    }

}