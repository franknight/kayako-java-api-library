package com.kayako.api.user;

import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.createPartialMock;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StaffGroup.class)
public class StaffGroupTest {

    private StaffGroup staffGroup;
    private static final String TEST_OBJECT_XML_NAME = "staffgroup";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_ELEMENT_NAME = "staffgroup";
    private static final String TEST_ID = "id";
    private static final String TEST_TITLE = "title";
    private static final String TEST_IS_ADMIN = "isadmin";
    private static final String TEST_STAFF_GROUP_OBJECT = "StaffGroup- ID: 0";
    private static final String TEST_FIRST_NAME = "firstname";
    private static final String TEST_LAST_NAME = "lastname";
    private static final String TEST_USERNAME = "username";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_PASSWORD = "password";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Test
    public void shouldCreateConstructorUsingRawArrayElement() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        StaffGroup staffGroup = new StaffGroup(rawArrayElement);
    }

    @Test
    public void shouldCheckConstructorUsingTitle() {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);

        // Assert
        collector.checkThat(staffGroup.getTitle(), equalTo(TEST_TITLE));
        collector.checkThat(staffGroup.isAdmin(), is(false));
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        StaffGroup.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, StaffGroup.getObjectXmlName());
    }

    @Test
    public void shouldSetReadonly() {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);

        // Act
        staffGroup.setReadOnly(true);

        // Assert
        assertTrue(staffGroup.getReadOnly());
    }

    @Test
    public void shouldSetController() {
        // Act
        StaffGroup.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, StaffGroup.getController());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();

        // Act
        components.add(new RawArrayElement(TEST_ID));
        components.add(new RawArrayElement(TEST_TITLE));
        components.add(new RawArrayElement(TEST_IS_ADMIN));

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_STAFF_GROUP_OBJECT, staffGroup.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);

        // Assert
        assertEquals(2, staffGroup.buildHashMap().size());
    }

    @Test
    public void shouldCreateStaff() {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);

        // Act
        staffGroup.createStaff(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USERNAME,
                            TEST_EMAIL, TEST_PASSWORD);
    }

    @Test(expected = NullPointerException.class)
    public void shouldCheckSave() throws Exception {
        // Arrange
        StaffGroup staffGroup = new StaffGroup(TEST_TITLE);

        // Act
        StaffGroup mockStaffGroup = createPartialMock(StaffGroup.class, "save");
        replay(StaffGroup.class);

        // Assert
        collector.checkThat(staffGroup.save(), equalTo(mockStaffGroup));
    }

}
