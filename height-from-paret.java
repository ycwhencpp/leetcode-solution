/** You need to find the height of a tree given its parent-child relationship in an array
where each index represents a node, and the value at that index represents its parent. 
The root node has a value of -1.


Input: [4, 3, 0, 6, 6, 3, -1, 0]
Output: 4
**/


public class Main{

  public static void main(int[] args) {
      int[] arr = new int [] {4, 3, 0, 6, 6, 3, -1, 0};
      List<List<Integer>> adj = new ArrayList<>();
      for(int i=0; i<arr.length; i++) adj.add(new ArrayList<>());

      int root = -1;
      for(int i=0; i<arr.length; i++){
        if(arr[i] == -1) {
          root = i;
          continue;
        }
        
        adj.get(arr[i]).add(i);
        
      }
      Queue<Integer> q = new LinkedList<>();
      int height =0;
      q.offer(root);
      while(!q.isEmpty()){
        height++;
        int size = q.size();
        for(int i=0; i<size; i++){
            int node = q.poll();
            for(int neigh : adj.get(node)) q.offer(neigh);
        }
      }
      System.out.println(height);
      return;
      
  }
}
