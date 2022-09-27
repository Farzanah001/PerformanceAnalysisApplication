package com.application.performanceanalysis

class DataStorageClasses {
}
data class UserData(var userId:Int,var name:String,var dateOfJoining:String,var goals: Goals,var assignments: Assignments,var projects: Projects) //add a goals object,assignments object and projects object in this data class

data class Goals(var goalName:ArrayList<String>,var weightage:ArrayList<Int>,var rating:Int)
//don't add user id in goals
//just have only the goal properties, add them into an object and pass the object into the userData class as another property of the userdata class


data class Assignments(var assignmentName:ArrayList<String>,var weightage:ArrayList<Int>,var rating: Int)
data class Projects(var projectName:ArrayList<String>,var weightage: ArrayList<Int>,var rating: Int)

data class CycleData(var cycleName:String,var cycleStartingFrom:String,var cycleEndingOn:String,val isGoalsEnabled:Boolean,val isAssignmentsEnabled:Boolean,val isProjectsEnabled:Boolean,var isWeightageEnabled:Boolean)