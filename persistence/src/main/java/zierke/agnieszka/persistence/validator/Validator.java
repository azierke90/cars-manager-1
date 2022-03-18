package zierke.agnieszka.persistence.validator;

import zierke.agnieszka.persistence.validator.exception.ValidatorException;

import java.util.Map;
import java.util.stream.Collectors;

public interface Validator<T> {

    Map<String, String> validate(T t);

    static <T> void validate(T t, Validator<T> validator){
        var errors = validator.validate(t);

        if (!errors.isEmpty()){
            throw new ValidatorException(errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", ")));
        }
    }
}
