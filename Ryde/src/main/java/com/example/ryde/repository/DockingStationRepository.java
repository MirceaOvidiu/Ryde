/** DockingStationRepository interface
 * @author Dodi Mircea Ovidiu
 * @version 2 Jan 2025
 */

package com.example.ryde.repository;

import com.example.ryde.model.DockingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockingStationRepository extends JpaRepository<DockingStation, Long> {
    DockingStation findByName(String name);
}
