import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create two GenericStack objects of type Integer
        GenericStack<Integer> numStack1 = new GenericStack<>();
        GenericStack<Integer> numStack2 = new GenericStack<>();

        // fill the two stacks with values read from the files
        fillStack(numStack1, "numbers1.txt");
        fillStack(numStack2, "numbers2.txt");

        // print the two stacks
        System.out.println("Numbers Stack 1 filled with values from numbers1.txt");
        printStack(numStack1);
        System.out.println("Numbers Stack 2 filled with values from numbers2.txt");
        printStack(numStack2);

        // merge the two stacks
        GenericStack<Integer> mergedStack = mergeStacks(numStack1, numStack2);

        // reverse the merged stack
        GenericStack<Integer> finalMergedStack = reverseStack(mergedStack);

        // print the final merged stack
        System.out.println("Merged Stack - lowest value on top");
        printStack(finalMergedStack);

        // repeat the same steps for the two String files
        GenericStack<String> strStack1 = new GenericStack<>();
        GenericStack<String> strStack2 = new GenericStack<>();

        fillStack(strStack1, "mountains1.txt");
        fillStack(strStack2, "mountains2.txt");

        System.out.println("String Stack 1 filled with values from mountains1.txt");
        printStack(strStack1);
        System.out.println("String Stack 2-filled with values from mountains2.txt");
        printStack(strStack2);

        GenericStack<String> mergedStack2 = mergeStacks(strStack1, strStack2);
        GenericStack<String> finalMergedStack2 = reverseStack(mergedStack2);

        System.out.println("Merged Stack-lowest value on top");
        printStack(finalMergedStack2);

        // print the two merged stacks side by side
       
        printTwoStacks(finalMergedStack, finalMergedStack2);
    }

    // method to fill a stack with values read from a file
    public static <E extends Comparable<E>> void fillStack(GenericStack<E> stack, String fileName) {
        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNext()) {
                // read a value and push it to the stack
                stack.push((E) input.next());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }
    

    // method to print a stack
    public static <E extends Comparable<E>> void printStack(GenericStack<E> stack) {
        // create a temporary stack to store the elements
        GenericStack<E> temp = new GenericStack<>();
        while (!stack.isEmpty()) {
            // pop an element from the stack and print it
            E element = stack.pop();
            System.out.println(element);
            // push the element to the temporary stack
            temp.push(element);
        }
        // restore the original stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // method to merge two stacks
    public static <E extends Comparable<E>> GenericStack<E> mergeStacks(GenericStack<E> stack1, GenericStack<E> stack2) {
        // create a new stack to store the merged elements
        GenericStack<E> mergedStack = new GenericStack<>();
        // loop until both stacks are empty
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            // compare the top elements of the two stacks
            if (stack1.peek().compareTo(stack2.peek()) < 0) {
                // stack1 has smaller element, pop it and push it to the merged stack
                mergedStack.push(stack1.pop());
            } else {
                // stack2 has smaller or equal element, pop it and push it to the merged stack
                mergedStack.push(stack2.pop());
            }
        }
        // if stack1 is not empty, push its remaining elements to the merged stack
        while (!stack1.isEmpty()) {
            mergedStack.push(stack1.pop());
        }
        // if stack2 is not empty, push its remaining elements to the merged stack
        while (!stack2.isEmpty()) {
            mergedStack.push(stack2.pop());
        }
        return mergedStack;
    }

    // method to reverse a stack
    public static <E extends Comparable<E>> GenericStack<E> reverseStack(GenericStack<E> stack) {
        // create a new stack to store the reversed elements
        GenericStack<E> reversedStack = new GenericStack<>();
        while (!stack.isEmpty()) {
            // pop an element from the stack and push it to the reversed stack
            reversedStack.push(stack.pop());
        }
        return reversedStack;
    }

    public static <E extends Comparable<E>, F extends Comparable<F>> void printTwoStacks(GenericStack<E> stack1, GenericStack<F> stack2) {
        System.out.println("Printing Merged Stacks side by side - lowest value on top");
        System.out.println("Integers     String");
        
        // create two temporary stacks to store the elements
        GenericStack<E> temp1 = new GenericStack<>();
        GenericStack<F> temp2 = new GenericStack<>();
        
        // loop until both stacks are empty
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // pop an element from each stack and print them side by side
            E element1 = stack1.isEmpty() ? null : stack1.pop();
            F element2 = stack2.isEmpty() ? null : stack2.pop();
            
            // Format the output to have a fixed width for numbers and strings
            String output = String.format("%-12s %-12s", element1 == null ? "....." : element1, element2 == null ? "....." : element2);
            System.out.println(output);
            
            // push the elements to the temporary stacks
            if (element1 != null) temp1.push(element1);
            if (element2 != null) temp2.push(element2);
        }
        
        // restore the original stacks for temp1
        while (!temp1.isEmpty()) {
            stack1.push(temp1.pop());
        }
        
        // restore the original stacks for temp2
        while (!temp2.isEmpty()) {
            stack2.push(temp2.pop());
        }
    }


    }
