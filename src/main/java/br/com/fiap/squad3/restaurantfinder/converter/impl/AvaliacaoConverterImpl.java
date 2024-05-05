package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.AvaliacaoConverter;
import br.com.fiap.squad3.restaurantfinder.model.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.dtos.AvaliacaoDto;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoConverterImpl implements AvaliacaoConverter {
    @Override
    public AvaliacaoDto toDto(Avaliacao avaliacao) {
        return new AvaliacaoDto(
                avaliacao.getId(),
                avaliacao.getNome(),
                avaliacao.getComentario(),
                avaliacao.getNota(),
                avaliacao.getRestaurante().getId()
        );
    }

    @Override
    public Avaliacao toEntity(AvaliacaoDto avaliacaoDto, Restaurante restaurante) {
        return new Avaliacao(
                avaliacaoDto.id(),
                avaliacaoDto.nome(),
                avaliacaoDto.comentario(),
                avaliacaoDto.nota(),
                restaurante
        );
    }

    @Override
    public void updateEntityFromDto(Avaliacao avaliacao, AvaliacaoDto avaliacaoDto) {
        avaliacao.setComentario(avaliacaoDto.comentario());
        avaliacao.setNota(avaliacaoDto.nota());
    }
}