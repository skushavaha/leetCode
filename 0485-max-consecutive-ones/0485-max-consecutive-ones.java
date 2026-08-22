class Solution {
    public int findMaxConsecutiveOnes(int[] arr) {
        int count = 0;
        int maxCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1){
                count++;
                maxCount = Math.max(count , maxCount);
            }else {
                count = 0;
            }
        }
        return maxCount;
    }
}