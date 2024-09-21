package org.kkumulkkum.server.domain.promise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DressUpLevel {
    LV1("LV 1. 안꾸"),
    LV2("LV 2. 꾸안꾸"),
    LV3("LV 3. 꾸꾸"),
    LV4("LV 4. 꾸꾸꾸"),
    FREE("마음대로 입고 오기");

    private String content;
}
