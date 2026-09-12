package pure_test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    
    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        Queue<Node> q = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        Node start = new Node();

        q.offer(start);

        visited.add(start);

        while(!q.isEmpty()){
            
            Node cur = q.poll();
            
            cur.equals(target)

        }
    }


}


class Node {
    Node next;
}