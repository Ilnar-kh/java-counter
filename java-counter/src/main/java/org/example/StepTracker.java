package org.example;

import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    MonthData[] monthToData = new MonthData[12];
    int goalByStepsPerDay = 10000;
    Converter converter = new Converter();

    //для команды по сохранению количества шагов.
    StepTracker(Scanner scan) {
        scanner = scan;

        // для создания нового экземпляра класса MonthData для каждого индекса массива monthToData.
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца, в который вы хотите ввести кол-во шагов (от 1-12)");
        int month = scanner.nextInt();
        if ((month < 1) || (month > 12)) {
            System.out.println("Введен некорректный номер");
            return;
        }

        System.out.println("Введите день, в который вы хотите ввести кол-во шагов (от 1-30)");
        int day = scanner.nextInt();
        if ((day < 1) || (day > 30)) {
            System.out.println("Введен некорректный номер");
            return;
        }

        System.out.println("Введите количество шагов");
        int steps = scanner.nextInt();
        if (steps < 0) {
            System.out.println("Введен некорректный номер");
            return;
        }

        MonthData monthData = monthToData[month - 1];

        monthData.days[day - 1] = steps;

        System.out.println("Шаги успешно сохранены!");
    }


    void changeStepGoal() {
        System.out.println("Введите новую цель шагов на день:");
        int newGoal = scanner.nextInt();

        if (newGoal <= 0) {
            System.out.println("Цель должна быть больше 0. Введите корректное значение.");
            return;
        }

        goalByStepsPerDay = newGoal;
        System.out.println("Новая цель шагов установлена: " + goalByStepsPerDay);
    }


    void printStatistic() {
        System.out.println("Введите номер месяца (от 1-12): ");
        int month = scanner.nextInt();
        if ((month < 1) || (month > 12)) {
            System.out.println("Введен некорректный номер");
            return;
        }
        MonthData selectedMonth = monthToData[month - 1];
        System.out.println("Статистика за " + month + " месяц:");
        int sumSteps = selectedMonth.sumStepsFromMonth();
        int maxSteps = selectedMonth.maxSteps();
        int finalSeries = selectedMonth.bestSeries(goalByStepsPerDay);

        //количество пройденных шагов по дням
        System.out.println("Количество пройденных шагов по дням: ");
        selectedMonth.printDaysAndStepsFromMonth();


        //общее количество шагов за месяц;
        System.out.println("Общее количество шагов за месяц: " + sumSteps);

        //максимальное пройденное количество шагов в месяце;
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);

        //среднее количество шагов;
        System.out.println("Среднее количество шагов: " + sumSteps / 30);

        //пройденная дистанция (в километрах);
        System.out.println("Пройденная дистанция в километрах: " + converter.convertToKm(sumSteps));

        //количество сожжённых килокалорий;
        System.out.println("Количество сожжённых килокалорий: " + converter.convertStepsToKilocalories(sumSteps));

        /*лучшая серия: максимальное количество подряд идущих дней,
        в течение которых количество шагов за день было равно или выше целевого */
        System.out.println("Лучшая серия: максимальное количество подряд идущих дней, " +
                "в течение которых количество шагов за день было равно или выше целевого: " + finalSeries);
        System.out.println();
        System.out.println("-".repeat(50));
        System.out.println();
    }
}