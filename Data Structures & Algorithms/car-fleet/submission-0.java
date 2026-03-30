class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        Map<Integer, Double> positionTime = IntStream.range(0, position.length)
            .boxed() // Box the int primitives into Integer objects
            .collect(Collectors.toMap(
                i -> position[i], // key in map
                i -> ((double)(target - position[i])) / speed[i], // value in Map
                (exiting, replacement) -> replacement, // merger function
                TreeMap::new // Map supplier for sorting by key
            ));

        System.out.println(positionTime);

        Stack<Map.Entry<Integer, Double>> st = new Stack<>();
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(positionTime.entrySet());
        for(int i = 0; i<entryList.size(); i++) {
            while(!st.isEmpty() && entryList.get(i).getValue() >= st.peek().getValue()) {
                st.pop();
            }
            st.push(entryList.get(i));
        }


        return st.size();
    }
}


/*

    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>(); // pair: [temp, index]

        for (int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > stack.peek()[0]) {
                int[] pair = stack.pop();
                res[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{t, i});
        }
        return res;
    }
}



*/
