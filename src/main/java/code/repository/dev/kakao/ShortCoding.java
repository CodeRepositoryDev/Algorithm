/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author YoungSeok.Kim
 */
public class ShortCoding {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] splitLine = line.split("&&");

		Set<String> variables = new HashSet<>();
		Set<String> integer = new HashSet<>();

		if (splitLine.length == 1) {
			System.out.println(line);
			return;
		}

		for (String logicalFormula : splitLine) {
			if (logicalFormula.contains("==")) {
				String[] monomials = logicalFormula.split("==");
				for (String monomial : monomials) {

				}
			} else if (logicalFormula.contains("!=")) {
				String[] monomials = logicalFormula.split("!=");
			}
		}
	}
}
