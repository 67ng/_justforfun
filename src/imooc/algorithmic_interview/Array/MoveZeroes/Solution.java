package imooc.algorithmic_interview.Array.MoveZeroes;

import java.util.ArrayList;

/**
 * Description:
 * 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/description/
 * 时间复杂度: O(n)
 * 空间复杂度: O(n)
 *
 * @date: 2019/1/21 10:11
 */
public class Solution {
    //solution1:时间复杂度: O(n),空间复杂度: O(n)
  /**  public void moveZeroes(int[] nums) {
        ArrayList<Integer> nonZeroElements = new ArrayList<Integer>();

        // 将vec中所有非0元素放入nonZeroElements中
        for(int i = 0 ; i < nums.length ; i ++)
            if(nums[i] != 0)
                nonZeroElements.add(nums[i]);
        // 将nonZeroElements中的所有元素依次放入到nums开始的位置
        for(int i = 0 ; i < nonZeroElements.size() ; i ++)
            nums[i] = nonZeroElements.get(i);
        // 将nums剩余的位置放置为0
        for(int i = nonZeroElements.size() ; i < nums.length ; i ++)
            nums[i] = 0;
    }*/

  //solution2:时间复杂度: O(n),空间复杂度: O(1)
 /** public void moveZeroes(int[] nums) {
      int k = 0; // nums中, [0...k)的元素均为非0元素

      // 遍历到第i个元素后,保证[0...i]中所有非0元素
      // 都按照顺序排列在[0...k)中
      for(int i = 0 ; i < nums.length ; i ++)
          if( nums[i] != 0 )
              nums[k++] = nums[i];

      // 将nums剩余的位置放置为0
      for(int i = k ; i < nums.length ; i ++)
          nums[i] = 0;
  }*/

    //solution3:时间复杂度: O(n),空间复杂度: O(1)
    public void moveZeroes(int[] nums) {
        int k = 0; // nums中, [0...k)的元素均为非0元素

        // 遍历到第i个元素后,保证[0...i]中所有非0元素
        // 都按照顺序排列在[0...k)中
        // 同时, [k...i] 为 0
        for(int i = 0 ; i < nums.length ; i ++)
            if(nums[i] != 0)
                if(k != i)//避免全是非零元素进行原地交换
                    swap(nums, k++, i);
                else
                    k++;
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String args[]) {
        int[] arr = {0, 1, 0, 3, 12};
        (new Solution()).moveZeroes(arr);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
