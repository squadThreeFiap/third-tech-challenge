//package br.com.fiap.squad3.restaurantfinder.external.restapi.converter.impl;
//
//import br.com.fiap.squad3.restaurantfinder.external.restapi.converter.ReservaConverter;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ReservaConverterImpl {
//    public ReservaDto toDto(ReservaEntity reservaEntity) {
//        return new ReservaDto(
//                reservaEntity.getId(),
//                reservaEntity.getUsuarioEntity().getId(),
//                reservaEntity.getRestauranteEntity().getId(),
//                reservaEntity.getQuantidadePessoas(),
//                reservaEntity.getDataHoraFim(),
//                reservaEntity.getDataHoraInicio(),
//                reservaEntity.getStatus()
//        );
//    }
//
//    public ReservaEntity toEntity(ReservaDto reservaDto, UsuarioEntity usuarioEntity, RestauranteEntity restauranteEntity) {
//        return new ReservaEntity(
//                reservaDto.id(),
//                usuarioEntity,
//                restauranteEntity,
//                reservaDto.quantidadePessoas(),
//                reservaDto.dataHoraFim(),
//                reservaDto.dataHoraInicio(),
//                reservaDto.status()
//        );
//    }
//
//    public void updateEntityFromDto(ReservaEntity reservaEntity, ReservaDto reservaDto) {
//		// TODO: Implement updateEntityFromDto
//    }
//}