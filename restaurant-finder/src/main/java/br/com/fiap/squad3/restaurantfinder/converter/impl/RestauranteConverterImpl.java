package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.RestauranteConverter;
import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.dtos.RestauranteDto;
import org.springframework.stereotype.Component;

@Component
public class RestauranteConverterImpl implements RestauranteConverter {

    @Override
    public RestauranteDto toDto(Restaurante restaurante) {
        return new RestauranteDto(
                restaurante.getId(),
                restaurante.getCnpjCpf(),
                restaurante.getNomeFantasia(),
                restaurante.getDdd(),
                restaurante.getTelefoneContato(),
                restaurante.getEmailContato(),
                restaurante.getCulinaria(),
                restaurante.getCategoria(),
                restaurante.getCep(),
                restaurante.getEndereco(),
                restaurante.getUf(),
                restaurante.getCidade(),
                restaurante.getBairro(),
                restaurante.getDiasFuncionamentos(),
                restaurante.getHoraAbertura(),
                restaurante.getHoraEnceramento(),
                restaurante.getCapacidade()
        );
    }

    @Override
    public Restaurante toEntity(RestauranteDto restauranteDto) {
        return new Restaurante(
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
    public void updateEntityFromDto(Restaurante restaurante, RestauranteDto restauranteDto) {
        restaurante.setCnpjCpf(restauranteDto.cnpjcpf());
        restaurante.setNomeFantasia(restauranteDto.nomeFatasia());
        restaurante.setTelefoneContato(restauranteDto.telefone());
        restaurante.setEmailContato(restauranteDto.email());
        restaurante.setCulinaria(restauranteDto.culinaria());
        restaurante.setCategoria(restauranteDto.categoria());
        restaurante.setCep(restauranteDto.cep());
        restaurante.setEndereco(restauranteDto.endereco());
        restaurante.setCidade(restauranteDto.cidade());
        restaurante.setBairro(restauranteDto.bairro());
        restaurante.setDiasFuncionamentos(restauranteDto.diasFuncionamentos());
        restaurante.setHoraAbertura(restauranteDto.horaAbertura());
        restaurante.setHoraEnceramento(restauranteDto.horaEnceramento());
        restaurante.setCapacidade(restauranteDto.capacidade());
    }
}