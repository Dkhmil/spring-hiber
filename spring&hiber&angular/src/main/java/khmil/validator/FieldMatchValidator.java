package khmil.validator;

import khmil.validator.annotations.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch fieldMatch) {
        this.field = fieldMatch.field();
        this.fieldMatch = fieldMatch.fieldMatch();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(o);
        boolean result = false;
        String field1 = (String) beanWrapper.getPropertyValue(field);
        String field2 = (String) beanWrapper.getPropertyValue(fieldMatch);
        if (field1 != null && field2 != null) {
            result = field1.equals(field2);
        }
        return result;
    }
}
