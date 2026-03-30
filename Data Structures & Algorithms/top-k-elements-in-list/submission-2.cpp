class Solution_brute_force {
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


class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;

        //O(n)
        for(int num : nums) {
            cnt[num]++;
        }


        vector<vector<int>> buckets(nums.size() + 1);  // 0 index wil lbe unused
        for(const auto &x : cnt) { // const for not modifiing the map
            buckets[x.second].push_back(x.first);
        }

        k = min(k, (int)nums.size());
        vector<int> ans;


        for (auto it = buckets.rbegin(); it != buckets.rend(); ++it) {
            for(auto &number : *it) {
                if(k>0) {
                ans.push_back(number);
                k--;
                }
            }
            //cout << *it << " ";
        }
        
        return ans;
    }
};


