package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Equipment;
import com.gymchampion.GymChampion.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return this.equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(int id) {
        Optional<Equipment> optionalBodypart = this.equipmentRepository.findById(id);
        return optionalBodypart.orElseGet(null);
    }

    public void addEquipment(Equipment equipment) {
        this.equipmentRepository.save(equipment);
    }

    public boolean doesEquipmentExists(Equipment equipment) {
        return this.equipmentRepository.findByEquipmentName(equipment.getEquipmentName()) != null;
    }

    public Equipment getEquipmentByName(String equipmentName) {
        return this.equipmentRepository.findByEquipmentName(equipmentName);
    }

    public void updateEquipment(Equipment equipment) {
        this.equipmentRepository.save(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        this.equipmentRepository.delete(equipment);
    }
}
