import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
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
        $("input[name='email']").setValue("6hjhJhjjwr@gmail.com");
        $("input[name='password']").setValue("kO6hjhjwud3");
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
        $("input[name='email']").setValue("525665@gmail.com");
        $("input[name='password']").setValue(faker.internet().password());
        $x("//button[text()='Sign Up']").click();
        $x("//div[contains(text(),'Please check your login')").shouldBe(Condition.visible, Duration.ofSeconds(10));

    }

    @Test
    void logOut() {
        $(" a[href='/login']").click();
        $("input[name='email']").setValue("Hrtgdd@gmail.com");
        $("input[name='password']").setValue("pass");
        $x("//button[text()='Login']").click();
        $(" a[href='/logout']").click();
        $x("//h1[contains(text(),'Test home page')]").shouldBe(Condition.visible, Duration.ofSeconds(30));

    }

    @Test
    void loginFromHomePage() {
        $(" a[href='/login']").click();
        $("input[name='email']").setValue("Hrtgdd@gmail.com");
        $("input[name='password']").setValue("pass");
        $x("//button[text()='Login']").click();
        $x("//h1[contains(text(),'Welcome')]").shouldBe(Condition.visible, Duration.ofSeconds(30));

    }


}

