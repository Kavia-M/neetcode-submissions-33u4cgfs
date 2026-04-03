// GIVEN SOLUTION 2 -> MIN HEAP
// we keep {distance, x_coordinate, y_coordinate} as array
class Solution {
    public int[][] kClosest(int[][] points, int k) { // capital K is given in this solution

        // This is a factory method that creates a comparator based on a key.
        // for reversing Comparator.comparing(Student::getAge).reversed();
        // for multiple Comparator<Student> comp = Comparator.comparing(Student::getAge).thenComparing(Student::getName);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        for(int[] point : points) {
            // int dist = point[0]*point[0] + point[1]*point[1];
            int dist = (int)myPow(point[0], 2) + (int)myPow(point[1], 2);
            minHeap.offer(new int[]{dist, point[0], point[1]});
        }

        int[][] res = new int[k][2]; // size or initialization needed
        for(int i=0; i<res.length; i++) { // res.length is number of rows which is k. ++i can also be used, because the i there is not used anywhere not giving value of i to anywhere
            // 1st poll and save it, or we will loss the polled point y coordinate. 
            // but we can use peek for x coordinate and poll for y coordinate also

            // it is guaranteed k points are there, but for safety do below
            if(minHeap.isEmpty()) break;
            res[i] = new int[]{minHeap.peek()[1], minHeap.poll()[2]};
        }
        return res;
    }

    // GET POWER IN O(log n) time
    private double myPow(double x, int n){
        long N = n;

        if(N < 0){
            x = 1/x;
            N = -N;
        }

        double res = 1;

        while(N > 0){
            if(N % 2 == 1)
                res *= x;

            x *= x;
            N /= 2;
        }

        return res;
    }
}


