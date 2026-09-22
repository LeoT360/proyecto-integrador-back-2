package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.response.CreateStudentResponseDto;
import co.edu.cesde.ga.application.repository.StudentRepository;
import co.edu.cesde.ga.domain.exceptions.StudentAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.StudentNotFoundException;
import co.edu.cesde.ga.domain.models.Student;
import co.edu.cesde.ga.infrastructure.repository.StudentJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService implements StudentRepository {

    private final StudentJpaRepository studentRepository;

    public StudentService(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(CreateStudentResponseDto student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (studentRepository.existsById((student.id()))) {
            throw new StudentAlreadyExistsException(student.id());
        }

        var newStudent = new Student();
        newStudent.setId(student.id());
        newStudent.setFirstName(student.firstName());
        newStudent.setLastName(student.lastName());
        newStudent.setEmail(student.email());
        newStudent.setEnrollmentStatus(student.enrollmentStatus());
        newStudent.setCreatedAt(LocalDateTime.now());
        newStudent.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(newStudent);
    }

    @Override
    public Boolean existsById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        return studentRepository.existsById(id);
    }

    @Override
    public Student findById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (!studentRepository.existsById((student.getId()))) {
            throw new StudentNotFoundException(student.getId());
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}