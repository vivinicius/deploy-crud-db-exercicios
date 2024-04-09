package br.com.crud.cruddb.repository;

import br.com.crud.cruddb.model.Pessoa;
import br.com.crud.cruddb.model.exception.exceptions.PessoaExceptions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepository_old extends PessoaExceptions {
    private Integer ultimoIdPessoa = 0;

    /**
     * Metodo para retornar lista de pessoas
     * @return Lista de produtos.
     */
    public List<Pessoa> obterTodos(){
        return pessoas;
    }
    /**
    * Metodo que retorna a pessoa encontarda por seu id.
    * @param id do produto que sera localizado.
    * @return Retorna um produto caso tenha encontrado.
    */
    public Optional<Pessoa> obterPorId(Integer id) {
    Optional<Pessoa> pessoaEncontrada = pessoas.stream()
            .filter(pessoa -> pessoa.getId().equals(id))
            .findFirst();
    
//    buscarPessoaPorIDExceptions(pessoaEncontrada);
    return pessoaEncontrada;
    }

    /**
    * Metodo para adicionar produto na lista.
    * @param pessoa que sera adicionada.
    * @return Retorna o produto que foi adicionado na lista.
    */
    public Pessoa adicionar(Pessoa pessoa){
        adicionarPessoasExceptions(pessoa);
        ultimoIdPessoa++;
        pessoa.setId(ultimoIdPessoa);
        pessoas.add(pessoa);
        return pessoa;
    }

    /**
     * Metodo para deletar um produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        Optional<Pessoa> pessoaEncontrada = pessoas.stream()
            .filter(pessoa -> pessoa.getId().equals(id))
            .findFirst();
//        buscarPessoaPorIDExceptions(pessoaEncontrada);
        pessoas.removeIf(pessoa -> pessoa.getId() == id);
    }
    
    /**
     * Metodo para atualizar pessoa na lista
     * @param pessoaAtualizada que sera atualizada.
     * @return Retorna a pessoa atualizada na lista.
     */
    public Pessoa atualizar(Pessoa pessoaAtualizada){
    Optional<Pessoa> optionalPessoa = obterPorId(pessoaAtualizada.getId());
        editarPessoasExceptions(pessoaAtualizada);
        Pessoa pessoaExistente = optionalPessoa.get();
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        editarPessoasExceptions(pessoaExistente);
        return pessoaExistente;
    }
}
