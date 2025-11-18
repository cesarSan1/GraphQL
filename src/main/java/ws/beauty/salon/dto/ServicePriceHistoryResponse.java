package ws.beauty.salon.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ServicePriceHistoryResponse {
    private Integer idPrice;
    private Integer idService;
    private String serviceName;
    private Double oldPrice;
    private Double newPrice;
    private Integer changedById;
    private String changedByName;
    private LocalDateTime changedAt;
}
