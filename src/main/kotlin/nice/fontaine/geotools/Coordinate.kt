package nice.fontaine.geotools

data class Coordinate(val lat: Double, val lon: Double) {

    fun latRad(): Double = Math.toRadians(lat)

    fun lonRad(): Double = Math.toRadians(lon)

    fun metersTo(other: Coordinate): Double = meters(this, other)
}