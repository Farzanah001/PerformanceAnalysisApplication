package com.application.performanceanalysis

class SelfEvaluation {
    var cycles=ArrayList<CycleData>()
    fun viewCycles() {
        for(i in cycles){
            println("\nCycle Name:"+i.cycleName+"\nCycle Starting Date:"+i.cycleStartingFrom+"\nCycle Ending Date:"+i.cycleEndingOn)
        }
    }

    fun addCycles(){
        cycles.add(CycleData("Cycle 1","01-01-2016","31-12-2016",true,true,true))
        cycles.add(CycleData("Cycle 2","01-01-2017","31-12-2017",true,false,false))
        cycles.add(CycleData("Cycle 3","01-01-2018","31-12-2018",true,true,false))
        cycles.add(CycleData("Cycle 4","01-01-2019","31-12-2019",true,false,false))
        cycles.add(CycleData("Cycle 5","01-01-2020","31-12-2020",false,false,false))
        cycles.add(CycleData("Cycle 6","01-01-2021","31-12-2021",false,false,true))
    }


}