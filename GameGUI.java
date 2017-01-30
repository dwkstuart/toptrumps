import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;


public class GameGUI extends JFrame implements ActionListener{

   //card text file name
   private final String title = "DINSAUR TOP TRUMPS!";
   private final  String textFile = "cardText.txt";

   //integers representing the number of categories and the total number of cards in the deck
   private final int numCat = 5;
   private final int deckSize = 40;

   //String arrays to store categories and card details read in form the .txt file
   private String[] categories;
   private String[] deck;


   //monospaced font in two sizes for use in all card display and message fields
   private final Font theFont1 = new Font("Consolas", Font.BOLD, 6);
   private final Font theFont2 = new Font("Consolas", Font.BOLD, 12);


   //main GUI sections
   private JPanel top, middle, bottom;

   //top panel instance variables
   //panels for computer players
   private  JPanel player1, player2, player3, player4;
   //text areas for players' card details
   private JTextArea comp1Card, comp2Card, comp3Card, comp4Card;
   //labels to display each player's number of cards in hand
   private JLabel comp1CardCount, comp2CardCount, comp3CardCount, comp4CardCount;
   //
   private final String cardInfo = "CARDS IN HAND: ";
   private final String deckInfo = "CARDS LEFT IN DECK";
   //ASCII pattern to display when players' card details need to be hidden
   private final String dinoImage = "───────────████████\n──────────███▄███████\n──────────███████████\n──────────███████████\n──────────██████\n──────────█████████\n█───────███████\n██────████████████\n███──██████████──█\n███████████████\n███████████████\n─█████████████\n──███████████\n────████████\n─────███──██\n─────██────█\n─────█─────█\n─────██────██\n";

   //middle panel instance variables
   private JTextArea messageArea;
   private JTextField humanTurn;

   //bottom panel instance variables
   //text areas for the cards left over in deck, and the user's card details
   private JTextArea drawPile, humanCard;
   //labels to display number of cards in deck and number in user's hand
   private JLabel drawCardCount, humanCardCount;
   //the play button which the user will use to step through each round of the game
   private JButton play;
   //combo box user will use to select chosen category when it is their turn
   private JComboBox trumpCategories;

   //borders deliniating to user what player is in play at any one time
   // Border currentPlayer = BorderFactory.createMatteBorder(5,5,5,5, Color.green);
   // private Border notCurrentPlayer =  BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
   // private Border nonPlayer = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray);

   private Game startGame;

   /**
   *Creates an instance of the Game GUI, based on how many opponents the user has selected
   *@param player the number of opponents the user has selected for their game in the StartGUI
   **/
   public GameGUI(int player){

      setLayout(new GridLayout(3,1));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle(title);
      setSize(750, 900);

      GUITop();
      GUIMiddle();
      GUIBottom();

      startGame = new Game(player, getCategories(), this.getDeck());

      //sets instance variables
      this.SetOpponentsView(player);
      this.UpdatePlayer(0);

      setVisible(true);
   }

