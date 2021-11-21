package ru.netology.cardtest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @BeforeEach
    public void shouldOpenForm() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSendCorrectForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(text("Успешно! Встреча успешно забронирована на " + dateTest));
    }

    @Test
    void shouldSend5DaysDeliveryForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(text("Успешно! Встреча успешно забронирована на " + dateTest));
    }

    @Test
    void shouldSendEnglishTownForm() {
        $("[data-test-id='city'] input").setValue("moskow");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Доставка в выбранный город недоступна")).should(appear);
    }

    @Test
    void shouldSendFormNoTown() {
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).should(appear);
    }

    @Test
    void shouldSendNotFoundTownForm() {
        $("[data-test-id='city'] input").setValue("Узловая");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Доставка в выбранный город недоступна")).should(appear);
    }

    @Test
    void shouldSendSymbolsAndNumbersAsTownForm() {
        $("[data-test-id='city'] input").setValue("!8594№@");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Доставка в выбранный город недоступна")).should(appear);
    }

    @Test
    void shouldSend1DayDeliveryForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Заказ на выбранную дату невозможен")).should(appear);
    }

    @Test
    void shouldSendEnglishNameForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Sergeev Pavel");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).should(appear);
    }

    @Test
    void shouldSendEmptyNameForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).should(appear);
    }

    @Test
    void shouldSendEmptyPhoneForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).should(appear);
    }

    @Test
    void shouldSendEmptyCheckboxForm() {
        $("[data-test-id='city'] input").setValue("Москва");
        LocalDate date = LocalDate.now();
        String dateTest = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder='Дата встречи']").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Иванова Мария");
        $("[data-test-id='phone'] input").setValue("+79999990000");
        $("button.button").click();
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(appear);
    }
}
