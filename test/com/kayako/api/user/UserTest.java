package com.kayako.api.user;

import com.kayako.api.department.Department;
import com.kayako.api.enums.SalutationEnum;
import com.kayako.api.rest.RawArrayElement;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.CoreMatchers.*;

public class UserTest {

    private User user;
    private static final String ELEMENT_NAME = "user";
    private static final String OBJECT_XML_NAME = "user";
    private static final String CONTROLLER_NAME = "controller";
    private static final String CONTENT = "content";
    private static final String SUBJECT = "subject";
    private static final String ID = "id";
    private static final String USER_GROUP_ID = "usergroupid";
    private static final String USER_ROLE = "userrole";
    private static final String USER_ORGANIZATION_ID = "userorganizationid";
    private static final String SALUTATION = "salutation";
    private static final String USER_EXPIRY = "userexpiry";
    private static final String FULL_NAME = "fullname";
    private static final String EMAIL = "email";
    private static final String DESIGNATION = "designation";
    private static final String PHONE = "phone";
    private static final String DATELINE = "dateline";
    private static final String LAST_VISIT = "lastvisit";
    private static final String IS_ENABLED = "isenabled";
    private static final String TIMEZONE = "timezone";
    private static final String ENABLE_DST = "enabledst";
    private static final String SLA_PLAN_ID = "slaplanid";
    private static final String SLA_PLAN_EXPIRY = "slaplanexpiry";
    private static final String PASSWORD = "password";
    private static final String USER_OBJECT = "User- ID: 0";

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
        User.setObjectXmlName (OBJECT_XML_NAME);

        // Assert
        assertEquals (OBJECT_XML_NAME, User.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        User.setController (CONTROLLER_NAME);

        // Assert
        assertEquals (CONTROLLER_NAME, User.getController());
    }

    @Test
    public void shouldSetEmail() {
        // Act
        user.setEmail (EMAIL);

        // Assert
        assertEquals (EMAIL, user.getEmail());
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
        user.setTimeZone (TIMEZONE);

        // Assert
        assertEquals (TIMEZONE, user.getTimeZone());
    }

    @Test
    public void shouldSetPhone() {
        // Act
        user.setPhone (PHONE);

        // Assert
        assertEquals (PHONE, user.getPhone());
    }

    @Test
    public void shouldSetUserRole() {
        // Act
        user.setUserRole (USER_ROLE);

        // Assert
        assertEquals (USER_ROLE, user.getUserRole());
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
        user.addEmail (EMAIL);

        // Assert
        assertEquals(1, user.getEmails().size());
    }

    @Test
    public void shouldSetExpiry() {
        // Arrange
        int expiry = 1;

        // Act
        user.setUserExpiry(expiry);

        // Assert
        assertEquals(expiry, user.getUserExpiry());
    }

    @Test
    public void shouldSetUSerOrganizationId() {
        // Arrange
        int userOrganizationId = 1;

        // Act
        user.setUserOrganizationId(userOrganizationId);

        // Assert
        assertEquals(userOrganizationId, user.getUserOrganizationId());
    }

    @Test
    public void shouldSetDesignation() {
        // Act
        user.setDesignation (DESIGNATION);

        // Assert
        assertEquals (DESIGNATION, user.getDesignation());
    }

    @Test
    public void shouldSetDateLine() {
        // Arrange
        int dateLine = 1;

        // Act
        user.setDateLine(dateLine);

        // Assert
        assertEquals(dateLine, user.getDateLine());
    }

    @Test
    public void shouldSetLastVisit() {
        // Arrange
        int lastVisit = 1;

        // Act
        user.setLastVisit(lastVisit);

        // Assert
        assertEquals(lastVisit, user.getLastVisit());
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
        // Arrange
        int SLAPlanId = 1;

        // Act
        user.setSLAPlanId(SLAPlanId);

        // Assert
        assertEquals(SLAPlanId, user.getSLAPlanId());
    }

    @Test
    public void shouldSetSLAPlanExpiry() {
        // Arrange
        int SLAPlanExpiry = 1;

        // Act
        user.setSLAPlanExpiry(SLAPlanExpiry);

        // Assert
        assertEquals(SLAPlanExpiry, user.getSLAPlanExpiry());
    }

    @Test
    public void shouldSetSendWelcomeEmail() {
        // Arrange
        boolean newValue = !user.isSendWelcomeEmail();

        // Act
        user.setSendWelcomeEmail(newValue);

        // Assert
        assertEquals(newValue, user.isSendWelcomeEmail());
    }

    @Test
    public void shouldSetUserOrganization() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();
        int id = 1;

        // Act
        userOrganization.setId(id);
        user.setUserOrganization(userOrganization);

        // Assert
        assertEquals(userOrganization, user.getUserOrganization());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement (ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();
        String[] elements = {ID, USER_GROUP_ID, USER_ROLE, USER_ORGANIZATION_ID, SALUTATION,
                USER_EXPIRY, FULL_NAME, EMAIL, DESIGNATION, PHONE, DATELINE,
                LAST_VISIT, IS_ENABLED, TIMEZONE, ENABLE_DST, SLA_PLAN_ID, SLA_PLAN_EXPIRY};

        // Act
        for (String element : elements) {
            components.add(new RawArrayElement(element));
        }

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals (USER_OBJECT, user.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Act
        user.setFullName (FULL_NAME);
        user.setUserGroupId(1);
        user.setPassword (PASSWORD);
        user.setSendWelcomeEmail(true);
        user.setEmail (EMAIL);
        user.setUserOrganizationId(1);
        user.setSalutation(SalutationEnum.MR);
        user.setDesignation (DESIGNATION);
        user.setPhone (PHONE);
        user.setEnabled(true);
        user.setUserRole (USER_ROLE);
        user.setTimeZone (TIMEZONE);
        user.setDST(true);
        user.setSLAPlanId(1);
        user.setSLAPlanExpiry(1);
        user.setUserExpiry(1);

        Map<String, String> map = user.buildHashMap(true);

        // Assert
        collector.checkThat(map.size(), is(16));
    }

    @Test
    public void shouldCreateTicket() throws Exception {
        // Arrange
        Department department = new Department();

        // Act
        user.createTicket(department, CONTENT, SUBJECT);
    }

}
