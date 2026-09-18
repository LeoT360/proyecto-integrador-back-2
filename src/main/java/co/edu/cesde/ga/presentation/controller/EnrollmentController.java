package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateEnrollmentRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateEnrollmentResponseDto;
import co.edu.cesde.ga.application.repository.EnrollmentRepository;
import co.edu.cesde.ga.domain.exceptions.EnrollmentAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.EnrollmentNotFoundException;
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
        try {
            var enrollments = enrollmentService.findAll();

            var response = enrollments.stream()
                    .map(CreateEnrollmentResponseDto::fromEnrollment)
                    .toList();

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            System.out.println("Unexpected error getting enrollments: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createEnrollment(@Valid @RequestBody CreateEnrollmentRequestDto enrollment) {

        try {
            Enrollment createdEnrollment = enrollmentService.save(new Enrollment(
                    enrollment.studentId(),
                    enrollment.courseId(),
                    enrollment.enrollmentDate(),
                    null
            ));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(CreateEnrollmentResponseDto.fromEnrollment(createdEnrollment));

        } catch (EnrollmentAlreadyExistsException e) {
            System.out.println("Error creating enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (Exception e) {
            System.out.println("Unexpected error creating enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable @NotBlank String id) {

        try {
            enrollmentService.deleteById(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (EnrollmentNotFoundException e) {
            System.out.println("Error deleting enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error deleting enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnrollment(@PathVariable @NotBlank String id) {

        try {
            Enrollment enrollment = enrollmentService.findById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreateEnrollmentResponseDto.fromEnrollment(enrollment));

        } catch (EnrollmentNotFoundException e) {
            System.out.println("Error finding enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error finding enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEnrollment(@PathVariable @NotBlank String id, @RequestBody Enrollment enrollment) {

        try {
            enrollment.setId(id);

            Enrollment updatedEnrollment = enrollmentService.update(enrollment);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreateEnrollmentResponseDto.fromEnrollment(updatedEnrollment));

        } catch (EnrollmentNotFoundException e) {
            System.out.println("Error updating enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error updating enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}