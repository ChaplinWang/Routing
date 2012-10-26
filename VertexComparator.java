import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex>
	{
	    @Override
	    public int compare(Vertex x, Vertex y)
	    {
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        if (x.weight < y.weight)
	        {
	            return -1;
	        }
	        if (x.weight > y.weight)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}

