package it.sevenbits.spring.web.models;

import org.springframework.web.bind.annotation.ResponseBody;


public class CreateStatusRequest {
    String status;

    @ResponseBody
    public static String getStatus(String status) {
        if (status.equals("inbox") || status.equals("done")) {
            return status;
        } else {
            return null;
        }
    }
}
