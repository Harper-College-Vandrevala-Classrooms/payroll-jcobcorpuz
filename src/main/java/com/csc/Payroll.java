package com.csc;

import java.util.Scanner;

public class Payroll {
  private static final int HOURS = 40;
  private static final double SOCIAL_SECURITY_TAX = 0.06;
  private static final double FEDERAL_TAX = 0.14;
  private static final double STATE_TAX = 0.05;
  private static final double UNION_DEDUCTION = 10.00;
  private static final double INSURANCE_3_PLUS = 35.00;
  private static final double INSURANCE_2_MINUS = 15.00;


  public static double calculateGrossPay(double hoursWorked, double hourlyRate){
      double overtimeRate = hourlyRate * 1.5;
      if(hoursWorked > HOURS){
          double overtimeHours = hoursWorked - HOURS;
          return (HOURS * hourlyRate) + (overtimeHours * overtimeRate);
      }
      else{
          return hoursWorked * hourlyRate;
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

  public static double calculateRequiredDeductions(double grossPay){
      return calculateSocialSecurity(grossPay) + calculateFederalTax(grossPay) + calculateStateTax(grossPay);
  }

  public static double calculateTotalDeductions(double grossPay, int dependents){
      return calculateRequiredDeductions(grossPay) + calculateUnionDeduction() + calculateInsurance(dependents);
  }

  public static double calculateNetPay(double grossPay, double totalDeductions){
      return grossPay - totalDeductions;
  }

  public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);

      System.out.println("Welcome to my Payroll Program");

      System.out.print("How many hours did you work this week? ");
      double hoursWorked = scanner.nextDouble();

      double hourlyRate = -1;
      while(hourlyRate < 0){
          System.out.print("What is your hourly pay rate? ");
          hourlyRate = scanner.nextDouble();
          if(hourlyRate < 0){
              System.out.println("Pay rate cannot be negative, please enter a positive value.");
          }
      }

      System.out.print("How many children do you have? ");
      int dependents = scanner.nextInt();
      if (dependents < 0){
          dependents = 0;
      }

      double grossPay = Payroll.calculateGrossPay(hoursWorked, hourlyRate);

      double requiredDeductions = Payroll.calculateRequiredDeductions(grossPay);
      double unionDeduction = Payroll.calculateUnionDeduction();
      double insurance = Payroll.calculateInsurance(dependents);
      double netPayBeforeUnionInsurance = grossPay - requiredDeductions;

      System.out.println("\nPayroll Stub:");
      System.out.printf("Hours: %.1f%n", hoursWorked);
      System.out.printf("Rate: $%.2f/hr%n", hourlyRate);
      System.out.printf("Gross: $%.2f%n", grossPay);
      System.out.printf("SocSec: $%.2f%n",Payroll.calculateSocialSecurity(grossPay));
      System.out.printf("FedTax: $%.2f%n", Payroll.calculateFederalTax(grossPay));
      System.out.printf("StTax: $%.2f%n", Payroll.calculateStateTax(grossPay));

      if(netPayBeforeUnionInsurance >= (unionDeduction + insurance)){
          double totalDeductions = requiredDeductions + unionDeduction + insurance;
          double netPay = grossPay - totalDeductions;

          System.out.printf("Union: $%.2f%n", unionDeduction);
          System.out.printf("Ins: $%.2f%n", insurance);
          System.out.printf("Net: $%.2f%n", netPay);
      }
      else{
          double netPay = netPayBeforeUnionInsurance;

          System.out.printf("Net: $%.2f%n", netPay);
          System.out.println("\nThe employee still owes:");
          System.out.printf("Union: $%.2f%n", unionDeduction);
          System.out.printf("Ins: $%.2f%n", insurance);
      }

      System.out.println("\n Thank you for using my Payroll Program!");

      scanner.close();
  }
}
