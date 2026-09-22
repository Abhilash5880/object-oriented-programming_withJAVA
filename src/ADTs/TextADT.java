package ADTs;

public class TextADT {

    private char[] chars;
    private int length;
    // Concrete invariant: 0 <= length <= chars.length
    // Abstraction function:
    //   AF(this) = the character sequence chars[0..length-1]

    public TextADT() {
        chars = new char[16];
        length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    // pre:  0 <= pos <= length()
    // post: c has been inserted at index pos; later chars shift right
    public void insert(int pos, char c) {
        if (pos < 0 || pos > length) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(length + 1);
        for (int i = length; i > pos; i--) {
            chars[i] = chars[i - 1];
        }
        chars[pos] = c;
        length++;
    }

    // pre:  0 <= pos < length()
    // post: character at pos removed; later chars shift left
    public void delete(int pos) {
        // Fixed the missing closing parenthesis and added exception handling
        if (pos < 0 || pos >= length) {
            throw new IndexOutOfBoundsException();
        }

        // Added the logic to shift characters to the left to overwrite the deleted character
        for (int i = pos; i < length - 1; i++) {
            chars[i] = chars[i + 1];
        }
        length--; // Reduce the total length since one character was removed
    }

    // Added this missing method required by insert() to expand the array when full
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > chars.length) {
            int newCapacity = chars.length * 2; // Double the capacity
            char[] newChars = new char[newCapacity];
            for (int i = 0; i < length; i++) {
                newChars[i] = chars[i]; // Copy old characters to the new array
            }
            chars = newChars;
        }
    }
    public void print() {
        for (int i = 0; i <= length; i++) {
            System.out.print(chars[i] + " ");
        }
    }
}