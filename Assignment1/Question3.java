import java.util.*;

public class Question3 {

    static class Edge {
        int src, des, weight;
        Edge() {
            src = des = weight = 0;
        }
    }

    public static void printpath(int[] parent, int des) {
        if(parent[des]==-1) {
            System.out.print(des+" ");
            return;
        }
        printpath(parent, parent[des]);
        System.out.print(des+" ");
    }

    public static void bellman(Edge[] edges, int V, int E, int src, int des) {

        int dist[] = new int[V];
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        int parent[] = new int[V];
        for(int i=0;i<V;i++)
            parent[i] = -1;

        for(int i=1;i<V;i++) {
            for(int j=0;j<E;j++) {
                int u = edges[j].src;
                int v = edges[j].des;
                int w = edges[j].weight;
                if(dist[u]!=Integer.MAX_VALUE && dist[u]+w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                }
            }
        }

        for(int i=0;i<E;i++) {
            int u = edges[i].src;
            int v = edges[i].des;
            int w = edges[i].weight;
            if(dist[u]!=Integer.MAX_VALUE && dist[u]+w < dist[v]) {
                System.out.println("Negative Cycle Exists");
                return;
            }
        }

        System.out.println("The Shortest path from "+src+" to "+des+" is: ");
        printpath(parent, des);
        System.out.println();
    }

    public static void printallpaths(Edge[] edges, int V, int E, int src, int des, boolean[] visited, ArrayList<Integer> ans) {
        visited[src] = true;
        if(src==des){
            for(int i=0;i< ans.size();i++){
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
            visited[src] = false;
            return;
        }

        for(int i=0;i<E;i++) {
            if(edges[i].src == src && !visited[edges[i].des]) {
                ans.add(edges[i].des);
                printallpaths(edges, V, E, edges[i].des, des, visited, ans);
                ans.remove(ans.size()-1);
            }
            if(edges[i].des == src && !visited[edges[i].src]) {
                ans.add(edges[i].src);
                printallpaths(edges, V, E, edges[i].src, des, visited, ans);
                ans.remove(ans.size()-1);
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

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; ++i)
            edges[i] = new Edge();

        System.out.println("Enter Starting vertex, Ending vertex and its Weight");
        for(int i=0;i<E;i++) {
            String input = sc.nextLine();
            String[] numbers = input.split(" ");
            int s = Integer.parseInt(numbers[0]);
            int d = Integer.parseInt(numbers[1]);
            int w = Integer.parseInt(numbers[2]);
            edges[i].src = s;
            edges[i].des = d;
            edges[i].weight = w;
        }

        System.out.println("Enter Source Vertex");
        int source = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Destination Vertex");
        int destination = Integer.parseInt(sc.nextLine());

        // shortest path - dijkstra - having shortest weight;
        bellman(edges, V, E, source, destination);

        // all paths from src to des
        boolean[] visited = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<Integer>();
        System.out.println("All paths from "+source+" to "+destination+" are: ");
        ans.add(source);
        printallpaths(edges, V, E, source, destination, visited, ans);

    }
}

//0 1 1
//0 3 1
//1 3 2
//1 2 1
//1 4 2
//3 4 1
//2 4 1
//2 5 2
//4 5 1

// 0 1 2 4 5
// 0 1 2 5
// 0 1 3 4 2 5
// 0 1 3 4 5
// 0 1 4 2 5
// 0 1 4 5
// 0 3 1 2 4 5
// 0 3 1 2 5
// 0 3 1 4 2 5
// 0 3 1 4 5
// 0 3 4 1 2 5
// 0 3 4 2 5
// 0 3 4 5
