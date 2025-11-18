package ws.beauty.salon.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StylistServiceId implements Serializable {
    @Column(name = "id_stylist", nullable = false)
    private Integer stylistId;

    @Column(name = "id_service", nullable = false)  
    private Integer serviceId;

    @Column(name = "id_appointment", nullable = false) 
    private Integer appointmentId;
}
