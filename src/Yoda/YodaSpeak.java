package Yoda;

/**
 * Created by Ethan on 11/14/2014.
 */
public class YodaSpeak {
    static String yoda = "The force is strong with your like!";

    public static void main(String[] args) {
        YodaSpeak ys = new YodaSpeak(yoda);
    }

    public YodaSpeak(String yoda) {

        String[] words = yoda.split(" ");
        String reversedyoda = words[words.length-1];
        for(int i=words.length-2;i>=0;i--){
            reversedyoda = reversedyoda +" "+words[i];
        }

        System.out.println(reversedyoda);
    }
}
