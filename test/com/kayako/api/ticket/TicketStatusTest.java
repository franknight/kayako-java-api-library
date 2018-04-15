package com.kayako.api.ticket;

import com.kayako.api.department.Department;
import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import com.kayako.api.user.StaffGroup;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.equalTo;

public class TicketStatusTest {

    private TicketStatus ticketStatus;
    private static final String TEST_DEPARTMENT_NAME = "Department A";
    private static final String TEST_STATUS = "Status";
    private static final String TEST_OBJECT_XML_NAME = "ticketstatus";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_FR_COLOR_CODE = "frcolorcode";
    private static final String TEST_BG_COLOR_CODE = "bgcolorcode";
    private static final String TEST_STAFF_GROUP_TITLE_NAME = "Staff title";
    private static final String TEST_ELEMENT_NAME = "ticketstatus";
    private static final String TEST_ID = "id";
    private static final String TEST_TITLE = "title";
    private static final String TEST_DISPLAY_ORDER = "displayorder";
    private static final String TEST_DEPARTMENT_ID = "departmentid";
    private static final String TEST_DISPLAY_ICON = "displayicon";
    private static final String TEST_TYPE = "type";
    private static final String TEST_STATUS_COLOR = "statuscolor";
    private static final String TEST_STATUS_BG_COLOR = "statusbgcolor";
    private static final String TEST_RESET_DUE_TIME = "resetduetime";
    private static final String TEST_DISPLAY_IN_MAIN_LIST = "displayinmainlist";
    private static final String TEST_STAFF_VISIBILITY_CUSTOM = "staffvisibilitycustom";
    private static final String TEST_STAFF_GROUP_ID = "staffgroupid";
    private static final String TEST_MARK_AS_RESOLVED = "markasresolved";
    private static final String TEST_DISPLAY_COUNT = "displaycount";
    private static final String TEST_TRIGGER_SURVEY = "triggersurvey";
    private static final String TICKET_STATUS_OBJECT = "TicketStatus- ID: 0";
    private static final String STAFF_GROUP_OBJECT = "{1=StaffGroup- ID: 1}";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketStatus = new TicketStatus();
    }

    @Test
    public void shouldSetDepartment() {
        // Arrange
        Department department = new Department(TEST_DEPARTMENT_NAME);

        // Act
        ticketStatus.setDepartment(department);

        // Assert
        assertEquals(TEST_DEPARTMENT_NAME, ticketStatus.getDepartment().getTitle());
    }

    @Test
    public void shouldCheckStaffVisibilityCustom() {
        // Act
        ticketStatus.setStaffVisibilityCustom(true);

        // Assert
        assertTrue(ticketStatus.isStaffVisibilityCustom());
    }

    @Test
    public void shouldSetStatus() {
        // Act
        ticketStatus.setStatus(TEST_STATUS);

        // Assert
        assertEquals(TEST_STATUS, ticketStatus.getStatus());
    }

    @Test
    public void shouldCheckReadOnly() {
        // Act
        ticketStatus.setReadOnly(true);

        // Assert
        assertTrue(ticketStatus.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketStatus.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketStatus.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketStatus.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketStatus.getController());
    }

    @Test
    public void shouldSetColor() {
        // Act
        ticketStatus.setColor(TEST_FR_COLOR_CODE);

        // Assert
        assertEquals(TEST_FR_COLOR_CODE, ticketStatus.getColor());
    }

    @Test
    public void shouldSetBackgroundColor() {
        // Act
        ticketStatus.setBackgroundColor(TEST_BG_COLOR_CODE);

        // Assert
        assertEquals(TEST_BG_COLOR_CODE, ticketStatus.getBackgroundColor());
    }

    @Test
    public void shouldCheckResetDueTime() {
        // Act
        ticketStatus.setResetDueTime(true);

        // Assert
        assertTrue(ticketStatus.getResetDueTime());
    }

    @Test
    public void shouldCheckDisplayCount() {
        // Act
        ticketStatus.setDisplayCount(true);

        // Assert
        assertTrue(ticketStatus.getDisplayCount());
    }

    @Test
    public void shouldCheckMarkAsResolved() {
        // Act
        ticketStatus.setMarkAsResolved(true);

        // Assert
        assertTrue(ticketStatus.getMarkAsResolved());
    }

    @Test
    public void shouldSetType() {
        // Act
        ticketStatus.setType(AccessTypeEnum.PUBLIC);

        // Assert
        assertEquals(AccessTypeEnum.PUBLIC, ticketStatus.getType());
    }

    @Test
    public void shouldCheckTriggerSurvey() {
        // Act
        ticketStatus.setTriggerSurvey(true);

        // Assert
        assertTrue(ticketStatus.getTriggerSurvey());
    }

    @Test
    public void shouldSetUserGroupIds() {
        // Arrange
        ArrayList<Integer> staffGroupIds = new ArrayList<>();

        // Act
        staffGroupIds.add(1);
        ticketStatus.setStaffGroupIds(staffGroupIds);

        // Assert
        assertEquals(1, ticketStatus.getStaffGroupIds().size());
    }

    @Test
    public void shouldCheckDisplayInMainList() {
        // Act
        ticketStatus.setDisplayInMainList(true);

        // Assert
        assertTrue(ticketStatus.getDisplayInMainList());
    }

    @Test
    public void shouldCheckIsVisibleToStaffGroupWhenIsStaffVisibilityCustomIsFalse() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_STAFF_GROUP_TITLE_NAME);
        Map<Integer, StaffGroup> staffGroups = new HashMap<>();

        // Act
        staffGroup.setId(1);
        staffGroups.put(1, staffGroup);
        ticketStatus.setStaffGroups((HashMap<Integer, StaffGroup>) staffGroups);
        ticketStatus.setStaffVisibilityCustom(false);

        // Assert
        assertTrue(ticketStatus.isVisibleToStaffGroup(staffGroup));
    }

    @Test
    public void shouldCheckIsVisibleToStaffGroupWhenIsStaffVisibilityCustomIsTrue() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_STAFF_GROUP_TITLE_NAME);
        Map<Integer, StaffGroup> staffGroups = new HashMap<>();
        ArrayList<Integer> staffGroupIds = new ArrayList<>();

        // Act
        staffGroup.setId(1);
        staffGroups.put(1, staffGroup);
        staffGroupIds.add(1);
        ticketStatus.setStaffGroups((HashMap<Integer, StaffGroup>) staffGroups);
        ticketStatus.setStaffGroupIds(staffGroupIds);
        ticketStatus.setStaffVisibilityCustom(true);

        // Assert
        assertTrue(ticketStatus.isVisibleToStaffGroup(staffGroup));
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetStaffGroups() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_STAFF_GROUP_TITLE_NAME);
        Map<Integer, StaffGroup> staffGroups = new HashMap<>();
        Set<Integer> keySet = staffGroups.keySet();
        ArrayList<Integer> staffGroupIds = new ArrayList<Integer>();

        // Act
        staffGroup.setId(1);
        staffGroups.put(1, staffGroup);
        staffGroupIds.addAll(keySet);
        ticketStatus.setStaffGroups((HashMap<Integer, StaffGroup>) staffGroups);
        ticketStatus.setStaffGroupIds(staffGroupIds);

        // Assert
        collector.checkThat(STAFF_GROUP_OBJECT, equalTo(ticketStatus.getStaffGroups().toString()));
        collector.checkThat(STAFF_GROUP_OBJECT, equalTo(ticketStatus.getStaffGroups(true).toString()));
    }

    @Test
    public void shouldCheckIsAvailableInDepartmentWhenDepartmentIdIsNotZero() {
        // Arrange
        Department department = new Department();

        // Act
        department.setId(1);
        ticketStatus.setDepartmentId(department.getId());

        // Assert
        assertTrue(ticketStatus.isAvailableInDepartment(department));
    }

    @Test
    public void shouldCheckIsAvailableInDepartmentWhenDepartmentIdIsZero() {
        // Assert
        assertTrue(ticketStatus.isAvailableInDepartment(new Department()));
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
        componenets.add(new RawArrayElement(TEST_DEPARTMENT_ID));
        componenets.add(new RawArrayElement(TEST_DISPLAY_ICON));
        componenets.add(new RawArrayElement(TEST_TYPE));
        componenets.add(new RawArrayElement(TEST_STATUS_COLOR));
        componenets.add(new RawArrayElement(TEST_STATUS_BG_COLOR));
        componenets.add(new RawArrayElement(TEST_STATUS));
        componenets.add(new RawArrayElement(TEST_RESET_DUE_TIME, "1"));
        componenets.add(new RawArrayElement(TEST_RESET_DUE_TIME, "0"));
        componenets.add(new RawArrayElement(TEST_DISPLAY_IN_MAIN_LIST, "1"));
        componenets.add(new RawArrayElement(TEST_DISPLAY_IN_MAIN_LIST, "0"));
        componenets.add(new RawArrayElement(TEST_STAFF_VISIBILITY_CUSTOM, "1"));
        componenets.add(new RawArrayElement(TEST_STAFF_VISIBILITY_CUSTOM, "0"));
        componenets.add(new RawArrayElement(TEST_STAFF_GROUP_ID, "1"));
        componenets.add(new RawArrayElement(TEST_MARK_AS_RESOLVED, "1"));
        componenets.add(new RawArrayElement(TEST_MARK_AS_RESOLVED, "0"));
        componenets.add(new RawArrayElement(TEST_DISPLAY_COUNT, "1"));
        componenets.add(new RawArrayElement(TEST_DISPLAY_COUNT, "0"));
        componenets.add(new RawArrayElement(TEST_TRIGGER_SURVEY, "1"));
        componenets.add(new RawArrayElement(TEST_TRIGGER_SURVEY, "0"));

        rawArrayElement.setComponents(componenets);

        // Assert
        assertEquals(TICKET_STATUS_OBJECT, ticketStatus.populate(rawArrayElement).toString());
    }

    @Test(expected = KayakoException.class)
    public void shouldBreakPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_STAFF_GROUP_ID);
        ArrayList<RawArrayElement> componenets = new ArrayList<>();

        // Act
        componenets.add(new RawArrayElement(TEST_ID));
        rawArrayElement.setComponents(componenets);

        // Assert
        assertEquals(TICKET_STATUS_OBJECT, ticketStatus.populate(rawArrayElement).toString());
    }

}
