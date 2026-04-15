package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @Test
    void successfulFullRegistrationTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#firstName").setValue("Jibek");
        $("#lastName").setValue("Tumenbaeva");
        $("#userEmail").setValue("jibekt@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("0555667667");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1988");
        $(".react-datepicker__month").$(byText("26")).click();
        $("#subjectsInput").setValue("Accounting").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img.jpg");
        $("#currentAddress").setValue("Bishkek, Kyrgyzstan");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-content").shouldHave(text("Jibek Tumenbaeva"));
        $(".modal-content").shouldHave(text("jibekt@gmail.com"));
        $(".modal-content").shouldHave(text("Female"));
        $(".modal-content").shouldHave(text("0555667667"));
        $(".modal-content").shouldHave(text("26 June,1988"));
        $(".modal-content").shouldHave(text("Accounting"));
        $(".modal-content").shouldHave(text("Sports"));
        $(".modal-content").shouldHave(text("img.jpg"));
        $(".modal-content").shouldHave(text("Bishkek, Kyrgyzstan"));
        $(".modal-content").shouldHave(text("NCR Delhi"));
    }

    @Test
    void successfulRequiredFieldsRegistrationTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#firstName").setValue("Jibek");
        $("#lastName").setValue("Tumenbaeva");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("0555667667");
        $("#submit").click();

        $(".modal-content").shouldHave(text("Jibek Tumenbaeva"));
        $(".modal-content").shouldHave(text("Female"));
        $(".modal-content").shouldHave(text("0555667667"));
    }

    @Test
    void emptyRegistrationTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#submit").click();
        $(".modal-content").shouldNot(exist);
    }

    @Test
    void registrationWithoutFirstNameTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#lastName").setValue("Tumenbaeva");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("0555667667");
        $("#submit").click();

        $(".modal-content").shouldNot(exist);
    }

    @Test
    void registrationWithShortPhoneTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("#firstName").setValue("Jibek");
        $("#lastName").setValue("Tumenbaeva");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("12345");
        $("#submit").click();

        $(".modal-content").shouldNot(exist);
    }
}
