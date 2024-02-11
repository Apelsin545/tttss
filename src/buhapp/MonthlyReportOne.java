package buhapp;

public class MonthlyReportOne {
    private String itemName;
    private boolean isExpanse;
    private int quantity;
    private int sumOfOne;

    public MonthlyReportOne(String itemName, boolean isExpanse, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpanse = isExpanse;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isExpanse() {
        return isExpanse;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }
}
