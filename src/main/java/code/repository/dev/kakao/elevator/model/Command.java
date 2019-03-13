/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author YoungSeok.Kim
 */
@Data
public class Command {
	private Integer elevator_id;
	private String command;
	private List<Integer> call_ids = new ArrayList<>();

	public boolean addCallId(Integer callId){
		return call_ids.add(callId);
	}
}
