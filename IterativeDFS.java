import java.util.*;
public class IterativeDFS {
    

    
    static class AdjacencyList{
        private Map<Integer, List<Integer>> ListMap;
        private int V;

        public AdjacencyList(int V){
            this.V = V;
            ListMap = new HashMap<>();
            for(int i = 0; i < V; i++){
                ListMap.put(i, new LinkedList<>());
            }

        }
        public void addEdge(int source, int destination){
            ListMap.get(source).add(destination);
            ListMap.get(destination).add(source);
        }

        public void iterativeDFS(int start_vertex){
            Set<Integer> visited = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            stack.push(start_vertex);
            visited.add(start_vertex);
            while(!stack.isEmpty()){
                int current_vertex = stack.pop();
                System.out.println(current_vertex);
                
                

                for(int neighbour : ListMap.getOrDefault(current_vertex, Collections.emptyList())){
                    if(!visited.contains(neighbour)){
                        stack.push(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }

        public void DFS(int start_vertex ){
            HashSet<Integer> visited = new HashSet<>();
            recursiveDFS(start_vertex, visited);
        }
        private void recursiveDFS(int vertex, HashSet<Integer> visited){
            visited.add(vertex);
            System.out.println(vertex);

            for(int neighbour : ListMap.getOrDefault(vertex, Collections.emptyList())){
                if(!visited.contains(neighbour)){
                    recursiveDFS(neighbour, visited);
                }
            }
        }

        public void BFSiterative(int start_vertex){
            Queue<Integer> queue = new LinkedList<>();
            HashSet<Integer> visited = new HashSet<>();

            queue.add(start_vertex);
            visited.add(start_vertex);

            while(!queue.isEmpty()){
                int current_vertex = queue.poll();
                System.out.println(current_vertex);

                for(int neighbour : ListMap.getOrDefault(current_vertex, Collections.emptyList())){
                    if(!visited.contains(neighbour)){
                        queue.add(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }


        public void removeEdge(int source, int destination){
            ListMap.get(source).remove((Integer) destination);
            ListMap.get(destination).remove((Integer) source);
        }
        public void addVertex(int vertex){
            if(!ListMap.containsKey(vertex)){
                ListMap.put(vertex, new LinkedList<>());
                vertex++;
            }
            
        }

        

        public void removeVertex(int vertex){
            ListMap.remove(vertex);
            for(List<Integer> neighbour : ListMap.values()){
                neighbour.remove((Integer) vertex);
            }
        }

        
        public void printGraph(){

            for(int i : ListMap.keySet()){
                System.out.print(i + "->");
                for(int j  : ListMap.get(i)){
                    System.out.print(j + " ");
                }
                System.out.println();
            }

        }
    }
    public static void main(String[] args){
        int n = 4;
        AdjacencyList adj = new AdjacencyList(n);
        adj.addEdge(2, 0);
        adj.addEdge(1, 3);
        adj.addEdge(2, 3);
        adj.addEdge(2, 1);
        adj.addEdge(3, 0);


        System.out.println("ORIGINAL LINKED LIST GRAPH : ");
        adj.printGraph();

        System.out.println("ITERATIVE DFS APPROACH : ");
        adj.iterativeDFS(1);

        System.out.println("RECURSIVE DFS APPROACH : ");

        adj.DFS(3);

        System.out.println("ITERATIVE BFS APPROACH : ");
        adj.BFSiterative(1);

        System.out.println("AFTER REMOVING ELEMENTS : ");
        adj.removeEdge(1,3);
        adj.printGraph();

        System.out.println("AFTER ADDING VERTEX");
        adj.addVertex(4);
        adj.printGraph();

        System.out.println("REMOVING VERTEX");
        adj.removeVertex(2);
        adj.printGraph();

        


    }
}

