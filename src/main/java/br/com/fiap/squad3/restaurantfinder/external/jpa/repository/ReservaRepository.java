package br.com.fiap.squad3.restaurantfinder.external.jpa.repository;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    @Query("SELECT r FROM ReservaEntity r WHERE " +
            "r.restauranteEntity.id = :idRestaurante AND " + // Somente reservas do restaurante desejado
            "(r.dataHoraInicio >= :inicio AND r.dataHoraFim <= :fim) AND " + // Intervalo desejado
            "(r.status = 'ANDAMENTO' OR r.status = 'AGENDADO')" // Apenas status que impossibilitariam novas reservas
    )
    List<ReservaEntity> findAllOccupiedReservationBetweenDates(
            @Param("idRestaurante") Long idRestaurante,
            @Param("inicio") LocalDateTime dataHoraInicio,
            @Param("fim") LocalDateTime dataHoraFim
    );
}