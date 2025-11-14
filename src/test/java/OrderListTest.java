import io.qameta.allure.Step;
import org.junit.Test;
import steps.OrderSteps;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;

public class OrderListTest extends BaseApiTest {
    @Test
    @Step("Получение списка заказов")
    public void testGetOrdersList() {
        OrderSteps.getOrders()
                .then()
                .statusCode(200)
                .body("orders", instanceOf(List.class));
    }
}