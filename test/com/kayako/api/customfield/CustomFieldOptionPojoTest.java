package com.kayako.api.customfield;

import javax.annotation.Generated;

import org.junit.Test;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

@Generated("GeneralPatterns")
public class CustomFieldOptionPojoTest {

    @Test
    public void test_validate_CustomFieldOption_Getters() {
        Validator validator = TestChain.startWith(Testers.getterTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(CustomFieldOption.class));
    }
}
