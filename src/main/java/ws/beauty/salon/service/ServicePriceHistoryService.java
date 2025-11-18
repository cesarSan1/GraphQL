package ws.beauty.salon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ws.beauty.salon.dto.ServicePriceHistoryDTO;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;

@Service
public interface ServicePriceHistoryService {
    List<ServicePriceHistoryResponse> findAll();
    List<ServicePriceHistoryResponse> findAll(int page, int pageSize);
    ServicePriceHistoryResponse findById(Integer id);
    ServicePriceHistoryResponse create(ServicePriceHistoryDTO request);
    ServicePriceHistoryResponse update(Integer id, ServicePriceHistoryDTO request);
    List<ServicePriceHistoryResponse> findByServiceId(Integer serviceId);
    ServicePriceHistoryResponse findLatestByServiceId(Integer serviceId);
    List<ServicePriceHistoryResponse> findByChangedById(Integer userId);
    List<ServicePriceHistoryResponse> findByOldPriceGreaterThan(Double value);
    List<ServicePriceHistoryResponse> findByNewPriceLessThan(Double value);
    Page<ServicePriceHistoryResponse> findAllWithPagination(int page, int pageSize);
}
