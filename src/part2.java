import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

class part2 {
  public static void main(String[] args) {
    File file = new File("src/loremimpsum.txt");
    // sentencePrintCount(file);
    // averageLetterPos(file);
    System.out.println(exponentiate(2, -2));
  }

  static void sentencePrintCount (File param){
    List<String> list = Collections.<String>emptyList();
    try{
        list = Files.readAllLines(param.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
    String[] sentences = list.get(0).split("\\.");
    System.out.println(" sentences" + sentences);
    for (int i = 0; i < sentences.length; i ++){
      String[] words = sentences[i].split("\\+s");      
      System.out.println(sentences[i] + "." + " words: " + words.length);
    }
  }

  static void averageLetterPos (File param){
    List<String> list = Collections.<String>emptyList();
    try{
        list = Files.readAllLines(param.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
    String[] sentences = list.get(0).split("\\.\\s+");
    System.out.println(sentences.toString());
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    for (int j = 0; j < alphabet.length; j++){  
      int avg = 0;
      int qty = 0;
      for (int i = 0; i < sentences.length; i ++){
        char[] letters = sentences[i].toCharArray();   
        for (int k = 0; k < letters.length; k++){
          if (letters[k] == alphabet[j]){ 
            avg += k;
            qty++;
          }
        }  
      }
      if (avg !=0 ) avg /= qty;
      System.out.println(alphabet[j] + ": "+ (avg!=0?avg:"not found"));
    }
  }

  static long exponentiate(long x, long n) {
    if(n==0){
      System.out.println("base");
      return 1;
    } else if(n < 0){
      System.out.println("NEG");
        return (1/exponentiate(x, -n));
    } else if (n %2 ==0) {
      System.out.println("even");
      return exponentiate(x*x, n/2);
    } else {
      System.out.println("odd");
      return x* exponentiate(x*x,(n-1)/2); 
    } 
  }
}