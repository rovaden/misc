import java.io.*;
import java.util.StringTokenizer;

public class code {
    static BufferedReader r;
	static PrintWriter pw;
	static {
		try {
			r = new BufferedReader(new FileReader("whereami.in"));
			pw = new PrintWriter(new FileWriter("whereami.out"));
		} catch (IOException e) {}
	}

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(r.readLine());
        int number = Integer.parseInt(st.nextToken());
        String mailboxes = st.nextToken();
        pw.println(check(number, mailboxes));
        pw.close();
    }

    public static int check(int number, String mailboxes){
        char[] letters = new char[number];
        int[] match = new int[2];
        for(int i =0; i < number; i++){
            letters[i] = mailboxes.charAt(i);
        }
        for (int i = 0; i < letters.length; i++) {
            for (int j = i + 1 ; j < letters.length; j++) {
                 if (letters[i] == (letters[j])) {
                    return j;
                 }
            }
        }
        return number;
    }
    
}
