package br.com.crud.cruddb.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EnderecoApi {

    public static Response cadastrarEndereco(Integer userId, String rua,Integer numero,String bairro,String cidade,String estado,String cep) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"rua\":\""+rua+"\",\n" +
                        "    \"numero\":"+numero+",\n" +
                        "    \"bairro\": \""+bairro+"\",\n" +
                        "    \"cidade\": \""+cidade+"\",\n" +
                        "    \"estado\": \""+estado+"\",\n" +
                        "    \"cep\": \""+cep+"\"\n" +
                        "}")
                .post("http://localhost:8080/api/users/"+userId+"/address");
        response.prettyPrint();

        return response;
    }

}
