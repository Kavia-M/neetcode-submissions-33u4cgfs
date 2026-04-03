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

// GIVEN SOLUTUION 2 is min heap is O((n+k) log n) => O(n log n) as k<=n as given in question

// GIVEN SOLUTION 3 -> MAX HEAP
// this is equivalent to my solution
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            // Integer.compare() instead of minus to prevent integer overflow
            (a,b) -> Integer.compare(b[0]*b[0] + b[1]*b[1] , a[0]*a[0] + a[1]*a[1]) // b before a. so desc order
        ); 

        for(int[] point : points) {
            maxHeap.offer(point);
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // int[][] res = new int[k][2]; // [[0,0], [0,0]...] by defaut as it is primitive datatype
        // not possible in given question by if n<k heap size will be less than k
        int[][] res = new int[maxHeap.size()][2];
        int i = 0;
        while(!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll(); // res[i] is updated and then i increments
        }
        return res;
    }
}
