package br.com.fiap.squad3.restaurantfinder.service.impl;

import br.com.fiap.squad3.restaurantfinder.controller.exception.ControllerNotFoundException;
import br.com.fiap.squad3.restaurantfinder.converter.ReservaConverter;
import br.com.fiap.squad3.restaurantfinder.model.Reserva;
import br.com.fiap.squad3.restaurantfinder.model.dtos.ReservaDto;
import br.com.fiap.squad3.restaurantfinder.model.enums.DiasSemanas;
import br.com.fiap.squad3.restaurantfinder.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.service.ReservaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;

@Service
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;
    private RestauranteRepository restauranteRepository;
    private UsuarioRepository usuarioRepository;
    private ReservaConverter reservaConverter;

    public ReservaServiceImpl(
            ReservaRepository reservaRepository,
            RestauranteRepository restauranteRepository,
            UsuarioRepository usuarioRepository,
            ReservaConverter reservaConverter
    ) {
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.reservaConverter = reservaConverter;
    }

    @Override
    @Transactional
    public ReservaDto save(ReservaDto reservaDto) {
        var usuario = usuarioRepository.findById(reservaDto.usuario_id())
                .orElseThrow(() -> new ControllerNotFoundException("Usuario não encontrado"));

        var restaurante = restauranteRepository.findById(reservaDto.restaurante_id())
                .orElseThrow(() -> new ControllerNotFoundException("Restaurante não encontrado"));

        Reserva reserva = reservaConverter.toEntity(reservaDto, usuario, restaurante);

        // Verifica se o usuário já tem uma reserva no mesmo horário
        if (reservaRepository.existsByUsuarioIdAndDataHoraInicio(reserva.getUsuario().getId(), reserva.getDataHoraInicio())) {
            throw new IllegalArgumentException("Usuário já possui uma reserva neste horário");
        }

        // Verifica se o dia da reserva é permitido pelo restaurante
        DayOfWeek dayOfWeek = reserva.getDataHoraInicio().getDayOfWeek();
        if (!restaurante.getDiasFuncionamentos().contains(DiasSemanas.valueOf(dayOfWeek.name()))) {
            throw new IllegalArgumentException("Restaurante não funciona neste dia");
        }

        // Atualiza a capacidade do restaurante
        if (restaurante.getCapacidade() < reserva.getQuantidadePessoas()) {
            throw new IllegalArgumentException("Capacidade do restaurante excedida");
        }
        restaurante.setCapacidade(restaurante.getCapacidade() - reserva.getQuantidadePessoas());
        restauranteRepository.save(restaurante);

        Reserva novaReserva = reservaRepository.save(reserva);
        return reservaConverter.toDto(novaReserva);
    }

    @Override
    @Transactional
    public ReservaDto update(Long id, ReservaDto reservaDto) {
        // Implementação similar ao save, com lógica adicional para atualizar uma reserva existente
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Implementação para deletar uma reserva e atualizar a capacidade do restaurante
    }

    @Override
    public ReservaDto findById(Long id) {
        // Implementação para encontrar uma reserva pelo ID
        return null;
    }

    @Override
    public Page<ReservaDto> findAll(Pageable pageable) {
        // Implementação para listar todas as reservas com paginação
        return null;
    }

}