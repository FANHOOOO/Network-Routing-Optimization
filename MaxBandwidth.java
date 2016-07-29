public class MaxBandwidth
{
	public static Integer[] Dijkstra_withheap(Node[] heap, Integer s, Integer t){
		long time1 = System.currentTimeMillis();
		Integer[] status = new Integer[5000];
		Integer[] dad = new Integer[5000];
		Integer[] weight = new Integer[5000];
		Node[] fringes = new Node[5100];
		int fringesNum=0;
		int i;
		int max_bandwidth=9000;
		//set all vertices as unseen(-1)
		for(i=0;i<5000;i++){
			status[i] = -1;
			dad[i] = 0;
			weight[i] = 0;
		}
		for(i=0;i<5100;i++){
			fringes[i] = new Node(0,0,null,null);
		}
		//set s as intree(1)
		status[s] = 1;
		//set fringes(0)
		Node temp;
		for(temp=heap[s];temp.next!=null;temp=temp.next){
			status[temp.vertex]=0;
			dad[temp.vertex]=s;
			Node newfringe = new Node(temp.vertex,temp.weight,null,null);
			fringes = Heap.Insert(fringes,newfringe,fringesNum);
			fringesNum++;
		}
		//while there are fringes do
		while(fringesNum != 0){
			int index=0;
			int vertex = fringes[0].vertex;
			status[vertex] = 1;
			//remove vertex
			weight[vertex] = fringes[0].weight;
			fringes = Heap.Delete(fringes,0,fringesNum);
			fringesNum--;
			for(temp=heap[vertex];temp.next!=null;temp=temp.next){
				if(status[temp.vertex] == 0){
					for(i=0;i<5000;i++){
						if(temp.vertex.equals(fringes[i].vertex)){
							index = i;
							break;
						}
					}
				}

				if(status[temp.vertex] == -1){
					status[temp.vertex] = 0;
					dad[temp.vertex] = vertex;
					Node freshfringes = new Node(temp.vertex, Math.min(weight[vertex],temp.weight),null,null);
					fringes = Heap.Insert(fringes,freshfringes,fringesNum);
					fringesNum++;

				}else if((status[temp.vertex] == 0) && (fringes[index].weight < Math.min(weight[vertex],temp.weight))){
					dad[temp.vertex] = vertex;	
					fringes[index].weight = Math.min(weight[vertex], temp.weight);
					fringes = Heap.heapfy(fringes,index);
				}
			}

		}
		max_bandwidth = weight[t];
		i=t;
		System.out.printf("\nMax Bandwidth Path of Dijkstra with heap: %d ",t);
		while(i!=s){
			System.out.printf("<- %d ", dad[i]);
			i = dad[i];
		}
		System.out.printf("\nMax Bandwidth of Dijkstra with heap: %d\n",max_bandwidth);
		long time2 = System.currentTimeMillis();
		Integer time = (int)(time2-time1);
		System.out.printf("Running time for Dijkstra with heap is: %d milliseconds\n",time);
		return dad;
	}


	public static Integer[] Dijkstra_noheap(Node[] graph, Integer s, Integer t){
		long time1 = System.currentTimeMillis();
		Integer[] status = new Integer[5000];
		Integer[] dad = new Integer[5000];
		Integer[] fringes = new Integer[5000];
		Integer[] weight = new Integer[5000];
		int fringesNum=0;
		int max_bandwidth=9000;
		int i;
		//set all vertices as unseen(-1)
		for(i=0;i<5000;i++){
			status[i] = -1;
			dad[i] = 0;
			fringes[i] = 0;
		}
		//set s as intree(1)
		status[s] = 1;
		//set fringes(0)
		Node temp;
		for(temp=graph[s];temp.next!=null;temp=temp.next){
			status[temp.vertex]=0;
			dad[temp.vertex]=s;
			fringes[temp.vertex] = temp.weight;
			fringesNum++;
		}
		//while there are fringes do
		while(fringesNum != 0){
			int index,vertex=0,max=0;
			for(i=0;i<5000;i++){
				if((fringes[i]>max) && (status[i]!=1)){
					max = fringes[i];
					vertex = i;
				}
			}
			status[vertex] = 1;
			for(temp=graph[vertex];temp.next!=null;temp=temp.next){
				if(status[temp.vertex] == -1){
					status[temp.vertex] = 0;
					dad[temp.vertex] = vertex;
					fringes[temp.vertex] = Math.min(temp.weight,fringes[vertex]);
					fringesNum++;		
				}else if((status[temp.vertex] == 0) && (fringes[temp.vertex] < Math.min(fringes[vertex],temp.weight))){
					dad[temp.vertex] = vertex;
					fringes[temp.vertex] = Math.min(temp.weight,fringes[vertex]);
				}
			}
			//remove vertex
			weight[vertex] = fringes[vertex];
			fringes[vertex] = 0;
			fringesNum--;

		}
		max_bandwidth = weight[t];

		i=t;
		System.out.printf("\nMax Bandwidth Path of Dijkstra without heap: %d ",t);
		while(i!=s){
			System.out.printf("<- %d ", dad[i]);
			i = dad[i];
		}
		System.out.printf("\nMax Bandwidth of Dijkstra without heap: %d\n",max_bandwidth);
		long time2 = System.currentTimeMillis();
		Integer time = (int)(time2-time1);
		System.out.printf("Running time for Dijkstra without heap is: %d milliseconds\n",time);
		return dad;
	}



	public static UFM[] Kruskal(Node[] graph, Integer s, Integer t){
		long time1 = System.currentTimeMillis();
		int i,j,edgeNum=0;
		Node temp;
		Integer[] dad = new Integer[5000];
		Node[] newgraph = graph;
		Edge[] edge = new Edge[6000000];
		UFM [] ufm = new UFM[5000];

		for(i=0;i<6000000;i++){
			edge[i] = new Edge(0,0,0);
		}

		for(i=0;i<5000;i++){
			for(temp=newgraph[i];temp.next!=null;temp=temp.next){
				Heap.InsertEdge(edge,temp.weight,i,temp.vertex,edgeNum);
				edgeNum++;
				newgraph = Node.delete(newgraph,temp.vertex,i);
			}
		}
		//System.out.println(edgeNum);
		//System.out.println(edgeNum);
		edge = Heap.heapsortEdge(edge,edgeNum);

		for(i=0;i<5000;i++){
			ufm[i] = new UFM(i,null,0);
			ufm = UFM.Makeset(ufm,i);
		}

		int vertex1, vertex2,weight;
		UFM node1,node2;
		Node[] spanningtree = new Node[5000];
		for(i=0;i<5000;i++){
			spanningtree[i] = new Node(0,0,null,null);
		}
		Integer chosenEdgeNum = 0;
		for(i=0;i<edgeNum;i++){
			vertex1 = edge[i].vertex1;
			vertex2 = edge[i].vertex2;
			weight = edge[i].weight;
			node1 = UFM.Find(ufm,vertex1);
			node2 = UFM.Find(ufm,vertex2);
			if(UFM.nosamefather(node1,node2)){
				spanningtree[vertex1] = Node.addbefore(vertex2,weight,spanningtree[vertex1]);
				spanningtree[vertex2] = Node.addbefore(vertex1,weight,spanningtree[vertex2]);
				ufm = UFM.Union(node1.vertex,node2.vertex,ufm);
			}

		}

		dad = Node.FindPath(s,t,spanningtree);
		int max_bandwidth=9000;
		i=t;


		System.out.printf("\nMax Bandwidth Path of Kruskal Algorithm: %d ",t);
		while(i!=s){
			for(temp=spanningtree[i];temp.next!=null;temp=temp.next){
				if((temp.vertex.equals(dad[i])) && (temp.weight < max_bandwidth)){
					max_bandwidth = temp.weight;
				}
			}
			System.out.printf("<- %d ", dad[i]);		
			i = dad[i];
		}
		System.out.printf("\nMax Bandwidth of Kruskal Algorithm: %d\n", max_bandwidth);
		long time2 = System.currentTimeMillis();
		Integer time = (int)(time2-time1);
		System.out.printf("Running time for Kruskal Algorithm is: %d milliseconds\n\n",time);

		return ufm;
	}
}