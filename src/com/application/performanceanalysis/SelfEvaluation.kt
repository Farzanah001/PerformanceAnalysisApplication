package com.application.performanceanalysis

import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object SelfEvaluation {
    var cycles=ArrayList<CycleData>()
    var scan=Scanner(System.`in`)
    var totalSelfRating:Int=0

    var selfCycleRatingNumber:Int=0

    var goalsTotalAverage=0f
    var assignmentsTotalAverage=0f
    var projectsTotalAverage=0f
    var finalSelfRatingScore:Float=0f

    @JvmStatic
    fun addCycles(){
        cycles.add(CycleData("Cycle 1","01-01-2014","31-12-2016",true,true,true,true))
        cycles.add(CycleData("Cycle 2","01-01-2017","31-12-2017",true,false,false,true))
        cycles.add(CycleData("Cycle 3","01-01-2018","31-12-2018",true,true,false,false))
        cycles.add(CycleData("Cycle 4","01-01-2015","31-12-2019",true,false,false,true))
        cycles.add(CycleData("Cycle 5","01-01-2020","31-12-2020",false,false,false,false))
        cycles.add(CycleData("Cycle 6","01-01-2021","31-12-2021",false,false,true,false))
    }


    fun viewCycles() {

        //display only the cycle that is applicable for this user(Akon)
        try {
            val changeDateFormat = SimpleDateFormat("dd-MM-yyyy")
            var cycleStartingDate: Date
            val userDOJ: Date = changeDateFormat.parse(PeerEvaluation.userDeets[0].dateOfJoining)
            println("\nCycle Number\tCycle Name")
            for ((i, e) in cycles.withIndex()) {
                cycleStartingDate = changeDateFormat.parse(e.cycleStartingFrom)
                val dateDiff = userDOJ.compareTo(cycleStartingDate)
                if (dateDiff < 0) {
                    println("${i + 1}\t\t\t\t${e.cycleName}")
                }
            }
            println("Enter the Cycle Number to Start Rating")
            selfCycleRatingNumber = scan.nextInt()
            displayRatingFactors(selfCycleRatingNumber)
        }
        catch (e:IndexOutOfBoundsException){
            println("Cycle Not Available. Please Choose a Cycle from the list Displayed!")
            viewCycles()
        }
    }

    private fun displayRatingFactors(selfCycleRatingNumber:Int){
        //display all the rating factors that are enabled for that cycle
        //call getSelfRating()
        var currentCycleName =""
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
        for((index, element) in cycles.withIndex()){
            if(element.cycleName == currentCycleName) {
                    if (element.isGoalsEnabled) {
                        calculateGoalsTotalAverage(element,index)
                    }
                    if (element.isAssignmentsEnabled) {
                        calculateAssignmentsTotalAverage(element,index)
                    }
                    if (element.isProjectsEnabled) {
                        calculateProjectsTotalAverage(element,index)
                    }
                calculateFinalScore()
            }
        }
        //calculateFinalScore(goalsTotalAverage,assignmentsTotalAverage,projectsTotalAverage)
    }

    private fun calculateGoalsTotalAverage(element: CycleData, index: Int) {
        var tempWeightageSumOfGoals = 0f
        //var goalsTotalAverage = 0f
        if (element.isWeightageEnabled) {
            for (j in PeerEvaluation.userDeets) {
                for (k in PeerEvaluation.userDeets[index - 1].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    var getRating= scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        var tempWeightage = PeerEvaluation.userDeets[index - 1].goals.weightage[index]
                        tempWeightageSumOfGoals += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                }
                goalsTotalAverage = totalSelfRating / tempWeightageSumOfGoals
                println("Total Rating:$goalsTotalAverage")
                break
            }
        } else {
            for ((j, e) in PeerEvaluation.userDeets.withIndex()) {
                for (k in PeerEvaluation.userDeets[index - 1].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        totalSelfRating += getRating
                    }
                }
                var totalNoOfGoals = PeerEvaluation.userDeets[j].goals.goalName.size
                goalsTotalAverage = (totalSelfRating / totalNoOfGoals).toFloat()
                println("Total Rating:$goalsTotalAverage")
                break
            }
        }
    }

    private fun calculateAssignmentsTotalAverage(element: CycleData,index: Int){
        var tempWeightageSumOfAssignments =0f
        //var assignmentsTotalAverage =0f
        if(element.isWeightageEnabled) {
            inner2@ for (j in PeerEvaluation.userDeets) {
                for (k in PeerEvaluation.userDeets[index - 1].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        var tempWeightage = PeerEvaluation.userDeets[index - 1].assignments.weightage[index]
                        tempWeightageSumOfAssignments += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                }
                assignmentsTotalAverage = totalSelfRating / tempWeightageSumOfAssignments
                println("Total Rating:$assignmentsTotalAverage")
                break@inner2
            }
        }
        else {
            for ((j,e) in PeerEvaluation.userDeets.withIndex()) {
                for (k in PeerEvaluation.userDeets[index - 1].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        totalSelfRating += getRating
                    }
                }
                var totalNoOfAssignments=PeerEvaluation.userDeets[j].assignments.assignmentName.size
                assignmentsTotalAverage = (totalSelfRating / totalNoOfAssignments).toFloat()
                println("Total Rating:$assignmentsTotalAverage")
                break
            }
        }
    }

    private fun calculateProjectsTotalAverage(element: CycleData,index: Int) {
        var tempWeightageSumOfProjects = 0f
        //var projectsTotalAverage = 0f
        if (element.isWeightageEnabled) {
            inner3@ for (j in PeerEvaluation.userDeets) {
                for (k in PeerEvaluation.userDeets[index - 1].projects.projectName) {
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        var tempWeightage = PeerEvaluation.userDeets[index - 1].projects.weightage[index]
                        tempWeightageSumOfProjects += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                }
                projectsTotalAverage = totalSelfRating / tempWeightageSumOfProjects
                println("Total Rating:$projectsTotalAverage")
                break@inner3
            }
        }
        else{
            inner3@ for ((j,e) in PeerEvaluation.userDeets.withIndex()) {
                for (k in PeerEvaluation.userDeets[index - 1].projects.projectName) {
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        displayRatingFactors(selfCycleRatingNumber)
                    }
                    else {
                        totalSelfRating += getRating
                    }
                }
                var totalNoOfProjects=PeerEvaluation.userDeets[j].projects.projectName.size
                projectsTotalAverage = (totalSelfRating / totalNoOfProjects).toFloat()
                println("Total Rating:$projectsTotalAverage")
                break@inner3
            }
        }
    }


    private fun calculateFinalScore(){
        val actualGoalsScore:Float
        val actualAssignmentsScore:Float
        val actualProjectsScore:Float

        var totalGoalsEnabledCount=0f
        var totalAssignmentsEnabledCount=0f
        var totalProjectsEnabledCount=0f
        for(i in cycles){
            if(i.isGoalsEnabled){
                totalGoalsEnabledCount++
            }
            if(i.isAssignmentsEnabled){
                totalAssignmentsEnabledCount++
            }
            if(i.isProjectsEnabled){
                totalProjectsEnabledCount++
            }
        }
        actualGoalsScore=goalsTotalAverage/totalGoalsEnabledCount
        actualAssignmentsScore=assignmentsTotalAverage/totalAssignmentsEnabledCount
        actualProjectsScore=projectsTotalAverage/totalProjectsEnabledCount

        finalSelfRatingScore=actualGoalsScore+actualAssignmentsScore+actualProjectsScore

        println("\n\nFINAL RATING: $finalSelfRatingScore")
        MainManagement.runApplication()
    }

}