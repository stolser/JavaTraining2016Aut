package com.stolser.javatraining.webproject.controller.validator.user;

import com.stolser.javatraining.webproject.controller.validator.ValidationResult;
import com.stolser.javatraining.webproject.controller.validator.Validator;
import com.stolser.javatraining.webproject.model.entity.user.Credential;
import com.stolser.javatraining.webproject.service.UserService;
import com.stolser.javatraining.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.stolser.javatraining.webproject.controller.ApplicationResources.*;

public class SignInUsernameValidator implements Validator {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public ValidationResult validate(String usernameValue, HttpServletRequest request) {
        int statusCode;
        String messageKey;

        Credential credential = userService.findOneCredentialByUserName(usernameValue);

        if (credential != null) {
            statusCode = STATUS_CODE_SUCCESS;
            messageKey = MSG_SUCCESS;
        } else {
            statusCode = STATUS_CODE_VALIDATION_FAILED;
            messageKey = MSG_NO_SUCH_USER_NAME;
        }

        return new ValidationResult(statusCode, messageKey);
    }
}
