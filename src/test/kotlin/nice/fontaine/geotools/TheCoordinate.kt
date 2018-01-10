package nice.fontaine.geotools

import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class TheCoordinate {

    private var lat:Double = 0.0
    private var lon:Double = 0.0

    @Before fun setup() {
        lat = 52.0
        lon = 13.0
    }

    @Test fun `should contain given coordinates`() {
        val coordinate = Coordinate(lat, lon)

        assertThat(coordinate.lat).isEqualTo(lat)
        assertThat(coordinate.lon).isEqualTo(lon)
    }

    @Test fun `should compute distance in meters between two coordinates`() {
        val expected = 404_279.7224028446
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = from metersTo to

        assertThat(actual).isEqualTo(expected)
    }

    @Test fun `should compute approximation of distance in meters between two coordinates`() {
        val from = Coordinate(1.0, 0.0)
        val to = Coordinate(2.0, 0.0)

        val actual = from approxTo to

        assertThat(actual).isEqualTo(111195.0802335329)
    }

    @Test fun `should compute bearing of initial coordinate to next`() {
        val expected = 156.16658258153177
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = from bearingTo to

        assertThat(actual).isEqualTo(expected)
    }

    @Test fun `should compute midpoint between coordinates`() {
        val expected = Coordinate(50.53632687827434, 1.2746141006782636)
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = from midpointTo to

        assertThat(actual).isEqualTo(expected)
    }

    @Test fun `should be equal when latitude and longitude are the same`() {
        val from = Coordinate(lat, lon)
        val to = Coordinate(lat, lon)

        assertThat(from == to).isTrue()
    }

    @Test fun `should throw exception when latitude is lower -90 degrees`() {
        assertThatThrownBy { Coordinate(-90.1, 0.0) }
                .hasMessageContaining("Latitude")
                .hasMessageContaining("is invalid!")
    }

    @Test fun `should throw exception when latitude is higher 90 degrees`() {
        assertThatThrownBy { Coordinate(90.1, 0.0) }
                .hasMessageContaining("Latitude")
                .hasMessageContaining("is invalid!")
    }

    @Test fun `should throw exception when longitude is lower -180 degrees`() {
        assertThatThrownBy { Coordinate(0.0, -180.1) }
                .hasMessageContaining("Longitude")
                .hasMessageContaining("is invalid!")
    }

    @Test fun `should throw exception when longitude is higher 180 degrees`() {
        assertThatThrownBy { Coordinate(0.0, 180.1) }
                .hasMessageContaining("Longitude")
                .hasMessageContaining("is invalid!")
    }
}