package br.com.fiap.squad3.restaurantfinder.repository;

import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCpf(String cpf);

}