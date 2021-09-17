package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @BeforeEach
    void beforeEach() {
        open("https://lmslite47vr.demo.mirapolis.ru/mira");
    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.addScreenshotAs("Last screenshot");
        Selenide.closeWebDriver();
    }
}
