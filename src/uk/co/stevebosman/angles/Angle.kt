package uk.co.stevebosman.angles

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

    companion object {
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

