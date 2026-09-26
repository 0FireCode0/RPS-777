package org.top.economic;

public class StorePlayer {

    public static Boolean theamsDark = false;
    public static int orientation;
    public static String Name = "Аня";
    public static String NameAnimal = "Мурчик";

    private static Integer Money = 0;
    private static Integer NeedMoney = 0;
    private static Integer Moneybox = 0;
    private static Integer Wants = 0;


    // Какое животное выбрано
    private static AnimalType animalType = AnimalType.CAT;

    // Что надето на голову
    // Например:
    // null
    // "hat_red"
    // "hat_blue"
    // "helmet_motorcycle"
    private static String headwearId = null;

    // Очки
    // null
    // "glasses_round"
    private static String glassesId = null;

    // Наушники
    // null
    // "headphones_black"
    private static String headphonesId = null;

    // Часы
    // null
    // "watch_classic"
    private static String watchId = null;

    // Браслет
    // null
    // "bracelet_gold"
    private static String braceletId = null;


    // ---------------------------------------------------------
    // MONEY
    // ---------------------------------------------------------
    public static Integer getMoney() {
        return Money;
    }
    public static void setMoney(Integer money) {
        Money = money;
    }

    public static Integer getNeedMoney() {
        return NeedMoney;
    }
    public static void setNeedMoney(Integer needMoney) {
        NeedMoney = needMoney;
    }

    public static Integer getMoneybox() {
        return Moneybox;
    }
    public static void setMoneybox(Integer moneybox) {
        Moneybox = moneybox;
    }

    public static Integer getWants() {
        return Wants;
    }
    public static void setWants(Integer wants) {
        Wants = wants;
    }


    // ---------------------------------------------------------
    // ANIMAL
    // ---------------------------------------------------------
    public static AnimalType getAnimalType() {
        return animalType;
    }
    public static void setAnimalType(AnimalType animalType) {
        StorePlayer.animalType = animalType;
    }


    // ---------------------------------------------------------
    // HEADWEAR
    // ---------------------------------------------------------
    public static String getHeadwearId() {
        return headwearId;
    }
    public static void setHeadwearId(String headwearId) {
        StorePlayer.headwearId = headwearId;
    }

    // ---------------------------------------------------------
    // GLASSES
    // ---------------------------------------------------------
    public static String getGlassesId() {
        return glassesId;
    }
    public static void setGlassesId(String glassesId) {
        StorePlayer.glassesId = glassesId;
    }

    // ---------------------------------------------------------
    // HEADPHONES
    // ---------------------------------------------------------
    public static String getHeadphonesId() {
        return headphonesId;
    }
    public static void setHeadphonesId(String headphonesId) {
        StorePlayer.headphonesId = headphonesId;
    }

    // ---------------------------------------------------------
    // WATCH
    // ---------------------------------------------------------
    public static String getWatchId() {
        return watchId;
    }
    public static void setWatchId(String watchId) {
        StorePlayer.watchId = watchId;
    }

    // ---------------------------------------------------------
    // BRACELET
    // ---------------------------------------------------------
    public static String getBraceletId() {
        return braceletId;
    }
    public static void setBraceletId(String braceletId) {
        StorePlayer.braceletId = braceletId;
    }
}
