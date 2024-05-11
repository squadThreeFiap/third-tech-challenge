//package br.com.fiap.squad3.restaurantfinder.external.restapi.services.impl;
//
//import br.com.fiap.squad3.restaurantfinder.external.restapi.services.RestauranteService;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.exception.ControllerNotFoundException;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.converter.RestauranteConverter;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;
//import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RestauranteServiceImpl implements RestauranteService {
//
//    private final RestauranteRepository restauranteRepository;
//    private final RestauranteConverter restauranteConverter;
//
//    @Autowired
//    public RestauranteServiceImpl(
//            RestauranteRepository restauranteRepository,
//            RestauranteConverter restauranteConverter
//    ) {
//        this.restauranteRepository = restauranteRepository;
//        this.restauranteConverter = restauranteConverter;
//    }
//
//    @Override
//    public RestauranteDto save(RestauranteDto restauranteDto) {
//        if (restauranteRepository.existsByCnpjCpf(restauranteDto.cnpjcpf())) {
//            throw new ControllerNotFoundException("Restaurante com documento " + restauranteDto.cnpjcpf() + " já existe.");
//        }
//        var restaurante = restauranteConverter.toEntity(restauranteDto);
//        restaurante = restauranteRepository.save(restaurante);
//
//		return restauranteConverter.toDto(restaurante);
//    }
//
//    @Override
//    public RestauranteDto update(Long id, RestauranteDto restauranteDto) {
//        var restaurante = restauranteRepository.findById(id)
//                .orElseThrow(() -> new ControllerNotFoundException("Restaurante não encontrado"));
//        restauranteConverter.updateEntityFromDto(restaurante, restauranteDto);
//        restaurante = restauranteRepository.save(restaurante);
//
//        return restauranteConverter.toDto(restaurante);
//    }
//
//    @Override
//    public void delete(Long id) {
//        restauranteRepository.deleteById(id);
//    }
//
//    @Override
//    public RestauranteDto findById(Long id) {
//        var restaurante = restauranteRepository.findById(id)
//                .orElseThrow(() -> new ControllerNotFoundException("Restaurante não encontrado"));
//        return restauranteConverter.toDto(restaurante);
//    }
//
//    @Override
//    public Page<RestauranteDto> findAll(Pageable pageable) {
//        return restauranteRepository
//                .findAll(pageable)
//                .map(restauranteConverter::toDto);
//    }
//}