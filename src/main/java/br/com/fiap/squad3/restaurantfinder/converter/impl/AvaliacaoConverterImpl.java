package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.AvaliacaoConverter;
import br.com.fiap.squad3.restaurantfinder.model.AvaliacaoEntity;
import br.com.fiap.squad3.restaurantfinder.model.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.model.dtos.AvaliacaoDto;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoConverterImpl implements AvaliacaoConverter {
    @Override
    public AvaliacaoDto toDto(AvaliacaoEntity avaliacaoEntity) {
        return new AvaliacaoDto(
                avaliacaoEntity.getId(),
                avaliacaoEntity.getNome(),
                avaliacaoEntity.getComentario(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getRestauranteEntity().getId()
        );
    }

    @Override
    public AvaliacaoEntity toEntity(AvaliacaoDto avaliacaoDto, RestauranteEntity restauranteEntity) {
        return new AvaliacaoEntity(
                avaliacaoDto.id(),
                avaliacaoDto.nome(),
                avaliacaoDto.comentario(),
                avaliacaoDto.nota(),
                restauranteEntity
        );
    }

    @Override
    public void updateEntityFromDto(AvaliacaoEntity avaliacaoEntity, AvaliacaoDto avaliacaoDto) {
        avaliacaoEntity.setComentario(avaliacaoDto.comentario());
        avaliacaoEntity.setNota(avaliacaoDto.nota());
    }
}