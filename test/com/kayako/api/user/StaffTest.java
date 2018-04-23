package com.kayako.api.user;

import com.kayako.api.department.Department;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.ticket.Ticket;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StaffTest {

    private Staff staff;
    private static final String TEST_ELEMENT_NAME = "staff";
    private static final String TEST_OBJECT_XML_NAME = "staff";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_TITLE = "Title";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_TIME_ZONE = "timezone";
    private static final String TEST_CONTENT = "content";
    private static final String TEST_SUBJECT = "subject";
    private static final String TEST_ID = "id";
    private static final String TEST_STAFF_GROUP_ID = "staffgroupid";
    private static final String TEST_FIRST_NAME = "firstname";
    private static final String TEST_LAST_NAME = "lastName";
    private static final String TEST_FULLNAME = "fullname";
    private static final String TEST_USERNAME = "username";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_DESIGNATION = "designation";
    private static final String TEST_GREETING = "greeting";
    private static final String TEST_MOBILE_NUMBER = "mobilenumber";
    private static final String TEST_IS_ENABLED = "isenabled";
    private static final String TEST_ENABLE_DST = "enabledst";
    private static final String TEST_SIGNATURE = "signature";
    private static final String TEST_TIMEZONE = "timezone";
    private static final String TEST_STAFF_OBJECT = "Staff- ID: 0";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        staff = new Staff();
    }

    @Test
    public void shouldCreatedConstructorWithArguments() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);
        Staff newStaff = new Staff(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USERNAME, TEST_EMAIL, staffGroup, TEST_PASSWORD);

        // Assert
        collector.checkThat(newStaff.getFirstName(), equalTo(TEST_FIRST_NAME));
        collector.checkThat(newStaff.getLastName(), equalTo(TEST_LAST_NAME));
        collector.checkThat(newStaff.getUserName(), equalTo(TEST_USERNAME));
        collector.checkThat(newStaff.getEmail(), equalTo(TEST_EMAIL));
        collector.checkThat(newStaff.getStaffGroup(), equalTo(staffGroup));
        collector.checkThat(newStaff.getPassword(), equalTo(TEST_PASSWORD));
    }

    @Test
    public void shouldSetId() {
        // Act
        staff.setId(1);

        // Assert
        assertEquals(1, staff.getId());
    }

    @Test
    public void shouldSetReadonly() {
        // Act
        staff.setReadOnly(true);

        // Assert
        assertTrue(staff.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        Staff.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, Staff.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        Staff.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, Staff.getController());
    }

    @Test
    public void shouldSetStaffGroupId() {
        // Act
        staff.setStaffGroupId(1);

        // Assert
        assertEquals(1, staff.getStaffGroupId());
    }

    @Test
    public void shouldSetFullName() {
        // Act
        staff.setFullName(TEST_FULLNAME);

        // Assert
        assertEquals(TEST_FULLNAME, staff.getFullName());
    }

    @Test
    public void shouldSetDesignation() {
        // Act
        staff.setDesignation(TEST_DESIGNATION);

        // Assert
        assertEquals(TEST_DESIGNATION, staff.getDesignation());
    }

    @Test
    public void shouldSetGreeting() {
        // Act
        staff.setGreeting(TEST_GREETING);

        // Assert
        assertEquals(TEST_GREETING, staff.getGreeting());
    }

    @Test
    public void shouldSetSignature() {
        // Act
        staff.setSignature(TEST_SIGNATURE);

        // Assert
        assertEquals(TEST_SIGNATURE, staff.getSignature());
    }

    @Test
    public void shouldSetMobileNumber() {
        // Act
        staff.setMobileNumber(TEST_MOBILE_NUMBER);

        // Assert
        assertEquals(TEST_MOBILE_NUMBER, staff.getMobileNumber());
    }

    @Test
    public void shouldSetEnabled() {
        // Act
        staff.setEnabled(true);

        // Assert
        assertTrue(staff.isEnabled());
    }

    @Test
    public void shouldSetDST() {
        // Act
        staff.setDST(true);

        // Assert
        assertTrue(staff.isDST());
    }

    @Test
    public void shouldSetTimezone() {
        // Act
        staff.setTimeZone(TEST_TIME_ZONE);

        // Assert
        assertEquals(TEST_TIME_ZONE, staff.getTimeZone());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();
        String[] elements = { TEST_ID, TEST_STAFF_GROUP_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_FULLNAME,
                TEST_USERNAME, TEST_EMAIL, TEST_DESIGNATION, TEST_GREETING, TEST_MOBILE_NUMBER,
                TEST_IS_ENABLED, TEST_ENABLE_DST, TEST_SIGNATURE, TEST_TIMEZONE };

        // Act
        for (String element : elements) {
            components.add(new RawArrayElement(element));
        }

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, staff.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Act
        staff.setFirstName(TEST_FIRST_NAME);
        staff.setLastName(TEST_LAST_NAME);
        staff.setFullName(TEST_FULLNAME);
        staff.setUserName(TEST_USERNAME);
        staff.setPassword(TEST_PASSWORD);
        staff.setEmail(TEST_EMAIL);
        staff.setDesignation(TEST_DESIGNATION);
        staff.setMobileNumber(TEST_MOBILE_NUMBER);
        staff.setSignature(TEST_SIGNATURE);
        staff.setGreeting(TEST_GREETING);
        staff.setStaffGroupId(1);
        staff.setEnabled(true);
        staff.setDST(true);

        // Assert
        assertEquals(13, staff.buildHashMap(true).size());
    }

    @Test
    public void shouldCreateTicket() {
        // Arrange
        Department department = new Department();

        // Act
        staff.createTicket(department, TEST_CONTENT, TEST_SUBJECT);
    }

}
