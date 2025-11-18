package ws.beauty.salon.graphql;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ws.beauty.salon.dto.StylistServiceDTO;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.service.StylistServiceService;

@Controller
public class StylistServiceGraphql {

    @Autowired
    private StylistServiceService service;

    @QueryMapping
    public List<StylistServiceResponse> findAllStylistServices() {
        return service.findAll();
    }

    @QueryMapping
    public StylistServiceResponse findStylistServiceById(
            @Argument Integer stylistId,
            @Argument Integer serviceId,
            @Argument Integer appointmentId) {
        return service.findById( stylistId, serviceId, appointmentId); 
    }

    @QueryMapping
    public List<StylistServiceResponse> findStylistServicesByStylist(@Argument Integer stylistId) {
        return service.findByStylistId(stylistId);
    }

    @QueryMapping
    public List<StylistServiceResponse> findStylistServicesByService(@Argument Integer serviceId) {
        return service.findByServiceId(serviceId);
    }

    @MutationMapping
    public StylistServiceResponse createStylistService(@Valid @Argument StylistServiceDTO req) {
        return service.create(req);
    }
}
