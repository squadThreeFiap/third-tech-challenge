//package br.com.fiap.squad3.restaurantfinder.external.restapi.services.impl;
//
//import br.com.fiap.squad3.restaurantfinder.external.restapi.services.UsuarioService;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.exception.ControllerNotFoundException;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.converter.UsuarioConverter;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioDto;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsuarioServiceImpl implements UsuarioService {
//    private final UsuarioRepository usuarioRepository;
//    private final UsuarioConverter usuarioConverter;
//
//    @Autowired
//    public UsuarioServiceImpl(
//            UsuarioRepository usuarioRepository,
//            UsuarioConverter usuarioConverter
//    ) {
//        this.usuarioRepository = usuarioRepository;
//        this.usuarioConverter = usuarioConverter;
//    }
//
//    @Override
//    public UsuarioDto save(UsuarioDto usuarioDto) {
//        if (usuarioRepository.existsByCpf(usuarioDto.cpf())) {
//            throw new ControllerNotFoundException("Usuário com CPF " + usuarioDto.cpf() + " já existe.");
//        }
//
//		var usuario = usuarioConverter.toEntity(usuarioDto);
//        usuario = usuarioRepository.save(usuario);
//
//		return usuarioConverter.toDto(usuario);
//    }
//
//    @Override
//    public UsuarioDto update(Long id, UsuarioDto usuarioDto) {
//        var usuario = usuarioRepository.findById(id)
//                .orElseThrow(() -> new ControllerNotFoundException("Usuario não encontrado"));
//
//		usuarioConverter.updateEntityFromDto(usuario, usuarioDto);
//        usuario = usuarioRepository.save(usuario);
//
//		return usuarioConverter.toDto(usuario);
//    }
//
//    @Override
//    public void delete(Long id) {
//        usuarioRepository.deleteById(id);
//    }
//
//    @Override
//    public UsuarioDto findById(Long id) {
//        var usuario = usuarioRepository.findById(id)
//                .orElseThrow(() -> new ControllerNotFoundException("Usuario não encontrado"));
//
//        return usuarioConverter.toDto(usuario);
//    }
//
//    @Override
//    public Page<UsuarioDto> findAll(Pageable pageable) {
//        return usuarioRepository
//                .findAll(pageable)
//                .map(usuarioConverter::toDto);
//    }
//}