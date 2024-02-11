package buhapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class RunApp {

    public static void MenuOutput() {
        System.out.println("1 - enter all month");
        System.out.println("2 - enter all year");
        System.out.println("3 - Compare two");
        System.out.println("4 - show info about all month");
        System.out.println("5 - show info about all year");
        System.out.println("0 - exit");
    }


    public static void run() {
        Scanner in = new Scanner(System.in);
        MenuOutput();
        System.out.print("Enter the mode: ");
        int mode = in.nextInt();
        in.nextLine();
        ArrayList<MonthReport> monthReports = new ArrayList<>();
        ArrayList<YearReport> yearReports = new ArrayList<>();

        while (mode != 0) {

            if (mode == 1) {
                System.out.print("Enter the dir, which contains monthly reports: ");
                String path = in.nextLine();
                File dirOfFiles = new File(path);

                File[] listOfFiles = dirOfFiles.listFiles();
                for (File file : listOfFiles) {
                    String fileName = file.getName();

                    if (!file.isDirectory() && fileName.charAt(0) == 'm') {
                        MonthReport monthRepToRead = new MonthReport(Integer.parseInt(fileName.substring(6, 8)),Integer.parseInt(fileName.substring(2, 6)));
                        monthRepToRead.readCSVFile(file);
                        monthReports.add(monthRepToRead);
                    }
                }

            } else if (mode == 2) {
                System.out.print("Enter the dir, which contains yearly reports: ");
                String path = in.nextLine();
                File dirOfFiles = new File(path);

                File[] listOfFiles = dirOfFiles.listFiles();
                for (File file : listOfFiles) {
                    String fileName = file.getName();

                    if (!file.isDirectory() && fileName.charAt(0) == 'y') {
                        YearReport YearReportToRead = new YearReport(Integer.parseInt(fileName.substring(2, 6)));
                        YearReportToRead.readCSVFile(file);
                        yearReports.add(YearReportToRead);
                    }
                }
            } else if (mode == 3) {
                boolean isThereAMistake = false;

                for (YearReport rep : yearReports) {
                    rep.countIncExp();
                    int j = 0;
                    for (int i = 0; i < 12; i++) {
                        if (rep.getIncome()[i] != 0 || rep.getExpenses()[i] != 0) {
                            monthReports.get(j).countIncExp();
                            if (rep.getIncome()[i] != monthReports.get(j).getIncomeSum() || rep.getExpenses()[i] != monthReports.get(j++).getExpensesSum()) {
                                System.out.println("month, where there is a mistake is: " + "0" + (i + 1));

                                isThereAMistake = true;
                            }
                        }
                    }
                }
                if (!isThereAMistake) System.out.println("no mistakes detected");
            } else if (mode == 4) {
                System.out.println("Info about monthly reports.");

                for (MonthReport rep : monthReports) {
                    rep.showInfo();
                }
            } else if (mode == 5) {
                System.out.println("Info about yearly reports.");

                for (YearReport rep : yearReports) {
                    rep.showInfo();
                }
            }


            MenuOutput();
            System.out.print("Enter the mode: ");
            mode = in.nextInt();
            in.nextLine();
        }
    }
}
