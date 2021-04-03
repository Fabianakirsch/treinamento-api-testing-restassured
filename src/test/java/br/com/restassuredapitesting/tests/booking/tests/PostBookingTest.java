package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.requests.BaseRequest;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import groovy.xml.StreamingDOMBuilder;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

@Feature("Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Criar uma nova reserva")
    public void criarReserva() {
        postBookingRequest.criarReserva(Utils.validPayloadBooking())
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan (0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void criarReservaComPayloadInvalido() {
        postBookingRequest.criarReserva(Utils.validPayloadBookingInvalidPayload()).then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de mais de um livro em sequencia")
    public void criarReservasEmSequencia() {
        postBookingRequest.criarReserva(Utils.validPayloadBooking())
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0));
        postBookingRequest.criarReserva(Utils.validPayloadBooking())
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo (2));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criarReservaComMaisParâmetros() {
        postBookingRequest.criarReserva(Utils.validPayloadBookingMoreParameter()).then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan (0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void criarReservaComAcceptInvalido() {
        postBookingRequest.criarReservaAcceptInvalido().then()
                .assertThat()
                .statusCode(418);
    }

}
