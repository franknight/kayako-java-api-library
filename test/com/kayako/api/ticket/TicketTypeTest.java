package com.kayako.api.ticket;

import com.kayako.api.department.Department;
import com.kayako.api.enums.AccessTypeEnum;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TicketTypeTest {

    private TicketType ticketType;
    private static final String TEST_OBJECT_XML_NAME = "XML";
    private static final String TEST_CONTROLLER_NAME = "Controller";

    @Test
    public void shouldSetDepartment() {
        // Arrange
        ticketType = new TicketType();
        Department department = new Department();

        // Act
        ticketType.setDepartment(department);

        // Assert
        assertEquals(department, ticketType.getDepartment());
    }

    @Test
    public void shouldSetType() {
        // Arrange
        ticketType = new TicketType();

        // Act
        ticketType.setType(AccessTypeEnum.PUBLIC);

        // Assert
        assertEquals(AccessTypeEnum.PUBLIC, ticketType.getType());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketType.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketType.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketType.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketType.getController());
    }

    @Test
    public void shouldSetUserVisibilityCustom() {
        // Arrange
        ticketType = new TicketType();

        // Act
        ticketType.setUserVisibilityCustom(true);

        // Assert
        assertTrue(ticketType.isUserVisibilityCustom());
    }

    @Test
    public void shouldSetReadOnly() {
        // Arrange
        ticketType = new TicketType();

        // Act
        ticketType.setReadOnly(true);

        // Assert
        assertTrue(ticketType.readOnly);
    }

    @Test
    public void shouldCheckToString() {
        // Arrange
        ticketType = new TicketType();

        // Assert
        assertEquals("TicketType- ID: 0", ticketType.toString());
    }

}
