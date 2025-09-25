import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Create a 1D dp array with size equal to the last row
        int[] dp = new int[n];

        // Initialize with the last row of the triangle
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Start from the second-last row and move upward
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                int current = triangle.get(row).get(col);
                dp[col] = current + Math.min(dp[col], dp[col + 1]);
            }
        }

        return dp[0]; // The top element contains the result
    }
}
