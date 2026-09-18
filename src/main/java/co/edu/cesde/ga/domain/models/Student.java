package co.edu.cesde.ga.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "student_id")
    private Long id;

    @NotEmpty
    @Column(name = "student_first_name", nullable = false, length = 100)
    private String firstName;

    @NotEmpty
    @Column(name = "student_last_name", nullable = false, length = 100)
    private String lastName;

    @Email
    @Column(name = "student_email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "student_birth_date")
    private LocalDate birthDate;

    @Column(name = "student_enrollment_status")
    private EnrollmentStatus enrollmentStatus;

    @Column(name = "student_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "student_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Student(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.enrollmentStatus = EnrollmentStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}