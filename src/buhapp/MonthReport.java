package buhapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MonthReport {
    private int month;
    private int year;
    ArrayList<MonthlyReportOne> monthReportData = new ArrayList<>();
    private int incomeSum = 0;
    private int expensesSum = 0;
    private boolean isCounted = false;

    public MonthReport(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public void countIncExp() {
        if (!isCounted) {
            for (MonthlyReportOne rep : monthReportData) {
                if (rep.isExpanse()) {
                    expensesSum += rep.getSumOfOne() * rep.getQuantity();
                } else {
                    incomeSum += rep.getSumOfOne() * rep.getQuantity();
                }
            }
        }

        isCounted = true;
    }

    public void readCSVFile(File file) {
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNext()) {
            MonthlyReportOne lineToWrite;
            boolean isExp;

            String line = scanner.nextLine();
            String[] splitLine = line.split(",");

            if (Objects.equals(splitLine[1], "TRUE")) isExp = true;
            else isExp = false;

            lineToWrite = new MonthlyReportOne(splitLine[0], isExp, Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
            monthReportData.add(lineToWrite);
        }

    }

    public void showInfo() {
        System.out.println("month: " + month + ", year: " + year);
        int biggestPlus = -1;
        int biggestMinus = -1;

        for (MonthlyReportOne rep : monthReportData) {
            if (rep.isExpanse() && (rep.getQuantity() * rep.getSumOfOne() > biggestMinus)) {
                biggestMinus = rep.getQuantity() * rep.getSumOfOne();
            } else if (!rep.isExpanse() && (rep.getQuantity() * rep.getSumOfOne() > biggestPlus)) {
                biggestPlus = rep.getQuantity() * rep.getSumOfOne();
            }
        }

        System.out.println("The most big sell is: " + biggestPlus);
        System.out.println("The most big expanse is: " + biggestMinus);
    }

    public int getIncomeSum() {
        return incomeSum;
    }

    public int getExpensesSum() {
        return expensesSum;
    }
}
