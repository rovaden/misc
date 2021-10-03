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
    topLetterFind(file);
    // System.out.println(exponentiate(2, -2));
  }

  static void sentencePrintCount (File param){
    List<String> list = Collections.<String>emptyList();
    try{
        list = Files.readAllLines(param.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println(list.get(0));
    for(int j = 0; j < list.size(); j++){
      String[] sentences = list.get(j).split("\\.\\s+");
      System.out.println(sentences.length);
      for (int i = 0; i < sentences.length; i ++){
        String[] words = sentences[i].split("\\s+");      
        System.out.println(sentences[i] + "." + " words: " + words.length);
      }
    }
  }

  static void averageLetterPos (File param){
    List<String> list = Collections.<String>emptyList();
    try{
        list = Files.readAllLines(param.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    for (int j = 0; j < alphabet.length; j++){  
      int avg = 0;
      int qty = 0;
      for (int r = 0; r < list.size(); r++){
        String[] sentences = list.get(0).split("\\.\\s+");
        for (int i = 0; i < sentences.length; i ++){
          char[] letters = sentences[i].toCharArray();   
          for (int k = 0; k < letters.length; k++){
            if (letters[k] == alphabet[j]){ 
              avg += (k);
              break;
            }
          } 
          qty++; 
        }
      }
      avg /= qty;
      System.out.println(alphabet[j] + ": " + ((avg!=0)?avg:"not found"));
    }
  }

  static void topLetterFind(File param){
    List<String> list = Collections.<String>emptyList();
    try{
        list = Files.readAllLines(param.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    for (int j = 0; j < alphabet.length; j++){  
      int topCt = 0;
      String word = "";
      for (int r = 0; r < list.size(); r++){
        String[] sentences = list.get(0).split("\\.\\s+");
        for (int i = 0; i < sentences.length; i ++){
          String[] words = sentences[i].split("\\s+");
          for (int k = 0; k < words.length; k++){
            int count = 0;
            char[] letters = words[k].toCharArray();
            for (int p = 0; p < letters.length; p++){
              if (letters[p] == alphabet[j]) count++;
            }
            if (count > topCt){ 
              word = words[k];
              topCt = count;
            }
          }
        }
      }
      System.out.println(alphabet[j] + ": " + ((word!="")?word:"not found"));
    }
  }

  static double exponentiate(int x, int n) {
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