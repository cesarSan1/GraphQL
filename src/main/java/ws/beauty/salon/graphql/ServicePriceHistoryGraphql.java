package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.ServicePriceHistoryDTO;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;
import ws.beauty.salon.service.ServicePriceHistoryService;

@Controller
public class ServicePriceHistoryGraphql {
    @Autowired
    private ServicePriceHistoryService service;

    @QueryMapping
    public List<ServicePriceHistoryResponse> findAllPriceHistories() {
        return service.findAll();
    }

    @QueryMapping
    public List<ServicePriceHistoryResponse> findAllPriceHistoriesPaged(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    @QueryMapping
    public ServicePriceHistoryResponse findPriceHistoryById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<ServicePriceHistoryResponse> findPriceHistoriesByServiceId(@Argument Integer serviceId) {
        return service.findByServiceId(serviceId);
    }

    @QueryMapping
    public ServicePriceHistoryResponse findLatestPriceByServiceId(@Argument Integer serviceId) {
        return service.findLatestByServiceId(serviceId);
    }

    @QueryMapping
    public List<ServicePriceHistoryResponse> findPriceHistoriesByUserId(@Argument Integer userId) {
        return service.findByChangedById(userId);
    }

    @QueryMapping
    public List<ServicePriceHistoryResponse> findPriceHistoriesByOldPriceGreaterThan(@Argument Double value) {
        return service.findByOldPriceGreaterThan(value);
    }

    @QueryMapping
    public List<ServicePriceHistoryResponse> findPriceHistoriesByNewPriceLessThan(@Argument Double value) {
        return service.findByNewPriceLessThan(value);
    }

    // Mutación 

    @MutationMapping
    public ServicePriceHistoryResponse createPriceHistory(@Valid @Argument ServicePriceHistoryDTO request) {
        return service.create(request);
    }

    @MutationMapping
    public ServicePriceHistoryResponse updatePriceHistory(
            @Argument Integer id,
            @Valid @Argument ServicePriceHistoryDTO request) {
        return service.update(id, request);
    }
}

