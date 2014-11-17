package blackjack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Ethan on 11/16/2014.
 */
public class Poker {
    HashMap<Integer, Image> cards = new HashMap<Integer, Image>();
    Image image=null;

    public static void main(String[] args) {
        Poker p = new Poker();
    }

    public Poker() {

        try {
            for(int i=1;i<=53;i++) {
                image=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/poker_image/"+i+".jpg"));
                cards.put(i,image);
                System.out.println(i+" is ok!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }



}