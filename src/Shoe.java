import java.util.ArrayList;

public class Shoe {

    private int rc;
    private double tc;
    private ArrayList<String> shoe = new ArrayList<String>();
    private int tableMin;
    private int tableMax;
    private int bankroll;
    private int maxSplits;
    private int decks;
    private double deckPen;
    private ArrayList<Integer> betSpread;
    private boolean surrender;
    private boolean shuffleFlag = false;
    final String[][] HARD_STRATEGY_TABLE = {
            /* Dealer Upcard:
              2,    3,    4,    5,    6,    7,    8,    9,    10,   A
             */
            //hard totals
            {"HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH"}, // 4
            {"HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH"}, // 5
            {"HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH"}, // 6
            {"HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH"}, // 7
            {"HH", "HH", "HH", "HH", "HH+2DH", "HH", "HH", "HH", "HH", "HH"}, // 8
            {"HH+1DH", "DH", "DH", "DH", "DH", "HH+3DH", "HH", "HH", "HH", "HH"}, // 9
            {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "HH+4DH", "HH+3DH"}, // 10
            {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH"}, // 11
            {"HH+3SS", "HH+2SS", "SS-0HH", "SS-2HH", "SS-1HH", "HH", "HH", "HH", "HH", "HH"}, // 12
            {"SS-1HH", "SS-2HH", "SS", "SS", "SS", "HH", "HH", "HH", "HH", "HH"}, // 13
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH", "HH", "HH", "HH"}, // 14
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH", "HH+2SH", "SH-0HH", "HH-1SH"}, // 15
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH+4SH", "SH-1HH", "SH", "SH"}, // 16
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 17
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 18
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 19
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 20
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 21
    };

    final String[][] SOFT_STRATEGY_TABLE =
            {
            {"HH", "HH", "HH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,2
            {"HH", "HH", "HH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,3
            {"HH", "HH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,4
            {"HH", "HH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,5
            {"HH+1DH", "DH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,6
            {"DS", "DS", "DS", "DS", "DS", "SS", "SS", "HH", "HH", "HH"}, // A,7
            {"SS", "SS", "SS+3DS", "SS+1DS", "DS-0SS", "SS", "SS", "SS", "SS", "SS"}, // A,8
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // A,9
    };

    final String[][] SPLITTABLE_STRATEGY_TABLE = {
            /* Dealer Upcard:
              2,    3,    4,    5,    6,    7,    8,    9,    10,   A
             */
            {"PP", "PP", "PP", "PP", "PP", "PP", "HH", "HH", "HH", "HH"}, // 2,2
            {"PP", "PP", "PP", "PP", "PP", "PP", "HH", "HH", "HH", "HH"}, // 3,3
            {"HH", "HH", "HH", "PP", "PP", "HH", "HH", "HH", "HH", "HH"}, // 4,4
            {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "HH", "HH"}, // 5,5
            {"PP", "PP", "PP", "PP", "PP", "HH", "HH", "HH", "HH", "HH"}, // 6,6
            {"PP", "PP", "PP", "PP", "PP", "PP", "HH", "HH", "HH", "HH"}, // 7,7
            {"PP", "PP", "PP", "PP", "PP", "PP", "PP", "PP", "HH", "HH"}, // 8,8
            {"PP", "PP", "PP", "PP", "PP", "SS", "PP", "PP", "SS", "SS"}, // 9,9
            {"SS", "SS", "SS+6PP", "SS+5PP", "SS+4PP", "SS", "SS", "SS", "SS", "SS"}, // 10,10
            {"PP", "PP", "PP", "PP", "PP", "PP", "PP", "PP", "PP", "HH"}  // A,A
    };

    public Shoe(int decksInShoe, int maxSplits, int tableMin, int tableMax, int bankroll, double deckPen, ArrayList<Integer> betSpread)
    {
        decks = decksInShoe;
        for (int i = 0; i < (4 * decks); i++) //populate shoe
        {
            shoe.add("A");
            for (int j = 0; j < 4; j++)
            {
                shoe.add("10");
            }
            shoe.add("9");
            shoe.add("8");
            shoe.add("7");
            shoe.add("6");
            shoe.add("5");
            shoe.add("4");
            shoe.add("3");
            shoe.add("2");
        }
        this.tableMin = tableMin;
        this.tableMax = tableMax;
        this.bankroll = bankroll;
        this.deckPen = deckPen;
        this.betSpread = betSpread;
        this.maxSplits = maxSplits;
    }

    public int runRound(int initialBet)
    {
        boolean endAction = false;
        int splits = 0;
        int bet = initialBet;
        int winnings = 0;

        ArrayList<String> handSplitQueue = new ArrayList<>();

        ArrayList<String> ph = new ArrayList<>();
        Hand playerHand = new Hand(ph);

        ArrayList<String> dh = new ArrayList<>();
        Hand dealerHand = new Hand(dh);

        for (int i = 0; i < 2; i++) //populate hands
        {
            playerHand.drawToHand(drawFromShoe());
            dealerHand.drawToHand(drawFromShoe());
        }

        while (!endAction)
        {
            switch (getNextAction(playerHand, dealerHand, splits))
            {
                case "HH":
                    playerHand.drawToHand(drawFromShoe());

                    if (playerHand.isBust())
                    {
                        winnings -= bet;

                        if (handSplitQueue.isEmpty())
                        {
                            endAction = true;
                        }
                        else
                        {
                            playerHand = (getNewHandFromSplitQueue(handSplitQueue, playerHand));
                        }
                    }
                    break;
                case "SS":

                    break;
                case "DH":

                    break;
                case "DS":

                    break;
                case "PP":
                    splits += 1;
                    handSplitQueue.add(playerHand.getHand().get(1)); //adds second card in hand to queue of hands to play

                    playerHand.drawToHand(drawFromShoe()); //adds new card to current hand in place of second card
                    break;
                case "SH":

                    break;
                }
            }

        return winnings;
    }

    public int getBet()
    {
        return 0;
    }

    public String drawFromShoe()
    {
        int drawIdx = (int) (Math.random() * (shoe.size() + 1));
        String card = shoe.get(drawIdx);
        shoe.remove(drawIdx);

        adjustTcRc(card);

        if(shoe.size() <= (1 - deckPen) * 52 * decks)
        {
            shuffleFlag = true;
        }

        return card;
    }

    public String getNextAction(Hand hand, Hand dealerHand, int numSplits)
    {
        String action;
        if (hand.isBlackjack())
        {
            return "BJ";
        }
        else if (hand.canBeSplit() && numSplits < maxSplits)
        {
            action = SPLITTABLE_STRATEGY_TABLE[(hand.getRawTotal() / 2) - 2][dealerHand.getYIndex()];

            return Shoe.handleDeviations(tc, rc, action);
        }
        else if(hand.isSoft())
        {
            action = SOFT_STRATEGY_TABLE[hand.getHandTotal() - 13][dealerHand.getYIndex()];

            return Shoe.handleDeviations(tc, rc, action);
        }
        else
        {
            action = HARD_STRATEGY_TABLE[hand.getHandTotal() - 4][dealerHand.getYIndex()];

            return Shoe.handleDeviations(tc, rc, action);
        }
    }

    public static String getDeviation(String actionWithDeviation)
    {
        return actionWithDeviation.substring(4);
    }

    public static boolean shouldDeviate(double tc, int rc, String actionWithDeviation)
    {
        String deviationCountString = actionWithDeviation.substring(2,4);

        if (deviationCountString.contains("+"))
        {
            return (tc >= Integer.parseInt(actionWithDeviation.substring(3,4)));
        }
        else if (deviationCountString.contains("0"))
        {
            return (rc < 0);
        }
        else
        {
            return tc <= Integer.parseInt(deviationCountString);
        }
    }

    public static String handleDeviations(double tc, int rc, String action)
    {
        if (action.length() > 2) //deviation
        {
            String countToDeviate = action.substring(2,4);

            if (Shoe.shouldDeviate(tc, rc, action))
            {
                return Shoe.getDeviation(action);
            }
            else
            {
                return action.substring(0,2);
            }
        }
        else
        {
            return action;
        }
    }

    public void adjustTcRc(String cardDrawn)
    {
        if (cardDrawn.equals("2") || cardDrawn.equals("3") || cardDrawn.equals("4") || cardDrawn.equals("5") || cardDrawn.equals("6")) // if low card
        {
            rc++;
        }
        else if (cardDrawn.equals("10") || cardDrawn.equals("A")) // if high card
        {
            rc--;
        }

        tc = rc / (getShoeRemaining() / 52.0);
    }

    public Hand getNewHandFromSplitQueue(ArrayList<String> handSplitQueue, Hand playerHand)
    {
        playerHand.getHand().set(0, handSplitQueue.getFirst());
        handSplitQueue.removeFirst();

        playerHand.drawToHand(drawFromShoe());

        return playerHand;
    }

    public boolean checkForSplitsInQueue(ArrayList<String> handSplitQueue, Hand playerHand)
    {
        //if nothing else to split
        return handSplitQueue.isEmpty();
    }

    public int getShoeRemaining()
    {
        return shoe.size();
    }

    public void setTc(double tc)
    { //test method
        this.tc = tc;
    }

    public double getTc() {
        return tc;
    }

    public String toString()
    {
        return shoe.toString();
    }
}
