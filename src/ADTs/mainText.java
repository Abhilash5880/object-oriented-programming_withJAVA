package ADTs;

public class mainText {
    public static void main(String[] args) {
        TextADT text = new TextADT();

        System.out.println("Initial state:");
        System.out.println("Is empty? " + text.isEmpty());
        System.out.println("Length: " + text.length());
        System.out.println("Text: [" + text.toString() + "]\n");

        // Test inserting characters
        System.out.println("Inserting 'H', 'e', 'l', 'l', 'o'...");
        text.insert(0, 'H');
        text.insert(1, 'e');
        text.insert(2, 'l');
        text.insert(3, 'l');
        text.insert(4, 'o');

        System.out.println("Current Text: " + text.toString());
        System.out.println("Length: " + text.length() + "\n");

        // Test inserting in the middle (shifting right)
        System.out.println("Inserting 'y' at index 1...");
        text.insert(1, 'y');
        System.out.println("Current Text: " + text.toString() + "\n");

        // Test deleting a character (shifting left)
        System.out.println("Deleting character at index 0 ('H')...");
        text.delete(0);
        System.out.println("Current Text: " + text.toString());
        System.out.println("Length: " + text.length() + "\n");

        // Test ensuring capacity (forcing it to expand past the initial 16 characters)
        System.out.println("Inserting 15 more characters to test capacity expansion...");
        for (int i = 0; i < 15; i++) {
            text.insert(text.length(), '!');
        }
        System.out.println("Current Text: " + text.toString());
        System.out.println("Final Length: " + text.length());
        text.print();
    }
}