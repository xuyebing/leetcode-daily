class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, List<Integer>> restaurantMap = new HashMap<String, List<Integer>>();

        int l1 = list1.length;
        int l2 = list2.length;
        for (int i = 0; i < l1; i++) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(i);
            restaurantMap.put(list1[i], list);
        }

        int min = Integer.MAX_VALUE;
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < l2; i++) {
            List<Integer> vList = restaurantMap.get(list2[i]);
            if (vList != null) {
                int i1 = vList.get(0);
                int t = i + i1;
                if (min == t) {
                    resultList.add(list2[i]);
                } else if (min > t) {
                    min = t;
                    resultList = new ArrayList<String>();
                    resultList.add(list2[i]);
                }
            }
        }

        int size = resultList.size();
        String[] results = new String[size];
        for (int i = 0; i < size; i++) {
            results[i] = resultList.get(i);
        }
        return results;
    }
}
