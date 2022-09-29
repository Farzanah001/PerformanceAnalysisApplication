package com.application.performanceanalysis

class SetValues {
    fun setFinalStatus(finalScore:Float){
        var status=if(finalScore in 4.1..5.0)
            "OUT STANDING"
        else if(finalScore in 3.1..4.0)
            "EXCELLENT"
        else if(finalScore in 2.1..3.0)
            "SATISFACTORY"
        else if(finalScore in 1.1..2.0)
            "NEEDS IMPROVEMENT"
        else
            "UNSATISFACTORY"

        println("FINAL RATING: $status")
    }
}