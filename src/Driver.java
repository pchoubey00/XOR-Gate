public class Driver {
    static Layer[] layers; 
    static TrainingData[] inputs; 
    public static void main(String[] args) {
    	Neuron.setRangeWeight(-1,1);
    	layers = new Layer[3];
    	layers[0] = null; 
    	layers[1] = new Layer(2,6); 
    	layers[2] = new Layer(6,1); 
    	 CreateTrainingData();
    	 
        System.out.println("Output before training for XOR gates:");
        for(int i = 0; i < inputs.length; i++) {
            forwardpropogation(inputs[i].data);
            System.out.println(layers[2].neuronarray[0].value);
        }
        
        trainingdata(1000000, 0.07f);      
        System.out.println("Output after training for XOR gates:");
        for(int i = 0; i < inputs.length; i++) {
            forwardpropogation(inputs[i].data);
            System.out.println(Math.round(layers[2].neuronarray[0].value));
        }
    }
    public static void forwardpropogation(float[] inputs) {
    	layers[0] = new Layer(inputs);
        for(int i = 1; i < layers.length; i++) {
        	for(int j = 0; j < layers[i].neuronarray.length; j++) {
        		float sum = 0;
        		for(int k = 0; k < layers[i-1].neuronarray.length; k++) {
        			sum += layers[i-1].neuronarray[k].value*layers[i].neuronarray[j].weights[k];
        		}
       
        		layers[i].neuronarray[j].value = StatUtil.Sigmoid(sum);
        	}
        } 	
    }
    
    public static void backwardpropagation(float learning_rate,TrainingData trainingData) {
    	int number_layers = layers.length;
    	int out_index = number_layers-1;  
    	for(int i = 0; i < layers[out_index].neuronarray.length; i++) {
    		
    		float output = layers[out_index].neuronarray[i].value;
    		float target = trainingData.Target[i];
    		float derivative = output-target;
    		float delta = derivative*(output*(1-output));
    		layers[out_index].neuronarray[i].gradient = delta;
    		for(int j = 0; j < layers[out_index].neuronarray[i].weights.length;j++) { 
    			float previous_output = layers[out_index-1].neuronarray[j].value;
    			float error = delta*previous_output;
    			layers[out_index].neuronarray[i].cache_weights[j] = layers[out_index].neuronarray[i].weights[j] - learning_rate*error;
    		}
    	}
    	
    	//Update all the subsequent hidden layers
    	for(int i = out_index-1; i > 0; i--) {
    		// For all neurons in that layers
    		for(int j = 0; j < layers[i].neuronarray.length; j++) {
    			float output = layers[i].neuronarray[j].value;
    			float gradient_sum = sumGradient(j,i+1);
    			float delta = (gradient_sum)*(output*(1-output));
    			layers[i].neuronarray[j].gradient = delta;
    			// And for all their weights
    			for(int k = 0; k < layers[i].neuronarray[j].weights.length; k++) {
    				float previous_output = layers[i-1].neuronarray[k].value;
    				float error = delta*previous_output;
    				layers[i].neuronarray[j].cache_weights[k] = layers[i].neuronarray[j].weights[k] - learning_rate*error;
    			}
    		}
    	}
    	
    
    	for(int i = 0; i< layers.length;i++) {
    		for(int j = 0; j < layers[i].neuronarray.length;j++) {
    			layers[i].neuronarray[j].update_weight();
    		}
    	}
    
    }
    public static float sumGradient(int n_index,int l_index) {
    	float gradient_sum = 0;
    	Layer current_layer = layers[l_index];
    	for(int i = 0; i < current_layer.neuronarray.length; i++) {
    		Neuron current_neuron = current_layer.neuronarray[i];
    		gradient_sum += current_neuron.weights[n_index]*current_neuron.gradient;
    	}
    	return gradient_sum;
    }
    public static void CreateTrainingData() {
        float[] input1 = new float[] {0, 0}; 
        float[] input2 = new float[] {0, 1}; 
        float[] input3 = new float[] {1, 0}; 
        float[] input4 = new float[] {1, 1}; 
       
        float[] Target1 = new float[] {0};
        float[] Target2 = new float[] {1};
        float[] Target3 = new float[] {1};
        float[] Target4 = new float[] {0};
        
        
        inputs = new TrainingData[4];
        inputs[0] = new TrainingData(input1, Target1);
        inputs[1] = new TrainingData(input2, Target2);
        inputs[2] = new TrainingData(input3, Target3);
        inputs[3] = new TrainingData(input4, Target4);        
    }

    public static void trainingdata(int training_iterations,float learning_rate) {
    	for(int i = 0; i < training_iterations; i++) {
    		for(int j = 0; j < inputs.length; j++) {
    			forwardpropogation(inputs[j].data);
    			backwardpropagation(learning_rate,inputs[j]);
    		}
    	}
    }
}
