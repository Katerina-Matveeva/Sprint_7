package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    static Faker faker = new Faker();

    // Для курьера
    public static final String LOGIN = faker.name().lastName() + faker.regexify("[0-9]{4}");
    public static final String PASSWORD = faker.regexify("[0-9]{4}");
    public static final String FIRSTNAME = faker.name().firstName();

    // Для заказа
    public static final String ORDER_FIRST_NAME = faker.name().firstName();
    public static final String ORDER_LAST_NAME = faker.name().lastName();
    public static final String ORDER_ADDRESS = faker.address().streetAddress();
    public static final String ORDER_METRO_STATION = String.valueOf(faker.number().numberBetween(1, 237));
    public static final String ORDER_PHONE = faker.phoneNumber().phoneNumber();
    public static final int ORDER_RENT_TIME = faker.number().numberBetween(1, 7);
    public static final String ORDER_DELIVERY_DATE = LocalDate.now().plusDays(faker.number().numberBetween(1, 30)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final String ORDER_COMMENT = faker.lorem().sentence();
    public static final List<String> ORDER_COLOR_BLACK = Arrays.asList("BLACK");
    public static final List<String> ORDER_COLOR_GREY = Arrays.asList("GREY");
    public static final List<String> ORDER_COLOR_BOTH = Arrays.asList("BLACK", "GREY");
    public static final List<String> ORDER_COLOR_NONE = null;
}