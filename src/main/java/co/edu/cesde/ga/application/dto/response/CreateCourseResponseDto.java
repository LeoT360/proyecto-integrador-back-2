package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.Course;

import java.time.LocalDateTime;

public record CreateCourseResponseDto(

        Long id,

        String code,

        String name,

        String description,

        Integer maxCapacity

) {

    public static CreateCourseResponseDto fromCourse(Course createdCourse) {
        return new CreateCourseResponseDto(
                createdCourse.getId(),
                createdCourse.getCode(),
                createdCourse.getName(),
                createdCourse.getDescription(),
                createdCourse.getMaxCapacity()
        );
    }
}