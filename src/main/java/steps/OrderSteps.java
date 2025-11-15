package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static final String CREATE_ORDER_PATH = "/api/v1/orders";
    public static final String GET_ORDERS_PATH = "/api/v1/orders";
    public static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel";

    @Step("Создание заказ")
    public static Response createOrder(OrderModel orderModel) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(orderModel)
                .when()
                .post(CREATE_ORDER_PATH)
                .then()
                .log().all()
                .extract().response();
    }

    @Step ("Получить список заказов")
    public static Response getOrders() {
        return given()
                .log().all()
                .when()
                .get(GET_ORDERS_PATH)
                .then()
                .log().all()
                .extract().response();
    }
    @Step("Отменить заказ по track")
    public static Response cancelOrder(int track) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"track\": " + track + "}")
                .when()
                .put(CANCEL_ORDER_PATH)
                .then()
                .log().all()
                .extract().response();
    }

}