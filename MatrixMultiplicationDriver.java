// Matrix Multiplication - Classic and Strassen

import java.util.Random;

public class MatrixMultiplicationDriver {
    public static void main(String[] args) {
        long startClassic, endClassic, startStrassen, endStrassen;
        
        ClassicMultiplication classicMultiplicationMatrix = new ClassicMultiplication();
        StrassenMultiplication strassenMultiplicationMatrix = new StrassenMultiplication();

        int sizeArray[] = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        // Random numbers will be generated from 0 to poolSize
        int poolSize = 20;
        Random rand = new Random();

        for (int size: sizeArray) {
            int [][] firstMatrix = new int [size][size];
            int [][] secondMatrix = new int [size][size];
            int [][] solutionClassic = new int [size][size];
            int [][] solutionStrassen = new int [size][size];

            // Generate random numbers for first matrix array
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    firstMatrix[i][j] = rand.nextInt(poolSize) + 1;

            // Generate random numbers for second matrix array
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    secondMatrix[i][j] = rand.nextInt(size) + 1;
            
            startClassic = System.nanoTime();
            solutionClassic = classicMultiplicationMatrix.classicMultiply(firstMatrix, secondMatrix);
            endClassic = System.nanoTime();

            startStrassen = System.nanoTime();
            solutionStrassen = strassenMultiplicationMatrix.strassenMultiply(firstMatrix, secondMatrix);
            endStrassen = System.nanoTime();

            // printOriginalMatrices(firstMatrix, secondMatrix);
            // printSolution("Classic Multiplication Matrix Solution:", solutionClassic);
            // printSolution("Strassen Multiplication Matrix Solution:", solutionStrassen);

            System.out.println("Type of method for index size: " + size + "x" + size + "       \t\tTime (ns)");
            System.out.printf("%30s%35d\n", "Classic Multiplication Matrix", endClassic - startClassic);
            System.out.printf("%30s%35d\n\n", "Strassen Multiplication Matrix", endStrassen - startStrassen);
        }
    }

    static void printOriginalMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        System.out.print("\n=====> Matrix Array size: " + firstMatrix.length + "x" + firstMatrix.length + " <=====\n");
        System.out.print("MatrixA contents:");
        printMatrix(firstMatrix);
        System.out.print("MatrixB contents:");
        printMatrix(secondMatrix);
    }

    static void printSolution(String statement, int[][] solutionMatrix) {
        System.out.print(statement);
        printMatrix(solutionMatrix);
    }

    static void printMatrix(int [][] matrixArray) {
        System.out.println();
        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray.length; j++)
                System.out.print(matrixArray[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}