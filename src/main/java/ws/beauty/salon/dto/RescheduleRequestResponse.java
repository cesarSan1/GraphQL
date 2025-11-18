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

    @JsonProperty("Requested Date")
    private String requestedDate;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Reason")
    private String reason;

    @JsonProperty("reated at")
    private String createdAt; 
}