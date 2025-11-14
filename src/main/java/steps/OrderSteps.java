package steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static final String CREATE_ORDER_PATH = "/api/v1/orders";
    public static final String GET_ORDERS_PATH = "/api/v1/orders";

    // Создать заказ
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

    // Получить список заказов
    public static Response getOrders() {
        return given()
                .log().all()
                .when()
                .get(GET_ORDERS_PATH)
                .then()
                .log().all()
                .extract().response();
    }
}