package ipbhalle.de.webassemblydemo;

import ipbhalle.de.webassemblydemo.dynamicOptimisation.PriceMatrix;
import ipbhalle.de.webassemblydemo.dynamicOptimisation.State;
import java.util.Date;

public class DynamicOptimisation {

    private static int timepoints = 100;
    private static int simulations = 1;
    private static State[] states;
    private static PriceMatrix[] prices;
    private static float[][][] stateMatrix;
    private static float[][] optimalValues;
    private static int[][][] optimalPaths;

    public static float start(int timepoints, int simulations) {
        DynamicOptimisation.timepoints = timepoints;
        DynamicOptimisation.simulations = simulations;
        Date d = new Date();

        prices = new PriceMatrix[]{new PriceMatrix(100, 4), new PriceMatrix(50, 2)};
        prices[0].createSimulations(timepoints, simulations);
        prices[1].createSimulations(timepoints, simulations);

        states = new State[]{
            new State(45, .904f),
            new State(37, .75f),
            new State(30, .6f)};

        createStateMatrix();
        calculateOptimalPath();

        long duration = new Date().getTime() - d.getTime();
        System.out.println("Duration " + duration);
        return duration;
    }

    private static void createStateMatrix() {
        stateMatrix = new float[timepoints][simulations][states.length];

        for (int i = 0; i < timepoints; i++) {
            for (int j = 0; j < simulations; j++) {
                for (int k = 0; k < states.length; k++) {
                    stateMatrix[i][j][k] = states[k].caclulateValue(prices[0].getPriceAt(i, j), prices[1].getPriceAt(i, j));
                }
            }
        }
    }

    private static void calculateOptimalPath() {
        optimalValues = new float[simulations][states.length];
        optimalPaths = new int[timepoints][simulations][states.length];

        for (int i = 0; i < simulations; i++) {

            for (int j = 0; j < states.length; j++) {
                optimalValues[i][j] = states[j].caclulateValue(prices[0].getPriceAt(timepoints - 1, i), prices[1].getPriceAt(timepoints - 1, i));
                optimalPaths[timepoints - 1][i][j] = j;
            }

        }
        for (int i = timepoints - 2; i >= 0; i--) {
            for (int j = 0; j < simulations; j++) {
                float[] newBestValues = new float[states.length];
                for (int state = 0; state < states.length; state++) {
                    float valueOfState = states[state].caclulateValue(prices[0].getPriceAt(i, j), prices[1].getPriceAt(i, j));
                    float[] x = getMaxValues(optimalValues[j], state);
                    newBestValues[state] = valueOfState + x[0];
                    optimalPaths[i][j][state] = (int) x[1];

                }
                optimalValues[j] = newBestValues;

            }
        }

    }

    private static float[] getMaxValues(float[] values, int startIndex) {
        int[] allowedIndices = new int[]{startIndex - 1, startIndex, startIndex + 1};
        if (startIndex == 0) {
            allowedIndices = new int[]{startIndex, startIndex + 1};
        } else if (startIndex == states.length - 1) {
            allowedIndices = new int[]{startIndex, startIndex - 1};
        }
        float bestValue = -100000000;
        float bestIndex = -1;
        for (int i = 0; i < allowedIndices.length; i++) {
            int currentIndex = allowedIndices[i];
            if (values[currentIndex] > bestValue) {
                bestValue = values[currentIndex];
                bestIndex = currentIndex;
            }
        }
        return new float[]{bestValue, bestIndex};

    }

}
