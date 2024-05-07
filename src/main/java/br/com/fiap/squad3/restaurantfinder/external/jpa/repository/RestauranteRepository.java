//package br.com.fiap.squad3.restaurantfinder.external.jpa.repository;
//
//import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
//    boolean existsByCnpjCpf(String cnpjcpf);
//
//    Optional<RestauranteEntity> findByNomeFantasia(String nome);
//
//    Optional<RestauranteEntity> findByCulinaria(String culinaria);
//
//    Optional<RestauranteEntity> findByCidade(String localizacao);
//
//}
