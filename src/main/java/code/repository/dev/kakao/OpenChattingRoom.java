/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YoungSeok.Kim
 */
public class OpenChattingRoom {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
		String[] answer = solution(record);

		for (String ans : answer) {
			System.out.println(ans);
		}
	}

	private static String[] solution(String[] record) {
		List<Record> records = new ArrayList<>();
		List<Record> answerRecords = new ArrayList<>();
		Map<String, String> userIdMappingNickName = new HashMap<>();

		for (String recordItem : record) {
			String[] temp = recordItem.split(" ");
			switch (temp[0]) {
				case "Leave":
					records.add(new Record(temp[0], temp[1]));
					break;
				default:
					records.add(new Record(temp[0], temp[1], temp[2]));
					userIdMappingNickName.put(temp[1], temp[2]);
					break;
			}
		}

		for (Record record1 : records) {
			switch (record1.getBehavior()) {
				case "Enter":
					answerRecords.add(record1);
					break;
				case "Leave":
					answerRecords.add(record1);
					break;
				default:
					break;
			}
		}

		String[] answer = new String[answerRecords.size()];
		for (int i = 0; i < answerRecords.size(); i++) {
			Record answerRecord = answerRecords.get(i);
			switch (answerRecord.getBehavior()) {
				case "Enter":
					answer[i] = userIdMappingNickName.get(answerRecord.getUserId()) + "님이 들어왔습니다.";
					break;
				case "Leave":
					answer[i] = userIdMappingNickName.get(answerRecord.getUserId()) + "님이 나갔습니다.";
					break;
			}
		}

		return answer;
	}

	private static class Record {
		private String behavior;
		private String userId;
		private String nickName;

		public String getBehavior() {
			return behavior;
		}

		public void setBehavior(String behavior) {
			this.behavior = behavior;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		Record(String behaviorCode, String userId, String nickName) {
			this.behavior = behaviorCode;
			this.userId = userId;
			this.nickName = nickName;
		}

		Record(String behaviorCode, String userId) {
			this.behavior = behaviorCode;
			this.userId = userId;
		}

		@Override
		public String toString() {
			return behavior + " " + userId + " " + nickName;
		}
	}
}
