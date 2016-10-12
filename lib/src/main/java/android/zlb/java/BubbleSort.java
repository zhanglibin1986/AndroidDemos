package android.zlb.java;


public class BubbleSort {
    public static void main(String[] args) {
        int score[] = {67, 69, 75, 87, 89, 90, 99, 100};
        for (int i = 0; i < score.length - 1; i++) {    //最多做n-1趟排序
            for (int j = 0; j < score.length - i - 1; j++) {    //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
                if (score[j] < score[j + 1]) {    //把小的值交换到后面
                    int temp = score[j];
                    score[j] = score[j + 1];
                    score[j + 1] = temp;
                }
            }
            System.out.print("第" + (i + 1) + "次排序结果：");
            for (int a = 0; a < score.length; a++) {
                System.out.print(score[a] + "\t");
            }
            System.out.println("");
        }
        System.out.println("最终排序结果：");
        for (int a = 0; a < score.length; a++) {
            System.out.print(score[a] + "\t");
        }
        swap(score, 1, 2);

        System.out.println("最终排序结果2：");
        for (int a = 0; a < score.length; a++) {
            System.out.print(score[a] + "\t");
        }

        bubbleSort(score);

        System.out.println("最终排序结果3：");
        for (int a = 0; a < score.length; a++) {
            System.out.print(score[a] + "\t");
        }

        bubbleSort2(score);
        System.out.println("最终排序结果3：");
        for (int a = 0; a < score.length; a++) {
            System.out.print(score[a] + "\t");
        }
    }

    public static int[] bubbleSort(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length - i - 1; j++) {
                if(a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
        return a;
    }

    private static int[] bubbleSort2(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length - i - 1; j++) {
                if(a[j] < a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
        return a;
    }


    public static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    private void bubbleSort3(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length - 1 - i; j++) {
                if(a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}
