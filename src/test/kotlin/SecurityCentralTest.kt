import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.mockk.verifyAll
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class SecurityCentralTest {
    @RelaxedMockK
    private lateinit var windowMock: Window

    @RelaxedMockK
    private lateinit var doorMock: Door

    @Test
    fun `securityOn method closes all the doors and windows`() {
        val securityCentral = SecurityCentral(windowMock, doorMock)
        securityCentral.securityOn()

        verify(exactly = 1) { windowMock.close() }
        verify(atLeast = 1) { doorMock.close() }

        verifyAll {
            doorMock.close()
            windowMock.close()
        }

        verifySequence {
            windowMock.close()
            doorMock.close()
        }
    }
}