class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> dp = new ArrayList<>();

        // First row
        dp.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {

            List<Integer> row = new ArrayList<>();

            // First element
            row.add(1);

            // Middle elements
            for (int j = 1; j < i; j++) {

                int value = dp.get(i - 1).get(j - 1)
                          + dp.get(i - 1).get(j);

                row.add(value);
            }

            // Last element
            row.add(1);

            dp.add(row);
        }

        return dp;
    }
}