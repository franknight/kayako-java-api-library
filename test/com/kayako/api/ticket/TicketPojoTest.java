package com.kayako.api.ticket;

import javax.annotation.Generated;

import org.junit.Test;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

@Generated("GeneralPatterns")
public class TicketPojoTest {

    @Test
    public void test_validate_Ticket_Getters() {
        Validator validator = TestChain.startWith(Testers.getterTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(Ticket.class));
    }

    @Test
    public void test_validate_Ticket_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(Ticket.class));
    }
}
