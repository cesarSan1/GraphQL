package ws.beauty.salon.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;

import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.StylistServiceDTO;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.mapper.StylistServiceMapper;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.StylistService;
import ws.beauty.salon.model.StylistServiceId;
import ws.beauty.salon.repository.AppointmentRepository;
import ws.beauty.salon.repository.ServiceRepository;
import ws.beauty.salon.repository.StylistRepository;
import ws.beauty.salon.repository.StylistServiceRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class StylistServiceServiceImpl implements StylistServiceService {
    private final StylistServiceRepository repository;
    private final StylistRepository stylistRepository;
    private final ServiceRepository serviceRepository;
    private final StylistServiceMapper mapper;
    private final AppointmentRepository appointmentRepository;

        @Override
        public List<StylistServiceResponse> findAll() {
        return repository.findAllWithRelations().stream()
                .map(mapper::toResponse)
                .toList();
        }

        @Override
        public List<StylistServiceResponse> findAll(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(mapper::toResponse)
                .toList();
        }

       @Override
        public StylistServiceResponse findById(Integer stylistId, Integer serviceId, Integer appointmentId) {
                StylistService entity = repository.findByCompositeId(stylistId, serviceId, appointmentId)
                        .orElseThrow(() -> new RuntimeException("Stylist-Service relation not found."));
                return mapper.toResponse(entity);
        }

        @Override
        public List<StylistServiceResponse> findByStylistId(Integer stylistId) {
                return repository.findByStylist_Id(stylistId).stream()
                .map(mapper::toResponse)
                .toList();
        }
        @Override
        public List<StylistServiceResponse> findByServiceId(Integer serviceId) {
                return repository.findByService_Id(serviceId).stream()
                .map(mapper::toResponse)
                .toList();
        }
        @Override
        public StylistServiceResponse create(StylistServiceDTO req) {
                Stylist stylist = stylistRepository.findById(req.getStylistId())
                        .orElseThrow(() -> new RuntimeException("Stylist not found"));
                Service service = serviceRepository.findById(req.getServiceId())
                        .orElseThrow(() -> new RuntimeException("Service not found"));
                Appointment appointment = appointmentRepository.findById(req.getAppointmentId())
                        .orElseThrow(() -> new RuntimeException("Appointment not found"));

                StylistServiceId id = StylistServiceId.builder()
                        .stylistId(req.getStylistId())
                        .serviceId(req.getServiceId())
                        .appointmentId(req.getAppointmentId())
                        .build();

                StylistService entity = StylistService.builder()
                        .id(id)
                        .stylist(stylist)
                        .service(service)
                        .appointment(appointment)
                        .build();

                StylistService saved = repository.save(entity);
                        saved.getStylist().getId();
                        saved.getService().getId();
                        saved.getAppointment().getId();

                return mapper.toResponse(repository.save(entity));
        }
}