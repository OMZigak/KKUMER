package org.kkumulkkum.server.external.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FcmContent {

    FIRST_PREPARATION("⏰ 누군가 준비를 시작했어요 ⏰", "첫 번째 사람이 준비를 시작했어요\uD83D\uDE35\\n어플로 들어가 누군지 확인해 보세요!", "readyStatus"),
    FIRST_DEPARTURE("⏰ 누군가 이동을 시작했어요 ⏰", "첫 번째로 누군가 이동을 시작했어요\uD83D\uDE35\\n어플로 들어가 누군지 확인해 보세요!", "readyStatus"),
    FIRST_ARRIVAL("⏰ 누군가 도착 했어요 ⏰", "누군가 약속 장소에 도착했어요!\uD83D\uDE35\\n도착한 사람이 누구인지 확인해보세요!", "readyStatus"),
    ;

    private final String title;
    private final String body;
    private final String screen;
}
