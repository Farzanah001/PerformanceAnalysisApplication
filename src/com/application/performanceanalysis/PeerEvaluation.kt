package com.application.performanceanalysis

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object PeerEvaluation {
    var userDeets=ArrayList<UserData>()
    private var scan=Scanner(System.`in`)
    //var rate=RatingFactors()
    var totalPeerRating:Int=0

    @JvmStatic
    fun addUsers() {
        userDeets.add(UserData(101,"Akon","20-01-2016",Goals(RatingFactors.akonGoalsList,RatingFactors.akonGoalWeightageList,0),Assignments(RatingFactors.akonAssignmentsList,RatingFactors.akonAssignmentWeightageList,0),Projects(RatingFactors.akonProjectList,RatingFactors.akonProjectWeightageList,0)))
        userDeets.add(UserData(102,"Bkon","06-06-2019",Goals(RatingFactors.bkonGoalsList,RatingFactors.bkonGoalWeightageList,0),Assignments(RatingFactors.bkonAssignmentsList,RatingFactors.bkonAssignmentWeightageList,0),Projects(RatingFactors.bkonProjectList,RatingFactors.bkonProjectWeightageList,0)))
        userDeets.add(UserData(103,"Ckon","20-07-2017",Goals(RatingFactors.ckonGoalsList,RatingFactors.ckonGoalWeightageList,0),Assignments(RatingFactors.ckonAssignmentsList,RatingFactors.ckonAssignmentWeightageList,0),Projects(RatingFactors.ckonProjectList,RatingFactors.ckonProjectWeightageList,0)))
        userDeets.add(UserData(104,"Dkon","01-09-2020",Goals(RatingFactors.dkonGoalsList,RatingFactors.dkonGoalWeightageList,0),Assignments(RatingFactors.dkonAssignmentsList,RatingFactors.dkonAssignmentWeightageList,0),Projects(RatingFactors.dkonProjectList,RatingFactors.dkonProjectWeightageList,0)))
        userDeets.add(UserData(105,"Ekon","09-08-2018",Goals(RatingFactors.ekonGoalsList,RatingFactors.ekonGoalWeightageList,0),Assignments(RatingFactors.ekonAssignmentsList,RatingFactors.ekonAssignmentWeightageList,0),Projects(RatingFactors.ekonProjectList,RatingFactors.ekonProjectWeightageList,0)))
    }

    fun viewUsers(userDeets: ArrayList<UserData>) {
        println("User Id\tUser Name")
        for(i in 1 until this.userDeets.size){
            println("${this.userDeets[i].userId}\t\t${this.userDeets[i].name}")
        }
        println("\n\nEnter the User ID of the User to Review Them:")
        var userId=scan.nextInt()
        displayRelevantCycles(this.userDeets,SelfEvaluation.cycles,userId)
    }

    private fun displayRelevantCycles(userDeets: ArrayList<UserData>, cycles: ArrayList<CycleData>, userId:Int) {
        var changeDateFormat=SimpleDateFormat("dd-MM-yyyy")
        var userDOJ: Date?=null
        var cycleStartingDate:Date
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
                    println("${i+1}\t\t\t\t${e.cycleName}")
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
        var currentCycleName:String="";
        if(currentCycleNumber==1) currentCycleName="Cycle 1"
        else if(currentCycleNumber==2) currentCycleName="Cycle 2"
        else if(currentCycleNumber==3) currentCycleName="Cycle 3"
        else if(currentCycleNumber==4) currentCycleName="Cycle 4"
        else if(currentCycleNumber==5) currentCycleName="Cycle 5"
        else if(currentCycleNumber==6) currentCycleName="Cycle 6"
        else{
            println("Invalid Input!! Enter Valid Input.")
            viewUsers(userDeets)
        }
        getUserRating(currentCycleName)
    }

    private fun getUserRating(currentCycleName:String){
        //get the rating for all individual goals/Assignments and Projects
        //do not get these factors for the cycles that disables certain factors (like a cycle disables goals and projects, only take assignments into account)
        //call calculateFinalScore()
        for((i,e) in SelfEvaluation.cycles.withIndex()){
            if(e.cycleName == currentCycleName){
                if(e.isGoalsEnabled){
                    inner1@for(j in userDeets){
                        for(k in userDeets[i-1].goals.goalName) {
                            println(k)
                            trackTotalPeerRating()
                        }
                        break@inner1
                    }
                }
                if(e.isAssignmentsEnabled){
                    inner2@for(j in userDeets){
                        for(k in userDeets[i-1].assignments.assignmentName) {
                            println(k)
                            trackTotalPeerRating()
                        }
                        break@inner2
                    }
                }
                if(e.isProjectsEnabled){
                    inner3@for(j in userDeets){
                        for(k in userDeets[i-1].projects.projectName) {
                            println(k)
                            trackTotalPeerRating()
                        }
                        break@inner3
                    }
                }
            }
        }
    }

    private fun trackTotalPeerRating(){
        println("Give Your Rating(1-5):")
        var getRating= scan.nextInt()
        totalPeerRating +=getRating
        println("Total Rating:${totalPeerRating}")

        calculateFinalScore(totalPeerRating)
    }

    private fun calculateFinalScore(totalPeerRating:Int){

    }

}