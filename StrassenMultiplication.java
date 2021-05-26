public class StrassenMultiplication {
    /*
        The Strassen matrix multiplication algorithm is useful in practice
        for large matrices, but would be slower for extremely large matrices
        since it adds a considerable O(n^2) workload in addition and subtractions.
    */
    public int[][] strassenMultiply(int[][] A, int[][] B) {
        final int LEAF_SIZE = 30;
        int size = A.length;
        int[][] C = new int[size][size];

        // Base case of recursive algorithm
        if (size <= LEAF_SIZE)
            C = naiveMultiply(A, B);

        /*
           Partition A into four submatrices
           Partition B into four submatrices
           Compute C = A x B using Strassen's method
        */
        else {
            // Initialize A into four submatrices A11, A12, A21, A22
            int[][] A11 = new int[size / 2][size / 2];
            int[][] A12 = new int[size / 2][size / 2];
            int[][] A21 = new int[size / 2][size / 2];
            int[][] A22 = new int[size / 2][size / 2];

            // Initialize B into four submatrices B11, B12, B21, B22
            int[][] B11 = new int[size / 2][size / 2];
            int[][] B12 = new int[size / 2][size / 2];
            int[][] B21 = new int[size / 2][size / 2];
            int[][] B22 = new int[size / 2][size / 2];

            // Define subarray values for matrices
            int k = size / 2;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][k + j];
                    A21[i][j] = A[k + i][j];
                    A22[i][j] = A[k + i][k + j];
                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][k + j];
                    B21[i][j] = B[k + i][j];
                    B22[i][j] = B[k + i][k + j];
                }
            }

            /**
            To multiply two 2 × 2 matrices, Strassen’s method requires
            seven multiplications and 18 additions/subtractions
            M1 = (A11 + A22) * (B11 + B22)
            M2 = (A21 + A22) * B11
            M3 = A11 * (B12 - B22)
            M4 = A22 * (B21 - B11)
            M5 = (A11 + A12) * B22
            M6 = (A21 - A11) * (B11 + B12)
            M7 = (A12 - A22) * (B21 + B22)
            **/
            int[][] M1 = strassenMultiply(add(A11, A22), add(B11, B22));
            int[][] M2 = strassenMultiply(add(A21, A22), B11);
            int[][] M3 = strassenMultiply(A11, subtract(B12, B22));
            int[][] M4 = strassenMultiply(A22, subtract(B21, B11));
            int[][] M5 = strassenMultiply(add(A11, A12), B22);
            int[][] M6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
            int[][] M7 = strassenMultiply(subtract(A12, A22), add(B21, B22));

            /**
            The product C is given by:
            C11 = M1 + M4 - M5 + M7
            C12 = M3 + M5
            C21 = M2 + M4
            C22 = M1 - M2 + M3 + M6
            **/
            int[][] C11 = add(subtract(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(subtract(add(M1, M3), M2), M6);

            // Return the values from subarray matrices to matrix
            for(int i = 0; i < k; i++)
                for(int j = 0; j < k; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + k] = C12[i][j];
                    C[k + i][j] = C21[i][j];
                    C[k + i][k + j] = C22[i][j];
                }
        }

        return C;
     }

    // Subtract two matrices
    public int[][] subtract(int[][] matrixOne, int[][] matrixTwo) {
        int n = matrixOne.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = matrixOne[i][j] - matrixTwo[i][j];
        return temp;
    }

    // Add two matrices
    public int[][] add(int[][] matrixOne, int[][] matrixTwo) {
        int n = matrixOne.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = matrixOne[i][j] + matrixTwo[i][j];
        return temp;
    }

    public int[][] naiveMultiply(int A[][], int B[][]) {
        int size = A.length;
        int [][] C = new int[size][size];

        // Perform naive method of matrix multiplication
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
}