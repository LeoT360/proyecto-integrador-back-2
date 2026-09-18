package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CreateCourseRequestDto;
import co.edu.cesde.ga.application.dto.response.CreateCourseResponseDto;
import co.edu.cesde.ga.application.repository.CourseRepository;
import co.edu.cesde.ga.domain.exceptions.CourseAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.CourseNotFoundException;
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
        try {
            var courses = courseService.findAll();

            var response = courses.stream()
                    .map(CreateCourseResponseDto::fromCourse)
                    .toList();

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            System.out.println("Unexpected error getting courses: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseRequestDto course) {

        try {
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

        } catch (CourseAlreadyExistsException e) {
            System.out.println("Error creating course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (Exception e) {
            System.out.println("Unexpected error creating course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(
            @Valid @Min(1) @PathVariable Long id) {

        try {
            courseService.deleteById(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (CourseNotFoundException e) {
            System.out.println("Error deleting course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error deleting course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourse(
            @Valid @Min(1) @PathVariable Long id) {

        try {
            Course course = courseService.findById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreateCourseResponseDto.fromCourse(course));

        } catch (CourseNotFoundException e) {
            System.out.println("Error finding course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error finding course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(
            @Valid @Min(1) @PathVariable Long id,
            @RequestBody Course course) {

        try {
            course.setId(id);

            Course updatedCourse = courseService.update(course);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreateCourseResponseDto.fromCourse(updatedCourse));

        } catch (CourseNotFoundException e) {
            System.out.println("Error updating course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            System.out.println("Unexpected error updating course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}