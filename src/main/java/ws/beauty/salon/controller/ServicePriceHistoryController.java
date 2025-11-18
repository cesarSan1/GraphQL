package ws.beauty.salon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ws.beauty.salon.dto.ServicePriceHistoryDTO;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;
import ws.beauty.salon.service.ServicePriceHistoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service_price-history")
@Tag(name = "Service Price History", description = "Manages price change history for services") 
public class ServicePriceHistoryController {

    private final ServicePriceHistoryService service;

    /*@GetMapping
    @Operation(summary = "Get all price history records")
    public List<ServicePriceHistoryResponse> findAll() {
        return service.findAll();
    }*/

    @GetMapping("/pagination")
    @Operation(summary = "Get price history with pagination")
    public List<ServicePriceHistoryResponse> findAllPaged(
            @RequestParam int page,
            @RequestParam int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get price history by ID")
    public ServicePriceHistoryResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/service/{serviceId}")
    @Operation(summary = "Get price history by service ID")
    public List<ServicePriceHistoryResponse> findByServiceId(@PathVariable Integer serviceId) {
        return service.findByServiceId(serviceId);
    }

    @GetMapping("/service/{serviceId}/latest")
    @Operation(summary = "Get latest price change for a service")
    public ServicePriceHistoryResponse findLatestByServiceId(@PathVariable Integer serviceId) {
        return service.findLatestByServiceId(serviceId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get price changes made by a user")
    public List<ServicePriceHistoryResponse> findByUserId(@PathVariable Integer userId) {
        return service.findByChangedById(userId);
    }

    @GetMapping("/old-price-greater-than/{value}")
    @Operation(summary = "Get records where old price is greater than value")
    public List<ServicePriceHistoryResponse> findByOldPrice(@PathVariable Double value) {
        return service.findByOldPriceGreaterThan(value);
    }

    @GetMapping("/new-price-less-than/{value}")
    @Operation(summary = "Get records where new price is less than value")
    public List<ServicePriceHistoryResponse> findByNewPrice(@PathVariable Double value) {
        return service.findByNewPriceLessThan(value);
    }

    @PostMapping
    @Operation(summary = "Create new price history record")
    public ResponseEntity<ServicePriceHistoryResponse> create(@Valid @RequestBody ServicePriceHistoryDTO req) {
        ServicePriceHistoryResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/service-price-history/" + created.getIdPrice()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update price history record")
    public ServicePriceHistoryResponse update(
            @PathVariable Integer id,
            @Valid @RequestBody ServicePriceHistoryDTO req) {
        return service.update(id, req);
    }
} 
