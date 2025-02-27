import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print ("How many decks in the shoe: ");
        int decks = Integer.parseInt(s.nextLine());
        final int PLAYED_CARDS = (52 * decks) / 4;

        System.out.print ("Late surrender? ");
        final boolean SURRENDER = s.nextLine().equalsIgnoreCase("y");

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

        System.out.print("Bet Spread (index 0 is bet at tc <= 0, each index following 0 corresponds to tc, at whichever count maxbet should be placed type \"maxbet\", if minbet should be placed, type \"minbet\", separate w commas): ");
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

        ArrayList<String> h = new ArrayList<>();
        h.add("A");
        h.add("5");
        Hand testHand = new Hand(h, 1);

        ArrayList<String> dh = new ArrayList<>();
        dh.add("7"); //upcard1
        dh.add("A");
        Hand dealerHand = new Hand(dh, 1);

        System.out.println(shoe.getRoundResults());


//        System.out.println(hand.isBlackjack());



//        System.out.println(Shoe.shouldDeviate(4, 0, "HH+4DH"));
//        System.out.println(Shoe.getDeviation("HH+4DH"));



//        ArrayList<String> h = new ArrayList<>();
//        h.add("2");
//        h.add("2");
//        Hand hand = new Hand(h);
//        System.out.println(hand.getHandTotal());
//        System.out.println(hand.canBeSplit());
//

//
//        System.out.println(shoe.getNextAction(hand, dealerHand, 0));


//        for (int i = 0; i < 23; i++)
//        {
//            hand.add("A");
//        }
//        hand.add("10");
//        hand.add("10");
//        hand.add("10");
//        hand.add("5");
//        System.out.println(shoe.getHandTotal(hand));

//        shoe.setTc(2);
//
//        System.out.println(shoe.getBet());

//        System.out.println(shoe.getTc());
//        for (int i = 0; i < 10; i++)
//        {
//            System.out.println(shoe.drawFromShoe());
//            System.out.println(shoe.getTc());
//        }
//        System.out.println(shoe.toString());

//
//        //Test code to make sure strategy arrays are identical

//        for(int i=0; i<splittableStrategyTable.length; i++) {
//            for(int j=0; j<splittableStrategyTable[i].length; j++) {
//                switch (splittableStrategyTable[i][j])
//                {
//                    case "H":
//                        if (!splittableStrategyTable2[i][j].equals("HH"))
//                        {
//                            System.out.println("i: " + i + " j: " + j);
//                        }
//                        break;
//                    case "S":
//                        if (!splittableStrategyTable2[i][j].equals("SS"))
//                        {
//                            System.out.println("i: " + i + " j: " + j);
//                        }
//                        break;
//                    case "P":
//                        if (!splittableStrategyTable2[i][j].equals("PP"))
//                        {
//                            System.out.println("i: " + i + " j: " + j);
//                        }
//                        break;
//                    case "DH":
//                        if (!splittableStrategyTable2[i][j].equals("DH"))
//                        {
//                            System.out.println("i: " + i + " j: " + j);
//                        }
//                        break;
//                    case "DS":
//                        if (!splittableStrategyTable2[i][j].equals("DS"))
//                        {
//                            System.out.println("i: " + i + " j: " + j);
//                        }
//                        break;
//                }
//            }
//        }
    }
}
