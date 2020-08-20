import java.util.*;

// only positive weights allowed
// no negative cycles can exist

public class Question3 {

    public static int[][] mat;

    public static int getMinVertex(boolean[] visited, int[] weight, int V) {
        int W = Integer.MAX_VALUE;
        int vertex = -1;
        for(int i=0;i<V;i++){
            if(!visited[i] && weight[i]<W){
                vertex = i;
                W = weight[i];
            }
        }
        return vertex;
    }

    public static void printpath(int[] parent, int des) {
        if(parent[des]==-1) {
            System.out.print(des+" ");
            return;
        }
        printpath(parent, parent[des]);
        System.out.print(des+" ");
    }

    public static void dijkstra(int V, int source, int destination) {
        boolean[] visited = new boolean[V];
        int[] weight = new int[V];
        int[] parent = new int[V];
        for(int i=0;i<V;i++) {
            weight[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        weight[source] = 0;
        for(int i=0;i<V-1;i++) {
            int minvertex = getMinVertex(visited, weight, V);
            visited[minvertex] = true;
            for(int j=0;j<V;j++) {
                if(!visited[j]) {
                    if(mat[j][minvertex] != 0){
                        if(weight[j]>mat[minvertex][j]+weight[minvertex]) {
                            weight[j] = mat[minvertex][j]+weight[minvertex];
                            parent[j] = minvertex;
                        }
                    }
                }
            }
        }
        System.out.println("The Shortest path from "+source+" to "+destination+" is: ");
        printpath(parent, destination);
        System.out.println();
    }

    public static void printallpaths(int V, int src, int des, boolean[] visited, ArrayList<Integer> ans) {
        visited[src] = true;
        if(src==des){
            for(int i=0;i< ans.size();i++){
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
            visited[src] = false;
            return;
        }
        for(int i=0;i<V;i++) {
            if(!visited[i]) {
                if(mat[src][i]!=0) {
                    ans.add(i);
                    printallpaths(V, i, des, visited, ans);
                    ans.remove(ans.size()-1);
                }
            }
        }
        visited[src] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Vertices");
        int V = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Number of Edges");
        int E = Integer.parseInt(sc.nextLine());
        mat = new int[V+1][V+1];
        System.out.println("Enter Starting vertex, Ending vertex and its Weight");
        for(int i=0;i<E;i++) {
            String input = sc.nextLine();
            String[] numbers = input.split(" ");
            int s = Integer.parseInt(numbers[0]);
            int d = Integer.parseInt(numbers[1]);
            int w = Integer.parseInt(numbers[2]);
            mat[s][d] = w;
            mat[d][s] = w;
        }
        System.out.println("Enter Source Vertex");
        int source = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Destination Vertex");
        int destination = Integer.parseInt(sc.nextLine());

        // shortest path - dijkstra - having shortest weight;
        dijkstra(V, source, destination);

        // all paths from src to des
        boolean[] visited = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<Integer>();
        System.out.println("All paths from "+source+" to "+destination+" are: ");
        ans.add(source);
        printallpaths(V, source, destination, visited, ans);
    }
}