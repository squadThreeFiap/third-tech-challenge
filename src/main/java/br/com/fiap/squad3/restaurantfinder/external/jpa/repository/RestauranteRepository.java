package br.com.fiap.squad3.restaurantfinder.external.jpa.repository;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}
