import io.qameta.allure.Description;

import io.qameta.allure.junit4.DisplayName;
import models.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;


@DisplayName("Тесты на создание заказа")

@RunWith(Parameterized.class)
public class OrderCreateParameterizedTest extends BaseApiTest {
    private final List<String> color;
    private Integer orderTrack;

    public OrderCreateParameterizedTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет заказа: {0}")
    public static Collection<Object[]> colorData() {
        return Arrays.asList(new Object[][] {
                {ORDER_COLOR_BLACK},
                {ORDER_COLOR_GREY},
                {ORDER_COLOR_BOTH},
                {ORDER_COLOR_NONE}
        });
    }

    @Test
    @DisplayName("Создание заказа с цветом: {color}")
    @Description("Проверяет создание заказа с разными цветами")
    public void testCreateOrderWithColor() {
        OrderModel order = new OrderModel(ORDER_FIRST_NAME, ORDER_LAST_NAME, ORDER_ADDRESS, ORDER_METRO_STATION, ORDER_PHONE, ORDER_RENT_TIME, ORDER_DELIVERY_DATE, ORDER_COMMENT, color);
        orderTrack = OrderSteps.createOrder(order)
                .then()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract().path("track");
    }

    // Отмена заказа
    @After
    public void tearDown() {
        if (orderTrack != null) {
            OrderSteps.cancelOrder(orderTrack);
        }
    }
}