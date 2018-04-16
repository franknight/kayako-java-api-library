package com.kayako.api.ticket;

import com.kayako.api.enums.ColorEnum;
import com.kayako.api.enums.TicketNoteTypeEnum;
import com.kayako.api.user.Staff;
import com.kayako.api.user.User;
import com.kayako.api.user.UserOrganization;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TicketNoteTest {

    private TicketNote ticketNote;
    private static final String TEST_CONTENT_NAME = "Content";
    private static final String TEST_OBJECT_XML_NAME = "note";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_CREATOR_NAME = "John Doe";
    private static final String TEST_STAFF_OBJECT = "Staff- ID: 0";
    private static final String TEST_USER_ORGANIZATION_OBJECT = "UserOrganization- ID: 0";
    private static final String TEST_TICKET_NOTE_OBJECT = "TicketNote- ID: 0";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketNote = new TicketNote();
    }

    @Test
    public void shouldSetConstructorWithTicketAndStaffAndContents() throws Exception {
        // Arrange
        Ticket ticket = new Ticket();
        Staff staff = new Staff();

        // Act
        ticketNote = new TicketNote(ticket, staff, TEST_CONTENT_NAME);

        // Assert
        collector.checkThat(ticketNote.getTicket(), is(ticket));
        collector.checkThat(ticketNote.getCreatorStaff(), equalTo(null));
        collector.checkThat(ticketNote.getContents(), equalTo(TEST_CONTENT_NAME));
        collector.checkThat(ticketNote.getCreatorStaffName(), equalTo(""));
    }

    @Test
    public void shouldSetId() {
        // Act
        ticketNote.setId(1);

        // Assert
        assertEquals(1, ticketNote.getId());
    }

    @Test
    public void shouldCheckReadOnly() {
        // Act
        ticketNote.setReadOnly(true);

        // Assert
        assertTrue(ticketNote.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketNote.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketNote.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketNote.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketNote.getController());
    }

    @Test
    public void shouldSetTicketId() {
        // Act
        ticketNote.setTicketId(1);

        // Assert
        assertEquals(1, ticketNote.getTicketId());
    }

    @Test
    public void shouldSetUserIdWhenEnumIsUser() {
        // Act
        ticketNote.setUserId(1);
        ticketNote.setType(TicketNoteTypeEnum.USER);

        // Assert
        assertEquals(1, ticketNote.getUserId());
    }

    @Test
    public void shouldSetUserIdWhenEnumIsNotUser() {
        // Act
        ticketNote.setUserId(1);
        ticketNote.setType(TicketNoteTypeEnum.USER_ORGANIZATION);

        // Assert
        assertEquals(0, ticketNote.getUserId());
    }

    @Test
    public void shouldSetUser() throws Exception {
        // Arrange
        User user = new User();

        // Act
        user.setId(1);
        ticketNote.setUser(user);

        // Assert
        assertEquals(user, ticketNote.getUser());
    }

    @Test
    public void shouldSetUserOrganizationId() {
        // Act
        ticketNote.setUserOrganizationId(1);
        ticketNote.setType(TicketNoteTypeEnum.USER_ORGANIZATION);

        // Assert
        collector.checkThat(ticketNote.getUserOrganizationId(), equalTo(1));
        collector.checkThat(new TicketNote().getUserOrganizationId(), equalTo(0));
    }

    @Test
    public void shouldSetNoteColor() {
        // Act
        ticketNote.setNoteColor(ColorEnum.BLUE);

        // Assert
        assertEquals(ColorEnum.BLUE, ticketNote.getNoteColor());
    }

    @Test
    public void shouldSetCreatorStaffId() {
        // Act
        ticketNote.setCreatorStaffId(1);

        // Assert
        assertEquals(1, ticketNote.getCreatorStaffId());
    }

    @Test
    public void shouldSetForStaffId() {
        // Act
        ticketNote.setForStaffId(1);

        // Assert
        assertEquals(1, ticketNote.getForStaffId());
    }

    @Test
    public void shouldSetCreationDate() {
        // Act
        ticketNote.setCreationDate(100);

        // Assert
        assertEquals(100, ticketNote.getCreationDate());
    }

    @Test
    public void shouldSetCreatorWithStaff() throws Exception {
        // Arrange
        Staff staff = new Staff();

        // Act
        ticketNote.setCreator(staff);
        ticketNote.setCreatorStaffId(1);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, ticketNote.getCreatorStaff().toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetCreatorWithStaffId() throws Exception {
        // Act
        ticketNote.setCreator(1);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, ticketNote.getCreatorStaff().toString());
    }

    @Test
    public void shouldSetCreatorWithCreator() throws Exception {
        // Act
        ticketNote.setCreator(TEST_CREATOR_NAME);

        // Assert
        assertNull(ticketNote.getCreatorStaff());
    }

    @Test
    public void shouldSetForStaffWhenRefreshIsFalse() throws Exception {
        // Arrange
        Staff staff = new Staff();

        // Act
        ticketNote.setForStaff(staff);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, ticketNote.getForStaff().toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetForStaffWhenRefreshIsTrue() throws Exception {
        // Arrange
        Staff staff = new Staff();

        // Act
        ticketNote.setForStaff(staff);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, ticketNote.getForStaff(true).toString());
    }

    @Test
    public void shouldSetUserOrganizationWhenRefreshIsFalse() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();

        // Act
        ticketNote.setUserOrganization(userOrganization);

        // Assert
        assertEquals(TEST_USER_ORGANIZATION_OBJECT, ticketNote.getUserOrganization().toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetUserOrganizationWhenRefreshIsTrue() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();

        // Act
        ticketNote.setUserOrganization(userOrganization);
        ticketNote.setUserOrganizationId(1);

        // Assert
        assertEquals(TEST_USER_ORGANIZATION_OBJECT, ticketNote.getUserOrganization(true).toString());
    }

    @Test
    public void shouldCreateToString() {
        // Act
        ticketNote = new TicketNote();

        // Assert
        assertThat(ticketNote.toString(), is(TEST_TICKET_NOTE_OBJECT));
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        TicketNote ticketNote1 = new TicketNote();
        TicketNote ticketNote2 = new TicketNote();

        // Act
        ticketNote1.setTicketId(1);
        ticketNote1.setContents(TEST_CONTENT_NAME);
        ticketNote1.setCreatorStaffId(1);
        ticketNote1.setForStaffId(1);
        ticketNote1.setNoteColor(ColorEnum.BLUE);

        ticketNote2.setTicketId(1);
        ticketNote2.setContents(TEST_CONTENT_NAME);
        ticketNote2.setCreatorStaffId(0);
        ticketNote2.setForStaffId(1);
        ticketNote2.setNoteColor(ColorEnum.BLUE);

        // Assert
        collector.checkThat(ticketNote1.buildHashMap().size(), equalTo(5));
        collector.checkThat(ticketNote1.buildHashMap().size(), equalTo(5));
    }

}
