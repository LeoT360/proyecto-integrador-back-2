package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateStudentRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateStudentResponseDto;
import co.edu.cesde.ga.application.repository.StudentRepository;
import co.edu.cesde.ga.domain.exceptions.StudentAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.StudentNotFoundException;
import co.edu.cesde.ga.domain.models.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private final StudentRepository studentService;

    public StudentController(StudentRepository studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> getStudents() {
        try {
            var students = studentService.findAll();

            var response = students.stream()
                    .map(CreateStudentResponseDto::fromStudent)
                    .toList();

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            System.out.println("Unexpected error getting students: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@Valid @RequestBody CreateStudentRequestDto student) {
        try {
            Student createdStudent = studentService.save(new Student(
                    student.id(),
                    student.firstName(),
                    student.lastName(),
                    student.email(),
                    student.birthDate()
            ));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (StudentAlreadyExistsException e) {
            System.out.println("Error creating student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println("Unexpected error creating student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@Valid @Min(1) @PathVariable Long id) {
        try {
            studentService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (StudentNotFoundException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println("Unexpected error deleting student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@Valid @Min(1) @PathVariable Long id) {
        try {
            Student student = studentService.findById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreateStudentResponseDto.fromStudent(student));

        } catch (StudentNotFoundException e) {
            System.out.println("Error finding student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error finding student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@Valid @PathVariable Long id, @RequestBody Student student) {
        try {
            student.setId(id);
            Student updatedStudent = studentService.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudent);
        } catch (StudentNotFoundException e) {
            System.out.println("Error updating student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println("Unexpected error updating student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}