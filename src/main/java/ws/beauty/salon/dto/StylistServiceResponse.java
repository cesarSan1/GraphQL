package ws.beauty.salon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StylistServiceResponse {
    private Integer appointmentId;
    private Integer serviceId;
    private Integer stylistId;
    private String serviceName;
    private String stylistName;
}
