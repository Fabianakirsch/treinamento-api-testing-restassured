package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static br.com.restassuredapitesting.utils.Utils.getPrimeiroId;
import static br.com.restassuredapitesting.utils.Utils.validPayloadBooking;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Alterar uma reserva utilizando token")
    public void validarAlterarUmaReservaUtilizandoToken() throws Exception {
        putBookingRequest.alterarUmaReservaComToken(getPrimeiroId(), validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Alterar uma reserva usando o Basic auth")
    //O teste está falhando porque o nome correto do header é "Authorization" e na API está documentado como
    // "Authorisation".
    public void validarAlterarUmaReservaUsandoBasicAuth() throws Exception {
        putBookingRequest.alterarUmaReservaComBasicAuth(getPrimeiroId(), validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void validarAlterarUmaReservaSemToken() throws Exception {
        putBookingRequest.alterarUmaReservaSemEnviarToken(getPrimeiroId(), validPayloadBooking())
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void validarAlterarUmaReservaComTokenInvalido() throws Exception {
        putBookingRequest.alterarUmaReservaComTokenInvalido(getPrimeiroId(), validPayloadBooking())
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void validarAlterarUmaReservaQueNãoExiste() throws Exception {
        int id = 0;

        putBookingRequest.alterarUmaReservaComToken(id, validPayloadBooking()).then()
                .statusCode(404)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

}
