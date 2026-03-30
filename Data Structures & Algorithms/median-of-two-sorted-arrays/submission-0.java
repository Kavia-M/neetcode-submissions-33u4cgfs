class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Determine the length of the new array
        int mergedLength = nums1.length + nums2.length;
        int[] mergedArray = new int[mergedLength];
        
        // Copy elements from nums1 to mergedArray
        System.arraycopy(nums1, 0, mergedArray, 0, nums1.length);
        
        // Copy elements from nums2 to mergedArray, starting after nums1 elements
        System.arraycopy(nums2, 0, mergedArray, nums1.length, nums2.length);

        // Sort the combined array O(k log k) where k = n+m
        Arrays.sort(mergedArray);

        if((mergedLength % 2) == 1) {
            return (double) mergedArray[mergedLength/2];
        }
        return ((double) mergedArray[mergedLength/2] + mergedArray[mergedLength/2 - 1])/2;


    }
}