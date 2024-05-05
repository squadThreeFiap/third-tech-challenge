package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.dtos.AvaliacaoDto;

public interface AvaliacaoConverter {
    AvaliacaoDto toDto(Avaliacao avaliacao);

    Avaliacao toEntity(AvaliacaoDto avaliacaoDto, Restaurante restaurante);

    void updateEntityFromDto(Avaliacao avaliacao, AvaliacaoDto avaliacaoDto);
}