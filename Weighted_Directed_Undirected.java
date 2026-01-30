import java.util.*;
public class Weighted_Directed_Undirected {
    static class GraphEdge{
        private int source;
        private int destination;
        private int weight;

        public GraphEdge(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
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

        List<GraphEdge>[] list = wg.getVertices();
        for(int i = 0; i < list.length; i++){
            System.out.print("Vertex : " + i + " ->");

            for(GraphEdge edge : list[i]){
                System.out.print("(" + edge.destination + ", weight = "+ edge.weight + ")   ");
            }
            System.out.println();
        }


    }
}
