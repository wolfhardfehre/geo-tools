package nice.fontaine.geotools

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.math.PI

class TheFunctions {

    private lateinit var from: Coordinate
    private lateinit var to: Coordinate

    @Before fun setup() {
        from = Coordinate(1.0, 0.0)
        to = Coordinate(2.0, 0.0)
    }

    @Test fun `converts radians to degrees`() {
        assertThat(PI.deg()).isEqualTo(180.0)
    }

    @Test fun `converts degrees to radians`() {
        assertThat(180.0.rad()).isEqualTo(PI)
    }

    @Test fun `should compute distance in meters using haversine formula simple`() {
        val actual = meters(from, to)

        assertThat(actual).isEqualTo(111_195.0802335329)
    }

    @Test fun `should compute distance in meters using haversine formula`() {
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = meters(from, to)

        assertThat(actual).isEqualTo(404_279.7224028446)
    }

    @Test fun `should compute angle in radians using haversine formula`() {
        val actual = angle(from, to)

        assertThat(actual.deg()).isEqualTo(1.0)
    }

    @Test fun `should compute distance in meters using equirectangular approximation`() {
        val actual = approx(from, to)

        assertThat(actual).isEqualTo(111_195.0802335329)
    }

    @Test fun `should compute pythagoras distance`() {
        val actual = pythagoras(3.0, 4.0)

        assertThat(actual).isEqualTo(5.0)
    }

    @Test fun `should compute haversine of x`() {
        val actual = hav(Math.PI)

        assertThat(actual).isEqualTo(1.0)
    }

    @Test fun `should compute initial bearing with simple values`() {
        var actual = bearing(from, to)

        assertThat(actual).isEqualTo(0.0)

        actual = bearing(to, from)

        assertThat(actual).isEqualTo(180.0)
    }

    @Test fun `should compute initial bearing`() {
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = bearing(from, to)

        assertThat(actual).isEqualTo(156.16658258153177)
    }

    @Test fun `should compute final bearing`() {
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = finalBearing(from, to)

        assertThat(actual).isEqualTo(157.89044019049254)
    }

    @Test fun `should compute midpoint between two coordinates simple`() {
        val actual = midpoint(from, to)

        assertThat(actual).isEqualTo(Coordinate(1.5, 0.0))
    }

    @Test fun `should compute midpoint between two coordinates`() {
        val expected = Coordinate(50.53632687827434, 1.2746141006782636)
        val from = Coordinate(52.205, 0.119)
        val to = Coordinate(48.857, 2.351)

        val actual = midpoint(from, to)

        assertThat(actual).isEqualTo(expected)
    }
}