//package br.com.fiap.squad3.restaurantfinder.external.restapi.converter.impl;
//
//import br.com.fiap.squad3.restaurantfinder.external.restapi.converter.UsuarioConverter;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UsuarioConverterImpl {
//
//    public UsuarioDto toDto(UsuarioEntity usuarioEntity) {
//        return new UsuarioDto(
//                usuarioEntity.getId(),
//                usuarioEntity.getCpf(),
//                usuarioEntity.getNome(),
//                usuarioEntity.getDdd(),
//                usuarioEntity.getTelefone(),
//                usuarioEntity.getEmail(),
//                usuarioEntity.getDataCadastro()
//        );
//    }
//
//    public UsuarioEntity toEntity(UsuarioDto usuarioDto) {
//        return new UsuarioEntity(
//                usuarioDto.id(),
//                usuarioDto.cpf(),
//                usuarioDto.nome(),
//                usuarioDto.ddd(),
//                usuarioDto.telefone(),
//                usuarioDto.email(),
//                usuarioDto.dataCadastro()
//        );
//    }
//
//    public void updateEntityFromDto(UsuarioEntity usuarioEntity, UsuarioDto usuarioDto) {
//        usuarioEntity.setNome(usuarioDto.nome());
//        usuarioEntity.setDdd(usuarioDto.ddd());
//        usuarioEntity.setTelefone(usuarioDto.telefone());
//        usuarioEntity.setEmail(usuarioDto.email());
//    }
//}