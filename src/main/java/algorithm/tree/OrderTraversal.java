package algorithm.tree;

import java.util.Stack;

/**
 * Created by dongliang on 17/11/6.
 */
public class OrderTraversal {


    public class Node {

        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public void preRecuOrder(Node head) {
        if (head == null) {
            return;
        }

        System.out.println("the head value is" + head.data);
        preRecuOrder(head.left);
        preRecuOrder(head.right);
    }

    public void middleRecuOrder(Node head) {
        if (head == null) {
            return;
        }

        middleRecuOrder(head.left);
        System.out.println("the head value is" + head.data);
        middleRecuOrder(head.right);
    }


    public void afterRecuOrder(Node head) {

        if (head == null) {
            return;
        }

        afterRecuOrder(head.left);
        afterRecuOrder(head.right);
        System.out.println("the head value is:" + head.data);
    }

    public void preUnRecuOrder(Node head){
        System.out.println("the per order");

        if(head != null){
            Stack<Node> stack = new Stack();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println("the head value is:"+head.data);
                if(head.right != null){
                    stack.push(head.right);
                }

                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
    }

    public void middleUnRecuOrder(Node head){

        System.out.println("the middle order");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() && head != null){
                if(head != null){
                    stack.push(head.left);
                    head = head.left;
                }else{
                    stack.push(head.right);
                    head = head.right;
                }
            }
        }
    }
}
