package external.restapi.dtos;

import br.com.fiap.squad3.restaurantfinder.model.enums.DiaSemana;
import br.com.fiap.squad3.restaurantfinder.util.CpfCnpj;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.List;

public record RestauranteDto(
        @Schema(hidden = true)
        long id,

        @Schema(example = "99882681000126")
        @CpfCnpj(message = "CPF/CNPJ inválido")
        String cnpjcpf,

        @Schema(example = "Restaurant Finder Ltda")
        @NotBlank(message = "nomeFatasia não pode ser branco")
        @Size(min = 5, max = 100)
        String nomeFatasia,

        @Schema(example = "11")
        @Pattern(regexp = "\\d{2}", message = "DDD inválido. Deve conter exatamente 2 dígitos.")
        String ddd,

        @Schema(example = "986417485")
        @Pattern(regexp = "^(?!\\d*(\\d)\\1{7,8})\\d{8,9}$", message = "Telefone inválido")
        String telefone,

        @Schema(example = "contato@restaurantfinder.com")
        @Email(message = "Email invalido")
        String email,

        @NotBlank
        String culinaria,

        @NotBlank
        String categoria,

        @Schema(example = "06150347")
        @NotBlank
        @Pattern(regexp = "^(?!.*(\\d)\\1{4,})\\d{5}\\d{3}$", message = "cep inválido")
        String cep,

        @NotBlank(message = "endereco não pode ser branco")
        String endereco,

        @NotBlank(message = "Estadao não pode ser branco")
        String uf,

        @NotBlank(message = "cidade não pode ser branco")
        String cidade,

        @NotBlank(message = "bairro não pode ser branco")
        String bairro,

        @Enumerated(EnumType.STRING)
        List<DiaSemana> diasFuncionamentos,

        @Schema(example = "07:00:00")
        LocalTime horaAbertura,

        @Schema(example = "20:00:00")
        LocalTime horaEnceramento,

        @Min(value = 1)
        int capacidade
) {
}