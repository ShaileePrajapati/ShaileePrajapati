package tides;

import java.util.*;

/**
 * This class contains methods that provide information about select terrains 
 * using 2D arrays. Uses floodfill to flood given maps and uses that 
 * information to understand the potential impacts. 
 * Instance Variables:
 *  - a double array for all the heights for each cell
 *  - a GridLocation array for the sources of water on empty terrain 
 * 
 * @author Original Creator Keith Scharz (NIFTY STANFORD) 
 * @author Vian Miranda (Rutgers University)
 */
public class RisingTides {

    // Instance variables
    private double[][] terrain;     // an array for all the heights for each cell
    private GridLocation[] sources; // an array for the sources of water on empty terrain 
    double lowest= Integer.MAX_VALUE;
    double highest= Integer.MIN_VALUE;;
    double currentnum;

    /**
     * DO NOT EDIT!
     * Constructor for RisingTides.
     * @param terrain passes in the selected terrain 
     */
    public RisingTides(Terrain terrain) {
        this.terrain = terrain.heights;
        this.sources = terrain.sources;
    }

    /**
     * Find the lowest and highest point of the terrain and output it.
     * 
     * @return double[][], with index 0 and index 1 being the lowest and 
     * highest points of the terrain, respectively
     */
    public double[] elevationExtrema() {
        

        for(int i=0; i<terrain.length; i++){
            for(int j=0; j<terrain[0].length; j++){
                currentnum = terrain[i][j];
                if (currentnum<lowest){
                    lowest=currentnum;
                }
                if(currentnum>highest){
                    highest=currentnum;
                }
            }
        }
    
        double[] lowhigh = new double[2]; // Create an empty array with a size of 2
        lowhigh[0] = lowest;
        lowhigh[1] = highest;
        return lowhigh;
    }



        /* WRITE YOUR CODE BELOW */
        //terrain[i][j]
        //row length terrain.length
        //column terrain[i].length
        //2d array nested for loop
        //double lowest, highest
        //double extrema[2] 1D array
        //extrema[0]=lowest and extrema[1]=highest
        //how to find the lowest and highest values in a matrix (2d array)
        
    


        /*return null; // substitute this line. It is provided so that the code compiles.
    }

    /**
     * Implement the floodfill algorithm using the provided terrain and sources.
     * 
     * All water originates from the source GridLocation. If the height of the 
     * water is greater than that of the neighboring terrain, flood the cells. 
     * Repeat iteratively till the neighboring terrain is higher than the water 
     * height.
     * 
     * 
     * @param height of the water
     * @return boolean[][], where flooded cells are true, otherwise false
     */
    public boolean[][] floodedRegionsIn(double height) {
        
        /* WRITE YOUR CODE BELOW */
        int numRows= terrain.length;
        int numCols=terrain[0].length;
        boolean[][] resultingarray = new boolean[numRows][numCols];


        ArrayList<GridLocation>locationList=new ArrayList<>();
        for(int i=0;i<sources.length;i++){
            locationList.add(sources[i]);
            int numRows1=sources[i].row;
            int numCols1=sources[i].col;
            resultingarray[numRows1][numCols1]=true;

    }
    while(!locationList.isEmpty()){
        GridLocation a = locationList.remove(0);
        //boolean spot=resultingarray[a.row][a.col];

       
        
        //For up
        
        if(a.row-1>=0 && terrain[a.row-1][a.col]<=height && !resultingarray[a.row-1][a.col]){
            locationList.add (new GridLocation(a.row-1,a.col));
            resultingarray[a.row-1][a.col]=true;
        }
        

        //For down
        
        if(a.row+1<numRows && terrain[a.row+1][a.col]<=height && !resultingarray[a.row+1][a.col]){
            locationList.add (new GridLocation(a.row+1,a.col));
            resultingarray[a.row+1][a.col]=true;
        }
        
        

        //For left
        
        if(a.col-1>=0 && terrain[a.row][a.col-1]<=height && !resultingarray[a.row][a.col-1]){
            locationList.add (new GridLocation(a.row,a.col-1));
            resultingarray[a.row][a.col-1]=true;
        }
        
        //For right
        
        if(a.col+1<numCols && terrain[a.row][a.col+1]<=height && !resultingarray[a.row][a.col+1]){
            locationList.add (new GridLocation(a.row,a.col+1));
            resultingarray[a.row][a.col+1]=true;
        }
        


            
        }
        return resultingarray; 
       
        }


    
    
        // substitute this line. It is provided so that the code compiles.


