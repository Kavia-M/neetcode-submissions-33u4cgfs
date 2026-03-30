class Solution_MyOwn {
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


public class Solution_stack {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] pair = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));
        Stack<Double> stack = new Stack<>();
        for (int[] p : pair) {
            double time = ((double) (target - p[0]) / p[1]);
            if (stack.isEmpty() || 
                (time > stack.peek()))
            {
                stack.push(time);
            }
        }
        return stack.size();
    }
}

public class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] pair = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));
        double prevTime = (double)(target - pair[0][0]) / pair[0][1]; // push in epty stack
        int fleet  = 1 ; // push in empty stack
        for (int[] p : pair) {
            double time = ((double) (target - p[0]) / p[1]);
            if (time > prevTime) // stack peek is prevTime
            {
                fleet++; // push increases fleet size
                prevTime = time; // push means updatinfg this time so it becomes peek next time
            }
        }
        return fleet;
    }
}

