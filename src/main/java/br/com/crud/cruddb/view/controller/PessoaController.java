package br.com.crud.cruddb.view.controller;

import br.com.crud.cruddb.model.Pessoa;
import br.com.crud.cruddb.services.PessoaService;
import br.com.crud.cruddb.shared.PessoaDTO;
import br.com.crud.cruddb.view.model.PessoaRequest;
import br.com.crud.cruddb.view.model.PessoaResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Buscar usuários",
    description = "<b>Buscar todos usuário cadastrados</b>",
    tags = "Usuário")
    @GetMapping
    public ResponseEntity<List<PessoaResponse>> obterTodos(){
        List<PessoaDTO> pessoas = pessoaService.obterTodos();
        ModelMapper modelMapper = new ModelMapper();
        List<PessoaResponse> resposta = pessoas.stream()
                .map(pessoaDto -> modelMapper.map(pessoaDto,PessoaResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
    
    @Operation(summary = "Buscar usuário",
    description = "<b>Buscar um usuário passando seu ID</b>",
    tags = "Usuário")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PessoaResponse>> obterPorId(@PathVariable Integer id){
        Optional<PessoaDTO> pessoaDTO = pessoaService.obterPorId(id);
        PessoaResponse pessoa = new ModelMapper().map(pessoaDTO.get(),PessoaResponse.class);
        return new ResponseEntity<>(Optional.of(pessoa),HttpStatus.OK);
    }

    @Operation(summary = "Criar usuário",
    description = "<b>Cadastrar um novo usuário</b>",
    tags = "Usuário")
    @PostMapping
    public ResponseEntity<PessoaResponse> adicionar(@RequestBody PessoaRequest pessoaRequest){
        ModelMapper modelMapper = new ModelMapper();
        PessoaDTO pessoaDTO = modelMapper.map(pessoaRequest, PessoaDTO.class);
        pessoaDTO =  pessoaService.adicionar(pessoaDTO);
        return new ResponseEntity<>(modelMapper.map(pessoaDTO, PessoaResponse.class),HttpStatus.CREATED);
    }

    @Operation(summary = "Deletar usuário",
    description = "<b>Deletar um usuário passando seu ID</b>",
    tags = "Usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        pessoaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Editar usuário",
    description = "<b>Editar um usuário passando seu ID</b>",
    tags = "Usuário")
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@RequestBody PessoaRequest pessoaRequest,@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        PessoaDTO pessoaDTO = modelMapper.map(pessoaRequest,PessoaDTO.class);
        pessoaDTO = pessoaService.atualizar(id, pessoaDTO);
        return new ResponseEntity<>(
                modelMapper.map(pessoaDTO, PessoaResponse.class),
                HttpStatus.OK);
    }
}
