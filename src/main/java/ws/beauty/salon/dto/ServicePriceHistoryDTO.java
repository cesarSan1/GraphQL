package ws.beauty.salon.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ServicePriceHistoryDTO {
     @NotNull(message = "Service ID is required")
    private Integer serviceId;
    
    @NotNull(message = "Old price is required")
    @Positive(message = "Old price must be positive")
    private Double oldPrice;
    
    @NotNull(message = "New price is required")
    @Positive(message = "New price must be positive")
    private Double newPrice;
    private Integer changedById; 
}