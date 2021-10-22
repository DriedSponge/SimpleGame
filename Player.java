import java.util.Scanner;

public class Player {

  private String name;
  private int balance = 1000;
  private Card card;
  public boolean hasFolded = false;

  public Player(String name){
      this.name = name;
  }

  /**
  * Get the players name.
  * @return The players name
  */
  public String getName(){
      return this.name;
  }

  /**
    * Set the players card
    * @param card The card you want to give them
    */
  public void setCard(Card card) {
      this.card = card;
  }

  /**
  * Outputs the players card to the console.
  */
  public void viewCard(){
      System.out.println("You currently have a "+this.card.toString());
  }

  /**
  * Gets the players card
  * @return The players card
  */
  public Card getCard() {
      return card;
  }

  public void fold() {
    System.out.print("Folding");
    this.hasFolded = true;
  }

  /**
  * Ask the player for the amount of money they would like to bet.
  * @return The amount they want to bet.
  */
  public int getBet() {
    if (this.hasFolded == false) {
      Scanner userIn = new Scanner(System.in);
      while(true){
        System.out.print("Your balance is $"+this.balance+". How much would you like to bet? $");
        if(userIn.hasNextInt()){
          int bet = userIn.nextInt();
          if(this.balance >= bet){
            this.balance -= bet;
            return bet;
          }else{
            System.out.println("Sorry, you don't have enough money for that bet.");
            return 0;
          }

        }else{
          System.out.println("Please input an integer.");
        }
      }
    } else {
      //folded
      return 0;
    }
  }
}

