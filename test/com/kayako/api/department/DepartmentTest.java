package com.kayako.api.department;

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.enums.AppEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.user.UserGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DepartmentTest {

    private Department department;
    private Department parentDepartment;

    private static final String TEST_DEPARTMENT_NAME = "Department A";
    private static final String TEST_ICON_NAME = "Icon A";
    private static final String TEST_XML_NAME = "Xml A";
    private static final String TEST_CONTROLLER_NAME = "Controller A";

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
        assertEquals(TEST_DEPARTMENT_NAME, department.getTitle());
        assertEquals(AccessTypeEnum.PUBLIC, department.getType());
        assertEquals(AppEnum.TICKETS, department.getApp());
    }

    @Test
    public void shouldSetParentDepartment() throws KayakoException {
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
        assertEquals(null, department.getParentDepartment(true));
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
        HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();
        Set<Integer> keySet = userGroups.keySet();
        ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

        // Act
        userGroup.setId(1);
        userGroups.put(1, userGroup);
        userGroupIds.addAll(keySet);

        department.setUserGroups(userGroups);
        department.setUserGroupIds(userGroupIds);

        department.addUserGroup(userGroup);

        // Assert
        assertThat(department.getUserGroups().size(), is(1));
        assertTrue(department.isVisibleToUserGroup(userGroup));

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

}
