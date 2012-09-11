package mathematics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
	public static char complexUnit = 'i';
	
	private double real;
	private double imaginary;
	
	public static Complex getConjugateComplexValue(Complex complex)
	{
		return new Complex(complex.real, complex.imaginary * -1);
	}
	
	public static Complex add(Complex summand1, Complex summand2)
	{
		Complex result = new Complex(summand1);
		result.real += summand2.real;
		result.imaginary += summand2.imaginary;
		return result;
	}
	
	public static Complex add(Complex summand1, double summand2)
	{
		return add(summand1, new Complex(summand2));
	}
	
	public static Complex add(double summand1, Complex summand2)
	{
		return add(new Complex(summand1), summand2);
	}
	
	public static Complex subtract(Complex summand1, Complex summand2)
	{
		return add(summand1, multiply(summand2, -1));
	}
	
	public static Complex subtract(Complex summand1, double summand2)
	{
		return add(summand1, summand2 * -1);
	}
	
	public static Complex subtract(double summand1, Complex summand2)
	{
		return add(summand1, multiply(summand2, -1));
	}
	
	
	public static Complex multiply(Complex summand1, double summand2) {
		return multiply(summand1, new Complex(summand2));
	}
	
	public static Complex multiply(double summand1, Complex summand2)
	{
		return multiply(new Complex(summand1), summand2);
	}

	public static Complex multiply(Complex summand1, Complex summand2) {
		Complex result = new Complex();
		
		result.real = summand1.real * summand2.real - summand1.imaginary * summand2.imaginary;
		result.imaginary = summand1.real * summand2.imaginary + summand1.imaginary * summand2.real;
		
		return result;
	}
	
	public static Complex divide(Complex summand1, Complex summand2)
	{
		return null;
	}

	public Complex(String string) throws IllegalArgumentException
	{
		Pattern reg = Pattern.compile("([+-]?\\d+\\.?\\d*)?(([+-])?i(\\d+\\.?\\d*)?)?");
		Matcher m = reg.matcher(string);
		if(m.matches() && string.length() > 0)
		{
			
			if(m.group(1) != null)
			{
				real = new Double(m.group(1));
			}
			else real = 0;
			
			if(m.group(2) != null)
			{
				String value = "";
				if(m.group(3) != null)  value += m.group(3);
				if(m.group(4) != null)
				{
					value += m.group(4);
					imaginary = new Double(value);
				}
				else 
				{
					imaginary = new Double((value+="1"));
				}
				
				if(m.group(3) == "-") imaginary = imaginary * -1;
			}
			else imaginary = 0;
		}
		else
		{
			throw new IllegalArgumentException("wrong complex number: " + string);
		}
	}
	
	public Complex(double realvalue, double imagvalue)
	{
		real = realvalue;
		imaginary = imagvalue;
	}
	
	public Complex(double realvalue)
	{
		this(realvalue, 0);
	}
	
	
	public Complex(Complex complex) {
		real = complex.real;
		imaginary = complex.imaginary;
	}

	public Complex() {
		this(0, 0);
	}

	public String toString()
	{
		String output;
		
		if(real == 0 && imaginary == 0)
		{
			output = "0";
		}
		else if(real == 0)
		{
			if(imaginary > 0) 
			{
				if(imaginary == 1) output = Complex.complexUnit + "";
				else output = Complex.complexUnit + new Double(imaginary).toString();
			}
			else 
			{
				if(imaginary == -1) output = "-" + Complex.complexUnit;
				else output = "-" + Complex.complexUnit + Math.abs(imaginary);
			}
		}
		else if(imaginary == 0)
		{
			output = new Double(real).toString();
		}
		else
		{
			if(imaginary > 0) 
			{
				if(imaginary == 1) output = real + " + " + Complex.complexUnit;
				else output = real + " + " + Complex.complexUnit + imaginary;
			}
			else 
			{
				if(imaginary == -1) output = real + " - " + Complex.complexUnit;
				else output = real + " - " + Complex.complexUnit + Math.abs(imaginary);
			}
		}
		
		return output;
	}
	
}
