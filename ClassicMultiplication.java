public class ClassicMultiplication {
    public int[][] classicMultiply(int A[][], int B[][]) {
        int size = A.length;
        int [][] C = new int[size][size];

        /*
           The number of columns in matrix A must be equal to the
           number of rows in matrix B. The resulting matrix, known
           as the matrix product, has the number of rows in matrix A
           and the number of columns in matrix B. The product of
           matrices A and B is denoted as C.
        */
        // Classic (naive) method of matrix multiplication will take O(n^3)
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
