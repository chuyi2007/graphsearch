public class HeapSort {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {5, 4, 9, 16, 14, 11, 12, 8, 7, 2, 3, 1};
		heaplify(A, A.length - 1);
		int end = A.length - 1;
		print(A);
		while(end > 0){
			swap(A, 0, end);
			shiftDown(A, 0, --end);
			print(A);
		}
		print(A);
	}

	public static void print(int[] A){
		for(int val: A)
			System.out.print(val + ",");
		System.out.println();
	}
	public static void heaplify(int[] A, int n){
		//Not a full heap
		//n = (n - 2) / 2;
		for(int i = n; i >= 0; --i)
			shiftDown(A, i, n);
	}
	
	public static void shiftDown(int[] A, int root, int end){
		while(root * 2 + 1 <= end){
			int child = root * 2 + 1;
			int swap = root;
			if(A[swap] < A[child]){
				swap = child;
			}
			
			if(child + 1 <= end && A[swap] < A[child + 1]){
				swap = child + 1;
			}
			
			if(swap != root){
				swap(A, swap, root);
				root = swap;
			}
			else
				break;
		}
	}
	
	public static void swap(int[] A, int i, int j){
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
}