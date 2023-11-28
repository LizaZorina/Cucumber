package org.example.Page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddRemoveElementsPage {
    public SelenideElement addElement = $(byXpath("//button[@onclick='addElement()']"));
    public ElementsCollection button = $$("button[onclick='deleteElement()']");
}
