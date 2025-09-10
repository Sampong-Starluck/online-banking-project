package org.sampong.onlinebanking._common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sampong.onlinebanking._common.exception.ErrorException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest req,
            HttpServletResponse res,
            AuthenticationException authException
    ) throws IOException, ServletException {
        var exception = new ErrorException();

        // Safely get "invalid" attribute if present
        Object invalidAttr = req.getAttribute("invalid");
        if (invalidAttr != null) {
            exception.setMessage(invalidAttr.toString());
        } else {
            exception.setMessage(authException.getMessage());
        }

        // Handle 404 case
        if (res.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            exception.setMessage("Not Found");
            exception.setStatus(HttpServletResponse.SC_NOT_FOUND);
            exception.setError("NOT_FOUND");
        }

        // Write JSON response
        res.setContentType("application/json");
        res.setStatus(exception.getStatus() != 0 ? exception.getStatus() : HttpServletResponse.SC_UNAUTHORIZED);

        var resp = res.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp, exception);
        resp.flush();
    }
}
