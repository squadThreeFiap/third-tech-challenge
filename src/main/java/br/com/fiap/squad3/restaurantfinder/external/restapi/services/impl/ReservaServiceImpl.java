package br.com.fiap.squad3.restaurantfinder.external.restapi.services.impl;

import br.com.fiap.squad3.restaurantfinder.external.jpa.converter.ReservaConverter;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.exception.ControllerNotFoundException;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.services.ReservaService;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
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

        ReservaEntity reservaEntity = reservaConverter.toEntity(reservaDto, usuario, restaurante);

        // Verifica se o usuário já tem uma reserva no mesmo horário
        if (reservaRepository.existsByUsuarioIdAndDataHoraInicio(reservaEntity.getUsuarioEntity().getId(), reservaEntity.getDataHoraInicio())) {
            throw new IllegalArgumentException("Usuário já possui uma reserva neste horário");
        }

        // Verifica se o dia da reserva é permitido pelo restaurante
        DayOfWeek dayOfWeek = reservaEntity.getDataHoraInicio().getDayOfWeek();
        if (!restaurante.getDiasFuncionamentos().contains(DiaSemana.valueOf(dayOfWeek.name()))) {
            throw new IllegalArgumentException("Restaurante não funciona neste dia");
        }

        // Atualiza a capacidade do restaurante
        if (restaurante.getCapacidade() < reservaEntity.getQuantidadePessoas()) {
            throw new IllegalArgumentException("Capacidade do restaurante excedida");
        }
        restaurante.setCapacidade(restaurante.getCapacidade() - reservaEntity.getQuantidadePessoas());
        restauranteRepository.save(restaurante);

        ReservaEntity novaReservaEntity = reservaRepository.save(reservaEntity);
        return reservaConverter.toDto(novaReservaEntity);
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