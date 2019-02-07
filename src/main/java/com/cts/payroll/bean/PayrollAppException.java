package com.cts.payroll.bean;

public class PayrollAppException extends Exception {
	public PayrollAppException(String errorMessage) {
		super(errorMessage);
	}
}