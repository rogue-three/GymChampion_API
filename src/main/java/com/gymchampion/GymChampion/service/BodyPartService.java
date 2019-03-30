package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.BodyPart;
import com.gymchampion.GymChampion.repository.BodyPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodyPartService {

    private BodyPartRepository bodyPartRepository;

    @Autowired
    public BodyPartService(BodyPartRepository bodyPartRepository) {
        this.bodyPartRepository = bodyPartRepository;
    }

    public List<BodyPart> getAllBodyParts() {
        return this.bodyPartRepository.findAll();
    }

    public boolean doesBodyPartExist(BodyPart bodyPart) {
        return this.bodyPartRepository.findByBodyPartName(bodyPart.getBodyPartName()) != null;
    }

    public BodyPart getBodyPartById(int id) {
        Optional<BodyPart> optionalBodypart = this.bodyPartRepository.findById(id);
        return optionalBodypart.orElseGet(null);
    }

    public BodyPart getBodyPartByName(String name) {
        return this.bodyPartRepository.findByBodyPartName(name);
    }

    public void addBodyPart(BodyPart bodyPart) {
        this.bodyPartRepository.save(bodyPart);
    }

    public void updateBodyPart(BodyPart bodyPart) {
        this.bodyPartRepository.save(bodyPart);
    }

    public void removeBodyPart(BodyPart bodyPart) {
        this.bodyPartRepository.delete(bodyPart);
    }
}
