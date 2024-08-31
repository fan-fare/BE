package cc.happybday.fanfare.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

// Filter 에서 발생하는 응답 핸들링
public class CustomErrorResponder {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorResponder.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendErrorResponse(HttpServletResponse response, ErrorResponseCode errorResponseCode) {
        try {
            ErrorResponse errorResponse = new ErrorResponse(errorResponseCode);

            response.setStatus(errorResponse.getStatus());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            logger.error("에러를 응답하는 중 예상치 못한 예외가 발생했습니다", e);
        }
    }
}
