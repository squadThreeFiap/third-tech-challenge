package br.com.fiap.squad3.restaurantfinder.repository;

import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    boolean existsByCnpjCpf(String cnpjcpf);

    Optional<Restaurante> findByNomeFantasia(String nome);

    Optional<Restaurante> findByCulinaria(String culinaria);

    Optional<Restaurante> findByCidade(String localizacao);

}
