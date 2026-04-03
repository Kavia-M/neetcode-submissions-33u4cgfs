
/*
Because each array is **sorted**.

So we always have:

```
leftA ≤ rightA
leftB ≤ rightB
```

If both conditions were false, we would need:

```
leftA > rightB
and
leftB > rightA
```

From `leftB > rightA` and `rightA ≥ leftA`, we get:

```
leftB > leftA
```

But from `leftA > rightB` and `rightB ≥ leftB`, we get:

```
leftA > leftB
```

So we would have both:

```
leftB > leftA
and
leftA > leftB
```

which is impossible.

Therefore **both conditions cannot be false at the same time**.
*/
class Solution {
    /*
    Once x Is Guessed

    We now know both partitions:

    A: [A_left | A_right]
    B: [B_left | B_right]

    Specifically:

    A_left_last  = A[x-1]
    A_right_first = A[x]

    B_left_last  = B[y-1]
    B_right_first = B[y]
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // making new references to the array, this does not increase space complexity
        int[] a, b;
        int n1, n2;
        if(nums1.length <= nums2.length) {
            a = nums1;
            n1 = nums1.length;
            b = nums2;
            n2 = nums2.length;
        }
        else {
            a = nums2;
            n1 = nums2.length;
            b = nums1;
            n2 = nums1.length;
        }
        int half = (n1 + n2) / 2;
        int l = 0, r = n1 - 1;
        
        while(l <= r) {
            // c is x - 1 since 0 indexed
            int c = l + (r - l) / 2;
            System.out.println(c);
            // next array x1 = half - x;
            // c1 is x1 - 1
            // x1 is c1 + 1
            // x is c + 1
            // c1 + 1 = half - (c + 1)
            //  c1 = half - c - 1 - 1
            // c1 = half - c -2
            int c1 = half - c - 2;
            // we need a[c] < b[c1+1] 
            // c1 < n2-1 because 2nd array cant be fully taken for left of median part at any point
            // if not more left
            // we need a[c+1] > b[c1]
            // if c1 = n1-1 it means smaller array is fully taken for left of median
            // in this case no need condition 2
            // if not move right
            // since duplicates can be allowed I choose <= >=
            
            System.out.println()
            System.out.println()
            System.out.println()
            boolean condition1 = a[c] <= b[c1+1];
            boolean condition2 = c==n1-1 || a[c+1] >= b[c1];
            if(condition1 && condition2) {
                // correct
                // if odd min element in right part
                if((n1+n2)%2 == 1) {
                    if(c==n1-1) {
                        return b[c1+1];
                    }
                    return Math.min(a[c+1], b[c1+1]);
                }
                else {
                    // if even, avg of (max of left) and (min of right)
                    int maxLeft = Math.max(a[c], b[c1]);
                    int minRight = (c == n1-1) ? b[c1+1] : Math.min(a[c+1], b[c1+1]);
                    return (double)(maxLeft + minRight) / 2;
                }
            }
            else if(! condition1) {
                r = c - 1;
            }
            else if(! condition2) {
                l = c + 1;
            }
            else {
                System.out.println("Arrays are not sorted");
                return Integer.MIN_VALUE;
            }
        }
        System.out.println("Cannot find median");
        return Integer.MIN_VALUE;
    }
}

class Solution_bruteForce {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Determine the length of the new array
        int mergedLength = nums1.length + nums2.length;
        int[] mergedArray = new int[mergedLength];
        
        // Copy elements from nums1 to mergedArray
        System.arraycopy(nums1, 0, mergedArray, 0, nums1.length);
        
        // Copy elements from nums2 to mergedArray, starting after nums1 elements
        System.arraycopy(nums2, 0, mergedArray, nums1.length, nums2.length);
        // O(k) extra space compexity where k = n+m

        // Sort the combined array O(k log k) where k = n+m
        Arrays.sort(mergedArray);

        if((mergedLength % 2) == 1) {
            return (double) mergedArray[mergedLength/2];
        }
        return ((double) mergedArray[mergedLength/2] + mergedArray[mergedLength/2 - 1])/2;


    }
}