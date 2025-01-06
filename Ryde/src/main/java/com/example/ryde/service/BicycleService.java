/** service interface for bicycle related operations
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service;

import com.example.ryde.model.Bicycle;
import java.util.List;

public interface BicycleService {
    List<Bicycle> getAllBicycles();
    Bicycle getBicycleById(Long id);
    Bicycle getBicycleByModel(String model);
    List<Bicycle> findAvailableBicycles();
}
