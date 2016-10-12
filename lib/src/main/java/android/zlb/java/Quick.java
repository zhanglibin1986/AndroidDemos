package android.zlb.java;

public class Quick {
	public static void main(String[] args) {
		int stru[]={49,38,65,97,13,27};
		System.out.println("快速排序之前：");
		for(int k=0;k<stru.length;k++){
			System.out.print(stru[k]+"  ");
		}
		Quick q = new Quick();
		q.sort(stru, 0, stru.length - 1);
		
		System.out.println("\n快速排序之后：");
		for(int k=0;k<stru.length;k++){
			System.out.print(stru[k]+"  ");
		}
	}
	
	public void sort(int[] a, int left, int right) {
		if(left < right) {
			int low = left;
			int high = right;
			int X = a[low];
			while(low < high) {
				while(X < a[high] && low < high) {
					high --;
				}
				
				a[low] ^= a[high];
				a[high] ^= a[low];
				a[low] ^= a[high];
				
				while(X > a[low] && low < high) {
					low ++;
				}
				
				a[low] ^= a[high];
				a[high] ^= a[low];
				a[low] ^= a[high];
			}
			a[low] = X;
			sort(a, left, high - 1);
			sort(a, high + 1, right);
		}
	}


    private void quickSort5(int[] a, int left, int right) {
        int i = left;
        int j = right;
        int n = i;
        int data = a[i];

        while(left < right) {
            while(i < j && data < a[j]) {
                j--;
            }
            a[n] = a[j];
            n = j;

            while(i < j && data > a[i]) {
                i++;
            }

            a[n] = a[i];
            n = i;
        }

        a[i] = data;
        quickSort5(a, left, j - 1);
        quickSort5(a, j + 1, right);
    }

    private void quick(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int n = i;
        int data = array[n];

        while(left < right) {
            while(i < j && data < array[j]) {
                j--;
            }
            array[n] = array[j];
            n = j;

            while(i < j && data > array[i]) {
                i++;
            }
            array[n] = array[i];
            n = i;
        }
        array[i] = data;
        quick(array, left, j - 1);
        quick(array, j + 1, right);
    }

    private int binarySerach(int[] a, int key) {
        int i = 0;
        int j = a.length - 1;
        int mid = (i + j) / 2;
        while(i < j) {
            if(key > a[mid]) {
                i = mid;
                mid = (i + j) / 2;
            } else if(key < a[mid]) {
                j = mid;
                mid = (i + j) / 2;
            } else if(key == a[mid]){
                return mid;
            }
        }
        return -1;
    }
}
