package com.epam.javaee.util;

import com.epam.javaee.entity.News;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@ApplicationScoped
public class ConstraintsTranslator {

    @Inject
    Validator validator;

    public Map<String, String> translate(News news) {
        Set<ConstraintViolation<News>> violationsSet = validator.validate(news);
        Map<String, String> violationsMap = new HashMap<>();
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        for (ConstraintViolation<News> violation : violationsSet) {
            violationsMap.put(violation.getPropertyPath().toString(),
                bundle.getString(violation.getMessage()));
        }
        return violationsMap;
    }
}
