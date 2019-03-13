/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;

import code.repository.dev.kakao.elevator.type.ElevatorStatus;
import lombok.Data;

/**
 * @author YoungSeok.Kim
 */
@Data
public class Elevator {
	private static int MAX_PASSENGER_COUNT = 8;

	private Integer id;
	private Integer floor;
	private CopyOnWriteArrayList<Call> passengers;
	private String status = ElevatorStatus.STOPPED.name();

	public int destinationFloor() {
		int destinationFloor = 0;
		if (StringUtils.equals(status, ElevatorStatus.UPWARD.name())) {
			for (Call passenger : passengers) {
				destinationFloor = Integer.max(destinationFloor, passenger.getEnd());
			}
		} else if (StringUtils.equals(status, ElevatorStatus.DOWNWARD.name())) {
			destinationFloor = Integer.MAX_VALUE;
			for (Call passenger : passengers) {
				destinationFloor = Integer.min(destinationFloor, passenger.getEnd());
			}
		}

		return destinationFloor;
	}

	private boolean isEnterable(int startFloor) {
		if (passengers.size() < MAX_PASSENGER_COUNT) {
			return true;
		}

		for (Call call : passengers) {
			if (StringUtils.equals(status, ElevatorStatus.UPWARD.name())) {
				if (call.getEnd() <= startFloor) {
					return true;
				}
			} else if (StringUtils.equals(status, ElevatorStatus.DOWNWARD.name())) {
				if (call.getEnd() >= startFloor) {
					return true;
				}
			}
		}

		return false;
	}
}
