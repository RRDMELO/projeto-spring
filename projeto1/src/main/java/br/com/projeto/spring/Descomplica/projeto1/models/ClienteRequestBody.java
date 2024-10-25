package br.com.projeto.spring.Descomplica.projeto1.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

public record ClienteRequestBody(
        @Size(min = 11, max = 11) @NotBlank String cpf,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotNull @Past LocalDate dataNascimento) {
}
