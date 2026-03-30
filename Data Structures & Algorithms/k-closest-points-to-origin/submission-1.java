// using max_heap
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            int distA = a[0]*a[0] + a[1]*a[1];
            int distB = b[0]*b[0] + b[1]*b[1];

            return Integer.compare(distB, distA); // max heap as distB comes first
        });
        // space O(n) 
        // if exceeds k remove the max element
        int i = 0;
        int n = points.length;
        for(int[] point : points) {
            for(int[] p :pq) {
                System.out.println(Arrays.toString(p));
            }
            pq.add(point);
            if(pq.size() > k) {
                pq.poll();
            }  
            for(int[] p :pq) {
                System.out.println(Arrays.toString(p));
            }
        }

        return pq.toArray(new int[pq.size()][2]); // typed array, return type is passes in () in toArray

    }
}

// Min heap correct implementation
class KthLargest {
    private int k;
    private PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>(); // default min // space O(k)
        for(int num : nums) {
            // add it
            pq.offer(num); // O(log k) since we maintain k size for pq

            if(pq.size() > k) {
                // remove least elenent
                pq.poll(); // O(log k)
            }
        }
    }
    
    public int add(int val) { // called m times as m times add is called
        // max_heap
        pq.offer(val); // O(log k)
        if(pq.size() > k) 
            pq.poll(); // O(log k)
        
        return pq.peek(); // O(1)
    }
}