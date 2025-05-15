/** implementation class for the DockingStationService interface
 * @author Dodi Mircea Ovidiu
 * @version 2 Jan 2025
 */

package com.example.ryde.service.implementation;

import com.example.ryde.model.DockingStation;
import com.example.ryde.service.DockingStationService;
import com.example.ryde.repository.DockingStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockingStationServiceImplementation implements DockingStationService {
    private final DockingStationRepository dockingStationRepository;

    public DockingStationServiceImplementation(DockingStationRepository dockingStationRepository) {
        this.dockingStationRepository = dockingStationRepository;
    }

    @Override
    public List<DockingStation> findAllDockingStations() {
        return dockingStationRepository.findAll();
    }

    @Override
    public DockingStation getDockingStationById(Long id) {
        return dockingStationRepository.findById(id).orElse(null);
    }

    @Override
    public DockingStation getDockingStationByName(String name) {
        return dockingStationRepository.findByName(name);
    }
}
