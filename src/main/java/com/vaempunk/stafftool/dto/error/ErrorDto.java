package com.vaempunk.stafftool.dto.error;

import java.util.List;

public record ErrorDto(int code, String message, List<ErrorDetail> details) {

    public ErrorDto(int code, String message) {
        this(code, message, List.of());
    }

}
