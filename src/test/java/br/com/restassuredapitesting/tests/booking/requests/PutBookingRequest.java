package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import javax.rmi.CORBA.Util;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.urlEncodingEnabled;

public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComToken(int id, JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva com Basic auth")
    public Response alterarUmaReservaComBasicAuth(int id, JSONObject payload) {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorisation", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva sem enviar o token")
    public Response alterarUmaReservaSemEnviarToken(int id, JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva com token inválido")
    public Response alterarUmaReservaComTokenInvalido(int id, JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=0")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

}
