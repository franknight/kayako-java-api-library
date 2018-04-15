package com.kayako.api.ticket;

import com.kayako.api.enums.TicketCreatorEnum;
import com.kayako.api.enums.TicketPostCreatorEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import com.kayako.api.user.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TicketPostTest {

    private TicketPost ticketPost;
    private static final String TEST_OBJECT_XML_NAME = "XML";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_SUBJECT_NAME = "Subject";
    private static final String TEST_TICKET_POST_OBJECT = "TicketPost- ID: 0";
    private static final String TEST_ELEMENT_NAME = "XML";
    private static final String TEST_ID = "id";
    private static final String TEST_TICKET_ID = "ticketid";
    private static final String TEST_DATELINE = "dateline";
    private static final String TEST_USER_ID = "userid";
    private static final String TEST_FULL_NAME= "fullname";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_EMAIL_TO = "emailto";
    private static final String TEST_IP_ADDRESS = "ipaddress";
    private static final String TEST_HAS_ATTACHMENT = "hasattachment";
    private static final String TEST_CREATOR = "creator";
    private static final String TEST_IS_THIRD_PARTY = "isthirdparty";
    private static final String TEST_IS_HTML = "ishtml";
    private static final String TEST_IS_EMAILED = "isemailed";
    private static final String TEST_STAFF_ID = "staffid";
    private static final String TEST_IS_SURVEY_COMMENT = "issurveycomment";
    private static final String TEST_CONTENT_NAME = "contents";
    private static final String TEST_IS_PRIVATE = "isprivate";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticketPost = new TicketPost();
    }

    @Test
    public void shouldGetIdArray() {
        // Act
        ticketPost.setTicketId(1);
        ticketPost.setId(1);

        // Assert
        assertEquals(2, ticketPost.getIdArray().size());
    }

    @Test
    public void shouldSetReadOnly() {
        // Act
        ticketPost.setReadOnly(true);

        // Assert
        assertTrue(ticketPost.getReadOnly());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        TicketPost.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, TicketPost.getObjectXmlName());
    }

    @Test
    public void shouldSetController() {
        // Act
        TicketPost.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, TicketPost.getController());
    }

    @Test
    public void shouldSetTicketId() {
        // Act
        ticketPost.setTicketId(1);

        // Assert
        assertEquals(1, ticketPost.getTicketId());
    }

    @Test
    public void shouldSetFullName() {
        // Act
        ticketPost.setFullName(TEST_FULL_NAME);

        // Assert
        assertEquals(TEST_FULL_NAME, ticketPost.getFullName());
    }

    @Test
    public void shouldSetUserIdIsNotZero() throws Exception {
        // Act
        ticketPost.setUserId(1);

        // Assert
        collector.checkThat(ticketPost.getUserId(), equalTo(ticketPost.getCreator()));
        collector.checkThat(ticketPost.getStaffId(), equalTo(0));
        collector.checkThat(ticketPost.getStaff(), equalTo(null));
        collector.checkThat(ticketPost.getUserId(), equalTo(1));
    }

    @Test
    public void shouldSetUserIdIsZero() throws Exception {
        // Act
        ticketPost.setUserId(0);

        // Assert
        assertNull(ticketPost.getUser());
    }

    @Test
    public void shouldSetDateLine() {
        // Act
        ticketPost.setDateLine(1);

        // Assert
        assertEquals(1, ticketPost.getDateLine());
    }

    @Test
    public void shouldSetEmail() {
        // Act
        ticketPost.setEmail(TEST_EMAIL);

        // Assert
        assertEquals(TEST_EMAIL, ticketPost.getEmail());
    }

    @Test
    public void shouldSetStaffId() throws Exception {
        // Act
        ticketPost.setStaffId(1);

        // Assert
        collector.checkThat(ticketPost.getUserId(), equalTo(0));
        collector.checkThat(ticketPost.getUser(), equalTo(null));
        collector.checkThat(ticketPost.getCreator(), equalTo(ticketPost.getStaffId()));
        collector.checkThat(TicketCreatorEnum.STAFF.getString(), equalTo(ticketPost.getCreatorType().getString()));
    }

    @Test
    public void shouldSetEmailTo() {
        // Act
        ticketPost.setEmailTo(TEST_EMAIL);

        // Assert
        assertEquals(TEST_EMAIL, ticketPost.getEmailTo());
    }

    @Test
    public void shouldSetIpAddress() {
        // Act
        ticketPost.setIPAddress(TEST_IP_ADDRESS);

        // Assert
        assertEquals(TEST_IP_ADDRESS, ticketPost.getIPAddress());
    }

    @Test
    public void shouldSetHasAttachment() {
        // Act
        ticketPost.setHasAttachment(true);

        // Assert
        assertTrue(ticketPost.getHasAttachment());
    }

    @Test
    public void shouldSetHTML() {
        // Act
        ticketPost.setHTML(true);

        // Assert
        assertTrue(ticketPost.getHTML());
    }

    @Test
    public void shouldSetThirdParty() {
        // Act
        ticketPost.setThirdParty(true);

        // Assert
        assertTrue(ticketPost.getThirdParty());
    }

    @Test
    public void shouldSetEmailed() {
        // Act
        ticketPost.setEmailed(true);

        // Assert
        assertTrue(ticketPost.getEmailed());
    }

    @Test
    public void shouldSetSurveyComment() {
        // Act
        ticketPost.setSurveyComment(true);

        // Assert
        assertTrue(ticketPost.getSurveyComment());
    }

    @Test
    public void shouldSetContents() {
        // Act
        ticketPost.setContents(TEST_CONTENT_NAME);

        // Assert
        assertEquals(TEST_CONTENT_NAME, ticketPost.getContents());
    }

    @Test
    public void shouldSetSubject() {
        // Act
        ticketPost.setSubject(TEST_SUBJECT_NAME);

        // Assert
        assertEquals(TEST_SUBJECT_NAME, ticketPost.getSubject());
    }

    @Test
    public void shouldSetPrivate() {
        // Act
        ticketPost.setPrivate(true);

        // Assert
        assertTrue(ticketPost.isPrivate());
    }

    @Test
    public void shouldSetCreatorWithCreatorIdAndEnum() {
        ticketPost.setCreator(1, TicketPostCreatorEnum.STAFF);
        collector.checkThat(TicketPostCreatorEnum.STAFF, equalTo(ticketPost.getCreatorType()));

        ticketPost.setCreator(2, TicketPostCreatorEnum.USER);
        collector.checkThat(TicketPostCreatorEnum.USER, equalTo(ticketPost.getCreatorType()));
    }

    @Test
    public void shouldSetCreatorWithStaff() {
        // Arrange
        Staff staff = new Staff();

        // Act
        ticketPost.setCreator(staff);
    }

    @Test
    public void shouldSetCreatorWithEnum() {
        // Act
        ticketPost.setCreator(TicketPostCreatorEnum.USER);

        // Assert
        assertEquals(TicketPostCreatorEnum.USER, ticketPost.getCreatorType());
    }

    @Test
    public void shouldSetCreatorWithUser() throws Exception {
        // Arrange
        User user = new User();

        // Act
        ticketPost.setCreator(user);

        // Assert
        assertEquals(0, ticketPost.getUser().getId());
    }

    @Test
    public void shouldSetAttachmentsWhenRefreshIsFalse() throws Exception {
        // Arrange
        TicketAttachment ticketAttachment = new TicketAttachment();
        ArrayList<TicketAttachment> attachments = new ArrayList<>();

        // Act
        attachments.add(ticketAttachment);
        ticketPost.setAttachments(attachments);

        // Assert
        assertEquals(1, ticketPost.getAttachments().size());
    }

    @Test
    public void shouldCreateTicketPostWithTicketAndContentsAndStaff() {
        // Arrange
        Ticket ticket = new Ticket();
        Staff staff = new Staff();

        // Act
        TicketPost.createNew(ticket, TEST_CONTENT_NAME, staff);
    }

    @Test
    public void shouldCreateTicketPostWithTicketAndContentsAndUser() {
        // Arrange
        Ticket ticket = new Ticket();
        User user = new User();

        // Act
        TicketPost.createNew(ticket, TEST_CONTENT_NAME, user);
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        TicketPost ticketPost1 = new TicketPost();
        TicketPost ticketPost2 = new TicketPost();

        // Act
        ticketPost1.setTicketId(1);
        ticketPost1.setSubject(TEST_SUBJECT_NAME);
        ticketPost1.setContents(TEST_CONTENT_NAME);
        ticketPost1.setPrivate(true);
        ticketPost1.setCreator(TicketPostCreatorEnum.STAFF);

        ticketPost2.setTicketId(2);
        ticketPost2.setSubject(TEST_SUBJECT_NAME);
        ticketPost2.setContents(TEST_CONTENT_NAME);
        ticketPost2.setPrivate(true);
        ticketPost2.setCreator(TicketPostCreatorEnum.USER);

        // Assert
        collector.checkThat(ticketPost1.buildHashMap().size(), equalTo(5));
        collector.checkThat(ticketPost2.buildHashMap().size(), equalTo(5));
    }

    @Test
    public void shouldCheckToString() {
        // Assert
        assertEquals(TEST_TICKET_POST_OBJECT, ticketPost.toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldGetWhenTicketIdAndId() throws Exception {
        // Act
        assertEquals("", TicketPost.get(1,1));
    }

    @Test
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();

        // Act
        components.add(new RawArrayElement(TEST_ID));
        components.add(new RawArrayElement(TEST_TICKET_ID));
        components.add(new RawArrayElement(TEST_DATELINE));
        components.add(new RawArrayElement(TEST_USER_ID));
        components.add(new RawArrayElement(TEST_FULL_NAME));
        components.add(new RawArrayElement(TEST_EMAIL));
        components.add(new RawArrayElement(TEST_EMAIL_TO));
        components.add(new RawArrayElement(TEST_IP_ADDRESS));
        components.add(new RawArrayElement(TEST_HAS_ATTACHMENT));
        components.add(new RawArrayElement(TEST_CREATOR));
        components.add(new RawArrayElement(TEST_IS_THIRD_PARTY));
        components.add(new RawArrayElement(TEST_IS_HTML));
        components.add(new RawArrayElement(TEST_IS_EMAILED));
        components.add(new RawArrayElement(TEST_STAFF_ID));
        components.add(new RawArrayElement(TEST_IS_SURVEY_COMMENT));
        components.add(new RawArrayElement(TEST_CONTENT_NAME));
        components.add(new RawArrayElement(TEST_IS_PRIVATE));

        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(TEST_TICKET_POST_OBJECT, ticketPost.populate(rawArrayElement).toString());
    }

}
