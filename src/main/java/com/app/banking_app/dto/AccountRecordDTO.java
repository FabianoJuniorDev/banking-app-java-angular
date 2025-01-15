package com.app.banking_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record AccountRecordDTO (@NotBlank String holder, @NotNull BigDecimal balance) {
}
