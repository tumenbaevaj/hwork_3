package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTests extends TestBase{

    @Test
    void successfulFullRegistrationTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Jibek");
        $("[id=lastName]").setValue("Tumenbaeva");
        $("[id=userEmail]").setValue("jibekt@gmail.com");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("0555667667");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1988");
        $(".react-datepicker__month").$(byText("26")).click();
        $("[id=subjectsInput]").setValue("Accounting").pressEnter();
        $("[id=hobbiesWrapper]").$(byText("Sports")).click();
        $("[id=uploadPicture]").uploadFromClasspath("img.jpg");
        $("[id=currentAddress]").setValue("Bishkek, Kyrgyzstan");
        $("[id=state]").click();
        $("[id=stateCity-wrapper]").$(byText("NCR")).click();
        $("[id=city]").click();
        $("[id=stateCity-wrapper]").$(byText("Delhi")).click();
        $("[id=submit]").click();

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

        $("[id=firstName]").setValue("Jibek");
        $("[id=lastName]").setValue("Tumenbaeva");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("0555667667");
        $("[id=submit]").click();

        $(".modal-content").shouldHave(text("Jibek Tumenbaeva"));
        $(".modal-content").shouldHave(text("Female"));
        $(".modal-content").shouldHave(text("0555667667"));
    }

    @Test
    void EmptyRegistrationTest() {
        open("/automation-practice-form");

        $("[id=submit]").click();
        $(".modal-content").shouldNot(exist);
    }

    @Test
    void RegistrationWithoutFirstNameTest() {
        open("/automation-practice-form");

        $("[id=lastName]").setValue("Tumenbaeva");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("0555667667");
        $("[id=submit]").click();

        $(".modal-content").shouldNot(exist);
    }

    @Test
    void RegistrationWithShortPhoneTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Jibek");
        $("[id=lastName]").setValue("Tumenbaeva");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("12345");
        $("[id=submit]").click();

        $(".modal-content").shouldNot(exist);
    }
}
