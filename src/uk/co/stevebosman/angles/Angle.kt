package uk.co.stevebosman.angles

import kotlin.math.PI

/**
 * Representation of an angle, more specifically a rotation.
 *
 * Unlike the standard Kotlin trigonometric functions,
 * there is no need to convert between radians and degrees
 * as this is is done automatically. This makes the maths slower, but safer.
 */
class Angle private constructor(val accuracy: Accuracy, val radians: Double, val degrees: Double) {
    /**
     * Obtain the negative of the current angle.
     * @return -this
     */
    operator fun unaryMinus(): Angle {
        return Angle(accuracy, -radians, -degrees)
    }

    /**
     * Add this angle to another angle.
     * @param v angle to add to this angle
     * @return this + v
     */
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

    /**
     * Subtract one angle from this angle.
     * @param v angle to subtract from this angle
     * @return this - v
     */
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

    /**
     * Divide this angle (**a**) by a scalar constant, **a** * [d].
     * @param d scalar to multiply this angle by
     * @return this * d
     */
    operator fun times(d: Number): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(degrees * d.toDouble())
        } else {
            fromRadians(radians * d.toDouble(), accuracy)
        }
    }

    /**
     * Divide this angle (**a**) by a scalar constant, **a** / [d].
     * @param d scalar to divide this angle by
     * @return this / d
     */
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

    /**
     * Returns equivalent angle in the defined range,
     * by default the equivalent angle is in the range [0, 2π) radians.
     *
     * If [start] is supplied with [startInclusive] true it simplifies in the range [start, start + 2π).
     *
     * If [startInclusive] is false then the range is (start, start + 2π].
     * @sample uk.co.stevebosman.angles.AngleTest.simplifyDegrees
     * @sample uk.co.stevebosman.angles.AngleTest.simplifyRadiansSplit2
     */
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

        /**
         * Create an angle with [r] radians,
         * marking it with the given [accuracy] ([Accuracy.RADIANS] or [Accuracy.MIXED]).
         */
        private fun fromRadians(r: Number, accuracy: Accuracy): Angle {
            if (accuracy!=Accuracy.RADIANS && accuracy!=Accuracy.MIXED)
                throw IllegalArgumentException("")
            return Angle(accuracy, r.toDouble(), radToDeg(r.toDouble()))
        }

        /**
         * Create an angle with [r] radians,
         * marking it with an [accuracy] of [Accuracy.RADIANS].
         */
        fun fromRadians(r: Number): Angle {
            return fromRadians(r, Accuracy.RADIANS)
        }

        /**
         * Create an angle with [d] degrees, [m] minutes and [s] seconds,
         * i.e [d]° [m]' [s]",
         * marking it with an [accuracy] of [Accuracy.DEGREES].
         */
        fun fromDegrees(d: Number, m: Number = 0.0, s: Number = 0.0): Angle {
            val degrees = d.toDouble() + (m.toDouble() + s.toDouble() / 60) / 60
            return Angle(Accuracy.DEGREES, degToRad(degrees), degrees)
        }
    }

    enum class Accuracy {
        /**
         * Marks an angle as created using degrees.
         */
        DEGREES,
        /**
         * Marks an angle as created using radians.
         */
        RADIANS,
        /**
         * Marks an angle as created using a mix of degrees or radians,
         * further arithmetic will use the radians value.
         */
        MIXED
    }
}

