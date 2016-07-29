public class Heap 
{
	public static Node[] Insert(Node[] heap, Node v, Integer n){
		heap[n].vertex = v.vertex;
		heap[n].weight = v.weight;
		int father;
		father = (int)Math.floor((n-1)/2);
		if(heap[father].weight >= heap[n].weight){
			return heap;
		}else{
			heapfy(heap,n);
		}	
		return heap;
	}

	public static Node[] Delete(Node[] heap, Integer index, Integer n){
		heap[index].vertex = heap[n-1].vertex;
		heap[index].weight = heap[n-1].weight;
		heap[n-1].vertex = 0;
		heap[n-1].weight = 0;
		int father;
		father = (int)Math.floor((index-1)/2);
		if(index<2500){
			if((heap[father].weight >= heap[index].weight) && (heap[index].weight >= heap[2*index+1].weight) && (heap[index].weight >= heap[2*index+2].weight)){
				return heap;
			}else{
				heapfy(heap,index);
			}
		}else{
			if(heap[father].weight >= heap[index].weight){
				return heap;
			}else{
				heapfy(heap,index);
			}
		}	
		return heap;
	}
	

	public static Node[] heapfy(Node[] heap, Integer index){
		int father1;
		father1 = (int)Math.floor((index-1)/2);
		if(index<2500){
			if((heap[father1].weight >= heap[index].weight) && (heap[index].weight >= heap[2*index+1].weight) && (heap[index].weight >= heap[2*index+2].weight)){
				return heap;
			}
			if(heap[father1].weight < heap[index].weight){
				Node temp = new Node(0,0,null,null);
				temp.vertex = heap[index].vertex;
				temp.weight = heap[index].weight;
				heap[index].vertex= heap[father1].vertex;
				heap[index].weight= heap[father1].weight;
				heap[father1].vertex = temp.vertex;
				heap[father1].weight = temp.weight;
				//only need to check heap[father1] and its father
				int father2;
				father2 = (int)Math.floor((father1-1)/2);
				if(heap[father2].weight >= heap[father1].weight){
					return heap;
				}else{
					heapfy(heap,father1);
				}
			}else{
				if(heap[index*2+1].weight > heap[index*2+2].weight){
					Node temp = new Node(0,0,null,null);
					temp.vertex = heap[index].vertex;
					temp.weight = heap[index].weight;
					heap[index].vertex = heap[index*2+1].vertex;
					heap[index].weight = heap[index*2+1].weight;
					heap[index*2+1].vertex = temp.vertex;
					heap[index*2+1].weight = temp.weight;
					if((index*2+1)<2500){
						if((heap[index*2+1].weight >= heap[index*4+3].weight) && (heap[index*2+1].weight >= heap[index*4+4].weight)){
							return heap;
						}else{
							heapfy(heap,(index*2+1));
						}
					}else{
						return heap;
					}
					
				}else{
					Node temp = new Node(0,0,null,null);
					temp.vertex = heap[index].vertex;
					temp.weight = heap[index].weight;
					heap[index].vertex = heap[index*2+2].vertex;
					heap[index].weight = heap[index*2+2].weight;
					heap[index*2+2].vertex = temp.vertex;
					heap[index*2+2].weight = temp.weight;
					if((index*2+2)<2500){
						if((heap[index*2+2].weight >= heap[index*4+5].weight) && (heap[index*2+2].weight >= heap[index*4+6].weight)){
							return heap;
						}else{
							heapfy(heap,(index*2+2));
						}
					}else{
						return heap;
					}
					
				}
			}
		}else{
			if(heap[father1].weight >= heap[index].weight){
				return heap;
			}
			if(heap[father1].weight < heap[index].weight){
				Node temp = new Node(0,0,null,null);
				temp.vertex = heap[index].vertex;
				temp.weight = heap[index].weight;
				heap[index].vertex= heap[father1].vertex;
				heap[index].weight= heap[father1].weight;
				heap[father1].vertex = temp.vertex;
				heap[father1].weight = temp.weight;
				//only need to check heap[father1] and its father
				int father2;
				father2 = (int)Math.floor((father1-1)/2);
				if(heap[father2].weight >= heap[father1].weight){
					return heap;
				}else{
					heapfy(heap,father1);
				}
			}
		}
		
		
		return heap;
	}


	public static Edge[] InsertEdge(Edge[] heap, Integer w, Integer v1, Integer v2, Integer n){
		heap[n].weight = w;
		heap[n].vertex1 = v1;
		heap[n].vertex2 = v2;
		int father;
		father = (int)Math.floor((n-1)/2);
		if(heap[father].weight >= heap[n].weight){
			return heap;
		}else{
			heapfyEdge(heap,n);
		}	
		return heap;
	}

	public static Edge[] DeleteEdge(Edge[] heap, Integer index, Integer n){
		heap[index].vertex1 = heap[n-1].vertex1;
		heap[index].vertex2 = heap[n-1].vertex2;
		heap[index].weight = heap[n-1].weight;
		heap[n-1].vertex1 = 0;
		heap[n-1].vertex2 = 0;
		heap[n-1].weight = 0;
		int father;
		father = (int)Math.floor((index-1)/2);
		if(index<2999998){
			if((heap[father].weight >= heap[index].weight) && (heap[index].weight >= heap[2*index+1].weight) && (heap[index].weight >= heap[2*index+2].weight)){
				return heap;
			}else{
				heapfyEdge(heap,index);
			}
		}else{
			if(heap[father].weight >= heap[index].weight){
				return heap;
			}else{
				heapfyEdge(heap,index);
			}
		}		
		return heap;
	}

	public static Edge[] heapfyEdge(Edge[] heap, Integer index){
		int father1;
		father1 = (int)Math.floor((index-1)/2);
		if(index<2999998){
			if((heap[father1].weight >= heap[index].weight) && (heap[index].weight >= heap[2*index+1].weight) && (heap[index].weight >= heap[2*index+2].weight)){
			return heap;
			}
			if(heap[father1].weight < heap[index].weight){
				Edge temp = new Edge(0,0,0);
				temp.vertex1 = heap[index].vertex1;
				temp.vertex2 = heap[index].vertex2;
				temp.weight = heap[index].weight;
				heap[index].vertex1= heap[father1].vertex1;
				heap[index].vertex2= heap[father1].vertex2;
				heap[index].weight= heap[father1].weight;
				heap[father1].vertex1 = temp.vertex1;
				heap[father1].vertex2 = temp.vertex2;
				heap[father1].weight = temp.weight;
				//only need to check heap[father1] and its father
					int father2;
				father2 = (int)Math.floor((father1-1)/2);
				if(heap[father2].weight >= heap[father1].weight){
					return heap;
				}else{
					heapfyEdge(heap,father1);
				}
			}else{
				if(heap[index*2+1].weight > heap[index*2+2].weight){
					Edge temp = new Edge(0,0,0);
					temp.vertex1 = heap[index].vertex1;
					temp.vertex2 = heap[index].vertex2;
					temp.weight = heap[index].weight;
					heap[index].vertex1 = heap[index*2+1].vertex1;
					heap[index].vertex2 = heap[index*2+1].vertex2;
					heap[index].weight = heap[index*2+1].weight;
					heap[index*2+1].vertex1 = temp.vertex1;
					heap[index*2+1].vertex2 = temp.vertex2;
					heap[index*2+1].weight = temp.weight;
					if((index*2+1)<2999998){
						if((heap[index*2+1].weight >= heap[index*4+3].weight) && (heap[index*2+1].weight >= heap[index*4+4].weight)){
							return heap;
						}else{
							heapfyEdge(heap,(index*2+1));
						}
					}else{
						return heap;
						
					}
					
				}else{
					Edge temp = new Edge(0,0,0);
					temp.vertex1 = heap[index].vertex1;
					temp.vertex2 = heap[index].vertex2;
					temp.weight = heap[index].weight;
					heap[index].vertex1 = heap[index*2+2].vertex1;
					heap[index].vertex2 = heap[index*2+2].vertex2;
					heap[index].weight = heap[index*2+2].weight;
					heap[index*2+2].vertex1 = temp.vertex1;
					heap[index*2+2].vertex2 = temp.vertex2;
					heap[index*2+2].weight = temp.weight;
					if((index*2+2)<2999998){
						if((heap[index*2+2].weight >= heap[index*4+5].weight) && (heap[index*2+2].weight >= heap[index*4+6].weight)){
							return heap;
						}else{
							heapfyEdge(heap,(index*2+2));
						}
					}else{
						return heap;
					}
					
				}
			}

		}else{
			if(heap[father1].weight >= heap[index].weight) return heap;
			if(heap[father1].weight < heap[index].weight){
				Edge temp = new Edge(0,0,0);
				temp.vertex1 = heap[index].vertex1;
				temp.vertex2 = heap[index].vertex2;
				temp.weight = heap[index].weight;
				heap[index].vertex1= heap[father1].vertex1;
				heap[index].vertex2= heap[father1].vertex2;
				heap[index].weight= heap[father1].weight;
				heap[father1].vertex1 = temp.vertex1;
				heap[father1].vertex2 = temp.vertex2;
				heap[father1].weight = temp.weight;
				//only need to check heap[father1] and its father
					int father2;
				father2 = (int)Math.floor((father1-1)/2);
				if(heap[father2].weight >= heap[father1].weight){
					return heap;
				}else{
					heapfyEdge(heap,father1);
				}
			}
		}
		
		
		
		return heap;
	}

	public static Edge[] heapsortEdge(Edge[] graph, Integer edgeNum){
		int i;
		Edge[] sortedEdge = new Edge[6000000];
		for(i=0;i<6000000;i++){
			sortedEdge[i] = new Edge(0,0,0);
		}
		i=0;
		while(edgeNum!=0){
			sortedEdge[i].vertex1 = graph[0].vertex1;
			sortedEdge[i].vertex2 = graph[0].vertex2;
			sortedEdge[i].weight = graph[0].weight;
			DeleteEdge(graph,0,edgeNum);
			edgeNum--;
			i++;
		}
		return sortedEdge;
	}


}	