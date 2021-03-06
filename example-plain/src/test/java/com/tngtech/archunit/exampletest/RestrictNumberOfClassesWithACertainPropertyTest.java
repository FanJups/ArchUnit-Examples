package com.tngtech.archunit.exampletest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.example.SomeBusinessInterface;
import com.tngtech.archunit.example.SomeOtherBusinessInterface;
import org.junit.Test;

import static com.tngtech.archunit.base.DescribedPredicate.lessThanOrEqualTo;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class RestrictNumberOfClassesWithACertainPropertyTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.tngtech.archunit.example");

    @Test
    public void no_new_classes_should_implement_SomeBusinessInterface() {
        classes().that().implement(SomeBusinessInterface.class)
                .should().containNumberOfElements(lessThanOrEqualTo(1))
                .because("from now on new classes should implement " + SomeOtherBusinessInterface.class.getName())
                .check(classes);
    }
}
