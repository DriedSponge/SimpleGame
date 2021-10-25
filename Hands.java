/**
 * Different kind of possible poker hands.
 */
public enum Hands {
    /**
     * Has a high Card
     */
    HIGH_CARD,
    /**
     * Pair of cards
     */
    PAIR,
    /**
     * Two pairs of cards
     */
    TWO_PAIR,
    /**
     * Three of a kind.
     */
    THREE_OF_A_KIND,
    /**
     * 5 cards in order
     */
    STRAIT,
    /**
     * Five cards, same suite
     */
    FLUSH,
    /**
     * Three of a kind & two pair
     */
    FULL_HOUSE,
    /**
     * Four of a kind
     */
    FOUR_OF_A_KIND,
    /**
     * All cards in order & same suite
     */
    STRAIT_FLUSH,
    /**
     * All cards in order, same suite, & and includes 10, J, Q, K, A
     */
    ROYAL_FLUSH
}
