package uk.co.stevebosman.angles

import kotlin.math.PI


/**
 * Convert a [value] in radians to the equivalent value in degrees.
 */
fun radiansToDegrees(value: Number): Double {
    return (180.0 * value.toDouble() / PI)
}

/**
 * Convert a [value] in degrees to the equivalent value in radians.
 */
fun degreesToRadians(value: Number): Double {
    return (PI * value.toDouble() / 180.0)
}

/**
 * Multiply a scalar constant **s** by an Angle [a].
 * @return s * a
 */
operator fun Number.times(a: Angle): Angle {
    return a * this
}


