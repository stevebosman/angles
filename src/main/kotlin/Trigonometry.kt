package uk.co.stevebosman.angles

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

fun atan(v: Number): Angle {
    return Angle.fromRadians(kotlin.math.atan(v.toDouble()))
}

fun asin(v: Number): Angle {
    if (v.toDouble() > 1 || v.toDouble() < -1) {
        throw IllegalArgumentException("v should be between -1 and 1 (inclusive)")
    }
    return Angle.fromRadians(kotlin.math.asin(v.toDouble()))
}

fun acos(v: Number): Angle {
    if (v.toDouble() > 1 || v.toDouble() < -1) {
        throw IllegalArgumentException("v should be between -1 and 1 (inclusive)")
    }
    return Angle.fromRadians(kotlin.math.acos(v.toDouble()))
}

fun asec(v: Number): Angle {
    if (v.toDouble() > -1 && v.toDouble() < 1) {
        throw IllegalArgumentException("abs(v) should be greater than 1")
    }
    return Angle.fromRadians(kotlin.math.acos(1 / v.toDouble()))
}

fun acsc(v: Number): Angle {
    if (v.toDouble() > -1 && v.toDouble() < 1) {
        throw IllegalArgumentException("abs(v) should be greater than 1")
    }
    return Angle.fromRadians(kotlin.math.asin(1 / v.toDouble()))
}

fun acot(v: Number): Angle {
    return Angle.fromRadians(kotlin.math.atan(1 / v.toDouble()))
}

