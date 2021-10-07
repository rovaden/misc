public class Complex implements IComplex 
{
    public static final Complex ZERO;
    public static final Complex ONE;
    public static final Complex I;

    private final double re;
    private final double im;

    static
    {
        ZERO = new Complex(0.0);
        ONE = new Complex(1.0);
        I = new Complex(0.0, 1.0);
    }
    public static void main(String[] args) {
        Complex cmplx = new Complex(2, 4);
        Complex cmplx2 = new Complex(4, 3);
        Complex cmplx3 = new Complex(2, 4);
        System.out.println(cmplx.equals(cmplx2) + " " + cmplx.equals(cmplx3));
    }

    /**
     * Creates a Complex Number with the specified real and imaginary components.
     *
     * @param  re  the real number of the complex number
     * @param  im  the coefficient of the i, the imaginary part of the complex number
     * 
     */
    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    /**
     * Creates a Complex Number with the specified real component and a default imaginary 
     * vaule (coefficient of i) of 0.0.
     * 
     * @param  re  the real number of the complex number
     * 
     */    
    public Complex(double re){
        this.re = re;
        this.im = 0.0;
    
    }

    /**
     * Creates a Complex Number with the default real and imaginary 
     * vaules (coefficient of i) of 0.0.
     * 
     */ 
    public Complex(){
        this.re = 0.0;
        this.im = 0.0;
    }

    /**
     * Adds the specified complex number to the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the Complex number being added to the current complex number
     */
    public Complex add(Complex that)
    {
        return new Complex((that.re()+re()), (that.im()+im()));
    }
    /**
     * Adds the specified number to the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the number being added to the current complex number
     */
    public Complex add(double that)
    {
        return new Complex(re()+that, im());
    }
    /**
     * Subtracts the the specified complex number from the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the Complex number being subtracted from the current complex number
     */
    public Complex subtract(Complex that)
    {
        return new Complex((re() -that.re()), (im() - that.im()));
    }
    /**
     * Subtracts the the specified number from the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the number being subtracted from the current complex number
     */
    public Complex subtract(double that)
    {       
        return new Complex((re() - that), im());
    }
    /**
     * Multiplies the the specified complex number with the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the Complex number being multiplied with the current complex number
     */
    public Complex multiply(Complex that)
    {
        double re = (that.re() * re()) - (that.im() * im());
        double im = (that.im() * re()) + (that.re() * im());
        return new Complex(re, im);
    }
    /**
     * Multiplies the the specified number with the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the number being multiplied with the current complex number
     */
    public Complex multiply(double that)
    {
        return new Complex((that * re()), (that * im()));
    }
    /**
     * Divides the the specified complex number with the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the Complex number being divided with the current complex number
     */
    public Complex divide(Complex that)
    {
        double re = ((that.re()*re())+ (that.im() * im()))/((Math.pow(that.re(), 2)) + (Math.pow(that.im(), 2)));
        double im = ((im() * that.re()) - (re() * that.im()))/((Math.pow(that.re(), 2)) + (Math.pow(that.im(), 2)));
        return new Complex(re, im);
    }
    /**
     * Divides the the specified number with the current complex number {@code Complex} 
     * instance.  
     *
     * @param that the number being divided with the current complex number
     */
    public Complex divide(double that)
    {
        return new Complex(re()/that, im()/that);
    }
    /**
     * Returns the conjugate of the currect complex number 
     *
     */
    public Complex conjugate()
    {
        return new Complex(re(), -im());
    }
    /**
     * The absolute value of the {@code Complex} complex number
     * 
     */
    public double abs()
    {
        return Math.sqrt((re() * re()) + (im() * im()));
    }
    /**
     * Taking the initialized complex number {@code Complex} and
     * raising it to a given power
     * 
     * @param n the power the complex number is being raised to
     */
    public Complex pow(int n)
    {
        if (n==0){
            return Complex.ONE;
        } else if (n < 0) {
            return Complex.ONE.divide(pow(-n));
        } else {
            return new Complex(re(), im()).multiply(pow(n-1));
        }
    }

    /**
     * Returns the real number of the complex number
     * 
     * @return re, the real number
     */
    public double re()
    {
        return re;
    }
    /**
     * Returns the imaginary nnumber or the coefficient 
     * of i of the complex number
     * 
     * @return im, the imaginary number
     */
    public double im()
    {
        return im;
    }
    /**
     * Return the angle between the complex number and the x-axis
     * 
     * @return the angle in radians
     */
    public double Arg()
    {
        return Math.atan(im/re);
    }
    /**
     * Returns a boolean specifying if an object, specificaly for other complex numbers
     * of type Complex {@code Complex}, to see if they are the same
     * 
     */
    public boolean equals(Object o)
    {
        if (o.toString().equals(new Complex(re(), im()).toString()))  return true;
        return false;
    }
    /**
     * Return the complex number as a string
     * 
     */
    public String toString()
    {
        return (re() + " + " + im() + "i");
    }
}
