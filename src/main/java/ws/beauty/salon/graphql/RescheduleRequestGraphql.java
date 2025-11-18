package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.RescheduleRequestDTO;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.service.RescheduleRequestService;

@Controller
public class RescheduleRequestGraphql {

    @Autowired
    private RescheduleRequestService service;

    // Obtiene todas las solicitudes de reprogramación 
    @QueryMapping
    public List<RescheduleRequestResponse> findAllRescheduleRequests() {
        return service.findAll();
    }

    // Obtiene solicitudes paginadas 
    @QueryMapping
    public List<RescheduleRequestResponse> findAllRescheduleRequestsPaged(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    //Busca una solicitud por su ID 
    @QueryMapping
    public RescheduleRequestResponse findRescheduleRequestById(@Argument Integer id) {
        return service.findById(id);
    }

    //  Nuevo método para buscar por estado
    @QueryMapping
    public List<RescheduleRequestResponse> findRescheduleRequestsByStatus(@Argument String status) {
        return service.findByStatus(status);
    }

    // Crea una nueva solicitud de reprogramación 
    @MutationMapping
    public RescheduleRequestResponse createRescheduleRequest(@Valid @Argument RescheduleRequestDTO request) {
        return service.create(request);
    }

    //Actualiza una solicitud existente 
    @MutationMapping
    public RescheduleRequestResponse updateRescheduleRequest(
            @Argument Integer id,
            @Valid @Argument RescheduleRequestDTO request) {
        return service.update(id, request);
    }
}
