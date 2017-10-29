package algorithm.stackqueue;

import java.util.Stack;

/**
 * Created by dongliang on 17/10/29.
 */
public class MyStack1 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;


    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        /**
         * the stackMin's peek always be the min element.
         */
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.stackMin.peek()) {
            this.stackMin.push(newNum);
        }

        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Yours stack is empty");
        }

        int value = this.stackData.pop();

        if (value == this.stackMin.peek()) {
            this.stackMin.pop();
        }
        return this.stackData.peek();
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();

        stack1.push(2);
        stack1.push(3);
        stack1.push(1);
        stack1.push(6);
        stack1.push(9);

        int min = stack1.stackMin.pop();

        System.out.println("the min element in all the elements is:" + min);
    }
}
