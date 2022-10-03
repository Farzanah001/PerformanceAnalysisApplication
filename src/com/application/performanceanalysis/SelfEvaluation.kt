package com.application.performanceanalysis

import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object SelfEvaluation {
    var cycles=ArrayList<CycleData>()
    var scan=Scanner(System.`in`)
    var set=SetValues()

    var selfCycleRatingNumber:Int=0

    var goalsTotalAverage=0f
    var assignmentsTotalAverage=0f
    var projectsTotalAverage=0f
    var finalSelfRatingScore:Float=0f

    var goalsNACount=0
    var assignmentsNACount=0
    var projectsNACount=0

    @JvmStatic
    fun addCycles(){
        cycles.add(CycleData("Cycle 1","01-01-2019","31-12-2016",true,true,true,true,true))
        cycles.add(CycleData("Cycle 2","01-01-2017","31-12-2017",true,true,true,true,false))
        cycles.add(CycleData("Cycle 3","01-01-2018","31-12-2018",true,true,false,true,false))
        cycles.add(CycleData("Cycle 4","01-01-2015","31-12-2019",true,false,false,true,true))
        cycles.add(CycleData("Cycle 5","01-01-2020","31-12-2020",false,false,false,false,false))
        cycles.add(CycleData("Cycle 6","01-01-2021","31-12-2021",false,false,true,false,true))
    }


    fun viewCycles() {
        //display only the cycle that is applicable for this user(Akon)
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
        var cycleNo=scan.next()
        try {
            selfCycleRatingNumber = cycleNo.toInt()
        }
        catch (e:IndexOutOfBoundsException){
            println("Cycle Not Available. Please Choose a Cycle from the list Displayed!")
            viewCycles()
        }
        catch (e:InputMismatchException){
            println("Enter Appropriate Input, From the Given Cycle Numbers")
            viewCycles()
        }
        catch (e:Exception){
            println("Enter Valid Cycle Number")
            viewCycles()
        }
        displayRatingFactors(selfCycleRatingNumber)
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
        for(element in cycles){
            if(element.cycleName == currentCycleName) {
                if(!element.isNAEnabled) {
                    if (element.isGoalsEnabled) {
                        println("---GOALS---")
                        calculateGoalsTotalAverage(element)
                    }
                    if (element.isAssignmentsEnabled) {
                        println("---ASSIGNMENTS---")
                        calculateAssignmentsTotalAverage(element)
                    }
                    if (element.isProjectsEnabled) {
                        println("---PROJECTS---")
                        calculateProjectsTotalAverage(element)
                    }
                    if(!(element.isGoalsEnabled) && !(element.isAssignmentsEnabled) && !(element.isProjectsEnabled)){
                        println("No Rating Modules are Enabled for this Cycle! Please choose any other Cycle to Rate.")
                        //MainManagement.runApplication()
                        break
                    }
                    calculateFinalScore()
                }
                else{
                    if (element.isGoalsEnabled) {
                        println("---GOALS---")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                    if (element.isAssignmentsEnabled) {
                        println("---ASSIGNMENTS---")
                        calculateAssignmentsTotalAverageWithNA(element)
                    }
                    if (element.isProjectsEnabled) {
                        println("---PROJECTS---")
                        calculateProjectsTotalAverageWithNA(element)
                    }
                    if(!(element.isGoalsEnabled) && !(element.isAssignmentsEnabled) && !(element.isProjectsEnabled)){
                        println("No Rating Modules are Enabled for this Cycle! Please choose any other Cycle to Rate.")
                        //MainManagement.runApplication()
                        break
                    }
                    calculateFinalScoreWithNA()
                }
            }
        }

    }

    private fun calculateGoalsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfGoals = 0f
        var totalSelfRating=0f
        //var goalsTotalAverage = 0f

        if (element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                println("Current User Id${PeerEvaluation.userDeets[j].userId}")
                for (k in PeerEvaluation.userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        goalsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = PeerEvaluation.userDeets[j].goals.weightage[j]
                        tempWeightageSumOfGoals += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                goalsTotalAverage = totalSelfRating / tempWeightageSumOfGoals
                println("Total Goals Rating:%.1f".format(goalsTotalAverage))
                break
            }
        } else {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    val getRating = scan.nextInt()
                    if(getRating==0){
                        goalsNACount++
                    }
                    else if(getRating in 1..5) {
                        totalSelfRating += getRating
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                var totalNoOfGoals = (PeerEvaluation.userDeets[j].goals.goalName.size)
                goalsTotalAverage = (totalSelfRating / totalNoOfGoals)
                println("Total Goals Rating:%.1f".format(goalsTotalAverage))
                break
            }
        }
    }

    private fun calculateAssignmentsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfAssignments = 0f
        var totalSelfRating=0f
        if (element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                println("Current User Id${PeerEvaluation.userDeets[j].userId}")
                for (k in PeerEvaluation.userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        assignmentsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = PeerEvaluation.userDeets[j].assignments.weightage[j]
                        tempWeightageSumOfAssignments += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                assignmentsTotalAverage = totalSelfRating / tempWeightageSumOfAssignments
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        } else {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    val getRating = scan.nextInt()
                    if(getRating==0){
                        assignmentsNACount++
                    }
                    else if(getRating in 1..5) {
                        totalSelfRating += getRating
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateAssignmentsTotalAverageWithNA(element)
                    }
                }
                var totalNoOfAssignments = (PeerEvaluation.userDeets[j].assignments.assignmentName.size)
                goalsTotalAverage = (totalSelfRating / totalNoOfAssignments)
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        }
    }

    private fun calculateProjectsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfProjects = 0f
        var totalSelfRating=0f

        if (element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                println("Current User Id${PeerEvaluation.userDeets[j].userId}")
                for (k in PeerEvaluation.userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        projectsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = PeerEvaluation.userDeets[j].assignments.weightage[j]
                        tempWeightageSumOfProjects += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalSelfRating += weightageProduct
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                projectsTotalAverage = totalSelfRating / tempWeightageSumOfProjects
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        } else {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    val getRating = scan.nextInt()
                    if(getRating==0){
                        projectsNACount++
                    }
                    else if(getRating in 1..5) {
                        totalSelfRating += getRating
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateAssignmentsTotalAverageWithNA(element)
                    }
                }
                var totalNoOfProjects = (PeerEvaluation.userDeets[j].projects.projectName.size)
                projectsTotalAverage = (totalSelfRating / totalNoOfProjects)
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        }
    }

    private fun calculateGoalsTotalAverage(element: CycleData) {
        var tempWeightageSumOfGoals = 0f
        var totalSelfRating=0f
        //var goalsTotalAverage = 0f

        if (element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                println("Current User Id${PeerEvaluation.userDeets[j].userId}")
                for (k in PeerEvaluation.userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    var getRating= scan.nextInt()
                    var tempWeightage = PeerEvaluation.userDeets[j].goals.weightage[j]
                    tempWeightageSumOfGoals += tempWeightage
                    var weightageProduct = getRating.times(tempWeightage)
                    totalSelfRating += weightageProduct
                }
                goalsTotalAverage = totalSelfRating / tempWeightageSumOfGoals
                println("Total Goals Rating:%.1f".format(goalsTotalAverage))
                break
            }
        } else {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    totalSelfRating += getRating
                }
                var totalNoOfGoals = PeerEvaluation.userDeets[j].goals.goalName.size
                goalsTotalAverage = (totalSelfRating / totalNoOfGoals)
                println("Total Goals Rating:%.1f".format(goalsTotalAverage))
                break
            }
        }
    }

    private fun calculateAssignmentsTotalAverage(element: CycleData){
        var tempWeightageSumOfAssignments =0f
        var totalSelfRating=0f
        //var assignmentsTotalAverage =0f
        if(element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    var tempWeightage = PeerEvaluation.userDeets[j].assignments.weightage[j]
                    tempWeightageSumOfAssignments += tempWeightage
                    var weightageProduct = getRating.times(tempWeightage)
                    totalSelfRating += weightageProduct
                }
                assignmentsTotalAverage = totalSelfRating / tempWeightageSumOfAssignments
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        }
        else {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    totalSelfRating += getRating
                }
                var totalNoOfAssignments=PeerEvaluation.userDeets[j].assignments.assignmentName.size
                assignmentsTotalAverage = (totalSelfRating / totalNoOfAssignments)
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        }
    }

    private fun calculateProjectsTotalAverage(element: CycleData) {
        var tempWeightageSumOfProjects = 0f
        var totalSelfRating=0f
        if (element.isWeightageEnabled) {
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    var tempWeightage = PeerEvaluation.userDeets[j].projects.weightage[j]
                    tempWeightageSumOfProjects += tempWeightage
                    var weightageProduct = getRating.times(tempWeightage)
                    totalSelfRating += weightageProduct
                }
                projectsTotalAverage = totalSelfRating / tempWeightageSumOfProjects
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        }
        else{
            for(j in 0 until PeerEvaluation.userDeets.size) {
                for (k in PeerEvaluation.userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    totalSelfRating += getRating
                }
                var totalNoOfProjects=PeerEvaluation.userDeets[j].projects.projectName.size
                projectsTotalAverage = (totalSelfRating / totalNoOfProjects)
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        }
    }


    private fun calculateFinalScore(){
        var actualGoalsScore:Float=0f
        var actualAssignmentsScore:Float=0f
        var actualProjectsScore:Float=0f

        var totalGoalsEnabledCount=0
        var totalAssignmentsEnabledCount=0
        var totalProjectsEnabledCount=0
        for(i in 0 until PeerEvaluation.userDeets.size) {
            for (element in cycles) {
                if (element.isGoalsEnabled) {
                    totalGoalsEnabledCount = PeerEvaluation.userDeets[i].goals.goalName.size
                    actualGoalsScore = goalsTotalAverage / totalGoalsEnabledCount
                }
                if (element.isAssignmentsEnabled) {
                    totalAssignmentsEnabledCount = PeerEvaluation.userDeets[i].assignments.assignmentName.size
                    actualAssignmentsScore = assignmentsTotalAverage / totalAssignmentsEnabledCount
                }
                if (element.isProjectsEnabled) {
                    totalProjectsEnabledCount = PeerEvaluation.userDeets[i].projects.projectName.size
                    actualProjectsScore = projectsTotalAverage / totalProjectsEnabledCount
                }
            }
        }
        calculateFinalSelfRatingScore(actualGoalsScore,actualAssignmentsScore,actualProjectsScore)

    }

    private fun calculateFinalScoreWithNA() {
        var actualGoalsScore:Float=0f
        var actualAssignmentsScore:Float=0f
        var actualProjectsScore:Float=0f

        var totalGoalsEnabledCount=0
        var totalAssignmentsEnabledCount=0
        var totalProjectsEnabledCount=0
        for(i in 0 until PeerEvaluation.userDeets.size) {
            for (element in cycles) {
                if (element.isGoalsEnabled) {
                    totalGoalsEnabledCount = (PeerEvaluation.userDeets[i].goals.goalName.size)- goalsNACount
                    actualGoalsScore = goalsTotalAverage / totalGoalsEnabledCount
                }
                if (element.isAssignmentsEnabled) {
                    totalAssignmentsEnabledCount = (PeerEvaluation.userDeets[i].assignments.assignmentName.size)- assignmentsNACount
                    actualAssignmentsScore = assignmentsTotalAverage / totalAssignmentsEnabledCount
                }
                if (element.isProjectsEnabled) {
                    totalProjectsEnabledCount = (PeerEvaluation.userDeets[i].projects.projectName.size)- projectsNACount
                    actualProjectsScore = projectsTotalAverage / totalProjectsEnabledCount
                }
            }
        }
        calculateFinalSelfRatingScore(actualGoalsScore,actualAssignmentsScore,actualProjectsScore)
    }


    private fun calculateFinalSelfRatingScore(actualGoalsScore: Float, actualAssignmentsScore: Float, actualProjectsScore: Float) {
        finalSelfRatingScore=actualGoalsScore+actualAssignmentsScore+actualProjectsScore
        println("-------------------------")
        println("\n\nFINAL RATING SCORE: %.1f".format(finalSelfRatingScore))
        println("-------------------------")
        set.setFinalStatus(finalSelfRatingScore)
        finalSelfRatingScore=0f
        println()
    }

}