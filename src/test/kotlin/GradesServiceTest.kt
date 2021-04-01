import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GradesServiceTest {
    private lateinit var student: Student

    @MockK
    private lateinit var gradebookStub: Gradebook

    @BeforeEach
    fun setUp() {
        student = Student("Max")
        every { gradebookStub.gradesFor(student) } returns listOf(8, 6, 10)
    }

    @Test
    fun `calculate grades average for student`() {
        val averageGrade: Double? = GradesService(gradebookStub).averageGrades(student)
        assertEquals(averageGrade, 8.0)
    }
}