package co.edu.cesde.ga.application.dto.request;

import co.edu.cesde.ga.domain.models.EnrollmentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateStudentRequestDto(

        @NotNull
        Long id,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String lastName,
        @Email
        String email,
        @NotNull
        @NotBlank
        EnrollmentStatus enrollmentStatus,
        @NotNull
        LocalDate birthDate

) {
}
