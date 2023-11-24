import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class KNN {
    private int k;
    private PointSet pointSet;
    private int versionNum;

    public KNN(PointSet pointSet, int versionNum) {
        this.pointSet = pointSet;
        this.versionNum = versionNum;
        this.k = 0;
    }

    public void setK(int k) {
        this.k = k;
    }

    public ArrayList<LabelledPoint> findNN(LabelledPoint qryPoint) {
        ArrayList<LabelledPoint> nearestNeighbours = new ArrayList<LabelledPoint>();
        for (LabelledPoint q : pointSet.getPointsList()) {
                    q.setKey(qryPoint.distanceTo(q));
                }
        switch (versionNum) {
            case 1:
                    
                PriorityQueue1 pQ1 = new PriorityQueue1(k, pointSet.getPointsList());
                while (!pQ1.isEmpty()) {
                    nearestNeighbours.add(pQ1.poll());
                }
                break;
            case 2:
                PriorityQueue2 pQ2 = new PriorityQueue2(pointSet.getPointsList(), k);
                while (!pQ2.isEmpty()) {
                    nearestNeighbours.add(pQ2.poll());
                }
                break;
            case 3:
                
                PriorityQueue3 pQ3 = new PriorityQueue3(pointSet.getPointsList(), k);
                while (!pQ3.isEmpty()) {
                    nearestNeighbours.add(pQ3.poll());
                }
        }
        return nearestNeighbours;
    }

    public static void main(String args[]) {

        // int versionNum = Integer.parseInt(args[0]);
        // int k = Integer.parseInt(args[1]);
        // String dataset = args[2];
        // String queryFile = args[3];

        int versionNum = Integer.parseInt(args[0]);
        int k= Integer.parseInt(args[1]);
        String dataset= args[2];
        String queryFile = args[3];

        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(queryFile));
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(dataset));

        System.out.println("Query set: " + queryPts.getPointsList().size());
        System.out.println("Point set: " + pointSet.getPointsList().size());

        long startTime = System.currentTimeMillis();

        KNN knn = new KNN(pointSet, versionNum);
        knn.setK(k);

        ArrayList<LabelledPoint> nearestNeighbours = new ArrayList<LabelledPoint>();
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("knn_" + versionNum + "_" + k + "_" + queryPts.getPointsList().size()
                            + "_" + pointSet.getPointsList().size() + ".txt"));
            for (LabelledPoint p : queryPts.getPointsList()) {
                nearestNeighbours = knn.findNN(p);
                writer.write(p.getLabel() + ": ");
                for (LabelledPoint neighbour : nearestNeighbours) {
                    writer.write(neighbour.getLabel() + ", ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }

}
