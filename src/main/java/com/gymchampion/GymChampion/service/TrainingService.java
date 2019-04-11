package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.Training;
import com.gymchampion.GymChampion.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void addTraining(Training training) {
        double userWeight = training.getUser().getWeight();
        training.setUserBodyWeight(userWeight);
        this.trainingRepository.save(training);
    }

    public List<Training> getAllTrainings() {
        return this.trainingRepository.findAll();
    }

    public Training getTrainingById(int id) {
        Optional<Training> optionalTraining = this.trainingRepository.findById(id);

        return optionalTraining.orElse(null);
    }

    public List<Training> getAllTrainingsFromActiveUsers() {
        return this.trainingRepository.findTrainingsByArchived(false);
    }

    public List<Training> getNotRemovedTrainingsFromUserByLogin(String login) {
        List<Training> userTrainings = this.trainingRepository.findAllByUser_Login(login);
        return userTrainings.stream().filter(training -> !training.isArchived()).collect(Collectors.toList());
    }

    public List<Training> getAllArchivedTrainings() {
        return this.trainingRepository.findTrainingsByArchived(true);
    }

    public List<Training> getTrainingsByUserLogin(String login) {
        return this.trainingRepository.findAllByUser_Login(login);
    }

    public void updateTraining(Training training) {
        this.trainingRepository.save(training);
    }

    public void updateTrainings(List<Training> trainings) {
        this.trainingRepository.saveAll(trainings);
    }

    public void removeTraining(Training training) {
        this.trainingRepository.delete(training);
    }

    public void removeTrainings(List<Training> trainings) {
        this.trainingRepository.deleteAll(trainings);
    }

    public Integer countTrainingsByUserLogin(String login) {
        return this.getTrainingsByUserLogin(login).size();
    }

    public Long countTrainingDurationInMillisecondsByTrainingID(int trainingId) {
        Long trainingStart = getTrainingById(trainingId).getTrainingDateStart().getTime();
        Long trainingFinish = getTrainingById(trainingId).getTrainingDateFinish().getTime();
        return trainingFinish - trainingStart;

    }
}
