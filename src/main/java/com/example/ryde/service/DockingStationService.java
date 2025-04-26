/** interface for docking station related operations
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service;

import com.example.ryde.model.DockingStation;

import java.util.List;

public interface DockingStationService {
    List<DockingStation> findAllDockingStations();
    DockingStation getDockingStationById(Long id);
    DockingStation getDockingStationByName(String name);
}
