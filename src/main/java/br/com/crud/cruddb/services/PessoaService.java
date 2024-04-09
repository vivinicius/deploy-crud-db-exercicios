package br.com.crud.cruddb.services;

import br.com.crud.cruddb.model.Pessoa;
import br.com.crud.cruddb.model.exception.exceptions.PessoaExceptions;
import br.com.crud.cruddb.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.crud.cruddb.shared.PessoaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaExceptions pessoaExceptions;


    public List<PessoaDTO> obterTodos() {


       List<Pessoa> pessoas = pessoaRepository.findAll();

       return pessoas.stream()
               .map(pessoa -> new ModelMapper().map(pessoa,PessoaDTO.class))
               .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> obterPorId(Integer id) {
        //Obtendo pessoa por id.
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        //Validando se o ID existe na base.
        pessoaExceptions.buscarPessoaPorIDExceptions(id);
        //Convertendo o optional de pessoa em uma pessoaDTO.
        PessoaDTO pessoaDTO = new ModelMapper().map(pessoa.get(), PessoaDTO.class);
        //Criando e retornando um optional de produtoDto.
        return Optional.of(pessoaDTO);
    }

    public PessoaDTO adicionar(PessoaDTO pessoaDto) {
        //Remove o id para conseguir fazer o cadastro.
        pessoaDto.setId(null);
        //Cria um objeto para mapeamento.
        ModelMapper modelMapper = new ModelMapper();
        //Converte o PessoaDTO em um Pessoa.
        Pessoa pessoa = modelMapper.map(pessoaDto,Pessoa.class);
        //Faz as validaçoes de campos.
        pessoaExceptions.adicionarPessoasExceptions(pessoa);
        //Salva a pessoa no banco.
        pessoa = pessoaRepository.save(pessoa);
        //Seta o id da pessoa.
        pessoaDto.setId(pessoa.getId());
        //Retorna a PessoaDTO atualizada.
        return pessoaDto;
    }

    public void deletar(Integer id) {
        //Verificar se a pessoa existe.
        pessoaExceptions.buscarPessoaPorIDExceptions(id);

        pessoaRepository.deleteById(id);
    }

    public PessoaDTO atualizar(Integer id, PessoaDTO pessoaDto) {
        //Passar o id para a PessoaDTO.
        pessoaDto.setId(id);
        //Criar um objeto de mapeamento.
        ModelMapper modelMapper = new ModelMapper();
        //Converter uma PessoaDTO em uma Pessoa.
        Pessoa pessoa = modelMapper.map(pessoaDto,Pessoa.class);
        //Validacoes de campos
        pessoaExceptions.atualizarDadosPessoaExceptions(id,pessoa);
        //Atualizar a pessoa no banco.
        pessoaRepository.save(pessoa);
        //Retornar a PessoaDTO atualizada.
        return pessoaDto;
    }
}
