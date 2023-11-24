/* ---------------------------------------------------------------------------------
A vector of Floats with its associated int label
It includes also a key that can be used to order LabelledPoint instances

(c) Robert Laganiere, CSI2510 2023
------------------------------------------------------------------------------------*/

class LabelledPoint {
    private Float[] vector;   // the vector
	private int label;        // the id (should be unique)
	private double key;       // the key used for ordering

    public LabelledPoint(Float[] vector, int label) {
        this.vector = vector;
		this.label= label;
		this.key= 0.0;
    }
	
	// gets the label
	public int getLabel() {
	
	    return label;
	}
	
	// gets the vector (an array of floats)
	public Float[] getVector(){
	
	    return vector;
	}
	
	// gets the key value
	public double getKey() {
		return key;
	}
	
	// set the key value
	public void setKey(double k) {
		key= k;
	}

    // gets the length (dimension) of the vector	
	public int getLength() {
	    return vector.length;	
	}

    // computes the Euclidean distance between two vectors
    public double distanceTo(LabelledPoint other) {
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            float diff = vector[i] - other.vector[i];
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i=0; i<4; i++) {
            sb.append(vector[i]).append(", ");
        }
        sb.append("..., ");
        for (int i=vector.length-2; i<vector.length; i++) {
            sb.append(vector[i]).append(", ");
        }
        sb.setLength(sb.length() - 2); 
        sb.append(")");
        return sb.toString();
    }
}
