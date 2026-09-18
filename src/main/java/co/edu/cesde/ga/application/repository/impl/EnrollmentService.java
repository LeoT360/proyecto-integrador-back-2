package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.repository.EnrollmentRepository;
import co.edu.cesde.ga.domain.exceptions.EnrollmentAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.EnrollmentNotFoundException;
import co.edu.cesde.ga.domain.models.Enrollment;
import co.edu.cesde.ga.infrastructure.repository.EnrollmentJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentRepository;

    public EnrollmentService(EnrollmentJpaRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }

        if (enrollmentRepository.existsById(enrollment.getId())) {
            throw new EnrollmentAlreadyExistsException(enrollment.getId());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Boolean existsById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }

        return enrollmentRepository.existsById(id);
    }

    @Override
    public Enrollment findById(String id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }

        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }

        enrollmentRepository.deleteById(id);
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }

        if (!enrollmentRepository.existsById(enrollment.getId())) {
            throw new EnrollmentNotFoundException(enrollment.getId());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }
}