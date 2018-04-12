package com.kayako.api.ticket;

import com.kayako.api.enums.CustomFieldGroupTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertEquals;

public class TicketCustomFieldGroupTest {

    private TicketCustomFieldGroup ticketCustomFieldGroup;
    private static final String TEST_ELEMENT_NAME = "group";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Test
    public void shouldCreatedConstructorWithTicketId() {
        // Arrange
        ticketCustomFieldGroup = new TicketCustomFieldGroup(1);

        // Assert
        collector.checkThat(ticketCustomFieldGroup.getTicketId(), equalTo(1));
        collector.checkThat(ticketCustomFieldGroup.getType(), equalTo(CustomFieldGroupTypeEnum.TICKET));
    }

    @Test
    public void shouldCreatedConstructorWithTicketIdAndElement() throws KayakoException {
        // Arrange
        ticketCustomFieldGroup = new TicketCustomFieldGroup(1, new RawArrayElement(TEST_ELEMENT_NAME));

        //Assert
        collector.checkThat(ticketCustomFieldGroup.getTicketId(), equalTo(1));
        collector.checkThat(ticketCustomFieldGroup.getType(), equalTo(CustomFieldGroupTypeEnum.TICKET));
    }

    @Test
    public void shouldGetController() {
        // Arrange
        ticketCustomFieldGroup = new TicketCustomFieldGroup(1);

        // Assert
        assertEquals(TicketCustomFieldGroup.controller, TicketCustomFieldGroup.getController());
    }

}
