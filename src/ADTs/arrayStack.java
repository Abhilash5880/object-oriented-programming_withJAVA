package ADTs;

public class arrayStack <T> {

//    ADT Text
//        abstract state: a finite sequence of characters

//        operation insert(pos, c)
//            pre:  0 <= pos <= length(text)
//            post: a character c has been inserted into the
//                sequence at index pos; all characters at or
//                after pos are shifted one position to the right

//        operation delete(pos)
//            pre:  0 <= pos < length(text)
//            post: the character at index pos has been removed;
//                    all later characters shift one position left

//        operation length()
//            post: returns the number of characters currently
//            in the sequence
//        -----------------------
        private Object[] data;   // concrete representation
        private int top;         // -1 means empty stack
        // Concrete invariant: -1 <= top < data.length
        // Abstraction function:
        //   AF(this) = sequence [data[top], data[top-1], ..., data[0]]
        public arrayStack(int capacity) {  //constructor
                data = new Object[capacity];
                top = -1;
        }
        public boolean isEmpty() {
                return top == -1;
        }
        public boolean isFull() {
                return top == data.length - 1;
        }
        public void push(T value) {
                if (isFull()) throw new RuntimeException("Stack overflow");
                data[++top] = value;          // invariant preserved
        }
        @SuppressWarnings("unchecked")
        public T pop() {
                if (isEmpty()) throw new RuntimeException("Stack underflow");
                return (T) data[top--];       // invariant preserved
        }
        @SuppressWarnings("unchecked")
        public T peek() {
                if (isEmpty()) throw new RuntimeException("Stack is empty");
                return (T) data[top];
        }
        public int size() {
                return top + 1;
        }

        public void print() {
                for (int i = 0; i <= top; i++) {
                        System.out.print(data[i] + " ");
                }
        }

}

