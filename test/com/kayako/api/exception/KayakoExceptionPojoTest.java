package com.kayako.api.exception;

import javax.annotation.Generated;

import org.junit.Test;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

@Generated("GeneralPatterns")
public class KayakoExceptionPojoTest {

    @Test
    public void test_validate_KayakoException_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(KayakoException.class));
    }
}
