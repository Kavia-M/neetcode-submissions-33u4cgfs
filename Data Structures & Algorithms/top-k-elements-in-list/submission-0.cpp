class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;

        //O(n)
        for(int num : nums) {
            cnt[num]++;
        }

        // O(n)
        vector<pair<int, int>> v(cnt.begin(), cnt.end());

        // O(n logn)
        sort(v.begin(), v.end(), 
            [](auto &a, auto &b) {
            return a.second > b.second;  // sort by VALUE desc
        });

        k = min(k, (int)v.size());
        vector<int> ans;

        for (int i = 0; i < k; i++) {
            ans.push_back(v[i].first);
        }
        
        return ans;
    }
};