   /**
   *creates the top third of the GAMEGUI
   *@author Lauren
   **/
   public void GUITop(){

      final GridLayout topGrid = new GridLayout(1,4);

      top = new JPanel(topGrid);

      player1 = new JPanel();
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

      comp1Card.setFont(theFont1);
      comp2Card.setFont(theFont1);
      comp3Card.setFont(theFont1);
      comp4Card.setFont(theFont1);

      comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));


      comp1Card.setEditable(false);
      comp2Card.setEditable(false);
      comp3Card.setEditable(false);
      comp4Card.setEditable(false);

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

      top.add(player1);
      top.add(player2);
      top.add(player3);
      top.add(player4);

      add(top);
   }

   /**
   *creates the middle third of the GAMEGUI
   *@author Lauren
   **/
   public void GUIMiddle(){

      middle = new JPanel();
      final GridLayout middleGrid = new GridLayout(2,1);

      middle.setLayout(middleGrid);

      JPanel row1 = new JPanel();
      JPanel row2 = new JPanel();

      messageArea = new JTextArea(12,50);
      messageArea.setFont(theFont2);
      messageArea.setEditable(false);

      humanTurn = new JTextField("it's not your turn!");
      humanTurn.setBackground(Color.GRAY);
      humanTurn.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
      humanTurn.setEditable(false);

      row1.add(messageArea);
      row2.add(humanTurn);

      middle.add(row1);
      middle.add(row2);

      add(middle);
   }

   /**
   *creates the bottom third of the GAMEGUI
   *@author Lauren
   **/
   public void GUIBottom(){
      bottom = new JPanel();
      GridLayout bottomGrid = new GridLayout(1,3);
      bottom.setLayout(bottomGrid);


      //left hand side of bottom, containing draw pile
      JPanel left = new JPanel();

      drawPile = new JTextArea(dinoImage, 4, 12);
      drawPile.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
      drawPile.setFont(theFont1);
      drawCardCount = new JLabel(deckInfo);
      left.add(drawPile);
      left.add(drawCardCount);

      JPanel center = new JPanel();
      // humanCard = new JTextArea(dinoImage, 4, 12);
      humanCard = new JTextArea(4, 12);
      humanCard.setFont(theFont2);
      humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
      humanCardCount = new JLabel(cardInfo);
      center.add(humanCard);
      center.add(humanCardCount);

      JPanel right = new JPanel();
      trumpCategories = new JComboBox(getCategories());
      JPanel row1 = new JPanel();
      row1.add(trumpCategories);

      play = new JButton("PLAY");
      play.addActionListener(this);
      JPanel row2 = new JPanel();
      row2.add(play);

      right.add(row1);
      right.add(row2);

      bottom.add(left);
      bottom.add(center);
      bottom.add(right);

      add(bottom);
   }

   /**
   *Method sets the game gui to show the correct number of opponents at the begining of gameplay
   *@author Lauren
   **/
   public void SetOpponentsView(int opponents){
      if(opponents == 4){}
         if(opponents <= 3){ player4.setVisible(false); }
         if(opponents <= 2){player3.setVisible(false);}
         if(opponents<=1){player2.setVisible(false);}
      }

      /**
      *Method to update gui display to reflect the current player
      *@param playerNumber the player whose turn it is
      *@author Lauren
      **/
      public void UpdatePlayer(int playerNumber){
         // set all borders to default (black)
         humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
         comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
         comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
         comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
         comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));

         // set all computer cards to dinos at the start of a round
         comp1Card.setFont(theFont1);
         comp1Card.setText(dinoImage);
         comp2Card.setFont(theFont1);
         comp2Card.setText(dinoImage);
         comp3Card.setFont(theFont1);
         comp3Card.setText(dinoImage);
         comp4Card.setFont(theFont1);
         comp4Card.setText(dinoImage);

         if (playerNumber == 0){
            Player user = startGame.getActivePlayer(0);
            humanTurn.setText("IT'S YOUR TURN! Pick a category!");
            humanTurn.setBackground(Color.GREEN);
            humanTurn.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            humanCard.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            humanCard.setText(user.returnCurrentCardStr());
            trumpCategories.setEnabled(true);

         }
         if(playerNumber==1){
            // RISKY: THIS CODE NEEDS REVIEWING
            Player currentPlayer = startGame.getActivePlayer(1);
            comp1Card.setText(currentPlayer.returnCurrentCardStr());
            comp1Card.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            trumpCategories.setEnabled(false);
         }
         if(playerNumber==2){
            comp2Card.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            trumpCategories.setEnabled(false);
         }
         if(playerNumber==3){
            comp3Card.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            trumpCategories.setEnabled(false);
         }
         if(playerNumber==4){
            comp4Card.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.green));
            trumpCategories.setEnabled(false);
         }
      }
      /**
      *Method to reset theGUI to default between player turns
      *@author Lauren
      **/
      public void ResetGUI(){
         trumpCategories.setEnabled(false);
         humanTurn.setText("it's not your turn!");
         humanTurn.setBackground(Color.gray);
         humanTurn.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
         humanCard.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
         comp1Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
         comp2Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
         comp3Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
         comp4Card.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));


      }

      /**
      *method to handle user actions on GUI, in this case just user pressing 'play'
      *@author Lauren
      **/
      public void actionPerformed(ActionEvent e){
         int input =-1;
         if (e.getSource()==play) {
            if(startGame.getPlayerPointer()==0){
               input=trumpCategories.getSelectedIndex();
            }
            startGame.playRound(input);

         }

      }

      /**
      *method to get the top trumps categories from the .txt file
      *@author Lauren
      @return an array of Strings detailing the top trumps categories
      **/
      public String[] getCategories(){
         try{
            FileReader readCategories = new FileReader(textFile);
            Scanner scan = new Scanner(readCategories);
            scan.next();
            categories = new String[numCat];
            System.err.println("TOP TRUMPS CATEGORIES \n-------------------------");
            for(int i=0; i<numCat;i++){
               categories[i]=scan.next();
               System.err.println(i + " " + categories[i]);
            }
         }
         catch(FileNotFoundException e){System.err.println("file not found exception in GetCategories()");}
         return categories;
      }

      /**
      *method to get a Strings for each of the top trumps card
      *@author Lauren
      *@return an array of Strings of card details
      **/
      public String[] getDeck(){
         try{
            FileReader readDeck = new FileReader(textFile);
            Scanner scan = new Scanner(readDeck);
            scan.useDelimiter("\n");
            scan.next(); //scan first line to skip over categories
            deck = new String[deckSize];
            System.err.println("TOP TRUMPS CARD DETAILS \n-------------------------");
            for(int i =0; i<deckSize; i++){
               deck[i]=scan.next();
               System.err.println(i + " " + deck[i]);
            }
         }
         catch(FileNotFoundException e){System.err.println("file not found exception in getDeck()");}
         return deck;
      }



   }
