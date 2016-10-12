package android.zlb.java;

public class QuickSort {
	public static void main(String[] args) {
		int stru[]={49,38,65,97,13,27};
		System.out.println("快速排序之前：");
		for(int k=0;k<stru.length;k++){
			System.out.print(stru[k]+"  ");
		}

		quicksort(stru,0,stru.length-1);
		System.out.println("\n快速排序之后：");
		for(int k=0;k<stru.length;k++){
			System.out.print(stru[k]+"  ");
		}
	}

	public static void quicksort(int stru[], int left, int right) {
		if (left < right) {
			int i = left, j = right;
			int X = stru[i];//关键数据
			int n = i;//关键数据的位置
			while (i < j) {
				while (X < stru[j] && j > i) {
					j--;
				}
				stru[n] = stru[j];
				n = j;
				while (X > stru[i] && i < j) {
					i++;
				}

				stru[n] = stru[i];
				n = i;
			}
			stru[i] = X;

			quicksort(stru, left, j - 1); // 对左边进行递回
			quicksort(stru, j + 1, right); // 对右边进行递回
		}
	}

    public static void quick1(int[] a) {

    }

    public static void quick2(int a[], int left, int right) {
        if(left < right) {
            int i = left;
            int j = right;
            int n = i;//记录关键数据的位置
            int data = a[n];//关键数据
            while(i < j) {
                while(data < a[j] && i < j) {//从右往左一直找到比关键数据小的那个值的位置j
                    j--;
                }
                a[n] = a[j];
                n = j;//关键数据的位置变成了j
                while(data > a[i] && i < j) {//从左往右一直找到比关键数据大的那个值的位置i
                    i++;
                }
                a[n] = a[i];
                n = i;
            }
            a[i] = data;//给关键数据找到一个新位置i

            quick2(a, left, j - 1);
            quick2(a, j + 1, right);

        }
    }

    private void quick3(int a[], int left, int right) {
        int i = left;
        int j = right;
        int n = i;

        int data = a[n];
        while(i < j) {
            while(data < a[j] && i < j) {
                j--;
            }
            a[n] = a[j];
            n = j;

            while(data > a[i] && i < j) {
                i++;
            }

            a[n] = a[i];
            n = i;
        }
        a[i] = data;
        quick3(a, left, j - 1);
        quick3(a, j + 1, right);
    }


    private void quick4(int[] a, int left, int right) {
        int i = left;
        int j = right;
        int n = i;
        int data = a[n];

        while(i < j) {
            while(data < a[j] && i < j) {
                j--;
            }
            a[n] = a[j];
            n = j;

            while(data > a[i] && i < j) {
                i++;
            }

            a[n] = a[i];
            n = i;
        }
        a[i] = data;
        quick4(a, left, j - 1);
        quick4(a, j + 1, right);
    }

















}
/**
 * 快速排序（Quicksort）是冒泡排序的一种改进。是不稳定的排序算法，就是说，多个相同的值的相对位置也许会在算法结束时产生变动。 
  设要排序的数组是A[0]……A[N-1]，首先任意选取一个数据（通常选用第一个数据）作为关键数据，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。
  一趟快速排序的算法是：
　　1）设置两个变量I、J，排序开始的时候：I=0，J=N-1；
　　2）以第一个数组元素作为关键数据，赋值给key，即 key=A[0]；
　　3）从J开始向前搜索，即由后开始向前搜索（J=J-1），找到第一个小于key的值A[J]，并与A[I]交换；



　　4）从I开始向后搜索，即由前开始向后搜索（I=I+1），找到第一个大于key的A[I]，与A[J]交换；
　　5）重复第3、4、5步，直到 I=J； (3,4步是在程序中没找到时候j=j-1，i=i+1，直至找到为止。找到并交换的时候i， j指针位置不变。另外当i=j这过程一定正好是i+或j-完成的最后另循环结束)
　　例如：待排序的数组A的值分别是：（初始关键数据：X=49） 注意关键X永远不变，永远是和X进行比较，无论在什么位子，最后的目的就是把X放在中间，小的放前面大的放后面。
　　A[0] 、 A[1]、 A[2]、 A[3]、 A[4]、 A[5]、 A[6]：
　　49 38 65 97 76 13 27
　　进行第一次交换后： 27 38 65 97 76 13 49
　　( 按照算法的第三步从后面开始找)
　　进行第二次交换后： 27 38 49 97 76 13 65
　　( 按照算法的第四步从前面开始找>X的值，65>49,两者交换，此时：I=3 )
　　进行第三次交换后： 27 38 13 97 76 49 65
　　( 按照算法的第五步将又一次执行算法的第三步从后开始找
　　进行第四次交换后： 27 38 13 49 76 97 65
　　( 按照算法的第四步从前面开始找大于X的值，97>49,两者交换，此时：I=4,J=6 )
　　此时再执行第三步的时候就发现I=J，从而结束一趟快速排序，那么经过一趟快速排序之后的结果是：27 38 13 49 76 97 65，即所以大于49的数全部在49的后面，所以小于49的数全部在49的前面。 
 * 
 * 
 * 
 * 6,3,20,5,4,16,9,6,2,7,11
 * 3,5,4,2, 6 ,20,16,9,7,11
 * 
 * 2, 3, 5,4 6, 16,9,7,11,20
 * 
 * 
 */
