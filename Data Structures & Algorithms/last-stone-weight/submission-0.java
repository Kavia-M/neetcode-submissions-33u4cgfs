class Solution {
    public int lastStoneWeight(int[] stones) {
        List<Integer> list = Arrays.stream(stones).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(list);
        while(pq.size()>1) {
            int x = pq.poll();
            int y = pq.poll();
            System.out.println(x + " " + y);
            if(x!=y) {
                // y is 2nd in maxheap, it means it is less than or equal to x
                pq.add(x-y);
            }
        }
        if(!pq.isEmpty()) return pq.poll();
        return 0;
    }
}