    /**
     * Checks if a given cell is flooded at a certain water height.
     * 
     * @param height of the water
     * @param cell location 
     * @return boolean, true if cell is flooded, otherwise false
     */
    public boolean isFlooded(double height, GridLocation cell) {
        
        /* WRITE YOUR CODE BELOW */
        

        boolean[][] yFlooded = floodedRegionsIn(height);
        //int x = cell.row;
        //int y = cell.col;
   
        if(yFlooded[cell.row][cell.col] == true){
               return true;
           }
           else{     
           return false; 
           } //substitute this line. It is provided so that the code compiles.
       }
   
        //return false; //substitute this line. It is provided so that the code compiles.
    
        // substitute this line. It is provided so that the code compiles.
    
    
                                                                                                                                   /**
     * Given the water height and a GridLocation find the difference between 
     * the chosen cells height and the water height.
     * 
     * If the return value is negative, the Driver will display "meters below"
     * If the return value is positive, the Driver will display "meters above"
     * The value displayed will be positive.
     * 
     * @param height of the water
     * @param cell location
     * @return double, representing how high/deep a cell is above/below water
     */
    public double heightAboveWater(double height, GridLocation cell) {
        
            double ch=terrain[cell.row][cell.col];
            double d =ch-height;
             
    
    
            return d;
        /* WRITE YOUR CODE BELOW */
       // return -1; // substitute this line. It is provided so that the code compiles.
    }

    /**
     * Total land available (not underwater) given a certain water height.
     * 
     * @param height of the water
     * @return int, representing every cell above water
     */
    public int totalVisibleLand(double height) {
        
        /* WRITE YOUR CODE BELOW */
        boolean[][] wasflooded = floodedRegionsIn(height);
        int sum= 0;
        for(int i=0;i < wasflooded.length;i++){
            for(int j=0;j < wasflooded[0].length;j++){
                if (wasflooded[i][j]==false){
                    sum++;
                }
            }
        }

        return sum; // substitute this line. It is provided so that the code compiles.
    } 


    /**
     * Given 2 heights, find the difference in land available at each height. 
     * 
     * If the return value is negative, the Driver will display "Will gain"
     * If the return value is positive, the Driver will display "Will lose"
     * The value displayed will be positive.
     * 
     * @param height of the water
     * @param newHeight the future height of the water
     * @return int, representing the amount of land lost or gained
     */
    public int landLost(double height, double newHeight) {
        
        /* WRITE YOUR CODE BELOW */
        int h1 = totalVisibleLand(height);
        int h2 = totalVisibleLand(newHeight);

        return h1-h2; // substitute this line. It is provided so that the code compiles.
    }

    /**
     * Count the total number of islands on the flooded terrain.
     * 
     * Parts of the terrain are considered "islands" if they are completely 
     * surround by water in all 8-directions. Should there be a direction (ie. 
     * left corner) where a certain piece of land is connected to another 
     * landmass, this should be considered as one island. A better example 
     * would be if there were two landmasses connected by one cell. Although 
     * seemingly two islands, after further inspection it should be realized 
     * this is one single island. Only if this connection were to be removed 
     * (height of water increased) should these two landmasses be considered 
     * two separate islands.
     * 
     * @param height of the water
     * @return int, representing the total number of islands
     */
    public int numOfIslands(double height) {
        WeightedQuickUnionUF unfi=new WeightedQuickUnionUF(terrain.length, terrain[0].length);
        boolean[][] f= floodedRegionsIn(height);

        for(int r1 =0;r1<terrain.length;r1++){
            for(int c1=0;c1<terrain[0].length;c1++){
                if(!f[r1][c1]){
                    int[] r= {-1,-1,-1,0,0,1,1,1};
                    int[] c= {-1,0,1,-1,1,-1,0,1};


                    for(int i=0;i<8;i++){
                        int r2= r1 + r[i];
                        int c2= c1 + c[i];

                        if(r2<terrain.length && c2<terrain[0].length && c2>=0 && r2>=0 && !f[r2][c2]&& !f[r1][c1]){
                            GridLocation cL = new GridLocation(r1,c1);
                            GridLocation nL = new GridLocation(r2,c2);

                            unfi.union(cL,nL);
                        }
                    }

                        }
                    }
                    
            }
            
            ArrayList<GridLocation>floody=new ArrayList<>();
           for(int row=0; row<terrain.length;row++){
           for(int col=0; col<terrain[0].length;col++){
            GridLocation np = new GridLocation(row, col);

            if(!f[row][col]){
                GridLocation z = unfi.find(np);

                if(!floody.contains(z)){
                    floody.add(z);


                }
                if(floody.contains(z)){
                    continue;
                }
            }


        }
    }
    return floody.size(); 

        }
        

            
       

   

        
        /* WRITE YOUR CODE BELOW */
        //return -1; // substitute this line. It is provided so that the code compiles.
    }





