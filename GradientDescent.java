

package code;

/**
 * @author abhishek.d
 *
 */
public class GradientDescent {

private double theta0;
private double theta1;

public GradientDescent(double theta0, double theta1) {
    this.theta0 = theta0;
    this.theta1 = theta1;
}

/**
 * @return theta0 (intercept)
 */
public double getTheta0() {
    return theta0;
}

/**
 * @return theta1 (slope)
 */
public double getTheta1() {
    return theta1;
}

/**
 * @param input data point 
 * @return prediction value from hypothesis
 * 
 */
public double getHypothesisResult(double x) {
    return theta0 + theta1*x;
}

/**
 * 
 * @param trainingData : 2-D array of training data set
 * @return cost corresponding to prediction and actual value
 */
private double getCost(double[][] trainingData) {
	
	double cost = 0.0;
	
	 for (int i = 0; i < trainingData.length; i++) 		 	
	        cost += Math.pow((getHypothesisResult(trainingData[i][0]) - trainingData[i][1]),2.0) ;
	    
	return cost / (2 * trainingData.length);
	
}

private double getError(double[] dataRow) {
	return getHypothesisResult(dataRow[0]) - dataRow[1];
}

private double getCorrectionParameter(double[][] trainingData, boolean isConstantTerm) {
	
    double result = 0;
    
    for (int i = 0; i < trainingData.length; i++) {
        //result += (getHypothesisResult(trainingData[i][0]) - trainingData[i][1]) *  trainingData[i][0];
    	if(isConstantTerm)
    		result += (getError(trainingData[i]) );
    	else 
    		result += (getError(trainingData[i]) *  trainingData[i][0]);
    }
    
    return result;
}

public void train(double learningRate, double[][] trainingData) {
    int iteration = 0;
    
    double cost = Double.MAX_VALUE;
    final double TOLERANCE = 0.00001;

    
    do{
        System.out.println("Iteration : " + iteration);
        iteration++;
        
        //System.out.println("SUBS: " + (learningRate*((double) 1/trainingData.length))*getResult(trainingData, false));
        double temp0 = theta0 - ( ( learningRate / trainingData.length ) * ( getCorrectionParameter(trainingData, true) ) );
        double temp1 = theta1 - ( ( learningRate / trainingData.length ) * ( getCorrectionParameter(trainingData, false) ) );
        
        theta0 = temp0;
        theta1 = temp1;
        
        cost = getCost(trainingData);
        System.out.print("New cost is " + cost);
        
    } while(cost > TOLERANCE );
    
    
}



public static void main(String[] args) {
	
		double[][] prices = {{1, 100},{2, 200},{3, 300},{4, 400},{5, 500},{6, 600}};
	
		double learningRate = 0.001;
		
		GradientDescent objGd = new GradientDescent(0,0);
		
	    objGd.train(learningRate, prices);
	    
	    System.out.print("Result is : " +  objGd.getHypothesisResult(7.0));
	    
	    if(objGd.getTheta1() > 0) {
	    	System.out.print("Upwards");
	    }
	    else {
	    	System.out.print("Downwards");
	    }

}

}