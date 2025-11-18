package ws.beauty.salon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class RescheduleRequestDTO {

    @NotNull(message = "Appointment ID is required")
    @JsonProperty("Id Appointment") 
    private Integer appointmentId;

    @NotNull(message = "Client ID is required")
    @JsonProperty("Id Client")
    private Integer clientId;

    @NotNull(message = "Requested date is required")
    @JsonProperty("Requested Date")
    private String requestedDate;

    @NotBlank(message = "Status is required")
    @JsonProperty("Status")
    private String status;

    @JsonProperty("Reason")
    private String reason;
}