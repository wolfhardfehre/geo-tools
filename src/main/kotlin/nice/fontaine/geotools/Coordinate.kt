package nice.fontaine.geotools

data class Coordinate(val lat: Double, val lon: Double) {

    init { validate() }

    infix fun metersTo(other: Coordinate): Double = meters(this, other)

    infix fun approxTo(other: Coordinate): Double = approx(this, other)

    infix fun bearingTo(other: Coordinate): Double = bearing(this, other)

    infix fun midpointTo(other: Coordinate): Coordinate = midpoint(this, other)

    private fun validate() {
        if (lat <= -90.0) throw IllegalArgumentException("Latitude $lat is invalid!")
        if (lat >= 90.0) throw IllegalArgumentException("Latitude $lat is invalid!")
        if (lon <= -180.0) throw IllegalArgumentException("Longitude $lon is invalid!")
        if (lon >= 180.0) throw IllegalArgumentException("Longitude $lon is invalid!")
    }
}