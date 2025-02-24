import java.util.ArrayList;

public class Hand
{
    private ArrayList<String> hand;
    private boolean soft = false;

    public Hand(ArrayList<String> hand)
    {
        this.hand = hand;
    }

    public int getHandTotal()
    {
        int handTotal = 0;
        int aceCount = 0;
        for (String card : hand)
        {
            if (!card.equals("A"))
            {
                handTotal += Integer.parseInt(card);
            } else
            {
                handTotal += 11;
                aceCount++;
            }
        }

        for (int i = 0; i < aceCount; i++)
        {
            if (handTotal < 21)
            {
                break;
            }
            else
            {
                soft = true;
                handTotal -= 10;
            }
        }
        return handTotal;
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

    public boolean isSoft() {
        return soft;
    }

    public boolean canBeDoubled()
    {
        return hand.size() == 2;
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
}
