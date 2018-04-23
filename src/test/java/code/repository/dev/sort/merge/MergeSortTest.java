package code.repository.dev.sort.merge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortTest {
    private MergeSort mergeSort = new MergeSort();

    @Test
    public void mergeSort() {
        List<Integer> unsortedList = Arrays.asList(2, 3, 1, 5, 67, 23, 4, 6, 9);
        List<Integer> result = mergeSort.sort(unsortedList, 0, unsortedList.size() - 1);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
