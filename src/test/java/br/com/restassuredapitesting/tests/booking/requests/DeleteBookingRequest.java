package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Excluir reserva com token")
    public Response excluirReserva(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .delete("booking/"+id);
    }

    @Step("Excluir reserva sem autorização")
    public Response excluirReservaSemAutorizacao (int id) {
        return given()
                .header("Content-Type", "application/json")
                .delete("booking" +id);
    }

}
