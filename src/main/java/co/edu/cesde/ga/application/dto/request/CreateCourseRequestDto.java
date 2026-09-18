package co.edu.cesde.ga.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseRequestDto(

        @NotNull
        Long id,
        @NotNull
        @NotBlank
        String code,
        @NotNull
        @NotBlank
        String name,
        String description,
        @NotNull
        @Min(value = 15, message = "La capacidad mínima del curso debe ser de 15")
        @Max(value = 30, message = "La capacidad máxima del curso debe ser de 30")
        Integer maxCapacity

) {
}