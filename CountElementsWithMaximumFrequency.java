class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101];  // because nums[i] ≤ 100

        // Step 1: count frequencies
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: find maximum frequency
        int maxFreq = 0;
        for (int f : freq) {
            if (f > maxFreq) maxFreq = f;
        }

        // Step 3: sum frequencies of elements with max frequency
        int total = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                total += f;
            }
        }

        return total;
    }
}
