/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author YoungSeok.Kim
 */
public class HistogramBiggestRectangleTest {
	@Test
	public void testCalculate() {
		try {
			File inputFile = new File("C:\\Projects\\StudyProejects\\Algorithm\\src\\main\\java\\code\\repository\\dev\\backjoon\\histogram.in");
			File outputFile = new File("C:\\Projects\\StudyProejects\\Algorithm\\src\\main\\java\\code\\repository\\dev\\backjoon\\histogram.out");

			FileReader inputFileReader = new FileReader(inputFile);
			BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
			String line;
			List<String> answer = new ArrayList<>();
			while ((line = inputBufferedReader.readLine()) != null) {
				String[] splitLine = line.split(" ");
				int n = Integer.parseInt(splitLine[0]);
				int[] heights = new int[n + 1];

				for (int i = 0; i < n; i++) {
					heights[i] = Integer.parseInt(splitLine[i + 1]);
				}

				answer.add(String.valueOf(calculateArea(0, n, heights)));
			}
			inputBufferedReader.close();

			FileReader outputFileReader = new FileReader(outputFile);
			BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
			int i = 0;
			while ((line = inputBufferedReader.readLine()) != null) {

				assertTrue(StringUtils.equals(line, answer.get(i)));
			}

			outputBufferedReader.close();
		} catch (Exception e) {

		}
	}

	private int calculateArea(int start, int end, int[] heights) {
		if (start >= end) {
			return heights[start];
		}

		int minHeight = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = start; i < end; i++) {
			if (minHeight > heights[i]) {
				minHeight = heights[i];
				minIndex = i;
			}
		}

		int area = minHeight * (end - start);
		int left = calculateArea(start, minIndex, heights);
		int right = calculateArea(minIndex + 1, end, heights);
		return Integer.max(area, Integer.max(left, right));
	}
}
