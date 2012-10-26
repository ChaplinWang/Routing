
import java.util.*;


public class tempDijstra {
	static int st[];
	static float dist[];
		
	public static char [] dijkstra (WeightedGraph G, char v1, char v2) {

		st = new int[G.size()+1];
		dist = new float[G.size()+1];
			
		dijkstra(G, v1);

		int i;
	
		//calculate path
		
		Stack<Character> path = new Stack<Character>();
		
		int tempV = (int)(v2-65);
		
		while(tempV >= 0){
			path.push((char)(tempV+65));
			tempV = st[tempV];
		}
		char array[] = new char [30];
		i = 0;

	//		System.out.println(" ");
		while(!path.isEmpty()){
			array[i] = path.pop();
	//		System.out.print(array[i] + " ");
			i++;
		}
	//	System.out.println(" ");

		
		return array;
	}
	
	public static void dijkstra(WeightedGraph g, char s){ 
	    int v, t;
	    Comparator<Vertex> comparator = new VertexComparator();
	    PriorityQueue<Vertex> priq = new PriorityQueue<Vertex> (100000,comparator);

	    for (v=0; v< g.size(); v++){                           // for all vertices
	        st[v] = -1;                                        // Initialize predecessors
	        dist[v] = Integer.MAX_VALUE;                       // Initialize distances
	        Vertex vt = new Vertex(dist[v], v);                 // create a priq node
	        priq.add(vt);                                    	// put priq node into the priq
	    } 
	                                            				// set start vertex dist to 0

	    
	    v = (int)(s-65);
	    priq.remove(new Vertex(dist[v],v));
	    dist[v] = 0;
	    priq.add(new Vertex(dist[v],v));
	    
	    while (!priq.isEmpty()){                             		  
	    	
	       v = priq.poll().getV();                         		 	 // get the minimum from the priq
	       if (dist[v] < Integer.MAX_VALUE){                         // if there is a reachable vertex
	          for (t = 0; t < g.size(); t++){                  		 // for every vertex t 
	             if (g.IsAdjacent(v, t)){           				
	     	
                	if(!g.CMD.equals("LLP") && dist[t] == g.Update(dist[v], v, t)){       //some randomness in the network
						Random rd = new Random();
					    int randomNum = rd.nextInt(2) + 1;
										//System.out.println(t);	
						if(randomNum == 1){
							//priq.remove(new Vertex(dist[t],t));
							//dist[t] = g.Update(dist[v], v, t);  		
							//priq.add(new Vertex(dist[t],t));
						    st[t] = v;   
						}
					}

            	 
	                else if (dist[t] > g.Update(dist[v], v, t)){		  // if t has priority > v + v->t  		
				
	            	    priq.remove(new Vertex(dist[t],t));
	            	    dist[t] = g.Update(dist[v], v, t);  		
	            	    priq.add(new Vertex(dist[t],t));
	                    st[t] = v;                          		 // set the predecessor
	                }

	             }
	          }
	       }
	    }
	
	
	}
	

	
		
}

