import java.util.Scanner;

/**
 * PaystubCalculator is a Java program that calculates paystubs for employees based on user input.
 * It calculates gross earnings, Medicare tax, Social Security tax, Federal Income Tax withheld,
 * and net earnings for each employee. Additionally, it keeps track of the grand payroll totals
 * for multiple employees.
 *
 * <p>This program interacts with the user through the console, asking for employee information
 * and displaying the paystub results. The user can enter employee data until they decide to quit
 * the program.
 *
 * @author Million Eyassu
 * @version 1.0
 */



public class PayStubME {
    public static void main(String[] args) {
        
        /**get user input with scanner */
        Scanner scanner = new Scanner(System.in);

        System.out.println("Paystub Calculator");
        System.out.println("-----------------------------------------");

        /** variable declarations for totals */
        float[] sngGrandTotals = new float[6];
        /**
             * sngGrandTotals[0] -> Total Gross Income
             * sngGrandTotals[1] -> Total Medicare Tax
             * sngGrandTotals[2] -> Total Social Security Tax
             * sngGrandTotals[3] -> Total FICA Tax (Medicare + Social Security)
             * sngGrandTotals[4] -> Total Income Tax Withheld
             * sngGrandTotals[5] -> Total Net Income
         */
       



        String strResponse;
        do {
            System.out.print("Employee Name: (type 'Quit' to end program) ");
            strResponse = readStringFromUser(scanner);
            if (strResponse.toUpperCase().equals("QUIT")) {
                break;
            }

            /** Hourly Wage input */
            float sngHourlyWage = readPositiveFloatFromUser(scanner, "Hourly Wage");

            /** Hours Worked input */
            float sngHoursWorked = readPositiveFloatFromUser(scanner, "Hours Worked");

            /** Withholding Exemptions input */
            int intWithholdingExemptions = readIntInRangeFromUser(scanner, "Withholding Exemptions", 0, 10);

            /** Marital Status input */
            String strMaritalStatus = readMaritalStatusFromUser(scanner);

            /** Calculate Payroll for the Employee */
            float sngGrossEarnings = calculateGrossEarnings(sngHourlyWage, sngHoursWorked);
            float sngMedicareTax = calculateMedicareTax(sngGrossEarnings);
            float sngSocialSecurityTax = calculateSocialSecurityTax(sngGrossEarnings);
            float sngFederalIncomeTaxWithheld = calculateFederalIncomeTaxWithheld(sngGrossEarnings, intWithholdingExemptions, strMaritalStatus);
            float sngNetEarnings = calculateNetEarnings(sngGrossEarnings, sngMedicareTax, sngSocialSecurityTax, sngFederalIncomeTaxWithheld);

            /**  Update Grand Payroll Totals */
            sngGrandTotals[0] += sngGrossEarnings;
            sngGrandTotals[1] += sngMedicareTax;
            sngGrandTotals[2] += sngSocialSecurityTax;
            sngGrandTotals[3] += sngMedicareTax + sngSocialSecurityTax;
            sngGrandTotals[4] += sngFederalIncomeTaxWithheld;
            sngGrandTotals[5] += sngNetEarnings;

            /** Display Paystub for the Employee */
            System.out.println("\nPaycheck for " + strResponse);
            System.out.printf("Gross Income:                          $%,10.2f%n", sngGrossEarnings);
            System.out.printf("less Medicare Tax:                     $%,10.2f%n", sngMedicareTax);
            System.out.printf("less Social Security Tax:              $%,10.2f%n", sngSocialSecurityTax);
            System.out.printf("less FICA Tax:                         $%,10.2f%n", sngMedicareTax + sngSocialSecurityTax);
            System.out.printf("less Federal Income Tax Withheld:      $%,10.2f%n", sngFederalIncomeTaxWithheld);
            System.out.printf("Net Income:                            $%,10.2f%n", sngNetEarnings);
            System.out.println("-----------------------------------------");

        } while (true);

        /**  Display Grand Payroll Totals using the array */
        System.out.println("\nGrand Paycheck Totals-----------------------");
        System.out.println("-----------------------------------------");
        System.out.printf("Total Gross Income:                     $%,10.2f%n", sngGrandTotals[0]);
        System.out.printf("Total Medicare Tax:                     $%,10.2f%n", sngGrandTotals[1]);
        System.out.printf("Total Social Security Tax:              $%,10.2f%n", sngGrandTotals[2]);
        System.out.printf("Total FICA Tax:                         $%,10.2f%n", sngGrandTotals[3]);
        System.out.printf("Total Income Tax Withheld:              $%,10.2f%n", sngGrandTotals[4]);
        System.out.printf("Total Net Income:                       $%,10.2f%n", sngGrandTotals[5]);
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        scanner.close();
    }


