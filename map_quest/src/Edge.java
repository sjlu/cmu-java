public class Edge
{
  /*
   * This class models an edge from vertex X to vertex Y -- pretty
   * straightforward. 
   *
   * It is a little wasteful to keep the starting vertex, since the 
   * adjacency list will do this for us -- but it makes the code
   * neater in other places (makes the Edge independent of the Adj. List
   */
        private int vertex_from;
        private int vertex_to;
        private int cost;

        public Edge(int vertex_from, int vertex_to, int cost)
        {
                this.vertex_from = vertex_from;
                this.vertex_to = vertex_to;
                this.cost = cost;
        }

        public Edge(int vertex_from, int vertex_to)
        {
                this.vertex_from = vertex_from;
                this.vertex_to = vertex_to;
                this.cost = 0;
        }

	int getStartVertex()
	{
		return vertex_from;
	}

	public int getDestinationVertex()
	{
		return vertex_to;
	}

	public int getCost()
	{
		return cost;
	}

        /*
         * considers two edges equal if they go to the same
         * vertex, regardless of their weight
         */
        public boolean equals(Object other_edge)
        {
                return ( ((((Edge)other_edge).vertex_from == 
			   ((Edge)this).vertex_from)) && 
			 ((((Edge)other_edge).vertex_to == 
			   ((Edge)this).vertex_to)) );
        }
		
        /*
         * Feel free to make this prettier, if you'd like.
         */
	public String toString()
	{
	  return "{" + vertex_from + "," + vertex_to + "," + cost + "}";
	}
}
