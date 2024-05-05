package br.com.fiap.squad3.restaurantfinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import external.restapi.controller.exception.ControllerNotFoundException;
import br.com.fiap.squad3.restaurantfinder.converter.AvaliacaoConverter;
import external.restapi.dtos.AvaliacaoDto;
import external.database.jpa.repository.AvaliacaoRepository;
import external.database.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.service.AvaliacaoService;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
	private final AvaliacaoRepository avaliacaoRepository;
	private final RestauranteRepository restauranteRepository;
	private final AvaliacaoConverter avaliacaoConverter;

	@Autowired
	public AvaliacaoServiceImpl(
			AvaliacaoRepository avaliacaoRepository,
			AvaliacaoConverter avaliacaoConverter,
			RestauranteRepository restauranteRepository
	) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.avaliacaoConverter = avaliacaoConverter;
		this.restauranteRepository = restauranteRepository;
	}

	@Override
	public AvaliacaoDto save(AvaliacaoDto avaliacaoDto) {
		var restaurante = restauranteRepository.findById(avaliacaoDto.restauranteId())
				.orElseThrow(() -> new ControllerNotFoundException("Restaurante não encontrado"));

		var avaliacao = avaliacaoConverter.toEntity(avaliacaoDto, restaurante);
		avaliacao = avaliacaoRepository.save(avaliacao);

		return avaliacaoConverter.toDto(avaliacao);
	}

	@Override
	public AvaliacaoDto update(Long id, AvaliacaoDto avaliacaoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public AvaliacaoDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AvaliacaoDto> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}