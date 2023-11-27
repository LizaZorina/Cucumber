package org.example.Booking;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingStep {

    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    private Response response;
    private static String token;
    private Response bookingId;
    private Response check;
    private Response delete;
    private String id;

    @Given("^Вводим логин \"([^\"]*)\" и пароль \"([^\"]*)\"$")
    public void enterLoginAndPassword(String username, String password) {
        response = given()
                .baseUri(BASE_URI)
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .contentType(ContentType.JSON)
                .post(BASE_URI + "/auth");
        response
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @When("^Получаем токен$")
    public void getToken() {
        token = response.jsonPath().getString("token");
        System.out.println("Получаем токен " + token);
    }

    @When("^Создаем бронирование$")
    public void createReservation() {
        System.out.println("Создали бронирование");
        bookingId = given()
                .baseUri(BASE_URI)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .post(BASE_URI + "/booking");
        bookingId
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @And("^Получаем Id бронирования$")
    public void getReservationId() {
        id = bookingId.jsonPath().getString("bookingid");
        System.out.println("Получаем Id бронирования " + id);
    }

    @Then("^Проверяем бронирование$")
    public void checkingReservation() {
        System.out.println("Проверка создания бронирования");
        check = given()
                .baseUri(BASE_URI)
                .get(BASE_URI + "/booking/" + id);
        check
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Then("^Обновляем бронирование$")
    public void updateReservation() {
        System.out.println("Обновляем бронирование");
        given()
                .header("Cookie", "token=" + token)
                .baseUri(BASE_URI)
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 123,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-02-01\",\n" +
                        "        \"checkout\" : \"2019-02-23\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .put(BASE_URI + "/booking/" + id)
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @And("^Проверяем обновленное бронирование$")
    public void checkingUpdateReservation() {
        System.out.println("Проверка создания обновленного бронирования");
        check = given()
                .baseUri(BASE_URI)
                .get(BASE_URI + "/booking/" + id);
        check
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Then("^Удаляем бронирование$")
    public void deleteReservation() {
        System.out.println("Удаляем бронирование");
        delete = given()
                .header("Cookie", "token=" + token)
                .baseUri(BASE_URI)
                .delete(BASE_URI + "/booking/" + id);
        delete
                .then()
                .statusCode(201);
    }

    @And("^Производим проверку удаления бронирования$")
    public void checkDeleteReservation() {
        check = given()
                .baseUri(BASE_URI)
                .get(BASE_URI + "/booking/" + id);
        check
                .then()
                .statusCode(404)
                .log()
                .body();
    }

    @Then("^Создаем бронирование, делая ошибки в теле метода$")
    public void creatingReservationErrorsBody() {
        System.out.println("Создаем бронирование с ошибками в теле метода");
        bookingId = given()
                .baseUri(BASE_URI)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 123456789,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2118-01-01\",\n" +
                        "        \"checkout\" : \"0919-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .post(BASE_URI + "/booking");
        bookingId
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @And("^Получаем Id бронирования с неверными данными$")
    public void getIdReservationErrorsBody() {
        id = bookingId.jsonPath().getString("bookingid");
        System.out.println("Получаем Id бронирования в котором есть ошибки " + id);
    }

    @Then("^Проверяем бронирование с несуществующим id \"([^\"]*)\"$")
    public void CheckingReservationWrongId(String wrongId) {
        System.out.println("Проверяем бронирование с несуществующим Id");
        check = given()
                .header("Cookie", "token=" + token)
                .baseUri(BASE_URI)
                .get(BASE_URI + "/booking/" + wrongId);
        check
                .then()
                .statusCode(404)
                .log()
                .body();
    }

    @And("^Удаляем бронирование с несуществующим id \"([^\"]*)\"$")
    public void DeletingWrongId(String wrongId) {
        System.out.println("Удаляем бронирование с несуществующим id");
        delete = given()
                .header("Cookie", "token=" + token)
                .baseUri(BASE_URI)
                .delete(BASE_URI + "/booking/" + wrongId);
        delete
                .then()
                .statusCode(405);
    }
}