package utils;

import controller.RegistrationPanelController;
import dao.CustomerDao;
import model.Customer;
import utils.exception.ApplicationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ResourceBundle;
import java.util.Set;

public class ValidatorManager {

    private static final ResourceBundle RESOURCE_BUNDLE = FXMLUtils.getResourceBundle();
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    public static boolean validationOfRegistrationData(RegistrationPanelController registrationPanelController, Customer customer) throws ApplicationException {
        if (validationOfConfirmRegistrationData(registrationPanelController, customer)) return true;
        if (validationOfConfirmPassword(registrationPanelController)) return true;
        if (validationOfDuplicateEmail(registrationPanelController)) return true;
        return false;

    }

    private static boolean validationOfConfirmRegistrationData(RegistrationPanelController registrationPanelController, Customer customer) {
        Set<ConstraintViolation<Customer>> errors = getValidator().validate(customer);
        if (!errors.isEmpty()) {
            errors.forEach(error -> {
                switch (error.getPropertyPath().toString()) {
                    case FIRST_NAME: {
                        registrationPanelController.getFirstNameError().setText(
                                RESOURCE_BUNDLE.getString("validator.message.firstName")
                        );
                        break;
                    }
                    case LAST_NAME: {
                        registrationPanelController.getLastNameError().setText(
                                RESOURCE_BUNDLE.getString("validator.message.lastName")
                        );
                        break;
                    }
                    case EMAIL: {
                        registrationPanelController.getEmailError().setText(
                                RESOURCE_BUNDLE.getString("validator.message.email")
                        );
                        break;
                    }
                    case PASSWORD: {
                        registrationPanelController.getPasswordError().setText(
                                RESOURCE_BUNDLE.getString("validator.message.password")
                        );
                        break;
                    }
                }
            });
            return true;
        }
        return false;
    }

    private static boolean validationOfConfirmPassword(RegistrationPanelController registrationPanelController) {
        if (!registrationPanelController.getPasswordField().getText().equals(registrationPanelController.getConfirmPasswordField().getText())) {
            registrationPanelController.getConfirmPasswordError().setText(
                    RESOURCE_BUNDLE.getString("validator.message.confirmPassword")
            );
            return true;
        }
        return false;
    }

    private static boolean validationOfDuplicateEmail(RegistrationPanelController registrationPanelController) throws ApplicationException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        if (customerDao.queryForEmail(registrationPanelController.getPasswordField().getText())) {
            registrationPanelController.getEmailError().setText(
                    RESOURCE_BUNDLE.getString("validator.message.duplicateEmail")
            );
            return true;
        }
        DBManager.closeConnectionSource();
        return false;
    }
}
