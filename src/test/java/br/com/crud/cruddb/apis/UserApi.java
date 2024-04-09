package br.com.crud.cruddb.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserApi {

    public static Response cadastrarUsuario(String nome, String cpf, String dataNascimento) {
        Response response = given()
        .contentType(ContentType.JSON)
        .body("{\"nome\": \""+nome+"\",\n" +
                "\"dataNascimento\": \""+dataNascimento+"\",\n" +
                "\"cpf\": \""+cpf+"\"\n}")
        .post("http://localhost:8080/api/users");
         response.prettyPrint();

        return response;
    }

    public static Response buscarUsuarioPorId(Integer currentUserID) {
        Response response = given()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/users/"+currentUserID);
        response.prettyPrint();

        return response;
    }

    public static Integer getCurrentUserID(Response response){
        return response.then().extract().path("id");
    }

    public static Response buscarListaDeUsuarios() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/users");
        response.prettyPrint();

        return response;
    }

    public static Response editarUsuarioPorID(String nome,String cpf,Integer currentUserID) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"nome\": \""+nome+"\",\n" +
                        "\"cpf\": \""+cpf+"\"\n}")
                .put("http://localhost:8080/api/users/"+currentUserID);
        response.prettyPrint();

        return response;
    }

    public static Response deletarUsuarioPorId(Integer currentUserID) {
        Response response = given()
                .contentType(ContentType.JSON)
                .delete("http://localhost:8080/api/users/"+currentUserID);
        response.prettyPrint();

        return response;
    }
}
