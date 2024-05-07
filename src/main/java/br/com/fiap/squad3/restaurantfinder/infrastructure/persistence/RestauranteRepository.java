package br.com.fiap.squad3.restaurantfinder.infrastructure.persistence;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}
