package uk.co.stevebosman.angles

import uk.co.stevebosman.angles.uk.co.stevebosman.angles.degToRad
import uk.co.stevebosman.angles.uk.co.stevebosman.angles.radToDeg

class Angle private constructor(val radians:Double, val degrees:Double) {
    operator fun unaryMinus(): Angle {
        return Angle(-radians, -degrees)
    }
    operator fun plus(v: Angle): Angle {
        return Angle(radians + v.radians, degrees + v.degrees)
    }

    operator fun times(d: Double): Angle {
        return Angle(radians * d, degrees * d)
    }

    operator fun div(d: Double): Angle {
        return Angle(radians / d, degrees / d)
    }

    companion object {
        fun fromRadians(value: Double): Angle {
            return Angle(value, radToDeg(value))
        }
        fun fromDegrees(value: Double): Angle {
            return Angle(degToRad(value), value)
        }
        fun fromDegrees(value: Int, minutes: Double=0.0, seconds: Double=0.0): Angle {
            val degrees = value + (minutes + seconds/60)/60
            return Angle(degToRad(degrees), degrees)
        }
    }

}

