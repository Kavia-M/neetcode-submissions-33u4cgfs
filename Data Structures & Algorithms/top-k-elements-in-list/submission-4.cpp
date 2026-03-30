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

        k = min(k, (int)cnt.size());
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







class Solution_with_complexity_analysis {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;

        // Time: O(n)
        // Count frequency of each number
        for (int num : nums) {
            cnt[num]++;
        }

        // Time: O(n)
        // Space: O(n)
        // buckets[f] stores all numbers that appear exactly f times
        // Maximum possible frequency of any number is n
        vector<vector<int>> buckets(nums.size() + 1);

        // Time: O(n)
        // Place numbers into buckets based on their frequency
        for (const auto &x : cnt) {   // x.first = number, x.second = frequency
            buckets[x.second].push_back(x.first);
        }

        // k cannot exceed number of unique elements
        k = min(k, (int)cnt.size());

        vector<int> ans;

        // Time: O(n)
        // Traverse buckets from highest frequency to lowest
        for (auto it = buckets.rbegin(); it != buckets.rend() && k > 0; ++it) {
            for (int number : *it) {
                ans.push_back(number);
                k--;
                if (k == 0) break;
            }
        }

        return ans;
    }

// TIME COMPLEXITY
// Frequency counting      → O(n)
// Bucket grouping         → O(n)
// Reverse bucket traversal→ O(n)
// --------------------------------
// Total                  → O(n)


// SPACE COMPLEXITY
// unordered_map           → O(n)
// bucket array             → O(n)
// output vector            → O(k)
// --------------------------------
// Total                   → O(n)

};



