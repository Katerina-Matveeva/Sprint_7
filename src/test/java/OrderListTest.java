import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import steps.OrderSteps;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.instanceOf;


public class OrderListTest extends BaseApiTest {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяет получение списка заказов")
    public void testGetOrdersList() {
        OrderSteps.getOrders()
                .then()
                .statusCode(HTTP_OK)
                .body("orders", instanceOf(List.class));
    }
}