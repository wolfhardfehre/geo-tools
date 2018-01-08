package nice.fontaine.geotools

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class TheFunctions {

    private lateinit var from: Coordinate
    private lateinit var to: Coordinate

    @Before fun setup() {
        from = Coordinate(1.0, 0.0)
        to = Coordinate(2.0, 0.0)
    }

    @Test fun `should compute distance in meters using haversine formula`() {
        val actual = meters(from, to)

        assertThat(actual).isEqualTo(111_195.0802335329)
    }

    @Test fun `should compute distance in meters using equirectangular approximation`() {
        val actual = metersApprox(from, to)

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
}