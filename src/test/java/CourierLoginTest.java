
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest extends BaseApiTest {
    private int courierId;

    @Test
    @DisplayName("Авторизация курьера с валидными данными")
    @Description("Проверяет успешную авторизацию курьера с валидными данными")
    public void testLoginCourierSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        CourierSteps.createCourier(courier);
        CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD))
                .then()
                .statusCode(HTTP_OK)
                .body("id", equalTo(courierId = CourierSteps.loginCourier(new CourierLoginModel(LOGIN, PASSWORD)).jsonPath().getInt("id")));
    }

    @Test
    @DisplayName("Авторизация с неправильным логином")
    @Description("Проверяет ошибку при попытке авторизоваться с неправильным логином")
    public void testLoginCourierWrongLogin() {
        CourierSteps.loginCourier(new CourierLoginModel("wrong", PASSWORD))
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация с неправильным паролем")
    @Description("Проверяет ошибку при попытке авторизоваться с неправильным паролем")
    public void testLoginCourierWrongPassword() {
        CourierSteps.loginCourier(new CourierLoginModel(LOGIN, "wrong"))
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация без поля login")
    @Description("Проверяет ошибку при попытке авторизоваться без поля login")
    public void testLoginCourierWithoutLogin() {
        CourierSteps.loginCourier(new CourierLoginModel(null, PASSWORD))
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //Тест падает (Expected status code <400> but was <504>.)
    @Test
    @DisplayName("Авторизация без поля password")
    @Description("Проверяет ошибку при попытке авторизоваться без поля password")
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