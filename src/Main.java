import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print ("How many decks in the shoe: ");
        int decks = Integer.parseInt(s.nextLine());
        final int PLAYED_CARDS = (52 * decks) / 4;

        System.out.print("Max Splits: ");
        final int MAX_SPLITS = Integer.parseInt(s.nextLine());

        System.out.print("Table Min: ");
        final int TABLE_MIN = Integer.parseInt(s.nextLine());

        System.out.print("Table Max: ");
        final int TABLE_MAX = Integer.parseInt(s.nextLine());

        System.out.print("Bankroll: ");
        final int BANKROLL = Integer.parseInt(s.nextLine());

        System.out.print("Deck Penetration (must be a decimal that, when multiplied by the amount of cards in the shoe, results in an integer): ");
        final double DECK_PENETRATION = Double.parseDouble(s.nextLine());

        System.out.print("Rounds Per Hour: ");
        final int RPH = Integer.parseInt(s.nextLine());

        System.out.print("Avg Session Length (in hours): ");
        final double HOURS_PER_SESSION = Double.parseDouble(s.nextLine());

        System.out.print("Bet Spread (index 0 is bet at tc <= 1, each index following 0 corresponds to tc, at whichever count maxbet should be placed type \"maxbet\", if minbet should be placed, type \"minbet\", separate w commas): ");
        String[] betSpreadStr = s.nextLine().split(",");
        ArrayList<Integer> betSpread = new ArrayList<Integer>();
        for(String string : betSpreadStr)
        {
            if (string.equalsIgnoreCase("maxbet"))
            {
                betSpread.add(13124);
            }
            else if (string.equalsIgnoreCase("minbet"))
            {
                betSpread.add(13914);
            }
            else
            {
                betSpread.add(Integer.parseInt(string));
            }
        }

        Shoe shoe = new Shoe(decks, MAX_SPLITS, TABLE_MIN, TABLE_MAX, BANKROLL, DECK_PENETRATION, betSpread);
        //setting game conditions^^

        double totalWinnings = 0;
        int ruinedSessions = 0;

        for(int i = 0; i < 50000; i++)
        {
            double realBankroll = BANKROLL;
            for (int j = 0; j < RPH * HOURS_PER_SESSION; j++)
            {
                totalWinnings += shoe.getRoundResults();
                realBankroll += shoe.getRoundResults();
                if(realBankroll <= 0)
                {
                    ruinedSessions += 1;
                    break;
                }
            }
        }
        System.out.print("Expected value: $" );
        System.out.printf("%.2f/Hr %n", (totalWinnings / (HOURS_PER_SESSION * 50000)));
        System.out.println("Risk of Ruin for a Single Session: " + ruinedSessions / 500.0+ "%");
    }
}
