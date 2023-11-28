package org.example.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FormAuthenticationPage {
    public SelenideElement enterUsername = $("#username");
    public SelenideElement enterPassword = $("#password");
    public SelenideElement buttonLogin = $(byXpath("//button[@type='submit']"));
    public SelenideElement correctData = $(byXpath("//div[@class='flash success']"));
    public SelenideElement incorrectData = $(byXpath("//div[@class='flash error']"));
}