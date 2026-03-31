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
class Solution_brute_force {
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
        ArrayList<Integer> processed = new ArrayList<>();
        
        // until all processes in arr is done
        while(!arr.isEmpty()) {
            // select a process for this slot in processed list
            int maxi = -1; // task index in arr
            for(int i=0; i<arr.size(); i++) {
                boolean process_can_be_selcted = true;
                

                // time can be used as index of processed list we are goign to fill now
                // we look for time-n (inclusive) to time - 1 (inclusive) (means last processed)
                
                // for(int j=Math.max(0, time-n); j <= time-1; j++)
                for(int j=Math.max(0, time-n); j<time; j++) {
                    // no need to check j < processed.size() it will be always true
                    if(processed.get(j) == arr.get(i)[1]) {
                        process_can_be_selcted = false;
                        break; // reduce extra unwanted looping
                    }
                }

                if(!process_can_be_selcted) {
                    continue; // leave the arr as it is
                }

                if(maxi==-1 || arr.get(maxi)[0] < arr.get(i)[0]) {
                    maxi = i;
                }
            }

            // updating the processed list, however t++ even idle this time
            time++;
            if(maxi!=-1) {
                arr.get(maxi)[0]--;
                
                processed.add(arr.get(maxi)[1]);

                // DO NOT REMOVE BEFORE adding to processed, it will become index out of bound
                if(arr.get(maxi)[0] == 0) 
                    arr.remove(maxi); // int primitive is passed for remove INDEX function
            }
            else {
                processed.add(-1);
            }
        }
        return time;
    }
}

// given Brute Force
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
        ArrayList<Integer> processed = new ArrayList<>();
        
        // until all processes in arr is done
        while(!arr.isEmpty()) {
            // select a process for this slot in processed list
            int maxi = -1; // task index in arr
            for(int i=0; i<arr.size(); i++) {
                boolean process_can_be_selcted = true;
                

                // time can be used as index of processed list we are goign to fill now
                // we look for time-n (inclusive) to time - 1 (inclusive) (means last processed)
                
                // for(int j=Math.max(0, time-n); j <= time-1; j++)
                for(int j=Math.max(0, time-n); j<time; j++) {
                    if(j < processed.size() && processed.get(j) == arr.get(i)[1]) {
                        process_can_be_selcted = false;
                        break; // reduce extra unwanted looping
                    }
                }

                if(!process_can_be_selcted) {
                    continue; // leave the arr as it is
                }

                if(maxi==-1 || arr.get(maxi)[0] < arr.get(i)[0]) {
                    maxi = i;
                }
            }

            // updating the processed list, however t++ even idle this time
            time++;
            int curr = -1; // if idle we use this
            if(maxi!=-1) {
                // before removing store curr
                curr = arr.get(maxi)[1];
                arr.get(maxi)[0]--;
                

                if(arr.get(maxi)[0] == 0) 
                    arr.remove(maxi); // int primitive is passed for remove INDEX function

            }

            processed.add(curr);
        }
        return time;
    }
}

