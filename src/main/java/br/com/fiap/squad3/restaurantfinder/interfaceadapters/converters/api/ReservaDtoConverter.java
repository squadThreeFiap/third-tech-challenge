package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDetalhadaResponseDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaResponsetDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioReservaDetalhadaDto;
import org.springframework.stereotype.Component;

@Component
public class ReservaDtoConverter implements DtoConverter<ReservaRequestDto, Reserva, ReservaResponsetDto> {
    @Override
    public ReservaResponsetDto toResponse(Reserva domainObj) {
        return new ReservaResponsetDto(domainObj.getId(), domainObj.getStatus());
    }

    public ReservaDetalhadaResponseDto toDetailedResponse(ReservaDetalhada domainObj) {
        return new ReservaDetalhadaResponseDto(
                domainObj.getId(),
                new UsuarioReservaDetalhadaDto(
                        domainObj.getUsuario().getId(),
                        domainObj.getUsuario().getNome(),
                        domainObj.getUsuario().getTelefone(),
                        domainObj.getUsuario().getEmail()
                ),
                domainObj.getQuantidadePessoas(),
                domainObj.getDataHoraInicio(),
                domainObj.getDataHoraFim(),
                domainObj.getStatus()
        );
    }

    @Override
    public Reserva toDomain(ReservaRequestDto reservaRequestDto) {
        return new Reserva(
                reservaRequestDto.idUsuario(),
                reservaRequestDto.idRestaurante(),
                reservaRequestDto.quantidadePessoas(),
                reservaRequestDto.dataHoraInicio(),
                reservaRequestDto.dataHoraFim()
        );
    }

    @Override
    public void updateDomainFromDto(Reserva domainObj, ReservaRequestDto reservaRequestDto) {
        domainObj.setIdUsuario(reservaRequestDto.idUsuario());
        domainObj.setIdRestaurante(reservaRequestDto.idRestaurante());
        domainObj.setQuantidadePessoas(reservaRequestDto.quantidadePessoas());
        domainObj.setDataHoraInicio(reservaRequestDto.dataHoraInicio());
        domainObj.setDataHoraFim(reservaRequestDto.dataHoraFim());
    }
}