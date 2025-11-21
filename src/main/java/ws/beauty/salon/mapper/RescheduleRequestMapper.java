package ws.beauty.salon.mapper;

import org.springframework.stereotype.Component;

import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.model.RescheduleRequest;
@Component
public class RescheduleRequestMapper {
    public RescheduleRequestResponse toResponse(RescheduleRequest entity) {
        if (entity == null) return null;
        return RescheduleRequestResponse.builder()
                .idRequest(entity.getId())
                .appointmentId(entity.getAppointment() != null ? entity.getAppointment().getId() : null)
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .requestedDate(entity.getRequestedDate())
                .status(entity.getStatus())
                .reason(entity.getReason())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

