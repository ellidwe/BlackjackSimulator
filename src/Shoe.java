import java.util.ArrayList;

public class Shoe {

    private int rc;
    private double tc;
    private ArrayList<String> shoe = new ArrayList<String>();
    private int tableMin;
    private int tableMax;
    private int bankroll;
    private final ArrayList<Integer> betSpread;
    private boolean surrender;
    final String[][] STRATEGY_TABLE = {
            /* Dealer Upcard:
              2,    3,    4,    5,    6,    7,    8,    9,    10,   A
             */
            //hard totals
            {"HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH", "HH"}, // 7-
            {"HH", "HH", "HH", "HH", "HH+2DH", "HH", "HH", "HH", "HH", "HH"}, // 8
            {"HH+1DH", "DH", "DH", "DH", "DH", "HH+3DH", "HH", "HH", "HH", "HH"}, // 9
            {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "HH+4DH", "HH+3DH"}, // 10
            {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH"}, // 11
            {"HH+3SS", "HH+2SS", "SS-0HH", "SS-2HH", "SS-1HH", "HH", "HH", "HH", "HH", "HH"}, // 12
            {"SS-1HH", "SS-2HH", "SS", "SS", "SS", "HH", "HH", "HH", "HH", "HH"}, // 13
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH", "HH", "HH", "HH"}, // 14
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH", "HH+2SH", "SH-0HH", "HH-1SH"}, // 15
            {"SS", "SS", "SS", "SS", "SS", "HH", "HH+4SH", "SH-1HH", "SH", "SH"}, // 16
            {"SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS", "SS"}, // 17+
            //soft totals
            {"HH", "HH", "HH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,2
            {"HH", "HH", "HH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,3
            {"HH", "HH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,4
            {"HH", "HH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,5
            {"HH+1DH", "DH", "DH", "DH", "DH", "HH", "HH", "HH", "HH", "HH"}, // A,6
            {"DS", "DS", "DS", "DS", "DS", "SS", "SS", "HH", "HH", "HH"}, // A,7
            {"SS", "SS", "SS+3DS", "SS+1DS", "DS-0SS", "SS", "SS", "SS", "SS", "SS"}, // A,8+
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

    public Shoe(int decksInShoe, int tableMin, int tableMax, int bankroll, ArrayList<Integer> betSpread)
    {
        for (int i = 0; i < (4 * decksInShoe); i++) //populate shoe
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
        this.betSpread = betSpread;
    }

    public String drawFromShoe()
    {
        int drawIdx = (int) (Math.random() * (shoe.size() + 1));
        String card = shoe.get(drawIdx);
        shoe.remove(drawIdx);

        if (card.equals("2") || card.equals("3") || card.equals("4") || card.equals("5") || card.equals("6"))
        {
            rc++;
        }
        else if (card.equals("10") || card.equals("A"))
        {
            rc--;
        }

        tc = rc / (getShoeRemaining() / 52.0);

        return card;
    }

    public int getBet()
    {
        int bet;

        if (Math.round(tc) < 1) //if bad count
        {
            if (betSpread.getFirst() == 13914)
            {
                bet = tableMin;
            }
            else
            {
                bet = betSpread.getFirst();
            }
        }
        else if(Math.round(tc) > betSpread.size()) //if count is above where table max would be put out
        {
            if (betSpread.getLast() == 13124)
            {
                bet = tableMax;
            }
            else
            {
                bet = betSpread.getLast();
            }
        }
        else
        {
            if (betSpread.get((int) Math.round(tc)) == 13124)
            {
                bet = tableMax;
            }
            else if (betSpread.get((int) Math.round(tc)) == 13914)
            {
                bet = tableMin;
            }
            else
            {
                bet = betSpread.get((int) Math.round(tc));
            }
        }
        return bet;
    }

    public String getHandOutcome(Hand hand, String dealerUpcard)
    {
        if (hand.getHand().size() == 1)
        {
            hand.drawToHand(drawFromShoe());
        }
        boolean splittable = (hand.getHand().getFirst().equals(hand.getHand().getLast()) && hand.getHand().size() == 2);

        switch (dealerUpcard)
        {
            case "A":
                break;
            case "10":
                break;
            case "9":
                break;
            case "8":
                break;
        }
        return "0";
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
