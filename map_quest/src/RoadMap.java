import java.io.*;
import java.util.*;

/*
 * This class models a collection of cities and roads. 
 * It a graphs to keep the distance of the roads in miles as the 
 * cost of the edges. 
 */

class RoadMap
{

  private class DijTableEntry implements Comparable
  {
    /*
     * This class should contain a vertex's entry in 
     * the table used by Dijkstra's algorithm. Basically,
     * it should be a row of this table (path, distance, known).
     * It should also contain conveniences such as toString()
     * You shoudl write this...
     */
    private int count = INFINITY;
    private String name, via;
    private boolean known;

    public DijTableEntry(boolean known, String name) {
      this.known = known;

      this.name = name;
      this.via = name;
    }

    public int getCount() {
      return count;
    }

    public String getName() {
      return name;
    }

    public String getVia() {
      return via;
    }

    public boolean isKnown() {
      return known;
    }
    
    public void setCount(int count) {
      this.count = count;
    }
    
    public void setVia(String via) {
      this.via = via;
    }

    public void mark() {
      this.known = true;
    }

    public int compareTo(Object other) {
      DijTableEntry entry = (DijTableEntry)other;

      return count - entry.count;
    }
    public String toString() {
      return name + "[" + count + "]";
    }
  }

  private final int INFINITY = 999999999;
  private DataReader mapFile; // File abstraction for input file
  private String cityName[]; // City number to name mapping table
  private AdjList distanceMap; // Graph representing map w/cost as distance

  /*
   * Helper method to return city's number, given its name
   * The reverse mapping is done by indexing into the table
   * 
   */
  private int getCityNumber (String givenCityName)
  {
    for (int index=0; index < cityName.length; index++)
    {
      if (cityName[index].equals(givenCityName))
        return index;
    }

    return -1;
  }

  /*
   * This creates the distanceMap from a properly
   * formatted input file
   */
  public RoadMap (String mapFileName)
  {
    try 
    {
      mapFile = new DataReader (mapFileName);
      int numCities = mapFile.readInt();

      distanceMap = new AdjList(numCities);
      cityName = new String [numCities];

      // Read city names into name:number mapping
      for (int index=0; index < numCities; index++)
        cityName[index] = mapFile.readWord();
    }
    catch (Exception e)
    {
      // An error -- not much to do.
      System.out.println (e);
      return;
    }

    try
    {
      while (true)
      {
        String startCity = mapFile.readWord();
        String destinationCity = mapFile.readWord();

        int distance = mapFile.readInt();

        distanceMap.addEdge(new Edge( getCityNumber(startCity), 
                                              getCityNumber(destinationCity), 
                                              distance));
      }
    }
    catch (EOFException e)
    { } // Normal exit -- no more roads to map

    catch (Exception e)
    {
      // A real error -- not much to do.
      System.out.println (e);
      System.exit (-1);
    }
  }

  public DijTableEntry[] dijkstra (String start)
  {
    /*
     * You should write this.
     * It should implement Dijkstra's algorithm to determine the 
     * path from start to each other vertex. This path should
     * be stored (with distance) in a table, as we did in class.
     * This table is represented as an array of DijTableEntry 
     * with one entry (row) per vertex.
     * 
     * "start" is the name of the start city.
     */

    //Instantiates the DijTable
    DijTableEntry[] dijTable = new DijTableEntry[cityName.length];
    for(int i = 0; i < dijTable.length; i++) dijTable[i] = new DijTableEntry(false, cityName[i]);

    //Sets up starting point within DijTable
    int startIndex = getCityNumber(start);
    dijTable[startIndex].setCount(0);

    //Creates PriorityQueue through comparing counts.
    Queue queue = new PriorityQueue();
    for(int i = 0; i < dijTable.length; i++) queue.add(dijTable[i]);

    while(queue.size() > 0) {
      //Get minimum count DijTableEntry
      DijTableEntry min = (DijTableEntry)queue.poll();

      //Get all adjacencies
      ListIterator iter = distanceMap.getAdjacencies(getCityNumber(min.getName()));

      //Iterate through adjacencies
      while(iter.hasNext()) {
        Edge edge = (Edge)iter.next();
        int finishIndex = edge.getDestinationVertex();

        //Change is shorter path is already found
        int altCost = min.getCount() + edge.getCost();
        if(altCost < dijTable[finishIndex].getCount()) {
          dijTable[finishIndex].setCount(altCost);
          dijTable[finishIndex].setVia(min.getName());
        }
      }
    }

    return dijTable;
  }

  /**
   * Prints the path from the start to the finish specified.
   *
   * @param dijTable The Dijkstra's Algorithm data structure.
   * @param finish The String representation of the finish.
   */
  public void printPath (DijTableEntry []dijTable, String finish)
  {
    boolean notFound = false;
    Stack stack = new Stack();
    int finishIndex = getCityNumber(finish);

    while(true) {
      //If is finish cannot link anywhere else in map
      if(dijTable[finishIndex].getCount() == INFINITY) {
        notFound = true;
        break;
      }

      stack.push(dijTable[finishIndex].toString());
      finishIndex = getCityNumber(dijTable[finishIndex].getVia());

      if(dijTable[finishIndex].getVia().equals(dijTable[finishIndex].getName())) {
        stack.push(dijTable[finishIndex].toString());
        break;
      }
    }

    if(notFound) {
      System.out.println("Path does not exist within map.");
      return;
    }

    while(!stack.empty()) System.out.print((String)stack.pop() + " -> ");
    System.out.println("END");
  }


  public static void main (String []args)
  {
    RoadMap[] maps;
    boolean isRunning = true;
    Scanner in = new Scanner(System.in);

    //Accept RoadMap as CLI argument
    if(args.length == 0) {
      System.out.println("Please enter the name of the graph file as an argument.");
      return;
    }
    else {
      maps = new RoadMap[args.length];
      for(int i = 0; i < args.length; i++) maps[i] = new RoadMap(args[i]);
    }


    //Continue to ask for start/finish while running.
    while(isRunning) {
      try {
        System.out.println("Which map would you like to use?: ");
        int choice = Integer.parseInt(in.nextLine());

        System.out.print("Please enter the city in which to start: ");
        String start = in.nextLine();

        System.out.print("Please enter the city in which to finish: ");
        String finish = in.nextLine();

        DijTableEntry[] results = maps[choice].dijkstra(start);
        maps[choice].printPath(results, finish);

        System.out.print("Would you like to search for more paths? (y/n): ");
        String ans = in.nextLine();

        //Exit if answered no.
        if(ans.equals("N") || ans.equals("n")) isRunning = false;
      }
      catch(Exception e) {
        System.out.println("Error in Input. Please try again.");
        continue;
      }
    }
  }
}

