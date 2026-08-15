class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] smallest = nums1.length > nums2.length ? nums2 : nums1;
        int[] largest = nums1.length > nums2.length ? nums1 : nums2;
        int totalLength = nums1.length + nums2.length;

        int low = 0 ; int high = smallest.length;

        while (low <= high){
            int partitionX = low + (high  - low)/2;
            int partitionY = (totalLength + 1)/2 - partitionX;

            int l1 = partitionX == 0 ? Integer.MIN_VALUE : smallest[partitionX - 1];
            int r1 = partitionX == smallest.length ? Integer.MAX_VALUE : smallest[partitionX];

            int l2 = partitionY == 0 ? Integer.MIN_VALUE : largest[partitionY - 1];
            int r2 = partitionY == largest.length ? Integer.MAX_VALUE : largest[partitionY];

            if (l1 <= r2 && l2 <= r1){

                if ((totalLength) % 2 == 0){
                    return (Math.max(l1 , l2) + Math.min(r1, r2)) / 2.0;
                }else{
                      return Math.max(l1 , l2);
                }
                
            }
              if (l1 > r2){
                    high = partitionX - 1;
                }else {
                    low = partitionX + 1;
                }
        }
        return 0;
    }
}