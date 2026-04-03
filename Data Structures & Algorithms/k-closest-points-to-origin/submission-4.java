// using max_heap
// we need closest points, so use the opposite heap. that is maxheap
// in maxheap we store k elements, if size>k pop the 1st head (pop the maximum head) this cannot be in k closer points anymore
class Solution_My_Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            int distA = a[0]*a[0] + a[1]*a[1]; // no need to minus with x2 y2 since (0,0) for origin
            int distB = b[0]*b[0] + b[1]*b[1];

            return Integer.compare(distB, distA); // max heap as distB comes first
        });
        // space O(k) 
        // if exceeds k remove the max element
        int i = 0;
        int n = points.length;
        for(int[] point : points) { // O(n)
            pq.add(point); // O(log k) since k is size of heap
            if(pq.size() > k) {
                pq.poll(); // O(log k)
            }  
        }
        // TOTAL TIME COMPLEXITY n * (logk + log k) = n*(2*logk) = n * log k

        return pq.toArray(new int[pq.size()][2]); // typed array, return type is passes in () in toArray

    }
}

// GIVEN SOLUTION 1 -> SORTING
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // sort inplace
        Arrays.sort(points, (a,b) -> {
            int distA = a[0]*a[0] + a[1]*a[1]; // no need to minus with x2 y2 since (0,0) for origin
            int distB = b[0]*b[0] + b[1]*b[1];

            return Integer.compare(distA, distB); // ASC ORDER as distA comes first
        });

        // copies to create a new array. starting index 0 included, ending index k excluded 
        // it is guaranteed that atleast k points given, but for safety do as below
        return Arrays.copyOfRange(points, 0, Math.min(k, points.length));


        /**
            * Note on Arrays.copyOfRange(original, from, to):
            * - If 'to' > original.length: Pads new array with default values (0, null, etc.).
            * - Throws ArrayIndexOutOfBoundsException: If 'from' < 0 or 'from' > original.length.
            * - Throws IllegalArgumentException: If 'from' > 'to'.
        */

    }
}
