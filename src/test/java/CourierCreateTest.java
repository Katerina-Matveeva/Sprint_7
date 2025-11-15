
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тесты на создание курьера")
public class CourierCreateTest extends BaseApiTest {
    private int courierId;

    @Test
    @DisplayName("Создание курьера с валидными данными")
    @Description("Проверяет успешное создание курьера с корректными данными")
    public void testCreateCourierSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    // Тест падает (Expected: Этот логин уже используется
//  Actual: Этот логин уже используется. Попробуйте другой.)
    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Проверяет ошибку при попытке создать курьера с существующим логином")
    public void testCreateDuplicateCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание курьера без обязательного поля login")
    @Description("Проверяет ошибку при создании курьера без логина")
    public void testCreateCourierWithoutLogin() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без обязательного поля password")
    @Description("Проверяет ошибку при создании курьера без пароля")
    public void testCreateCourierWithoutPassword() {
        CourierModel courier = new CourierModel(LOGIN, null, FIRSTNAME);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        if (courierId != 0) {
            courierId = CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD)).jsonPath().getInt("id");
            CourierSteps.deleteCourier(courierId);
        }
    }
}