package uk.co.stevebosman.angles

import kotlin.math.PI
import kotlin.math.abs

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
     * Add an angle [a] to this angle **a₀**.
     * @return a₀ + a
     */
    operator fun plus(a: Angle): Angle {
        return if (accuracy == Accuracy.RADIANS || accuracy != a.accuracy) {
            fromRadians(radians + a.radians, if (accuracy == a.accuracy) {
                accuracy
            } else {
                Accuracy.MIXED
            })
        } else {
            fromDegrees(degrees + a.degrees)
        }
    }

    /**
     * Subtract an angle [a] from this angle **a₀**.
     * @return a₀ - a
     */
    operator fun minus(a: Angle): Angle {
        return if (accuracy == Accuracy.RADIANS || accuracy != a.accuracy) {
            fromRadians(radians - a.radians, if (accuracy == a.accuracy) {
                accuracy
            } else {
                Accuracy.MIXED
            })
        } else {
            fromDegrees(degrees - a.degrees)
        }
    }

    /**
     * Multiply this angle **a** by a scalar constant [s].
     * @return a * s
     */
    operator fun times(s: Number): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(degrees * s.toDouble())
        } else {
            fromRadians(radians * s.toDouble(), accuracy)
        }
    }

    /**
     * Divide this angle **a** by a scalar constant [s].
     * @return a / s
     */
    operator fun div(s: Number): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(degrees / s.toDouble())
        } else {
            fromRadians(radians / s.toDouble(), accuracy)
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
     * @sample uk.co.stevebosman.angles.simplifyExample1
     * @sample uk.co.stevebosman.angles.simplifyExample2
     * @sample uk.co.stevebosman.angles.simplifyExample3
     */
    fun simplify(start:Number = 0, startInclusive:Boolean = true): Angle {
        return if (accuracy == Accuracy.DEGREES) {
            fromDegrees(simplify(degrees, ONE_TURN_DEGREES, start.toDouble(), startInclusive))
        } else {
            fromRadians(simplify(radians, ONE_TURN_RADIANS, start.toDouble(), startInclusive), accuracy)
        }
    }

    /**
     * Decide if this angle is equivalent to another angle [a2]
     * subject to a given maximum absolute difference [maxAbsoluteDifference].
     *
     * For example, 180° is equivalent to π radians
     */
    fun isEquivalentTo(a2: Angle, maxAbsoluteDifference: Double = 0.0): Boolean {
        val absoluteDifference:Double
        if (this.accuracy == Accuracy.DEGREES && a2.accuracy == Accuracy.DEGREES) {
            absoluteDifference = abs(a2.degrees - degrees)
        } else {
            absoluteDifference = abs(a2.radians - radians)
        }
        return absoluteDifference <= abs(maxAbsoluteDifference)
    }


    /**
     * Decide if the simplified equivalent of this angle
     * is equivalent to the simplified equivalent of another angle [a2]
     * subject to a given maximum absolute difference [maxAbsoluteDifference].
     *
     * For example, 180° is equivalent to 3π radians
     */
    fun whenSimplifiedIsEquivalentTo(a2: Angle, maxAbsoluteDifference: Double = 0.0): Boolean {
        return this.simplify().isEquivalentTo(a2.simplify(), maxAbsoluteDifference)
    }

    companion object {
        const val ONE_TURN_RADIANS = 2 * PI
        const val ONE_TURN_DEGREES = 360

        private fun simplify(
            v: Double,
            oneTurn: Number,
            min: Number = 0,
            minInclusive: Boolean = true
        ): Double {
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
            return Angle(accuracy, r.toDouble(), radiansToDegrees(r.toDouble()))
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
            return Angle(Accuracy.DEGREES, degreesToRadians(degrees), degrees)
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

