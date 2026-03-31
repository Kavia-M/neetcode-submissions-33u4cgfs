/*
    containsValue(value) in HashMap is O(n) 
    iteration in HashMap and HashSet is O(n)

    other operations are O(1) in average and O(n) in worst case and after java 8, red black tree implemented so O(log n) in worst case
*/
class Twitter {

    int count; 
    /* 
    Even if HashSet is a child of Set,
    HashSet<Integer> is NOT a child of Set<Integer> in generics.
    */
    // Map<Integer, Set<Integer>> userFollowees = new HashMap<Integer, HashSet<Integer>>();
    Map<Integer, Set<Integer>> userFollowees; // hashset is used for unfollow to become O(1)
    Map<Integer, int[]> userTweets;
    List<int[]> tweets; // tweetId, userId. added as it comes so time is tracked
//    PriorityQueue<int[]> lastestTweets; // {count, tweetId, UserId} who posted it
    public Twitter() {
        count = 0;
        userFollowees = new HashMap<>();
        userTweets = new HashMap<>();
        tweets = new ArrayList<>();
//        lastestTweets = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    }
    
    public void postTweet(int userId, int tweetId) {
        // insert O(1) in HashMap
        count++;
        userTweets.put(userId, new int[]{count, tweetId});
        tweets.add(new int[]{tweetId, userId});
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feeds = new ArrayList<>();
        for(int i = tweets.size()-1; i>=0; i--) {
            int postedUser = tweets.get(i)[1];
            if(postedUser == userId || 
                userFollowees.containsKey(userId) && userFollowees.get(userId).contains(postedUser)) {
                    feeds.add(tweets.get(i)[0]);
                    if(feeds.size() == 10) 
                        break;
                }
        }
        return feeds;
    }
    
    public void follow(int followerId, int followeeId) { // follower is the current user who click follow button
        userFollowees.putIfAbsent(followerId, new HashSet<>());
        userFollowees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(userFollowees.containsKey(followerId)) {
            userFollowees.get(followerId).remove(followeeId);
        }

        /*
        // the below can be used for memory managenent
        // containsKey and get both loopup the hashMap, the below reduces loopup to only once
        // also memory management by removing users in map who doesnot follow anyone
        Set<Integer> set = userFollowees.get(followerId);

        if(set != null){
            set.remove(followeeId);

            if(set.isEmpty()){
                userFollowees.remove(followerId);
            }
        }
        */

    }
}
