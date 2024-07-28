package com.tasks.management.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusData {
    private Integer id;
    @NotEmpty(message = "type status required")
    private String statusType;
    @NotEmpty(message = "name status required")
    private String statusName;
}
