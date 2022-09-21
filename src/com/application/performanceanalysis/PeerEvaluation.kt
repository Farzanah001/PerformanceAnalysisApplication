package com.application.performanceanalysis

import java.text.SimpleDateFormat

class PeerEvaluation {
    var userDeets=ArrayList<UserData>()
    //var selfEvaluation=SelfEvaluation()


    fun addUsers() {
        userDeets.add(UserData(101,"Akon","20-01-2016"))
        userDeets.add(UserData(102,"Bkon","06-06-2019"))
        userDeets.add(UserData(103,"Ckon","20-07-2017"))
        userDeets.add(UserData(104,"Dkon","01-09-2020"))
        userDeets.add(UserData(105,"Ekon","09-08-2018"))
    }


    fun viewUsers() {

        var changeDateFormat=SimpleDateFormat("dd-MM-yyyy")
        userloop@for(i in userDeets){
            var userDOJ = changeDateFormat.parse(i.dateOfJoining)
            cycleloop@for(j in SelfEvaluation.cycles) {

                //println(userDOJ)
                var cycleStartingDate = changeDateFormat.parse(j.cycleStartingFrom)
                //println(cycleStartingDate)
                //var cycleEndingDate=changeDateFormat.parse(j.cycleEndingOn)
                val dateDiff=userDOJ.compareTo(cycleStartingDate)
               // println(dateDiff)
                if(dateDiff<0){
                    println("${i.userId}\t${i.name}")
                    continue@userloop
                }
                else{
                    continue@cycleloop
                }
            }
        }

    }

}