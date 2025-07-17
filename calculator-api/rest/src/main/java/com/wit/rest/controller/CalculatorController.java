package com.wit.rest.controller;

import com.wit.rest.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/sum")
    public String sum(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        return sendKafkaOperation("sum", a, b);
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        return sendKafkaOperation("subtract", a, b);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        return sendKafkaOperation("multiply", a, b);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) {
        return sendKafkaOperation("divide", a, b);
    }


    private String sendKafkaOperation(String operation, BigDecimal a, BigDecimal b) {
        String message = String.format("{\"operation\": \"%s\", \"a\": %s, \"b\": %s}", operation, a, b);
        kafkaProducerService.sendOperation(message);
        return "Mensagem enviada para Kafka: " + message;
    }
}
