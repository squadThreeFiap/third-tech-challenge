package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.ReservaConverter;
import external.database.jpa.entities.ReservaEntity;
import external.database.jpa.entities.RestauranteEntity;
import external.database.jpa.entities.UsuarioEntity;
import external.restapi.dtos.ReservaDto;
import org.springframework.stereotype.Component;

@Component
public class ReservaConverterImpl implements ReservaConverter {
    @Override
    public ReservaDto toDto(ReservaEntity reservaEntity) {
        return new ReservaDto(
                reservaEntity.getId(),
                reservaEntity.getUsuarioEntity().getId(),
                reservaEntity.getRestauranteEntity().getId(),
                reservaEntity.getQuantidadePessoas(),
                reservaEntity.getDataHoraFim(),
                reservaEntity.getDataHoraInicio(),
                reservaEntity.getStatus()
        );
    }

    @Override
    public ReservaEntity toEntity(ReservaDto reservaDto, UsuarioEntity usuarioEntity, RestauranteEntity restauranteEntity) {
        return new ReservaEntity(
                reservaDto.id(),
                usuarioEntity,
                restauranteEntity,
                reservaDto.quantidadePessoas(),
                reservaDto.dataHoraFim(),
                reservaDto.dataHoraInicio(),
                reservaDto.status()
        );
    }

    @Override
    public void updateEntityFromDto(ReservaEntity reservaEntity, ReservaDto reservaDto) {
		// TODO: Implement updateEntityFromDto
    }
}