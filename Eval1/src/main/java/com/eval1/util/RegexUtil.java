package com.eval1.util;

import custom.springutils.exception.CustomException;

public class RegexUtil {

    public static Boolean isEmailValid(String email) throws CustomException {
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.][a-zA-Z]{2,4}$")) {
            return true;
        }
        throw new CustomException("Email invalide");
    }

    public static Boolean isMalagasyPhoneNumberValid(String phoneNumber) throws CustomException {
        if (phoneNumber.matches("^(0|[+]261)[\s]?(3[2348]|20)[\s]?[0-9]{2}[\s]?[0-9]{3}[\s]?[0-9]{2}$")) {
            return true;
        }
        throw new CustomException("Le numero de telephone ne correspond pas au format malagasy");
    }
}
