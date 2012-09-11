package mathematics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main use of the Fraction class is to easily display fractions. 
 * Additionally, there are methods for basic arithmetic operations.
 * 
 * @author Wolfi
 * @version 1.6
 * @since 1.7
 */
public class Fraction 
{
  protected int Numerator;
	protected int Denominator;
	
	/**
	 * This method computes the greatest common divisor of two integer values.
	 * @param value1
	 * @param value2
	 * @return an integer which is a factor of both parameters 
	 * @see Fraction#leastCommonMultiple(int, int)
	 */
	public static int greatestCommonDivisor(int value1, int value2) {
		value1 = Math.abs(value1);
		value2 = Math.abs(value2);
		
		while(value1 != 0 && value2 != 0)
		{
			if(value1 >= value2) value1 -= value2;
			else value2 -= value1;
		}
		
		if(value1 == 0) return value2;
		else return value1;
	}
	
	/**
	 * This method computes the least common multiple of two integer values.
	 * @param value1
	 * @param value2
	 * @return an integer which is the least multiple of both parameters
	 * @see Fraction#greatestCommonDivisor(int, int)
	 */
	public static int leastCommonMultiple(int value1, int value2)
	{
		value1 = Math.abs(value1);
		value2 = Math.abs(value2);
		
		return value1 * value2 / Fraction.greatestCommonDivisor(value1, value2);
	}
	
	/**
	 * This method compares two Fraction objects.
	 * @param fraction1
	 * @param fraction2
	 * @return a boolean which is true if they are equal and false if they are not
	 */
	public static boolean compare(Fraction fraction1, Fraction fraction2)
	{
		if((fraction1.Denominator == fraction2.Denominator) && (fraction1.Numerator == fraction2.Numerator)) return true;
		return false;
	}
	
	/**
	 * This method compares a Fraction object with an integer.
	 * @param fraction1
	 * @param fraction2
	 * @return a boolean which is true if they are equal and false if they are not
	 */
	public static boolean compare(Fraction fraction, int value)
	{
		return compare(fraction, new Fraction(value));
	}
	
	/**
	 * This method compares a Fraction object with an integer.
	 * @param fraction1
	 * @param fraction2
	 * @return a boolean which is true if they are equal and false if they are not
	 */
	public static boolean compare(int value, Fraction fraction)
	{
		return compare(fraction, new Fraction(value));
	}
	
	/**
	 * This method adds two Fraction objects.
	 * @param fraction1
	 * @param fraction2
	 * @return a Fraction object as the sum of the two given fractions
	 */
	public static Fraction add(Fraction fraction1, Fraction fraction2)
	{
		Fraction result = new Fraction();
		
		result.Denominator = fraction1.Denominator * fraction2.Denominator;
		result.Numerator = fraction1.Numerator * fraction2.Denominator + fraction1.Denominator * fraction2.Numerator;
		result.reduce();
		
		return result;
	}
	
	/**
	 * This method adds a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the sum of the two given values
	 */
	public static Fraction add(Fraction fraction, int value)
	{
		return add(fraction, new Fraction(value));
	}
	
	/**
	 * This method adds a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the sum of the two given values
	 */
	public static Fraction add(int value, Fraction fraction)
	{
		return add(fraction, new Fraction(value));
	}
	
	/**
	 * This method subtracts two Fraction objects.
	 * @param fraction1
	 * @param fraction2
	 * @return a Fraction object as the difference of the two given fractions
	 */
	public static Fraction subtract(Fraction fraction1, Fraction fraction2)
	{
		return add(fraction1, multiply(fraction2, -1));
	}
	
	/**
	 * This method subtracts a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the difference of the two given values
	 */
	public static Fraction subtract(Fraction fraction, int value)
	{
		return add(fraction, value * -1);
	}
	
	/**
	 * This method subtracts a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the difference of the two given values
	 */
	public static Fraction subtract(int value, Fraction fraction)
	{
		return add(value, multiply(fraction, -1));
	}
	
