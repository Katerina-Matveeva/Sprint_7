package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CourierLoginModel;
import models.CourierModel;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static final String CREATE_PATH = "/api/v1/courier";
    public static final String LOGIN_PATH = "/api/v1/courier/login";
    public static final String DELETE_PATH = "/api/v1/courier/";

    @Step("Создание курьера")
    public static Response createCourier(CourierModel courierModel) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courierModel)
                .when()
                .post(CREATE_PATH)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Логин курьера")
    public static Response loginCourier(CourierLoginModel loginModel) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post(LOGIN_PATH)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Удалить курьера по ID")
    public static Response deleteCourier(int courierId) {
        return given()
                .log().all()
                .when()
                .delete(DELETE_PATH + courierId)
                .then()
                .log().all()
                .extract().response();
    }
}