class KthLargest_brute_force {
    private int k;
    private List<Integer> list;
    public KthLargest_brute_force(int k, int[] nums) {
        this.k = k;
        this.list = Arrays.stream(nums)
                  .boxed()
                  .collect(Collectors.toList());
        list.sort(Comparator.reverseOrder()); //O(nlogn)
    }
    
    public int add(int val) {
        list.add(val);
        Collections.sort(list, Comparator.reverseOrder());
        return list.get(k-1);
    }
}

class KthLargest {
    private int k;
    private PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = Arrays.stream(nums)
                  .boxed()
                  .collect(Collectors.toCollection(
                        () -> new PriorityQueue<Integer>((a, b) -> b-a)         // or inside () we can also use Comparator.reverseOrder()
                    )); // insetrt n elements insert is logn. so O(nlogn)

        // negative → left wins in comparator
        // positive → right wins
    }
    
    public int add(int val) {
        // max_heap
        pq.offer(val);
        System.out.println(pq.toString());
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(pq);
        System.out.println(pq1.toString());
        for(int i =0; i<k-1; i++) {
            pq1.poll();
        }
        System.out.println(pq1.toString());
        return pq1.peek();
    }
}


/*
What is buildheap using heapify?

Building a heap from an array using bottom-up approach instead of inserting one by one.
*/
