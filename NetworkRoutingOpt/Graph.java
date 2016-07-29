import java.util.Random;
public class Graph {
	
	public static Node[] G1() {
		Node[] graph1 = new Node[5000];
		Integer[] nodecount = new Integer[5000];
		int i,j;
		int repeat=0;
		Node temp;
		for(i=0; i<5000; i++){
			graph1[i] = new Node(0,0,null,null);
			nodecount[i] = 0;
		}
		for(i=0; i<5000; i++){
			while(nodecount[i]<6){
				Random rw = new Random();
				int rweight = rw.nextInt(5000)+120;
				Random rv = new Random();
				int rvertex = rv.nextInt(5000)+0;
				int set[] = {0,0,0,0,0,0};
				int k=0;
				for(temp=graph1[i];temp.next!=null;temp=temp.next){
					set[k] = temp.vertex;
					k++;
				}
				for(k=0;k<6;k++){
					if((set[k] == rvertex) || (nodecount[rvertex]>5) || (rvertex == i)){
						for(j=0;j<5000;j++){
							if((nodecount[j]<6) && (j!=i) && (j!=set[0]) && (j!=set[1]) && (j!=set[2]) && (j!=set[3]) && (j!=set[4]) && (j!=set[5])){
								rvertex = j;
								break;
							}
						}
					}
				}
				
				graph1[i] = Node.addbefore(rvertex,rweight,graph1[i]);
				nodecount[i]++;
				graph1[rvertex] = Node.addbefore(i,rweight,graph1[rvertex]);
				nodecount[rvertex]++;
				
			}
		}

		return graph1;
	}

	public static Node[] G2() {
		Node[] graph2 = new Node[5000];
		Integer[] nodecount = new Integer[5000];
		int i,j;
		Node temp;
		for(i=0; i<5000; i++){
			graph2[i] = new Node(0,0,null,null);
			nodecount[i] = 0;
		}
		for(i=0; i<5000; i++){
			Integer set[] = new Integer[1000];
			int k=0;
			for(k=0;k<1000;k++){
				set[k] = 0;
			}
			
			while(nodecount[i]<1000){
				Random rw = new Random();
				int rweight = rw.nextInt(5000)+123;
				Random rv = new Random();
				int rvertex = rv.nextInt(5000)+0;
				k=0;
				for(temp=graph2[i];temp.next!=null;temp=temp.next){
					set[k] = temp.vertex;
					k++;
				}
			
				if((!notIncluded(rvertex,set)) || (nodecount[rvertex]>999) || (rvertex == i)){
					for(j=0;j<5000;j++){
						if((nodecount[j]<1000) && (j!=i) && notIncluded(j,set)){
							rvertex = j;
							break;
						}
					}
				}
				
				
				graph2[i] = Node.addbefore(rvertex,rweight,graph2[i]);
				nodecount[i]++;
				graph2[rvertex] = Node.addbefore(i,rweight,graph2[rvertex]);
				nodecount[rvertex]++;
				
			}
		}
		return graph2;
	}

	public static boolean notIncluded(Integer s, Integer[] set){
		int i;
		for(i=0;i<1000;i++){
			if(set[i].equals(s)){
				return false;
			}
		}
		return true;
	}

	public static Node[] AddTestPath(Node[] graph, Integer s, Integer t){
		Integer[] node = new Integer[5000];
		int i=0;
		for(i=0;i<5000;i++){
			node[i] = i;
		}
		node[0]=s;
		node[s]=0;
		node[4999]=t;
		node[t]=4999;
		for(i=0;i<4999;i++){	
			Random rw = new Random();
			int rweight = rw.nextInt(5000)+123;
			graph[node[i]] = Node.addbefore(node[i+1],rweight,graph[node[i]]);
			graph[node[i+1]] = Node.addbefore(node[i],rweight,graph[node[i+1]]);
		}
		return graph;
	}

	public static Integer[] GenerateSD(){
		Integer[] sd = new Integer[2];
		Random s = new Random();
		int source = s.nextInt(5000)+0;
		sd[0] = source;
		Random d = new Random();
		int destination = d.nextInt(5000)+0;
		sd[1] = destination;
		while(sd[0]==sd[1]){
			Random d1 = new Random();
			int destination1 = d1.nextInt(5000)+0;
			sd[1] = destination1;
			if(sd[0] != sd[1]) break;
		}
		return sd;
	}

}