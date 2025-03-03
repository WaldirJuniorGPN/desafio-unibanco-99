package com.desafio99.desafio_unibanco_99.controller.transacao.dto.response;

import java.math.BigDecimal;

public record TransacaoResponse(Integer count, BigDecimal sum, BigDecimal avg, BigDecimal min, BigDecimal max) {
}
