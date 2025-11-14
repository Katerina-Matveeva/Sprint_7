import io.qameta.allure.Step;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;

import static data.TestData.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends BaseApiTest {
    private int courierId;

    @Test
    @Step("Создание курьера с валидными данными")
    public void testCreateCourierSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
        courierId = CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD)).jsonPath().getInt("id");
    }
    // Тест падает (Expected: Этот логин уже используется
//  Actual: Этот логин уже используется. Попробуйте другой.)
    @Test
    @Step("Создание двух одинаковых курьеров")
    public void testCreateDuplicateCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
        courierId = CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD)).jsonPath().getInt("id");
    }

    @Test
    @Step("Создание курьера без обязательного поля login")
    public void testCreateCourierWithoutLogin() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Step("Создание курьера без обязательного поля password")
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
            CourierSteps.deleteCourier(courierId);
        }
    }
}