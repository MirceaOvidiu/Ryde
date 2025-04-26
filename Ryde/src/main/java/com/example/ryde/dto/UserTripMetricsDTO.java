/** DTO class for the query result.
 * @author Dodi Mircea Ovidiu
 * @version 6 jan 2025
 */

package com.example.ryde.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTripMetricsDTO {
    private Long userId;
    private Integer totalTrips;
    private Integer paidTrips;
    private Integer unpaidTrips;
}