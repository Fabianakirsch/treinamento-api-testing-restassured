package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma reserva")
    public Response criarReserva(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(payload.toString())
                .when()
                .post("booking");
    }

    @Step("Criar uma reserva com o header Accept inválido")
    public Response criarReservaAcceptInvalido() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application")
                .body(Utils.validPayloadBookingMoreParameter())
                .when()
                .post("booking");
    }


}
