package ws.beauty.salon.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.RescheduleRequestDTO;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.service.RescheduleRequestService;

@RestController
@RequestMapping("/api/reschedule-request")
@Tag(name = "Reschedule Requests", description = "Manages appointment reschedule requests") 
@RequiredArgsConstructor
public class RescheduleRequestController {
    // Inyección de dependencias por constructor 
    private final RescheduleRequestService service;
    public List<RescheduleRequestResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/pagination", params = {"page", "pageSize"})
    @Operation(summary = "Get all reschedule requests with pagination")
    public List<RescheduleRequestResponse> findAll(@RequestParam int page, @RequestParam int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reschedule request by ID")
    public RescheduleRequestResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create new reschedule request")
    public ResponseEntity<RescheduleRequestResponse> create(@Valid @RequestBody RescheduleRequestDTO req) {
        RescheduleRequestResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/reschedule-request/" + created.getIdRequest()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update reschedule request")
    public RescheduleRequestResponse update(@PathVariable Integer id, @Valid @RequestBody RescheduleRequestDTO req) {
        return service.update(id, req);
    }

}

