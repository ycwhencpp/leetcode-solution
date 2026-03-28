/** At a team event, engineers at Uber are sitting around a circular table playing a modified Rock–Paper–Scissors game.
Each engineer secretly chooses one of:
'R' → Rock
'P' → Paper
'S' → Scissors
After revealing their choices, every engineer compares their choice with their two immediate neighbors.
Two neighbors tie if they choose the same option.
Uber wants to make the game more interesting by ensuring no ties occur between any pair of neighbors.
You are allowed to ask any engineer to change their choice to either of the other two options.
Your task is to compute the minimum number of engineers whose choice must be changed so that no two adjacent engineers choose the same option.

Note that the engineers sit in a circle, so the first and last engineers are also neighbors.

Function Signature
int minChanges(string choices);
Input

A string choices of length n representing the selections of engineers sitting clockwise.

choices[i] ∈ {'R','P','S'}
Constraints
3 ≤ n ≤ 2 * 10^5

The arrangement is circular.

Example 1

Input

PRSSP

Output

2

Explanation

Two neighboring pairs have the same choice:

P R S S P
↑
S S

and the first and last:

P .... P

Changing two engineers is enough to remove all ties.

Example 2

Input

RRRRRRR

Output

4
Example 3

Input

RSPRPSPRS

Output

0

No adjacent engineers choose the same option.
**/
class Solution{
    public int findMinimumChange(String choices){

        int[] intVerison = new int[choices.length()];
        for(int i=0; i< choices.length(); i++){
            intVerison[i] = (choices.charAt(i) == 'R' ? 1 : (choices.charAt(i) == 'P' ? 2 : 3));
        }
        int ans =0;

        for(int i=0; i<3; i++){
            int isDataChanged = i == intVerison[i] ? 0 :1;
            int original = intVerison[0];
            intVerison[0]= i;
            int cost = isDataChanged + solve(1, intVerison);
            intVerison[0] = original;

            ans = Math.min(ans, cost);
        }


        return ans;
    }

    public int solve(int i, int[] intVerison){
        if(i>= intVerison.length){
            return 0;
        }
        int prev = intVerison[i-1];
        int count = (int)(1e9);
        for(int j=0; j<3; j++){
            if(prev == j) continue;
            if(i==intVerison.length-1 && intVerison[0] == j) continue;
            int isDataChanged = j == intVerison[i] ? 0 :1;
            intVerison[i] = j; // doesnt matter if already same or not
            int cost = isDataChanged + solve(i+1, intVerison);
            cost  = Math.min(count, cost);
        }
        return count;
    }
}

class main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: PRSSP -> 2
        System.out.println("Test Case 1 (PRSSP): " + sol.findMinimumChange("PRSSP")); 

        // Example 2: RRRRRRR -> 4
        System.out.println("Test Case 2 (RRRRRRR): " + sol.findMinimumChange("RRRRRRR")); 

        // Example 3: RSPRPSPRS -> 0
        System.out.println("Test Case 3 (RSPRPSPRS): " + sol.findMinimumChange("RSPRPSPRS")); 
        
        // Edge Case: Smallest circle
        System.out.println("Test Case 4 (RRR): " + sol.findMinimumChange("RRR")); // Expected 2 (e.g., RPS)
    }
}