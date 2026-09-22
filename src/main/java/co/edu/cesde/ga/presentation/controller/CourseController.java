package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateCourseRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateCourseResponseDto;
import co.edu.cesde.ga.application.repository.CourseRepository;
import co.edu.cesde.ga.domain.models.Course;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {

    private final CourseRepository courseService;

    public CourseController(CourseRepository courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Object> getCourses() {
        var courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseRequestDto course) {
        Course createdCourse = courseService.save(new Course(
                course.id(),
                course.code(),
                course.name(),
                course.description(),
                course.maxCapacity()
        ));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateCourseResponseDto.fromCourse(createdCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(
            @Valid @Min(1) @PathVariable Long id) {

        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourse(
            @Valid @Min(1) @PathVariable Long id) {

        Course course = courseService.findById(id);

        return ResponseEntity
                .ok(CreateCourseResponseDto.fromCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(
            @Valid @Min(1) @PathVariable Long id,
            @Valid @RequestBody Course course) {

        course.setId(id);
        Course updatedCourse = courseService.update(course);

        return ResponseEntity
                .ok(CreateCourseResponseDto.fromCourse(updatedCourse));
    }
}