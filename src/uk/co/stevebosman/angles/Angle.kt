package uk.co.stevebosman.angles

import kotlin.math.PI

class Angle private constructor(val accuracy: Accuracy, val radians: Double, val degrees: Double) {
    operator fun unaryMinus(): Angle {
        return Angle(accuracy, -radians, -degrees)
    }

    operator fun plus(v: Angle): Angle {
        return if (accuracy == Accuracy.RADIANS || accuracy != v.accuracy) {
            fromRadians(radians + v.radians, if (accuracy == v.accuracy) {
                accuracy
            } else {
                Accuracy.MIXED
            })
        } else {
            fromDegrees(degrees + v.degrees)
        }
    }

    operator fun minus(v: Angle): Angle {
        return if (accuracy == Accuracy.RADIANS || accuracy != v.accuracy) {
            fromRadians(radians - v.radians, if (accuracy == v.accuracy) {
                accuracy
            } else {
                Accuracy.MIXED
            })
        } else {
            fromDegrees(degrees - v.degrees)
        }
    }

    operator fun times(d: Number): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(degrees * d.toDouble())
        } else {
            fromRadians(radians * d.toDouble(), accuracy)
        }
    }

    operator fun div(d: Number): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(degrees / d.toDouble())
        } else {
            fromRadians(radians / d.toDouble(), accuracy)
        }
    }

    override fun toString(): String {
        return if (accuracy == Accuracy.DEGREES) "$degrees°" else if (accuracy == Accuracy.MIXED) "~$degrees°/$radians rads" else "$radians rads"
    }

    fun simplify(start:Number = 0, startInclusive:Boolean = true): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(simplify(degrees, start.toDouble(), startInclusive, ONE_TURN_DEGREES))
        } else {
            fromRadians(simplify(radians, start.toDouble(), startInclusive, ONE_TURN_RADIANS), accuracy)
        }
    }

    companion object {
        const val ONE_TURN_RADIANS = 2 * PI
        const val ONE_TURN_DEGREES = 360

        private fun simplify(v: Double, min: Number, minInclusive:Boolean = true, oneTurn: Number): Double {
            val minD = min.toDouble()
            val oneTurnD = oneTurn.toDouble()
            val max = minD + oneTurnD
            var v1 = v
            if (minInclusive) {
                // simplify to the range [min, max)
                while (v1 >= max) {
                    v1 -= oneTurnD
                }
                while (v1 < minD) {
                    v1 += oneTurnD
                }
            } else {
                // simplify to the range (min, max]
                while (v1 > max) {
                    v1 -= oneTurnD
                }
                while (v1 <= minD) {
                    v1 += oneTurnD
                }
            }
            return v1
        }

        private fun fromRadians(value: Number, accuracy: Accuracy): Angle {
            return Angle(accuracy, value.toDouble(), radToDeg(value.toDouble()))
        }

        fun fromRadians(value: Number): Angle {
            return fromRadians(value, Accuracy.RADIANS)
        }

        fun fromDegrees(value: Number, minutes: Number = 0.0, seconds: Number = 0.0): Angle {
            val degrees = value.toDouble() + (minutes.toDouble() + seconds.toDouble() / 60) / 60
            return Angle(Accuracy.DEGREES, degToRad(degrees), degrees)
        }
    }

    enum class Accuracy {
        DEGREES, RADIANS, MIXED
    }
}

