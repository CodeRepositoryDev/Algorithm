/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator.model;

import lombok.Data;

/**
 * @author YoungSeok.Kim
 */
@Data
public class Call {
	private Integer id;
	private Integer timestamp;
	private Integer start;
	private Integer end;
}
