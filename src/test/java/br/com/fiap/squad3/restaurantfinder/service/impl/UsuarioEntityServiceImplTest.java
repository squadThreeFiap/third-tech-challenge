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

import external.restapi.controller.exception.ControllerNotFoundException;
import br.com.fiap.squad3.restaurantfinder.converter.UsuarioConverter;
import external.database.jpa.entities.UsuarioEntity;
import external.restapi.dtos.UsuarioDto;
import external.database.jpa.repository.UsuarioRepository;

public class UsuarioEntityServiceImplTest {

	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private UsuarioConverter usuarioConverter;
	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	private UsuarioEntity usuarioEntity;
	private UsuarioDto usuarioDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		usuarioEntity = new UsuarioEntity(null, "33014076090", "Restaurant Finder", "11", "987654321", "teste@teste.com", null);
		usuarioDto = new UsuarioDto(null, "33014076090", "Restaurant Finder", "11", "987654321", "teste@teste.com",
				null);

		when(usuarioConverter.toEntity(any(UsuarioDto.class))).thenReturn(usuarioEntity);
		when(usuarioConverter.toDto(any(UsuarioEntity.class))).thenReturn(usuarioDto);
	}

	
	@Test
    void saveShouldPersistData() {
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        UsuarioDto savedDto = usuarioService.save(usuarioDto);

        assertNotNull(savedDto);
        assertEquals("Restaurant Finder", savedDto.nome());
        verify(usuarioRepository).save(usuarioEntity);
    }

	@Test
    void updateShouldChangeData() {
	    when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
	    when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
	    when(usuarioConverter.toDto(any(UsuarioEntity.class))).thenReturn(usuarioDto);

	    UsuarioDto updatedDto = usuarioService.update(1L, usuarioDto);
        assertNotNull(updatedDto);
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

	@Test
	void deleteShouldRemoveData() {
		doNothing().when(usuarioRepository).deleteById(anyLong());
		usuarioService.delete(1L);
		verify(usuarioRepository).deleteById(anyLong());
	}

	@Test
    void findByIdShouldReturnData() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
        when(usuarioConverter.toDto(any(UsuarioEntity.class))).thenReturn(usuarioDto);

        UsuarioDto foundDto = usuarioService.findById(1L);

        assertNotNull(foundDto);
        verify(usuarioRepository).findById(anyLong());
    }

	@Test
	void findAllShouldReturnPage() {
		Page<UsuarioEntity> page = new PageImpl<>(List.of(usuarioEntity));
		when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
		when(usuarioConverter.toDto(any(UsuarioEntity.class))).thenReturn(usuarioDto);

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
		UsuarioEntity mockUsuarioEntity = new UsuarioEntity();
		Page<UsuarioEntity> page = new PageImpl<>(Collections.singletonList(mockUsuarioEntity));
		when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
		assertEquals(1, usuarioService.findAll(Pageable.unpaged()).getTotalElements());
	}

}