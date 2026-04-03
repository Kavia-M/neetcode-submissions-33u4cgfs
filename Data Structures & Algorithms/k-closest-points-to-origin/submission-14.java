// SOLUTION - 4 : QUICK SEARCH

/*
    Quick sort means fixes one by one element in its correct position

    here we aim to fix only kth element in its correct positiion, 
    means before kth element it will have <= elements, then kth element is correctly fitted
    after kth element there will be elements > kth element

    so if we fix k th elements we have k smallest element in the left side

*/


public class Solution_GIVEN_QUICK_SEARCH {
    public int[][] kClosest(int[][] points, int k) {
        int L = 0, R = points.length - 1; // both L and R are inclusive
        // since no element is fixed in current position now we make pivot = points.length. this is not valid index now
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
        // THIS FUNCTION FIXES LAST ELEMENT IN GIVEN RANGE INTO IT's CORRECT POSITION
        int pivotIdx = r;
        int pivotDist = euclidean(points[pivotIdx]); // to be pivoted is the last element
        int i = l; // i is the index for smaller elements that we will find inside array and swap it to position i
        for (int j = l; j < r; j++) { // we compare all elements before the last element
            if (euclidean(points[j]) <= pivotDist) { // if the jth element <= last element, swap it with the index for smaller elements
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
                i++; // increment i so the next smaller element goes to new incremented i. This is incremented one more time in the last before loop ends
            }
        }
        // since i is already incremented as we seen above, this is correct spot where we can place the LAST element (pivoted element)
        int[] temp = points[i];
        points[i] = points[r];
        points[r] = temp;
        return i; // this i is the position of PIVOTTED element
    }

    private int euclidean(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}


// My Solution little modified from the given solution
public class Solution {
    // if we are not sending points to fix_pivot function, we can make it class variable
    private int[][] points;
    public int[][] kClosest(int[][] points, int k) {
        this.points = points;
        int l = 0,  r = points.length - 1; // both are included
        int pivot = -1; // any non relevant index will work here for initialization as no element is fixed as of now

        while(pivot!=k) {
            pivot = fix_pivot(l,r);
            if(pivot < k) {
                // we need more elements , move to right
                l = pivot + 1;
            }
            else {
                // our needed kth element pivot is in left side
                r = pivot - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }


    private int fix_pivot(int l, int r) {
        int pivotted_element = euclidean(points[r]);
        int i = l; // not 0

        // from l to r fully
        // however we can going to swap the r at last after loop
        // so i include it inside. at last it will check r with itself and it will be get swapped with correct i
        for(int j=l; j<=r; j++) {
            // if(euclidean(points[j])<=pivotted_element) // we will do the process
            // so if the other way around, then we can skip it

            if(euclidean(points[j]) > pivotted_element) continue;

            // swapping part
            int[] temp = points[i]; // since each elememt itself a array of size 2
            points[i] = points[j];
            points[j] = temp;

            // if(j!=r)  /. skip this contition and handle in return stmt
            i++; 
        }

        // do not increment i if the loop reached r. we need the index of fixed last element only. if we not check this condition then
        //  return --i. return i-- is wrong as i is passed to return stmt and then only decrement happens in i--
        return --i;
    }

    private int euclidean(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }
}

