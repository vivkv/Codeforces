/*
 * Author - vivkv
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class SalemSticks {
	
	private static Integer[] arr;
	private static int N; 
	private static int minCost=-1;
	
	public static int computeT() {
		int t=0;
		for(int i=0;i<N;i++) {
			int tempt = arr[i]-1;
			int tempt2 = arr[i]+1;
			if(tempt<1) {
				tempt = arr[i];
			}
			int tempcost=0;
			int tempt2cost=0;
			
			for(int j=0;j<N;j++) {
				if(!(Math.abs(tempt-arr[j])<=1)) {
					tempcost += Math.abs(tempt-arr[j])-1;
				}
				if(!(Math.abs(tempt2-arr[j])<=1)) {
					tempt2cost += Math.abs(tempt2-arr[j])-1;
				}
			}
			
			if(minCost<0) {
				if(tempt2cost<tempcost) {
					tempcost = tempt2cost;
					tempt = tempt2;
				}
				t = tempt;
				minCost = tempcost;
			}else if(minCost!=0) {
				if(tempt2cost<tempcost) {
					tempcost=tempt2cost;
					tempt = tempt2;
				}
				if(minCost>tempcost) {
					t = tempt;
					minCost = tempcost;
				}
			}
			
		}
		return t;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(reader.readLine());
		arr = new Integer[N];
		String[] temp= reader.readLine().split(" ");
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}
		temp = null;
		Arrays.sort(arr, Collections.reverseOrder());
		
		int t = computeT();
		
		System.out.print(t+" "+minCost);
	}

}