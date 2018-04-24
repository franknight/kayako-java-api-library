package com.kayako.api.user;

import com.kayako.api.department.Department;
import com.kayako.api.enums.SalutationEnum;
import com.kayako.api.rest.RawArrayElement;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ErrorCollector;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private User user;
    private static final String TEST_ELEMENT_NAME = "user";
    private static final String TEST_OBJECT_XML_NAME = "user";
    private static final String TEST_CONTROLLER_NAME = "controller";
    private static final String TEST_CONTENT = "content";
    private static final String TEST_SUBJECT = "subject";
    private static final String TEST_ID = "id";
    private static final String TEST_USER_GROUP_ID = "usergroupid";
    private static final String TEST_USER_ROLE = "userrole";
    private static final String TEST_USER_ORGANIZATION_ID = "userorganizationid";
    private static final String TEST_SALUTATION = "salutation";
    private static final String TEST_USER_EXPIRY = "userexpiry";
    private static final String TEST_FULL_NAME = "fullname";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_DESIGNATION = "designation";
    private static final String TEST_PHONE = "phone";
    private static final String TEST_DATELINE = "dateline";
    private static final String TEST_LAST_VISIT = "lastvisit";
    private static final String TEST_IS_ENABLED = "isenabled";
    private static final String TEST_TIMEZONE = "timezone";
    private static final String TEST_ENABLE_DST = "enabledst";
    private static final String TEST_SLA_PLAN_ID = "slaplanid";
    private static final String TEST_SLA_PLAN_EXPIRY = "slaplanexpiry";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_USER_OBJECT = "User- ID: 0";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void shouldSetReadonly() {
        // Act
        user.setReadOnly(true);

        // Assert
        assertTrue(user.isReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        User.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, User.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        User.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, User.getController());
    }

    @Test
    public void shouldSetEmail() {
        // Act
        user.setEmail(TEST_EMAIL);

        // Assert
        assertEquals(TEST_EMAIL, user.getEmail());
    }

    @Test
    public void shouldSetDST() {
        // Act
        user.setDST(true);

        // Assert
        assertTrue(user.isDST());
    }

    @Test
    public void shouldSetTimezone() {
        // Act
        user.setTimeZone(TEST_TIMEZONE);

        // Assert
        assertEquals(TEST_TIMEZONE, user.getTimeZone());
    }

    @Test
    public void shouldSetPhone() {
        // Act
        user.setPhone(TEST_PHONE);

        // Assert
        assertEquals(TEST_PHONE, user.getPhone());
    }

    @Test
    public void shouldSetUserRole() {
        // Act
        user.setUserRole(TEST_USER_ROLE);

        // Assert
        assertEquals(TEST_USER_ROLE, user.getUserRole());
    }

    @Test
    public void shouldSetSalutation() {
        // Act
        user.setSalutation(SalutationEnum.MR);

        // Assert
        assertEquals(SalutationEnum.MR, user.getSalutation());
    }

    @Test
    public void shouldSetEmails() {
        // Arrange
        ArrayList<String> emails = new ArrayList<>();

        // Act
        user.setEmails(emails);
        user.addEmail(TEST_EMAIL);

        // Assert
        assertEquals(1, user.getEmails().size());
    }

    @Test
    public void shouldSetExpiry() {
        // Act
        user.setUserExpiry(1);

        // Assert
        assertEquals(1, user.getUserExpiry());
    }

    @Test
    public void shouldSetUSerOrganizationId() {
        // Act
        user.setUserOrganizationId(1);

        // Assert
        assertEquals(1, user.getUserOrganizationId());
    }

    @Test
    public void shouldSetDesignation() {
        // Act
        user.setDesignation(TEST_DESIGNATION);

        // Assert
        assertEquals(TEST_DESIGNATION, user.getDesignation());
    }

    @Test
    public void shouldSetDateLine() {
        // Act
        user.setDateLine(1);

        // Assert
        assertEquals(1, user.getDateLine());
    }

    @Test
    public void shouldSetLastVisit() {
        // Act
        user.setLastVisit(1);

        // Assert
        assertEquals(1, user.getLastVisit());
    }

    @Test
    public void shouldSetEnabled() {
        // Act
        user.setEnabled(true);

        // Assert
        assertTrue(user.isEnabled());
    }

    @Test
    public void shouldSetSLAPlanId() {
        // Act
        user.setSLAPlanId(1);

        // Assert
        assertEquals(1, user.getSLAPlanId());
    }

    @Test
    public void shouldSetSLAPlanExpiry() {
        // Act
        user.setSLAPlanExpiry(1);

        // Assert
        assertEquals(1, user.getSLAPlanExpiry());
    }

    @Test
    public void shouldSetSendWelcomeEmail() {
        // Act
        user.setSendWelcomeEmail(true);

        // Assert
        assertTrue(user.isSendWelcomeEmail());
    }

    @Test
    public void shouldSetUserOrganization() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();

        // Act
        userOrganization.setId(1);
        user.setUserOrganization(userOrganization);

        // Assert
        assertEquals(userOrganization, user.getUserOrganization());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();
        String[] elements = {TEST_ID, TEST_USER_GROUP_ID, TEST_USER_ROLE, TEST_USER_ORGANIZATION_ID, TEST_SALUTATION,
                TEST_USER_EXPIRY, TEST_FULL_NAME, TEST_EMAIL, TEST_DESIGNATION, TEST_PHONE, TEST_DATELINE,
                TEST_LAST_VISIT, TEST_IS_ENABLED, TEST_TIMEZONE, TEST_ENABLE_DST, TEST_SLA_PLAN_ID, TEST_SLA_PLAN_EXPIRY};

        // Act
        for (String element : elements) {
            components.add(new RawArrayElement(element));
        }

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_USER_OBJECT, user.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Act
        user.setFullName(TEST_FULL_NAME);
        user.setUserGroupId(1);
        user.setPassword(TEST_PASSWORD);
        user.setSendWelcomeEmail(true);
        user.setEmail(TEST_EMAIL);
        user.setUserOrganizationId(1);
        user.setSalutation(SalutationEnum.MR);
        user.setDesignation(TEST_DESIGNATION);
        user.setPhone(TEST_PHONE);
        user.setEnabled(true);
        user.setUserRole(TEST_USER_ROLE);
        user.setTimeZone(TEST_TIMEZONE);
        user.setDST(true);
        user.setSLAPlanId(1);
        user.setSLAPlanExpiry(1);
        user.setUserExpiry(1);

        // Assert
        assertEquals(16, user.buildHashMap(true).size());
    }

    @Test
    public void shouldCreateTicket() throws Exception {
        // Arrange
        Department department = new Department();

        // Act
        user.createTicket(department, TEST_CONTENT, TEST_SUBJECT);
    }

}
