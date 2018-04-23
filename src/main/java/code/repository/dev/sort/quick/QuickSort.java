package code.repository.dev.sort.quick;

class QuickSort {

    void sort(int[] unsortedList, int begin, int end) {
        if (begin < end) {
            int halfIndex = partition(unsortedList, begin, end);
            sort(unsortedList, begin, halfIndex - 1);
            sort(unsortedList, halfIndex + 1, end);
        }
    }

    private int partition(int[] unsortedList, int begin, int end) {
        int pivot = unsortedList[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            while (pivot > unsortedList[left] && left < right) left++;
            while (pivot < unsortedList[right] && left < right) right--;

            if (left < right) {
                int temp = unsortedList[left];
                unsortedList[left] = unsortedList[right];
                unsortedList[right] = temp;
            }
        }

        return left;
    }
}
