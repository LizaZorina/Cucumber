package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.example.Page.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class HerokuappSteps {
    //  Checkbox
    private static final String BASE_URI = "https://the-internet.herokuapp.com/";
    CheckboxPage checkboxPage = new CheckboxPage();
    FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage();
    HoversPage hoversPage = new HoversPage();
    DynamicallyLoadedPageElementsPage dynamicallyLoadedPageElementsPage = new DynamicallyLoadedPageElementsPage();
    KeyPressesPage keyPressesPage = new KeyPressesPage();
    AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage();

    @Given("^Открываем страницу с доменом \"([^\"]*)\"$")
    public void openPageDomain(String domain) {
        System.out.println("Открыли страницу ");
        open(BASE_URI + domain);
    }

    //Checkbox
    @When("^Делаем клик на checkbox$")
    public void makeClickCheckbox() {
        System.out.println("Кликнули на chekbox 1");
        checkboxPage.checkbox.click();
    }

    @And("^Проверяем что он нажат$")
    public void checkPress() {
        System.out.println("Проверили что checkbox 1 нажат");
        checkboxPage.clickTestCheckbox.shouldBe();
    }

    //Form Authentication
    @When("^Вводим логин \"([^\"]*)\"$")
    public void enterLogin(String username) {
        formAuthenticationPage.enterUsername.setValue(username);
        System.out.println("Ввели логин");
    }

    @And("^Вводим пароль \"([^\"]*)\"$")
    public void enterPassword(String password) {
        formAuthenticationPage.enterPassword.setValue(password);
        System.out.println("Ввели пароль");
    }

    @Then("^Нажимаем на кнопку авторизации$")
    public void clickButtonLogin() {
        System.out.println("Нажимаем на кнопку авторизации");
        formAuthenticationPage.buttonLogin.click();
    }

    @And("^При успешной авторизации видим  текст \"([^\"]*)\"$")
    public void successfulAuthorizationText(String successText) {
        formAuthenticationPage.correctData.shouldBe(Condition.text(successText));
        System.out.println("При успешной авторизации увидели текст: " + successText);
    }

    @And("^При неуспешной авторизации видим  текст \"([^\"]*)\"$")
    public void failedAuthorizationText(String errorText) {
        formAuthenticationPage.incorrectData.shouldBe(Condition.text(errorText));
        System.out.println("При неуспешной авторизации увидели текст: " + errorText);
    }

    //Hovers
    @When("^Наводим на нужного пользователя$")
    public void pointUser() {
        hoversPage.hoversOne.hover();
        System.out.println("Навели на пользователя");
    }

    @And("^Проверяем, что отображается текст$")
    public void checkingText() {
        hoversPage.text.shouldBe();
        System.out.println("Проверили, что текст отображается");
    }

    //Dynamically Loaded Page Elements
    @When("^Нажимаем на кнопку$")
    public void clickButtonStart() {
        dynamicallyLoadedPageElementsPage.buttonStart.click();
        System.out.println("Нажали кнопку Start");
    }

    @And("^Ждем загрузки$")
    public void waitingLoading() {
        System.out.println("Ждем пока загрузиться");
        Configuration.timeout = 10000;
    }

    @Then("^Видим текст$")
    public void waitingText() {
        dynamicallyLoadedPageElementsPage.textHello.shouldHave(visible);
        System.out.println("Видим текст");
    }

    //Key Presses
    @When("^Нажимаем клавишу$")
    public void pressKey() {
        System.out.println("Нажимаем клавишу");
        keyPressesPage.input.sendKeys(Keys.F6);
    }

    @And("^Проверяем, что клавиша нажалась$")
    public void checkingPressKey() {
        System.out.println("Проверяем, что клавиша нажалась");
        keyPressesPage.result.shouldBe(text("You entered: F6"));
    }

    //Add/Remove Elements
    @When("^Нажимаем на кнопку добавления элементов, например (\\d+) раз$")
    public void addElements(int number) {
        System.out.println("Добавляем нужное количество элементов");
        for (int i = 0; i < number; i++) {
            addRemoveElementsPage.addElement.click();
        }
    }

    @And("^Проверяем элементы в колличестве (\\d+) штук$")
    public void checkingElements(int number) {
        System.out.println("Проверяем количество добавленных элементов");
        addRemoveElementsPage.button.shouldHave(size(number));
    }
}