package com.kayako.api.ticket;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
        TicketAttachment.class,
        KEntity.class
})
public class TicketAttachmentTest {

    private TicketAttachment ticketAttachment;
    private static final int TEST_FILE_SIZE_INT = 5_000;
    private static final int TEST_DATE_LINE = 1_000;
    private static final String TEST_ELEMENT_NAME = "attachment";
    private static final String TEST_ID = "id";
    private static final String TEST_TICKET_ID = "ticketid";
    private static final String TEST_TICKET_POST_ID = "ticketpostid";
    private static final String TEST_FILE_SIZE= "filesize";
    private static final String TEST_DATELINE = "dateline";
    private static final String TEST_CONTENTS = "contents";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_OBJECT_XML_NAME = "attachment";
    private static final String TEST_FILE_NAME = "filename";
    private static final String TEST_FILE_TYPE = "filetype";
    private static final String TEST_TICKET_ATTACHMENT_OBJECT = "TicketAttachment- ID: 0";
    private static final String TEST_ELEMENT = "<Auto Element>\n"
                                            + "null\n"
                                            + "</Auto Element>\n";

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketAttachment = new TicketAttachment();
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketAttachment.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketAttachment.getController());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketAttachment.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketAttachment.getObjectXmlName());
    }

    @Test
    public void shouldSetId() {
        // Act
        ticketAttachment.setId(1);

        // Assert
        assertEquals(1, ticketAttachment.getId());
    }

    @Test
    public void shouldSetTicketId() {
        // Act
        ticketAttachment.setTicketId(1);

        // Assert
        assertEquals(1, ticketAttachment.getTicketId());
    }

    @Test
    public void shouldSetTicketPostId() {
        // Act
        ticketAttachment.setTicketPostId(1);

        // Assert
        assertEquals(1, ticketAttachment.getTicketPostId());
    }

    @Test
    public void shouldSetFileName() {
        // Act
        ticketAttachment.setFileName(TEST_FILE_NAME);

        // Assert
        assertEquals(TEST_FILE_NAME, ticketAttachment.getFileName());
    }

    @Test
    public void shouldSetFileSize() {
        // Act
        ticketAttachment.setFileSize(TEST_FILE_SIZE_INT);

        // Assert
        assertEquals(TEST_FILE_SIZE_INT, ticketAttachment.getFileSize());
    }

    @Test
    public void shouldSetFileType() {
        // Act
        ticketAttachment.setFileType(TEST_FILE_TYPE);

        // Assert
        assertEquals(TEST_FILE_TYPE, ticketAttachment.getFileType());
    }

    @Test
    public void shouldSetDateLine() {
        // Act
        ticketAttachment.setDateLine(TEST_DATE_LINE);

        // Assert
        assertEquals(TEST_DATE_LINE, ticketAttachment.getDateLine());
    }

    @Test
    public void shouldSetContents() throws Exception {
        // Arrange
        byte[] contents = new byte[100];

        // Act
        ticketAttachment.setContents(contents);

        // Assert
        assertEquals(100, ticketAttachment.getContents().length);
    }

    @Test
    public void shouldSetTicketWhenRefreshIsFalse() throws Exception {
        // Arrange
        Ticket ticket = new Ticket();

        // Act
        ticket.setId(1);
        ticketAttachment.setTicket(ticket);

        // Assert
        assertEquals(1, ticketAttachment.getTicket().getId());
    }

    @Test
    public void shouldSetTicketWhenRefreshIsTrue() throws Exception {
        // Act
        ticketAttachment.setTicket(new Ticket());

        // Assert
        assertNull(ticketAttachment.getTicket(true));
    }

    @Test
    public void shouldSetTicketPostWhenRefreshIsFalse() throws Exception {
        // Arrange
        Ticket ticket = new Ticket();
        TicketPost ticketPost = new TicketPost();

        // Act
        ticket.setId(1);

        ticketPost.setId(1);
        ticketPost.setTicket(ticket);
        ticketAttachment.setTicketPost(ticketPost);

        // Assert
        assertEquals(1, ticketAttachment.getTicketPost(false).getId());
    }

    @Test
    public void shouldSetTicketPostWhenRefreshIsTrue() throws Exception {
        // Arrange
        Ticket ticket = new Ticket();
        TicketPost ticketPost = new TicketPost();

        // Act
        ticket.setId(1);

        ticketPost.setId(0);
        ticketPost.setTicket(ticket);
        ticketAttachment.setTicketPost(ticketPost);

        // Assert
        assertNull(ticketAttachment.getTicketPost(true));
    }

    @Test
    public void shouldGetIdArray() {
        // Act
        ticketAttachment.setTicketId(1);
        ticketAttachment.setId(1);

        // Assert
        assertEquals(2, ticketAttachment.getIdArray().size());
    }

    @Test
    public void shouldCheckGet() {

    }

    @Test(expected = KayakoException.class)
    public void shouldGetAllWhenController() throws Exception {
        // Assert
        assertNull(TicketAttachment.getAll(TEST_CONTROLLER_NAME));
    }

    @Test(expected = NullPointerException.class)
    public void shouldGetAllWhenTicketId() {
        // Act
        PowerMock.mockStatic(KEntity.class);
        EasyMock.expect(KEntity.getAll(EasyMock.anyString(), EasyMock.anyObject())).andReturn(new RawArrayElement());

        // Assert
        assertEquals(TEST_ELEMENT, TicketAttachment.getAll(1).toString());
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();

        // Act
        components.add(new RawArrayElement(TEST_ID));
        components.add(new RawArrayElement(TEST_TICKET_ID));
        components.add(new RawArrayElement(TEST_TICKET_POST_ID));
        components.add(new RawArrayElement(TEST_FILE_NAME));
        components.add(new RawArrayElement(TEST_FILE_SIZE));
        components.add(new RawArrayElement(TEST_FILE_TYPE));
        components.add(new RawArrayElement(TEST_DATELINE));
        components.add(new RawArrayElement(TEST_CONTENTS));

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_TICKET_ATTACHMENT_OBJECT, ticketAttachment.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        ticketAttachment = new TicketAttachment();
        byte[] contents = new byte[100];

        // Act
        ticketAttachment.setId(1);
        ticketAttachment.setTicketId(1);
        ticketAttachment.setFileName(TEST_FILE_NAME);
        ticketAttachment.setContents(contents);

        // Assert
        assertEquals(4, ticketAttachment.buildHashMap().size());
    }

    @Test
    public void shouldCreateTicketAttachmentWithTicketPostAndContentsAndFileName() throws Exception {
        // Arrange
        TicketPost ticketPost = new TicketPost();
        Ticket ticket = new Ticket();
        byte[] contents = new byte[100];

        // Act
        ticket.setId(1);
        ticketPost.setTicket(ticket);
        ticketAttachment = TicketAttachment.createTicketAttachment(ticketPost, contents, TEST_FILE_NAME);

        // Assert
        collector.checkThat(ticketAttachment.getTicketPost(), equalTo(ticketPost));
        collector.checkThat(ticketAttachment.getContents(), equalTo(contents));
        collector.checkThat(ticketAttachment.getFileName(), equalTo(TEST_FILE_NAME));
    }

    @Test(expected = KayakoException.class)
    public void shouldUpdate() throws Exception {
        // Assert
        assertEquals(null, ticketAttachment.update());
    }

    @Test
    public void shouldRefineToArray() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();

        // Act
        components.add(new RawArrayElement(TEST_ID));
        components.add(new RawArrayElement(TEST_TICKET_ID));
        components.add(new RawArrayElement(TEST_TICKET_POST_ID));
        components.add(new RawArrayElement(TEST_FILE_NAME));
        components.add(new RawArrayElement(TEST_FILE_SIZE));
        components.add(new RawArrayElement(TEST_FILE_TYPE));
        components.add(new RawArrayElement(TEST_DATELINE));
        components.add(new RawArrayElement(TEST_CONTENTS));

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(0, TicketAttachment.getAllAttachments(1).size());
    }

}
