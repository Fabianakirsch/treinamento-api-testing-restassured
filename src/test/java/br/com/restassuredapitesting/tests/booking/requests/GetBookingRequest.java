package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar reserva específica")
    public Response idBooking(int id, JSONObject payload) {
        return given()
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .get("booking/" + id);
    }

    @Step("Buscar todas as reservas e filtrar por um parâmetro")
    public Response allBookingsOneParam(String param, String object) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param, object)
                .get("booking");
    }

    @Step("Buscar todas as reservas e filtrar por dois parâmetros")
    public Response allBookingsTwoParams(String param1, String object1, String param2, String object2) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param1, object1)
                .queryParam(param2, object2)
                .get("booking");
    }

    @Step("Buscar todas as reservas e filtrar por três parâmetros")
    public Response allBookingsThreeParams(String param1, String object1,
                                           String param2, String object2,
                                           String param3, String object3) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param1, object1)
                .queryParam(param2, object2)
                .queryParam(param3, object3)
                .get("booking");
    }


}
