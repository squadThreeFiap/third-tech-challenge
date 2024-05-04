package br.com.fiap.squad3.restaurantfinder.service;

import br.com.fiap.squad3.restaurantfinder.model.dtos.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    UsuarioDto save(UsuarioDto usuarioDto);

    UsuarioDto update(Long id, UsuarioDto usuarioDto);

    void delete(Long id);

    UsuarioDto findById(Long id);

    Page<UsuarioDto> findAll(Pageable pageable);
}