    /**
     * Reads a string input from the user using the provided Scanner object.
     *
     * @param scanner The Scanner object to read the input from.
     * @return The string entered by the user.
     */
    public static String readStringFromUser(Scanner scanner) {
        String strValue = "";
        boolean blnIsValid = false;

        while (!blnIsValid) {
            if (scanner.hasNextLine()) {
                strValue = scanner.nextLine();
                blnIsValid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid string.");
                scanner.next(); /**Clear the invalid input */
            }
        }

        return strValue;
    }

    /**
     * Reads a floating-point number input from the user using the provided Scanner object.
     *
     * @param scanner The Scanner object to read the input from.
     * @return The float value entered by the user.
     */
    public static float readFloatFromUser(Scanner scanner) {
        float sngFloatValue = 0;
        boolean blnIsValid = false;

        while (!blnIsValid) {
            if (scanner.hasNextFloat()) {
                sngFloatValue = scanner.nextFloat();
                blnIsValid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); /** Clear the invalid input */
            }
        }

        return sngFloatValue;
    }

    /**
     * Reads a positive floating-point number input from the user using the provided Scanner object.
     *
     * @param scanner The Scanner object to read the input from.
     * @param strInputName The name of the input value for displaying prompts.
     * @return The positive float value entered by the user.
     */
    public static float readPositiveFloatFromUser(Scanner scanner, String strInputName) {
        float sngValue;
        do {
            System.out.print(strInputName + ": ");
            sngValue = readFloatFromUser(scanner);
            if (sngValue <= 0) {
                System.out.println(strInputName + " must be a positive number.");
            }
        } while (sngValue <= 0);
        return sngValue;
    }

    /**
     * Reads an integer input within a specific range from the user using the provided Scanner object.
     *
     * @param scanner The Scanner object to read the input from.
     * @param strInputName The name of the input value for displaying prompts.
     * @param intMin The minimum allowed integer value (inclusive).
     * @param intMax The maximum allowed integer value (inclusive).
     * @return The integer value entered by the user within the specified range.
     */
    public static int readIntInRangeFromUser(Scanner scanner, String strInputName, int intMin, int intMax) {
        int intValue;
        do {
            System.out.print(strInputName + ": ");
            intValue = (int) readFloatFromUser(scanner);
            if (intValue < intMin || intValue > intMax) {
                System.out.println(strInputName + " must be between " + intMin + " and " + intMax + ".");
            }
        } while (intValue < intMin || intValue > intMax);
        return intValue;
    }

    /**
     * Reads the marital status (S = Single, M = Married) from the user using the provided Scanner object.
     *
     * @param scanner The Scanner object to read the input from.
     * @return The uppercase string representing the marital status entered by the user (S or M).
     */
    public static String readMaritalStatusFromUser(Scanner scanner) {
        String strMaritalStatus;
        do {
            System.out.print("Marital Status (S = Single, M = Married): ");
            strMaritalStatus = readStringFromUser(scanner).toUpperCase();
            if (!strMaritalStatus.equals("S") && !strMaritalStatus.equals("M")) {
                System.out.println("Invalid marital status. Please enter S or M.");
            }
        } while (!strMaritalStatus.equals("S") && !strMaritalStatus.equals("M"));
        return strMaritalStatus;
    }

    /**
     * Calculates the gross earnings based on the hourly wage and hours worked.
     *
     * @param sngHourlyWage The hourly wage.
     * @param sngHoursWorked The number of hours worked.
     * @return The gross earnings as a float value.
     */
    public static float calculateGrossEarnings(float sngHourlyWage, float sngHoursWorked) {
        float sngGrossEarnings;

        if (sngHoursWorked <= 40) {
            sngGrossEarnings = sngHourlyWage * sngHoursWorked;
        } else {
            float sngOvertimeHours = sngHoursWorked - 40;
            sngGrossEarnings = (40 * sngHourlyWage) + (sngOvertimeHours * (sngHourlyWage * 1.5f));
        }

        return sngGrossEarnings;
    }

    /**
     * Calculates the Medicare tax (1.45% of total wages) based on the gross earnings.
     *
     * @param sngGrossEarnings The gross earnings.
     * @return The Medicare tax amount as a float value.
     */
    public static float calculateMedicareTax(float sngGrossEarnings) {
        return sngGrossEarnings * 0.0145f;
    }

    /**
     * Calculates the Social Security tax (6.2% of total wages) based on the gross earnings.
     *
     * @param sngGrossEarnings The gross earnings.
     * @return The Social Security tax amount as a float value.
     */
    public static float calculateSocialSecurityTax(float sngGrossEarnings) {
        return sngGrossEarnings * 0.062f;
    }

    /**
     * Calculates the Federal Income Tax withheld based on the adjusted gross income, withholding exemptions,
     * and marital status.
     *
     * @param sngGrossEarnings The gross earnings.
     * @param intWithholdingExemptions The number of withholding exemptions claimed.
     * @param strMaritalStatus The marital status ("S" for Single, "M" for Married).
     * @return The Federal Income Tax withheld as a float value.
     */
    public static float calculateFederalIncomeTaxWithheld(float sngGrossEarnings, int intWithholdingExemptions, String strMaritalStatus) {
        float sngAdjustedGrossIncome = sngGrossEarnings - (intWithholdingExemptions * 55.77f);
        float sngFederalIncomeTaxWithheld;

        if (sngAdjustedGrossIncome <= 50.99f) {
            sngFederalIncomeTaxWithheld = 0;
        } else if (sngAdjustedGrossIncome <= 500.99f) {
            sngFederalIncomeTaxWithheld = (strMaritalStatus.equals("S")) ? (sngAdjustedGrossIncome - 51) * 0.10f : (sngAdjustedGrossIncome - 51) * 0.05f;
        } else if (sngAdjustedGrossIncome <= 2500.99f) {
            sngFederalIncomeTaxWithheld = (strMaritalStatus.equals("S")) ? 45.00f + (sngAdjustedGrossIncome - 500.00f) * 0.15f : 22.50f + (sngAdjustedGrossIncome - 500.00f) * 0.10f;
        } else if (sngAdjustedGrossIncome <= 5000f) {
            sngFederalIncomeTaxWithheld = (strMaritalStatus.equals("S")) ? 345.00f + (sngAdjustedGrossIncome - 2500.00f) * 0.20f : 225.50f + (sngAdjustedGrossIncome - 2500.00f) * 0.15f;
        } else {
            sngFederalIncomeTaxWithheld = (strMaritalStatus.equals("S")) ? 845.00f + (sngAdjustedGrossIncome - 5000.00f) * 0.25f : 600.50f + (sngAdjustedGrossIncome - 5000.00f) * 0.20f;
        }

        return sngFederalIncomeTaxWithheld;
    }

    /**
     * Calculates the net earnings after deducting taxes from the gross earnings.
     *
     * @param sngGrossEarnings The gross earnings.
     * @param sngMedicareTax The Medicare tax amount.
     * @param sngSocialSecurityTax The Social Security tax amount.
     * @param sngFederalIncomeTaxWithheld The Federal Income Tax withheld amount.
     * @return The net earnings as a float value.
     */
    public static float calculateNetEarnings(float sngGrossEarnings, float sngMedicareTax, float sngSocialSecurityTax, float sngFederalIncomeTaxWithheld) {
        return sngGrossEarnings - (sngMedicareTax + sngSocialSecurityTax + sngFederalIncomeTaxWithheld);
    }
}



