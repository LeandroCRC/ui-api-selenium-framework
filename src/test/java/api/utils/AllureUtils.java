package api.utils;

import io.qameta.allure.Attachment;

public class AllureUtils {

    @Attachment(value = "Request Body", type = "application/json")
    public static String attachRequest(String body) {
        return body;
    }

    @Attachment(value = "Response Body", type = "application/json")
    public static String attachResponse(String body) {
        return body;
    }
}
