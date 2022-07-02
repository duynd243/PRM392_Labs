package com.prm392.restapi.model;

import com.prm392.restapi.api.APIClient;

public class TraineeRepository {
    public static TraineeService getTraineeService() {
        return APIClient.getClient().create(TraineeService.class);
    }
}
