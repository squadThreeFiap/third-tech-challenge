package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoDtoConverter implements DtoConverter<AvaliacaoRequestDto, Avaliacao, AvaliacaoResponseDto> {

    @Override
    public AvaliacaoResponseDto toResponse(Avaliacao domainObj) {
        return new AvaliacaoResponseDto(domainObj.getId());
    }

    @Override
    public Avaliacao toDomain(AvaliacaoRequestDto avaliacaoRequestDto) {
        return new Avaliacao(
                avaliacaoRequestDto.reservaId(),
                avaliacaoRequestDto.nota(),
                avaliacaoRequestDto.comentario()
        );
    }

    @Override
    public void updateDomainFromDto(Avaliacao domainObj, AvaliacaoRequestDto avaliacaoRequestDto) {
        domainObj.setNota(avaliacaoRequestDto.nota());
        domainObj.setComentario(avaliacaoRequestDto.comentario());
    }
}