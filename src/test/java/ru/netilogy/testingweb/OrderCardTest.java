package ru.netilogy.testingweb;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {

    @Test void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Дмитрий Ячменцев");
        form.$("[data-test-id=phone] input").setValue("+79278913719");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test void shouldSubmitNotValidName() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Дмитрий Iachmentsev");
        form.$("[data-test-id=phone] input").setValue("+79278913719");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test void shouldSubmitNotValidPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Дмитрий Ячменцев");
        form.$("[data-test-id=phone] input").setValue("+79278913719999");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
