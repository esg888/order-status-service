package com.example.order_status_service.etntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusEvent {
    private String status;
    private Instant date;
}
