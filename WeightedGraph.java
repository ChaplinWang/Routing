import java.util.Comparator;
import java.util.PriorityQueue;



public class WeightedGraph {
	int size;

									//LinkedList<Edge> graph;
	Edge [][] graph;
	
	int [] Vlist;
	String CMD; 
	Comparator<Edge> comparator = new EdgeComparator();
	PriorityQueue<Edge> dieQ = new PriorityQueue<Edge> (100000,comparator);
	
	
	public WeightedGraph() {
		 size = 0;
		 graph = new Edge[100][];

			for(int i = 0; i < graph.length; i++)
			{
				graph[i] = new Edge[100];			
				for(int j = 0; j < i; j++){
					graph[i][j] = null;
				}
			}

		 Vlist = new int[100];

	}

	
	public void InsertEdge(char a, char b, int prob, int maxlink){
		Edge edge = new Edge(a, b, prob, maxlink);
		
		graph[(int)a - 65][(int)b - 65] = edge;
		graph[(int)b - 65][(int)a - 65] = edge;

		if(Vlist[(int)a - 65] == 0){
			Vlist[(int)a - 65]  = 1;
			size++;
		}
		if(Vlist[(int)b - 65] == 0){
			Vlist[(int)b - 65]  = 1;
			size++;
		}
	//	System.out.println("size = " + size );
	}
	
	public void show() {
		int i = 0;
		int j = 0;
		for(i = 0;i<100;i++){
			for(j = 0;j<100;j++){
				if(graph[i][j]!= null){
						System.out.print(graph[i][j].getVert1());
						System.out.println(graph[i][j].getVert2());
				}

			}

		}
	}
	
	public Edge inputLink(char a, char b, float dietime){
		
		return new Edge(a,b,dietime);
	}
	
	public boolean IsAdjacent(int i, int j){
		if(graph[i][j]!= null){return true;}
		else {return false;}
	}
	
	public float Update(float dist,int i,int j){
		if(CMD.equals("SDP")){
			//System.out.println(dist);
			return dist + graph[i][j].getProbagation();
		}
		else if (CMD.equals("LLP")) {
			float rate = ((float)graph[i][j].runTimeLink/graph[i][j].maxlink);
			
			if(dist < rate){
					return rate;
			}else{
					return dist;
			}
			
			
		}else{  //SHP
			return dist + 1;
		}
	}

	

	
	public int size() {
		return size;
	}
	
	public boolean ProcessLink(char a, char b){
		
		if(graph[(int)a - 65][(int)b - 65].runTimeLink < graph[(int)a - 65][(int)b - 65].maxlink){

	    				//System.out.println(graph[(int)a - 65][(int)b - 65].runTimeLink + "    " + a + " " + b);
						//System.out.println(graph[(int)b - 65][(int)a - 65].runTimeLink + "!!!!" + a + " " + b);

					//graph[(int)a - 65][(int)b - 65].runTimeLink++;
					//graph[(int)b - 65][(int)a - 65].runTimeLink++;

	    				//System.out.println(graph[(int)a - 65][(int)b - 65].runTimeLink + "???" + a + " " + b);
						//System.out.println(graph[(int)b - 65][(int)a - 65].runTimeLink + "~~~~" + a + " " + b);
			
			return true;
		}else{

			return false;
		}
		
	}

		
	 public class Edge {
		 private char V1,V2;
		 private int probagation;
		 private int maxlink;
		 public int runTimeLink;
		 public float dieTime;
		 
		 public Edge (char a,char b, int p, int m) {
			 V1 = a;
			 V2 = b;
			 probagation = p;
			 maxlink = m;
			 runTimeLink = 0;
		}
		 
		 public Edge (char a,char b, float die) {
			 V1 = a;
			 V2 = b;
			 dieTime = die;
		}
		 
		 public char getVert1(){
			 return V1;
		 }
		 
		 public char getVert2(){
			 return V2;
		 }
		 
		 public int getProbagation(){
			 return probagation;
		 }
		 
	
	}
	 



	 public class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge x, Edge y) {
		        if (x.dieTime < y.dieTime)
		        {
		            return -1;
		        }
		        if (x.dieTime > y.dieTime)
		        {
		            return 1;
		        }
		        return 0;
		}

	 }

	
	

}
