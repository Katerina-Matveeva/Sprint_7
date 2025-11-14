import io.qameta.allure.Step;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;

import static data.TestData.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest extends BaseApiTest {
    private int courierId;

    @Test
    @Step("Авторизация курьера с валидными данными")
    public void testLoginCourierSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier);
        CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD))
                .then()
                .statusCode(200)
                .body("id", equalTo(courierId = CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD)).jsonPath().getInt("id")));
    }

    @Test
    @Step("Авторизация с неправильным логином")
    public void testLoginCourierWrongLogin() {
        CourierSteps.loginCourier(new CourierLoginModel("wrong", PASSWORD))
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @Step("Авторизация с неправильным паролем")
    public void testLoginCourierWrongPassword() {
        CourierSteps.loginCourier(new CourierLoginModel(LOGIN, "wrong"))
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @Step("Авторизация без поля login")
    public void testLoginCourierWithoutLogin() {
        CourierSteps.loginCourier(new CourierLoginModel(null, PASSWORD))
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //Тест падает (Expected status code <400> but was <504>.)
    @Test
    @Step("Авторизация без поля password")
    public void testLoginCourierWithoutPassword() {
        CourierSteps.loginCourier(new CourierLoginModel(LOGIN, null))
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
        if (courierId != 0) {
            CourierSteps.deleteCourier(courierId);
        }
    }
}