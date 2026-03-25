class DSU{
    int[] parent;
    int[] rank;

    public DSU(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            parent[i]= i;
        }
    }

    public int find_parent(int u){
        if(parent[u] == u) return u;
        return parent[u] = find_parent(parent[u]);
    }

    public void join(int u, int v){
        int parent_u = find_parent(u);
        int parent_v = find_parent(v);

        if(parent_u == parent_v) return;

        if(rank[parent_u] > rank[parent_v]){
            parent[parent_v] = parent_u;
        } else if (rank[parent_u] < rank[parent_v]){
            parent[parent_u] = parent_v;
        } else {
            parent[parent_v] = parent_u;
            rank[parent_u]++;
        }
        return;

    }
}
class Solution {
    public List<Integer> numOfIslands(int n, int m, int[][] A) {
        List<Integer> ans = new ArrayList<>();
        int[] dc = new int[]{-1,1,0,0};
        int[] dr = new int[]{0,0,-1,1};
        DSU dsu = new DSU(n*m);
        boolean [][] seen = new boolean[n][m];
        int components = 0;
        for(int i=0; i< A.length; i++){
            int r = A[i][0];
            int c = A[i][1];
            int u = r*m+c;
            if(seen[r][c]) {
                ans.add(components);
                continue;
            }
            components++;
            for(int k =0; k<dc.length; k++){
                int nr = r + dr[k];
                int nc = c + dc[k];
                if(nr<0 || nr>=n || nc<0 || nc>= m) continue;

                //valid one
                int v = nr*m + nc;
                if(dsu.find_parent(u) != dsu.find_parent(v) && seen[nr][nc]) {
                    dsu.join(u,v);
                    components--;
                }
            }
            seen[r][c] = true;
            ans.add(components);
        }
        return ans;
    }
}


