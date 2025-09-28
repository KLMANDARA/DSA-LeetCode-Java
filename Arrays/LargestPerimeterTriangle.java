import java.util.Arrays;

public class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums); // Sort in ascending order
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }
}
