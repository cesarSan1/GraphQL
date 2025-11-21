package ws.beauty.salon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class RescheduleRequestResponse {

    @JsonProperty("Id")
    private Integer idRequest;

    @JsonProperty("Id Appointment")
    private Integer appointmentId;

    @JsonProperty("Id Cient")
    private Integer clientId;

    // Fecha solicitada para la reprogramación
    @JsonProperty("Requested Date")
    private String requestedDate;

    // Estado de la solicitud (ej. pending, approved, rejected)
    @JsonProperty("Status")
    private String status;

    // Motivo de la reprogramación
    @JsonProperty("Reason")
    private String reason;

    // Fecha y hora en que se creó la solicitud
    @JsonProperty("reated at")
    private String createdAt; 
}