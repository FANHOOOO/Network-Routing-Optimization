public class Testing{
	public static void main(String[] args){
		
	//generate a graph pair
	Node[] graph1 = Graph.G1();
	Node[] graph2 = Graph.G2();

	//pick 5 pairs source-destination vertices and run algorithm
	Integer i=0;
	for(i=0;i<5;i++){
		Run(graph1,graph2,i+1);
	}

		
	}
	public static void Run(Node[] graph1, Node[] graph2, Integer pair){
			Integer source,destination;
			Integer[] sd = new Integer[2];
			Node[] graph1test = new Node[5000];
			Node[] graph2test = new Node[5000];
			Node temp;
			int i=0;
			for(i=0;i<5000;i++){
				graph1test[i] = new Node(0,0,null,null);
				for(temp=graph1[i];temp.next!=null;temp=temp.next){
					graph1test[i] = Node.addbefore(temp.vertex,temp.weight,graph1test[i]);
				}
			}
			for(i=0;i<5000;i++){
				graph2test[i] = new Node(0,0,null,null);
				for(temp=graph2[i];temp.next!=null;temp=temp.next){
					graph2test[i] = Node.addbefore(temp.vertex,temp.weight,graph2test[i]);
				}
			}

			sd = Graph.GenerateSD();
			source = sd[0];
			destination = sd[1];

			Graph.AddTestPath(graph1test,source,destination);
		
			Graph.AddTestPath(graph2test,source,destination);

			System.out.printf("Pair %d: from source vertex: %d to destination vertex: %d\n\n",pair,source,destination);
			System.out.printf("Results for Graph 1:\n");
			MaxBandwidth.Dijkstra_noheap(graph1test,source,destination);
			
			MaxBandwidth.Dijkstra_withheap(graph1test,source,destination);

			MaxBandwidth.Kruskal(graph1test,source,destination);

			System.out.printf("Results for Graph 2:\n");
			MaxBandwidth.Dijkstra_noheap(graph2test,source,destination);
		
			MaxBandwidth.Dijkstra_withheap(graph2test,source,destination);

			MaxBandwidth.Kruskal(graph2test,source,destination);
	}
}