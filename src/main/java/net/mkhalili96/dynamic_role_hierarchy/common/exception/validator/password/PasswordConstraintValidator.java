//package net.mkhalili96.dynamic_role_hierarchy.common.exception.validator.password;
//
//import net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.PasswordValidationException;
//import org.passay.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class PasswordConstraintValidator {
//
//
//    public boolean isValid(String password) throws Exception {
//        PasswordValidator validator = new PasswordValidator(Arrays.asList(
//                new LengthRule(8, 30),
//                new UppercaseCharacterRule(1),
//                new DigitCharacterRule(1),
//                new SpecialCharacterRule(1),
//                new NumericalSequenceRule(3, false),
//                new AlphabeticalSequenceRule(3, false),
//                new QwertySequenceRule(3, false),
//                new WhitespaceRule()));
//
//        RuleResult result = validator.validate(new PasswordData(password));
//        if (result.isValid()) {
//            return true;
//        } else {
//            List<String> messages = validator.getMessages(result);
//            System.out.println(result);
//            throw new PasswordValidationException(messages);
//        }
//    }
//}