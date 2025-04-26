/** DockingStationRepository interface
 * @author Dodi Mircea Ovidiu
 * @version 2 Jan 2025
 */

package com.example.ryde.repository;

import com.example.ryde.model.DockingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DockingStationRepository extends JpaRepository<DockingStation, Long> {
    DockingStation findByName(String name);
    DockingStation findByLocation(String location);

    @Query("SELECT d FROM DockingStation d")
    List<DockingStation> findAllDockingStations();
}
