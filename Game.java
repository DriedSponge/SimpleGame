import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class controls the logic of the gamel
 */
public class Game {

  private int pot = 0;

  private ArrayList<Player> players = new ArrayList<Player>();
  private Deck deck;

  /**
   * This constructor will populate the deck.
   * @param deck The deck you want to use with the game
   */
  public Game(Deck deck) {
    this.deck = deck;
  }


  /**
   * This method starts the game and walks through the general game logic.
   */
  public void start() {
    int playerCount = 0;

    while (!(playerCount > 0 && playerCount <= 52)) {
      Scanner userIn = new Scanner(System.in);

      System.out.print("How many people are playing? ");

      // Make sure the character the user enters ins an int
      if (userIn.hasNextInt()) {
        playerCount = userIn.nextInt();
      } else {
        // Repeat the loop if there is no int
        System.out.println("Please input a valid integer!");
        continue;
      }

      // Check to make sure there are no more players than the amount of cards.
      if (playerCount == 0 || playerCount > 52) {
        System.out.println("There must be at least one player and no more than fifty-two players!");
      }
    }
    System.out.println();
    this.registerPlayers(playerCount);

  }

  /**
   * Finds the person with the highest card.
   * @return winner The player who won the game.
   */
  public Player findWinner() {
    // placeholder because player 0 might have folded
    Card high = new Card(0, Suite.values()[0]);
    Player winner = new Player("null");

    // Check for high cards (every player)
    for (int i = 0; i < this.players.size(); i++) {
      Player player = this.players.get(i);
      if (player.getCard().getValue() > high.getValue() && !player.hasFolded) {
        high = player.getCard();
        winner = player;
      }
    }

    System.out.println();
    System.out.println(winner.getName() + " won with a " + high.toString() + "! They won $"+this.pot);
    return winner;
  }

  /**
   * Creates each player and adds them to the games player list. Gives the player
   * their card.
   * 
   * @param playerCount The amount of players.
   */
  private void registerPlayers(int playerCount) {
    for (int i = 0; i < playerCount; i++) {
      System.out.print("Please enter a name for player " + (i + 1) + ": ");
      Scanner userIn = new Scanner(System.in);

      Player player = new Player(userIn.next());

      player.setCard(deck.getRandomCard());

      this.players.add(player);

    }
  }

  /**
   * This class controls the actually players actions
   */
  public void playPlayers() {
    this.players.forEach(player -> {
      Scanner userIn = new Scanner(System.in);
      System.out.println(player.getName() + ", it is your turn!");

      boolean isPlaying = true;

      while (isPlaying) {
        System.out.print("Your options are to (b)et, (v)iew card, (c)ontinue to the next player, or (f)old: ");

        char response = userIn.next().charAt(0);
        // Switch between various responses.
        switch (response) {
          case 'b':
            this.addBet(player);
            break;
          case 'v':
            player.viewCard();
            break;
          case 'c':
            isPlaying = false;
            break;
          case 'f':
            player.fold();
            isPlaying = false;
            break;
          default:
            System.out.println(
                "Please enter \"b\" for betting, \"v\" for viewing your card, \"c\" to continue to the next player, or \"f\" to fold: ");
            break;
        }
      }

      // Crappy way to prevent players from seeing other players cards.
      for (int i = 0; i < 50; i++) {
        System.out.println("-");
      }
    });
  }

  /**
  * Adds a bet to the pot.
  */
  private void addBet(Player player) {
    this.pot += player.getBet();
  }
}
