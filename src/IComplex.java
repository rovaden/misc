public interface IComplex 
{
    public Complex add(Complex that);
    public Complex add(double that);
    public Complex subtract(Complex that);
    public Complex subtract(double that);
    public Complex multiply(Complex that);
    public Complex multiply(double that);
    public Complex divide(Complex that);
    public Complex divide(double that);
    public Complex conjugate();
    public double abs();
    public Complex pow(int n);
    public double re();
    public double im();
    public double Arg();
    public boolean equals(Object o);
    public String toString();
    public static boolean closeEnough(Complex z, Complex w)
    {
        return z.subtract(w).abs() < 1e-12;
    }
}
