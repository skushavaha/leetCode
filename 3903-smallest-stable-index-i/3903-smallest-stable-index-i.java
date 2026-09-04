class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int end = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {

            int larger = largest(i, nums);
            int smaller = smallest(i, nums);
            int value = larger - smaller;

            if (value <= k) {
                return i;
            }
        }
        return -1;
    }

    private int largest(int i, int[] arr) {

        int max = Integer.MIN_VALUE;
        for (int j = 0; j <= i; j++) {
            max = Math.max(max, arr[j]);
        }
        return max;
    }

    private int smallest(int i, int[] arr) {

        int min = Integer.MAX_VALUE;
        for (int j = i; j < arr.length; j++) {
            min = Math.min(min, arr[j]);
        }
        return min;
    }

}