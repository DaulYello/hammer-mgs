package com.xl.utils;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
	public static void main(String[] args) {

		//这种算法需要知道数组的取值范围
		//从数组中得到第二小的数
		/*Integer[] value = {9,12,11,0,13,5,0,7};
		int firstmin = 10000;
		int secondmin = 10000;
		for (int i=0;i<value.length;i++)
		{
			if (value[i] < firstmin) //小于最小的元素 更新1和2
			{
				secondmin = firstmin;
				firstmin = value[i];
			}
			else if (value[i] < secondmin && value[i] != firstmin) //小于倒数二的 更新2
			{
				secondmin = value[i];
			}
		}
		System.out.println("最小值："+firstmin);
		System.out.println("第二小："+secondmin);*/

		//查找第一个没有重复的数组元素

		Integer[] arrays = {12,3,12,0,7,3,4};
		int sum =0;
		/*for (int i = 0;i<array.length;i++){
			int count = 0;
			for (int j= 0 ;j<array.length;j++){
				sum++;
				if(!array[i].equals(array[j]) && i != j){
					count ++;
					continue;
				}
			}
			if(count == array.length-1){
				System.out.println("第一个没有重复的数组元素"+array[i]);
				break;
			}
		}
		System.out.println("时间复杂度n的平方"+sum);*/
		for (int i = 0; i < arrays.length; ++i)
		{

			int j = 0;
			for (; j < arrays.length; ++j)
			{
				sum++;
				if (i != j && arrays[i] == arrays[j])
				{
					break;
				}
			}
			if (j == arrays.length) //找到最后一个也没找到
			{
				System.out.println("第一个没有重复的数组元素"+arrays[i]);
				break;
			}
		}
		System.out.println("时间复杂度n的平方"+sum);
	}
}
