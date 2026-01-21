package ipbhalle.de.webassemblydemo.dynamicOptimisation;

import java.util.Random;

public class PriceMatrix {

    private float mean;
    private float stdError;
    private float[][] simulationMatrix;

    public PriceMatrix(float expectedValue, float variance) {
        this.mean = expectedValue;
        this.stdError = variance;
    }

    public void createSimulations(int timePoints, int simulations) {
        Random r = new Random();
        simulationMatrix = new float[timePoints][simulations];
        for (int i = 0; i < timePoints; i++) {
            for (int j = 0; j < simulations; j++) {
                simulationMatrix[i][j] = (float) (mean + r.nextGaussian() * stdError);
            }
        }
    }

    public float getPriceAt(int time, int simulation) {
        return simulationMatrix[time][simulation];
    }
}
