package lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ###############################################
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 * ###############################################
 */
public class Ticket extends KEntityCustom {

    static final int FLAG_NONE = 0;
    static final int FLAG_PURPLE = 1;
    static final int FLAG_ORANGE = 2;
    static final int FLAG_GREEN = 3;
    static final int FLAG_YELLOW = 4;
    static final int FLAG_RED = 5;
    static final int FLAG_BLUE = 6;
    static final int CREATOR_AUTO = 0;
    static final int CREATOR_STAFF = 1;
    static final int CREATOR_USER = 2;
    static final int CREATOR_CLIENT = 2;
    static final int CREATION_MODE_SUPPORTCENTER = 1;
    static final int CREATION_MODE_STAFFCP = 2;
    static final int CREATION_MODE_EMAIL = 3;
    static final int CREATION_MODE_API = 4;
    static final int CREATION_MODE_SITEBADGE = 5;
    static final int CREATION_TYPE_DEFAULT = 1;
    static final int CREATION_TYPE_PHONE = 2;
    /**
     * Flag for searching using query - search the Ticket ID & Mask ID :  SEARCH_TICKET_ID
     */
    public static final String SEARCH_TICKET_ID = "ticketid";
    /**
     * Flag for searching using query - search the Ticket Post Contents.
     */
    public static final String SEARCH_CONTENTS = "contents";
    /**
     * Flag for searching using query - search the Full Name & Email.
     */
    public static final String SEARCH_AUTHOR = "author";
    /**
     * Flag for searching using query - search the Email Address (Ticket & Posts).
     */
    public static final String SEARCH_EMAIL = "email";
    /**
     * Flag for searching using query - search the Email Address (only Tickets).
     */
    public static final String SEARCH_CREATOR_EMAIL = "creatoremail";
    /**
     * Flag for searching using query - search the Full Name.
     */
    public static final String SEARCH_FULL_NAME = "fullname";
    /**
     * Flag for searching using query - search the Ticket Notes.
     */
    public static final String SEARCH_NOTES = "notes";
    /**
     * Flag for searching using query - search the User Group.
     */
    public static final String SEARCH_USER_GROUP = "usergroup";
    /**
     * Flag for searching using query - search the User Organization.
     */
    public static final String SEARCH_USER_ORGANIZATION = "userorganization";
    /**
     * Flag for searching using query - search the User (Full Name, Email).
     */
    public static final String SEARCH_USER = "user";
    /**
     * Flag for searching using query - search the Ticket Tags.
     */
    public static final String SEARCH_TAGS = "tags";
    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/Ticket";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "ticket";
    //Custom Field Croup Class = TicketCustomFieldGroup is used extensively
    static String customFieldGroupController = TicketCustomFieldGroup.getController();
    /**
     * The Object id field.
     */
    static protected String objectIdField = "ticketid";
    /**
     * Default status identifier for new tickets.
     *
     * @see Ticket::setDefaults
     */
    static private int defaultStatusId;
    /**
     * Default priority identifier for new tickets.
     *
     * @see Ticket::setDefaults
     */
    static private int defaultPriorityId;
    /**
     * Default type identifier for new tickets.
     *
     * @see Ticket::setDefaults
     */
    static private int defaultTypeId;
    /**
     * Default status identifier for new tickets.
     *
     * @see Ticket::setDefaults
     */
    static private Boolean autoCreateUser = true;
    /**
     * Ticket identifier.
     *
     * @apiField
     */
    protected int id;
    /**
     * Ticket flag type.
     *
     * @apiField  * @see Ticket::FLAG static final Stringants.
     */
    protected int flagType;
    /**
     * Ticket display identifier.
     *
     * @apiField
     */
    protected String displayId;
    /**
     * Ticket department identifier.
     *
     * @apiField required_create =true
     */
    protected int departmentId;
    /**
     * Ticket status identifier.
     *
     * @apiField required_create =true
     */
    protected int statusId;
    /**
     * Ticket priority identifier.
     *
     * @apiField required_create =true
     */
    protected int priorityId;
    /**
     * Ticket type identifier.
     *
     * @apiField required_create =true
     */
    protected int typeId;
    /**
     * Identifier of the user ticket was created by.
     *
     * @apiField
     */
    protected int userId;
    /**
     * Name of the organization of the user ticket was created by.
     *
     * @apiField
     */
    protected String userOrganizationName;
    /**
     * Identifier of the organization of the user ticket was created by.
     *
     * @apiField
     */
    protected int userOrganizationId;
    /**
     * Identifier of staff user who owns the ticket.
     *
     * @apiField
     */
    protected int ownerStaffId;
    /**
     * Full name of staff user who owns the ticket.
     *
     * @apiField
     */
    protected String ownerStaffName;
    /**
     * Full name of creator of the ticket.
     *
     * @apiField required_create =true
     */
    protected String fullName;
    /**
     * E-mail of creator of the ticket.
     *
     * @apiField required_create =true
     */
    protected String email;
    /**
     * Full name of the last replier to this ticket.
     *
     * @apiField
     */
    protected String lastReplier;
    /**
     * Ticket subject.
     *
     * @apiField required_create =true
     */
    protected String subject;
    /**
     * Timestamp of when this ticket was created.
     *
     * @apiField
     */
    protected int creationTime;
    /**
     * Timestamp of last activity in this ticket.
     *
     * @apiField
     */
    protected int lastActivity;
    /**
     * Timestamp of last staff user reply.
     *
     * @apiField
     */
    protected int lastStaffReply;
    /**
     * Timestamp of last user reply.
     *
     * @apiField
     */
    protected int lastUserReply;
    /**
     * Service Level Agreement plan identifier.
     *
     * @apiField
     */
    protected int SLAPlanId;
    /**
     * Timestamp of when the next replay is due.
     *
     * @apiField
     */
    protected int nextReplyDue;
    /**
     * Timestamp of when resolution of the ticket is due.
     *
     * @apiField
     */
    protected int resolutionDue;
    /**
     * Reply count.
     *
     * @apiField
     */
    protected int replies;
    /**
     * IP address from which the ticket was created.
     *
     * @apiField
     */
    protected String ipAddress;
    /**
     * Type of the ticket creator.
     *
     * @apiField  * @see Ticket::CREATOR static final Stringants.
     */
    protected int creator;
    /**
     * Ticket creation mode.
     *
     * @apiField  * @see Ticket::CREATION_MODE static final Stringants.
     */
    protected int creationMode;
    /**
     * Ticket creation type.
     *
     * @apiField alias =type     * @see Ticket::CREATION_TYPE static final Stringants.
     */
    protected int creationType;
    /**
     * Is this ticket escalated.
     *
     * @apiField
     */
    protected Boolean escalated;
    /**
     * Escalation rule identifier.
     *
     * @apiField
     */
    protected int escalationRuleId;
    /**
     * Template group identifier.
     *
     * @apiField getter =getTemplateGroupId setter=setTemplateGroup alias=templategroup
     */
    protected int templateGroupId;
    /**
     * Template group name.
     *
     * @apiField getter =getTemplateGroupName setter=setTemplateGroup
     */
    protected String templateGroupName;
    /**
     * Ticket tags.
     *
     * @apiField
     */
    protected String tags;
    /**
     * Ticket watchers.
     *
     * @apiField
     */
    protected HashMap<Integer, String> watchers = new HashMap<Integer, String>();
    /**
     * Ticket workflows.
     *
     * @apiField
     */
    protected HashMap<Integer, String> workflows = new HashMap<Integer, String>();
    /**
     * Identifier os staff user who will create this ticket.
     *
     * @apiField
     */
    protected int staffId;
    /**
     * Ticket contents.
     *
     * @apiField required_create =true
     */
    protected String contents = null;
    /**
     * Option to disable autoresponder e-mail.
     *
     * @apiField
     */
    protected Boolean ignoreAutoResponder = false;
    /**
     * Ticket status.
     */
    private TicketStatus status;
    /**
     * Ticket priority.
     */
    private TicketPriority priority;
    /**
     * Ticket type.
     */
    private TicketType type;
    /**
     * User, the creator of this ticket.
     */
    private User user;
    /**
     * Organization of user who created this ticket.
     */
    private UserOrganization userOrganization;
    /**
     * Staff user, the creator of this ticket.
     */
    private Staff staff;
    /**
     * Staff user who is the owner of this ticket.
     */
    private Staff ownerStaff;
    /**
     * Department of this ticket.
     */
    private Department department = null;
    /**
     * List of ticket notes.
     */
    private ArrayList<TicketNote> notes = new ArrayList<TicketNote>();
    /**
     * List of ticket time tracks.
     */
    private ArrayList<TicketTimeTrack> timeTracks = new ArrayList<TicketTimeTrack>();
    /**
     * List of ticket posts.
     */
    private ArrayList<TicketPost> posts = new ArrayList<TicketPost>();
    /**
     * List of ticket attachments.
     */
    private ArrayList<TicketAttachment> attachments = new ArrayList<TicketAttachment>();
    /**
     * Tickets statistic.
     */
    static private RawArrayElement statistics = null;

