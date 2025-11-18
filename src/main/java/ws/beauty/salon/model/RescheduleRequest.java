package ws.beauty.salon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reschedule_requests")
public class RescheduleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_request")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "appointment_id", nullable = false) 
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_client", nullable = false) 
    private Client client;

    @Column(name = "requested_date", nullable = false)
    private String requestedDate;

    @Column(name = "status", length = 20)
    private String status; 

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at", updatable = false)
    private String createdAt; 
}
