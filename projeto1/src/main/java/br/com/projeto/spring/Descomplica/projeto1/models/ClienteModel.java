package br.com.projeto.spring.Descomplica.projeto1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

}
