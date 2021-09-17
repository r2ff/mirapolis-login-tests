package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class MirapolisLoginTests extends TestBase {
    @Test
    @DisplayName("Проверка входа в систему с корректными данными")
    void loginWithCorrectUserAndPasswordTest() {
        step("Вводим логин и корректный пароль", () -> {
            $("[name=user]").setValue("fominaelena");
            $("[name=password]").setValue("z0K6CTQR");
            $("#button_submit_login_form").click();
        });

        step("Проверяем, что пользователь успешно залогинился", () -> {
            $(".avatar-full-name").shouldHave(text("Фомина Елена Сергеевна"));
        });
    }

    @Test
    @DisplayName("Проверка входа в систему с некорректными данными")
    void loginWithIncorrectUserAndPasswordTest() {
        step("Вводим логин и некорректный пароль", () -> {
            $("[name=user]").setValue("fominaelena");
            $("[name=password]").setValue("z0K6CTQR1");
            $("#button_submit_login_form").click();
        });

        step("Проверяем текст алерта и страницу после закрытия алерта", () -> {
            getWebDriver().switchTo().alert().getText().contains("Неверные данные для авторизации");
            getWebDriver().switchTo().alert().accept();
            $(".info-title").shouldHave(text("Вход в систему"));
        });
    }

    @Test
    @DisplayName("Проверка входа в систему только с логином")
    void loginWithoutPasswordTest() {
        step("Вводим только логин", () -> {
            $("[name=user]").setValue("fominaelena");
            $("#button_submit_login_form").click();
        });

        step("Проверяем текст алерта и страницу после закрытия алерта", () -> {
            getWebDriver().switchTo().alert().getText().contains("Неверные данные для авторизации");
            getWebDriver().switchTo().alert().accept();
            $(".info-title").shouldHave(text("Вход в систему"));
        });
    }

    @Test
    @DisplayName("Проверка кнопки восстановления пароля")
    void checkRecoveryPasswordButtonTest() {
        step("Нажимаем кнопку \"Забыли пароль?", () -> {
            $(".mira-default-login-page-link").click();
        });

        step("Проверяем текст алерта и страницу после закрытия алерта", () -> {
            $(".info-title").shouldHave(text("Восстановление пароля"));
        });
    }
}