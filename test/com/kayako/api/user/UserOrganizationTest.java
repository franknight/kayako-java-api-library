package com.kayako.api.user;

import com.kayako.api.rest.RawArrayElement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserOrganizationTest {

    private UserOrganization userOrganization;

    private static final String TEST_OBJECT_XML_NAME = "userorganization";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_ELEMENT_NAME = "userorganization";
    private static final String TEST_USER_ORGANIZATION_OBJECT = "UserOrganization- ID: 0";
    private static final String TEST_ID = "id";
    private static final String TEST_NAME = "name";
    private static final String TEST_ORGANIZATION_TYPE = "organizationtype";
    private static final String TEST_ADDRESS = "address";
    private static final String TEST_CITY = "city";
    private static final String TEST_STATE = "state";
    private static final String TEST_POSTAL_CODE = "postalcode";
    private static final String TEST_COUNTRY = "country";
    private static final String TEST_PHONE = "phone";
    private static final String TEST_FAX = "fax";
    private static final String TEST_WEBSITE = "website";
    private static final String TEST_DATE_LINE = "dateline";
    private static final String TEST_LAST_UPDATE = "lastupdate";
    private static final String TEST_SLA_PLAN_ID = "slaplanid";
    private static final String TEST_SLA_PLAN_EXPIRY = "slaplanexpiry";

    @Before
    public void setUp() {
        userOrganization = new UserOrganization();
    }

    @Test
    public void shouldSetName() {
        // Act
        userOrganization.setName(TEST_NAME);

        // Assert
        assertEquals(TEST_NAME, userOrganization.getName());
    }

    @Test
    public void shouldSetId() {
        // Act
        userOrganization.setId(1);

        // Assert
        assertEquals(1, userOrganization.getId());
    }

    @Test
    public void shouldSetReadonly() {
        // Act
        userOrganization.setReadOnly(true);

        // Assert
        assertTrue(userOrganization.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        UserOrganization.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, UserOrganization.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        UserOrganization.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, UserOrganization.getController());
    }

    @Test
    public void shouldSetType() {
        // Act
        userOrganization.setType(TEST_ORGANIZATION_TYPE);

        // Assert
        assertEquals(TEST_ORGANIZATION_TYPE, userOrganization.getType());
    }

    @Test
    public void shouldSetAddress() {
        // Act
        userOrganization.setAddress(TEST_ADDRESS);

        // Assert
        assertEquals(TEST_ADDRESS, userOrganization.getAddress());
    }

    @Test
    public void shouldSetCity() {
        // Act
        userOrganization.setCity(TEST_CITY);

        // Assert
        assertEquals(TEST_CITY, userOrganization.getCity());
    }

    @Test
    public void shouldSetState() {
        // Act
        userOrganization.setState(TEST_STATE);

        // Assert
        assertEquals(TEST_STATE, userOrganization.getState());
    }

    @Test
    public void shouldSetPostalCode() {
        // Act
        userOrganization.setPostalCode(TEST_POSTAL_CODE);

        // Assert
        assertEquals(TEST_POSTAL_CODE, userOrganization.getPostalCode());
    }

    @Test
    public void shouldSetCountry() {
        // Act
        userOrganization.setCountry(TEST_COUNTRY);

        // Assert
        assertEquals(TEST_COUNTRY, userOrganization.getCountry());
    }

    @Test
    public void shouldSetPhone() {
        // Act
        userOrganization.setPhone(TEST_PHONE);

        // Assert
        assertEquals(TEST_PHONE, userOrganization.getPhone());
    }

    @Test
    public void shouldSetSLAPlanExpiry() {
        // Act
        userOrganization.setSLAPlanExpiry(1);

        // Assert
        assertEquals(1, userOrganization.getSLAPlanExpiry());
    }

    @Test
    public void shouldSetSLAPlanId() {
        // Act
        userOrganization.setSLAPlanID(1);

        // Assert
        assertEquals(1, userOrganization.getSLAPlanID());
    }

    @Test
    public void shouldSetLastUpdate() {
        // Act
        userOrganization.setLastUpdate(1);

        // Assert
        assertEquals(1, userOrganization.getLastUpdate());
    }

    @Test
    public void shouldSetDateLine() {
        // Act
        userOrganization.setDateline(1);

        // Assert
        assertEquals(1, userOrganization.getDateline());
    }

    @Test
    public void shouldSetWebsite() {
        // Act
        userOrganization.setWebsite(TEST_WEBSITE);

        // Assert
        assertEquals(TEST_WEBSITE, userOrganization.getWebsite());
    }

    @Test
    public void shouldSetFax() {
        // Act
        userOrganization.setFax(TEST_FAX);

        // Assert
        assertEquals(TEST_FAX, userOrganization.getFax());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();
        String[] elements = {TEST_ID, TEST_NAME, TEST_ORGANIZATION_TYPE, TEST_ADDRESS, TEST_CITY,
                TEST_STATE, TEST_POSTAL_CODE, TEST_COUNTRY, TEST_PHONE, TEST_FAX, TEST_WEBSITE,
                TEST_DATE_LINE, TEST_LAST_UPDATE, TEST_SLA_PLAN_ID, TEST_SLA_PLAN_EXPIRY};

        // Act
        for (String element : elements) {
            components.add(new RawArrayElement(element));
        }

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_USER_ORGANIZATION_OBJECT, userOrganization.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Act
        userOrganization.setName(TEST_NAME);
        userOrganization.setType(TEST_ORGANIZATION_TYPE);
        userOrganization.setAddress(TEST_ADDRESS);
        userOrganization.setCity(TEST_CITY);
        userOrganization.setState(TEST_STATE);
        userOrganization.setPostalCode(TEST_POSTAL_CODE);
        userOrganization.setCountry(TEST_COUNTRY);
        userOrganization.setPhone(TEST_PHONE);
        userOrganization.setFax(TEST_FAX);
        userOrganization.setWebsite(TEST_WEBSITE);
        userOrganization.setSLAPlanID(1);
        userOrganization.setSLAPlanExpiry(1);

        // Assert
        assertEquals(12, userOrganization.buildHashMap().size());
    }

}
