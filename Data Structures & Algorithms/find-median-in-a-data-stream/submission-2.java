class MedianFinder {
    private int n;
    private PriorityQueue<Integer> left_max, right_min;
    public MedianFinder() {
        left_max = new PriorityQueue<>(Collections.reverseOrder());
        right_min = new PriorityQueue<>();
        n = 0;
    }
    
    public void addNum(int num) {
        n++;
        int left_heap_size = n/2;
        if(right_min.isEmpty() || (num < right_min.peek())) {
            left_max.offer(num);
        }
        else right_min.offer(num);
        

        if(left_max.size() > left_heap_size) {
            right_min.offer(left_max.poll());
        }
        if(right_min.size() > n - left_heap_size) {
            left_max.offer(right_min.poll());
        }
    }
    
    public double findMedian() {
        if(n%2==0) {
            return (left_max.peek() + right_min.peek()) / (double) 2;
        }
        return right_min.peek();
    }
}
