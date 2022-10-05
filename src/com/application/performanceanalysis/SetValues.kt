package com.application.performanceanalysis

class SetValues {
    fun setFinalStatus(finalScore: Float) {
        var status = ""
        if (finalScore > 4.1)
            status = "OUT STANDING"
        else if (finalScore in 3.1..4.0)
            status = "EXCELLENT"
        else if (finalScore in 2.1..3.0)
            status = "SATISFACTORY"
        else if (finalScore in 1.1..2.0)
            status = "NEEDS IMPROVEMENT"
        else if (finalScore <= 1.0)
            status = "UNSATISFACTORY"
        else
            status = "UNDEFINED"

        println("FINAL RATING: $status")
    }
}