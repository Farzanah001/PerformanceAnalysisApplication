package com.application.performanceanalysis

import java.text.SimpleDateFormat

class PeerEvaluation {
    var userDeets=ArrayList<UserData>()
    var selfEvaluation=SelfEvaluation()

    fun addUsers() {
        userDeets.add(UserData(101,"Akon","20-01-2016"))
        userDeets.add(UserData(102,"Bkon","06-06-2019"))
        userDeets.add(UserData(103,"Ckon","20-07-2017"))
        userDeets.add(UserData(104,"Dkon","01-09-2020"))
        userDeets.add(UserData(105,"Ekon","09-08-2018"))
        println("Added")
    }

    fun viewUsers() {
        println("Called")
        var changeDateFormat=SimpleDateFormat("dd-MM-yyyy")
        for(i in selfEvaluation.cycles){
            println("hi")
            println(i)
        }
        for(i in userDeets){
            for(j in selfEvaluation.cycles) {
                var userDOJ = changeDateFormat.parse(i.dateOfJoining)
                var cycleStartingDate = changeDateFormat.parse(j.cycleStartingFrom)
                //var cycleEndingDate=changeDateFormat.parse(j.cycleEndingOn)
                val dateDiff=userDOJ.compareTo(cycleStartingDate)
                if(dateDiff>0){
                    println("${i.userId}\t${i.name}")
                }
            }
        }

    }

}