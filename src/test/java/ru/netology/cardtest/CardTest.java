package ru.netology.cardtest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
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

}
