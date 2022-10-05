package com.application.performanceanalysis

class DataStorageClasses {
}

data class UserData(
    var userId: Int,
    var name: String,
    var dateOfJoining: String,
    var goals: Goals,
    var assignments: Assignments,
    var projects: Projects
)

data class Goals(var goalName: ArrayList<String>, var weightage: ArrayList<Int>, var rating: Int)

data class Assignments(var assignmentName: ArrayList<String>, var weightage: ArrayList<Int>, var rating: Int)
data class Projects(var projectName: ArrayList<String>, var weightage: ArrayList<Int>, var rating: Int)

data class CycleData(
    var cycleName: String,
    var cycleStartingFrom: String,
    var cycleEndingOn: String,
    val isGoalsEnabled: Boolean,
    val isAssignmentsEnabled: Boolean,
    val isProjectsEnabled: Boolean,
    var isWeightageEnabled: Boolean,
    var isNAEnabled: Boolean
)