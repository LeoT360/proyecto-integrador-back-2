package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.Course;
import co.edu.cesde.ga.domain.models.Enrollment;
import co.edu.cesde.ga.domain.models.EnrollmentStatus;
import co.edu.cesde.ga.domain.models.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateEnrollmentResponseDto(

        String id,

        Student studentId,

        Course courseId,

        LocalDate enrollmentDate,

        EnrollmentStatus status

) {

    public static CreateEnrollmentResponseDto fromEnrollment(Enrollment createdEnrollment) {
        return new CreateEnrollmentResponseDto(
                createdEnrollment.getId(),
                createdEnrollment.getStudentId(),
                createdEnrollment.getCourseId(),
                createdEnrollment.getEnrollmentDate(),
                createdEnrollment.getStatus()
        );
    }
}