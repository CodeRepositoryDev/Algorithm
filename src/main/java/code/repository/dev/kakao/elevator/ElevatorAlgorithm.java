/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import code.repository.dev.kakao.elevator.model.ActionApiParameter;
import code.repository.dev.kakao.elevator.model.ActionApiResult;
import code.repository.dev.kakao.elevator.model.Call;
import code.repository.dev.kakao.elevator.model.Command;
import code.repository.dev.kakao.elevator.model.Elevator;
import code.repository.dev.kakao.elevator.model.OnCallApiResult;
import code.repository.dev.kakao.elevator.model.StartApiResult;
import code.repository.dev.kakao.elevator.type.CommandCode;
import code.repository.dev.kakao.elevator.type.ElevatorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author YoungSeok.Kim
 */
public class ElevatorAlgorithm {
	private static HttpClient httpClient = HttpClients.createMinimal();

	public static void main(String[] args) {
		try {
			StartApiResult startApiResult = callStartApi();
			boolean isEnd = false;
			Map<Integer, Elevator> pastElevators = new HashMap<>();
			System.out.println(startApiResult.getToken());
			String token = startApiResult.getToken();

			while (!isEnd) {
				OnCallApiResult onCallApiResult = callOnCallApi(token);
				List<Elevator> currentElevators = onCallApiResult.getElevators();
				CopyOnWriteArrayList<Call> currentCalls = onCallApiResult.getCalls();
				Map<Integer, Command> commandMap = new HashMap<>();

				for (Elevator elevator : currentElevators) {
					Command command = new Command();
					command.setElevator_id(elevator.getId());
					Elevator pastElevator = pastElevators.get(elevator.getId());
					switch (ElevatorStatus.valueOf(elevator.getStatus())) {
						case STOPPED:
							if (Objects.isNull(pastElevator)) {
								if (CollectionUtils.isNotEmpty(currentCalls)) {
									Call nearestCall = getNearestCall(currentCalls, elevator);
									if (elevator.getFloor() < nearestCall.getStart()) {
										command.setCommand(CommandCode.UP.name());
									} else if (elevator.getFloor().equals(nearestCall.getStart())) {
										command.setCommand(CommandCode.OPEN.name());
									} else {
										command.setCommand(CommandCode.DOWN.name());
									}
								} else {
									command.setCommand(CommandCode.STOP.name());
								}
								break;
							}
							switch (ElevatorStatus.valueOf(pastElevator.getStatus())) {
								case STOPPED:
									if (CollectionUtils.isNotEmpty(currentCalls)) {
										Call nearestCall = getNearestCall(currentCalls, elevator);
										if (elevator.getFloor() < nearestCall.getStart()) {
											command.setCommand(CommandCode.UP.name());
										} else if (elevator.getFloor().equals(nearestCall.getStart())) {
											command.setCommand(CommandCode.OPEN.name());
										} else {
											command.setCommand(CommandCode.DOWN.name());
										}
									} else {
										command.setCommand(CommandCode.STOP.name());
									}
									break;
								case OPENED:
									if (CollectionUtils.isEmpty(elevator.getPassengers())) {
										if (CollectionUtils.isNotEmpty(currentCalls)) {
											Call nearestCall = getNearestCall(currentCalls, elevator);

											if (elevator.getFloor() < nearestCall.getStart()) {
												command.setCommand(CommandCode.UP.name());
											} else if (elevator.getFloor().equals(nearestCall.getStart())) {
												command.setCommand(CommandCode.OPEN.name());
											} else {
												command.setCommand(CommandCode.DOWN.name());
											}
										} else {
											command.setCommand(CommandCode.STOP.name());
										}
									} else {
										Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

										if (elevator.getFloor() < nearestPassenger.getEnd()) {
											command.setCommand(CommandCode.UP.name());
										} else if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
											command.setCommand(CommandCode.STOP.name());
										} else {
											command.setCommand(CommandCode.DOWN.name());
										}
									}

									break;
								case UPWARD:
									if (CollectionUtils.isEmpty(elevator.getPassengers())) {
										if (CollectionUtils.isNotEmpty(currentCalls)) {
											Call nearestCall = getNearestCall(currentCalls, elevator);
											if (elevator.getFloor() < nearestCall.getStart()) {
												command.setCommand(CommandCode.UP.name());
											} else if (elevator.getFloor().equals(nearestCall.getStart())) {
												command.setCommand(CommandCode.OPEN.name());
											} else {
												command.setCommand(CommandCode.DOWN.name());
											}
										} else {
											command.setCommand(CommandCode.STOP.name());
										}
									} else {
										Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

										if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
											command.setCommand(CommandCode.OPEN.name());
										} else {
											if (CollectionUtils.isNotEmpty(currentCalls)) {
												Call nearestCall = getNearestCall(currentCalls, elevator);

												if (elevator.getFloor().equals(nearestCall.getStart())) {
													command.setCommand(CommandCode.OPEN.name());
												} else {
													command.setCommand(CommandCode.UP.name());
												}
											} else {
												command.setCommand(CommandCode.UP.name());
											}
										}
									}

									break;
								case DOWNWARD:
									if (CollectionUtils.isEmpty(elevator.getPassengers())) {
										if (CollectionUtils.isNotEmpty(currentCalls)) {
											Call nearestCall = getNearestCall(currentCalls, elevator);
											if (elevator.getFloor() < nearestCall.getStart()) {
												command.setCommand(CommandCode.UP.name());
											} else if (elevator.getFloor().equals(nearestCall.getStart())) {
												command.setCommand(CommandCode.OPEN.name());
											} else {
												command.setCommand(CommandCode.DOWN.name());
											}
										} else {
											command.setCommand(CommandCode.STOP.name());
										}
									} else {
										Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

										if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
											command.setCommand(CommandCode.OPEN.name());
										} else {
											if (CollectionUtils.isNotEmpty(currentCalls)) {
												Call nearestCall = getNearestCall(currentCalls, elevator);

												if (elevator.getFloor().equals(nearestCall.getStart())) {
													command.setCommand(CommandCode.OPEN.name());
												} else {
													command.setCommand(CommandCode.DOWN.name());
												}
											} else {
												command.setCommand(CommandCode.DOWN.name());
											}
										}
									}
									break;
							}
							break;
						case OPENED:
							if (Objects.isNull(pastElevator)) {
								if (CollectionUtils.isNotEmpty(currentCalls)) {
									Call nearestCall = getNearestCall(currentCalls, elevator);
									if (elevator.getFloor() < nearestCall.getStart()) {
										command.setCommand(CommandCode.UP.name());
									} else if (elevator.getFloor().equals(nearestCall.getStart())) {
										command.setCommand(CommandCode.OPEN.name());
									} else {
										command.setCommand(CommandCode.DOWN.name());
									}
								} else {
									command.setCommand(CommandCode.CLOSE.name());
								}
								break;
							}

							switch (ElevatorStatus.valueOf(pastElevator.getStatus())) {
								case STOPPED:
									if (CollectionUtils.isEmpty(elevator.getPassengers())) {
										if (CollectionUtils.isNotEmpty(currentCalls)) {
											Call nearestCall = getNearestCall(currentCalls, elevator);

											if (elevator.getFloor() < nearestCall.getStart()) {
												command.setCommand(CommandCode.CLOSE.name());
											} else if (elevator.getFloor().equals(nearestCall.getStart())) {
												if (elevator.getPassengers().size() >= 8) {
													command.setCommand(CommandCode.CLOSE.name());
													break;
												}

												command.setCommand(CommandCode.ENTER.name());
												command.addCallId(nearestCall.getId());

												for (Call call : currentCalls) {
													if (nearestCall.getId().equals(call.getId())) {
														currentCalls.remove(call);
													}

													if(elevator.getPassengers().size() >= 8){
														command.setCommand(CommandCode.CLOSE.name());
														break;
													}

													if (!nearestCall.getId().equals(call.getId()) && elevator.getFloor().equals(call.getStart())) {
														command.addCallId(call.getId());
														currentCalls.remove(call);
													}
												}
											} else {
												command.setCommand(CommandCode.CLOSE.name());
											}
										} else {
											command.setCommand(CommandCode.CLOSE.name());
										}
									} else {
										Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

										if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
											command.setCommand(CommandCode.EXIT.name());
											command.addCallId(nearestPassenger.getId());
											int indexNearestPassenger = elevator.getPassengers().indexOf(nearestPassenger);
											elevator.getPassengers().remove(indexNearestPassenger);
											for (Call passenger : elevator.getPassengers()) {
												if (elevator.getFloor().equals(passenger.getEnd())) {
													command.addCallId(passenger.getId());
													elevator.getPassengers().remove(passenger);
												}
											}
										} else {
											if (CollectionUtils.isNotEmpty(currentCalls)) {
												Call nearestCall = getNearestCall(currentCalls, elevator);

												if (elevator.getFloor() < nearestCall.getStart()) {
													command.setCommand(CommandCode.CLOSE.name());
												} else if (elevator.getFloor().equals(nearestCall.getStart())) {
													if (elevator.getPassengers().size() >= 8) {
														command.setCommand(CommandCode.CLOSE.name());
														break;
													}
													command.setCommand(CommandCode.ENTER.name());
													command.addCallId(nearestCall.getId());

													for (Call call : currentCalls) {
														if (nearestCall.getId().equals(call.getId())) {
															currentCalls.remove(call);
														}
														if(elevator.getPassengers().size() >= 8){
															command.setCommand(CommandCode.CLOSE.name());
															break;
														}
														if (!nearestCall.getId().equals(call.getId()) && elevator.getFloor().equals(call.getStart())) {
															command.addCallId(call.getId());
															currentCalls.remove(call);
														}
													}
												} else {
													command.setCommand(CommandCode.CLOSE.name());
												}
											} else {
												command.setCommand(CommandCode.CLOSE.name());
											}
										}
									}
									break;
								case OPENED:
									if (CollectionUtils.isEmpty(elevator.getPassengers())) {
										if (CollectionUtils.isNotEmpty(currentCalls)) {
											Call nearestCall = getNearestCall(currentCalls, elevator);

											if (elevator.getFloor() < nearestCall.getStart()) {
												command.setCommand(CommandCode.CLOSE.name());
											} else if (elevator.getFloor().equals(nearestCall.getStart())) {
												if(elevator.getPassengers().size() >= 8){
													command.setCommand(CommandCode.CLOSE.name());
													break;
												}
												command.setCommand(CommandCode.ENTER.name());
												command.addCallId(nearestCall.getId());

												for (Call call : currentCalls) {
													if (nearestCall.getId().equals(call.getId())) {
														currentCalls.remove(call);
													}
													if(elevator.getPassengers().size() >= 8){
														command.setCommand(CommandCode.CLOSE.name());
														break;
													}
													if (!nearestCall.getId().equals(call.getId()) && elevator.getFloor().equals(call.getStart())) {
														command.addCallId(call.getId());
														currentCalls.remove(call);
													}
												}
											} else {
												command.setCommand(CommandCode.CLOSE.name());
											}
										} else {
											command.setCommand(CommandCode.CLOSE.name());
										}
									} else {
										Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

										if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
											command.setCommand(CommandCode.EXIT.name());
											command.addCallId(nearestPassenger.getId());
											int indexNearestPassenger = elevator.getPassengers().indexOf(nearestPassenger);
											elevator.getPassengers().remove(indexNearestPassenger);
											for (Call passenger : elevator.getPassengers()) {
												if (elevator.getFloor().equals(passenger.getEnd())) {
													command.addCallId(passenger.getId());
													int indexPassenger = elevator.getPassengers().indexOf(nearestPassenger);
													elevator.getPassengers().remove(indexPassenger);
												}
											}
										} else {
											if (CollectionUtils.isNotEmpty(currentCalls)) {
												Call nearestCall = getNearestCall(currentCalls, elevator);

												if (elevator.getFloor() < nearestCall.getStart()) {
													command.setCommand(CommandCode.CLOSE.name());
												} else if (elevator.getFloor().equals(nearestCall.getStart())) {
													if(elevator.getPassengers().size() >= 8){
														command.setCommand(CommandCode.CLOSE.name());
														break;
													}

													command.setCommand(CommandCode.ENTER.name());
													command.addCallId(nearestCall.getId());

													for (Call call : currentCalls) {
														if (nearestCall.getId().equals(call.getId())) {
															currentCalls.remove(call);
														}
														if(elevator.getPassengers().size() >= 8){
															command.setCommand(CommandCode.CLOSE.name());
															break;
														}
														if (!nearestCall.getId().equals(call.getId()) && elevator.getFloor().equals(call.getStart())) {
															command.addCallId(call.getId());
															currentCalls.remove(call);
														}
													}
												} else {
													command.setCommand(CommandCode.CLOSE.name());
												}
											} else {
												command.setCommand(CommandCode.CLOSE.name());
											}
										}
									}
									break;
							}
							break;
						case UPWARD:
							if (CollectionUtils.isEmpty(elevator.getPassengers())) {
								if (CollectionUtils.isNotEmpty(currentCalls)) {
									Call nearestCall = getNearestCall(currentCalls, elevator);

									if (elevator.getFloor() < nearestCall.getStart()) {
										command.setCommand(CommandCode.UP.name());
									} else if (elevator.getFloor().equals(nearestCall.getStart())) {
										command.setCommand(CommandCode.STOP.name());
									} else {
										command.setCommand(CommandCode.UP.name());
									}
								} else {
									command.setCommand(CommandCode.STOP.name());
								}
							} else {
								if(elevator.getPassengers().size() >= 8){
									command.setCommand(CommandCode.UP.name());
									break;
								}

								Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

								if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
									command.setCommand(CommandCode.STOP.name());
								} else {
									if (CollectionUtils.isNotEmpty(currentCalls)) {
										Call nearestCall = getNearestCall(currentCalls, elevator);

										if (elevator.getFloor() < nearestCall.getStart()) {
											command.setCommand(CommandCode.UP.name());
										} else if (elevator.getFloor().equals(nearestCall.getStart())) {
											command.setCommand(CommandCode.STOP.name());
										} else {
											command.setCommand(CommandCode.UP.name());
										}
									} else {
										command.setCommand(CommandCode.UP.name());
									}
								}
							}
							break;
						case DOWNWARD:
							if (CollectionUtils.isEmpty(elevator.getPassengers())) {
								if (CollectionUtils.isNotEmpty(currentCalls)) {
									Call nearestCall = getNearestCall(currentCalls, elevator);

									if (elevator.getFloor() < nearestCall.getStart()) {
										command.setCommand(CommandCode.DOWN.name());
									} else if (elevator.getFloor().equals(nearestCall.getStart())) {
										command.setCommand(CommandCode.STOP.name());
									} else {
										command.setCommand(CommandCode.DOWN.name());
									}
								} else {
									command.setCommand(CommandCode.STOP.name());
								}
							} else {
								if(elevator.getPassengers().size() >= 8){
									command.setCommand(CommandCode.DOWN.name());
									break;
								}

								Call nearestPassenger = getNearestCall(elevator.getPassengers(), elevator);

								if (elevator.getFloor().equals(nearestPassenger.getEnd())) {
									command.setCommand(CommandCode.STOP.name());
								} else {
									if (CollectionUtils.isNotEmpty(currentCalls)) {
										Call nearestCall = getNearestCall(currentCalls, elevator);

										if (elevator.getFloor() < nearestCall.getStart()) {
											command.setCommand(CommandCode.DOWN.name());
										} else if (elevator.getFloor().equals(nearestCall.getStart())) {
											command.setCommand(CommandCode.STOP.name());
										} else {
											command.setCommand(CommandCode.DOWN.name());
										}
									} else {
										if (elevator.getFloor() < nearestPassenger.getStart()) {
											command.setCommand(CommandCode.DOWN.name());
										} else if (elevator.getFloor().equals(nearestPassenger.getStart())) {
											command.setCommand(CommandCode.STOP.name());
										} else {
											command.setCommand(CommandCode.DOWN.name());
										}
									}
								}
							}
							break;
					}
					commandMap.put(elevator.getId(), command);
				}

				ActionApiResult actionApiResult = callActionApi(token, new ArrayList<>(commandMap.values()));
				pastElevators = currentElevators.stream().collect(Collectors.toMap(Elevator::getId, Function.identity()));
				isEnd = actionApiResult.getIs_end();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static StartApiResult callStartApi() throws IOException {
		HttpResponse response = httpClient.execute(getStartApiUrl("seok", "1", "4"));
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(EntityUtils.toString(response.getEntity()), StartApiResult.class);
	}

	private static OnCallApiResult callOnCallApi(String token) throws IOException {
		HttpResponse response = httpClient.execute(getOnCallsApiUrl(token));
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(EntityUtils.toString(response.getEntity()), OnCallApiResult.class);
	}

	private static ActionApiResult callActionApi(String token, List<Command> commands) throws IOException {
		HttpResponse response = httpClient.execute(getActionUrl(token, commands));
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(EntityUtils.toString(response.getEntity()), ActionApiResult.class);
	}

	private static HttpPost getStartApiUrl(String user, String problem, String count) {
		return new HttpPost("http://10.105.186.205:8000/start/" + user + "/" + problem + "/" + count);
	}

	private static HttpGet getOnCallsApiUrl(String token) {
		HttpGet request = new HttpGet("http://10.105.186.205:8000/oncalls");
		request.setHeader("X-Auth-Token", token);
		return request;
	}

	private static HttpPost getActionUrl(String token, List<Command> commands) throws UnsupportedEncodingException {
		HttpPost request = new HttpPost("http://10.105.186.205:8000/action");
		request.setHeader("X-Auth-Token", token);
		request.addHeader("content-type", "application/json");
		ActionApiParameter actionApiParameter = new ActionApiParameter();
		actionApiParameter.setCommands(commands);
		request.setEntity(new StringEntity(new Gson().toJson(actionApiParameter)));
//		System.out.println("gson" + new Gson().toJson(actionApiParameter));
		return request;
	}

	private static Call getNearestCall(List<Call> callList, Elevator elevator) {
		Call nearestCall = null;
		for (Call currentCall : callList) {
			if (Objects.isNull(nearestCall)) {
				nearestCall = currentCall;
			}

			if (Math.abs(currentCall.getStart() - elevator.getFloor()) < Math.abs(nearestCall.getStart() - elevator.getFloor())) {
				nearestCall = currentCall;
			}
		}
		return nearestCall;
	}
}
