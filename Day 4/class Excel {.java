class Excel {

    private int[][] values;
    private Map<String, Map<String, Integer>> formulas;
    private int rows;
    private int cols;

    public Excel(int height, char width) {

        rows = height;
        cols = width - 'A' + 1;

        values = new int[height + 1][cols];

        formulas = new HashMap<>();
    }

    public void set(int row, char column, int val) {

        String cell = column + String.valueOf(row);

        // Remove old formula
        formulas.remove(cell);

        values[row][column - 'A'] = val;
    }

    public int get(int row, char column) {

        String cell = column + String.valueOf(row);

        if (!formulas.containsKey(cell)) {
            return values[row][column - 'A'];
        }

        return calculate(cell, new HashSet<>());
    }

    public int sum(int row, char column, String[] numbers) {

        String cell = column + String.valueOf(row);

        Map<String, Integer> map = new HashMap<>();

        for (String s : numbers) {

            if (s.contains(":")) {

                String[] parts = s.split(":");

                String start = parts[0];
                String end = parts[1];

                char startCol = start.charAt(0);
                int startRow = Integer.parseInt(start.substring(1));

                char endCol = end.charAt(0);
                int endRow = Integer.parseInt(end.substring(1));

                for (int r = startRow; r <= endRow; r++) {

                    for (char c = startCol; c <= endCol; c++) {

                        String ref = c + String.valueOf(r);

                        map.put(
                            ref,
                            map.getOrDefault(ref, 0) + 1
                        );
                    }
                }

            } else {

                map.put(
                    s,
                    map.getOrDefault(s, 0) + 1
                );
            }
        }

        formulas.put(cell, map);

        return calculate(cell, new HashSet<>());
    }

    private int calculate(
            String cell,
            Set<String> visiting) {

        // Cycle protection
        if (visiting.contains(cell)) {
            return 0;
        }

        if (!formulas.containsKey(cell)) {

            int row = Integer.parseInt(cell.substring(1));
            char col = cell.charAt(0);

            return values[row][col - 'A'];
        }

        visiting.add(cell);

        int result = 0;

        for (Map.Entry<String, Integer> entry :
                formulas.get(cell).entrySet()) {

            String ref = entry.getKey();
            int count = entry.getValue();

            result += calculate(ref, visiting) * count;
        }

        visiting.remove(cell);

        return result;
    }
}