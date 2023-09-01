# Paystub Calculator in Java

This Java project is a Paystub calculator that calculates Gross Earnings, FICA tax (Medicare and Social Security taxes), Federal Tax Withheld, and Net Amount for each employee based on user input.

## Program Overview

The program performs the following tasks:

- Collects employee information, including Name, Hourly Wage (positive number), Hours Worked (positive number), Withholding Exemptions (0 to 10), and Marital Status (Single or Married).
- Calculates Gross Earnings, FICA Tax (Medicare and Social Security taxes), Federal Income Tax Withheld, and Net Earnings for each employee.
- Validates user input and provides error messages for incorrect data.
- Displays results with two decimal places.
- Uses the `ReadStringFromUser` and `ReadFloatFromUser` methods for user input.

## Input

The application collects the following "required" information for each employee:

1. Name
2. Hourly Wage (must be a positive number)
3. Hours Worked (must be a positive number)
4. Withholding Exemptions (valid values are from 0 to 10)
5. Marital Status (Single or Married)

## Output

The program displays the following for each employee:

1. Gross Earnings
2. FICA Tax (displaying both Medicare and Social Security taxes)
3. Federal Income Tax Withheld
4. Net Earnings

## Process

For each employee, the program calculates the following for their individual payroll for the week:

- Gross Earnings: Hourly wage times hours worked (with time and a half after 40 hours).
- FICA Tax: The sum of 6.2% of the total wages (Social Security tax) and 1.45% of total wages (Medicare tax).
- Federal Income Tax Withheld: Subtract $55.77 from the gross earnings for each withholding exemption, giving the Adjusted Gross Income. Based on the Adjusted Gross Income, the program determines the federal tax based on marital status.

### Federal Tax Withheld Parameters

| Adjusted Gross Income   | Income Tax Withheld (Single) | Income Tax Withheld (Married) |
| ----------------------- | ---------------------------- | ------------------------------ |
| $0 to $50.99             | $0                           | $0                             |
| $51 to $500.99           | 10% over $51                 | 5% over $51                     |
| $501 to $2,500.99        | $45.00 + 15% of amount over $500.00 | $22.50 + 10% of amount over $500.00 |
| $2,501 to $5,000         | $345.00 + 20% of amount over $2,500 | $225.50 + 15% of amount over $2,500 |
| Over $5,000              | $845.00 + 25% of amount over $5,000 | $600.50 + 20% of amount over $5,000 |

- Net Earnings: Gross Earnings - FICA taxes - Federal Income Tax Withheld

## How to Use the Program

1. Ensure you have Java installed on your system.
2. Compile the Java program.
3. Run the compiled program.
