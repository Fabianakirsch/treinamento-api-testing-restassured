package br.com.restassuredapitesting.tests.booking.tests;


import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static br.com.restassuredapitesting.utils.Utils.getPrimeiroId;
import static br.com.restassuredapitesting.utils.Utils.validPayloadBooking;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void garantirContratoListaReserva() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookings")
                                )
                        )
                );

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class})
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void garantirContratoReservaEspecifica() throws Exception {
        getBookingRequest.idBooking(getPrimeiroId(), validPayloadBooking()).then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookings-id")
                                )
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs das Reservas")
    public void listarIdsDasReservas() throws Exception{
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar uma reserva específica")
    public void listarReservaEspecifica() throws Exception{
        getBookingRequest.idBooking(getPrimeiroId(), validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void listasIdsDasReservasFiltrandoFirstname() throws Exception{
        getBookingRequest.allBookingsOneParam("firstname", "Fabiana").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void listarIdsDasReservasFiltrandoLastname() throws Exception{
        getBookingRequest.allBookingsOneParam("lastname", "Kirsch").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void listarIdsDasReservasFiltrandoCheckin() throws Exception{
        getBookingRequest.allBookingsOneParam("checkin", "2021-01-01").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void listarIdsDasReservasFiltrandoCheckout() throws Exception{
        getBookingRequest.allBookingsOneParam("checkout", "2018-01-01").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin and checkout")
    public void listarIdsDasReservasFiltrandoData() throws Exception{
        getBookingRequest.allBookingsTwoParams("checkin", "2018-01-01",
                "checkout", "2019-01-01").then().log().all()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin e checkout")
    public void listarIdsDasReservasFiltrandoNameEData() throws Exception{
        getBookingRequest.allBookingsThreeParams(
                "name", "Fabiana",
                "checkin", "2018-01-01",
                "checkout", "2019-01-01")
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void visualizarErroDeServidorComFiltroMalFormatado() throws Exception{
        getBookingRequest.allBookingsOneParam("checkin", "dd/mm/aaaa").then()
                .statusCode(500)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
 }

