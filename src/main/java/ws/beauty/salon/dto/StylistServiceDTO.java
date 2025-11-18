package ws.beauty.salon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StylistServiceDTO {

    @NotNull(message = "Stylist ID is required")
    @JsonProperty("Id Stylist")
    private Integer stylistId;

    @NotNull(message = "Service ID is required")
    @JsonProperty("Id Service")
    private Integer serviceId;

    @NotNull(message = "Appointment ID is requiered")
    //@JsonProperty("Id Appointment")
    private Integer appointmentId;
}
