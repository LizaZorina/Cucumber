@tagsBooking
Feature: Тестирование бронирования из седьмого домашнего заданя.

  Scenario: Получение токена, вывод токена в консоль.
    Given Вводим логин "admin" и пароль "password123"

  Scenario: Создание и проверка  бронирования.
    When Создаем бронирование
    """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    Then Проверяем бронирование

  Scenario: Создание, обновление и проверка  бронирования
    Given Вводим логин "admin" и пароль "password123"
    When Создаем бронирование
       """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    Then Обновляем бронирование
    """
{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-02-01",
        "checkout" : "2019-02-23"
    },
    "additionalneeds" : "Breakfast"
}
"""
    And Проверяем обновленное бронирование

  Scenario: Создание, удаление, проверка что бронирование удалено
    Given Вводим логин "admin" и пароль "password123"
    When Создаем бронирование
       """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    Then Удаляем бронирование
    And Производим проверку удаления бронирования

  Scenario: Получение токена, неверные логин или пароль
    Given Вводим логин "adminnnn" и пароль "123123"

  Scenario: Создание бронирования с неверным телом
    Given Вводим логин "admin" и пароль "password123"
    Then Создаем бронирование, делая ошибки в теле метода
    """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 123456789,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2118-01-01",
        "checkout" : "0919-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    And Получаем Id бронирования с неверными данными

  Scenario: Проверка бронирования с несуществующим id
    Given Вводим логин "admin" и пароль "password123"
    When Создаем бронирование
       """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    Then Проверяем бронирование с несуществующим id "000000"

  Scenario: Удаление бронирования с несуществующим id
    Given Вводим логин "admin" и пароль "password123"
    When Создаем бронирование
       """
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
    """
    Then Проверяем бронирование с несуществующим id "000000"
    And Удаляем бронирование с несуществующим id "000000"