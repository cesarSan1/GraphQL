package ws.beauty.salon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ws.beauty.salon.dto.RescheduleRequestDTO;
import ws.beauty.salon.dto.RescheduleRequestResponse;
@Service
public interface RescheduleRequestService {
    List<RescheduleRequestResponse> findAll();
    List<RescheduleRequestResponse> findAll(int page, int pageSize);
    RescheduleRequestResponse findById(Integer id);
    RescheduleRequestResponse create(RescheduleRequestDTO request);
    RescheduleRequestResponse update(Integer id, RescheduleRequestDTO request);
    List<RescheduleRequestResponse> findByStatus(String status);
    List<RescheduleRequestResponse> findByClientId(Integer clientId);
    List<RescheduleRequestResponse> searchByReason(String keyword);
    List<RescheduleRequestResponse> findAllOrderByCreatedAtDesc();
}
