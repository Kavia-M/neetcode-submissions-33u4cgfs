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
    Map<Integer, List<int[]>> userTweets; // values are list of pair of count and userID
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
        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new int[]{count, tweetId});
        tweets.add(new int[]{tweetId, userId});
    }
    
    public List<Integer> getNewsFeed(int userId) {
    /*
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

    */
        List<Integer> neededUsers = new ArrayList<>();
        neededUsers.add(userId);
        if(userFollowees.containsKey(userId)) {
            neededUsers.addAll(userFollowees.get(userId));
        }
        List<int[]> neededTweets = new ArrayList<>();
        for(int user : neededUsers) {
            if(userTweets.containsKey(user)) {
                neededTweets.addAll(userTweets.get(user));
            }
        }
        neededTweets.sort((a, b) -> b[0] - a[0]);

        // sublist - The returned list is a view, not a new, separate list. Modifications to the sublist will affect the original list, and vice versa. If you need an independent list, wrap the result in a new ArrayList.
        return new ArrayList<>(neededTweets.subList(0, Math.min(10, neededTweets.size())).stream().map(a -> a[1]).collect(Collectors.toList()));
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
