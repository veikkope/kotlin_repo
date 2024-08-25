package org.example

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalCredits = courses.sumOf { it.credits }
        val weightedSum = courses.sumOf { it.grade * it.credits }
        return weightedSum / totalCredits
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalCredits = filteredCourses.sumOf { it.credits }
        val weightedSum = filteredCourses.sumOf { it.grade * it.credits }
        return if (totalCredits > 0) weightedSum / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val minGrade = courses.minOf { it.grade }
        val maxGrade = courses.maxOf { it.grade }
        return Pair(minGrade, maxGrade)
    }
}
