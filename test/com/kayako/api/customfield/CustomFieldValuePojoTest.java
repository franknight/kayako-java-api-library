package com.kayako.api.customfield;

import javax.annotation.Generated;

import org.junit.Test;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

@Generated("GeneralPatterns")
public class CustomFieldValuePojoTest {

    @Test
    public void test_validate_CustomFieldValue_ToString() {
        Validator validator = TestChain.startWith(Testers.toStringTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(CustomFieldValue.class));
    }

    @Test
    public void test_validate_CustomFieldValue_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(CustomFieldValue.class));
    }
}
