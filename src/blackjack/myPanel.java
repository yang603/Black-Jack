package blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by Ethan on 11/16/2014.
 */
public class myPanel extends JFrame {

    private JTextField Jtf_player_add;
    private JButton Jb_add;
    private JButton Jb_player_hit;
    private JButton Jb_player_stand;
    private JButton Jb_player_deal;
    private JButton Jb_player_exit;
    private JPanel MyPanel;
    private JPanel Jp_player;
    private JLabel Jl_player_bet;
    private JLabel Jl_player_account;
    private JPanel Jp_dealer;
    private JLabel Jl_dealer_account;
    private JLabel Jl_dealer_bet;
    private JPanel Jp_dealer_cards;
    private JPanel Jp_player_cards;
    private JTextArea Jta_game_record;
    private JPanel Jp_info;
    private Poker card;
    private Random ran;

    int[] dealer=new int[2];
    int[] player=new int[2];
    int count=0;

    public static void main(String[] args) {
        myPanel mp = new myPanel();
    }

    public myPanel() {
        card = new Poker();
        createPanel();
        this.add(MyPanel);
        this.setSize(1380, 720);
        this.setLocation(0,0);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createPanel() {
        createDealer();
        createPlayer();
        createCardsInfo();
        MyPanel = new JPanel();
        MyPanel.setLayout(new BorderLayout());
        MyPanel.add(Jp_dealer, BorderLayout.NORTH);
        MyPanel.add(Jp_info, BorderLayout.CENTER);
        MyPanel.add(Jp_player, BorderLayout.SOUTH);
    }


    private void createDealer() {
        Jp_dealer = new JPanel();
        Jp_dealer.setLayout(new GridLayout(1, 2));
        Jl_dealer_account = new JLabel("Account: 1000 $");
        Jl_dealer_bet = new JLabel("Bet: 0 $");
        Jp_dealer.add(Jl_dealer_account);
        Jp_dealer.add(Jl_dealer_bet);
        Jp_dealer.setBorder(BorderFactory.createTitledBorder("Dealer"));
    }

    private void createPlayer() {
        mylistener ml = new mylistener();
        Jp_player = new JPanel();
        Jp_player.setLayout(new GridLayout(4, 2));

        Jl_player_account = new JLabel("Account: 1000 $");
        Jl_player_bet = new JLabel("Bet: 0 $");
        Jtf_player_add = new JTextField();

        Jb_add = new JButton("Add Money");
        Jb_add.addActionListener(ml);
        Jb_add.setActionCommand("Add");

        Jb_player_hit = new JButton("Hit");
        Jb_player_hit.addActionListener(ml);
        Jb_player_hit.setActionCommand("Hit");

        Jb_player_stand = new JButton("Stand");
        Jb_player_stand.addActionListener(ml);
        Jb_player_stand.setActionCommand("Stand");

        Jb_player_deal = new JButton("Deal");
        Jb_player_deal.addActionListener(ml);
        Jb_player_deal.setActionCommand("Deal");

        Jb_player_exit = new JButton("Exit");
        Jb_player_exit.addActionListener(ml);
        Jb_player_exit.setActionCommand("Exit");

        Jp_player.add(Jl_player_account);
        Jp_player.add(Jl_player_bet);
        Jp_player.add(Jtf_player_add);
        Jp_player.add(Jb_add);
        Jp_player.add(Jb_player_hit);
        Jp_player.add(Jb_player_stand);
        Jp_player.add(Jb_player_deal);
        Jp_player.add(Jb_player_exit);
        Jp_player.setBorder(BorderFactory.createTitledBorder("Player"));
    }


    private void createCardsInfo() {
        Jp_info = new JPanel();
        Jp_info.setLayout(new GridLayout(1, 3));
        Jp_dealer_cards = new JPanel();
        Jp_dealer_cards.setBorder(BorderFactory.createTitledBorder("Dealer's cards"));
        Jp_player_cards = new JPanel();
        Jp_player_cards.setBorder(BorderFactory.createTitledBorder("player's cards"));
        Jta_game_record = new JTextArea();
        Jta_game_record.setText("Are you ready? \r\nPlease press Deal to start your adventure!");
        JScrollPane Jsp_game_record = new JScrollPane(Jta_game_record);
        Jp_info.add(Jp_dealer_cards);
        Jp_info.add(Jsp_game_record);
        Jp_info.add(Jp_player_cards);
        Jp_info.setBorder(BorderFactory.createTitledBorder("Information"));
    }


    class mylistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add")) {

                    double money = Double.parseDouble(Jtf_player_add.getText());
                    String[] account = Jl_player_account.getText().split(" ");
                    double budget = Double.parseDouble(account[1]);
                    if (money<=0){
                        Jta_game_record.append("\r\nInvalid input, please try again!");
                        Jl_player_bet.setText("Bet: 0 $");
                    }
                    else if (budget < money) {
                        Jta_game_record.append("\r\nNot enough money");
                        Jl_player_bet.setText("Bet: 0 $");
                    } else {
                        Jl_player_bet.setText("Bet: " + money + " $");
                        Jl_player_account.setText("Account: " + (budget - money) + " $");
                    }
                    Jtf_player_add.setText("");
                    Jtf_player_add.updateUI();
                    Jl_player_bet.updateUI();
                    Jl_player_account.updateUI();

            }else if (e.getActionCommand().equals("Hit")){
                if(count==1) {
                    ran = new Random();
                    int i = ran.nextInt(52);
                    JLabel picLabel = new JLabel(new ImageIcon(card.cards.get(i)));
                    Jp_player_cards.add(picLabel);
                    Jp_player_cards.updateUI();
                    cardPoints(i, "player");
                    isWin();
                }else{
                    Jta_game_record.append("\r\nPlease press Deal to start game!");
                }

            }else if (e.getActionCommand().equals("Stand")){
                if(count==1) {
                    ran = new Random();
                    int count = ran.nextInt(5);
                    for (int i = 0; i < count; i++) {
                        int j = 1 + ran.nextInt(51);
                        JLabel picLabel = new JLabel(new ImageIcon(card.cards.get(j)));
                        Jp_dealer_cards.add(picLabel);
                        Jp_dealer_cards.updateUI();
                        cardPoints(j, "dealer");
                        isWin();
                    }
                }else{
                    Jta_game_record.append("\r\nPlease press Deal to start game!");
                }

            }else if (e.getActionCommand().equals("Deal")){
                if(count==1){
                    Jta_game_record.append("\r\nYou can not quit the current game!");
                }else {
                    String[] account = Jl_player_bet.getText().split(" ");
                    double money = Double.parseDouble(account[1]);
                    if (money<=0){
                        Jta_game_record.append("\r\nInvalid input, please add money and try again!");
                        Jl_player_bet.setText("Bet: 0 $");
                    }else {
                        count = 1;
                        newGame();
                    }
                }
            }else if (e.getActionCommand().equals("Exit")){
                System.exit(0);
            }
        }
    }

    public void newGame(){
        for(int i=0;i<2;i++) {
            ran = new Random();
            int j=ran.nextInt(52);
            JLabel picLabel = new JLabel(new ImageIcon(card.cards.get(j)));
            Jp_player_cards.add(picLabel);
            Jp_player_cards.updateUI();
            cardPoints(j, "player");

            if(i==1){
                ran = new Random();
                picLabel = new JLabel(new ImageIcon(card.cards.get(53)));
                Jp_dealer_cards.add(picLabel);
            }else {
                j=ran.nextInt(52);
                picLabel = new JLabel(new ImageIcon(card.cards.get(j)));
                Jp_dealer_cards.add(picLabel);
                Jp_dealer_cards.updateUI();
                cardPoints(j,"dealer");
            }
        }

        String[] account = Jl_dealer_account.getText().split(" ");
        double budget = Double.parseDouble(account[1]);
        double money = ran.nextInt((int)budget-(int)(budget*0.3));

        Jl_dealer_bet.setText("Bet: " + money + " $");
        Jl_dealer_account.setText("Account: "+(budget-money)+" $");

        Jl_dealer_bet.updateUI();
        Jl_dealer_account.updateUI();

        isWin();

    }

    public void cardPoints(int points, String who) {
        int output = points % 13;
        if (output == 1) {
            if (who.equals("player")) {
                player[0] = player[0] + 1;
                player[1] = player[1] + 11;
            } else {
                dealer[0] = dealer[0] + 1;
                dealer[1] = dealer[1] + 11;
            }
        } else if (output <= 10&&output>0) {
            if (who.equals("player")) {
                player[0] = player[0] + output;
                player[1] = player[1] + output;
            } else {
                dealer[0] = dealer[0] + output;
                dealer[1] = dealer[1] + output;
            }
        } else if (output > 10||output==0) {
            if (who.equals("player")) {
                player[0] = player[0] + 10;
                player[1] = player[1] + 10;
            } else {
                dealer[0] = dealer[0] + 10;
                dealer[1] = dealer[1] + 10;
            }
        }
    }

    public void isWin(){
        Jta_game_record.append("\r\nPlayer soft hand"+player[0]+"\r\nPlayer hard hand"+player[1]);
        Jta_game_record.append("\r\nDealer current known soft hand"+dealer[0]+"\r\nDealer current known hard hand"+dealer[1]);
        if(player[0]>21&&player[0]>21){
            Jta_game_record.append("\r\nPlayer bust!\r\nDealer Win!");
            cleanMoney("dealer");
            count=0;

        }else if(dealer[0]>21&&dealer[0]>21){
            Jta_game_record.append("\r\nDealer bust!\r\nPlayer Win!");
            cleanMoney("player");
            count=0;
        }
    }

    public void cleanMoney(String iswho){
        if(iswho.equals("player")){
            String[] dealer_bet = Jl_dealer_bet.getText().split(" ");
            String[] player_bet = Jl_player_bet.getText().split(" ");
            String[] account = Jl_player_account.getText().split(" ");
            double reward = Double.parseDouble(account[1])+ Double.parseDouble(player_bet[1]) +Double.parseDouble(dealer_bet[1]) ;
            Jl_player_bet.setText("Bet: " + 0 + " $");
            Jl_player_account.setText("Account: " + reward + " $");
            Jl_dealer_bet.setText("Bet: " + 0 + " $");
            Jl_player_bet.updateUI();
            Jl_player_account.updateUI();
            Jl_dealer_bet.updateUI();

        }else  if(iswho.equals("dealer")){
            String[] dealer_bet = Jl_dealer_bet.getText().split(" ");
            String[] player_bet = Jl_player_bet.getText().split(" ");
            String[] account = Jl_dealer_account.getText().split(" ");
            double reward = Double.parseDouble(account[1])+ Double.parseDouble(player_bet[1]) +Double.parseDouble(dealer_bet[1]) ;
            Jl_player_bet.setText("Bet: " + 0 + " $");
            Jl_dealer_account.setText("Account: " + reward + " $");
            Jl_dealer_bet.setText("Bet: " + 0 + " $");
            Jl_player_bet.updateUI();
            Jl_player_account.updateUI();
            Jl_dealer_bet.updateUI();
        }
        player[0]=0;
        player[1]=0;
        dealer[0]=0;
        dealer[1]=0;
        Jp_player_cards.removeAll();
        Jp_dealer_cards.removeAll();
    }
}