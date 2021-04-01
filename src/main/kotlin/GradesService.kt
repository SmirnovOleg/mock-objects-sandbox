data class Student(val name: String)

data class Gradebook(val grades: Map<String, List<Int>>) {
    fun gradesFor(student: Student): List<Int>? {
        return grades[student.name]
    }
}

class GradesService(private val gradebook: Gradebook) {
    fun averageGrades(student: Student): Double? {
        return gradebook.gradesFor(student)?.average()
    }
}