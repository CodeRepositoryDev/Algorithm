package code.repository.dev.tree;

import java.util.Objects;

import lombok.Data;

@Data
public class SegmentTreeNode {
	private int startIndex;
	private int endIndex;
	private int min;
	private int max;
	private SegmentTreeNode left;
	private SegmentTreeNode right;

	public int initMin(int targetArray[], int start, int end) {
		this.startIndex = start;
		this.endIndex = end;

		if (start > end) {
			return Integer.MAX_VALUE;
		}

		if (start == end) {
			return this.min = targetArray[start];
		} else {
			if (Objects.isNull(this.left)) {
				this.left = new SegmentTreeNode();
			}
			if (Objects.isNull(this.right)) {
				this.right = new SegmentTreeNode();
			}

			return this.min = Math.min(this.left.initMin(targetArray, start, (start + end) / 2), this.right.initMin(targetArray, (start + end) / 2 + 1, end));
		}
	}

	public int initMax(int targetArray[], int start, int end) {
		this.startIndex = start;
		this.endIndex = end;

		if (start > end) {
			return Integer.MIN_VALUE;
		}

		if (start == end) {
			return this.max = targetArray[start];
		} else {
			if (Objects.isNull(this.left)) {
				this.left = new SegmentTreeNode();
			}
			if (Objects.isNull(this.right)) {
				this.right = new SegmentTreeNode();
			}

			return this.max = Math.max(this.left.initMax(targetArray, start, (start + end) / 2), this.right.initMax(targetArray, (start + end) / 2 + 1, end));
		}
	}

	public int searchMin(int start, int end) {
		if (this.startIndex >= start && this.endIndex <= end) {
			return this.min;
		} else if ((this.startIndex > end && this.endIndex > end) || (this.startIndex < start && this.endIndex < start)) {
			return Integer.MAX_VALUE;
		} else {
			if (this.startIndex == this.endIndex) {
				return this.min;
			}

			return Math.min(this.left.searchMin(start, end), this.right.searchMin(start, end));
		}
	}

	public int searchMax(int start, int end) {
		if (this.startIndex >= start && this.endIndex <= end) {
			return this.max;
		} else if ((this.startIndex > end && this.endIndex > end) || (this.startIndex < start && this.endIndex < start)) {
			return Integer.MIN_VALUE;
		} else {
			if (this.startIndex == this.endIndex) {
				return this.max;
			}

			return Math.max(this.left.searchMax(start, end), this.right.searchMax(start, end));
		}
	}
}
