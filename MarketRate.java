package com.daimler.ecommerce.eapplication.parts;

import java.time.LocalDate;
import java.util.Random;

import javax.inject.Singleton;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;
 
//@Creatable
//@Singleton
public class MarketRate  extends ContextFunction{

	private int value;
	private LocalDate currDate;

	public LocalDate getCurrDate() {
		return LocalDate.now();
	}

	public void setCurrDate(LocalDate currDate) {
		this.currDate = currDate;
	}

	public int getValue() {
		return new Random().nextInt();
	}

	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public Object compute(final IEclipseContext context) {
	return Math.random();
	}
	
}
