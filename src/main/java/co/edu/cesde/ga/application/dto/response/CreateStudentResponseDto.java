package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.EnrollmentStatus;
import co.edu.cesde.ga.domain.models.Student;

public record CreateStudentResponseDto(

        Long id,

        String firstName,

        String lastName,

        String email,

        EnrollmentStatus enrollmentStatus
) {

    public static Object fromStudent(Student createdStudent) {
        return new CreateStudentResponseDto(
                createdStudent.getId(),
                createdStudent.getFirstName(),
                createdStudent.getLastName(),
                createdStudent.getEmail(),
                createdStudent.getEnrollmentStatus()
        );
    }
}
