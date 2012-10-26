
public class Vertex {
	float weight;
	int vert;
	
	public Vertex(float w, int v) {
		weight = w;
		vert = v;		
	}
	
	public int getV(){
		return vert;
	}
	
	public float getWeight(){
		return weight;
	}
}
