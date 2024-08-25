package org.example

class Major(val name: String) {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgAverage = averages.average()
        return Triple(minAverage, maxAverage, avgAverage)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val grades = students.flatMap { student ->
            student.courses.filter { it.name == courseName }
        }

        if (grades.isEmpty()) return Triple(0.0, 0.0, 0.0)

        val minGrade = grades.minOf { it.grade }
        val maxGrade = grades.maxOf { it.grade }
        val weightedAverage = grades.sumOf { it.grade * it.credits } / grades.sumOf { it.credits }

        return Triple(minGrade, maxGrade, weightedAverage)
    }
}



