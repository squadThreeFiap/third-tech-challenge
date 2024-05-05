package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.Reserva;
import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import br.com.fiap.squad3.restaurantfinder.model.dtos.ReservaDto;

public interface ReservaConverter {
    ReservaDto toDto(Reserva reserva);

    Reserva toEntity(ReservaDto reservaDto, Usuario usuario, Restaurante restaurante);

    void updateEntityFromDto(Reserva reserva, ReservaDto reservaDto);
}