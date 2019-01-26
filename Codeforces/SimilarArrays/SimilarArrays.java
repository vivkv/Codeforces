import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimilarArrays {
	
	private static int n,m;
	private static int[][] arr;
	private static int[][] pairs;
	private static byte[] presence;
	private static int adjust=0;
	private static int[] arr2;
	private static int culprit = 0;
	private static int culprit2 = 0;

	static String compute() {
		
		String possible = "NO";
		int fstSwapIndex=0,lstSwapIndex=0;
		for(int i=0;i<n;i++) {
			if(arr[i][1]<n-1) {
				culprit = arr[i][0];
				break;
			}
		}
		if(culprit>0) {
			presence[culprit] = 1;
			int count = 0;
			for(int i=0;i<m && count<=arr[culprit-1][1];i++) {
				if(pairs[i][0]==culprit) {
					presence[pairs[i][1]] = 1;
					count++;
				}else if(pairs[i][1]==culprit) {
					presence[pairs[i][0]] = 1;
					count++;
				}
			}
			for(int i=1;i<n+1;i++) {
				if(presence[i]==0) {
					culprit2 = i;
					break;
				}
			}
			
			if(culprit<culprit2) {
				fstSwapIndex = culprit-1;
				lstSwapIndex = culprit2-1;
			}else {
				fstSwapIndex = culprit2-1;
				lstSwapIndex = culprit-1;
			}
			
			int gap = lstSwapIndex-fstSwapIndex;
			int i = fstSwapIndex;
			int j = lstSwapIndex;
			while((j-i)>2) {
				int temp = arr[lstSwapIndex][0];
				arr[lstSwapIndex][0] = arr[j-1][0];
				arr[j-1][0] = temp;
				temp = arr[fstSwapIndex][0];
				arr[fstSwapIndex][0] = arr[i+1][0];
				arr[i+1][0] = temp;
				j--;
				i++;
			}
			if(gap%2==0) {
				int temp = arr[lstSwapIndex][0];
				arr[lstSwapIndex][0] = arr[j-1][0];
				arr[j-1][0] = temp;
			}
			arr2 = new int[n];
			for(int k=0;k<n;k++) {
				if(k==lstSwapIndex) {
					arr2[k] = arr[fstSwapIndex][0];
				}else {
					arr2[k] = arr[k][0];
				}
			}
			adjust = 1;
			possible="YES";
		}
		
		return possible;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] nM = reader.readLine().split(" ");
		n = Integer.parseInt(nM[0]);
		m = Integer.parseInt(nM[1]);
		arr = new int[n][2];
		pairs = new int[m][2];
		presence = new byte[n+1];
		
		for(int i=1;i<n+1;i++) {
			arr[i-1][0]= i;
		}	
		
		for(int i=0;i<m;i++) {
			String[] XY = reader.readLine().split(" ");
			int X = Integer.parseInt(XY[0]);
			int Y = Integer.parseInt(XY[1]);
			arr[X-1][1] += 1;
			arr[Y-1][1] += 1;
			pairs[i][0] = X;
			pairs[i][1] = Y;
		}		
		String output = compute();
		
		if(output.equalsIgnoreCase("YES")) {
			System.out.println(output);
			if(adjust==0) {
				for(int i=0;i<n;i++) {
					System.out.print(i+1+" ");
				}
				System.out.println(); 
				for(int i=0;i<n;i++) {
						System.out.print(arr[i][0]+" ");
				}
			}
			else{
				for(int i=0;i<n;i++) {
					System.out.print(arr[i][0]+" ");
				}
				System.out.println();
				for(int i=0;i<n;i++) {
					System.out.print(arr2[i]+" ");
				}
			}		
		}else {
			System.out.println(output);
		}
	}

}