import java.math.*;
import java.util.List;

class Main {
  public static void main(String[] args) {
  }

  int cubeNumber(int param){
    return param*param*param;
  }

  boolean compareString(String x, String y){
    if (x.equals(y)){
      return true;
    } else{
      return false;
    }
  }

  boolean compareInt(int x){
    if (x*x > 500 && 3*x < 1000 && (4*x) + 2 < 1050){
      return true;
    } else {
      return false;
    }
  }
  
  String grabChar(List<String> strings){
    char[] charString = new char[strings.size()];
    for (int i=0; i< strings.size(); i++){
        charString[i] = strings.get(i).charAt(0);
    }
    return charString.toString();
  }

  double avgValue(float a, float b, int n){
      double sum = 0.0;
      for (int i=0; i < (b-a)/ (n+1); i++){
        sum += Math.pow((a + i * (n+1)), 3);
      }
      return sum/((b-a)/(n+1));
  }

  boolean contains(String s1, String s2){
      char[] c1 = s1.toCharArray();
      char[] c2 = s2.toCharArray();
      for(int i = 0; i < c1.length; i++){
          if ( c1[i] == c2[i]){
              for (int j = 0; j < c2.length; j++){
                  if (c1[i+j] == c2[j]){
                      return false;
                  }
              }
              return true;
          }
      }
      return false;
  }

}