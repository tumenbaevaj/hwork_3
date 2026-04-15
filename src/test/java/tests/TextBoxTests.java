package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#userName").setValue("John Smith");
        $("#userEmail").setValue("johnsmith@gmail.com");
        $("#currentAddress").setValue("66 Karalaev street, Bishkek, KG");
        $("#permanentAddress").setValue("3 Matrosov street, Bishkek, KG");
        $("#submit").click();

        $("#output #name").shouldHave(text("John Smith"));
        $("#output #email").shouldHave(text("johnsmith@gmail.com"));
        $("#output #currentAddress").shouldHave(text("66 Karalaev street, Bishkek, KG"));
        $("#output #permanentAddress").shouldHave(text("3 Matrosov street, Bishkek, KG"));
    }

    @Test
    void minimalTextBoxTest() {
        open("/text-box");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#userName").setValue("Jibek");
        $("#submit").click();

        $("#output").shouldHave(text("Jibek"));
    }

    @Test
    void textBoxInvalidEmailTest() {
        open("/text-box");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#userName").setValue("Jibek");
        $("#userEmail").setValue("invalid-email");
        $("#submit").click();

        $("#userEmail").shouldHave(cssClass("field-error"));
    }
}
