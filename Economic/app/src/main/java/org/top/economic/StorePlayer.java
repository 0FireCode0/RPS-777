package org.top.economic;

public class StorePlayer {

    public static Boolean theamsDark = false;

    private static Integer Money = 0;
    private static Integer NeedMoney = 0;
    private static Integer Moneybox = 0;
    private static Integer Wants = 0;


    // Геттер и Сеттер для Money
    public static Integer getMoney() {
        return Money;
    }
    public static void setMoney(Integer money) {
        Money = money;
    }

    // Геттер и Сеттер для NeedMoney
    public static Integer getNeedMoney() {
        return NeedMoney;
    }
    public static void setNeedMoney(Integer needMoney) {
        NeedMoney = needMoney;
    }

    // Геттер и Сеттер для Moneybox
    public static Integer getMoneybox() {
        return Moneybox;
    }
    public static void setMoneybox(Integer moneybox) {
        Moneybox = moneybox;
    }

    // Геттер и Сеттер для Wants
    public static Integer getWants() {
        return Wants;
    }
    public static void setWants(Integer wants) {
        Wants = wants;
    }
}
