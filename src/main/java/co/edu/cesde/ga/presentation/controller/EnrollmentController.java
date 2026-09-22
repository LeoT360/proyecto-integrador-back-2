package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateEnrollmentRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateEnrollmentResponseDto;
import co.edu.cesde.ga.application.repository.EnrollmentRepository;
import co.edu.cesde.ga.domain.models.Enrollment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
@Validated
public class EnrollmentController {

    private final EnrollmentRepository enrollmentService;

    public EnrollmentController(EnrollmentRepository enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<Object> getEnrollments() {
        var enrollments = enrollmentService.findAll();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<Object> createEnrollment(@Valid @RequestBody CreateEnrollmentRequestDto enrollment) {
        Enrollment createdEnrollment = enrollmentService.save(new Enrollment(
                enrollment.studentId(),
                enrollment.courseId(),
                enrollment.enrollmentDate(),
                null
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateEnrollmentResponseDto.fromEnrollment(createdEnrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable @NotBlank String id) {
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnrollment(@PathVariable @NotBlank String id) {
        Enrollment enrollment = enrollmentService.findById(id);

        return ResponseEntity
                .ok(CreateEnrollmentResponseDto.fromEnrollment(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEnrollment(@PathVariable @NotBlank String id, @Valid @RequestBody Enrollment enrollment) {

        enrollment.setId(id);
        Enrollment updatedEnrollment = enrollmentService.update(enrollment);

        return ResponseEntity.ok(CreateEnrollmentResponseDto.fromEnrollment(updatedEnrollment));
    }
}