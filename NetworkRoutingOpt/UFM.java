import java.io.*;
import java.util.*;
class UFM{
	public static UFM Find(UFM[] vertex, Integer node){
		UFM temp = vertex[node];
		while(temp.father!=temp){
			temp = temp.father;
		}
		return temp;
	}

	public static UFM[] Makeset(UFM[] vertex, Integer node){
		vertex[node].vertex = node;
		vertex[node].rank = 0;
		vertex[node].father = vertex[node];
		return vertex;
	}

	public static UFM[] Union(Integer node1, Integer node2, UFM[] vertex){
		if(compare(node1,node2,vertex)>0){
			vertex[node2].father = vertex[node1];
		}else{
			vertex[node1].father = vertex[node2];
			if(compare(node1,node2,vertex) == 0){
				vertex[node2].rank = vertex[node2].rank +1;
			}
		}
		return vertex;
	}

	public UFM(Integer thisvertex, UFM node, Integer thisrank){
		vertex = thisvertex;
		father = node;
		rank = thisrank;
	}


	public static boolean nosamefather(UFM son1, UFM son2){
		if(son1.vertex.equals(son2.vertex)){
			return false;
		}else{
			return true;
		}
	}

	public static Integer compare(Integer node1, Integer node2, UFM[] vertex){
		return vertex[node1].rank.compareTo(vertex[node2].rank);
	}
	public UFM father;
	public Integer vertex;
	public Integer rank;
}