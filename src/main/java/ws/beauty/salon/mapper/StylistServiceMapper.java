package ws.beauty.salon.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.model.StylistService;

@Component
@Service
public class StylistServiceMapper {
    public StylistServiceResponse toResponse(StylistService entity) {
        if (entity == null) {
            return null;
        }

        return StylistServiceResponse.builder()
                .stylistId(entity.getStylist() != null ? entity.getStylist().getId() : null)
                .stylistName(entity.getStylist() != null 
                        ? entity.getStylist().getFirstName() + " " + entity.getStylist().getLastName() 
                        : null)
                .serviceId(entity.getService() != null ? entity.getService().getId() : null)
                .serviceName(entity.getService() != null ? entity.getService().getServiceName() : null)
                .appointmentId(entity.getAppointment() != null ? entity.getAppointment().getId() : null)
                .build();
    }
}


