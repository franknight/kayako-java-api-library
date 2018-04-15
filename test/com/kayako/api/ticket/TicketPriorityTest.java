package com.kayako.api.ticket;

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.UserGroup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class TicketPriorityTest {

    private TicketPriority ticketPriority;
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_OBJECT_XML_NAME = "ticketpriority";
    private static final String TEST_ELEMENT_NAME = "ticketpriority";
    private static final String TEST_ID = "id";
    private static final String TEST_TITLE = "title";
    private static final String TEST_DISPLAY_ORDER = "displayorder";
    private static final String TEST_DISPLAY_ICON = "displayicon";
    private static final String TEST_TYPE = "type";
    private static final String TEST_USER_VISIBILITY_CUSTOM = "uservisibilitycustom";
    private static final String TEST_USER_GROUP_ID = "usergroupid";
    private static final String TEST_FR_COLOR_CODE = "frcolorcode";
    private static final String TEST_BG_COLOR_CODE = "bgcolorcode";
    private static final String TICKET_PRIORITY_OBJECT = "TicketPriority- ID: 0";
    private static final String USER_GROUP_OBJECT = "{1=User Group : null}";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketPriority = new TicketPriority();
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketPriority.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketPriority.getController());
    }

    @Test
    public void shouldSetUserVisibilityCustom() {
        // Act
        ticketPriority.setUserVisibilityCustom(true);

        // Assert
        assertTrue(ticketPriority.isUserVisibilityCustom());
    }

    @Test
    public void shouldSetDisplayIcon() {
        // Act
        ticketPriority.setDisplayIcon(TEST_DISPLAY_ICON);

        // Assert
        assertEquals(TEST_DISPLAY_ICON, ticketPriority.getDisplayIcon());
    }

    @Test
    public void shouldSetDisplayOrder() {
        // Act
        ticketPriority.setDisplayOrder(1);

        // Assert
        assertEquals(1, ticketPriority.getDisplayOrder());
    }

    @Test
    public void shouldSetTitle() {
        // Act
        ticketPriority.setTitle(TEST_TITLE);

        // Assert
        assertEquals(TEST_TITLE, ticketPriority.getTitle());
    }

    @Test
    public void shouldSetReadOnly() {
        // Act
        ticketPriority.setReadOnly(true);

        // Assert
        assertTrue(ticketPriority.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketPriority.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketPriority.getObjectXmlName());
    }

    @Test
    public void shouldSetAccesType() {
        // Act
        ticketPriority.setType(AccessTypeEnum.PUBLIC);

        // Assert
        assertEquals(AccessTypeEnum.PUBLIC, ticketPriority.getType());
    }

    @Test
    public void shouldSetUserGroupIds() {
        // Arrange
        ArrayList<Integer> userGroupIds = new ArrayList<>();

        // Act
        userGroupIds.add(1);
        ticketPriority.setUserGroupIds(userGroupIds);

        // Assert
        assertEquals(1, ticketPriority.getUserGroupIds().size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetUserGroups() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();
        Set<Integer> keySet = userGroups.keySet();
        ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        userGroupIds.addAll(keySet);
        ticketPriority.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        ticketPriority.setUserGroupIds(userGroupIds);

        // Assert
        collector.checkThat(USER_GROUP_OBJECT, equalTo(ticketPriority.getUserGroups().toString()));
        collector.checkThat(USER_GROUP_OBJECT, equalTo(ticketPriority.getUserGroups(true).toString()));
    }

    @Test
    public void shouldCheckIsVisibleToUserGroupWhenIsUserVisibilityCustomIsFalse() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        ticketPriority.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        ticketPriority.setUserVisibilityCustom(false);

        // Assert
        assertTrue(ticketPriority.isVisibleToUserGroup(userGroup));
    }

    @Test
    public void shouldCheckIsVisibleToUserGroupWhenIsUserVisibilityCustomIsTrue() throws Exception {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<>();

        // Act
        userGroup.setId(2);
        userGroups.put(2, userGroup);
        ticketPriority.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        ticketPriority.setUserVisibilityCustom(true);

        // Assert
        assertFalse(ticketPriority.isVisibleToUserGroup(userGroup));
    }

    @Test
    public void shouldSetBackgroundColor() {
        // Act
        ticketPriority.setBackgroundColor(TEST_BG_COLOR_CODE);

        // Assert
        assertEquals(TEST_BG_COLOR_CODE, ticketPriority.getBackgroundColor());
    }

    @Test
    public void shouldSetColor() {
        // Act
        ticketPriority.setColor(TEST_FR_COLOR_CODE);

        // Assert
        assertEquals(TEST_FR_COLOR_CODE, ticketPriority.getColor());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> componenets = new ArrayList<>();

        // Act
        componenets.add(new RawArrayElement(TEST_ID));
        componenets.add(new RawArrayElement(TEST_TITLE));
        componenets.add(new RawArrayElement(TEST_DISPLAY_ORDER));
        componenets.add(new RawArrayElement(TEST_DISPLAY_ICON));
        componenets.add(new RawArrayElement(TEST_TYPE));
        componenets.add(new RawArrayElement(TEST_USER_VISIBILITY_CUSTOM));
        componenets.add(new RawArrayElement(TEST_USER_VISIBILITY_CUSTOM, "1"));
        componenets.add(new RawArrayElement(TEST_USER_GROUP_ID,"1"));
        componenets.add(new RawArrayElement(TEST_FR_COLOR_CODE));
        componenets.add(new RawArrayElement(TEST_BG_COLOR_CODE));

        rawArrayElement.setComponents(componenets);

        // Assert
        assertEquals(TICKET_PRIORITY_OBJECT, ticketPriority.populate(rawArrayElement).toString());
    }

}
