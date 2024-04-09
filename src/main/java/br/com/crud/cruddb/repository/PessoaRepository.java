package br.com.crud.cruddb.repository;

import br.com.crud.cruddb.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    boolean existsByCpf(String cpf);
    // Aqui, você pode definir métodos de consulta personalizados, se necessário.

    // Exemplo de um método de consulta personalizado:
    // List<Pessoa> findByNomeContaining(String nome);
}
