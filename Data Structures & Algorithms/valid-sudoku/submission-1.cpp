class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        // rows[9]   → 9 hash sets (one per row)
        // cols[9]   → 9 hash sets (one per column)
        // boxes[9]  → 9 hash sets (one per 3×3 sub-box)
        vector<unordered_set<char>> rows(9);
        vector<unordered_set<char>> columns(9);
        vector<unordered_set<char>> boxes(9);

        for(int row=0; row<board.size(); row++) {
            auto& rSet = rows[row];
            for(int column=0; column<board[row].size(); column++) {
                auto& cSet = columns[column];

                int box = (row / 3) * 3 + (column / 3);
                auto& bSet = boxes[box];

                char element = board[row][column];

                // we can check !('0'<=element<='9') also or (element=='.')

                // if(!isdigit(element)) 
                //     continue;
                if(element=='.')
                    continue;
                
                // if an element is not found it WILL be equal to end()
                if((rSet.find(element) == rSet.end()) && 
                   (cSet.find(element) == cSet.end()) &&
                   (bSet.find(element) == bSet.end())) {
                    rSet.insert(element);
                    cSet.insert(element);
                    bSet.insert(element);
                   }
                else
                    return false; 
            }
        }
        return true;
    }
};
