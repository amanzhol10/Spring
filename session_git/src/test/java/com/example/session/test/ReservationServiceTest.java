package com.example.session.test;

import com.example.session.dto.ReservationRequestDTO;
import com.example.session.dto.ReservationResponseDTO;
import com.example.session.entity.Book;
import com.example.session.entity.Reservation;
import com.example.session.entity.User;
import com.example.session.mappers.ReservationMapper;
import com.example.session.repository.BookRepository;
import com.example.session.repository.ReservationRepository;
import com.example.session.repository.UserRepository;
import com.example.session.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void create_shouldCreateReservation() {
        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setUserId(1L);
        dto.setBookId(1L);

        User user = new User();
        user.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setAvailableCopies(3);

        Reservation reservation = new Reservation();
        ReservationResponseDTO responseDTO = new ReservationResponseDTO();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(reservationMapper.toEntity(dto)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(responseDTO);

        ReservationResponseDTO result = reservationService.create(dto);

        assertNotNull(result);
        verify(bookRepository).save(book);
        verify(reservationRepository).save(reservation);
    }

    @Test
    void create_shouldThrowIfNoCopies() {
        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setUserId(1L);
        dto.setBookId(1L);

        Book book = new Book();
        book.setAvailableCopies(0);

        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(RuntimeException.class, () -> reservationService.create(dto));
    }

    @Test
    void getAll_shouldReturnList() {
        List<Reservation> reservations = List.of(new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);
        when(reservationMapper.toDtoList(reservations))
                .thenReturn(List.of(new ReservationResponseDTO()));

        List<ReservationResponseDTO> result = reservationService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void getById_shouldReturnReservation() {
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationMapper.toDto(reservation)).thenReturn(new ReservationResponseDTO());

        ReservationResponseDTO result = reservationService.getById(1L);

        assertNotNull(result);
    }

    @Test
    void delete_shouldCallRepository() {
        reservationService.delete(1L);
        verify(reservationRepository).deleteById(1L);
    }
}
