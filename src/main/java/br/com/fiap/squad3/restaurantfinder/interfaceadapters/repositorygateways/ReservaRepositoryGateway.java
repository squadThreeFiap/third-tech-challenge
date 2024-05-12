package br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.ReservaEntityConverter;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaRepositoryGateway implements ReservaGateway {
    private final ReservaRepository reservaRepository;
    private final ReservaEntityConverter reservaEntityConverter;

    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    public ReservaRepositoryGateway(
            ReservaRepository reservaRepository,
            ReservaEntityConverter reservaEntityConverter,
            UsuarioRepository usuarioRepository,
            RestauranteRepository restauranteRepository
    ) {
        this.reservaRepository = reservaRepository;
        this.reservaEntityConverter = reservaEntityConverter;
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(
            Long idRestaurante,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    ) {
        List<ReservaEntity> reservasOcupadas = reservaRepository.findAllOccupiedReservationBetweenDates(
                idRestaurante,
                dataHoraInicio,
                dataHoraFim
        );

        return reservasOcupadas.stream().map(reservaEntityConverter::toDomainObj).toList();
    }

    @Override
    public Reserva cadastrar(Reserva reserva) {
        ReservaEntity reservaEntity = this.reservaEntityConverter.toEntity(reserva);
        reservaEntity.setUsuarioEntity(usuarioRepository.getReferenceById(reserva.getIdUsuario()));
        reservaEntity.setRestauranteEntity(restauranteRepository.getReferenceById(reserva.getIdRestaurante()));

        ReservaEntity reservaEntityCadastrado = this.reservaRepository.save(reservaEntity);

        return this.reservaEntityConverter.toDomainObj(reservaEntityCadastrado);
    }

    @Override
    public List<Reserva> obterReservasDoUsuarioNoHorarioAgendado(
            Long idUsuario,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    ) {
        List<ReservaEntity> reservasOcupadas = this.reservaRepository.findAllByIdUsuarioBetweenDates(
                idUsuario,
                dataHoraInicio,
                dataHoraFim
        );

        return reservasOcupadas.stream().map(reservaEntityConverter::toDomainObj).toList();
    }
}
