package com.kayako.api.ticket;

import com.kayako.api.enums.ColorEnum;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TicketTimeTrackTest {

    private TicketTimeTrack ticketTimeTrack;
    private static final int TEST_TIME_WORKED_INT = 36000;
    private static final int TEST_TIME_BILLABLE_INT = 72000;
    private static final int TEST_BILL_DATE = 18000;
    private static final int TEST_WORK_DATE = 18000;
    private static final String TEST_NOTE_COLOR = "notecolor";
    private static final String TEST_TIME_WORKED_STRING = "10:00";
    private static final String TEST_TIME_BILLABLE_STRING = "20:00";
    private static final String TEST_CONTENT_NAME = "Content";
    private static final String TEST_ELEMENT_NAME = "timetrack";
    private static final String TEST_OBJECT_XML_NAME = "timetrack";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_TICKET_OBJECT = "Ticket- ID: 0";
    private static final String TEST_TICKET_TIME_TRACK_OBJECT = "TicketTimeTrack- ID: 0";
    private static final String TEST_STAFF_OBJECT = "Staff- ID: 1";
    private static final String TEST_STAFF_NAME = "John Doe";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketTimeTrack = new TicketTimeTrack();
    }

    @Test
    public void shouldCreateConstructorWithTicketAndContentsAndStaffAndTimeWorkedAndTimeBillableInt() throws Exception {
        // Act
        ticketTimeTrack = new TicketTimeTrack(new Ticket(),
                                            TEST_CONTENT_NAME,
                                            new Staff(),
                                            TEST_TIME_WORKED_INT,
                                            TEST_TIME_BILLABLE_INT);

        // Assert
        collector.checkThat(ticketTimeTrack.getTicket(), equalTo(null));
        collector.checkThat(ticketTimeTrack.getCreatorStaff(), equalTo(null));
        collector.checkThat(ticketTimeTrack.getContents(), equalTo(TEST_CONTENT_NAME));
        collector.checkThat(ticketTimeTrack.getTimeWorked(), equalTo(TEST_TIME_WORKED_INT));
        collector.checkThat(ticketTimeTrack.getTimeBillable(), equalTo(TEST_TIME_BILLABLE_INT));
    }

    @Test
    public void shouldCreateConsrtuctorWithTicketAndContentsAndStaffAndTimeWorkedAndTimeBillableString() throws Exception {
        // Act
        ticketTimeTrack = new TicketTimeTrack(new Ticket(),
                                            TEST_CONTENT_NAME,
                                            new Staff(),
                                            TEST_TIME_WORKED_STRING,
                                            TEST_TIME_BILLABLE_STRING);

        // Assert
        collector.checkThat(ticketTimeTrack.getTicket(), equalTo(null));
        collector.checkThat(ticketTimeTrack.getTimeWorked(), equalTo(TEST_TIME_WORKED_INT));
        collector.checkThat(ticketTimeTrack.getTimeBillable(), equalTo(TEST_TIME_BILLABLE_INT));
    }

    @Test
    public void shouldSetId() {
        // Act
        ticketTimeTrack.setId(1);

        // Assert
        assertEquals(1, ticketTimeTrack.getId());
    }

    @Test
    public void shouldCheckReadOnly() {
        // Act
        ticketTimeTrack.setReadOnly(true);

        // Assert
        assertTrue(ticketTimeTrack.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketTimeTrack.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketTimeTrack.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketTimeTrack.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketTimeTrack.getController());
    }

    @Test
    public void shouldSetTicketId() {
        // Act
        ticketTimeTrack.setTicketId(1);

        // Assert
        assertEquals(1, ticketTimeTrack.getTicketId());
    }

    @Test
    public void shouldSetTicket() throws Exception {
        // Arrange
        Ticket ticket = new Ticket();

        // Act
        ticketTimeTrack.setTicket(ticket);

        // Assert
        assertEquals(TEST_TICKET_OBJECT, ticketTimeTrack.getTicket(false).toString());
    }

    @Test
    public void shouldSetCreatorStaffName() {
        // Act
        ticketTimeTrack.setCreatorStaffName(TEST_STAFF_NAME);

        // Assert
        assertEquals(TEST_STAFF_NAME, ticketTimeTrack.getCreatorStaffName());
    }

    @Test
    public void shouldSetCreatorStaff() throws Exception {
        // Arrange
        Staff staff = new Staff();

        // Act
        staff.setId(1);
        staff.setFullName(TEST_STAFF_NAME);
        ticketTimeTrack.setCreatorStaff(staff);

        // Assert
        assertEquals(TEST_STAFF_OBJECT, ticketTimeTrack.getCreatorStaff().toString());
    }

    @Test
    public void shouldSetBillDate() {
        // Act
        ticketTimeTrack.setBillDate(TEST_BILL_DATE);

        // Assert
        assertEquals(TEST_BILL_DATE, ticketTimeTrack.getBillDate());
    }

    @Test
    public void shouldSetWorkDate() {
        // Act
        ticketTimeTrack.setWorkDate(TEST_WORK_DATE);

        // Assert
        assertEquals(TEST_WORK_DATE, ticketTimeTrack.getWorkDate());
    }

    @Test
    public void shouldSetWorkerStaffId() {
        // Act
        ticketTimeTrack.setWorkerStaffId(1);

        // Assert
        assertEquals(1, ticketTimeTrack.getWorkerStaffId());
    }

    @Test
    public void shouldSetWorkerStaffName() {
        // Act
        ticketTimeTrack.setWorkerStaffName(TEST_STAFF_NAME);

        // Assert
        assertEquals(TEST_STAFF_NAME, ticketTimeTrack.getWorkerStaffName());
    }

    @Test
    public void shouldSetNoteColor() {
        // Act
        ticketTimeTrack.setNoteColor(ColorEnum.BLUE);

        // Assert
        assertEquals(ColorEnum.BLUE, ticketTimeTrack.getNoteColor());
    }

    @Test(expected = NullPointerException.class)
    public void shouldSetWorkerStaff() throws Exception {
        // Arrange
        Staff staff = new Staff();

        // Act
        staff.setId(1);
        ticketTimeTrack.setWorkerStaff(staff);

        // Assert
        assertEquals("", ticketTimeTrack.getWorkerStaff(false));
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);

        // Act
        rawArrayElement.setAttribute(TEST_NOTE_COLOR, "1");

        // Assert
        assertEquals(TEST_TICKET_TIME_TRACK_OBJECT, ticketTimeTrack.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldCreateToString() {
        // Assert
        assertThat(ticketTimeTrack.toString(), is(TEST_TICKET_TIME_TRACK_OBJECT));
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        ticketTimeTrack = new TicketTimeTrack();

        // Act
        ticketTimeTrack.setTicketId(1);
        ticketTimeTrack.setCreatorStaffId(1);
        ticketTimeTrack.setContents(TEST_CONTENT_NAME);
        ticketTimeTrack.setWorkDate(TEST_WORK_DATE);
        ticketTimeTrack.setBillDate(TEST_BILL_DATE);
        ticketTimeTrack.setTimeWorked(TEST_TIME_WORKED_INT);
        ticketTimeTrack.setTimeBillable(TEST_TIME_BILLABLE_INT);
        ticketTimeTrack.setWorkerStaffId(1);
        ticketTimeTrack.setNoteColor(ColorEnum.BLUE);

        // Assert
        assertEquals(9, ticketTimeTrack.buildHashMap().size());
    }

}
