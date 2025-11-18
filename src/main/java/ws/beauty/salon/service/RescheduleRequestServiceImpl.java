package ws.beauty.salon.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.RescheduleRequestDTO;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.mapper.RescheduleRequestMapper;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.RescheduleRequest;
import ws.beauty.salon.repository.AppointmentRepository;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.repository.RescheduleRequestRepository;

@Service
@RequiredArgsConstructor
public class RescheduleRequestServiceImpl implements RescheduleRequestService {
    private final RescheduleRequestRepository repository;
    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final RescheduleRequestMapper mapper;

    @Override
    public List<RescheduleRequestResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<RescheduleRequestResponse> findAll(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public RescheduleRequestResponse findById(Integer id) {
        RescheduleRequest entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reschedule request not found with id " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public RescheduleRequestResponse create(RescheduleRequestDTO req) {
        Appointment appointment = appointmentRepository.findById(req.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Client client = clientRepository.findById(req.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        RescheduleRequest entity = RescheduleRequest.builder()
                .appointment(appointment)
                .client(client)
                .requestedDate(req.getRequestedDate())
                .status(req.getStatus())
                .reason(req.getReason())
                .build();

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public RescheduleRequestResponse update(Integer id, RescheduleRequestDTO req) {
        RescheduleRequest entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reschedule request not found"));

        entity.setRequestedDate(req.getRequestedDate());
        entity.setStatus(req.getStatus());
        entity.setReason(req.getReason());

        return mapper.toResponse(repository.save(entity));
    }
    @Override
    public List<RescheduleRequestResponse> findByStatus(String status) {
        throw new UnsupportedOperationException("Unimplemented method 'findByStatus'");
    }
    @Override
    public List<RescheduleRequestResponse> findByClientId(Integer clientId) {
        throw new UnsupportedOperationException("Unimplemented method 'findByClientId'");
    }
    @Override
    public List<RescheduleRequestResponse> searchByReason(String keyword) {
        throw new UnsupportedOperationException("Unimplemented method 'searchByReason'");
    }
    @Override
    public List<RescheduleRequestResponse> findAllOrderByCreatedAtDesc() {
        throw new UnsupportedOperationException("Unimplemented method 'findAllOrderByCreatedAtDesc'");
    }
}

