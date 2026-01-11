package org.example;

public class Converter {

    //Метод, который переводит количество шагов в километры
    int convertToKm(int steps) {
        return (steps * 75) / 100000;
    }

    //Метод, который переводит количество шагов в килокалории
    int convertStepsToKilocalories(int steps) {
        return (steps * 50) / 1000;
    }
}
