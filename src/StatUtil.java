public class StatUtil {
	
	
    public static float RandomFloat(float minimum, float maximum) {
        float a = (float) Math.random();
        float num = minimum + (float) Math.random() * (maximum - minimum);
        if(a < 0.5)
            return num;
        else
            return -num;
    }
    
   
    public static float Sigmoid(float x) {
        return (float) (1/(1+Math.pow(Math.E, -x)));
    }
    
    
    public static float SigmoidDerivative(float x) {
        return Sigmoid(x)*(1-Sigmoid(x));
    }
    
   
    public static float squaredError(float output,float target) {
    	return (float) (0.5*Math.pow(2,(target-output)));
    }
    
  
}