	/**
	 * This method multiplies two Fraction objects.
	 * @param fraction1
	 * @param fraction2
	 * @return a Fraction object as the product of the two given fractions
	 */
	public static Fraction multiply(Fraction fraction1, Fraction fraction2)
	{
		Fraction result = new Fraction(fraction1);
		
		result.Denominator *= fraction2.Denominator;
		result.Numerator *= fraction2.Numerator;
		result.reduce();
		
		return result;
	}
	
	/**
	 * This method multiplies a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the product of the two given values
	 */
	public static Fraction multiply(Fraction fraction1, int value)
	{
		return multiply(fraction1, new Fraction(value));
	}
	
	/**
	 * This method multiplies a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the product of the two given values
	 */
	public static Fraction multiply(int value, Fraction fraction1)
	{
		return multiply(fraction1, new Fraction(value));
	}
	
	/**
	 * This method divide two Fraction objects.
	 * @param fraction1
	 * @param fraction2
	 * @return a Fraction object as the quotient of the two given fractions
	 */
	public static Fraction divide(Fraction fraction1, Fraction fraction2)
	{
		Fraction result = new Fraction(fraction1);
		
		result.Denominator *= fraction2.Numerator;
		result.Numerator *= fraction2.Denominator;
		result.reduce();
		
		return result;
	}
	
	/**
	 * This method divide a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the quotient of the two given values
	 */
	public static Fraction divide(Fraction fraction, int value)
	{
		return divide(fraction, new Fraction(value));
	}
	
	/**
	 * This method divide a Fraction object with an integer
	 * @param fraction
	 * @param value
	 * @return a Fraction object as the quotient of the two given values
	 */
	public static Fraction divide(int value, Fraction fraction)
	{
		return divide(new Fraction(value), fraction);
	}
	
	/**
	 * Returns the value of the first argument raised to the power of the second argument.
	 * @param fraction The Fraction object (base)
	 * @param exponent it's the exponent (must be positive)
	 * @return the value a^b.
	 * @throws IllegalArgumentException is thrown if the exponent is lower than zero.
	 */
	public static Fraction pow(Fraction fraction, int exponent)
	{
		Fraction result;
		if(exponent > 0)
		{	result = new Fraction(fraction);
			for(int i = 1; i < exponent; i++)
			{
				result.Denominator *= fraction.Denominator;
				result.Numerator *= fraction.Numerator;
			}
			result.reduce();
		} else if(exponent == 0)
		{
			result = new Fraction(1);
		}
		else 
		{
			throw new IllegalArgumentException("Exponent " + exponent + " is lower than zero. This function will get implemented soon");
		}
		
		return result;
	}
	
	/**
	 * This method computes the product of an fraction with itself.
	 * @param fraction Fraction object
	 * @return a Fraction object which is the product of the parameter with itself
	 * @see Fraction#expo(Fraction, int)
	 */
	public static Fraction pow(Fraction fraction)
	{
		return pow(fraction, 2);
	}
	
	/**
	 * 
	 * @param fraction1
	 * @param fraction2
	 * @return
	 */
	public static Fraction pow(Fraction fraction1, Fraction fraction2)
	{
		return new Fraction(Fraction.root(Fraction.pow(fraction1, fraction2.Numerator), fraction2.Denominator));		
	}
	
	/**
	 * This method computes the multiplicative inverse or reciprocal of a number. 
	 * It calls Fraction.divide(1, fraction)
	 * @param fraction 
	 * @return a Fraction object which is the reciprocal of the parameter
	 * @see Fraction#divide(int, Fraction)
	 */
	public static Fraction reciprocal(Fraction fraction)
	{
		return Fraction.divide(1, fraction);
	}
	
	/**
	 * This method computes the multiplicative inverse or reciprocal of a number. 
	 * It calls Fraction.divide(1, fraction)
	 * @param fraction 
	 * @return a Fraction object which is the reciprocal of the parameter
	 * @see Fraction#divide(int, Fraction)
	 * @see Fraction#reciprocal(Fraction)
	 */
	public static Fraction reciprocal(int value)
	{
		return Fraction.divide(1, new Fraction(value));
	}
	
	/**
	 * This method returns the absolute value of an Fraction object
	 * @param fraction the argument whose absolute value is to be determined
	 * @return the absolute value of the argument
	 */
	public static Fraction abs(Fraction fraction)
	{
		if(fraction.Numerator < 0) return multiply(fraction, -1);
		else return fraction;
	}
	
