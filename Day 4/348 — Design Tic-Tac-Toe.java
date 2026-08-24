class TicTacToe {

    private int n;

    private int[] rows;
    private int[] cols;

    private int diagonal;
    private int antiDiagonal;

    public TicTacToe(int n) {

        this.n = n;

        rows = new int[n];
        cols = new int[n];

        diagonal = 0;
        antiDiagonal = 0;
    }

    public int move(int row, int col, int player) {

        int value;

        if (player == 1) {
            value = 1;
        } else {
            value = -1;
        }

        // Update row
        rows[row] += value;

        // Update column
        cols[col] += value;

        // Main diagonal
        if (row == col) {
            diagonal += value;
        }

        // Anti-diagonal
        if (row + col == n - 1) {
            antiDiagonal += value;
        }

        // Check winner
        if (Math.abs(rows[row]) == n ||
            Math.abs(cols[col]) == n ||
            Math.abs(diagonal) == n ||
            Math.abs(antiDiagonal) == n) {

            return player;
        }

        return 0;
    }
}