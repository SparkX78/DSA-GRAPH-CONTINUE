import java.util.*;
public class Prims {
    static class GraphEdge{
        private int source;
        private int destination;
        private int weight;

        public GraphEdge(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
        public  int getWeight(){
            return weight;
        }
        public  int getDestination(){
            return destination;
        }

    }
    static class WeightedGraph{
        private int vertices;
        private List<GraphEdge>[] adjList;

        public WeightedGraph(int vertices){
            this.vertices = vertices;
            adjList = new ArrayList[vertices];
            for(int i = 0; i< vertices; i++){
                adjList[i] = new ArrayList<>();
            }
        }

        public void addDirectedEdge(int source, int destination, int weight){
            GraphEdge edge = new GraphEdge(source, destination, weight);
            adjList[source].add(edge);
        }
        public void addUndirectedEdge(int source, int destination, int weight){
            GraphEdge source_To_Destination = new GraphEdge(source, destination, weight);
            GraphEdge Destination_To_Source = new GraphEdge(destination, source, weight);

            adjList[source].add(source_To_Destination);
            adjList[destination].add(Destination_To_Source);
        }
        public List<GraphEdge>[] getVertices(){
            return adjList;
        }
    }

    static class PrimsMST{
        public static List<GraphEdge> primsMst(WeightedGraph graph){
            List<GraphEdge>[] vertices = graph.getVertices();
            boolean[] inMST = new boolean[vertices.length];
            PriorityQueue<GraphEdge> pq = new PriorityQueue<>(Comparator.comparingInt(GraphEdge ::getWeight));
            List<GraphEdge> MST = new ArrayList<>();

            int start_vertex = 0;
            inMST[start_vertex] = true;
            pq.addAll(vertices[start_vertex]);

            while(!pq.isEmpty()){
                GraphEdge current_edge = pq.poll();
                int destination = current_edge.getDestination();
                if(inMST[destination] == true){
                    continue;
                }
                MST.add(current_edge);
                inMST[destination] = true;
                pq.addAll(vertices[destination]);


            }
            return MST;

        } 
    }

    public static void main(String[] args){
        WeightedGraph wg = new WeightedGraph(5);
        wg.addDirectedEdge(1,4,8);
        wg.addDirectedEdge(1,2,10);
        wg.addDirectedEdge(1,3,12);
        wg.addDirectedEdge(0,4,14);
        wg.addDirectedEdge(0,1,1);
        wg.addDirectedEdge(0,2,17);
        wg.addDirectedEdge(4,3,21);

        wg.addUndirectedEdge(3,2,5);
        wg.addUndirectedEdge(3,4,18);
        wg.addUndirectedEdge(2,4,20);

        List<GraphEdge> mst = PrimsMST.primsMst(wg);
        int total_weight = 0;
        System.out.println("Edges in MST:");
        for (GraphEdge e : mst) {
            System.out.println(
                e.source + " -> " + e.destination + " (weight = " + e.weight + ")"
            );
            total_weight += e.weight;
        }

        System.out.println("Total MST weight = " + total_weight);

    }
}
