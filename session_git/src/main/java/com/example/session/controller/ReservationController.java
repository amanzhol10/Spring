package com.example.session.controller;

import com.example.session.dto.ReservationRequestDTO;
import com.example.session.dto.ReservationResponseDTO;
import com.example.session.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_LIBRARIAN')")
    public ReservationResponseDTO create(@RequestBody ReservationRequestDTO dto) {
        return reservationService.create(dto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public List<ReservationResponseDTO> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("@reservationService.isOwnerOrLibrarian(#id, authentication.principal.id)")
    public ReservationResponseDTO getById(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@reservationService.isOwnerOrLibrarian(#id, authentication.principal.id)")
    public void delete(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
