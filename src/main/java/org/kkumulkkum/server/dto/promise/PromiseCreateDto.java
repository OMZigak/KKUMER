package org.kkumulkkum.server.dto.promise;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.kkumulkkum.server.domain.promise.DressUpLevel;

import java.time.LocalDateTime;
import java.util.List;

public record PromiseCreateDto (
        @NotBlank @Size(max = 10)
        String name,
        @NotBlank
        String placeName,
        @NotNull
        Double x,
        @NotNull
        Double y,
        @Nullable
        String address,
        @Nullable
        String roadAddress,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time,
        @NotNull
        List<Long> participants,
        @NotNull
        DressUpLevel dressUpLevel,
        @NotNull
        String penalty
) {
}
