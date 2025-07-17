package com.wit.calculator.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.calculator.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.MDC;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final CalculatorService calculatorService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "calculator-operations", groupId = "calculator-group")
        public void consume(String message) {
        try {
            Map<String, Object> data = objectMapper.readValue(
                message,
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {}
            );

            String operation = (String) data.get("operation");
            BigDecimal a = new BigDecimal(data.get("a").toString());
            BigDecimal b = new BigDecimal(data.get("b").toString());

            // Adiciona contexto ao log
            MDC.put("operation", operation);

            BigDecimal result;
            switch (operation) {
                case "sum":
                    result = calculatorService.sum(a, b);
                    break;
                case "subtract":
                    result = calculatorService.subtract(a, b);
                    break;
                case "multiply":
                    result = calculatorService.multiply(a, b);
                    break;
                case "divide":
                    result = calculatorService.divide(a, b);
                    break;
                default:
                    logger.warn("Operação desconhecida: {}", operation);
                    return;
            }

            logger.info("Resultado: {} entre {} e {} = {}", operation, a, b, result);
            System.out.printf("Resultado: %s entre %s e %s = %s%n", operation, a, b, result);

        } catch (Exception e) {
            logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
        } finally {
            MDC.clear(); // importante para não vazar contexto entre threads
        }
    }
}
