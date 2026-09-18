package co.edu.cesde.ga.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    @Id
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_code", unique = true, nullable = false)
    private String code;

    @Column(name = "course_name", nullable = false, length = 100)
    private String name;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "course_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Max(value = 30, message = "Minima capacidad del curso debe ser de al menos 15")
    @Min(value = 15, message = "Maxima capacidad del curso debe ser de 30")
    @Column(name = "course_maxCapacity", nullable = false)
    private Integer maxCapacity;

    public Course(Long id, String code, String name, String description, Integer maxCapacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.maxCapacity = maxCapacity;
    }
}