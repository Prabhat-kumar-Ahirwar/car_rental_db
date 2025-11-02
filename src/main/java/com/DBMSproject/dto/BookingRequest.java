package com.DBMSproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private Long userId;
    private Long carId;
    private String fromDate;
    private String toDate;
}
