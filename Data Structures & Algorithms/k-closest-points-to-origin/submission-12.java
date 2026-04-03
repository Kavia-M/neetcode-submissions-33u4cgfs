// SOLUTION - 4 : QUICK SEARCH

/*
    Quick sort means fixes one by one element in its correct position

    here we aim to fix only kth element in its correct positiion, 
    means before kth element it will have <= elements, then kth element is correctly fitted
    after kth element there will be elements > kth element

    so if we fix k th elements we have k smallest element in the left side

*/


public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int L = 0, R = points.length - 1;
        int pivot = points.length;
        // pivot means pivot's element is fixed
        // if kth element is fixed we will have k smallest elements (inclusing kth element) in starting
        // pivot is number of fixed elements
        while (pivot != k) {
            pivot = partition(points, L, R); // fixing one of the element in this range only
            if (pivot < k) { // number of fixed elements is less than k, so we need to keep processing after the current pivot also
                // pivot + 1 th element is already greater than pivot and so other elements after this,. so if we fix another element after this pivot eventually new pivot will have all elements before it <= new pivot
                L = pivot + 1;
            } else { // number of fixed elements is >= k means R = pivot - 1 means we need to search for kth element in the left side, NOTE: we cannot jump to kth element here becasue it is not sorted before pivot. PIVOT is onyl fixed and before after are not sorted. if pivot==k means dont worry in next check in while it ends loop
                R = pivot - 1;
            }
        }
        int[][] res = new int[k][2];

        // native fast array copy method
        /*
            copy from: points
            start index: 0

            copy to: res
            start index: 0

            number of elements: k
        */
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    private int partition(int[][] points, int l, int r) {
        int pivotIdx = r;
        int pivotDist = euclidean(points[pivotIdx]);
        int i = l;
        for (int j = l; j < r; j++) {
            if (euclidean(points[j]) <= pivotDist) {
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
                i++;
            }
        }
        int[] temp = points[i];
        points[i] = points[r];
        points[r] = temp;
        return i;
    }

    private int euclidean(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
