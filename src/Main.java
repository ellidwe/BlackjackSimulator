import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print ("How many decks in the shoe: ");
        int decks = Integer.parseInt(s.nextLine());
        final int PLAYED_CARDS = (52 * decks) / 4;

        System.out.print("Table Min: ");
        final int TABLE_MIN = Integer.parseInt(s.nextLine());

        System.out.print("Table Max: ");
        final int TABLE_MAX = Integer.parseInt(s.nextLine());

        System.out.print("Bankroll: ");
        final int BANKROLL = Integer.parseInt(s.nextLine());

        Shoe shoe = new Shoe(decks, TABLE_MIN, TABLE_MAX, BANKROLL);

        System.out.println(shoe.getTc());
        for (int i = 0; i < 10; i++)
        {
            System.out.println(shoe.draw());
            System.out.println(shoe.getTc());
        }
        System.out.println(shoe.toString());
    }
}
