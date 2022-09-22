package com.application.performanceanalysis

class DataStorageClasses {
}
data class UserData(var userId:Int,var name:String,var dateOfJoining:String)

data class Goals(var userId: Int,var goalName:ArrayList<String>,var weightage:ArrayList<Int>,var rating:Int)
//var userGoalsMap=ArrayList<Goals>()
//    fun add(){
//       userId=101
//       goalName.add("goal 1")
//       goalName.add("goal 2")
//       //goalName.add("goal 3")
//
//       weightage.add(34)
//       weightage.add(20)
//       rating.add(3)
//       rating.add(5)
//
//        userGoalsMap.add()
//   }

data class Assignments(var userId: Int,var assignmentName:String,var weightage: Int,var rating: Int)
data class Projects(var userId: Int,var projectName:String,var weightage: Int,var rating: Int)

data class CycleData(var cycleName:String,var cycleStartingFrom:String,var cycleEndingOn:String,val isGoalsEnabled:Boolean,val isAssignmentsEnabled:Boolean,val isProjectsEnabled:Boolean)