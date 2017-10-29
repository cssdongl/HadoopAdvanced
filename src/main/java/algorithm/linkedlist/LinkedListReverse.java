package algorithm.linkedlist;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by dongliang on 17/10/29.
 */
public class LinkedListReverse {

   static class Node{

        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
        }

        public int getData(){
            return this.data;
        }

        public void setData(int data){
            this.data = data;
        }

        public Node getNext(){
            return this.next;
        }

        public void setNext(Node next){
            this.next = next;
        }
    }

    public Node reverseList1(Node head){
        /**
         * reverse the node one by one change the point relation
         */
        Node pre = null;
        Node next = null;

        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        Node h = head;
        while(null != h){
            System.out.println("the original order for linkedlist is:"+h.getData());
            h = h.getNext();
        }

        Node reverseHead = new LinkedListReverse().reverseList1(head);

        Node temp = reverseHead;

        while(null != temp){
            System.out.println("the new reverse order for linkedlist is:"+temp.getData());
            temp = temp.getNext();
        }
    }
}
