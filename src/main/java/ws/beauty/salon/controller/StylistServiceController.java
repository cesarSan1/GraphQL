package ws.beauty.salon.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.StylistServiceDTO;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.service.StylistServiceService;

@RestController
@RequestMapping("/api/stylist-services")
@RequiredArgsConstructor
@Tag(name = "Stylist Services", description = "Manage relations between stylists and services") 
public class StylistServiceController {
    private final StylistServiceService service;

    @GetMapping
    @Operation(summary = "Get all stylist-service relationships")
    @ApiResponse(responseCode = "200", description = "List of stylist-service associations.",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StylistServiceResponse.class))))
    public List<StylistServiceResponse> findAll() {
        // Devuelve una lista estilistas y servicios
        return service.findAll();
    }

    @GetMapping(value = "/pagination", params = {"page", "pageSize"})
    @Operation(summary = "Get all stylist-service relationships with pagination")
    public List<StylistServiceResponse> findAll(@RequestParam int page, @RequestParam int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{stylistId}/{serviceId}/{appointmentId}")
    @Operation(summary = "Get stylist-service relationship by IDs")
    public StylistServiceResponse findById(@PathVariable Integer stylistId, @PathVariable Integer serviceId, @PathVariable Integer appointmentId ) {
        // Busca una relación específica entre un estilista y un servicio
        return service.findById(stylistId, serviceId, appointmentId);
    }

    @PostMapping
    @Operation(summary = "Create new stylist-service relationship")
    public ResponseEntity<StylistServiceResponse> create(@Valid @RequestBody StylistServiceDTO req) {
        // Crea una nueva relación entre un estilista y un servicio
        StylistServiceResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/stylist-service/" + created.getStylistId() + "/" + created.getServiceId()))
                .body(created);
    } 
}

