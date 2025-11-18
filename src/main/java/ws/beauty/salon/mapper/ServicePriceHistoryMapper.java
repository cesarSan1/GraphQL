package ws.beauty.salon.mapper;

import org.springframework.stereotype.Component;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;
import ws.beauty.salon.model.ServicePriceHistory;
import ws.beauty.salon.model.User;

@Component
public class ServicePriceHistoryMapper {
   public ServicePriceHistoryResponse toResponse(ServicePriceHistory entity) {
        if (entity == null) {
            return null;
        }
        
        String changedByName = null;
        Integer changedById = null;
        
        if (entity.getChangedBy() != null) {
            User user = entity.getChangedBy();
            changedById = user.getIdUser();

            if (user.getClient() != null) {
                changedByName = user.getClient().getFirstName() + " " + user.getClient().getLastName();
            } else if (user.getStylist() != null) {
                changedByName = user.getStylist().getFirstName() + " " + user.getStylist().getLastName();
            } else {
                changedByName = user.getUsername();
            }
        }
        
        return ServicePriceHistoryResponse.builder()
                .idPrice(entity.getIdPrice())
                .idService(entity.getService() != null ? entity.getService().getId() : null) 
                .serviceName(entity.getService() != null ? entity.getService().getServiceName() : null)
                .oldPrice(entity.getOldPrice())
                .newPrice(entity.getNewPrice())
                .changedById(changedById)
                .changedByName(changedByName)
                .changedAt(entity.getChangedAt())
                .build();
    }
}
