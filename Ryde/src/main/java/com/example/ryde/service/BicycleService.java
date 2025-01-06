/** interface class for interacting with the Bicycle entity
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
