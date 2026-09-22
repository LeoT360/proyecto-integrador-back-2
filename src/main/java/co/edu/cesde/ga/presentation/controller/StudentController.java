package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateStudentRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateStudentResponseDto;
import co.edu.cesde.ga.application.repository.StudentRepository;
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
        var students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@Valid @RequestBody CreateStudentRequestDto student) {
        var createStudentDto = new CreateStudentResponseDto(
                student.id(),
                student.firstName(),
                student.lastName(),
                student.email(),
                student.enrollmentStatus()
        );

        Student createdStudent = studentService.save(createStudentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateStudentResponseDto.fromStudent(createdStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@Valid @Min(1) @PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@Valid @Min(1) @PathVariable Long id) {
        Student student = studentService.findById(id);

        return ResponseEntity.ok(CreateStudentResponseDto.fromStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@Valid @Min(1) @PathVariable Long id, @Valid @RequestBody Student student) {

        student.setId(id);
        Student updatedStudent = studentService.update(student);

        return ResponseEntity.ok(CreateStudentResponseDto.fromStudent(updatedStudent));
    }
}