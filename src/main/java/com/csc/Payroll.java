package com.csc;

import java.util.Scanner;

public class Payroll {
  private static final int HOURS = 40;
  private static final double HOURLY_RATE = 16.78;
  private static final double OVERTIME_RATE = HOURLY_RATE * 1.5;
  private static final double SOCIAL_SECURITY_TAX = 0.06;
  private static final double FEDERAL_TAX = 0.14;
  private static final double STATE_TAX = 0.05;
  private static final double UNION_DEDUCTION = 10.00;
  private static final double INSURANCE_3_PLUS = 35.00;
  private static final double INSURANCE_2_MINUS = 15.00;


  public static double calculateGrossPay(double hoursWorked){
      if(hoursWorked > HOURS){
          double overtimeHours = hoursWorked - HOURS;
          return (HOURS * HOURLY_RATE) + (overtimeHours * OVERTIME_RATE);
      }
      else{
          return hoursWorked * HOURLY_RATE;
      }
  }

  public static double calculateSocialSecurity(double grossPay){
      return grossPay * SOCIAL_SECURITY_TAX;
  }

  public static double calculateFederalTax(double grossPay){
      return grossPay * FEDERAL_TAX;
  }

  public static double calculateStateTax(double grossPay){
      return grossPay * STATE_TAX;
  }

  public static double calculateUnionDeduction(){
      return UNION_DEDUCTION;
  }

  public static double calculateInsurance(int dependents){
      if(dependents >= 3){
          return INSURANCE_3_PLUS;
      }
      else{
          return INSURANCE_2_MINUS;
      }
  }

  public static double calculateTotalDeductions(double grossPay, int dependents){
      double socialSecurity = calculateSocialSecurity(grossPay);
      double federalTax = calculateFederalTax(grossPay);
      double stateTax = calculateStateTax(grossPay);
      double unionDeduction = calculateUnionDeduction();
      double insurance = calculateInsurance(dependents);

      return socialSecurity + federalTax + stateTax + unionDeduction + insurance;
  }

  public static double calculateNetPay(double grossPay, double totalDeductions){
      return grossPay - totalDeductions;
  }

  public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);

      System.out.println("Welcome to my Payroll Program");

      System.out.print("How many hours did you work this week? ");
      double hoursWorked = scanner.nextDouble();

      System.out.print("How many children do you have? ");
      int dependents = scanner.nextInt();

      double grossPay = Payroll.calculateGrossPay(hoursWorked);

      double totalDeductions = Payroll.calculateTotalDeductions(grossPay, dependents);

      double netPay = Payroll.calculateNetPay(grossPay, totalDeductions);

      System.out.println("\nPayroll Stub:");
      System.out.printf("Hours: %.1f%n", hoursWorked);
      System.out.printf("Rate: $%.2f/hr%n", 16.78);
      System.out.printf("Gross: $%.2f%n", grossPay);
      System.out.printf("SocSec: $%.2f%n",Payroll.calculateSocialSecurity(grossPay));
      System.out.printf("FedTax: $%.2f%n", Payroll.calculateFederalTax(grossPay));
      System.out.printf("StTax: $%.2f%n", Payroll.calculateStateTax(grossPay));
      System.out.printf("Union: $%.2f%n", Payroll.calculateUnionDeduction());
      System.out.printf("Ins: $%.2f%n", Payroll.calculateInsurance(dependents));
      System.out.printf("Net: $%.2f%n", netPay);

      System.out.println("\n Thank you for using my Payroll Program!");

      scanner.close();
  }
}
