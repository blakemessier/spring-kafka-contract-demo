package io.github.mathias82.demo.model;

public record OrderEvent(
        String orderId,
        Double amount,
        String createdAt
) {}