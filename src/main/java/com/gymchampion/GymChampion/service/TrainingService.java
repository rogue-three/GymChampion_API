package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training addTraining(Training training) {
        double userWeight = training.getUser().getWeight();
        training.setUserBodyWeight(userWeight);
        return this.trainingRepository.save(training);
    }

    public List<Training> getAllTrainings() {
        return this.trainingRepository.findAll();
    }

    public Training getTraining(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);
        Training training = new Training();

        if(optionalTraining.isPresent()) {
            training = optionalTraining.get();
        }
        return training;
    }

    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingRepository.findTrainingsByArchivized(false);
    }

    public List<Training> getAllArchivedTrainings() {
        return this.trainingRepository.findTrainingsByArchived(true);
    }

    public List<Training> getTrainingsByUserLogin(String login) {
        return this.trainingRepository.findTrainingsByUserLogin(login);
    }

    public Training archiveTraining(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);
        Training training;
        if (optionalTraining.isPresent()) {
            training = optionalTraining.get();
            training.setArchived(true);
            return this.trainingRepository.save(training);
        }
        return new Training();
    }

    public int countTrainingsByUserLogin(String login) {
        return this.trainingRepository.findTrainingsByUserLogin(login).size();
    }
}
