class TimeMap {
    private class ValueWithTimestamp {
        private String value;
        private int timestamp;

        ValueWithTimestamp(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        String getValue() {
            return this.value;
        }

        int getTimestamp() {
            return this.timestamp;
        }
    }

    private HashMap<String, ArrayList<ValueWithTimestamp>> map;
    public TimeMap() {
        map = new HashMap<String, ArrayList<ValueWithTimestamp>>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new ValueWithTimestamp(value, timestamp));
    }

    public String get(String key, int timestamp) {
        ArrayList<ValueWithTimestamp> valueList = map.get(key);
        if(valueList == null || valueList.isEmpty()) return "";
        int n = valueList.size();
        int l = 0, r = n-1;
        while(l<=r) {
            // if <= givenTimestamp then check right side of that can exist
            int c = l + (r-l) / 2;
            if(valueList.get(c).getTimestamp() <= timestamp) {
                l = c + 1;
            }
            else 
                r = c - 1;
        }
        if(r>=0) {
            return valueList.get(r).getValue();
        }
        return "";
    }
}
