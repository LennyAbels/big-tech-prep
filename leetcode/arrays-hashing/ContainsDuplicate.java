import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> vistos = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (vistos.contains(nums[i])) {
                return true;
            }
            vistos.add(nums[i]);
        }
        return false;
    }
}
