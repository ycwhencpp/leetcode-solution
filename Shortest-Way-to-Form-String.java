    public int shortestWay(String s, String target) {
        // write your code here
        //s = abc 
        // target = abcbc
        int j=0;
        int count=0;
        while(j<target.length()){
            int i =0;
            int k = j;
            count++;
            while(i<s.length() && j<target.length()){
                if(s.charAt(i) == target.charAt(j)){
                    i++;
                    j++;
                } else{
                    i++;
                }
            }
            if(j>= target.length()) break;

            if(k == j) return -1;
        }
        return count;
    }