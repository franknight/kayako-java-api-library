package com.kayako.api.department;

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.enums.AppEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.UserGroup;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DepartmentTest {

    private Department department;
    private Department parentDepartment;

    private static final String TEST_DEPARTMENT_NAME = "Department A";
    private static final String TEST_ICON_NAME = "Icon A";
    private static final String TEST_XML_NAME = "department";
    private static final String TEST_CONTROLLER_NAME = "Controller A";
    private static final String TEST_ELEMENT_NAME = "department";
    private static final String TEST_ID = "id";
    private static final String TEST_TITLE = "title";
    private static final String TEST_DISPLAY_ORDER = "displayorder";
    private static final String TEST_DEPARTMENT_ID = "departmentid";
    private static final String TEST_DISPLAY_ICON = "displayicon";
    private static final String TEST_TYPE = "type";
    private static final String TEST_APP = "app";
    private static final String TEST_USER_VISIBILITY_CUSTOM = "uservisibilitycustom";
    private static final String TEST_USER_GROUPS = "usergroups";
    private static final String TEST_FULLNAME = "fullname";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_CONTENTS = "contents";
    private static final String TEST_SUBJECT = "subject";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        department = new Department(TEST_DEPARTMENT_NAME);
    }

    @Test
    public void shouldCreatedWithDefaultConstructor() {
        // Arrange
        department = new Department();

        //  Act
        department.setTitle(TEST_DEPARTMENT_NAME);

        // Assert
        assertThat(department.getType(), is(AccessTypeEnum.PUBLIC));
        assertEquals(TEST_DEPARTMENT_NAME, department.getTitle());
    }

    @Test
    public void shouldSetTitle() {
        // Assert
        collector.checkThat(TEST_DEPARTMENT_NAME, equalTo(department.getTitle()));
        collector.checkThat(AccessTypeEnum.PUBLIC, equalTo(department.getType()));
        collector.checkThat(AppEnum.TICKETS, equalTo(department.getApp()));
    }

    @Test
    public void shouldSetParentDepartment() throws Exception {
        // Arrange
        parentDepartment = new Department();
        department = new Department(TEST_DEPARTMENT_NAME);

        // Act
        parentDepartment.setId(1);
        parentDepartment.setApp(AppEnum.TICKETS);
        department.setParentDepartment(parentDepartment);

        // Assert
        assertTrue(department.getParentDepartment().equals(parentDepartment));
    }

    @Test
    public void shouldGetParentDepartment() throws Exception {
        // Arrange
        parentDepartment = new Department(TEST_DEPARTMENT_NAME);

        // Act
        parentDepartment.setId(-1);
        department.setParentDepartment(parentDepartment);

        // Assert
        assertNull(department.getParentDepartment(true));
    }

    @Test
    public void shouldSetUserVisibilityCustom() {
        // Act
        department.setUserVisibilityCustom(true);

        // Assert
        assertTrue(department.isUserVisibilityCustom());
    }

    @Test
    public void shouldSetDisplayIcon() {
        // Act
        department.setDisplayIcon(TEST_ICON_NAME);

        // Assert
        assertEquals(TEST_ICON_NAME, department.getDisplayIcon());
    }

    @Test
    public void shouldSetOrderDisplay() {
        // Act
        department.setDisplayOrder(1);

        // Assert
        assertEquals(1, department.getDisplayOrder());
    }

    @Test
    public void shouldSetReadOnly() {
        // Act
        department.setReadOnly(true);

        // Assert
        assertTrue(department.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlNameAndController() {
        // Act
        Department.setObjectXmlName(TEST_XML_NAME);
        Department.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_XML_NAME, Department.getObjectXmlName());
        assertEquals(TEST_CONTROLLER_NAME, Department.getController());
    }

    @Test
    public void shouldSetUserGroups() throws KayakoException {
        // Arrange
        UserGroup userGroup = new UserGroup();
        Map<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();
        Set<Integer> keySet = userGroups.keySet();
        ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        userGroupIds.addAll(keySet);

        department.setUserGroups((HashMap<Integer, UserGroup>) userGroups);
        department.setUserGroupIds(userGroupIds);

        department.addUserGroup(userGroup);

        // Assert
        assertThat(department.getUserGroups().size(), is(1));
        assertTrue(department.isVisibleToUserGroup(userGroup));

    }

    @Test
    public void shouldCreateAutoTicket() throws Exception {
        // Act
        department.createAutoTicket(TEST_FULLNAME, TEST_EMAIL, TEST_CONTENTS, TEST_EMAIL);
    }

    @Test
    public void shouldCreateSubDepartment() {
        // Assert
        assertEquals(TEST_TITLE, department.createSubDepartment(TEST_TITLE).getTitle());
    }

    @Test
    public void shouldCheckIsVisibleToUserGroup() throws KayakoException {
        // Arrange
        Department departmentToCheckFalse = new Department(TEST_DEPARTMENT_NAME);
        UserGroup userGroup = new UserGroup();
        UserGroup userGroupToCheck = new UserGroup();

        // Act
        userGroup.setId(1);
        userGroupToCheck.setId(2);
        department.addUserGroup(userGroup);
        departmentToCheckFalse.addUserGroup(userGroupToCheck);
        department.setUserVisibilityCustom(true);
        departmentToCheckFalse.setUserVisibilityCustom(false);

        // Assert
        assertTrue(department.isVisibleToUserGroup(userGroup));
        assertTrue(departmentToCheckFalse.isVisibleToUserGroup(userGroup));
    }

    @Test
    public void shouldCheckToString() {
        // Assert
        assertEquals("Department : " + TEST_DEPARTMENT_NAME, department.toString());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();
        String[] elements = {TEST_ID, TEST_TITLE, TEST_DISPLAY_ORDER, TEST_DEPARTMENT_ID,
                TEST_DISPLAY_ICON, TEST_TYPE, TEST_APP, TEST_USER_VISIBILITY_CUSTOM, TEST_USER_GROUPS};

        // Act
        for (String element : elements) {
            components.add(new RawArrayElement(element));
        }

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals("Department : ", department.populate(rawArrayElement).toString());

    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        department = new Department();
        ArrayList<Integer> userGroupIds = new ArrayList<>();

        // Act
        userGroupIds.add(1);
        department.setTitle(TEST_DEPARTMENT_NAME);
        department.setType(AccessTypeEnum.PUBLIC);
        department.setApp(AppEnum.TICKETS);
        department.setDisplayOrder(1);
        department.setParentDepartmentId(1);
        department.setUserVisibilityCustom(true);
        department.setUserGroupIds(userGroupIds);

        // Assert
        assertEquals(7, department.buildHashMap(true).size());
    }

}
