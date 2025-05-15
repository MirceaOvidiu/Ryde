/** DTO class for the query result.
 * @author Dodi Mircea Ovidiu
 * @version 6 jan 2025
 */

package com.example.ryde.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentMetricDTO {
    private Long userId;
    private String userEmail;
    private double totalAmount;

    public PaymentMetricDTO(Long userId, Double totalAmount, String userEmail) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.userEmail = userEmail;
    }
}