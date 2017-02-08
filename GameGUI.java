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
   private final String title = "DINOSAUR TOP TRUMPS!";
   private final  String textFile = "cardText.txt";
 //  private final  String textFile = "DrawDeck.txt";

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
   private JTextField userCardCount, cardCount1, cardCount2, cardCount3, cardCount4;
   //
   private final String cardInfo = "CARDS IN HAND: ";
   private final String deckInfo = "CARDS IN COMMUNAL PILE: ";
   private JTextField communalCardCount;
   //ASCII pattern to display when players' card details need to be hidden
   private final String dinoImage = "───────────████████\n──────────███▄███████\n──────────███████████\n──────────███████████\n──────────██████\n──────────█████████\n█───────███████\n██────████████████\n███──██████████──█\n███████████████\n███████████████\n─█████████████\n──███████████\n────████████\n─────███──██\n─────██────█\n─────█─────█\n─────██────██\n";

   //middle panel instance variables
   private JTextArea messageArea;
   private JTextField humanTurn;

   //bottom panel instance variables
   //text areas for the cards left over in deck, and the user's card details
   private JTextArea drawPile, humanCard;
   //labels to display number of cards in deck and number in user's hand
   private JLabel communalCount, humanCardCount;
   //the play button which the user will use to step through each round of the game
   private JButton play,nextRound;
   //combo box user will use to select chosen category when it is their turn
   private JComboBox trumpCategories;

   //number of players in a game
   private int numPlayers;

   private Game startGame;

   /**
   *Creates an instance of the Game GUI, based on how many opponents the user has selected
   *@param player the number of opponents the user has selected for their game in the StartGUI
   **/
   public GameGUI(int player){
      numPlayers = player +1;
      setLayout(new GridLayout(3,1));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle(title);
      setSize(750, 900);

      GUITop();
      GUIMiddle();
      GUIBottom();
      //int totalPlayers = player++;//adds user to number of opponents selected
      startGame = new Game(player, getCategories(), this.getDeck());

      //sets instance variables
      this.SetOpponentsView(player);
      //this.UpdatePlayer(startGame.getPlayerPointer());
      this.ResetGUI();
      this.UpdateCardCount();
      setVisible(true);
   }

   /**
   *creates the top third of the GAMEGUI
   **/
   public void GUITop(){

      final GridLayout topGrid = new GridLayout(1,4);

      top = new JPanel(topGrid);

      player1 = new JPanel();
      player2 = new JPanel();
      player3 = new JPanel();
      player4 = new JPanel();

      JLabel title1 = new JLabel("PLAYER ONE" );
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

      comp1Card.setEditable(false);
      comp2Card.setEditable(false);
      comp3Card.setEditable(false);
      comp4Card.setEditable(false);

      comp1CardCount = new JLabel(cardInfo);
      comp2CardCount = new JLabel(cardInfo);
      comp3CardCount = new JLabel(cardInfo);
      comp4CardCount = new JLabel(cardInfo);

      cardCount1 = new JTextField("  ");
      cardCount2 = new JTextField("  ");
      cardCount3 = new JTextField("  ");
      cardCount4 = new JTextField("  ");

      cardCount1.setEditable(false);
      cardCount2.setEditable(false);
      cardCount3.setEditable(false);
      cardCount4.setEditable(false);


      player1.add(title1);
      player1.add(comp1Card);
      player1.add(comp1CardCount);
      player1.add(cardCount1);
      player2.add(title2);
      player2.add(comp2Card);
      player2.add(comp2CardCount);
      player2.add(cardCount2);
      player3.add(title3);
      player3.add(comp3Card);
      player3.add(comp3CardCount);
      player3.add(cardCount3);
      player4.add(title4);
      player4.add(comp4Card);
      player4.add(comp4CardCount);
      player4.add(cardCount4);

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
      drawPile.setFont(theFont1);
      drawPile.setEditable(false);
      communalCount = new JLabel(deckInfo);
      communalCardCount = new JTextField(" ");
      communalCardCount.setEditable(false);
      left.add(drawPile);
      left.add(communalCount);
      left.add(communalCardCount);

      JPanel center = new JPanel();
      // humanCard = new JTextArea(dinoImage, 4, 12);
      humanCard = new JTextArea(4, 12);
      humanCard.setFont(theFont2);
      humanCard.setEditable(false);
      humanCardCount = new JLabel(cardInfo);
      userCardCount = new JTextField(" ");
      userCardCount.setEditable(false);
      center.add(humanCard);
      center.add(humanCardCount);
      center.add(userCardCount);

      JPanel right = new JPanel();
      trumpCategories = new JComboBox(getCategories());
      JPanel row1 = new JPanel();
      row1.add(trumpCategories);

      nextRound= new JButton ("Next Round!");
      nextRound.setEnabled(false);
      nextRound.addActionListener(this);
      play = new JButton("PLAY");
      play.setEnabled(true);
      play.addActionListener(this);
      JPanel row2 = new JPanel();
      row2.add(nextRound);
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

      /**Update Card count
      * Method that updates the current card count on the GUI for each player
      *
      */
      public void UpdateCardCount(){
         //makes initial array of 5(maximum possible players, initialized at 0
         int [] cardsInHand = new int []{0,0,0,0,0};
         //populates array according to numof players in a particular game
         for (int i=0; i<numPlayers; i++){
            Player player= startGame.getActivePlayer(i);
            player.setNumCards();
            cardsInHand[i]= player.getNumCards();
         }

         userCardCount.setText("" + cardsInHand[0]);
         cardCount1.setText("" + cardsInHand[1]);
         cardCount2.setText("" + cardsInHand[2]);
         cardCount3.setText("" + cardsInHand[3]);
         cardCount4.setText("" + cardsInHand[4]);

         communalCardCount.setText("" + startGame.getCommunalCount());
      }

      /**
      *Method to update gui display to reflect the current player
      *@param playerNumber the player whose turn it is
      **/
      public void UpdatePlayer(int playerNumber){

         Player user = startGame.getActivePlayer(0);
         //humanCard.setText(user.returnCurrentCardStr());

         // case it is the user's turn to select a cateory
         if (playerNumber == 0){
            //humanTurn.setText("IT'S YOUR TURN! Pick a category!");
            humanTurn.setText("YOU WON THAT ROUND!");
            humanTurn.setBackground(Color.GREEN);
            humanCard.setBackground(Color.GREEN);
            trumpCategories.setEnabled(false);
         }

         // shows all opponents cards at the end of a round
         Card [] lastRound=startGame.getRoundCards();

         // Player p1 = startGame.getActivePlayer(1);

         // Player p2 = startGame.getActivePlayer(2);

         //Player p3 = startGame.getActivePlayer(3);

         //Player p4 = startGame.getActivePlayer(4);

         if(numPlayers>1){
            comp1Card.setFont(theFont2);
            if (lastRound[1] != null){
               comp1Card.setText(lastRound[1].formatCardText());}
               else comp1Card.setText("I'm DEAD!");
            }
            if(numPlayers>2){
               comp2Card.setFont(theFont2);
               if (lastRound[2] != null){
                  comp2Card.setText(lastRound[2].formatCardText());}
                  else comp2Card.setText("I'm DEAD!");
               }
               if(numPlayers>3){
                  comp3Card.setFont(theFont2);
                  if (lastRound[3] != null){
                     comp3Card.setText(lastRound[3].formatCardText());}
                     else comp3Card.setText("I'm DEAD!");
                  }
                  if(numPlayers>4){
                     comp4Card.setFont(theFont2);
                     if (lastRound[4] != null){
                        comp4Card.setText(lastRound[4].formatCardText());}
                        else comp4Card.setText("I'm DEAD!");
                     }


                     if(playerNumber==1){
                        Player currentPlayer = startGame.getActivePlayer(1);
                        comp1Card.setBackground(Color.GREEN);
                        trumpCategories.setEnabled(false);
                     }
                     if(playerNumber==2){
                        Player currentPlayer = startGame.getActivePlayer(2);
                        comp2Card.setBackground(Color.GREEN);
                        trumpCategories.setEnabled(false);
                     }
                     if(playerNumber==3){
                        Player currentPlayer = startGame.getActivePlayer(3);
                        comp3Card.setBackground(Color.GREEN);
                        trumpCategories.setEnabled(false);
                     }
                     if(playerNumber==4){
                        Player currentPlayer = startGame.getActivePlayer(4);
                        comp4Card.setBackground(Color.GREEN);
                        trumpCategories.setEnabled(false);
                     }
                  }
                  /**
                  *Method to reset theGUI to default between player turns
                  *@author Lauren
                  **/
                  public void ResetGUI(){
                     Player user = startGame.getActivePlayer(0);
                     humanCard.setText(user.returnCurrentCardStr());
                     trumpCategories.setEnabled(false);

                     humanTurn.setText("it's not your turn!");
                     humanTurn.setBackground(Color.gray);
                     humanTurn.setBackground(Color.WHITE);
                     humanCard.setBackground(Color.WHITE);
                     comp1Card.setBackground(Color.WHITE);
                     comp2Card.setBackground(Color.WHITE);
                     comp3Card.setBackground(Color.WHITE);
                     comp4Card.setBackground(Color.WHITE);

                     // set current players card to green to denote whose turn it is
                     //Doesn't set drop down box to disabled if it is players turn when GUI is reset
                     if (startGame.getPlayerPointer()== 0){
                        humanTurn.setText("IT'S YOUR TURN! Pick a category!");
                        humanTurn.setBackground(Color.GREEN);
                        humanCard.setBackground(Color.GREEN);
                        trumpCategories.setEnabled(true);}
                        else if (startGame.getPlayerPointer()==1){
                        comp1Card.setBackground(Color.GREEN);}
                        else if(startGame.getPlayerPointer()==2){
                        comp2Card.setBackground(Color.GREEN);}
                        else if(startGame.getPlayerPointer()==3){
                        comp3Card.setBackground(Color.GREEN);}
                        else if(startGame.getPlayerPointer()==4){
                        comp4Card.setBackground(Color.GREEN);}

                    // set all computer cards to dinos between rounds
                    comp1Card.setFont(theFont1);
                    comp1Card.setText(dinoImage);
                    comp2Card.setFont(theFont1);
                    comp2Card.setText(dinoImage);
                    comp3Card.setFont(theFont1);
                    comp3Card.setText(dinoImage);
                    comp4Card.setFont(theFont1);
                    comp4Card.setText(dinoImage);

                    //if a player is dead display this between rounds
                    

                    }

                     

                     /**
                     *method to handle user actions on GUI, in this case just user pressing 'play'
                     *@author Lauren
                     **/
                     public void actionPerformed(ActionEvent e){

                        if (e.getSource()==nextRound){
                           // set buttons to correct states
                           nextRound.setEnabled(false);
                           play.setEnabled(true);

                           //checks if game is over when a player has 40 cards
                           if (startGame.getGameOver()){
                              new GameOverStats(startGame);
                           }
                           this.ResetGUI();

                        }
                        if (e.getSource()==play) {
                           //default unless changed by human player
                           int input =-1;
                           // set buttons to correct states
                           nextRound.setEnabled(true);
                           play.setEnabled(false);
                           trumpCategories.setEnabled(false);


                           if (startGame.getGameOver()){
                              new GameOverStats(startGame);
                           }
                           if(startGame.getPlayerPointer()==0){
                              input=trumpCategories.getSelectedIndex();

                           }
                           startGame.playRound(input);
                           this.UpdatePlayer(startGame.getPlayerPointer());
                           this.UpdateCardCount();
                           this.GUIMessage();



                        }

                        Player user = startGame.getActivePlayer(0);
                        user.setNumCards();
                        Player c1 = startGame.getActivePlayer(1);
                        c1.setNumCards();

                        this.UpdateCardCount();
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
                           for(int i=0; i<numCat;i++){
                              categories[i]=scan.next();
                           }
                        }
                        catch(FileNotFoundException e){System.err.println("file not found exception in GetCategories()");}
                        return categories;
                     }

                     /**
                     *method to get a Strings for each of the top trumps card
                     *@return an array of Strings of card details
                     **/
                     public String[] getDeck(){
                        try{
                           FileReader readDeck = new FileReader(textFile);
                           Scanner scan = new Scanner(readDeck);
                           // scan.useDelimiter("\n");
                           scan.nextLine(); //scan first line to skip over categories
                           deck = new String[deckSize];

                           for(int i =0; i<deckSize; i++) {
                              deck[i]=scan.nextLine();

                           }
                        }
                        catch(FileNotFoundException e){System.err.println("file not found exception in getDeck()");}
                        return deck;
                     }


                     public  void GUIMessage(){
                    	int winner = this.startGame.getPlayerPointer();
                    	Card winningCard =startGame.getRoundCards()[winner];
                    	String dinosaur = winningCard.getDescription();
                    	int choosenCharIndex = startGame.getCurrentChosenCharacteristic();
                    	int winValue =  winningCard.getCharacteristicValueAt(choosenCharIndex);
                    	String characteristic = categories[choosenCharIndex];
                    	
                    	String roundresult="Round was won by player " + winner  +" They played the " + dinosaur  +"'s \n"  +characteristic + " which had a value of "+winValue;
                        System.out.println(roundresult);  
                    	
                        messageArea.setText(roundresult);
                        
                        //  messageArea.setText("The Game is complete! The winner is player " +);
                        //
                        //  messageArea.setText("YOU won contgratulations!");




                     }




                  }
