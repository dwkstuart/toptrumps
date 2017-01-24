

import java.applet.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.io.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
public class GameGUI extends JFrame {



  //card text file name
  private final  String textFile = "cardText.txt";
  Font theFont = new Font("Andale", Font.BOLD, 6);


  //GUI sections
  JPanel top, middle, bottom;

  //top panel instance variables
  private JPanel player1, player2, player3, player4;
  private JTextArea comp1Card, comp2Card, comp3Card, comp4Card;
  private JLabel comp1CardCount, comp2CardCount, comp3CardCount, comp4CardCount;
  private final String cardInfo = "CARDS IN HAND: ";
  private final String deckInfo = "CARDS LEFT IN DECK";
  private final String dinoImage = "───────────████████\n──────────███▄███████\n──────────███████████\n──────────███████████\n──────────██████\n──────────█████████\n█───────███████\n██────████████████\n███──██████████──█\n███████████████\n███████████████\n─█████████████\n──███████████\n────████████\n─────███──██\n─────██────█\n─────█─────█\n─────██────██\n";

  //middle panel variables
  private JTextArea messageArea;
  private JTextField humanTurn;

  //bottom panel variables
  private JTextArea drawPile, humanCard;
  private JLabel drawCardCount, humanCardCount;
  private JButton chooseCategory, play, nextRound;
  private JComboBox trumpCategories;

  private final int numCat = 5;
  private final int deckSize = 40;
  private String[] categories;
  private String[] deck;


    public GameGUI(){
      setLayout(new GridLayout(3,1));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("DINOSAUR TOP TRUMPS!");
      setSize(800, 900);

      GUITop();
      GUIMiddle();
      GUIBottom();
      setVisible(true);
    }

    public void GUITop(){
      GridLayout topGrid = new GridLayout(1,4);


      top = new JPanel(topGrid);

      player1  = new JPanel();
      player2 = new JPanel();
      player3 = new JPanel();
      player4 = new JPanel();

      JLabel title1 = new JLabel("PLAYER ONE");
      JLabel title2 = new JLabel("PLAYER TWO");
      JLabel title3 = new JLabel("PLAYER THREE");
      JLabel title4 = new JLabel ("PLAYER FOUR");

      comp1Card = new JTextArea(dinoImage, 4,12);
      comp2Card = new JTextArea(dinoImage, 4,12);
      comp3Card = new JTextArea(dinoImage, 4,12);
      comp4Card = new JTextArea(dinoImage, 4,12);

      comp1CardCount = new JLabel(cardInfo);
      comp2CardCount = new JLabel(cardInfo);
      comp3CardCount = new JLabel(cardInfo);
      comp4CardCount = new JLabel(cardInfo);


      player1.add(title1);
      player1.add(comp1Card);
      player1.add(comp1CardCount);
      player2.add(title2);
      player2.add(comp2Card);
      player2.add(comp2CardCount);
      player3.add(title3);
      player3.add(comp3Card);
      player3.add(comp3CardCount);
      player4.add(title4);
      player4.add(comp4Card);
      player4.add(comp4CardCount);

      comp1Card.setFont(theFont);
      comp2Card.setFont(theFont);
      comp3Card.setFont(theFont);
      comp4Card.setFont(theFont);
      comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

      top.add(player1);
      top.add(player2);
      top.add(player3);
      top.add(player4);

      add(top);
    }

    public void GUIMiddle(){

      middle = new JPanel();
      GridLayout middleGrid = new GridLayout(2,1);

      middle.setLayout(middleGrid);

      JPanel row1 = new JPanel();
      JPanel row2 = new JPanel();

      messageArea = new JTextArea(12,50);
      messageArea.setFont(new Font("Andale", Font.PLAIN, 14));
      messageArea.setEditable(false);
      row1.add(messageArea);


      humanTurn = new JTextField("it's not your turn!");
      humanTurn.setBackground(Color.GRAY);
      humanTurn.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.red));
      humanTurn.setEditable(false);


      row2.add(humanTurn);

      middle.add(row1);
      middle.add(row2);
      add(middle);

    }

    public void GUIBottom(){
      bottom = new JPanel();
      GridLayout bottomGrid = new GridLayout(1,3);
      bottom.setLayout(bottomGrid);


      //left hand side of bottom, containing draw pile
      JPanel left = new JPanel();
      drawPile = new JTextArea(dinoImage, 4, 12);
      drawPile.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));

      drawPile.setFont(theFont);
      drawCardCount = new JLabel(deckInfo);
      left.add(drawPile);
      left.add(drawCardCount);

      JPanel center = new JPanel();
      humanCard = new JTextArea(dinoImage, 4, 12);
      humanCard.setFont(theFont);
      humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      humanCardCount = new JLabel(cardInfo);
      center.add(humanCard);
      center.add(humanCardCount);

      JPanel right = new JPanel();
      JComboBox trumpCategories = new JComboBox(getCategories());
      JPanel row1 = new JPanel();
      row1.add(trumpCategories);

      JButton chooseCategories = new JButton("SELECT");
      JPanel row2 = new JPanel();
      row2.add(chooseCategories);

      JButton play = new JButton("PLAY");
      JPanel row3 = new JPanel();
      row3.add(play);


      JButton nextRound = new JButton("NEXT ROUND");
      JPanel row4 = new JPanel();
      row4.add(nextRound);

      right.add(row1);
      right.add(row2);
      right.add(row3);
      right.add(row4);


      bottom.add(left);
      bottom.add(center);
      bottom.add(right);

      add(bottom);
    }

    public void SetOpponentsView(int opponents){
      if(opponents == 4){}
      if(opponents <= 3){ player4.setVisible(false); }
      if(opponents <= 2){player3.setVisible(false);}
      if(opponents<=1){player2.setVisible(false);}
    }

    public void UpdatePlayer(int playerNumber){
      if (playerNumber == 0){
        humanTurn.setText("IT'S YOUR TURN!");
        humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
        humanTurn.setBackground(Color.GREEN);
      }
      if(playerNumber==1){
        comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
      }
      if(playerNumber==2){
        comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
      }
      if(playerNumber==3){
        comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
      }
      if(playerNumber==4){
        comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
      }





      }

      public void ResetGUI(){
        humanTurn.setText("it's not your turn!");
        humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        humanTurn.setBackground(Color.gray);
        comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      }

      public String[] getCategories(){
        try{
         FileReader readCategories = new FileReader(textFile);
         Scanner scan = new Scanner(readCategories);
         scan.next();
         categories = new String[numCat];
         for(int i=0; i<numCat;i++){
         categories[i]=scan.next();
         System.err.println(i + " " + categories[i]);

       }
       }
       catch(FileNotFoundException e){System.err.println("file not found exception in GetCategories()");}
      // catch(IOException e){System.err.println("i/o exception in GetCategories()");}
       return categories;
    }

    public String[] getDeck(){
      try{
      FileReader readDeck = new FileReader(textFile);
      Scanner scan = new Scanner(readDeck);
      scan.useDelimiter("\n");
      scan.next();
      deck = new String[deckSize];
      for(int i =0; i<deckSize; i++){
        deck[i]=scan.next();
        System.err.println(i + " " + deck[i]);
      }
    }
      catch(FileNotFoundException e){System.err.println("file not found exception in getDeck()");}

      return deck;
    }



}
