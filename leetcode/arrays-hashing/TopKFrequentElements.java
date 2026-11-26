import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : countMap.keySet()) {
            int frequency = countMap.get(num);
            buckets[frequency].add(num);
        }

        int[] result = new int[k];
        int index = 0;

        for (int freq = buckets.length - 1; freq >= 0 && index < k; freq--) {
            for (int num : buckets[freq]) {
                result[index] = num;
                index++;
                if (index == k) {
                    return result;
                }
            }
        }

        return result;
    }
}
