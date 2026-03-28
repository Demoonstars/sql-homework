package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement heading = $x("//h2[contains(text(), 'Личный кабинет')]");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }
}