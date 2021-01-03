
public class Neuron {
   
	static float minimumWeight;
	static float maximumWeight;
	
	
    float[] weights;
    float[] cache_weights;
    float gradient;
    float bias;
    float value = 0;
    
    public Neuron(float value){
        this.weights = null;
        this.bias = -1;
        this.cache_weights = this.weights;
        this.gradient = -1;
        this.value = value;
    }
    
    public Neuron(float[] weights, float bias){
        this.weights = weights;
        this.bias = bias;
        this.cache_weights = this.weights;
        this.gradient = 0;
    }
    
  
    public static void setRangeWeight(float minimum,float maximum) {
    	minimumWeight = minimum;
    	maximumWeight = maximum;
    }
    
    public void update_weight() {
    	this.weights = this.cache_weights;
    }
    
 
}
