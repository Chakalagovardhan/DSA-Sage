package com.sage.Dsa_sage_backend_consumer.configurations;



import com.sage.Dsa_sage_backend_consumer.entites.UserResponse;
import com.sage.Dsa_sage_backend_consumer.service.FinalStageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

@Configuration
public class KafkaConfigurations {


    @Autowired
    private FinalStageReport reportmaker;


    @KafkaListener(topics = AppConstants.topic,groupId = AppConstants.group1)
    public void consumekafkaResponse(UserResponse response)
    {
        reportmaker.studentReportMaker(response);

    }
}
