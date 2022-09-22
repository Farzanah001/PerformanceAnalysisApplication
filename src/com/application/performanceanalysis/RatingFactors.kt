package com.application.performanceanalysis

class RatingFactors {
    var userGoalsMap=HashMap<Int,ArrayList<String>>()
    var userAssignmentsMap=HashMap<Int,ArrayList<String>>()
    var userProjectsMap=HashMap<Int,ArrayList<String>>()

    fun addGoalsData(){
        var akonGoalsList=ArrayList<String>()
        akonGoalsList.add("1.Learn Kotlin")
        akonGoalsList.add("2.Learn Espresso")
        akonGoalsList.add("3.Learn Kaspresso")
        akonGoalsList.add("4.Complete the Application")

        userGoalsMap.put(101,akonGoalsList) //Goals List of Akon

        var bkonGoalsList=ArrayList<String>()
        bkonGoalsList.add("1.Learn Swift")
        bkonGoalsList.add("2.Learn XCTest")
        bkonGoalsList.add("3.Complete the Application")

        userGoalsMap.put(102,bkonGoalsList)

        var ckonGoalsList=ArrayList<String>()
        ckonGoalsList.add("1.Complete the Application")
        ckonGoalsList.add("2.Learn to Write Test Cases")

        userGoalsMap.put(103,ckonGoalsList)

        var dkonGoalsList=ArrayList<String>()
        dkonGoalsList.add("1.Learn Kotlin")
        dkonGoalsList.add("2.Learn Kaspresso")
        dkonGoalsList.add("3.Complete the Application")

        userGoalsMap.put(104,dkonGoalsList)

        var ekonGoalsList=ArrayList<String>()
        ekonGoalsList.add("1.Learn Swift")

        userGoalsMap.put(105,ekonGoalsList)

    }

    fun addAssignmentData(){
        var akonAssignmentsList=ArrayList<String>()

        akonAssignmentsList.add("1.Logical Programs Exercise")
        akonAssignmentsList.add("2.Test Case Writing")
        akonAssignmentsList.add("3.Use Case Writing")
        akonAssignmentsList.add("4.Performance Analysis Module Replication")

        userAssignmentsMap.put(101,akonAssignmentsList)

        var bkonAssignmentsList=ArrayList<String>()

        bkonAssignmentsList.add("1.Test Cases Writing")
        bkonAssignmentsList.add("2.Use Cases Writing")

        userAssignmentsMap.put(102,bkonAssignmentsList)

        var ckonAssignmentsList=ArrayList<String>()

        ckonAssignmentsList.add("1.Logical Programs Exercise")
        ckonAssignmentsList.add("2.Performance Analysis Module Replication")

        userAssignmentsMap.put(103,ckonAssignmentsList)

        var dkonAssignmentsList=ArrayList<String>()

        dkonAssignmentsList.add("1.Test Cases Writing")

        userAssignmentsMap.put(104,dkonAssignmentsList)

        var ekonAssignmentsList=ArrayList<String>()

        ekonAssignmentsList.add("1.Use Case Writing")
        ekonAssignmentsList.add("2.Logical Programs Exercise")
        ekonAssignmentsList.add("3.Performance Analysis Module Replication")

        userAssignmentsMap.put(105,ekonAssignmentsList)
    }

    fun addProjectsData(){
        var akonProjectsList=ArrayList<String>()

        akonProjectsList.add("1.Performance Analysis Module")
        akonProjectsList.add("2.Leave Tracker Module")
        akonProjectsList.add("3.Attendance Module")

        userProjectsMap.put(101,akonProjectsList)

        var bkonProjectsList=ArrayList<String>()

        bkonProjectsList.add("1.Leave Tracker Module")
        bkonProjectsList.add("2.Attendance Module")

        userProjectsMap.put(102,bkonProjectsList)

        var ckonProjectsList=ArrayList<String>()

        ckonProjectsList.add("1.Performance Analysis Module")

        userProjectsMap.put(103,ckonProjectsList)

        var dkonProjectsList=ArrayList<String>()

        dkonProjectsList.add("1.Performance Analysis Module")
        dkonProjectsList.add("2.Attendance Module")
        dkonProjectsList.add("3.Time Tracker Module")

        userProjectsMap.put(104,dkonProjectsList)

        var ekonProjectsList=ArrayList<String>()

        ekonProjectsList.add("1.Attendance Module")
        ekonProjectsList.add("2.Time Tracker Module")
        ekonProjectsList.add("3.Performance Analysis Module")
        ekonProjectsList.add("4.Leave Tracker Module")

        userProjectsMap.put(105,ekonProjectsList)
    }


}
