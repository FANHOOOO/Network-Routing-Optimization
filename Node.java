
public class Node
{
	public Node(Integer v, Integer w, Node p, Node n){
		vertex = v;
		weight = w;
		prev = p;
		next = n;
	}

	public Integer vertex;
	public Integer weight;
	public Node prev;
	public Node next;

	public static Node addbefore(Integer v, Integer w, Node p){
		Node newnode = new Node(v,w,null,p);
		p.prev = newnode;
		return newnode;
	}


	public static Node[] delete(Node[] graph, Integer vertex1, Integer vertex2){
		Node temp;
		for (temp=graph[vertex1];temp.next!=null;temp=temp.next) {
			if(vertex2.equals(temp.vertex)){
				if(temp.prev == null){
					temp.vertex=0;
					temp.weight=0;
					graph[vertex1] = temp.next;
					temp.next.prev = null;
				}else{
					temp.vertex=0;
					temp.weight=0;
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
				}
				break;
			}
		}
		return graph;
	}

	public static Integer[] FindPath(Integer s, Integer t, Node[] graph){
		Integer[] dad = new Integer[5000];
		Integer[] color = new Integer[5000];
		Integer i=0;
		for(i=0;i<5000;i++){
			color[i] = -1;
			dad[i] = 0;
		}
		dad = DFS(s,graph,color,dad);
		return dad;

	}

	public static Integer[] DFS(Integer s, Node[] graph, Integer[] color, Integer[] dad){
		color[s] = 0;
		Node temp;
		for(temp=graph[s];temp.next!=null;temp=temp.next){
			if(color[temp.vertex].equals(0)) continue;
			dad[temp.vertex]=s;
			if(color[temp.vertex].equals(-1)){
				DFS(temp.vertex,graph,color,dad);
			}
		}
		color[s] = 1;
		return dad;
	}
}