    /**
     * Instantiates a new Ticket.
     */
    public Ticket() {
    }

    private Ticket(Department department, String contents, String subject) {
        this.setDepartment(department);
        this.setContents(contents).setSubject(subject);
        this.setTypeId(getDefaultTypeId()).setPriorityId(getDefaultPriorityId()).setStatusId(getDefaultStatusId());

    }

    /**
     * Instantiates a new Ticket.
     *
     * @param department the department
     * @param creator the creator
     * @param contents the contents
     * @param subject the subject
     */
    public Ticket(Department department, int creator, String contents, String subject) {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with implicit staff as creator.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     * @param department the department
     * @param creator the creator
     * @param contents the contents
     * @param subject the subject
     */
    public Ticket(Department department, Staff creator, String contents, String subject) {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with implicit user as creator.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     * @param department the department
     * @param creator the creator
     * @param contents the contents
     * @param subject the subject
     * @throws KayakoException the kayako exception
     */
    public Ticket(Department department, User creator, String contents, String subject) throws KayakoException {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with creator user automatically created by server using provided name and e-mail.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     * @param department the department
     * @param creatorFullName the creator full name
     * @param creatorEmail the creator email
     * @param contents the contents
     * @param subject the subject
     * @throws KayakoException the kayako exception
     */
    public Ticket(Department department, String creatorFullName, String creatorEmail, String contents, String subject) throws KayakoException {
        this(department, contents, subject);
        this.setCreatorAuto(creatorFullName, creatorEmail);
    }

    /**
     * Sets creator id.
     *
     * @param creatorId the creator id
     * @param creatorType the creator type
     * @return the creator id
     * @throws KayakoException the kayako exception
     */
    public Ticket setCreatorId(int creatorId, int creatorType) throws KayakoException {
        switch (creatorType) {
            case CREATOR_STAFF:
                this.setStaffId(creatorId);
                break;
            case CREATOR_USER:
                this.setUserId(creatorId);
        }
        return this;
    }

    /**
     * Sets creator.
     *
     * @param user the user
     * @return the creator
     * @throws KayakoException the kayako exception
     */
    public Ticket setCreator(User user) throws KayakoException {
        this.setUser(user);
        return this;
    }

    /**
     * Sets creator.
     *
     * @param staff the staff
     * @return the creator
     */
    public Ticket setCreator(Staff staff) {
        this.setStaff(staff);
        return this;
    }

    /**
     * Sets creator auto.
     *
     * @param fullName the full name
     * @param email the email
     * @return the creator auto
     * @throws KayakoException the kayako exception
     */
    public Ticket setCreatorAuto(String fullName, String email) throws KayakoException {
        this.setFullName(fullName).setEmail(email).setCreator(CREATOR_AUTO);
        this.setUser(null).setUserId(0).setStaff(null).setStaffId(0);
        return this;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {
        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        Ticket.controller = controller;
    }

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {
        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        Ticket.objectXmlName = objectXmlName;
    }

    /**
     * Gets custom field group controller.
     *
     * @return the custom field group controller
     */
    public static String getCustomFieldGroupController() {
        return customFieldGroupController;
    }

    /**
     * Sets custom field group controller.
     *
     * @param customFieldGroupController the custom field group controller
     */
    public static void setCustomFieldGroupController(String customFieldGroupController) {
        Ticket.customFieldGroupController = customFieldGroupController;
    }

    /**
     * Gets object id field.
     *
     * @return the object id field
     */
    public static String getObjectIdField() {
        return objectIdField;
    }

    /**
     * Sets object id field.
     *
     * @param objectIdField the object id field
     */
    public static void setObjectIdField(String objectIdField) {
        Ticket.objectIdField = objectIdField;
    }

    @Override
    protected ArrayList<CustomFieldGroup> loadCustomFieldGroups(Boolean refresh) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("Custom fields are not available for new objects. Save the object before accessing its custom fields.");
        }
        ArrayList<CustomFieldGroup> customFieldGroups = this.getCustomFieldGroups();
        if (customFieldGroups.size() == 0 && !refresh) {
            return customFieldGroups;
        }
        RawArrayElement rawArrayElement = TicketCustomFieldGroup.getAll(Ticket.getCustomFieldGroupController());
        for (RawArrayElement component : rawArrayElement.getComponents()) {
            customFieldGroups.add(new TicketCustomFieldGroup(this.getId(), component));
        }
        this.setCustomFieldGroups(customFieldGroups);
        this.cacheFields();
        return this.getCustomFieldGroups();
    }

    /**
     * Prepares local array for custom field fast lookup based on its name.
     * this function should populate this.customFields
     */
    @Override
    protected ArrayList<CustomField> loadCustomField(Boolean refresh) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets default status id.
     *
     * @return the default status id
     */
    public static int getDefaultStatusId() {
        return defaultStatusId;
    }

    /**
     * Sets default status id.
     *
     * @param defaultStatusId the default status id
     */
    public static void setDefaultStatusId(int defaultStatusId) {
        Ticket.defaultStatusId = defaultStatusId;
    }

    /**
     * Gets default priority id.
     *
     * @return the default priority id
     */
    public static int getDefaultPriorityId() {
        return defaultPriorityId;
    }

    /**
     * Sets default priority id.
     *
     * @param defaultPriorityId the default priority id
     */
    public static void setDefaultPriorityId(int defaultPriorityId) {
        Ticket.defaultPriorityId = defaultPriorityId;
    }

    /**
     * Gets default type id.
     *
     * @return the default type id
     */
    public static int getDefaultTypeId() {
        return defaultTypeId;
    }

    /**
     * Sets default type id.
     *
     * @param defaultTypeId the default type id
     */
    public static void setDefaultTypeId(int defaultTypeId) {
        Ticket.defaultTypeId = defaultTypeId;
    }

    /**
     * Gets auto create user.
     *
     * @return the auto create user
     */
    public static Boolean getAutoCreateUser() {
        return autoCreateUser;
    }

    /**
     * Sets auto create user.
     *
     * @param autoCreateUser the auto create user
     */
    public static void setAutoCreateUser(Boolean autoCreateUser) {
        Ticket.autoCreateUser = autoCreateUser;
    }

    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Ticket setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets flag type.
     *
     * @return the flag type
     */
    public int getFlagType() {
        return flagType;
    }

    /**
     * Sets flag type.
     *
     * @param flagType the flag type
     * @return the flag type
     */
    public Ticket setFlagType(int flagType) {
        this.flagType = flagType;
        return this;
    }

    /**
     * Gets display id.
     *
     * @return the display id
     */
    public String getDisplayId() {
        return displayId;
    }

    /**
     * Sets display id.
     *
     * @param displayId the display id
     * @return the display id
     */
    public Ticket setDisplayId(String displayId) {
        this.displayId = displayId;
        return this;
    }

    /**
     * Gets department id.
     *
     * @return the department id
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets department id.
     *
     * @param departmentId the department id
     * @return the department id
     */
    public Ticket setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
        this.department = null;
        return this;
    }

    /**
     * Gets status id.
     *
     * @return the status id
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets status id.
     *
     * @param statusId the status id
     * @return the status id
     */
    public Ticket setStatusId(int statusId) {
        this.statusId = statusId;
        this.status = null;
        return this;
    }

    /**
     * Gets priority id.
     *
     * @return the priority id
     */
    public int getPriorityId() {
        return priorityId;
    }

    /**
     * Sets priority id.
     *
     * @param priorityId the priority id
     * @return the priority id
     */
    public Ticket setPriorityId(int priorityId) {
        this.priorityId = priorityId;
        this.priority = null;
        return this;
    }

    /**
     * Gets type id.
     *
     * @return the type id
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets type id.
     *
     * @param typeId the type id
     * @return the type id
     */
    public Ticket setTypeId(int typeId) {
        this.typeId = typeId;
        this.type = null;
        return this;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     * @return the user id
     * @throws KayakoException the kayako exception
     */
    public Ticket setUserId(int userId) throws KayakoException {
        if (userId > 0) {
            this.creator = CREATOR_USER;
            this.staff = null;
            this.staffId = 0;
            this.user = User.get(userId);
            this.fullName = user.getFullName();
            this.email = user.getEmail();
        }
        this.userId = userId;
        return this;
    }

    /**
     * Returns name of organization the user who created the ticket belongs to.
     *
     * @return userOrganizationName user organization name
     */
    public String getUserOrganizationName() {
        return userOrganizationName;
    }

    /**
     * Sets user organization name.
     *
     * @param userOrganizationName the user organization name
     * @return the user organization name
     */
    public Ticket setUserOrganizationName(String userOrganizationName) {
        this.userOrganizationName = userOrganizationName;
        return this;
    }

    /**
     * Returns identifier of organization the user who created the ticket belongs to.
     *
     * @return userOrganizationId user organization id
     */

    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    /**
     * Sets user organization id.
     *
     * @param userOrganizationId the user organization id
     * @return the user organization id
     */
    public Ticket setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        return this;
    }

    /**
     * Returns identifier of the staff user, owner of this ticket.
     * @return the owner staff id
     */
    public int getOwnerStaffId() {
        return ownerStaffId;
    }

    /**
     * Sets identifier of the staff user, owner of this ticket.
     * @param ownerStaffId the owner staff id
     * @return the owner staff id
     */
    public Ticket setOwnerStaffId(int ownerStaffId) {
        this.ownerStaffId = ownerStaffId;
        this.ownerStaff = null;
        return this;
    }

    /**
     * Returns full name of the staff user, owner of this ticket.
     * @return the owner staff name
     */
    public String getOwnerStaffName() {
        return ownerStaffName;
    }

    /**
     * Sets owner staff name.
     *
     * @param ownerStaffName the owner staff name
     * @return the owner staff name
     */
    public Ticket setOwnerStaffName(String ownerStaffName) {
        this.ownerStaffName = ownerStaffName;
        return this;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     * @return the full name
     */
    public Ticket setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     * @return the email
     */
    public Ticket setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Gets last replier.
     *
     * @return the last replier
     */
    public String getLastReplier() {
        return lastReplier;
    }

    /**
     * Sets last replier.
     *
     * @param lastReplier the last replier
     * @return the last replier
     */
    public Ticket setLastReplier(String lastReplier) {
        this.lastReplier = lastReplier;
        return this;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     * @return the subject
     */
    public Ticket setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Gets creation time.
     *
     * @return the creation time
     */
    public int getCreationTime() {
        return creationTime;
    }

    /**
     * Sets creation time.
     *
     * @param creationTime the creation time
     * @return the creation time
     */
    public Ticket setCreationTime(int creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    /**
     * Gets last activity.
     *
     * @return the last activity
     */
    public int getLastActivity() {
        return lastActivity;
    }

    /**
     * Sets last activity.
     *
     * @param lastActivity the last activity
     * @return the last activity
     */
    public Ticket setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
        return this;
    }

    /**
     * Gets last staff reply.
     *
     * @return the last staff reply
     */
    public int getLastStaffReply() {
        return lastStaffReply;
    }

    /**
     * Sets last staff reply.
     *
     * @param lastStaffReply the last staff reply
     * @return the last staff reply
     */
    public Ticket setLastStaffReply(int lastStaffReply) {
        this.lastStaffReply = lastStaffReply;
        return this;
    }

    /**
     * Gets last user reply.
     *
     * @return the last user reply
     */
    public int getLastUserReply() {
        return lastUserReply;
    }

    /**
     * Sets last user reply.
     *
     * @param lastUserReply the last user reply
     * @return the last user reply
     */
    public Ticket setLastUserReply(int lastUserReply) {
        this.lastUserReply = lastUserReply;
        return this;
    }

    /**
     * Gets sLA plan id.
     *
     * @return the sLA plan id
     */
    public int getSLAPlanId() {
        return SLAPlanId;
    }

    /**
     * Sets sLA plan id.
     *
     * @param SLAPlanId the sLA plan id
     * @return the sLA plan id
     */
    public Ticket setSLAPlanId(int SLAPlanId) {
        this.SLAPlanId = SLAPlanId;
        return this;
    }

    /**
     * Gets next reply due.
     *
     * @return the next reply due
     */
    public int getNextReplyDue() {
        return nextReplyDue;
    }

    /**
     * Sets next reply due.
     *
     * @param nextReplyDue the next reply due
     * @return the next reply due
     */
    public Ticket setNextReplyDue(int nextReplyDue) {
        this.nextReplyDue = nextReplyDue;
        return this;
    }

    /**
     * Gets resolution due.
     *
     * @return the resolution due
     */
    public int getResolutionDue() {
        return resolutionDue;
    }

    /**
     * Sets resolution due.
     *
     * @param resolutionDue the resolution due
     * @return the resolution due
     */
    public Ticket setResolutionDue(int resolutionDue) {
        this.resolutionDue = resolutionDue;
        return this;
    }

    /**
     * Gets replies.
     *
     * @return the replies
     */
    public int getReplies() {
        return replies;
    }

    /**
     * Sets replies.
     *
     * @param replies the replies
     * @return the replies
     */
    public Ticket setReplies(int replies) {
        this.replies = replies;
        return this;
    }

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets ip address.
     *
     * @param ipAddress the ip address
     * @return the ip address
     */
    public Ticket setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public int getCreator() {
        switch (this.creator) {
            case CREATOR_USER:
                return this.userId;
            case CREATOR_STAFF:
                return this.staffId;
        }
        return creator;
    }

    /**
     * Sets creator.
     *
     * @param creatorId the creator id
     * @param type the type
     * @return the creator
     * @throws KayakoException the kayako exception
     */
    public Ticket setCreator(int creatorId, int type) throws KayakoException {
        switch (type) {
            case CREATOR_USER:
                this.setUserId(creatorId);
                break;
            case CREATOR_STAFF:
                this.setStaffId(creatorId);
                break;
        }
        return this;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     * @return the creator
     */
    public Ticket setCreator(int creator) {
        this.creator = creator;
        return this;
    }

    /**
     * Gets creation mode.
     *
     * @return the creation mode
     */
    public int getCreationMode() {
        return creationMode;
    }

    /**
     * Sets creation mode.
     *
     * @param creationMode the creation mode
     * @return the creation mode
     */
    public Ticket setCreationMode(int creationMode) {
        this.creationMode = creationMode;
        return this;
    }

    /**
     * Gets creation type.
     *
     * @return the creation type
     */
    public int getCreationType() {
        return creationType;
    }

    /**
     * Sets creation type.
     *
     * @param creationType the creation type
     * @return the creation type
     */
    public Ticket setCreationType(int creationType) {
        this.creationType = creationType;
        return this;
    }

    /**
     * Gets escalated.
     *
     * @return the escalated
     */
    public Boolean getEscalated() {
        return escalated;
    }

    /**
     * Sets escalated.
     *
     * @param escalated the escalated
     * @return the escalated
     */
    public Ticket setEscalated(Boolean escalated) {
        this.escalated = escalated;
        return this;
    }

    /**
     * Gets escalation rule id.
     *
     * @return the escalation rule id
     */
    public int getEscalationRuleId() {
        return escalationRuleId;
    }

    /**
     * Sets escalation rule id.
     *
     * @param escalationRuleId the escalation rule id
     * @return the escalation rule id
     */
    public Ticket setEscalationRuleId(int escalationRuleId) {
        this.escalationRuleId = escalationRuleId;
        return this;
    }

    /**
     * Gets template group id.
     *
     * @return the template group id
     */
    public int getTemplateGroupId() {
        return templateGroupId;
    }

    /**
     * Sets template group id.
     *
     * @param templateGroupId the template group id
     * @return the template group id
     */
    public Ticket setTemplateGroupId(int templateGroupId) {
        this.templateGroupId = templateGroupId;
        this.templateGroupName = null;
        return this;
    }

    /**
     * Gets template group name.
     *
     * @return the template group name
     */
    public String getTemplateGroupName() {
        return templateGroupName;
    }

    /**
     * Sets template group name.
     *
     * @param templateGroupName the template group name
     * @return the template group name
     */
    public Ticket setTemplateGroupName(String templateGroupName) {
        this.templateGroupName = templateGroupName;
        this.templateGroupId = 0;
        return this;
    }

    /**
     * Sets template group.
     *
     * @param id the id
     * @return the template group
     */
    public Ticket setTemplateGroup(int id) {
        return this.setTemplateGroupId(id);
    }

    /**
     * Sets template group.
     *
     * @param name the name
     * @return the template group
     */
    public Ticket setTemplateGroup(String name) {
        return this.setTemplateGroupName(name);
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     * @return the tags
     */
    public Ticket setTags(String tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Gets watchers.
     *
     * @return the watchers
     *///HashMap staffId => Full Name
    public HashMap<Integer, String> getWatchers() {
        return watchers;
    }

    /**
     * Sets watchers.
     *
     * @param watchers the watchers
     * @return the watchers
     */
    public Ticket setWatchers(HashMap<Integer, String> watchers) {
        this.watchers = watchers;
        return this;
    }

    /**
     * Add watcher.
     *
     * @param id the id
     * @param name the name
     * @return the ticket
     */
    public Ticket addWatcher(int id, String name) {
        this.watchers.put(id, name);
        return this;
    }

    /**
     * Gets workflows.
     *
     * @return the workflows
     */
    public HashMap<Integer, String> getWorkflows() {
        return workflows;
    }

    /**
     * Sets workflows.
     *
     * @param workflows the workflows
     * @return the workflows
     */
    public Ticket setWorkflows(HashMap<Integer, String> workflows) {
        this.workflows = workflows;
        return this;
    }

    /**
     * Add workflow.
     *
     * @param id the id
     * @param title the title
     * @return the ticket
     */
    public Ticket addWorkflow(int id, String title) {
        this.workflows.put(id, title);
        return this;
    }

    /**
     * Gets staff id.
     *
     * @return the staff id
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Sets identifier of staff user, the creator of this ticket.
     * @param staffId the staff id
     * @return the staff id
     * @throws KayakoException the kayako exception
     */
    public Ticket setStaffId(int staffId) throws KayakoException {
        if (staffId > 0) {
            this.staffId = staffId;
            this.creator = CREATOR_STAFF;
            this.user = null;
            this.staff = Staff.get(staffId);
            this.userId = 0;
            this.fullName = staff.getFullName();
            this.email = staff.getEmail();
        }
        return this;
    }

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     * @return the contents
     */
    public Ticket setContents(String contents) {
        this.contents = contents;
        return this;
    }

    /**
     * Gets ignore auto responder.
     *
     * @return the ignore auto responder
     */
    public Boolean getIgnoreAutoResponder() {
        return ignoreAutoResponder;
    }

    /**
     * Sets ignore auto responder.
     *
     * @param ignoreAutoResponder the ignore auto responder
     * @return the ignore auto responder
     */
    public Ticket setIgnoreAutoResponder(Boolean ignoreAutoResponder) {
        this.ignoreAutoResponder = ignoreAutoResponder;
        return this;
    }

    /**
     * Gets status.
     *
     * @return the status
     * @throws KayakoException the kayako exception
     */
    public TicketStatus getStatus() throws KayakoException {
        return this.getStatus(false);
    }

    /**
     * Gets status.
     *
     * @param refresh the refresh
     * @return the status
     * @throws KayakoException the kayako exception
     */
    public TicketStatus getStatus(Boolean refresh) throws KayakoException {
        if ((this.status == null || refresh) && this.statusId > 0) {
            status = TicketStatus.get(this.statusId);
        }
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     * @return the status
     */
    public Ticket setStatus(TicketStatus status) {
        if (status != null) {
            this.statusId = status.getId();
            this.status = status;
        }
        return this;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     * @throws KayakoException the kayako exception
     */
    public TicketPriority getPriority() throws KayakoException {
        return this.getPriority(false);
    }

    /**
     * Gets priority.
     *
     * @param refresh the refresh
     * @return the priority
     * @throws KayakoException the kayako exception
     */
    public TicketPriority getPriority(Boolean refresh) throws KayakoException {
        if ((this.priority == null || refresh) && this.priorityId > 0) {
            priority = TicketPriority.get(this.priorityId);
        }
        return priority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     * @return the priority
     */
    public Ticket setPriority(TicketPriority priority) {
        if (priority != null) {
            this.priorityId = priority.getId();
            this.priority = priority;
        }
        return this;
    }

    /**
     * Gets type.
     *
     * @return the type
     * @throws KayakoException the kayako exception
     */
    public TicketType getType() throws KayakoException {
        return this.getType(false);
    }

    /**
     * Gets type.
     *
     * @param refresh the refresh
     * @return the type
     * @throws KayakoException the kayako exception
     */
    public TicketType getType(Boolean refresh) throws KayakoException {
        if ((this.type == null || refresh) && this.typeId > 0) {
            type = TicketType.get(this.typeId);
        }
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public Ticket setType(TicketType type) {
        if (type != null) {
            this.typeId = type.getId();
            this.type = type;
        }
        return this;
    }

    /**
     * Returns user, the creator of this ticket.
     * <p/>
     * Result is cached until the end of script.
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    /**
     * Gets user.
     *
     * @param refresh the refresh
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User getUser(Boolean refresh) throws KayakoException {
        if ((refresh || this.user == null) && this.getUserId() > 0) {
            this.user = User.get(this.getUserId());
        }
        return user;
    }

    /**
     * Sets user, the creator of this post.
     * @param user the user
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public Ticket setUser(User user) throws KayakoException {
        if (user != null) {
            this.setUserId(user.getId());
            this.user = user;
            this.userId = user.getId();
            this.fullName = user.getFullName();
            this.email = user.getEmail();
            this.creator = CREATOR_USER;
            this.staffId = 0;
            this.staff = null;

        }
        return this;
    }

    /**
     * Return organization the user who created the ticket belongs to.
     * <p/>
     * Result is cached until the end of script.
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization() throws KayakoException {
        return this.getUserOrganization(false);
    }

    /**
     * Gets user organization.
     *
     * @param refresh the refresh
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization(Boolean refresh) throws KayakoException {
        if ((userOrganization == null || refresh) && userOrganizationId > 0) {
            userOrganization = UserOrganization.get(this.userOrganizationId);
        }
        return userOrganization;
    }

    /**
     * Sets user organization.
     *
     * @param userOrganization the user organization
     * @return the user organization
     */
    public Ticket setUserOrganization(UserOrganization userOrganization) {
        if (userOrganization != null) {
            this.userOrganizationId = userOrganization.getId();
            this.userOrganization = userOrganization;
        }
        return this;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff getStaff() throws KayakoException {
        return this.getStaff(false);
    }

    /**
     * Gets staff.
     *
     * @param refresh the refresh
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff getStaff(Boolean refresh) throws KayakoException {
        if ((this.staff == null || refresh) && this.staffId > 0) {
            staff = Staff.get(this.staffId);
        }
        return staff;
    }

    /**
     * Sets staff user, the creator of this ticket.
     * @param staff the staff
     * @return the staff
     */
    public Ticket setStaff(Staff staff) {
        if (staff != null) {
            this.staffId = staff.getId();
            this.staff = staff;
            this.creator = CREATOR_STAFF;
            this.userId = 0;
            this.user = null;
            this.fullName = staff.getFullName();
            this.email = staff.getEmail();
        }
        return this;
    }

    /**
     * Return staff user, owner of this ticket.
     * <p/>
     * Result is cached until the end of script.
     *
     * @return Staff owner staff
     * @throws KayakoException the kayako exception
     */
    public Staff getOwnerStaff() throws KayakoException {
        return this.getOwnerStaff(false);
    }

    /**
     * Gets owner staff.
     *
     * @param refresh the refresh
     * @return the owner staff
     * @throws KayakoException the kayako exception
     */
    public Staff getOwnerStaff(Boolean refresh) throws KayakoException {
        if ((this.ownerStaff == null || refresh) && this.ownerStaffId > 0) {
            ownerStaff = Staff.get(this.ownerStaffId);
        }
        return ownerStaff;
    }

    /**
     * Sets staff user, owner of this ticket.
     * @param ownerStaff the owner staff
     * @return the owner staff
     */
    public Ticket setOwnerStaff(Staff ownerStaff) {
        if (ownerStaff != null) {
            this.ownerStaffId = ownerStaff.getId();
            this.ownerStaff = ownerStaff;
        }
        return this;
    }

    /**
     * Gets department.
     *
     * @return the department
     * @throws KayakoException the kayako exception
     */
    public Department getDepartment() throws KayakoException {
        return this.getDepartment(false);
    }

    /**
     * Gets department.
     *
     * @param refresh the refresh
     * @return the department
     * @throws KayakoException the kayako exception
     */
    public Department getDepartment(Boolean refresh) throws KayakoException {
        if ((this.department == null || refresh) && this.departmentId > 0) {
            department = Department.get(this.departmentId);
        }
        return department;
    }

    /**
     * Sets department.
     *
     * @param department the department
     * @return the department
     */
    public Ticket setDepartment(Department department) {
        if (department != null) {
            this.departmentId = department.getId();
            this.department = department;
        }
        return this;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketNote> getNotes() throws KayakoException {
        return this.getNotes(false);
    }

    /**
     * Gets notes.
     *
     * @param refresh the refresh
     * @return the notes
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketNote> getNotes(Boolean refresh) throws KayakoException {
        if ((this.notes.size() == 0 || refresh)) {
            for (TicketNote note : TicketNote.getAllNotes(this.getId())) {
                notes.add(note);
            }
        }
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     * @return the notes
     */
    public Ticket setNotes(ArrayList<TicketNote> notes) {
        this.notes = notes;
        return this;
    }

    /**
     * Add note.
     *
     * @param ticketNote the ticket note
     * @return the ticket
     */
    public Ticket addNote(TicketNote ticketNote) {
        this.notes.add(ticketNote);
        return this;
    }

    /**
     * Gets time tracks.
     *
     * @return the time tracks
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketTimeTrack> getTimeTracks() throws KayakoException {
        return this.getTimeTracks(false);
    }

    /**
     * Gets time tracks.
     *
     * @param refresh the refresh
     * @return the time tracks
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketTimeTrack> getTimeTracks(Boolean refresh) throws KayakoException {
        if ((this.timeTracks.size() == 0 || refresh)) {
            for (TicketTimeTrack timeTrack : TicketTimeTrack.getAllTimeTracks(this.getId())) {
                timeTracks.add(timeTrack);
            }
        }
        return timeTracks;
    }

    /**
     * Sets time tracks.
     *
     * @param timeTracks the time tracks
     * @return the time tracks
     */
    public Ticket setTimeTracks(ArrayList<TicketTimeTrack> timeTracks) {
        this.timeTracks = timeTracks;
        return this;
    }

    /**
     * Gets posts.
     *
     * @return the posts
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketPost> getPosts() throws KayakoException {
        return this.getPosts(false);
    }

    /**
     * Gets posts.
     *
     * @param refresh the refresh
     * @return the posts
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketPost> getPosts(Boolean refresh) throws KayakoException {
        if ((this.posts.size() == 0 || refresh)) {
            for (TicketPost post : TicketPost.getAllPosts(this.getId())) {
                posts.add(post);
            }
        }
        return posts;
    }

    /**
     * Gets first post.
     *
     * @return the first post
     * @throws KayakoException the kayako exception
     */
    public TicketPost getFirstPost() throws KayakoException {
        ArrayList<TicketPost> posts = this.getPosts();
        if (posts.size() == 0) {
            return null;
        }
        return posts.get(0);
    }

    /**
     * Sets posts.
     *
     * @param posts the posts
     * @return the posts
     */
    public Ticket setPosts(ArrayList<TicketPost> posts) {
        this.posts = posts;
        return this;
    }

    /**
     * Add post.
     *
     * @param ticketPost the ticket post
     * @return the ticket
     */
    public Ticket addPost(TicketPost ticketPost) {
        this.posts.add(ticketPost);
        return this;
    }

    /**
     * Gets attachments.
     *
     * @return the attachments
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketAttachment> getAttachments() throws KayakoException {
        return this.getAttachments(false);
    }

    /**
     * Gets attachments.
     *
     * @param refresh the refresh
     * @return the attachments
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketAttachment> getAttachments(Boolean refresh) throws KayakoException {
        if ((this.attachments.size() == 0 || refresh)) {
            for (TicketAttachment attachment : TicketAttachment.getAllAttachments(this.getId())) {
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    /**
     * Sets attachments.
     *
     * @param attachments the attachments
     * @return the attachments
     */
    public Ticket setAttachments(ArrayList<TicketAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * Gets statistics.
     *
     * @return the statistics
     */
    public static RawArrayElement getStatistics() {
        return statistics;
    }

    /**
     * Sets statistics.
     *
     * @param statistics the statistics
     */
    public static void setStatistics(RawArrayElement statistics) {
        Ticket.statistics = statistics;
    }

    /**
     * Gets all.
     *
     * @param department the department
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(Department department) throws KayakoException {
        return Ticket.getAll(department.getId());
    }

    /**
     * Gets all.
     *
     * @param departmentId the department id
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(int departmentId) throws KayakoException {

        ArrayList<Integer> departments = new ArrayList<Integer>();
        departments.add(departmentId);
        return Ticket.getAll(departments);
    }

    /**
     * Gets all.
     *
     * @param departments the departments
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(ArrayList<?> departments) throws KayakoException {
        if (departments.isEmpty()) {
            throw new KayakoException();
        }
        return Ticket.getAll(departments, new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    /**
     * Gets all.
     *
     * @param departments the departments
     * @param ticketStatuses the ticket statuses
     * @param owners the owners
     * @param users the users
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(ArrayList<?> departments, ArrayList<?> ticketStatuses, ArrayList<?> owners, ArrayList<?> users) throws KayakoException {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        RawArrayElement rawArrayElement = new RawArrayElement();
        if (departments.isEmpty()) {
            throw new KayakoException();
        }
        String departmentString = Integer.toString(KEntity.getId(departments.remove(0)));
        String ticketStatusString = "-1";
        String ownerString = "-1";
        String userString = "-1";
        for (Object department : departments) {
            departmentString += "," + Integer.toString(KEntity.getId(department));
        }

        searchParams.add(departmentString);
        if (!ticketStatuses.isEmpty()) {
            ticketStatusString = Integer.toString(KEntity.getId(ticketStatuses.remove(0)));
            for (Object ticketStatus : ticketStatuses) {
                ticketStatusString += "," + Integer.toString(KEntity.getId(ticketStatus));
            }
            searchParams.add(ticketStatusString);
        }

        if (!owners.isEmpty()) {
            ownerString = Integer.toString(KEntity.getId(owners.remove(0)));
            for (Object owner : owners) {
                ownerString += "," + Integer.toString(KEntity.getId(owner));
            }
            searchParams.add(ownerString);
        }

        if (!users.isEmpty()) {
            userString = Integer.toString(KEntity.getId(users.remove(0)));
            for (Object user : users) {
                userString += "," + Integer.toString(KEntity.getId(user));
            }
            searchParams.add(userString);
        }
        return KEntity.getAll(Ticket.controller, searchParams);
    }

    private static ArrayList<Ticket> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<Ticket> Tickets = new ArrayList<Ticket>();
        for (RawArrayElement rawArrayElementTicket : rawArrayElement.getComponents()) {
            Tickets.add(new Ticket().populate(rawArrayElementTicket));
        }
        return Tickets;
    }

    /**
     * Gets all tickets.
     *
     * @param departmentId the department id
     * @return the all tickets
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<Ticket> getAllTickets(int departmentId) throws KayakoException {
        return refineToArray(getAll(departmentId));
    }

    /**
     * Gets all tickets.
     *
     * @param department the department
     * @return the all tickets
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<Ticket> getAllTickets(Department department) throws KayakoException {
        return getAllTickets(department.getId());
    }

    /**
     * Get ticket.
     *
     * @param id the id
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public static Ticket get(int id) throws KayakoException {
        return new Ticket().populate(KEntity.get(controller, id));
    }

    /**
     * Search array list.
     *
     * @param query the query
     * @param areas the areas
     * @return the array list
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<Ticket> search(String query, ArrayList<String> areas) throws KayakoException {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("query", query);
        for (String area : areas) {
            data.put(area, "1");
        }
        RawArrayElement rawTickets = KEntity.getRESTClient().post("/Tickets/TicketSearch", new ArrayList<String>(), data);
        return refineToArray(rawTickets);
    }

    public Ticket updateCustomFields() throws KayakoException {
        return (Ticket) super.updateCustomFields(getCustomFieldGroupController());
    }

    /**
     * Sets default status, priority and type for newly created tickets.
     *
     * @param statusId Default ticket status identifier.
     * @param priorityId Default ticket priority identifier.
     * @param typeId Default ticket type identifier.
     * @param autoCreateUser True to automatically create user if none is provided as creator. False otherwise.
     */

    public static void setDefaults(int statusId, int priorityId, int typeId, Boolean autoCreateUser) {
        Ticket.setDefaultStatusId(statusId);
        Ticket.setDefaultPriorityId(priorityId);
        Ticket.setDefaultTypeId(typeId);
        Ticket.setAutoCreateUser(autoCreateUser);
    }

    /**
     * Create ticket post.
     *
     * @param creator the creator
     * @param contents the contents
     * @return the ticket post
     */
    public TicketPost createTicketPost(User creator, String contents) {
        return TicketPost.createNew(this, contents, creator);
    }

    /**
     * Create ticket post.
     *
     * @param creator the creator
     * @param contents the contents
     * @return the ticket post
     */
    public TicketPost createTicketPost(Staff creator, String contents) {
        return TicketPost.createNew(this, contents, creator);
    }

    /**
     * Create ticket note.
     *
     * @param creator the creator
     * @param contents the contents
     * @return the ticket note
     */
    public TicketNote createTicketNote(Staff creator, String contents) {
        return new TicketNote(this, creator, contents);
    }

    /**
     * Create ticket time track.
     *
     * @param contents the contents
     * @param staff the staff
     * @param timeWorked the time worked
     * @param timeBillable the time billable
     * @return the ticket time track
     */
    public TicketTimeTrack createTicketTimeTrack(String contents, Staff staff, String timeWorked, String timeBillable) {
        return new TicketTimeTrack(this, contents, staff, timeWorked, timeWorked);
    }

    //TODO - Statistics

    @Override
    public Ticket populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        ArrayList<RawArrayElement> components = rawArrayElement.getComponents();

        this.setId(Helper.parseInt(rawArrayElement.getAttribute("id")));
        this.setFlagType(Helper.parseInt(rawArrayElement.getAttribute("flagtype")));
        for (RawArrayElement component : components) {
            String elementName = component.getElementName();
            if (!component.isComposite() && component.getContent() == null) {
                break;
            }
            if (elementName.equals("displayid")) {
                this.setDisplayId(component.getContent());
            } else if (elementName.equals("departmentid")) {
                this.setDepartmentId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("statusid")) {
                this.setStatusId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("priorityid")) {
                this.setPriorityId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("typeid")) {
                this.setTypeId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userid")) {
                this.setUserId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userorganization")) {
                this.setUserOrganizationName(component.getContent());
            } else if (elementName.equals("userorganizationid")) {
                this.setUserOrganizationId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ownerstaffid")) {
                this.setOwnerStaffId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ownerstaffname")) {
                this.setOwnerStaffName(component.getContent());
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("lastreplier")) {
                this.setLastReplier(component.getContent());
            } else if (elementName.equals("subject")) {
                this.setSubject(component.getContent());
            } else if (elementName.equals("creationtime")) {
                this.setCreationTime(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastactivity")) {
                this.setLastActivity(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("laststaffreply")) {
                this.setLastStaffReply(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastuserreply")) {
                this.setLastUserReply(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("nextreplydue")) {
                this.setNextReplyDue(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("resolutiondue")) {
                this.setResolutionDue(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("replies")) {
                this.setReplies(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ipaddress")) {
                this.setIpAddress(component.getContent());
            } else if (elementName.equals("creator")) {
                this.setCreator(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("creationmode")) {
                this.setCreationMode(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("creationtype")) {
                this.setCreationType(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("isescalated")) {
                this.setEscalated(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("escalationruleid")) {
                this.setEscalationRuleId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("templategroupid")) {
                this.setTemplateGroupId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("templategroupname")) {
                this.setTemplateGroupName(component.getContent());
            } else if (elementName.equals("tags")) {
                this.setTags(component.getContent());
            } else if (elementName.equals("watcher")) {
                this.addWatcher(Helper.parseInt(component.getAttribute("staffid")), component.getAttribute("name"));
            } else if (elementName.equals("workflow")) {
                this.addWorkflow(Helper.parseInt(component.getAttribute("id")), component.getAttribute("title"));
            } else if (elementName.equals("note")) {
                TicketNote ticketNote = new TicketNote();
                ticketNote.populate(component);
                this.addNote(ticketNote);
            } else if (elementName.equals("posts")) {
                for (RawArrayElement postRaw : component.getComponents()) {
                    this.addPost(new TicketPost().populate(postRaw));
                }
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> ticketHashMap = buildHashMap();
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newTicket the new ticket
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newTicket) {
        HashMap<String, String> ticketHashMap = new HashMap<String, String>();
        ticketHashMap.put("subject", this.getSubject());
        ticketHashMap.put("fullname", this.getFullName());
        ticketHashMap.put("email", this.getEmail());

        ticketHashMap.put("departmentid", Integer.toString(this.getDepartmentId()));
        ticketHashMap.put("ticketstatusid", Integer.toString(this.getStaffId()));
        ticketHashMap.put("ticketpriorityid", Integer.toString(this.getPriorityId()));
        ticketHashMap.put("tickettypeid", Integer.toString(this.getTypeId()));
        if (this.getOwnerStaffId() > 0) {
            ticketHashMap.put("ownerstaffid", Integer.toString(this.getOwnerStaffId()));
        }
        if (newTicket) {
            switch (this.getCreator()) {
                case CREATOR_STAFF:
                    ticketHashMap.put("staffid", Integer.toString(this.getStaffId()));
                    break;
                case CREATOR_USER:
                    ticketHashMap.put("userid", Integer.toString(this.getUserId()));
                    break;
                case CREATOR_AUTO:
                    ticketHashMap.put("autouserid", Integer.toString(1));
                    break;
            }
            ticketHashMap.put("contents", this.getContents());
            ticketHashMap.put("type", Integer.toString(this.getCreationType()));
            ticketHashMap.put("ignoreautoresponder", this.getIgnoreAutoResponder() ? "1" : "0");

        } else {
            ticketHashMap.put("userid", Integer.toString(this.getUserId()));
        }

        return ticketHashMap;
    }
}
