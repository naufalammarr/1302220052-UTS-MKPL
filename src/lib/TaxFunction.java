package lib;

public class TaxFunction {

    private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
    private static final int MARRIED_ADDITION = 4500000;
    private static final int CHILD_ADDITION = 1500000;
    private static final int MAX_CHILDREN_COUNT = 3;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
                                   int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
            numberOfMonthWorking = 12;
        }

        if (numberOfChildren > MAX_CHILDREN_COUNT) {
            numberOfChildren = MAX_CHILDREN_COUNT;
        }

        int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = annualIncome - deductible - calculateNonTaxableIncome(isMarried, numberOfChildren);
        int tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(tax, 0);
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = BASIC_NON_TAXABLE_INCOME;

        if (isMarried) {
            nonTaxable += MARRIED_ADDITION;
        }

        nonTaxable += Math.min(numberOfChildren, MAX_CHILDREN_COUNT) * CHILD_ADDITION;

        return nonTaxable;
    }
}
