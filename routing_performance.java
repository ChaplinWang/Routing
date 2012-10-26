
import java.io.*;
import java.util.*;



public class routing_performance {
	
	
	public static void main(String[] args)throws Exception {
	
		WeightedGraph g = new WeightedGraph();
		g.CMD = args[0];
		System.out.println("---------" + g.CMD + "------------");
		topologyReader(args[1], g);
		ProcessWorkload(args[2], g);

	}
	
    static String topologyReader(String filename, WeightedGraph g){
		String t = null;
		 try{
			  FileInputStream tempfile = new FileInputStream(filename);
			  DataInputStream tempdata = new DataInputStream(tempfile);
			  BufferedReader buffer = new BufferedReader(new InputStreamReader(tempdata));
			  
			  while ((t = buffer.readLine()) != null){
				  	char v1,v2;
				  	int p,m;

				  	StringTokenizer OrderBreaker = new StringTokenizer(t);
	    			String OrderReader = OrderBreaker.nextToken(" ");
	    			v1 = OrderReader.charAt(0);
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			v2 = OrderReader.charAt(0);
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			p = Integer.parseInt(OrderReader);
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			m = Integer.parseInt(OrderReader);
	    			
	    			g.InsertEdge(v1, v2, p, m);
			  }
			  tempdata.close();
			  
		 }catch (Exception error){
			 System.out.println("WRONNG####!!!! error" + error);
		 }
		return t;
    }
    
    static void ProcessWorkload(String filename, WeightedGraph g){
    	String t = null;
    	int blocked = 0;
        float time = 0;
        int passed = 0;
        int total = 0;
        int totalHoc = 0;
        int totalprob = 0;
		 try{
			  FileInputStream tempfile = new FileInputStream(filename);
			  DataInputStream tempdata = new DataInputStream(tempfile);
			  BufferedReader buffer = new BufferedReader(new InputStreamReader(tempdata));
			  
			  while ((t = buffer.readLine()) != null){
				  	char v1,v2;
				  	float start,duration;

				  	StringTokenizer OrderBreaker = new StringTokenizer(t);
	    			String OrderReader = OrderBreaker.nextToken(" ");
	    			start = Float.valueOf(OrderReader.trim()).floatValue();
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			v1 = OrderReader.charAt(0);
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			v2 = OrderReader.charAt(0);
	    			OrderReader = OrderBreaker.nextToken(" ");
	    			duration = Float.valueOf(OrderReader.trim()).floatValue();
	    		   
	    			char [] route = tempDijstra.dijkstra(g, v1, v2);
	    			float dietime = start + duration;
	    			
	    			//do update & check maxlink
	    			int i = 0;
	    			total++;
	    			time = start;

	    			while(!g.dieQ.isEmpty() && (g.dieQ.element().dieTime <= start)){
	    				char a = g.dieQ.element().getVert1();
	    				char b = g.dieQ.element().getVert2();

	    				g.graph[(int)a - 65][(int)b - 65].runTimeLink--;

							
	    				g.dieQ.poll();

	    			}
	    			int subprob = 0;
	    			for(i = 0;route[i] != v2;i++){

	    				if (g.ProcessLink(route[i],route[i+1])){

	    					subprob = subprob + g.graph[(int)route[i]-65][(int)route[i+1]-65].getProbagation();
	    					

	    				}else{
	    					totalHoc = totalHoc - i;
	    					blocked++;
	    					break;
	    				}
	    			}
	    			
	    			if(route[i] == v2){
						for(i = 0;route[i] != v2;i++){
							g.graph[(int)route[i] - 65][(int)route[i+1] - 65].runTimeLink++;
							g.dieQ.add(g.inputLink(route[i], route[i+1], dietime));
							totalHoc++;
						}
	    					passed++;
	    					totalprob = totalprob + subprob;
	    			}
	    			
	    			

			  }

			  tempdata.close();
			  System.out.println("total number of virtual circuit requests: " + total);			  
			  System.out.println("number of successfully routed requests:" + passed);
			  System.out.println("percentage of routed request: " + (float)passed/total*100);
			  System.out.println("number of blocked requests: "+ blocked);
			  System.out.println("percentage of blocked requests: "+ (float)blocked/total*100);
			  System.out.println("average number of hops per circuit: " + (float)totalHoc/passed);
			  System.out.println("average cumulative propagation delay per circuit: " + (float)totalprob/passed);

			  
			  
			  
			  
			  
		 }catch (Exception error){
			 System.out.println("WRONNG####!!!! error" + error);
		 }
    	

    	
    }
    
}
