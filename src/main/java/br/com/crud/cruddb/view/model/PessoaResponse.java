package br.com.crud.cruddb.view.model;

import br.com.crud.cruddb.utils.DataUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;

public class PessoaResponse {

    private Integer id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    @Transient
    DataUtils dataUtils = new DataUtils();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Schema(hidden = true)
    public Integer getIdade() {
        return dataUtils.calcularIdade(dataNascimento);
    }

}
