package org.example.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HoversPage {
    public SelenideElement hoversOne = $(byXpath("//div[@class='figure'][1]"));
    public SelenideElement text = $(byXpath("//a[@href='/users/1']"));
}