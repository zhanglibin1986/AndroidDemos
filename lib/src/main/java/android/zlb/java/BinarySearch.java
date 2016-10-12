package android.zlb.java;


class BinarySearch {
	private String[] data;

	public BinarySearch(String[] data) {
		this.data = data;
	}

	public int search(String key) {
		int low;
		int high;
		int mid;
		if (data == null)
			return -1;
		low = 0;
		high = data.length - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			System.out.println("mid " + mid + " mid value:" + data[mid]);// /
			if (key.compareTo(data[mid]) < 0) {
				high = mid - 1;
			} else if (key.compareTo(data[mid]) > 0) {
				low = mid + 1;
			} else if (key.compareTo(data[mid]) == 0) {
				return mid;
			}
		}
		return -1;
	}

	private int doSearchRecursively(int low, int high, String key) {
		int mid;
		int result;
		if (low <= high) {
			mid = (low + high) / 2;
			result = key.compareTo(data[mid]);
			System.out.println("mid " + mid + " mid value:" + data[mid]);// /
			if (result < 0) {
				return doSearchRecursively(low, mid - 1, key);
			} else if (result > 0) {
				return doSearchRecursively(mid + 1, high, key);
			} else if (result == 0) {
				return mid;
			}
		}
		return -1;
	}

	public int searchRecursively(String key) {
		if (data == null)
			return -1;
		return doSearchRecursively(0, data.length - 1, key);
	}

	public static void main(String[] args) {
		// BinarySearch<Integer> binSearch = new BinarySearch<Integer>(data);
		// System.out.println("Key index:" + binSearch.search(33) );
		// System.out.println("Key index:" + binSearch.searchRecursively(3));
		String[] dataStr = new String[]{ "A", "C", "F", "J", "L", "N", "T" };
		BinarySearch binSearch = new BinarySearch(dataStr);
		System.out.println("Key index:" + binSearch.search("A"));
	}

    private int search5(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = (low + high) / 2;
        while(low < high) {
            if(a[mid] == key) {
                return mid;
            } else if(a[mid] > key) {
                high = mid;
                mid = (low + high) / 2;
            } else if(a[mid] < key) {
                low = mid;
                mid = (low + high) / 2;
            }
        }
        return -1;
    }

    public void quickSort(int start, int end, int[] a) {
        int i = start;
        int j = end;
        int n = i;
        int d = a[n];
        while(i < j) {
            while(n < j && d > a[j]) {
                j--;
            }
            a[n] = a[j];
            n = j;
            while(n > i && d < a[i]) {
                i++;
            }

            a[n] = a[i];
            n = i;
        }
        a[n] = d;
        quickSort(start, j - 1, a);
        quickSort(j + 1, end, a);
    }
}
