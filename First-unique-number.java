/** You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.


Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1


**/ 



class Node{
    int val;
    Node next;
    Node prev;
    public Node(int val){
        this.val = val;
    }
}

public class Solution {
    /**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */

    
    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here

        Node head = new Node(-1);
        Node tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        HashMap<Integer, Node> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for(int i=0; i<nums.length; i++){
            
            if(map.containsKey(nums[i])) continue;
           
            if(map.containsKey(nums[i])){
                delete(map.get(nums[i]), head, tail);
                set.add(nums[i]);
                map.remove(map.get(nums[i]).val, map.get(nums[i]));
            } else {
                Node node = new Node(nums[i]);
                map.put(node.val, node);
                add(node, head, tail);
            }
            if(nums[i] == number){
                return head.next.val;
            }
        }
        return -1;
    }

    public void add(Node node, Node head, Node tail){
       
        Node prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;
    }

    public void delete(Node node, Node head, Node tail){
       

        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.next= null;
        node.prev = null;
    }

}