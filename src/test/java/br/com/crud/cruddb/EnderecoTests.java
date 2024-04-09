package br.com.crud.cruddb;

import br.com.crud.cruddb.apis.EnderecoApi;
import br.com.crud.cruddb.apis.UserApi;
import br.com.crud.cruddb.utils.CPFUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoTests {
    private static Response response;
    CPFUtils cpfUtils = new CPFUtils();

    @Before
    public void setup() {
        response = UserApi.cadastrarUsuario("Teste Unitario",cpfUtils.gerarCPF(),"04/04/2000");
        Assert.assertEquals("Usuario não cadastrado",200, response.getStatusCode());
    }

    @Test
    public void cadastrarEndereco(){
        response = EnderecoApi.cadastrarEndereco(UserApi.getCurrentUserID(response),"Rua",1,"Bairro","Cidade","Estado","12345");
        Assert.assertEquals("Endereço não cadastrado",200, response.getStatusCode());
    }

}
