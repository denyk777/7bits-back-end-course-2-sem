package it.sevenbits.spring.web.models;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * checking provided status
 */
public class CreateStatusRequest {
    private String status;

    /**
     * @param status provided status
     * @return status if he correct
     */
    @ResponseBody
    public static String getStatus(final String status) {
        if (status.equals("inbox") || status.equals("done")) {
            return status;
        } else {
            return null;
        }
    }
}
