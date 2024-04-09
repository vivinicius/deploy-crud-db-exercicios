package br.com.crud.cruddb.view.model;

import br.com.crud.cruddb.utils.DataUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;

public class PessoaRequest {
    private String nome;
    private String dataNascimento;
    private String cpf;
    @Transient
    DataUtils dataUtils = new DataUtils();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
