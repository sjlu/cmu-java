import java.util.LinkedList;
import java.util.ListIterator;

public class AdjList
{
        private LinkedList list[];
        private int num_vertices;

        /* 
         * Constructor: Allocates right amount of space 
         */
        public AdjList(int num_vertices)
        {
                this.num_vertices = num_vertices;
                list = new LinkedList[num_vertices];

                /*
                 * initialize all of the linked lists in the array
                 */
                for (int index = 0; index < num_vertices; index++)
                {
                        list[index] = new LinkedList();
                }
        }

        /*
         * add an edge to the graph
         */
        public void addEdge(Edge new_edge)
        {
                list[new_edge.getStartVertex()].addFirst(new_edge);
        }

        /*
         * remove a directed edge from the graph
         */
        public void removeEdge(Edge remove_me)
        {
                list[remove_me.getStartVertex()].remove(remove_me);
        }

        /*
         * Provide an edge (get me) with a start and finish, get
         * back an edge with the start, finish, and cost
         */
	public Edge getEdge (Edge get_me)
	{
		int index = list[get_me.getStartVertex()].indexOf(get_me);

		return (Edge) list[get_me.getStartVertex()].get(index);
	}

        /*
         * Returns an iterator that contains the Edges leading to
         * adjacent nodes
         */
	public ListIterator getAdjacencies (int vertex)
	{
		return list[vertex].listIterator();
	}

        /*
         * Feel free to make this prettier, if you'd like
         */
        public String toString()
	{
	  String temp = "";

  	  for (int index=0; index < num_vertices; index++)
	  {
	    temp = temp + index + ": " + list[index].toString() + "\n";  
	  }

	  return temp;
	}
}
