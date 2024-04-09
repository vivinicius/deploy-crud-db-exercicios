package br.com.crud.cruddb.model.exception.exceptions;

import br.com.crud.cruddb.model.Pessoa;
import br.com.crud.cruddb.model.exception.ResourceBadRequestException;
import br.com.crud.cruddb.model.exception.ResourceNotFoundException;
import br.com.crud.cruddb.repository.PessoaRepository;
import br.com.crud.cruddb.utils.CPFUtils;
import br.com.crud.cruddb.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaExceptions {

    @Autowired
    private PessoaRepository pessoaRepository;

    private DataUtils dataUtils = new DataUtils();
    private CPFUtils cpfUtils = new CPFUtils();
    public List<Pessoa> pessoas = new ArrayList<Pessoa>();
    
    public void adicionarPessoasExceptions(Pessoa pessoa){
        validateEmptyCpf(pessoa);
        validateCPFFormat(pessoa);
        pessoa.setCpf(cpfUtils.formatarCPF(pessoa.getCpf()));
        validateEmpytName(pessoa);
        validateRegisteredCPF(pessoa);
        validateBirthyDate(pessoa);
    }

    public void editarPessoasExceptions(Pessoa pessoa){
        validateEmptyCpf(pessoa);
        validateCPFFormat(pessoa);
        pessoa.setCpf(cpfUtils.formatarCPF(pessoa.getCpf()));
        validateEmpytName(pessoa);
        validateBirthyDate(pessoa);
    }

    public void adicionarEnderecoExceptions(Optional<Pessoa> pessoaEncontrada){
        if(pessoaEncontrada.isEmpty()){
            throw new ResourceNotFoundException("ID do usuário não encontrado");
        }
    }

    public void buscarPessoaPorIDExceptions(Integer id){
        if(!pessoaRepository.existsById(id)){
            throw new ResourceNotFoundException("ID do usuário não encontrado");
        }
    }

    public void atualizarDadosPessoaExceptions(Integer id,Pessoa pessoa){
        if(!pessoaRepository.existsById(id)){
            throw new ResourceNotFoundException("ID do usuário não encontrado");
        }
        validateEmptyCpf(pessoa);
        validateCPFFormat(pessoa);
        pessoa.setCpf(cpfUtils.formatarCPF(pessoa.getCpf()));
        validateEmpytName(pessoa);
        validateRegisteredCPF(pessoa);
        validateBirthyDate(pessoa);
    }


    public void validateEmptyCpf(Pessoa pessoa){
        if (pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {
            throw new ResourceBadRequestException("CPF não pode ser nulo ou vazio");
        }
    }

    public void validateCPFFormat(Pessoa pessoa){
        if(!cpfUtils.validarCPF(pessoa.getCpf())){
            throw new ResourceBadRequestException("CPF invalido");
        }
    }

    public void validateEmpytName(Pessoa pessoa){
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new ResourceBadRequestException("Nome não pode ser nulo ou vazio");
        }
    }

    public void validateRegisteredCPF(Pessoa pessoa){
        if (cpfJaCadastrado(pessoa.getCpf())) {
            throw new ResourceBadRequestException("CPF já cadastrado");
        }
    }

    public boolean cpfJaCadastrado(String cpf) {
        return pessoaRepository.existsByCpf(cpf);
    }

    public void validateBirthyDate(Pessoa pessoa){
        if (pessoa.getDataNascimento() != null) {
            if(!dataUtils.validarDataBrasileira(pessoa.getDataNascimento())){
                throw new ResourceBadRequestException("Data invalida formato dd/mm/yyyy \n (Data não é obrigatorio ser enviada)");
            }
        }
    }
}
