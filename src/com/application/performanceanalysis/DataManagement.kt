package com.application.performanceanalysis
//
//import java.text.SimpleDateFormat
//
//class DataManagement {
//    var userDeets=ArrayList<UserData>()
//    //var selfEvaluation=SelfEvaluation()
//
//    fun addUsers() {
//        userDeets.add(UserData(101,"Akon","20-01-2016"))
//        userDeets.add(UserData(102,"Bkon","06-06-2019"))
//        userDeets.add(UserData(103,"Ckon","20-07-2017"))
//        userDeets.add(UserData(104,"Dkon","01-09-2020"))
//        userDeets.add(UserData(105,"Ekon","09-08-2018"))
//        //println("Added")
//    }
//
//    fun viewUsers() {
//        println("Called")
//        println(SelfEvaluation.cycles.size)
//        var changeDateFormat= SimpleDateFormat("dd-MM-yyyy")
//        for(i in SelfEvaluation.cycles){
//            println("hi")
//            println(i)
//        }
//        for(i in userDeets){
//            for(j in SelfEvaluation.cycles) {
//                var userDOJ = changeDateFormat.parse(i.dateOfJoining)
//                var cycleStartingDate = changeDateFormat.parse(j.cycleStartingFrom)
//                //var cycleEndingDate=changeDateFormat.parse(j.cycleEndingOn)
//                val dateDiff=userDOJ.compareTo(cycleStartingDate)
//                if(dateDiff<0){
//                    println("${i.userId}\t${i.name}")
//                }
//            }
//        }
//    }
//
//    var cycles=ArrayList<CycleData>()
//    fun viewCycles() {
//        for(i in cycles){
//            println("\nCycle Name:"+i.cycleName+"\nCycle Starting Date:"+i.cycleStartingFrom+"\nCycle Ending Date:"+i.cycleEndingOn)
//        }
//    }
//
//    fun addCycles(){
//        cycles.add(CycleData("Cycle 1","01-01-2016","31-12-2016",true,true,true))
//        cycles.add(CycleData("Cycle 2","01-01-2017","31-12-2017",true,false,false))
//        cycles.add(CycleData("Cycle 3","01-01-2018","31-12-2018",true,true,false))
//        cycles.add(CycleData("Cycle 4","01-01-2019","31-12-2019",true,false,false))
//        cycles.add(CycleData("Cycle 5","01-01-2020","31-12-2020",false,false,false))
//        cycles.add(CycleData("Cycle 6","01-01-2021","31-12-2021",false,false,true))
//    }
//}