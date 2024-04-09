package br.com.crud.cruddb;

import br.com.crud.cruddb.apis.UserApi;
import br.com.crud.cruddb.utils.CPFUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    private Response response;
    CPFUtils cpfUtils = new CPFUtils();

    @Test
    public void cadastrarUsuarioComSucesso(){
        response = UserApi.cadastrarUsuario("Teste Unitario",cpfUtils.gerarCPF(),"04/04/2000");
        Assert.assertEquals("Usuario não cadastrado",200, response.getStatusCode());
    }

    @Test
    public void buscarUsuarioCadastrado(){
        cadastrarUsuarioComSucesso();
        response = UserApi.buscarUsuarioPorId(UserApi.getCurrentUserID(response));
        Assert.assertEquals("Usuario não encontrado",200, response.getStatusCode());
    }

    @Test
    public void buscarListaDeUsuariosCadastrado(){
        response = UserApi.buscarListaDeUsuarios();
        Assert.assertEquals("Lista de usuario não retornada",200, response.getStatusCode());
    }

    @Test
    public void editarUsuarioCadastrado(){
        cadastrarUsuarioComSucesso();
        response = UserApi.editarUsuarioPorID("Teste Unitario Edit",cpfUtils.gerarCPF(),UserApi.getCurrentUserID(response));
        Assert.assertEquals("Usuario nao editado",200, response.getStatusCode());
    }

    @Test
    public void deletarUsuarioCadastrado(){
        cadastrarUsuarioComSucesso();
        response = UserApi.deletarUsuarioPorId(UserApi.getCurrentUserID(response));
        Assert.assertEquals("Usuario apagado",200, response.getStatusCode());
    }

    @Test
    public void buscarUsuarioPorIdNaoExistente(){
        cadastrarUsuarioComSucesso();
        response = UserApi.buscarUsuarioPorId(999999999);
        Assert.assertEquals("Usuario encontrado",404, response.getStatusCode());
    }

    @Test
    public void cadastrarUsuarioJaCadastrado(){
        String cpf = cpfUtils.gerarCPF();
        response = UserApi.cadastrarUsuario("Teste Unitario",cpf,"04/04/2000");
        Assert.assertEquals("Usuario não cadastrado",200, response.getStatusCode());
        response = UserApi.cadastrarUsuario("Teste Unitario",cpf,"04/04/2000");
        Assert.assertEquals("Usuario não cadastrado",400, response.getStatusCode());
    }

    @Test
    public void editarUsuarioPorIdNaoExistente(){
        cadastrarUsuarioComSucesso();
        response = UserApi.editarUsuarioPorID("Teste Unitario Edit",cpfUtils.gerarCPF(),999999999);
        Assert.assertEquals("Usuario encontrado",404, response.getStatusCode());
    }

    @Test
    public void deletarUsuarioPorIdNaoExistente(){
        cadastrarUsuarioComSucesso();
        response = UserApi.deletarUsuarioPorId(999999999);
        Assert.assertEquals("Usuario encontrado",404, response.getStatusCode());
    }
}