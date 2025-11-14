import io.qameta.allure.Step;
import models.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static data.TestData.*;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateParameterizedTest extends BaseApiTest {
    private final List<String> color;

    public OrderCreateParameterizedTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> colorData() {
        return Arrays.asList(new Object[][] {
                {ORDER_COLOR_BLACK},
                {ORDER_COLOR_GREY},
                {ORDER_COLOR_BOTH},
                {ORDER_COLOR_NONE}
        });
    }

    @Test
    @Step("Создание заказа с цветом: {color}")
    public void testCreateOrderWithColor() {
        OrderModel order = new OrderModel(ORDER_FIRST_NAME, ORDER_LAST_NAME, ORDER_ADDRESS, ORDER_METRO_STATION, ORDER_PHONE, ORDER_RENT_TIME, ORDER_DELIVERY_DATE, ORDER_COMMENT, color);
        OrderSteps.createOrder(order)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}