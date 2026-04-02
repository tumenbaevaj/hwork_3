package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "144.0";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000; // default 4000
    }

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("John Smith");
        $("[id=userEmail]").setValue("johnsmith@gmail.com");
        $("[id=currentAddress]").setValue("66 Karalaev street, Bishkek, KG");
        $("[id=permanentAddress]").setValue("3 Matrosov street, Bishkek, KG");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("John Smith"));
        $("[id=output] [id=email]").shouldHave(text("johnsmith@gmail.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("66 Karalaev street, Bishkek, KG"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("3 Matrosov street, Bishkek, KG"));
    }
}
