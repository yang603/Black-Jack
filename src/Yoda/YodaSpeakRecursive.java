package Yoda;

/**
 * Created by Ethan on 11/14/2014.
 */
public class YodaSpeakRecursive {
    static String yoda = "The force is strong with your like!";
    static String reversedwords="";
    static String[] words;
    static int n;

    public static void main(String[] args) {
        YodaSpeakRecursive ysr = new YodaSpeakRecursive();
        String rw = ysr.reverse(n-1);
        System.out.print(rw);
    }

    public YodaSpeakRecursive() {
        words = yoda.split(" ");
        n=words.length;
    }

    public static String reverse(int i){
        if(i==-1){
            return reversedwords;
        }
        else if(i==n-1){
            reversedwords=reversedwords+words[i];
            reverse(i-1);
        }
        else{
            reversedwords=reversedwords+" "+words[i];
            reverse(i-1);
        }
        return reversedwords;
    }
}
