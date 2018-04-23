package code.repository.dev.sort.merge;

import java.util.ArrayList;
import java.util.List;

class MergeSort {

    List<Integer> sort(List<Integer> unsortedList, int begin, int end) {
        if (begin >= end) {
            List<Integer> temp = new ArrayList<>();
            temp.add(unsortedList.get(begin));
            return temp;
        }

        int halfIndex = (begin + end) / 2;
        List<Integer> left = sort(unsortedList, begin, halfIndex);
        List<Integer> right = sort(unsortedList, halfIndex + 1, end);

        int left_index = 0;
        int right_index = 0;
        List<Integer> result = new ArrayList<>();

        while (left_index < left.size() && right_index < right.size()) {
            if (left.get(left_index) < right.get(right_index)) {
                result.add(left.get(left_index));
                left_index++;
            } else {
                result.add(right.get(right_index));
                right_index++;
            }
        }

        if (left_index < left.size()) {
            result.addAll(left.subList(left_index, left.size()));
        }

        if (right_index < right.size()) {
            result.addAll(right.subList(right_index, right.size()));
        }

        return result;
    }
}
