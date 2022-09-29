package com.application.performanceanalysis

object RatingFactors {

    //GOALS
    var akonGoalsList=ArrayList<String>()
    var akonGoalWeightageList=ArrayList<Int>()

    var bkonGoalsList=ArrayList<String>()
    var bkonGoalWeightageList=ArrayList<Int>()

    var ckonGoalsList=ArrayList<String>()
    var ckonGoalWeightageList=ArrayList<Int>()

    var dkonGoalsList=ArrayList<String>()
    var dkonGoalWeightageList=ArrayList<Int>()

    var ekonGoalsList=ArrayList<String>()
    var ekonGoalWeightageList=ArrayList<Int>()

    fun addGoalsData(){

        akonGoalsList.add("1.Learn Kotlin")
        akonGoalsList.add("2.Learn Espresso")
        akonGoalsList.add("3.Learn Kaspresso")
        akonGoalsList.add("4.Complete the Application")

        akonGoalWeightageList.add(70)
        akonGoalWeightageList.add(80)
        akonGoalWeightageList.add(70)
        akonGoalWeightageList.add(50)

        bkonGoalsList.add("1.Learn Swift")
        bkonGoalsList.add("2.Learn XCTest")
        bkonGoalsList.add("3.Complete the Application")

        bkonGoalWeightageList.add(40)
        bkonGoalWeightageList.add(90)
        bkonGoalWeightageList.add(60)

        ckonGoalsList.add("1.Complete the Application")
        ckonGoalsList.add("2.Learn to Write Test Cases")

        ckonGoalWeightageList.add(90)
        ckonGoalWeightageList.add(75)

        dkonGoalsList.add("1.Learn Kotlin")
        dkonGoalsList.add("2.Learn Kaspresso")
        dkonGoalsList.add("3.Complete the Application")

        dkonGoalWeightageList.add(80)
        dkonGoalWeightageList.add(90)
        dkonGoalWeightageList.add(50)

        ekonGoalsList.add("1.Learn Swift")

        ekonGoalWeightageList.add(80)

    }

    var akonAssignmentsList=ArrayList<String>()
    var akonAssignmentWeightageList=ArrayList<Int>()

    var bkonAssignmentsList=ArrayList<String>()
    var bkonAssignmentWeightageList=ArrayList<Int>()

    var ckonAssignmentsList=ArrayList<String>()
    var ckonAssignmentWeightageList=ArrayList<Int>()

    var dkonAssignmentsList=ArrayList<String>()
    var dkonAssignmentWeightageList=ArrayList<Int>()

    var ekonAssignmentsList=ArrayList<String>()
    var ekonAssignmentWeightageList=ArrayList<Int>()

    fun addAssignmentData(){

        akonAssignmentsList.add("1.Logical Programs Exercise")
        akonAssignmentsList.add("2.Test Case Writing")
        akonAssignmentsList.add("3.Use Case Writing")
        akonAssignmentsList.add("4.Performance Analysis Module Replication")

        akonAssignmentWeightageList.add(90)
        akonAssignmentWeightageList.add(70)
        akonAssignmentWeightageList.add(80)
        akonAssignmentWeightageList.add(60)


        bkonAssignmentsList.add("1.Test Cases Writing")
        bkonAssignmentsList.add("2.Use Cases Writing")

        bkonAssignmentWeightageList.add(90)
        bkonAssignmentWeightageList.add(70)

        ckonAssignmentsList.add("1.Logical Programs Exercise")
        ckonAssignmentsList.add("2.Performance Analysis Module Replication")

        ckonAssignmentWeightageList.add(90)
        ckonAssignmentWeightageList.add(70)

        dkonAssignmentsList.add("1.Test Cases Writing")
        dkonAssignmentWeightageList.add(90)


        ekonAssignmentsList.add("1.Use Case Writing")
        ekonAssignmentsList.add("2.Logical Programs Exercise")
        ekonAssignmentsList.add("3.Performance Analysis Module Replication")

        ekonAssignmentWeightageList.add(90)
        ekonAssignmentWeightageList.add(70)
        ekonAssignmentWeightageList.add(90)


    }

    var akonProjectList=ArrayList<String>()
    var akonProjectWeightageList=ArrayList<Int>()

    var bkonProjectList=ArrayList<String>()
    var bkonProjectWeightageList=ArrayList<Int>()

    var ckonProjectList=ArrayList<String>()
    var ckonProjectWeightageList=ArrayList<Int>()

    var dkonProjectList=ArrayList<String>()
    var dkonProjectWeightageList=ArrayList<Int>()

    var ekonProjectList=ArrayList<String>()
    var ekonProjectWeightageList=ArrayList<Int>()

    fun addProjectsData(){

        akonProjectList.add("1.Performance Analysis Module")
        akonProjectList.add("2.Leave Tracker Module")
        akonProjectList.add("3.Attendance Module")

        akonProjectWeightageList.add(80)
        akonProjectWeightageList.add(90)
        akonProjectWeightageList.add(70)


        bkonProjectList.add("1.Leave Tracker Module")
        bkonProjectList.add("2.Attendance Module")

        bkonProjectWeightageList.add(60)
        bkonProjectWeightageList.add(30)


        ckonProjectList.add("1.Performance Analysis Module")

        ckonProjectWeightageList.add(80)


        dkonProjectList.add("1.Performance Analysis Module")
        dkonProjectList.add("2.Attendance Module")
        dkonProjectList.add("3.Time Tracker Module")

        dkonProjectWeightageList.add(80)
        dkonProjectWeightageList.add(80)
        dkonProjectWeightageList.add(80)


        ekonProjectList.add("1.Attendance Module")
        ekonProjectList.add("2.Time Tracker Module")
        ekonProjectList.add("3.Performance Analysis Module")
        ekonProjectList.add("4.Leave Tracker Module")

        ekonProjectWeightageList.add(70)
        akonProjectWeightageList.add(80)
        akonProjectWeightageList.add(60)
        akonProjectWeightageList.add(50)

    }
}
