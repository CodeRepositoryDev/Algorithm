/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Data;

/**
 * @author YoungSeok.Kim
 */
@Data
public class OnCallApiResult {
	private String token;
	private Integer timestamp;
	private List<Elevator> elevators;
	private CopyOnWriteArrayList<Call> calls;
	private Boolean is_end;
}
