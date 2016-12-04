package com.stolser.javatraining.webproject.controller.validator;

import com.stolser.javatraining.webproject.model.entity.user.Login;
import com.stolser.javatraining.webproject.model.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

public class SignInUsernameValidator implements Validator {

    public SignInUsernameValidator() {
    }

    @Override
    public ValidationResult validate(String usernameValue, HttpServletRequest request) {
        int statusCode;
        String messageKey;

        Login login = UserService.getInstance().findOneLoginByUserName(usernameValue);

        if (login != null) {
            statusCode = 200;
            messageKey = "validation.ok";
        } else {
            statusCode = 404;
            messageKey = "validation.noSuchUserName";
        }

        return new ValidationResult(statusCode, messageKey);
    }
}
