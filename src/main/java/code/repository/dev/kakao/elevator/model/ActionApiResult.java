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
public class ActionApiResult {
	private String token;
	private Integer timestamp;
	private List<Elevator> elevators;
	private Boolean is_end;
}
