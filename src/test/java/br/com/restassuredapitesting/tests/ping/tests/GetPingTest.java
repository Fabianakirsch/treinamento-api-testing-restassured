package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Status")
public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class})
    @DisplayName("Verificar se API está online")
    //O teste está falhando porque retorna o status "Created" (201) e deveria retornar "OK" (200), já que o teste está
    // apenas verificando se a API está online.
    public void validarStatusAPI() throws Exception{
        getPingRequest.status().then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
