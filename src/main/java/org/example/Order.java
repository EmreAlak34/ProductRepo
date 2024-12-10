package org.example;

import java.util.List;
import java.time.Instant;


public record Order(String orderId, List<String> productIds, OrderStatus status, Instant orderTimestamp) {}



