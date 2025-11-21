package ws.beauty.salon.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stylist_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StylistService {

    @EmbeddedId
    private StylistServiceId id;

    //@MapsId("stylistId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stylist", nullable = false, insertable = false, updatable = false) 
    private Stylist stylist;

    //@MapsId("serviceId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false, insertable = false, updatable = false)
    private Service service;
    
    //@MapsId("appointmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_appointment", nullable = false, insertable = false, updatable = false)
    private Appointment appointment;
}
