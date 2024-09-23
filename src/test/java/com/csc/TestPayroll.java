package com.csc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayroll {

  Payroll payroll;
  private static final double HOURLY_RATE = 16.78;

  @BeforeEach
  void setUp() {
    payroll = new Payroll();
  }

  @Test
  void testCalculateGrossPayWithoutOvertime(){
    double hoursWorked = 40.0;
    double expectedGrossPay = 40.0 * 16.78;
    assertEquals(expectedGrossPay, Payroll.calculateGrossPay(hoursWorked, HOURLY_RATE));
  }

  @Test
  void testCalculateGrossPayWithOvertime(){
    double hoursWorked = 50.0;
    double expectedGrossPay = (40.0 * 16.78) + (10.0 * (16.78 * 1.5));
    assertEquals(expectedGrossPay, Payroll.calculateGrossPay(hoursWorked, HOURLY_RATE));
  }

  @Test
  void testCalculateSocialSecurity(){
    double grossPay = 450.0;
    double expectedSocialSecurity = 450.0 * 0.06;
    assertEquals(expectedSocialSecurity, Payroll.calculateSocialSecurity(grossPay));
  }

  @Test
  void testCalculateFederalTax(){
    double grossPay = 450;
    double expectedFederalTax = 450.0 * 0.14;
    assertEquals(expectedFederalTax, Payroll.calculateFederalTax(grossPay));
  }

  @Test
  void testCalculateStateTax(){
    double grossPay = 450.0;
    double expectedStateTax = 450.0 * 0.05;
    assertEquals(expectedStateTax, Payroll.calculateStateTax(grossPay));
  }

  @Test
  void testCalculateInsuranceWithLessThan3Dependents(){
    int dependents = 2;
    double expectedInsurance = 15.00;
    assertEquals(expectedInsurance, Payroll.calculateInsurance(dependents));
  }

  @Test
  void testCalculateInsuranceWith3OrMore(){
    int dependents = 3;
    double expectedInsurance = 35.00;
    assertEquals(expectedInsurance, Payroll.calculateInsurance(dependents));
  }

  @Test
  void testCalculateTotalDeductionsWith2Dependents(){
    double grossPay = 450.0;
    int dependents = 2;
    double expectedTotalDeductions = (450.0 * 0.06) + (450.0 * 0.14) + (450.0 * 0.05) + 10.00 + 15.00;
    assertEquals(expectedTotalDeductions, Payroll.calculateTotalDeductions(grossPay, dependents));
  }

  @Test
  void testCalculateTotalDeductionsWith3Dependents(){
    double grossPay = 450.0;
    int dependents = 3;
    double expectedTotalDeductions = (450.0 * 0.06) + (450.0 * 0.14) + (450.0 * 0.05) + 10.00 + 35.00;
    assertEquals(expectedTotalDeductions, Payroll.calculateTotalDeductions(grossPay, dependents));
  }

  @Test
  void testCalculateNetPay(){
    double grossPay = 450.0;
    double totalDeductions = (450.0 * 0.06) + (450.0 * 0.14) + (450.0 * 0.05) + 10.00 + 35.00;
    double expectedNetPay = grossPay - totalDeductions;
    assertEquals(expectedNetPay, Payroll.calculateNetPay(grossPay,totalDeductions));
  }
}
