class Solution_Wrong {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int max_count = 0;
        for(char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        int sum = 0;
        for(int i : map.values()) {
            if(i > max) {
                max = i;
            }
            if(i == max) {
                
                max_count++;
            }
            sum+=i;
        }
        System.out.println(map.toString());
        System.out.println(max);
        System.out.println(max_count);
        return Math.max(sum, ((n+1)*(max-1)) + max_count);
    }
}


// Brute Force
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // HashMap for count can also be used as above wrong answer
        int[] count = new int[26]; // A to Z 0 - 25 is index

        for(char task : tasks) {
            count[task-65]++;
            //count[task-'A']
        }

        List<int[]> arr = new ArrayList<>(); // pairs
        for(int i=0; i<26; i++){
            if(count[i]>0) {
                arr.add(new int[]{count[i], i}); // like pair (count, task)
            }
        }

        int time = 0;
        ArrayList<Integer> processed = new Arraylist<>();
        
        // until all processes in arr is done
        while(!arr.isEmpty()) {
            // select a process for this slot in processed list
            for(int i=0; i<arr.size(); i++) {
                boolean process_can_be_selcted = true;
                

                // time can be used as index of processed list we are goign to fill now
                // we look for time-n (inclusive) to time - 1 (inclusive) (means last processed)
                
                // for(int j=Math.max(0, time-n); j <= time-1; j++)
                for(int j=Math.max(0, time-n); j<time; j++) {
                    // no need to check j < processed.size() it will be always true
                    if(processed[j] == arr[i][1]) {
                        process_can_be_selcted = false;
                    }
                }

                if(!process_can_be_selcted) {
                    continue; // leave the arr as it is
                }

                if(maxi==-1 || arr.get(maxi)[0] < arr.get(i)[0]) {
                    maxi = i;
                }
            }
        }
    }
}

