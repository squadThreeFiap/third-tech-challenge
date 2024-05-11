package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.Set;

public record RestauranteRequestDto(
        @Schema(hidden = true)
        Long id,

        @Schema(example = "Restaurant Finder Ltda")
        @NotBlank(message = "Nome não pode estar em branco.")
        @Size(min = 5, max = 100)
        String nome,

        @NotBlank
        String tipoCozinha,

        @Schema(example = "100")
        Integer numero,

        @Schema(example = "06150347")
        @NotBlank
        @Pattern(regexp = "^(?!.*(\\d)\\1{4,})\\d{5}\\d{3}$", message = "CEP inválido.")
        String cep,

        @NotBlank(message = "Endereco não pode estar em branco.")
        String endereco,

        @NotBlank(message = "Estado não pode estar em branco.")
        String uf,

        @NotBlank(message = "Cidade não pode estar em branco.")
        String cidade,

        @NotBlank(message = "Bairro não pode estar em branco.")
        String bairro,

        @Enumerated(EnumType.STRING)
        Set<DiaSemana> diasFuncionamentos,

        @Schema(example = "07:00:00")
        LocalTime horaAbertura,

        @Schema(example = "20:00:00")
        LocalTime horaEnceramento,

        @Min(value = 1)
        int capacidade
) {
}