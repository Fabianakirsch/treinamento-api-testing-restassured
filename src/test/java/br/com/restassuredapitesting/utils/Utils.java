package br.com.restassuredapitesting.utils;

import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import org.json.simple.JSONObject;

public class Utils {

    public static JSONObject validPayloadBooking() {
        JSONObject payload = new JSONObject();

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Fabiana");
        payload.put("lastname", "Kirsch");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }

    public static JSONObject validPayloadBookingInvalidPayload() {
        JSONObject payload = new JSONObject();

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2018-01-01");

        payload.put("firstname", 1996);
        payload.put("lastname", "Andrade");
        payload.put("totalprice", 112);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "No breakfast");

        return payload;
    }

    public static JSONObject validPayloadBookingMoreParameter() {
        JSONObject payload = new JSONObject();

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2017-01-01");
        bookingDates.put("checkout", "2018-01-01");

        payload.put("firstname", "Julia");
        payload.put("lastname", "Andrade");
        payload.put("totalprice", 112);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "No breakfast");
        payload.put("observation", "Vamos atrasar 1h para o checkin");

        return payload;
    }

    public static  String getContractsBasePath(String pack, String contract) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }

    public static int getPrimeiroId() {
        GetBookingRequest getBookingRequest = new GetBookingRequest();

        return getBookingRequest.allBookings().then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }
}
