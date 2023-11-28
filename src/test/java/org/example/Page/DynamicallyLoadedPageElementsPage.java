package org.example.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DynamicallyLoadedPageElementsPage {
    public SelenideElement buttonStart = $(byXpath("//button[text()='Start']"));
    public SelenideElement textHello = $(byXpath("//h4[text()='Hello World!']"));
}
