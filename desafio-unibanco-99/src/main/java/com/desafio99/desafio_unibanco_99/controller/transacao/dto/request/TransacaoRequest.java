package com.desafio99.desafio_unibanco_99.controller.transacao.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequest(
        @NotNull
        @DecimalMin("0")
        BigDecimal valor,

        @NotNull
        @PastOrPresent(message = "A data deve estar no passado ou presente")
        OffsetDateTime dataHora
)
{
}
