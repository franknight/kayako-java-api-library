package com.kayako.api.user;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserGroupTest {

    private UserGroup userGroup;
    private static final String TEST_TITLE = "title";
    private static final String TEST_ELEMENT_NAME = "usergroup";
    private static final String TEST_OBJECT_XML_NAME = "usergroup";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_TO_STRING_OBJECT = "User Group : title";
    private static final String TEST_ID = "id";
    private static final String TEST_GROUP_TYPE = "grouptype";
    private static final String TEST_IS_MASTER = "ismaster";
    private static final String TEST_USER_GROUP_OBJECT = "User Group : ";
    private static final String TEST_FULL_NAME = "John Doe";
    private static final String TEST_EMAIL = "john.doe@example.com";
    private static final String TEST_PASSWORD = "password";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() throws Exception {
        userGroup = new UserGroup();
    }

    @Test
    public void shouldCreateConstructorWithTitle() {
        // Act
        userGroup = new UserGroup(TEST_TITLE);

        // Assert
        collector.checkThat(userGroup.getTitle(), equalTo(TEST_TITLE));
        collector.checkThat(userGroup.getType(), equalTo(UserGroup.TYPE_REGISTERED));
    }

    @Test
    public void shouldSetReadOnly() {
        // Act
        userGroup.setReadOnly(true);

        // Assert
        assertTrue(userGroup.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        UserGroup.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, UserGroup.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        UserGroup.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, UserGroup.getController());
    }

    @Test
    public void shouldSetMaster() {
        // Act
        userGroup.setMaster(true);

        // Assert
        assertTrue(userGroup.isMaster());
    }

    @Test
    public void shouldCreateToString() {
        // Act
        userGroup = new UserGroup(TEST_TITLE);

        // Asert
        assertEquals(TEST_TO_STRING_OBJECT, userGroup.toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSaveUserGroup() throws Exception {
        // Assert
        assertEquals(userGroup, userGroup.save());
    }

    @Test(expected = KayakoException.class)
    public void shouldUpdateUserGroup() throws Exception {
        // Assert
        assertEquals(userGroup, userGroup.update());
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateUserGroup() throws Exception {
        // Assert
        assertEquals(userGroup, userGroup.create());
    }

    @Test(expected = NullPointerException.class)
    public void shouldDelete() throws Exception {
        // Assert
        assertTrue(userGroup.delete());
    }

    @Test(expected = KayakoException.class)
    public void shouldRefresh() throws Exception {
        // Assert
        assertEquals(userGroup, userGroup.refresh());
    }

    @Test
    public void shouldBuildHashMap() {
        // Act
        userGroup = new UserGroup(TEST_TITLE);

        // Assert
        assertEquals(2, userGroup.buildHashMap().size());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> componenets = new ArrayList<>();

        // Act
        componenets.add(new RawArrayElement(TEST_ID));
        componenets.add(new RawArrayElement(TEST_TITLE));
        componenets.add(new RawArrayElement(TEST_GROUP_TYPE));
        componenets.add(new RawArrayElement(TEST_IS_MASTER));

        rawArrayElement.setComponents(componenets);

        // Assert
        assertEquals(TEST_USER_GROUP_OBJECT, userGroup.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldCreateUser() {
        // Arrange
        User user = new User();

        // Act
        user = userGroup.createUser(TEST_FULL_NAME, TEST_EMAIL, TEST_PASSWORD);

        // Assert
        collector.checkThat(user.getFullName(), equalTo(TEST_FULL_NAME));
        collector.checkThat(user.getEmail(), equalTo(TEST_EMAIL));
        collector.checkThat(user.getPassword(), equalTo(TEST_PASSWORD));
    }

}
