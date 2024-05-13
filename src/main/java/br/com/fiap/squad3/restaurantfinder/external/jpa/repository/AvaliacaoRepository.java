package br.com.fiap.squad3.restaurantfinder.external.jpa.repository;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {
    Optional<AvaliacaoEntity> findByReservaEntityId(Long idReserva);
}