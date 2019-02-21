/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator.model;

import java.util.List;

import lombok.Data;

/**
 * @author YoungSeok.Kim
 */
@Data
public class Elevator {
	private Integer id;
	private Integer floor;
	private List<Call> passengers;
	private String status;
}
