package nice.fontaine.geotools

import java.lang.Math.toDegrees
import kotlin.math.*

const val EARTH_RADIUS = 6_371_008.8

/**
 * Computes havDistance between two Coordinates in meters using haversine.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 */
fun meters(from: Coordinate, to: Coordinate) = EARTH_RADIUS * angle(from, to)

/**
 * Computes angle between two Coordinates in radians.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  angle in radians
 */
fun angle(from: Coordinate, to: Coordinate) = 2 * asin(sqrt(havDistance(from, to)))

/**
 * Computes haversine havDistance between two Coordinates.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  haversine havDistance
 */
fun havDistance(from: Coordinate, to: Coordinate) =
        hav(deltaLat(from, to)) + cos(from.latRad()) * cos(to.latRad()) * hav(deltaLon(from, to))

/**
 * Computes the haversine of a given value.
 *
 * @param   x Double
 * @return  haversine of x
 */
fun hav(x: Double): Double {
    val half = sin(x * 0.5)
    return half * half
}

/**
 * Computes havDistance between coordinates using Pythagoras’ theorem on an equi­rectangular projection.
 * Should be used if performance is an issue and accuracy less important, for small distances.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 */
fun metersApprox(from: Coordinate, to: Coordinate): Double {
    val x = deltaLon(from, to) * cos((from.latRad() + to.latRad()) / 2)
    val y = deltaLat(from, to)
    return EARTH_RADIUS * pythagoras(x, y)
}

/**
 * Computes havDistance between x and y value in cartesian coordinate system (Pythagoras´ Theorem).
 *
 * @param   x Double
 * @param   y Double
 * @return  havDistance between coordinates
 */
fun pythagoras(x: Double, y: Double) = sqrt(x * x + y * y)

/**
 * Initial bearing (also: forward azimuth) which if followed in a straight line
 * along a great-circle arc (also: orthodrome) will take you from the start point to the end point.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  bearing in degrees at start point
 */
fun bearing(from: Coordinate, to: Coordinate): Double {
    val delta = deltaLon(from, to)
    val y = sin(delta) * cos(to.latRad())
    val x = cos(from.latRad()) * sin(to.latRad()) - sin(from.latRad()) * cos(to.latRad()) * cos(delta)
    return toDegrees(atan2(y, x))
}

private fun deltaLon(from: Coordinate, to: Coordinate) = to.lonRad() - from.lonRad()

private fun deltaLat(from: Coordinate, to: Coordinate) = to.latRad() - from.latRad()
