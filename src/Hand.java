import java.util.ArrayList;

public class Hand
{
    private ArrayList<String> hand;
    private int bet;

    public Hand(ArrayList<String> hand, int bet)
    {
        this.hand = hand;
        this.bet = bet;
    }

    public int getRawTotal()
    {
        int handTotal = 0;
        for (String card : hand)
        {
            if (!card.equals("A"))
            {
                handTotal += Integer.parseInt(card);
            }
            else
            {
                handTotal += 11;
            }
        }
        return handTotal;
    }

    public int getHandTotal()
    {
        int aceCount = 0;
        int trueTotal = getRawTotal();

        for (String card : hand)
        {
            if (card.equals("A"))
            {
                aceCount++;
            }
        }
        for (int i = 0; i < aceCount; i++)
        {
            if (trueTotal > 21)
            {
                trueTotal -= 10;
            }
        }
        return trueTotal;
    }

    public int getYIndex() //used for dealer's hand to get what column in strategy table corresponds to their upcard
    {
        if (hand.getFirst().equals("A"))
        {
            return 9;
        }
        else
        {
            return Integer.parseInt(hand.getFirst()) - 2;
        }
    }

    public boolean isSoft()
    {
        return (hand.contains("A") && getRawTotal() < 21);
    }

    public boolean isBlackjack()
    {
        return (hand.size() == 2 && getRawTotal() == 21);
    }

    public boolean canBeDoubled()
    {
        return hand.size() == 2;
    }

    public boolean isBust()
    {
        return (getHandTotal() > 21);
    }

    public boolean canBeSplit()
    {
        return canBeDoubled() && hand.getFirst().equals(hand.getLast());
    }

    public void drawToHand(String card)
    {
        hand.add(card);
    }

    public ArrayList<String> getHand()
    {
        return hand;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
