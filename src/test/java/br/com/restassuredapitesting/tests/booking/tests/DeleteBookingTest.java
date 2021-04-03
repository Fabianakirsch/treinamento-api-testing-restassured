package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.requests.BaseRequest;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import lombok.experimental.FieldNameConstants;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.rmi.CORBA.Util;
import java.util.concurrent.TimeUnit;

import static br.com.restassuredapitesting.utils.Utils.getPrimeiroId;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Excluir uma reserva com sucesso")
    //O teste está falhando porque retorna o status "Created" (201) e deveria retornar "OK" (200), já que o teste está
    // excluindo uma reserva e não criando algo.
    public void excluirReserva() throws Exception{
        deleteBookingRequest.excluirReserva(getPrimeiroId()).then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar excluir um reserva que não existe")
    //O teste está falhando porque retorna o status "Method Not Allowed" (405) e deveria retornar "Not Found" (404),
    // pois o teste está validando a exclusão de uma reserva que não existe.
    public void excluirReservaQueNãoExiste() throws Exception {
        int id = 0;

        deleteBookingRequest.excluirReserva(id).then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar excluir uma reserva sem autorização")
    //O teste está falhando porque retorna o status "Not Found" (404) e deveria retornar "Forbiden" (403), pois o teste
    // está validando a exclusão de uma reserva sem autorização.
    public void excluirReservaSemAutorização() throws Exception{
        deleteBookingRequest.excluirReservaSemAutorizacao(getPrimeiroId()).then()
                .assertThat()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
