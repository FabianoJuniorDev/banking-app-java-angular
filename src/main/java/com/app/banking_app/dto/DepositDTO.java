package com.app.banking_app.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositDTO(@NotNull BigDecimal amount) {
}