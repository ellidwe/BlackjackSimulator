import java.util.ArrayList;

public class Hand
{
    private ArrayList<String> hand;

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
                handTotal -= 10;
            }
        }
        return handTotal;
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
