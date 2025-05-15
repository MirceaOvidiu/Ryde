/** BicycleRepository interface
 * @author Dodi Mircea Ovidiu
 * @version 20 Nov 2024
 */

package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Bicycle;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

    @Query("SELECT b FROM Bicycle b")
    List<Bicycle> findAllBicycles();

    Bicycle findBicycleById(Long id);

    @Query("SELECT b FROM Bicycle b WHERE b.occupied_by IS NULL")
    List<Bicycle> findAvailableBicycles();

    Bicycle findBicycleByModel(String model);
}
