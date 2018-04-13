package com.kayako.api.ticket;

import com.kayako.api.department.Department;
import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.user.UserGroup;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.equalTo;

public class TicketTypeTest {

    private TicketType ticketType;
    private static final String TEST_OBJECT_XML_NAME = "XML";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TICKET_TYPE_OBJECT = "TicketType- ID: 0";
    private static final String USER_GROUP_OBJECT = "{1=User Group : null}";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketType = new TicketType();
    }

    @Test
    public void shouldSetDepartment() {
        // Arrange
        Department department = new Department();

        // Act
        ticketType.setDepartment(department);

        // Assert
        assertEquals(department, ticketType.getDepartment());
    }

    @Test
    public void shouldSetType() {
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
        // Act
        ticketType.setUserVisibilityCustom(true);

        // Assert
        assertTrue(ticketType.isUserVisibilityCustom());
    }

    @Test
    public void shouldSetReadOnly() {
        // Act
        ticketType.setReadOnly(true);

        // Assert
        assertTrue(ticketType.readOnly);
    }

    @Test
    public void shouldCheckToString() {
        // Assert
        assertEquals(TICKET_TYPE_OBJECT, ticketType.toString());
    }

    @Test
    public void shouldSetUserGroupIds() {
        // Arrange
        List<Integer> userGroupIds = new ArrayList<Integer>();

        // Act
        userGroupIds.add(1);
        ticketType.setUserGroupIds(userGroupIds);

        // Assert
        assertEquals(1, ticketType.getUserGroupIds().size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetUserGroups() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();
        Set<Integer> keySet = userGroups.keySet();
        List<Integer> userGroupIds = new ArrayList<Integer>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        userGroupIds.addAll(keySet);

        ticketType.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        ticketType.setUserGroupIds(userGroupIds);

        // Assert
        collector.checkThat(USER_GROUP_OBJECT, equalTo(ticketType.getUserGroups().toString()));
        collector.checkThat(USER_GROUP_OBJECT, equalTo(ticketType.getUserGroups(true).toString()));
    }

    @Test
    public void shouldCheckIsVisibleToUserGroupWhenIsUserVisibilityCustomIsFalse() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        ticketType.setUserGroups((HashMap<Integer, UserGroup>) userGroups);

        // Assert
        assertTrue(ticketType.isVisibleToUserGroup(userGroup));
    }

    @Test
    public void shouldCheckIsVisibleToUserGroupWhenIsUserVisibilityCustomIsTrue() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        ticketType.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        ticketType.setUserVisibilityCustom(true);

        // Assert
        assertFalse(ticketType.isVisibleToUserGroup(userGroup));
    }

    @Test
    public void shouldCheckIsAvailableInDepartmentWhenDepartmentIdIsZero() {
        // Arrange
        Department department = new Department();

        // Assert
        assertTrue(ticketType.isAvailableInDepartment(department));
    }

    @Test
    public void shouldCheckIsAvailableInDepartmentWhenDepartmentIdIsNotZero() {
        // Arrange
        Department department = new Department();

        // Act
        department.setId(1);
        ticketType.departmentId = 1;

        // Assert
        assertTrue(ticketType.isAvailableInDepartment(department));
    }
}
