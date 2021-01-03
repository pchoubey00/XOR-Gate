public class Layer {
	public Neuron[] neuronarray;
	
	public Layer(float input[]) {
		this.neuronarray = new Neuron[input.length];
		for(int i = 0; i < input.length; i++) {
			this.neuronarray[i] = new Neuron(input[i]);
		}
	}
	public Layer(int inputNeurons,int numberofNeurons) {
		this.neuronarray = new Neuron[numberofNeurons];
		
		for(int i = 0; i < numberofNeurons; i++) {
			float[] weights = new float[inputNeurons];
			for(int j = 0; j < inputNeurons; j++) {
				weights[j] = StatUtil.RandomFloat(Neuron.minimumWeight, Neuron.maximumWeight);
			}
			neuronarray[i] = new Neuron(weights,StatUtil.RandomFloat(0, 1));
		}
	}
}
