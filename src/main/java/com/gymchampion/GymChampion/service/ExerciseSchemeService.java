package com.gymchampion.GymChampion.service;

import com.gymchampion.GymChampion.model.ExerciseScheme;
import com.gymchampion.GymChampion.repository.ExerciseSchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSchemeService {

    private ExerciseSchemeRepository exerciseSchemeRepository;

    @Autowired
    public ExerciseSchemeService(ExerciseSchemeRepository exerciseSchemeRepository) {
        this.exerciseSchemeRepository = exerciseSchemeRepository;
    }

    public List<ExerciseScheme> getAllExerciseSchemes() {
        return this.exerciseSchemeRepository.findAll();
    }

    public ExerciseScheme getExerciseSchemeById(int id) {
        Optional<ExerciseScheme> optionalExerciseScheme = this.exerciseSchemeRepository.findById(id);
        if (optionalExerciseScheme.isPresent()) {
            return optionalExerciseScheme.get();
        }
        return new ExerciseScheme();
    }

    public ExerciseScheme addExerciseScheme(ExerciseScheme exerciseScheme) {
        return this.exerciseSchemeRepository.save(exerciseScheme);
    }
}