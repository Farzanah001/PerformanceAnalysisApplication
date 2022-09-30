package com.application.performanceanalysis

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object PeerEvaluation {
    var userDeets = ArrayList<UserData>()
    private var scan = Scanner(System.`in`)
    var set=SetValues()

    var peerCycleRatingNumber:Int=0

    var goalsTotalAverage=0f
    var assignmentsTotalAverage=0f
    var projectsTotalAverage=0f
    var finalPeerRatingScore:Float=0f

    var goalsNACount=0
    var assignmentsNACount=0
    var projectsNACount=0

    @JvmStatic
    fun addUsers() {
        userDeets.add(UserData(101, "Akon", "20-01-2016",
                Goals(RatingFactors.akonGoalsList, RatingFactors.akonGoalWeightageList, 0),
                Assignments(RatingFactors.akonAssignmentsList, RatingFactors.akonAssignmentWeightageList, 0),
                Projects(RatingFactors.akonProjectList, RatingFactors.akonProjectWeightageList, 0)
            )
        )
        userDeets.add(UserData(102, "Bkon", "06-06-2019",
                Goals(RatingFactors.bkonGoalsList, RatingFactors.bkonGoalWeightageList, 0),
                Assignments(RatingFactors.bkonAssignmentsList, RatingFactors.bkonAssignmentWeightageList, 0),
                Projects(RatingFactors.bkonProjectList, RatingFactors.bkonProjectWeightageList, 0)
            )
        )
        userDeets.add(UserData(103, "Ckon", "20-07-2017",
                Goals(RatingFactors.ckonGoalsList, RatingFactors.ckonGoalWeightageList, 0),
                Assignments(RatingFactors.ckonAssignmentsList, RatingFactors.ckonAssignmentWeightageList, 0),
                Projects(RatingFactors.ckonProjectList, RatingFactors.ckonProjectWeightageList, 0)
            )
        )
        userDeets.add(UserData(104, "Dkon", "01-09-2020",
                Goals(RatingFactors.dkonGoalsList, RatingFactors.dkonGoalWeightageList, 0),
                Assignments(RatingFactors.dkonAssignmentsList, RatingFactors.dkonAssignmentWeightageList, 0),
                Projects(RatingFactors.dkonProjectList, RatingFactors.dkonProjectWeightageList, 0)
            )
        )
        userDeets.add(UserData(105, "Ekon", "09-08-2018",
                Goals(RatingFactors.ekonGoalsList, RatingFactors.ekonGoalWeightageList, 0),
                Assignments(RatingFactors.ekonAssignmentsList, RatingFactors.ekonAssignmentWeightageList, 0),
                Projects(RatingFactors.ekonProjectList, RatingFactors.ekonProjectWeightageList, 0)
            )
        )
    }

    fun viewUsers() {
        println("User Id\tUser Name")
        for (i in 1 until userDeets.size) {
            println("${userDeets[i].userId}\t\t${userDeets[i].name}")
        }
        println("\n\nEnter the User ID of the User to Review Them:")
        var userId = scan.nextInt()
        displayRelevantCycles(userDeets, SelfEvaluation.cycles, userId)
    }

    private fun displayRelevantCycles(userDeets: ArrayList<UserData>, cycles: ArrayList<CycleData>, userId: Int) {
        var changeDateFormat = SimpleDateFormat("dd-MM-yyyy")
        var userDOJ: Date? = null
        var cycleStartingDate: Date
        var userCycleMap = HashMap<Int, String>()
        for (i in userDeets) {
            if (userId == i.userId)
                userDOJ = changeDateFormat.parse(i.dateOfJoining)
        }
        println("Available Cycles for User $userId")
        println("\nCycle Number\tCycle Name")
        for ((i, e) in SelfEvaluation.cycles.withIndex()) {
            cycleStartingDate = changeDateFormat.parse(e.cycleStartingFrom)
            var dateDiff = userDOJ?.compareTo(cycleStartingDate)
            if (dateDiff != null) {
                if (dateDiff < 0) {
                    println("${i + 1}\t\t\t\t${e.cycleName}")
                    userCycleMap[userId] = e.cycleName //userCycleMap.put(userId,i.cycleName)
                }
            }
        }
        println("Enter the Cycle Number to Start Rating:")
        peerCycleRatingNumber = scan.nextInt()
        showRatingFactors(peerCycleRatingNumber, userCycleMap, userId)
    }

    private fun showRatingFactors(currentCycleNumber: Int, userCycleMap: HashMap<Int, String>, userId: Int) {
        //shows the rating factors like goals, assignments, and projects and its weightage
        //call getUserRating()
        var currentCycleName: String = "";
        if (currentCycleNumber == 1) currentCycleName = "Cycle 1"
        else if (currentCycleNumber == 2) currentCycleName = "Cycle 2"
        else if (currentCycleNumber == 3) currentCycleName = "Cycle 3"
        else if (currentCycleNumber == 4) currentCycleName = "Cycle 4"
        else if (currentCycleNumber == 5) currentCycleName = "Cycle 5"
        else if (currentCycleNumber == 6) currentCycleName = "Cycle 6"
        else {
            println("Invalid Input!! Enter Valid Input.")
            displayRelevantCycles(userDeets,SelfEvaluation.cycles,userId)
        }
        getUserRating(currentCycleName, userId)
    }

    private fun getUserRating(currentCycleName: String, userId: Int) {
        //get the rating for all individual goals/Assignments and Projects
        //do not get these factors for the cycles that disables certain factors (like a cycle disables goals and projects, only take assignments into account)
        //call calculateFinalScore()
        for(i in userDeets){
            if(i.userId==userId){
                for(element in SelfEvaluation.cycles){
                    if(element.cycleName == currentCycleName) {
                        if(!(element.isNAEnabled)) {
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
                            calculateFinalScoreWithNA()
                        }
                    }
                }
            }
        }
        calculateFinalScore()
    }

    private fun calculateGoalsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfGoals = 0f
        var totalSelfRating=0f
        //var goalsTotalAverage = 0f

        if (element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                println("Current User Id${userDeets[j].userId}")
                for (k in userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        goalsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = userDeets[j].goals.weightage[j]
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
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].goals.goalName) {
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
                var totalNoOfGoals = (userDeets[j].goals.goalName.size)
                goalsTotalAverage = (totalSelfRating / totalNoOfGoals)
                println("Total Goals Rating:%.1f".format(goalsTotalAverage))
                break
            }
        }
    }

    private fun calculateAssignmentsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfAssignments = 0f
        var totalPeerRating=0f
        if (element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                println("Current User Id${userDeets[j].userId}")
                for (k in userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        assignmentsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = userDeets[j].assignments.weightage[j]
                        tempWeightageSumOfAssignments += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalPeerRating += weightageProduct
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                assignmentsTotalAverage = totalPeerRating / tempWeightageSumOfAssignments
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        } else {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    val getRating = scan.nextInt()
                    if(getRating==0){
                        assignmentsNACount++
                    }
                    else if(getRating in 1..5) {
                        totalPeerRating += getRating
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateAssignmentsTotalAverageWithNA(element)
                    }
                }
                var totalNoOfAssignments = (userDeets[j].assignments.assignmentName.size)
                goalsTotalAverage = (totalPeerRating / totalNoOfAssignments)
                println("Total Assignments Rating:%.1f".format(assignmentsTotalAverage))
                break
            }
        }
    }

    private fun calculateProjectsTotalAverageWithNA(element: CycleData) {
        var tempWeightageSumOfProjects = 0f
        var totalPeerRating=0f

        if (element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                println("Current User Id${userDeets[j].userId}")
                for (k in userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    var getRating= scan.nextInt()
                    if(getRating==0){
                        projectsNACount++
                    }
                    else if(getRating in 1..5) {
                        var tempWeightage = userDeets[j].assignments.weightage[j]
                        tempWeightageSumOfProjects += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalPeerRating += weightageProduct
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateGoalsTotalAverageWithNA(element)
                    }
                }
                projectsTotalAverage = totalPeerRating / tempWeightageSumOfProjects
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        } else {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5) Or 0 for \"Not Applicable\"):")
                    val getRating = scan.nextInt()
                    if(getRating==0){
                        projectsNACount++
                    }
                    else if(getRating in 1..5) {
                        totalPeerRating += getRating
                    }
                    else{
                        println("Enter Only Values Ranging from 0-5")
                        calculateAssignmentsTotalAverageWithNA(element)
                    }
                }
                var totalNoOfProjects = (userDeets[j].projects.projectName.size)
                projectsTotalAverage = (totalPeerRating / totalNoOfProjects)
                println("Total Projects Rating:%.1f".format(projectsTotalAverage))
                break
            }
        }
    }


    private fun calculateProjectsTotalAverage(element: CycleData) {
        var tempWeightageSumOfProjects = 0f
        var totalPeerRating=0f
        if (element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateProjectsTotalAverage(element)
                    }
                    else {
                        var tempWeightage = userDeets[j].projects.weightage[j]
                        tempWeightageSumOfProjects += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalPeerRating += weightageProduct
                    }
                }
                projectsTotalAverage = totalPeerRating / tempWeightageSumOfProjects
                println("Total Projects Rating:${projectsTotalAverage}")
                break
            }
        }
        else{
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].projects.projectName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateProjectsTotalAverage(element)
                    }
                    else {
                        totalPeerRating += getRating
                    }
                }
                var totalNoOfProjects=userDeets[j].projects.projectName.size
                projectsTotalAverage = (totalPeerRating / totalNoOfProjects).toFloat()
                println("Total Projects Rating:${projectsTotalAverage}")
                break
            }
        }
    }

    private fun calculateAssignmentsTotalAverage(element: CycleData) {
        var tempWeightageSumOfAssignments =0f
        var totalPeerRating=0f
        //var assignmentsTotalAverage =0f
        if(element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateAssignmentsTotalAverage(element)
                    }
                    else {
                        var tempWeightage = userDeets[j].assignments.weightage[j]
                        tempWeightageSumOfAssignments += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalPeerRating += weightageProduct
                    }
                }
                SelfEvaluation.assignmentsTotalAverage = totalPeerRating / tempWeightageSumOfAssignments
                println("Total Assignments Rating:${assignmentsTotalAverage}")
                break
            }
        }
        else {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].assignments.assignmentName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateAssignmentsTotalAverage(element)
                    }
                    else {
                        totalPeerRating += getRating
                    }
                }
                var totalNoOfAssignments=userDeets[j].assignments.assignmentName.size
                assignmentsTotalAverage = (totalPeerRating / totalNoOfAssignments)
                println("Total Assignments Rating:${assignmentsTotalAverage}")
                break
            }
        }
    }

    private fun calculateGoalsTotalAverage(element: CycleData) {
        var tempWeightageSumOfGoals = 0f
        var totalPeerRating=0f

        if (element.isWeightageEnabled) {
            for(j in 0 until userDeets.size) {
                for (k in userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    var getRating= scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateGoalsTotalAverage(element)
                    }
                    else {
                        var tempWeightage = userDeets[j].goals.weightage[j]
                        tempWeightageSumOfGoals += tempWeightage
                        var weightageProduct = getRating.times(tempWeightage)
                        totalPeerRating += weightageProduct
                    }
                }
                goalsTotalAverage = totalPeerRating / tempWeightageSumOfGoals
                println("Total Goals Rating:$goalsTotalAverage")
                break
            }
        } else {
            for(j in 0..userDeets.size) {
                for (k in userDeets[j].goals.goalName) {
                    println(k)
                    println("Give Your Rating(1-5):")
                    val getRating = scan.nextInt()
                    if(getRating<=0||getRating>5){
                        println("You can only give ratings from 1 to 5. Please give a Valid Rating!")
                        calculateGoalsTotalAverage(element)
                    }
                    else {
                        totalPeerRating += getRating
                    }
                }
                var totalNoOfGoals = userDeets[j].goals.goalName.size
                goalsTotalAverage = (totalPeerRating / totalNoOfGoals).toFloat()
                println("Total Goals Rating:$goalsTotalAverage")
                break
            }
        }

    }

    private fun calculateFinalScore() {
        var actualGoalsScore: Float = 0f
        var actualAssignmentsScore: Float = 0f
        var actualProjectsScore: Float = 0f

        var totalGoalsEnabledCount = 0
        var totalAssignmentsEnabledCount = 0
        var totalProjectsEnabledCount = 0
        for(i in 0 until userDeets.size) { //for(i in 0..userDeets.size()) ->>try this
            for (element in SelfEvaluation.cycles) {
                if (element.isGoalsEnabled) {
                    totalGoalsEnabledCount = userDeets[i].goals.goalName.size
                    actualGoalsScore = goalsTotalAverage / totalGoalsEnabledCount
                }

                if (element.isAssignmentsEnabled) {
                    totalAssignmentsEnabledCount = userDeets[i].assignments.assignmentName.size
                    actualAssignmentsScore = assignmentsTotalAverage / totalAssignmentsEnabledCount
                }

                if (element.isProjectsEnabled) {
                    totalProjectsEnabledCount = userDeets[i].projects.projectName.size
                    actualProjectsScore = projectsTotalAverage / totalProjectsEnabledCount
                }

            }
        }
        calculateFinalSelfRatingScore(actualGoalsScore, actualAssignmentsScore, actualProjectsScore)
    }

    private fun calculateFinalScoreWithNA() {
        var actualGoalsScore: Float = 0f
        var actualAssignmentsScore: Float = 0f
        var actualProjectsScore: Float = 0f

        var totalGoalsEnabledCount = 0
        var totalAssignmentsEnabledCount = 0
        var totalProjectsEnabledCount = 0
        for(i in 0 until userDeets.size) { //for(i in 0..userDeets.size()) ->>try this
            for (element in SelfEvaluation.cycles) {
                if (element.isGoalsEnabled) {
                    totalGoalsEnabledCount = (userDeets[i].goals.goalName.size)- goalsNACount
                    actualGoalsScore = goalsTotalAverage / totalGoalsEnabledCount
                }

                if (element.isAssignmentsEnabled) {
                    totalAssignmentsEnabledCount = (userDeets[i].assignments.assignmentName.size)- assignmentsNACount
                    actualAssignmentsScore = assignmentsTotalAverage / totalAssignmentsEnabledCount
                }

                if (element.isProjectsEnabled) {
                    totalProjectsEnabledCount = (userDeets[i].projects.projectName.size)- projectsNACount
                    actualProjectsScore = projectsTotalAverage / totalProjectsEnabledCount
                }
            }
        }
        calculateFinalSelfRatingScore(actualGoalsScore, actualAssignmentsScore, actualProjectsScore)
    }

    private fun calculateFinalSelfRatingScore(actualGoalsScore: Float, actualAssignmentsScore: Float, actualProjectsScore: Float) {
        finalPeerRatingScore = actualGoalsScore + actualAssignmentsScore + actualProjectsScore
        println("\n\nFINAL RATING SCORE: %.1f".format(finalPeerRatingScore))
        set.setFinalStatus(finalPeerRatingScore)
        finalPeerRatingScore = 0f
    }
}
