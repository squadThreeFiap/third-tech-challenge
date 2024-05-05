package br.com.fiap.squad3.restaurantfinder.model.dtos;

public record RestauranteFiltroDto(
        String nome,
        String culinaria,
        String estado,
        String cidade,
        String bairro
) {
}
