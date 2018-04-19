package com.kayako.api.ticket;

import com.kayako.api.department.Department;
import com.kayako.api.enums.CreationModeEnum;
import com.kayako.api.enums.CreationTypeEnum;
import com.kayako.api.enums.FlagEnum;
import com.kayako.api.enums.TicketCreatorEnum;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import com.kayako.api.user.User;
import com.kayako.api.user.UserOrganization;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyInt;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest({User.class, Staff.class, KEntity.class, TicketStatus.class,
        TicketPriority.class, TicketType.class, TicketNote.class, UserOrganization.class})
public class TicketTest {

    private Ticket ticket;
    private static final int TEST_TIME = 36_000;
    private static final String TEST_ID = "id";
    private static final String TEST_TYPE = "type";
    private static final String TEST_FLAG_TYPE = "flagtype";
    private static final String TEST_DISPLAY_ID = "displayid";
    private static final String TEST_DEPARTMENT_ID = "departmentid";
    private static final String TEST_STATUS_IS = "statusid";
    private static final String TEST_PRIORITY_ID = "priorityid";
    private static final String TEST_TYPE_ID = "typeid";
    private static final String TEST_USER_ID = "userid";
    private static final String TEST_USER_ORGANIZATION = "userorganization";
    private static final String TEST_USER_ORGANIZATION_ID = "userorganizationid";
    private static final String TEST_OWNER_STAFF_ID = "ownerstaffid";
    private static final String TEST_OWNER_STAFF_NAME = "ownerstaffname";
    private static final String TEST_FULL_NAME = "fullname";
    private static final String TEST_EMAIL = "email";
    private static final String TEST_LAST_REPLIER = "lastreplier";
    private static final String TEST_SUBJECT_NAME = "subject";
    private static final String TEST_CREATION_TIME = "creationtime";
    private static final String TEST_LAST_ACTIVITY = "lastactivity";
    private static final String TEST_LAST_STAFF_REPLY = "laststaffreply";
    private static final String TEST_LAST_USER_REPLY = "lastuserreply";
    private static final String TEST_SLA_PLAN_ID = "slaplanid";
    private static final String TEST__NEXT_REPLY_DUE = "nextreplydue";
    private static final String TEST_RESOLUTION_DUE = "resolutiondue";
    private static final String TEST_REPLIES = "replies";
    private static final String TEST_IP_ADDRESS = "ipaddress";
    private static final String TEST_CREATOR = "creator";
    private static final String TEST_CREATION_MODE = "creationmode";
    private static final String TEST_CREATION_TYPE = "creationtype";
    private static final String TEST_IS_ESCALATED = "isescalated";
    private static final String TEST_ESCALATION_RULE_ID = "escalationruleid";
    private static final String TEST_TEMPLATE_GROUP_ID = "templategroupid";
    private static final String TEST_TEMPLATE_GROUP_NAME = "templategroupname";
    private static final String TEST_TAGS = "tags";
    private static final String TEST_WATCHER = "watcher";
    private static final String TEST_WORKFLOW = "workflow";
    private static final String TEST_NOTE = "note";
    private static final String TEST_POSTS = "posts";
    private static final String TEST_CONTENT_NAME = "Content";
    private static final String TEST_CONTROLLER_NAME = "Controller";
    private static final String TEST_OBJECT_XML_NAME = "ticket";
    private static final String TEST_ELEMENT_NAME = "ticket";
    private static final String TEST_CUSTOM_FIELD_GROUP_CONTROLLER = "CustomFieldGroupController";
    private static final String TEST_OBJECT_ID_FIELD = "ObjectIdField";
    private static final String TEST_DISPLAY_ID_INT = "1";
    private static final String TEST_ORGANIZATION_NAME = "Organization";
    private static final String TEST_TICKET_POST_OBJECT = "TicketPost- ID: 0";
    private static final String TEST_TICKET_STATISTICS = "<Auto Element>\n"
                                                    + "null\n"
                                                    + "</Auto Element>\n";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        ticket = new Ticket();
    }

    @Test
    public void shouldCreatedTicketWithDepartmentAndTicketCreatorEnumAndContentsAndSubject() throws Exception {
        // Arrange
        Department department = new Department();

        // Act
        ticket = new Ticket(department, TicketCreatorEnum.USER, TEST_CONTENT_NAME, TEST_SUBJECT_NAME);

        // Assert
        collector.checkThat(ticket.getDepartment(), equalTo(department));
        collector.checkThat(ticket.getCreatorType(), equalTo(TicketCreatorEnum.USER));
        collector.checkThat(ticket.getContents(), equalTo(TEST_CONTENT_NAME));
        collector.checkThat(ticket.getSubject(), equalTo(TEST_SUBJECT_NAME));
    }

    @Test
    public void shouldCreatedTicketWithDepartmentAndStaffAndContentsAndSubject() throws Exception {
        // Arrange
        Department department = new Department();
        Staff staff = new Staff();

        // Act
        ticket = new Ticket(department, staff, TEST_CONTENT_NAME, TEST_SUBJECT_NAME);

        // Assert
        assertEquals(staff, ticket.getStaff());
    }

    @Test
    public void shouldCreatedTicketWithDepartmentAndUserAndContentsAndSubject() throws Exception {
        // Arrange
        Department department = new Department();
        User user = new User();

        // Act
        ticket = new Ticket(department, user, TEST_CONTENT_NAME, TEST_SUBJECT_NAME);

        // Assert
        assertEquals(user, ticket.getUser());
    }

    @Test
    public void shouldCreatedTicketWithDepartmentAndCreatorFullNameAndEmailAndContentsAndSubject() throws Exception {
        // Arrange
        Department department = new Department();
        User user = new User();

        // Act
        ticket = new Ticket(department, TEST_FULL_NAME, TEST_EMAIL, TEST_CONTENT_NAME, TEST_SUBJECT_NAME);

        // Assert
        collector.checkThat(ticket.getFullName(), equalTo(TEST_FULL_NAME));
        collector.checkThat(ticket.getEmail(), equalTo(TEST_EMAIL));
    }

    @Test
    public void shouldSetCreatorIdWhenStaff() throws Exception {
        // Act
        ticket.setCreatorId(0, TicketCreatorEnum.STAFF);

        // Assert
        assertEquals(0, ticket.getStaffId());
    }

    @Test
    public void shouldSetCreatorIdWhenUser() throws Exception {
        // Act
        ticket.setCreatorId(0, TicketCreatorEnum.USER);

        // Assert
        assertEquals(0, ticket.getStaffId());
    }

    @Test
    public void shouldSetCreatorWhenUser() throws Exception {
        // Arrange
        User newUser = new User().setId(1).setFullName(TEST_FULL_NAME).setEmail(TEST_EMAIL);

        // Act
        mockStatic(User.class);
        expect(User.get(anyInt())).andReturn(newUser);
        replay(User.class);
        ticket.setCreator(1, TicketCreatorEnum.USER);

        // Assert
        assertEquals(1, ticket.getCreator());
    }

    @Test
    public void shouldSetCreatorWhenStaff() throws Exception {
        // Arrange
        Staff newStaff = new Staff();

        // Act
        newStaff.setFullName(TEST_FULL_NAME);
        newStaff.setEmail(TEST_EMAIL);

        mockStatic(Staff.class);
        expect(Staff.get(anyInt())).andReturn(newStaff);
        replay(Staff.class);

        ticket.setCreator(1, TicketCreatorEnum.STAFF);

        // Assert
        assertEquals(1, ticket.getCreator());
    }

    @Test
    public void shouldSetController() {
        // Act
        Ticket.setController(TEST_CONTROLLER_NAME);

        // Assert
        assertEquals(TEST_CONTROLLER_NAME, Ticket.getController());
    }

    @Test
    public void shouldSetObjectXmlName() {
        // Act
        Ticket.setObjectXmlName(TEST_OBJECT_XML_NAME);

        // Assert
        assertEquals(TEST_OBJECT_XML_NAME, Ticket.getObjectXmlName());
    }

    @Test
    public void shouldSetCustomFieldGroupController() {
        // Act
        Ticket.setCustomFieldGroupController(TEST_CUSTOM_FIELD_GROUP_CONTROLLER);

        // Assert
        assertEquals(TEST_CUSTOM_FIELD_GROUP_CONTROLLER, Ticket.getCustomFieldGroupController());
    }

    @Test
    public void shouldSetObjectIdField() {
        // Act
        Ticket.setObjectIdField(TEST_OBJECT_ID_FIELD);

        // Assert
        assertEquals(TEST_OBJECT_ID_FIELD, Ticket.getObjectIdField());
    }

    @Test
    public void shouldSetDefaultStatusId() {
        // Act
        Ticket.setDefaultStatusId(1);

        // Assert
        assertEquals(1, Ticket.getDefaultStatusId());
    }

    @Test
    public void shouldSetDefaultPriorityId() {
        // Act
        Ticket.setDefaultPriorityId(1);

        // Assert
        assertEquals(1, Ticket.getDefaultPriorityId());
    }

    @Test
    public void shouldSetDefaultTypeId() {
        // Act
        Ticket.setDefaultTypeId(1);

        // Assert
        assertEquals(1, Ticket.getDefaultTypeId());
    }

    @Test
    public void shouldSetAutoCreateUser() {
        // Act
        Ticket.setAutoCreateUser(true);

        // Assert
        assertTrue(Ticket.getAutoCreateUser());
    }

    @Test
    public void shouldSetId() {
        // Act
        ticket.setId(1);

        // Assert
        assertEquals(1, ticket.getId());
    }

    @Test
    public void shouldSetFlagType() {
        // Act
        ticket.setFlagType(FlagEnum.BLUE);

        // Assert
        assertEquals(FlagEnum.BLUE, ticket.getFlagType());
    }

    @Test
    public void shouldSetDisplayId() {
        // Act
        ticket.setDisplayId(TEST_DISPLAY_ID_INT);

        // Assert
        assertEquals(TEST_DISPLAY_ID_INT, ticket.getDisplayId());
    }

    @Test
    public void shouldSetDepartmentId() {
        // Act
        ticket.setDepartmentId(1);

        // Assert
        assertEquals(1, ticket.getDepartmentId());
    }

    @Test
    public void shouldSetTicketPriorityId() {
        // Act
        ticket.setTicketPriorityId(1);

        // Assert
        assertEquals(1, ticket.getTicketPriorityId());
    }

    @Test
    public void shouldSetTicketTypeId() {
        // Act
        ticket.setTicketTypeId(1);

        // Assert
        assertEquals(1, ticket.getTicketTypeId());
    }

    @Test
    public void shouldSetUserId() throws Exception {
        // Arrange
        User newUser = new User().setId(1).setFullName(TEST_FULL_NAME).setEmail(TEST_EMAIL);

        // Act
        mockStatic(User.class);
        expect(User.get(anyInt())).andReturn(newUser);
        replay(User.class);

        ticket.setUserId(1);

        // Assert
        assertEquals(1, ticket.getUserId());
    }

    @Test
    public void shouldSetStaffId() throws Exception {
        // Arrange
        Staff newStaff = new Staff();

        // Act
        newStaff.setFullName(TEST_FULL_NAME);
        newStaff.setEmail(TEST_EMAIL);

        mockStatic(Staff.class);
        expect(Staff.get(anyInt())).andReturn(newStaff);
        replay(Staff.class);

        ticket.setStaffId(1);

        // Assert
        assertEquals(1, ticket.getStaffId());

    }

    @Test
    public void shouldSetOrganizationName() {
        // Act
        ticket.setUserOrganizationName(TEST_ORGANIZATION_NAME);

        // Assert
        assertEquals(TEST_ORGANIZATION_NAME, ticket.getUserOrganizationName());
    }

    @Test
    public void shouldSetOrganizationId() {
        // Act
        ticket.setUserOrganizationId(1);

        // Assert
        assertEquals(1, ticket.getUserOrganizationId());
    }

    @Test
    public void shouldSetOwnerStaffId() {
        // Act
        ticket.setOwnerStaffId(1);

        // Assert
        assertEquals(1, ticket.getOwnerStaffId());
    }

    @Test
    public void shouldSetOwnerStaffName() {
        // Act
        ticket.setOwnerStaffName(TEST_FULL_NAME);

        // Assert
        assertEquals(TEST_FULL_NAME, ticket.getOwnerStaffName());
    }

    @Test
    public void shouldSetLastRepplier() {
        // Act
        ticket.setLastReplier(TEST_FULL_NAME);

        // Assert
        assertEquals(TEST_FULL_NAME, ticket.getLastReplier());
    }

    @Test
    public void shouldSetSubject() {
        // Act
        ticket.setSubject(TEST_SUBJECT_NAME);

        // Assert
        assertEquals(TEST_SUBJECT_NAME, ticket.getSubject());
    }

    @Test
    public void shouldSetCreationTime() {
        // Act
        ticket.setCreationTime(TEST_TIME);

        // Assert
        assertEquals(TEST_TIME, ticket.getCreationTime());
    }

    @Test
    public void shouldSetLastActivity() {
        // Act
        ticket.setLastActivity(1);

        // Assert
        assertEquals(1, ticket.getLastActivity());
    }

    @Test
    public void shouldSetLastStaffReply() {
        // Act
        ticket.setLastStaffReply(1);

        // Assert
        assertEquals(1, ticket.getLastStaffReply());
    }

    @Test
    public void shouldSetLastUserReply() {
        // Act
        ticket.setLastUserReply(1);

        // Assert
        assertEquals(1, ticket.getLastUserReply());
    }

    @Test
    public void shouldSetSLAPlanId() {
        // Act
        ticket.setSLAPlanId(1);

        // Assert
        assertEquals(1, ticket.getSLAPlanId());
    }

    @Test
    public void shouldSetNextReplyDue() {
        // Act
        ticket.setNextReplyDue(1);

        // Assert
        assertEquals(1, ticket.getNextReplyDue());
    }

    @Test
    public void shouldSetResolutionDue() {
        // Act
        ticket.setResolutionDue(1);

        // Assert
        assertEquals(1, ticket.getResolutionDue());
    }

    @Test
    public void shouldSetReplies() {
        // Act
        ticket.setReplies(1);

        // Assert
        assertEquals(1, ticket.getReplies());
    }

    @Test
    public void shouldSetIpAddress() {
        // Act
        ticket.setIpAddress(TEST_IP_ADDRESS);

        // Assert
        assertEquals(TEST_IP_ADDRESS, ticket.getIpAddress());
    }

    @Test
    public void shouldSetCreationMode() {
        // Act
        ticket.setCreationMode(CreationModeEnum.API);

        // Assert
        assertEquals(CreationModeEnum.API, ticket.getCreationMode());
    }

    @Test
    public void shouldSetCreationType() {
        // Act
        ticket.setCreationType(CreationTypeEnum.PHONE);

        // Assert
        assertEquals(CreationTypeEnum.PHONE, ticket.getCreationType());
    }

    @Test
    public void shouldSetEscalated() {
        // Act
        ticket.setEscalated(true);

        // Assert
        assertTrue(ticket.getEscalated());
    }

    @Test
    public void shouldSetEscalationRuleId() {
        // Act
        ticket.setEscalationRuleId(1);

        // Assert
        assertEquals(1, ticket.getEscalationRuleId());
    }

    @Test
    public void shouldSetTemplateGroupId() {
        // Act
        ticket.setTemplateGroupId(1);

        // Assert
        assertEquals(1, ticket.getTemplateGroupId());
    }

    @Test
    public void shouldSetTemplateGroupName() {
        // Act
        ticket.setTemplateGroupName(TEST_TEMPLATE_GROUP_NAME);

        // Assert
        assertEquals(TEST_TEMPLATE_GROUP_NAME, ticket.getTemplateGroupName());
    }

    @Test
    public void shouldSetTemplateGroupWithName() {
        // Act
        ticket.setTemplateGroup(TEST_TEMPLATE_GROUP_NAME);

        // Assert
        assertEquals(TEST_TEMPLATE_GROUP_NAME, ticket.getTemplateGroupName());
    }

    @Test
    public void shouldSetTemplateGroupWithId() {
        // Act
        ticket.setTemplateGroup(1);

        // Assert
        assertEquals(1, ticket.getTemplateGroupId());
    }

    @Test
    public void shouldSetTags() {
        // Act
        ticket.setTags(TEST_TAGS);

        // Assert
        assertEquals(TEST_TAGS, ticket.getTags());
    }

    @Test
    public void shouldSetWatchers() {
        // Arrange
        HashMap<Integer, String> watchers = new HashMap<>();

        // Act
        ticket.setWatchers(watchers);
        ticket.addWatcher(1, TEST_FULL_NAME);

        // Assert
        assertEquals(1, ticket.getWatchers().size());
    }

    @Test
    public void shouldSetWorkflows() {
        // Arrange
        HashMap<Integer, String> workflows = new HashMap<>();

        // Act
        ticket.setWorkflows(workflows);
        ticket.addWorkflow(1, TEST_CONTENT_NAME);

        // Assert
        assertEquals(1, ticket.getWorkflows().size());
    }

    @Test
    public void shouldIgnoreAutoResponder() {
        // Act
        ticket.setIgnoreAutoResponder(true);

        // Assert
        assertTrue(ticket.getIgnoreAutoResponder());
    }

    @Test
    public void shouldSetStatusWhenRefreshIsTrue() throws Exception {
        // Arrange
        TicketStatus ticketStatus = new TicketStatus();

        // Act
        ticketStatus.setId(1);

        mockStatic(TicketStatus.class);
        expect(TicketStatus.get(ticketStatus.getId())).andReturn(ticketStatus);
        expect(ticketStatus.toString()).andReturn("TicketStatus");
        expect(ticketStatus.getClass().getSimpleName()).andReturn("TicketStatus");
        replay(TicketStatus.class);

        ticket.setStatus(ticketStatus);

        // Assert
        assertEquals(ticketStatus, ticket.getStatus(true));
    }

    @Test
    public void shouldSetStatusWhenRefreshIsFalse() throws Exception {
        // Arrange
        TicketStatus ticketStatus = new TicketStatus();

        // Act
        ticketStatus.setId(1);
        ticket.setStatus(ticketStatus);

        // Assert
        assertEquals(ticketStatus, ticket.getStatus());
    }

    @Test
    public void shouldSetPriorityWhenRefreshIsTrue() throws Exception {
        // Arrange
        TicketPriority ticketPriority = new TicketPriority();

        // Act
        ticketPriority.setId(1);

        mockStatic(TicketPriority.class);
        expect(TicketPriority.get(ticketPriority.getId())).andReturn(ticketPriority);
        expect(ticketPriority.toString()).andReturn("TicketPriority");
        expect(ticketPriority.getClass().getSimpleName()).andReturn("TicketPriority");
        replay(TicketPriority.class);

        ticket.setPriority(ticketPriority);

        // Assert
        assertEquals(ticketPriority, ticket.getPriority(true));
    }

    @Test
    public void shouldSetPriorityWhenRefreshIsFalse() throws Exception {
        // Arrange
        TicketPriority ticketPriority = new TicketPriority();

        // Act
        ticketPriority.setId(1);
        ticket.setPriority(ticketPriority);

        // Assert
        assertEquals(ticketPriority, ticket.getPriority());
    }

    @Test
    public void shouldSetTypeWhenRefreshIsTrue() throws Exception {
        // Arrange
        TicketType ticketType = new TicketType();

        // Act
        ticketType.setId(1);

        mockStatic(TicketType.class);
        expect(TicketType.get(ticketType.getId())).andReturn(ticketType);
        expect(ticketType.toString()).andReturn("TicketType");
        expect(ticketType.getClass().getSimpleName()).andReturn("TicketType");
        replay(TicketType.class);

        ticket.setType(ticketType);

        // Assert
        assertEquals(ticketType, ticket.getType(true));
    }

    @Test
    public void shouldSetTypeWhenRefreshIsFalse() throws Exception {
        // Arrange
        TicketType ticketType = new TicketType();

        // Act
        ticketType.setId(1);
        ticket.setType(ticketType);

        // Assert
        assertEquals(ticketType, ticket.getType());
    }

    @Test
    public void shouldSetUserOrganizationWhenRefreshIsFalse() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();

        // Act
        userOrganization.setId(1);
        ticket.setUserOrganization(userOrganization);

        // Assert
        assertEquals(userOrganization, ticket.getUserOrganization());
    }

    @Test
    public void shouldSetUserOrganizationWhenRefreshIsTrue() throws Exception {
        // Arrange
        UserOrganization userOrganization = new UserOrganization();

        // Act
        userOrganization.setId(1);

        mockStatic(UserOrganization.class);
        expect(UserOrganization.get(userOrganization.getId())).andReturn(userOrganization);
        expect(userOrganization.toString()).andReturn("UserOrganization");
        expect(userOrganization.getClass().getSimpleName()).andReturn("UserOrganization");
        replay(UserOrganization.class);

        ticket.setUserOrganization(userOrganization);

        // Assert
        assertEquals(userOrganization, ticket.getUserOrganization(true));
    }

    @Test
    public void shouldSetOwnerStaffWhenRefreshIsTrue() throws Exception {
        // Arrange
        Staff ownerStaff = new Staff();

        // Act
        ownerStaff.setId(1);

        mockStatic(Staff.class);
        expect(Staff.get(ownerStaff.getId())).andReturn(ownerStaff);
        expect(ownerStaff.toString()).andReturn("Staff");
        expect(ownerStaff.getClass().getSimpleName()).andReturn("Staff");
        replay(Staff.class);

        ticket.setOwnerStaff(ownerStaff);

        // Assert
        assertEquals(ownerStaff, ticket.getOwnerStaff(true));
    }

    @Test
    public void shouldSetOwnerStaffWhenRefreshIsFalse() throws Exception {
        // Arrange
        Staff ownerStaff = new Staff();

        // Act
        ownerStaff.setId(1);
        ticket.setOwnerStaff(ownerStaff);

        // Assert
        assertEquals(ownerStaff, ticket.getOwnerStaff());
    }

    @Test
    public void shouldSetNotesWhenRefreshTrue() throws Exception {
        // Arrange
        ArrayList<TicketNote> notes = new ArrayList<>();
        ArrayList<TicketNote> noteArrayList = new ArrayList<>();
        TicketNote ticketNote = new TicketNote();

        // Act
        ticketNote.setId(1);
        notes.add(ticketNote);
        noteArrayList.add(ticketNote);
        ticket.setNotes(notes);

        mockStatic(TicketNote.class);
        expect(TicketNote.getAllNotes(anyInt())).andReturn(noteArrayList);
        replay(TicketNote.class);

        ticket.addNote(new TicketNote());

        // Assert
        assertEquals(3, ticket.getNotes(true).size());
    }

    @Test
    public void shouldSetNotesWhenRefreshTrueWhenRefreshIsFalse() throws Exception {
        // Arrange
        ArrayList<TicketNote> notes = new ArrayList<>();
        TicketNote ticketNote = new TicketNote();

        // Act
        ticketNote.setId(1);
        notes.add(ticketNote);
        ticket.setNotes(notes);

        // Assert
        assertEquals(1, ticket.getNotes().size());
    }

    @Test
    public void shouldSetTimeTracks() throws Exception {
        // Arrange
        ArrayList<TicketTimeTrack> ticketTimeTracks = new ArrayList<>();

        // Act
        ticketTimeTracks.add(new TicketTimeTrack());
        ticket.setTimeTracks(ticketTimeTracks);

        // Assert
        assertEquals(1, ticket.getTimeTracks().size());
    }

    @Test
    public void shouldSetPosts() throws Exception {
        // Arrange
        ArrayList<TicketPost> posts = new ArrayList<>();

        // Act
        ticket.setPosts(posts);
        ticket.addPost(new TicketPost());

        // Assert
        collector.checkThat( ticket.getPosts().size(), equalTo(1));
        collector.checkThat(ticket.getFirstPost().toString(), equalTo(TEST_TICKET_POST_OBJECT));
    }

    @Test
    public void shouldSetAttachments() throws Exception {
        // Arrange
        ArrayList<TicketAttachment> attachments = new ArrayList<>();

        // Act
        attachments.add(new TicketAttachment());
        ticket.setAttachments(attachments);

        // Assert
        assertEquals(1, ticket.getAttachments().size());
    }

    @Test
    public void shouldSetStatistics() {
        // Act
        Ticket.setStatistics(new RawArrayElement());

        // Assert
        assertEquals(TEST_TICKET_STATISTICS, Ticket.getStatistics().toString());
    }

    @Test
    public void shouldGetAll() throws Exception {
        // Arrange
        Department department = new Department();

        // Act
        mockStatic(KEntity.class);
        expect(KEntity.getId(anyObject())).andReturn(1);
        expect(KEntity.getAll(anyString(), anyObject(ArrayList.class))).andReturn(new RawArrayElement());
        replay(KEntity.class);
        department.setId(1);

        // Assert
        assertEquals(TEST_TICKET_STATISTICS, ticket.getAll(department).toString());
    }



    @Test
    public void shouldSetDefaults() {
        // Act
        Ticket.setDefaults(1, 2, 3);

        // Assert
        collector.checkThat(Ticket.getDefaultStatusId(), equalTo(1));
        collector.checkThat(Ticket.getDefaultPriorityId(), equalTo(2));
        collector.checkThat(Ticket.getDefaultTypeId(), equalTo(3));
    }

    @Test(expected = NullPointerException.class)
    public void shouldCheckPopulate() throws Exception {
        // Arrange
        RawArrayElement rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<>();

        // Act
        components.add(new RawArrayElement(TEST_ID));
        components.add(new RawArrayElement(TEST_TYPE));
        components.add(new RawArrayElement(TEST_FLAG_TYPE));
        components.add(new RawArrayElement(TEST_DISPLAY_ID));
        components.add(new RawArrayElement(TEST_DEPARTMENT_ID));
        components.add(new RawArrayElement(TEST_STATUS_IS));
        components.add(new RawArrayElement(TEST_PRIORITY_ID));
        components.add(new RawArrayElement(TEST_TYPE_ID));
        components.add(new RawArrayElement(TEST_USER_ID));
        components.add(new RawArrayElement(TEST_USER_ORGANIZATION));
        components.add(new RawArrayElement(TEST_USER_ORGANIZATION_ID));
        components.add(new RawArrayElement(TEST_OWNER_STAFF_ID));
        components.add(new RawArrayElement(TEST_OWNER_STAFF_NAME));
        components.add(new RawArrayElement(TEST_FULL_NAME));
        components.add(new RawArrayElement(TEST_EMAIL));
        components.add(new RawArrayElement(TEST_LAST_REPLIER));
        components.add(new RawArrayElement(TEST_SUBJECT_NAME));
        components.add(new RawArrayElement(TEST_CREATION_TIME));
        components.add(new RawArrayElement(TEST_LAST_ACTIVITY));
        components.add(new RawArrayElement(TEST_LAST_STAFF_REPLY));
        components.add(new RawArrayElement(TEST_LAST_USER_REPLY));
        components.add(new RawArrayElement(TEST_SLA_PLAN_ID));
        components.add(new RawArrayElement(TEST__NEXT_REPLY_DUE));
        components.add(new RawArrayElement(TEST_RESOLUTION_DUE));
        components.add(new RawArrayElement(TEST_REPLIES));
        components.add(new RawArrayElement(TEST_IP_ADDRESS));
        components.add(new RawArrayElement(TEST_CREATOR));
        components.add(new RawArrayElement(TEST_CREATION_MODE));
        components.add(new RawArrayElement(TEST_CREATION_TYPE));
        components.add(new RawArrayElement(TEST_IS_ESCALATED));
        components.add(new RawArrayElement(TEST_ESCALATION_RULE_ID));
        components.add(new RawArrayElement(TEST_TEMPLATE_GROUP_ID));
        components.add(new RawArrayElement(TEST_TEMPLATE_GROUP_NAME));
        components.add(new RawArrayElement(TEST_TAGS));
        components.add(new RawArrayElement(TEST_WATCHER));
        components.add(new RawArrayElement(TEST_WORKFLOW));
        components.add(new RawArrayElement(TEST_NOTE));
        components.add(new RawArrayElement(TEST_POSTS));

        rawArrayElement.setComponents(components);
        rawArrayElement.setAttribute(TEST_FLAG_TYPE, "1");

        // Assert
        assertEquals("", ticket.populate(rawArrayElement).toString());
    }

    @Test
    public void shouldBuildHashMap() {
        // Arrange
        ticket = new Ticket();

        // Act
        ticket.setSubject(TEST_SUBJECT_NAME);
        ticket.setFullName(TEST_FULL_NAME);
        ticket.setEmail(TEST_EMAIL);
        ticket.setDepartmentId(1);
        ticket.setTicketStatusId(1);
        ticket.setTicketPriorityId(1);
        ticket.setTicketTypeId(1);
        ticket.setOwnerStaffId(1);
        ticket.setCreator(TicketCreatorEnum.STAFF);
        ticket.setContents(TEST_CONTENT_NAME);
        ticket.setCreationType(CreationTypeEnum.PHONE);
        ticket.setIgnoreAutoResponder(true);

        // Assert
        collector.checkThat(ticket.buildHashMap(false).size(), equalTo(9));
        collector.checkThat(ticket.buildHashMap(true).size(), equalTo(12));
    }

    @Test
    public void shouldCreateTicketPostWithStaff() {
        // Act
        TicketPost ticketPost = ticket.createTicketPost(new Staff(), TEST_CONTENT_NAME);

        // Assert
        assertEquals(0, ticketPost.getCreator());
    }

    @Test
    public void shouldCreateTicketPostWithUser() {
        // Act
        TicketPost ticketPost = ticket.createTicketPost(new User(), TEST_CONTENT_NAME);

        // Assert
        assertEquals(0, ticketPost.getCreator());
    }

    @Test
    public void shouldCreateTicketNote() throws Exception {
        // Act
        TicketNote ticketNote = ticket.createTicketNote(new Staff(), TEST_CONTENT_NAME);

        // Assert
        assertNull(ticketNote.getCreatorStaff());
    }

    @Test
    public void shouldCreateTicketTimeTrack() throws Exception {
        // Act
        TicketTimeTrack ticketTimeTrack = ticket.createTicketTimeTrack(TEST_CONTENT_NAME, new Staff(), "10:00", "18000" );

        // Assert
        assertEquals(TEST_CONTENT_NAME, ticketTimeTrack.getContents());
    }

}
