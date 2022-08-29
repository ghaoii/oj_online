class Solution {
    public int[] twoSum(int[] nums, int target) {
        return a;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // testcase1
        int[] nums1 = {2,7,11,15};
        int target = 9;
        int[] ret = solution.twoSum(nums1, target);
        if(ret.length == 2 && ret[0] == 0 && ret[1] == 1) {
            System.out.println("testcase1 OK!");
        }else {
            System.out.println("testcase1 failed!");
        }
        // testcase2
        int[] nums2 = {3,2,4};
        target = 6;
        ret = solution.twoSum(nums2, target);
        if(ret.length == 2 && ret[0] == 1 && ret[1] == 2) {
            System.out.println("testcase2 OK!");
        }else {
            System.out.println("testcase2 failed!");
        }
    }
}