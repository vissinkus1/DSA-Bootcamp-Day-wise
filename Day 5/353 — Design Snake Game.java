class SnakeGame {

    private int width;
    private int height;

    private Deque<int[]> snake;
    private HashSet<String> body;

    private int[][] food;
    private int foodIndex;

    public SnakeGame(int width, int height, int[][] food) {

        this.width = width;
        this.height = height;

        this.food = food;
        this.foodIndex = 0;

        snake = new LinkedList<>();
        body = new HashSet<>();

        // Starting position
        snake.addFirst(new int[]{0, 0});
        body.add("0,0");
    }

    public int move(String direction) {

        int[] head = snake.peekFirst();

        int row = head[0];
        int col = head[1];

        // Calculate new head
        if (direction.equals("U")) {
            row--;
        }
        else if (direction.equals("D")) {
            row++;
        }
        else if (direction.equals("L")) {
            col--;
        }
        else if (direction.equals("R")) {
            col++;
        }

        // Check boundary
        if (row < 0 || row >= height ||
            col < 0 || col >= width) {

            return -1;
        }

        String newHead = row + "," + col;

        // Check if food is eaten
        boolean eating = false;

        if (foodIndex < food.length &&
            food[foodIndex][0] == row &&
            food[foodIndex][1] == col) {

            eating = true;
        }

        // If not eating, tail will move away
        if (!eating) {

            int[] tail = snake.removeLast();

            body.remove(tail[0] + "," + tail[1]);
        }

        // Check collision with body
        if (body.contains(newHead)) {
            return -1;
        }

        // Add new head
        snake.addFirst(new int[]{row, col});
        body.add(newHead);

        // If food eaten
        if (eating) {
            foodIndex++;
        }

        return snake.size() - 1;
    }
}