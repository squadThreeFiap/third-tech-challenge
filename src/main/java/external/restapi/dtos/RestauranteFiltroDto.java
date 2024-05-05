package external.restapi.dtos;

public record RestauranteFiltroDto(
        String nome,
        String culinaria,
        String estado,
        String cidade,
        String bairro
) {
}
