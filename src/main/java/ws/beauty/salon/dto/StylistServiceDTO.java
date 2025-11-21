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

    // ID del estilista asociado al servicio
    @NotNull(message = "Stylist ID is required")
    @JsonProperty("Id Stylist") // Nombre del campo en JSON
    private Integer stylistId;

    // ID del servicio asociado al estilista
    @NotNull(message = "Service ID is required")
    @JsonProperty("Id Service") // Nombre del campo en JSON
    private Integer serviceId;

    @NotNull(message = "Appointment ID is requiered")
    //@JsonProperty("Id Appointment")
    private Integer appointmentId;
}
