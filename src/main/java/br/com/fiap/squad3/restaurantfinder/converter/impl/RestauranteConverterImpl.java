package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.RestauranteConverter;
import br.com.fiap.squad3.restaurantfinder.model.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.model.dtos.RestauranteDto;
import org.springframework.stereotype.Component;

@Component
public class RestauranteConverterImpl implements RestauranteConverter {

    @Override
    public RestauranteDto toDto(RestauranteEntity restauranteEntity) {
        return new RestauranteDto(
                restauranteEntity.getId(),
                restauranteEntity.getCnpjCpf(),
                restauranteEntity.getNomeFantasia(),
                restauranteEntity.getDdd(),
                restauranteEntity.getTelefoneContato(),
                restauranteEntity.getEmailContato(),
                restauranteEntity.getCulinaria(),
                restauranteEntity.getCategoria(),
                restauranteEntity.getCep(),
                restauranteEntity.getEndereco(),
                restauranteEntity.getUf(),
                restauranteEntity.getCidade(),
                restauranteEntity.getBairro(),
                restauranteEntity.getDiasFuncionamentos(),
                restauranteEntity.getHoraAbertura(),
                restauranteEntity.getHoraEnceramento(),
                restauranteEntity.getCapacidade()
        );
    }

    @Override
    public RestauranteEntity toEntity(RestauranteDto restauranteDto) {
        return new RestauranteEntity(
                restauranteDto.id(),
                restauranteDto.cnpjcpf(),
                restauranteDto.nomeFatasia(),
                restauranteDto.ddd(),
                restauranteDto.telefone(),
                restauranteDto.email(),
                restauranteDto.culinaria(),
                restauranteDto.categoria(),
                restauranteDto.cep(),
                restauranteDto.endereco(),
                restauranteDto.uf(),
                restauranteDto.cidade(),
                restauranteDto.bairro(),
                restauranteDto.diasFuncionamentos(),
                restauranteDto.horaAbertura(),
                restauranteDto.horaEnceramento(),
                restauranteDto.capacidade()
        );
    }

    @Override
    public void updateEntityFromDto(RestauranteEntity restauranteEntity, RestauranteDto restauranteDto) {
        restauranteEntity.setCnpjCpf(restauranteDto.cnpjcpf());
        restauranteEntity.setNomeFantasia(restauranteDto.nomeFatasia());
        restauranteEntity.setTelefoneContato(restauranteDto.telefone());
        restauranteEntity.setEmailContato(restauranteDto.email());
        restauranteEntity.setCulinaria(restauranteDto.culinaria());
        restauranteEntity.setCategoria(restauranteDto.categoria());
        restauranteEntity.setCep(restauranteDto.cep());
        restauranteEntity.setEndereco(restauranteDto.endereco());
        restauranteEntity.setCidade(restauranteDto.cidade());
        restauranteEntity.setBairro(restauranteDto.bairro());
        restauranteEntity.setDiasFuncionamentos(restauranteDto.diasFuncionamentos());
        restauranteEntity.setHoraAbertura(restauranteDto.horaAbertura());
        restauranteEntity.setHoraEnceramento(restauranteDto.horaEnceramento());
        restauranteEntity.setCapacidade(restauranteDto.capacidade());
    }
}