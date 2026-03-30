/*
NOTE:
Strings can contain ANY UTF-8 character (emojis, symbols, non-English letters).
So using a delimiter (even non-ASCII) is unsafe because it may appear inside a string.
Hence, delimiter-based encoding is ambiguous; length-based encoding is required.

UTF-8 allows any character, so delimiter-based encoding is unsafe and ambiguous.

UTF-8 supports all characters; any chosen delimiter could exist in the string, 
making naive split-based encoding unreliable.
*/

class Solution {
public:

    string encode(vector<string>& strs) {
    //    int product = 1;  // removing product since it may exceed limit
        int sum = 0;
        string str = "";
        for(int i = 0; i < strs.size(); i++) {
            int str_size = strs[i].size();
            sum+=str_size;
            string str_size_as_string = to_string(str_size);
            // #size:string@size*
            str += "#" + str_size_as_string + ":" +  strs[i] + "@" + str_size_as_string + "*";
        }
        string sum_string = "sum=" + to_string(sum);
        string sum_string_size_as_string = to_string(sum_string.size());
        str += "#" + sum_string_size_as_string + ":" +  sum_string + "@" + sum_string_size_as_string + "*";
        return str;
    }

    vector<string> decode(string s) {
        vector<string> strs;
        int i = 0;
        int sum = 0;
        while(true) {
            int num = 0;
            string str = "";

            if(s[i++]!='#') 
                cout << "Not #";
            
            while (i < s.size() && isdigit(s[i])) 
                num = num * 10 + (s[i++] - '0');
            
            if(s[i++] != ':') 
                cout << "Not :";
            
            for(int j=0; j<num; j++) 
                str+=s[i++];
            
                        
            int num1 = 0;
            if(s[i++]!='@') 
                cout << "Not @";
            
            while (i < s.size() && isdigit(s[i])) 
                num1 = num1 * 10 + (s[i++] - '0');
            
            if(s[i++]!='*') 
                cout << "Not *";
            
            if(num != num1)
                cout << "in a string size not match";

            sum+=num;
            strs.push_back(str);

            if(i==s.size())
                break;
        }

        string sum_string = strs.back();
        strs.pop_back();
        int sum_from_sum_string = 0;

        if (sum_string.size() >= 4 && sum_string.substr(0, 4) == "sum=") {
            string sum_str = sum_string.substr(4);  // size of "sum=" is 4
            for(int k=0; k < sum_str.size() && isdigit(sum_str[k]); k++) 
                sum_from_sum_string = sum_from_sum_string * 10 + (sum_str[k] - '0');
        }
        else 
            cout << "Not strart with sum=";

        if(sum_from_sum_string!=sum-sum_string.size()) {
            cout << "sum" << sum-sum_string.size() << ' ' << sum_from_sum_string;
        }
        return strs;
    }
};