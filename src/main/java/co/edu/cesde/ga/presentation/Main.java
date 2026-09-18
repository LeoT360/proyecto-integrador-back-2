package co.edu.cesde.ga.presentation;


import co.edu.cesde.ga.application.repository.impl.CourseService;
import co.edu.cesde.ga.application.repository.impl.EnrollmentService;
import co.edu.cesde.ga.application.repository.impl.StudentService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static co.edu.cesde.ga.infrastructure.repository.StudentJpaRepository StudentJpaRepository;
    private static final StudentService studentService = new StudentService(StudentJpaRepository);
    private static co.edu.cesde.ga.infrastructure.repository.CourseJpaRepository CourseJpaRepository;
    private static final CourseService courseService = new CourseService(CourseJpaRepository);
    private static co.edu.cesde.ga.infrastructure.repository.EnrollmentJpaRepository EnrollmentJpaRepository;
    private static final EnrollmentService enrollmentService = new EnrollmentService(EnrollmentJpaRepository);

    public static void main(String[] args) {
    }
}