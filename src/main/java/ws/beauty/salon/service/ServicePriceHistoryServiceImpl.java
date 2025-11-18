package ws.beauty.salon.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.ServicePriceHistoryDTO;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;
import ws.beauty.salon.mapper.ServicePriceHistoryMapper;
import ws.beauty.salon.model.ServicePriceHistory;
import ws.beauty.salon.model.User;
import ws.beauty.salon.repository.ServicePriceHistoryRepository;
import ws.beauty.salon.repository.ServiceRepository;
import ws.beauty.salon.repository.UserRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicePriceHistoryServiceImpl implements ServicePriceHistoryService{
    private final ServicePriceHistoryRepository repository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final ServicePriceHistoryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findAll(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePriceHistoryResponse findById(Integer id) {
        ServicePriceHistory entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price history not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public ServicePriceHistoryResponse create(ServicePriceHistoryDTO req) {
        ws.beauty.salon.model.Service serviceEntity = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + req.getServiceId()));
        
        User changedBy = null;
        if (req.getChangedById() != null) {
            changedBy = userRepository.findById(req.getChangedById())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + req.getChangedById()));
        }

        ServicePriceHistory entity = ServicePriceHistory.builder()
                .service(serviceEntity)
                .oldPrice(req.getOldPrice())
                .newPrice(req.getNewPrice())
                .changedBy(changedBy)
                .build();
        ServicePriceHistory saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public ServicePriceHistoryResponse update(Integer id, ServicePriceHistoryDTO req) {
        ServicePriceHistory entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price history not found with id: " + id));

        entity.setOldPrice(req.getOldPrice());
        entity.setNewPrice(req.getNewPrice());

        if (req.getChangedById() != null) {
            User changedBy = userRepository.findById(req.getChangedById())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + req.getChangedById()));
            entity.setChangedBy(changedBy);
        }

        ServicePriceHistory updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findByServiceId(Integer serviceId) {
        return repository.findByService_Id(serviceId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ServicePriceHistoryResponse findLatestByServiceId(Integer serviceId) {
        ServicePriceHistory entity = repository
                .findFirstByService_IdOrderByChangedAtDesc(serviceId) 
                .orElseThrow(() -> new RuntimeException("No price history found for service id: " + serviceId));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findByChangedById(Integer idUser) {
            return repository.findByChangedBy_IdUser(idUser).stream() 
                    .map(mapper::toResponse)
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findByOldPriceGreaterThan(Double value) {
        return repository.findByOldPriceGreaterThan(value).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicePriceHistoryResponse> findByNewPriceLessThan(Double value) {
        return repository.findByNewPriceLessThan(value).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServicePriceHistoryResponse> findAllWithPagination(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize))
                .map(mapper::toResponse);
    }
}