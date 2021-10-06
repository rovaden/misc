import java.math.BigInteger;
import java.util.ArrayDeque;

public class bigInt {
    public static void main(String[] args) {
        System.out.println(factorial(1000));
        Iterable<String> it = new ArrayDeque<>();
    }
    
    public static BigInteger factorial(int number) {
        BigInteger res = BigInteger.ONE;

        for(int i = number; i > 0; i--){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}
