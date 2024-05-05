package br.com.fiap.squad3.restaurantfinder.repository;

import br.com.fiap.squad3.restaurantfinder.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    boolean existsByCpf(String cpf);

}