/* ---------------------------------------------------------------------------------
The PointSet class that contains an ArraList of LabelledPoint instances.
It also reads fvecs files from corpus-texmex.irisa.fr


(c) Robert Laganiere, CSI2510 2023
------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.ArrayList;

class PointSet {
    private ArrayList<LabelledPoint> pointsList = new ArrayList<>();
	
	// constructs an empty point set
    public PointSet(){  
        
    }

    // constructs a point set for the given ArrayList instance
    public PointSet(ArrayList<LabelledPoint> pointsList){
       this.pointsList = pointsList;
    }

    // read from fvecs file
    public static ArrayList<LabelledPoint> read_ANN_SIFT(String filename) {
        ArrayList<LabelledPoint> pointSet = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);

            int d = Integer.reverseBytes(dis.readInt());
            int vecSizeOf = 1 * 4 + d * 4;
            long fileLength = new File(filename).length();
            int bMax = (int) (fileLength / vecSizeOf);
            int a = 1;
            int b = bMax;

            if( a >= 1 && b>bMax){
                b =bMax;
            }

            if (b == 0 || b < a) {
                dis.close();
				throw new IOException("Error! Invalid file format...");
            }

            int n = b - a + 1;
            
            dis.skipBytes((a - 1) * vecSizeOf);
            for (int i = 0; i < n; i++) {
                Float[] vector = new Float[d];
                for (int j = 0; j < d; j++) {
                    int floatAsInt = Integer.reverseBytes(dis.readInt());
                    vector[j] = Float.intBitsToFloat(floatAsInt);
                }
                dis.skipBytes(4);
                pointSet.add(new LabelledPoint(vector,i));
            }

            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pointSet;
    }

    // get the ArrayList instance containing the point set
    public ArrayList<LabelledPoint> getPointsList() {
        return pointsList;
    }

    // set the point set
    public void setPointsList (ArrayList<LabelledPoint> pointsList){
        this.pointsList = pointsList;
    }

    public static void main(String[] args) {
		
		// read query points
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT("siftsmall_query.fvecs"));
		// read point set
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT("siftsmall_base.fvecs"));
		
        System.out.println("Query set: "+queryPts.getPointsList().size());
        System.out.println("Point set: "+pointSet.getPointsList().size());
	
		long startTime = System.currentTimeMillis(); // start timer
		
		// this is just a test loop to display the vectors in the query set
		for (int i=0; i<queryPts.getPointsList().size(); i++) {
			
		    // not a good idea to print to console while you are timing execution time!!
			System.out.println(i + " : " + queryPts.getPointsList().get(i)); // remove this line
		}
		
		long endTime = System.currentTimeMillis(); // end timer
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
	}
}

