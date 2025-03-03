package com.desafio99.desafio_unibanco_99.controller.transacao;

import com.desafio99.desafio_unibanco_99.controller.transacao.dto.request.TransacaoRequest;
import com.desafio99.desafio_unibanco_99.controller.transacao.dto.response.TransacaoResponse;
import com.desafio99.desafio_unibanco_99.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService service;

    @Operation(
            summary = "Cria uma nova Transação",
            description = "Registra uma transação com valor e data/hora fornecidos no corpo da requisição." +
                    "A transação é armazenada em memória para cálculo estatístico posterior."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Transação criada com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos no corpo da requisição",
                    content = @Content
            )

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@RequestBody @Valid TransacaoRequest request) {
        this.service.criar(request);
    }

    @Operation(
            summary = "Deleta todas as transações",
            description = "Remove todas as transações armazenadas em memória. " +
                    "Útil para reiniciar o estado do sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Transações deletadas com sucesso",
                    content = @Content
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletar() {
        this.service.deletar();
    }

    @Operation(
            summary = "Calcula estatísticas das transações",
            description = "Retorna estatísticas (soma, média, mínimo, máximo e quantidade) das transações " +
                    "realizadas nos últimos 60 segundos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estatísticas calculadas com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransacaoResponse.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhuma transação disponível para cálculo",
                    content = @Content
            )
    })
    @GetMapping("/estatistica")
    public ResponseEntity<TransacaoResponse> calcularEstatistica() {
        var response = this.service.calcularEstatistica();

        return ResponseEntity.ok(response);
    }
}
