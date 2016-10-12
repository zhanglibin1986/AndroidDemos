package android.zlb.java;

public class Binary {
	public static void main(String[] args) {
		int[] a = {3, 4, 8, 12, 15, 20, 30};
		Binary b = new Binary();
		System.out.println(b.search(a, 15));
	}
	
	public int search(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int mid = (low + high) / 2;
		while(low <= high) {
			if(key < a[mid]) {
				high = mid;
			} else if(key > a[mid]){
				low = mid;
			} else {
				return mid;
			}
			mid = (low + high) / 2;
		}
		return -1;
	}

    public int search2(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = (low + high) / 2;
        while(low < high) {
            if(key == a[mid]) {
                return mid;
            } else if(key < a[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else if(key > a[mid]) {
                low = a[mid];
                mid = (low + high) / 2;
            }
        }

        return -1;
    }

    private int serach3(int[] a, int key) {
        int l = 0;
        int h = 0;
        int m = (l + h) / 2;
        while(l < h) {
            if(key < a[m]) {
                h = m;
                m = (l + h) / 2;
            } else if(key > a[m]) {
                l = m;
                m = (l + h) / 2;
            } else {
                return m;
            }
        }
        return -1;
    }

    private int search4(int[] a, int key) {
        int low = 0;
        int high = 0;
        int mid = 0;
        while(low < high) {
            if(key < a[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else if(key > a[mid]) {
                low = mid;
                mid = (low + high) / 2;
            } else {
                return mid;
            }
        }

        return -1;
    }

    private int search5(int[] a, int key) {
        int left = 0;
        int right = a.length - 1;
        int mid = (left + right) / 2;
        while(left < right)
            if(mid == key) {
                return mid;
            } else if(key < a[mid]) {
                right = mid;
                mid = (left + right) / 2;
            } else if(key > a[mid]) {
                left = mid;
                mid = (left + right) / 2;
            }
        return -1;
    }

    private int search6(int a[], int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = (low + high) / 2;

        while (low < high) {
            if(a[mid] == key) {
                return mid;
            } else if(a[mid] < key) {
                low = mid;
                mid = (low + high) / 2;
            } else if(a[mid] > key) {
                high = mid;
                mid = (low + high) / 2;
            }
        }

        return -1;
    }
}