	/**
	 * This method returns the double of a Fraction object
	 * @param fraction
	 * @return a double of a Fraction object
	 */
	public static double doubleValue(Fraction fraction)
	{
		return new Double(fraction.Numerator) / new Double(fraction.Denominator);
	}
	
	/**
	 * This method returns the integer of a Fraction object. 
	 * If the value isn't an integer the value is truncation.
	 * @param fraction
	 * @return an integer of a Fraction object
	 */
	public static int intValue(Fraction fraction)
	{
		return fraction.Numerator / fraction.Denominator;
	}
	
	/**
	 * Returns the correctly rounded positive square root of a Fraction object. Special cases:
	 * <ul>
	 * <li>If the argument is zero or less than zero than the result is 0</li>
	 * </ul>
	 * @param fraction fraction object
	 * @return the positive square root of fraction
	 */
	public static Fraction sqrt(Fraction fraction)
	{
		return Fraction.root(fraction, 2);
	}
	
	/**
	 * 
	 * @param fraction
	 * @param degree
	 * @return
	 */
	public static Fraction root(Fraction fraction, int degree)
	{
		if(degree < 1) return null;
		else
		{
			double value = Fraction.doubleValue(fraction);
			value = Math.pow(value, 1.0 / degree);
			return new Fraction(value);
		}
	}
	
	/**
	 * 
	 * @param string
	 * @throws IllegalArgumentException
	 */
	public Fraction(String string)
	{
		Pattern reg = Pattern.compile("([-+]?\\d+)(/([-+]?\\d+))?");
		Matcher m = reg.matcher(string);
		if(m.matches()) {
			Numerator = new Integer(m.group(1));
			if(m.group(3) != null) Denominator = new Integer(m.group(3));
			else Denominator = 1;
		}
		else throw new IllegalArgumentException("wrong Fraction: " + string);
		
		reduce();
	}
	
	/**
	 * 
	 * @param numerator
	 */
	public Fraction(int numerator)
	{
		this(numerator, 1);
	}
	
	/**
	 * 
	 */
	public Fraction()
	{
		this(0,1);
	}

	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator)
	{
		Numerator = numerator;
		Denominator = denominator;
		
		reduce();
	}
	
	/**
	 * 
	 * @param fraction1
	 */
	public Fraction(Fraction fraction1) {
		Denominator = fraction1.Denominator;
		Numerator = fraction1.Numerator;
	}
	
	/**
	 * This constructor converts an double value to a Fraction Objects.
	 * @param value
	 * @param accuracy it's the accuracy of the conversion
	 */
	public Fraction(double value, int accuracy)
	{
		double denom = 0;
		long numerator;
		boolean condition;
		double accur = Math.pow(10, (accuracy+1) * -1);
		
		do
		{
			denom++;
			numerator = Math.round(value * denom);
			if((Math.abs(numerator/denom - value) > accur)) condition = true;
			else condition = false;
		}while(condition);
		
		Numerator = new Long(numerator).intValue();
		Denominator = new Double(denom).intValue();
		
		reduce();
	}
	
	/**
	 * This constructor converts an double value to a Fraction Objects. Therefore it takes an accuracy of 10^-5
	 * @param value
	 * @see Fraction#Fraction(double, int)
	 */
	public Fraction(double value)
	{
		this(value, 5);
	}
	
	/**
	 * 
	 * @throws ArithmeticException
	 */
	protected void reduce() throws ArithmeticException
	{
		if(Denominator == 0) throw new ArithmeticException("/ by zero");
		else if(Denominator < 0)
		{
			Denominator *= -1;
			Numerator *= -1;
		}
		
		int gcd = Fraction.greatestCommonDivisor(Numerator, Denominator);
		Numerator /= gcd;
		Denominator /= gcd;
	}

	/**
	 * Returns a string representation of this Fraction object. 
	 * @return a string representation of this object 
	 */
	public String toString()
	{
		if(Denominator == 1) return new Integer(Numerator).toString();
		else return Numerator + "/" + Denominator;
	}
}
