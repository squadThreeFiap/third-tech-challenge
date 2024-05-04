package br.com.fiap.squad3.restaurantfinder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.fiap.squad3.restaurantfinder.controller.exception.ControllerNotFoundException;
import br.com.fiap.squad3.restaurantfinder.converter.UsuarioConverter;
import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import br.com.fiap.squad3.restaurantfinder.model.dtos.UsuarioDto;
import br.com.fiap.squad3.restaurantfinder.repository.UsuarioRepository;

public class UsuarioServiceImplTest {

	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private UsuarioConverter usuarioConverter;
	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	private Usuario usuario;
	private UsuarioDto usuarioDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		usuario = new Usuario(null, "33014076090", "Restaurant Finder", "11", "987654321", "teste@teste.com", null);
		usuarioDto = new UsuarioDto(null, "33014076090", "Restaurant Finder", "11", "987654321", "teste@teste.com",
				null);

		when(usuarioConverter.toEntity(any(UsuarioDto.class))).thenReturn(usuario);
		when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDto);
	}

	
	@Test
    void saveShouldPersistData() {
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDto savedDto = usuarioService.save(usuarioDto);

        assertNotNull(savedDto);
        assertEquals("Restaurant Finder", savedDto.nome());
        verify(usuarioRepository).save(usuario);
    }

	@Test
    void updateShouldChangeData() {
	    when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
	    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
	    when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDto);

	    UsuarioDto updatedDto = usuarioService.update(1L, usuarioDto);
        assertNotNull(updatedDto);
        verify(usuarioRepository).save(any(Usuario.class));
    }

	@Test
	void deleteShouldRemoveData() {
		doNothing().when(usuarioRepository).deleteById(anyLong());
		usuarioService.delete(1L);
		verify(usuarioRepository).deleteById(anyLong());
	}

	@Test
    void findByIdShouldReturnData() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDto);

        UsuarioDto foundDto = usuarioService.findById(1L);

        assertNotNull(foundDto);
        verify(usuarioRepository).findById(anyLong());
    }

	@Test
	void findAllShouldReturnPage() {
		Page<Usuario> page = new PageImpl<>(List.of(usuario));
		when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
		when(usuarioConverter.toDto(any(Usuario.class))).thenReturn(usuarioDto);

		Page<UsuarioDto> dtoPage = usuarioService.findAll(Pageable.unpaged());

		assertNotNull(dtoPage);
		assertFalse(dtoPage.isEmpty());
		verify(usuarioRepository).findAll(any(Pageable.class));
	}

	@Test
    void saveShouldThrowExceptionWhenCpfExists() {
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(ControllerNotFoundException.class, () -> usuarioService.save(usuarioDto));
    }

	@Test
    void updateShouldThrowNotFoundExceptionWhenUserDoesNotExist() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> usuarioService.update(1L, usuarioDto));
    }

	@Test
    void findByIdShouldThrowNotFoundExceptionWhenUserDoesNotExist() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> usuarioService.findById(1L));
    }

	@Test
    void findById_WhenUserDoesNotExist_ThrowsException() {
        when(usuarioRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());
        assertThrows(ControllerNotFoundException.class, () -> usuarioService.findById(1L));
    }

	@Test
	void findAll_WhenUsersExist_ReturnsPageOfUsers() {
		Usuario mockUsuario = new Usuario();
		Page<Usuario> page = new PageImpl<>(Collections.singletonList(mockUsuario));
		when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
		assertEquals(1, usuarioService.findAll(Pageable.unpaged()).getTotalElements());
	}

}