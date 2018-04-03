package com.kayako.api.user;

import javax.annotation.Generated;

import org.junit.Test;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

@Generated("GeneralPatterns")
public class UserPojoTest {

    @Test
    public void test_validate_User_Getters() {
        Validator validator = TestChain.startWith(Testers.getterTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(User.class));
    }

    @Test
    public void test_validate_User_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(User.class));
    }
}
