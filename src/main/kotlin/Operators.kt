package uk.co.stevebosman.angles

/**
 * Multiply a scalar constant **s** by an Angle [a].
 * @return s * a
 */
operator fun Number.times(a: Angle): Angle {
    return a * this
}