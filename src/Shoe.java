import java.util.ArrayList;

public class Shoe {

    private int rc;
    private double tc;
    private ArrayList<String> shoe = new ArrayList<String>();
    private int tableMin;
    private int tableMax;
    private int bankroll;

    public Shoe(int decksInShoe, int tableMin, int tableMax, int bankroll)
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
    }

    public String draw()
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

    public void round()
    {
        int bet;
        boolean win;

        if (tc >= 2)
        {
            bet = tableMax;
        }
        else
        {
            bet = tableMin;
        }

        if (win)
        {
            bankroll += bet;
        }
        else
        {
            bankroll -= bet;
        }
    }

    public int getShoeRemaining()
    {
        return shoe.size();
    }

    public double getTc() {
        return tc;
    }

    public String toString()
    {
        return shoe.toString();
    }
}
