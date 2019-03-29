package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.GymChampionApplication;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.SetScheme;
import com.gymchampion.GymChampion.model.User;
import com.gymchampion.GymChampion.service.SetSchemeService;
import com.gymchampion.GymChampion.service.TrainingService;
import com.gymchampion.GymChampion.util.TrainingDaysCount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private TrainingService trainingService;
    private SetSchemeService setSchemeService;
    private static Logger logger = GymChampionApplication.logger;

    @Autowired
    public StatisticsController(TrainingService trainingService,
                                 SetSchemeService setSchemeService) {
        this.trainingService = trainingService;
        this.setSchemeService = setSchemeService;
    }

    @GetMapping("/max_weight_pulled/{exerciseId}")
    public ResponseEntity<?> getSetSchemeWithMaxWeightByExerciseId(@RequestBody User user,
                                                                   @PathVariable("exerciseId") int exerciseId) {
        logger.info(String.format("Fetching Set scheme with max weight by exercise with id %d", exerciseId));
        SetScheme setScheme = this.setSchemeService.getSetSchemeWithMaxWeightByExerciseId(exerciseId, user);
        if (setScheme == null) {
            logger.error(String.format("Set scheme with max weight by exercise with id %d not found.", exerciseId));
            return new ResponseEntity<>(new ResourceDoesNotExistException("Set scheme with max weight by exercise with id " +
                    exerciseId + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setScheme, HttpStatus.OK);
    }

    @GetMapping("/training_days")
    public ResponseEntity<?> countTrainingsByUserLogin(@RequestBody User user) {
        logger.info(String.format("Fetching count of trainings for user with login %s.", user.getLogin()));
        TrainingDaysCount trainingDaysCount = this.trainingService.countTrainingsByUserLogin(user);
        if (trainingDaysCount == null) {
            logger.error(String.format("Trainings for user with login %s not found.", user.getLogin()));
            return new ResponseEntity<>(new ResourceDoesNotExistException("User with login " +
                    user.getLogin() + "have trained 0 days.").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingDaysCount, HttpStatus.OK);
    }
}