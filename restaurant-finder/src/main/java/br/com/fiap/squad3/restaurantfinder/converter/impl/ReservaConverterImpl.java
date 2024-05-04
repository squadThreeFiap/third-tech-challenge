package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.ReservaConverter;
import br.com.fiap.squad3.restaurantfinder.model.Reserva;
import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import br.com.fiap.squad3.restaurantfinder.model.dtos.ReservaDto;
import org.springframework.stereotype.Component;

@Component
public class ReservaConverterImpl implements ReservaConverter {
    @Override
    public ReservaDto toDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getId(),
                reserva.getUsuario().getId(),
                reserva.getRestaurante().getId(),
                reserva.getQuantidadePessoas(),
                reserva.getDataHoraFim(),
                reserva.getDataHoraInicio(),
                reserva.getStatus()
        );
    }

    @Override
    public Reserva toEntity(ReservaDto reservaDto, Usuario usuario, Restaurante restaurante) {
        return new Reserva(
                reservaDto.id(),
                usuario,
                restaurante,
                reservaDto.quantidadePessoas(),
                reservaDto.dataHoraFim(),
                reservaDto.dataHoraInicio(),
                reservaDto.status()
        );
    }

    @Override
    public void updateEntityFromDto(Reserva reserva, ReservaDto reservaDto) {
		// TODO: Implement updateEntityFromDto
    }
}