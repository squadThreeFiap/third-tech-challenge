package br.com.fiap.squad3.restaurantfinder.repository;

import br.com.fiap.squad3.restaurantfinder.model.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    boolean existsByUsuarioIdAndDataHoraInicio(Long id, LocalDateTime dataHoraInicio);

}
