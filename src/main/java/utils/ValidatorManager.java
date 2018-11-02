package utils;

import controller.RegistrationPanelController;
import model.Customer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorManager {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    public static boolean isCorrectDataInRegistration(RegistrationPanelController registrationPanelController, Customer customer) {
        Set<ConstraintViolation<Customer>> errors = getValidator().validate(customer);
        if (!errors.isEmpty()) {
            errors.forEach(error -> {
                switch (error.getPropertyPath().toString()) {
                    case FIRST_NAME: {
                        registrationPanelController.getFirstNameError().setText(error.getMessage());
                        break;
                    }
                    case LAST_NAME: {
                        registrationPanelController.getLastNameError().setText(error.getMessage());
                        break;
                    }
                    case EMAIL: {
                        registrationPanelController.getEmailError().setText(error.getMessage());
                        break;
                    }
                    case PASSWORD: {
                        registrationPanelController.getPasswordError().setText(error.getMessage());
                        break;
                    }
                }
            });
            return true;
        }
        if (!registrationPanelController.getPasswordField().getText().equals(registrationPanelController.getConfirmPasswordField().getText())) {
            registrationPanelController.getConfirmPasswordError().setText("Hasła muszą być identyczne");
            return true;
        }
        return false;
    }
}
