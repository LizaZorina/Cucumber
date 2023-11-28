package org.example.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class KeyPressesPage {
    public SelenideElement input = $("#target");
    public SelenideElement result = $("#result");
}