import java.util.Arrays;

public class haha {
    public static void main(String[] args) {
        int[] ags = {1,2,3,4,5,6,7};
        mooo(ags);
    }

    public static void mooo(int[] args){
        Arrays.sort(args);
        int a = args[0];
        int b = args[1];
        int sum = args[args.length-1];
        int c = sum - a - b;
        int[] solution = {a,b,c};
        System.out.println(a + ", " + b + ", " + c);;

    }
}
