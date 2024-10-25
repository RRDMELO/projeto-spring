package br.com.projeto.spring.Descomplica.projeto1.Repository;

import br.com.projeto.spring.Descomplica.projeto1.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
