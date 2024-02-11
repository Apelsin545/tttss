package buhapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class YearReport {
    private int year;
    ArrayList<YearlyReportOne> yearReportData = new ArrayList<>();
    private int[] income = new int[12];
    private int[] expenses = new int[12];
    private boolean isCounted = false;

    public YearReport(int year) {
        this.year = year;
    }

    public void readCSVFile(File file) {
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNext()) {
            YearlyReportOne lineToWrite;
            boolean isExp;

            String line = scanner.nextLine();
            String[] splitLine = line.split(",");

            if (Objects.equals(splitLine[2], "TRUE")) isExp = true;
            else isExp = false;

            lineToWrite = new YearlyReportOne(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), isExp);
            yearReportData.add(lineToWrite);
        }

    }

    public void countIncExp() {

        if (!isCounted) {
            for (YearlyReportOne rep : yearReportData) {
                if (rep.isExpense()) {
                    expenses[rep.getMonth() - 1] += rep.getAmount();
                } else {
                    income[rep.getMonth() - 1] += rep.getAmount();
                }
            }
        }

        isCounted = true;
    }

    public void showInfo() {
        System.out.println("year: " + year);
        int incomeSum = 0;
        int expensesSum = 0;

        if (!isCounted) countIncExp();

        for (int i = 0; i < 12; i++) {
            System.out.println("profit: " + (income[i] - expenses[i]));
        }

        for (int i = 0; i < 12; i++) {
            incomeSum += income[i];
            expensesSum = expenses[i];
        }

        System.out.println("average income of all month in a year: " + (incomeSum / 12));
        System.out.println("average expenseSum of all month in a year: " + (expensesSum / 12));
    }

    public int[] getIncome() {
        return income;
    }

    public int[] getExpenses() {
        return expenses;
    }
}
