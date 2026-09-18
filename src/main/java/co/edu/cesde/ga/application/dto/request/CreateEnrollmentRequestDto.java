package co.edu.cesde.ga.application.dto.request;

import co.edu.cesde.ga.domain.models.EnrollmentStatus;
import co.edu.cesde.ga.domain.models.Student;
import co.edu.cesde.ga.domain.models.Course;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateEnrollmentRequestDto(

        @NotNull
        Student studentId,
        @NotNull
        Course courseId,
        @NotNull
        LocalDate enrollmentDate,
        @NotNull
        EnrollmentStatus status

) {
}