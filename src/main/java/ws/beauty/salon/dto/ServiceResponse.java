package ws.beauty.salon.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {
     private Integer id;

    @JsonProperty("service_name")
    private String serviceName;

    private String description;

    private Double price;

    @JsonProperty("estimated_duration")
    private LocalTime estimatedDuration;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
