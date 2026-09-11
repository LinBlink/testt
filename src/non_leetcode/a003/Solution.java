package non_leetcode.a003;

/*
反转单链表：给定链表头结点 head，将链表反转并返回新的头结点。要求迭代实现，时间 O(n)、空间
O(1)。节点定义如下：
class ListNode {
    int val;
    ListNode next;
}
*/

public class Solution {
    public static void main(String[] args) {

        System.out.println("before processing");

        ListNode sample = createSample(10);;

        printLinkedList(sample);

        System.out.println("after processing");

        sample = mymethod(sample);

        printLinkedList(sample);

    }

    
    public static ListNode mymethod( ListNode head ){

        // null      1 ->     2      -> 3 -> 4
        // prev     curr    

        // null      1 ->     2      -> 3 -> 4
        // prev     curr    next

        // null  <-  1      2      -> 3 -> 4
        // prev     curr    next

        // null  <-  1      2      -> 3 -> 4
                   // prev  curr    

        // null  <-  1      2      -> 3     -> 4        ->null
                                          // prev        curr    


        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while (curr != null) {

            next = curr.getNext();

            curr.setNext(prev);

            // 这里接下来只需要移动两个指针
            
            prev = curr;

            curr = next;
        }

        return prev;
    }

    public static ListNode createSample( int length ){

        if (length<=1) {
            return null;
        }
        ListNode head = new ListNode(1);

        ListNode temp = head;
        for (int i = 2; i <= length; i++) {
            ListNode newNode = new ListNode(i);
            temp.setNext(newNode);
            temp = newNode;
        }

        return head;
        
    }

    public static void printLinkedList( ListNode head ){
        ListNode temp = head;
        System.err.print("(HEAD)");
        while (temp != null) {
            System.out.print( temp.getVal() + " -> " );
            temp = temp.getNext();
        }
        System.out.println( "null");
    }


}

class ListNode {
    
    private int val;
    private ListNode next;

    ListNode( int val ){
        this.val = val;
    }

    public int getVal(){
        return this.val;
    }

    public void setNext( ListNode next ){
        this.next = next;
    }

    public ListNode getNext(){
        return this.next;
    }

}