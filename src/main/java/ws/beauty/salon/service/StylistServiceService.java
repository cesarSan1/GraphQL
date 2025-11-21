package ws.beauty.salon.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ws.beauty.salon.dto.StylistServiceDTO;
import ws.beauty.salon.dto.StylistServiceResponse;

@Service
public interface StylistServiceService {
    List<StylistServiceResponse> findAll();
    List<StylistServiceResponse> findAll(int page, int pageSize);
    StylistServiceResponse findById(Integer stylistId, Integer serviceId, Integer appointmentId);
    StylistServiceResponse create(StylistServiceDTO request);
    List<StylistServiceResponse> findByStylistId(Integer stylistId);
    List<StylistServiceResponse> findByServiceId(Integer serviceId);
}