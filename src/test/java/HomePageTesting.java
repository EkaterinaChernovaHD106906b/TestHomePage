import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class HomePageTesting {
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:5000/login");
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void registrationNewUser() {
        $(" a[href='/signup']").click();
        $("input[name='email']").setValue(faker.internet().emailAddress());
        $("input[name='password']").setValue(faker.internet().password());
        $x("//button[text()='Sign Up']").click();
        $("div[class='container has-text-centered']").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    void registrationNewUserWithSameEmail() {
        $(" a[href='/signup']").click();
        $("input[name='email']").setValue("testemail@gmail.com");
        $("input[name='password']").setValue("k78747hd3");
        $x("//button[text()='Sign Up']").click();
        $("div[class='notification is-danger']").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    void loginFromSignUpPage() {
        $(" a[href='/signup']").click();
        $("input[name='email']").setValue("H6ORj@gmail.com");
        $("input[name='password']").setValue("LIG43ud3");
        $x("//button[text()='Sign Up']").click();
        $("div[class='container has-text-centered']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $("input[name='email']").setValue("6hjhJhjjwr@gmail.com");
        $("input[name='password']").setValue("kO6hjhjwud3");
        $x("//button[text()='Login']").click();
        $x("//h1[contains(text(),'Welcome')]").shouldBe(Condition.visible, Duration.ofSeconds(30));

    }

    @Test
    void registrationWithoutPassword() {
        $(" a[href='/signup']").click();
        $("input[name='email']").setValue(faker.internet().emailAddress());
        $x("//button[text()='Sign Up']").click();
        $x("//h3[contains(text(),'Sign Up')]").shouldBe(Condition.visible);
    }

    @Test
    void registrationWithEmailOnlyNumbers() {
        $(" a[href='/signup']").click();
        $("input[name='email']").setValue("52665@gmail.com");
        $("input[name='password']").setValue(faker.internet().password());
        $x("//button[text()='Sign Up']").click();
        $x("//div[contains(text(),'Please check your login')]").shouldBe(Condition.visible, Duration.ofSeconds(10));

    }

    @Test
    void logOut() {
        $(" a[href='/login']").click();
        $("input[name='email']").setValue("myEmail@gmail.com");
        $("input[name='password']").setValue("pass");
        $x("//button[text()='Login']").click();
        $(" div.navbar-end>a[href='/logout']").click();
        $x("//h1[contains(text(),'Test home page')]").shouldBe(Condition.visible, Duration.ofSeconds(30));

    }

    @Test
    void loginFromHomePage() {
        $("div> a:nth-of-type(2)").click();
        $("form div div input[placeholder='Your Email']").setValue("myEmail@gmail.com");
        $("input[name='password']").setValue("pass");
        $x("//button[text()='Login']").click();
        $x("//h1[contains(text(),'Welcome')]").shouldBe(Condition.visible, Duration.ofSeconds(30));

    }


}

