package code.repository.dev.sort.quick;

import org.junit.Test;

public class QuickSortTest {
    private QuickSort quickSort = new QuickSort();

    @Test
    public void quickSort() {
        int[] unsortedList = new int[]{2, 3, 1, 5, 67, 23, 4, 6, 9};
        quickSort.sort(unsortedList, 0, unsortedList.length - 1);

        for (int i : unsortedList) {
            System.out.println(i);
        }
    }
}
