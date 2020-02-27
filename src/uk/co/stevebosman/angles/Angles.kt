package uk.co.stevebosman.angles.uk.co.stevebosman.angles

import uk.co.stevebosman.angles.Angle
import kotlin.math.PI


fun radToDeg(angleRad: Double): Double {
    return (180.0 * angleRad / PI)
}

fun degToRad(angleDeg: Double): Double {
    return (PI * angleDeg / 180.0)
}

// Main functions

fun cos(a: Angle): Double {
    return kotlin.math.cos(a.radians)
}

fun sin(a: Angle): Double {
    return kotlin.math.sin(a.radians)
}

fun tan(a: Angle): Double {
    return kotlin.math.tan(a.radians)
}

// Reciprocal functions

fun sec(a: Angle): Double {
    return 1 / kotlin.math.cos(a.radians)
}

fun csc(a: Angle): Double {
    return 1 / kotlin.math.sin(a.radians)
}

fun cot(a: Angle): Double {
    return 1 / kotlin.math.tan(a.radians)
}


// ARC- functions

fun atan(v: Double): Angle {
    return Angle.fromRadians(kotlin.math.atan(v))
}

fun asin(v: Double): Angle {
    if (v > 1 || v < -1) {
        throw IllegalArgumentException("v should be between -1 and 1 (inclusive)")
    }
    return Angle.fromRadians(kotlin.math.asin(v))
}

fun acos(v: Double): Angle {
    if (v > 1 || v < -1) {
        throw IllegalArgumentException("v should be between -1 and 1 (inclusive)")
    }
    return Angle.fromRadians(kotlin.math.acos(v))
}

fun asec(v: Double): Angle {
    if (v > -1 && v < 1) {
        throw IllegalArgumentException("abs(v) should be greater than 1")
    }
    return Angle.fromRadians(kotlin.math.acos(1 / v))
}

fun acsc(v: Double): Angle {
    if (v > -1 && v < 1) {
        throw IllegalArgumentException("abs(v) should be greater than 1")
    }
    return Angle.fromRadians(kotlin.math.asin(1 / v))
}

fun acot(v: Double): Angle {
    return Angle.fromRadians(kotlin.math.atan(1 / v))
}

operator fun Double.times(v: Angle): Angle {
    return v * this
}


