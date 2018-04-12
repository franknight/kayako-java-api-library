package com.kayako.api.ticket;

/*
 * Copyright (c) 2013 Kayako
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.department.Department;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.StaffGroup;
import com.kayako.api.util.Helper;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Ticket status.
 */
public class TicketStatus extends TicketAbstract {

  /**
   * The Controller.
   */
  protected static String controller = "/Tickets/TicketStatus";
  /**
   * The Object xml name.
   */
  protected static String objectXmlName = "ticketstatus";

  /**
   * Status of this ticket Status.
   *
   * @apiField
   * @var string
   * @see ::Status constants.
   */
  protected String Status;

  /**
   * If this ticket Status is visible to specific user groups only.
   *
   * @apiField
   * @var bool
   */
  protected boolean staffVisibilityCustom;

  /**
   * Identifiers of staff groups which can change ticket status to this status.
   *
   * @apiField name = staffgroupid
   * @var int[]
   */
  protected List<Integer> staffGroupIds = new ArrayList<Integer>();
  /**
   * Linked department.
   *
   * @var Department
   */
  private Department department;

  /**
   * Staff groups which can change ticket status to this status.
   *
   * @var StaffGroup[]
   */
  private Map<Integer, StaffGroup> staffGroups = new HashMap<Integer, StaffGroup>();
  /**
   * Background color associated with this ticket status in GUI.
   * <p/>
   * This color is used for the "General Tab Bar" in Kayako GUI when viewing the ticket.
   *
   * @apiField
   * @var string
   */
  private String backgroundColor;

  /**
   * Font color associated with this ticket status in GUI.
   *
   * @apiField
   * @var string
   */
  protected String color;

  protected AccessTypeEnum type;

  /**
   * If enabled, Kayako will automatically clear the due time for a ticket when the ticket status changes to this
   * status.
   *
   * @apiField
   * @var bool
   */
  protected boolean resetDueTime;

  /**
   * If enabled, whenever a ticket is changed to this ticket status a survey email will be dispatched to the user asking
   * for rating and comments.
   *
   * @apiField
   * @var bool
   */
  protected boolean triggerSurvey;

  /**
   * If ticket count for this status is displayed in the filter ticket tree.
   *
   * @apiField
   * @var bool
   */

  protected boolean displayCount;

  /**
   * If tickets with this status are marked as resolved/closed.
   *
   * @apiField
   * @var bool
   */
  protected boolean markAsResolved;

  /**
   * If tickets status is to be displayed in main list or not .
   *
   * @apiField
   * @var bool
   */

  protected boolean displayInMainList;

  /**
   * Gets department.
   *
   * @return the department
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Sets department.
   *
   * @param department the department
   */
  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * Is staff visibility custom.
   *
   * @return the boolean
   */
  public boolean isStaffVisibilityCustom() {

    return staffVisibilityCustom;
  }

  /**
   * Sets staff visibility custom.
   *
   * @param staffVisibilityCustom the staff visibility custom
   */
  public void setStaffVisibilityCustom(boolean staffVisibilityCustom) {
    this.staffVisibilityCustom = staffVisibilityCustom;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public String getStatus() {

    return Status;
  }

  /**
   * Sets status.
   *
   * @param Status the status
   */
  public void setStatus(String Status) {
    this.Status = Status;
  }

  public TicketStatus setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
    return this;
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
    TicketStatus.objectXmlName = objectXmlName;
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
    TicketStatus.controller = controller;
  }

  /**
   * Gets background color.
   *
   * @return the background color
   */
  public String getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * Gets staff groups.
   *
   * @return the staff groups
   * @throws com.kayako.api.exception.KayakoException the kayako exception
   */
  public Map<Integer, StaffGroup> getStaffGroups() throws KayakoException {
    return getStaffGroups(false);
  }

  /**
   * Gets staff groups.
   *
   * @param refresh the refresh
   * @return the staff groups
   * @throws KayakoException the kayako exception
   */
  public Map<Integer, StaffGroup> getStaffGroups(boolean refresh) throws KayakoException {
    for (int staffGroupId : getStaffGroupIds()) {
      if (!staffGroups.containsKey(staffGroupId) || refresh) {
        staffGroups.put(staffGroupId, StaffGroup.get(staffGroupId));
      }
    }
    return staffGroups;
  }

  /**
   * Sets staff groups.
   *
   * @param staffGroups the staff groups
   */
  public void setStaffGroups(HashMap<Integer, StaffGroup> staffGroups) {
    this.staffGroups = staffGroups;
  }

  /**
   * Sets background color.
   *
   * @param backgroundColor the background color
   */
  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * Sets color.
   *
   * @param color the color
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Gets reset due time.
   *
   * @return the reset due time
   */
  public boolean getResetDueTime() {
    return resetDueTime;
  }

  /**
   * Sets reset due time.
   *
   * @param resetDueTime the reset due time
   */
  public void setResetDueTime(boolean resetDueTime) {
    this.resetDueTime = resetDueTime;
  }

  /**
   * Gets display count.
   *
   * @return the display count
   */
  public boolean getDisplayCount() {
    return displayCount;
  }

  /**
   * Sets display count.
   *
   * @param displayCount the display count
   */
  public void setDisplayCount(boolean displayCount) {
    this.displayCount = displayCount;
  }

  /**
   * Gets mark as resolved.
   *
   * @return the mark as resolved
   */
  public boolean getMarkAsResolved() {
    return markAsResolved;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public AccessTypeEnum getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public TicketStatus setType(AccessTypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Gets trigger survey.
   *
   * @return the trigger survey
   */
  public boolean getTriggerSurvey() {
    return triggerSurvey;
  }

  /**
   * Sets trigger survey.
   *
   * @param triggerSurvey the trigger survey
   */
  public void setTriggerSurvey(boolean triggerSurvey) {
    this.triggerSurvey = triggerSurvey;
  }

  /**
   * Sets mark as resolved.
   *
   * @param markAsResolved the mark as resolved
   */
  public void setMarkAsResolved(boolean markAsResolved) {
    this.markAsResolved = markAsResolved;
  }

  /**
   * Gets staff group ids.
   *
   * @return the staff group ids
   */
  public List<Integer> getStaffGroupIds() {
    return staffGroupIds;
  }

  /**
   * Sets staff group ids.
   *
   * @param staffGroupIds the staff group ids
   */
  public void setStaffGroupIds(ArrayList<Integer> staffGroupIds) {
    this.staffGroupIds = staffGroupIds;
  }

  /**
   * Gets display in main list.
   *
   * @return the display in main list
   */
  public boolean getDisplayInMainList() {
    return displayInMainList;
  }

  /**
   * Sets display in main list.
   *
   * @param displayInMainList the display in main list
   * @return the display in main list
   */
  public TicketStatus setDisplayInMainList(boolean displayInMainList) {
    this.displayInMainList = displayInMainList;
    return this;
  }

  /**
   * Is visible to staff group.
   *
   * @param staffGroupId the staff group id
   * @return the boolean
   */
  public boolean isVisibleToStaffGroup(int staffGroupId) {
    if (!isStaffVisibilityCustom()) {
      return true;
    }
    return this.staffGroupIds.contains(staffGroupId);
  }

  /**
   * Is visible to staff group.
   *
   * @param staffGroup the staff group
   * @return the boolean
   */
  public boolean isVisibleToStaffGroup(StaffGroup staffGroup) {
    return this.isVisibleToStaffGroup(staffGroup.getId());
  }

  /**
   * Is available in department.
   *
   * @param departmentId the department id
   * @return the boolean
   */
  public boolean isAvailableInDepartment(int departmentId) {
    if (this.departmentId == 0) {
      return true;
    }
    return this.departmentId == departmentId;
  }

  /**
   * Is available in department.
   *
   * @param department the department
   * @return the boolean
   */
  public boolean isAvailableInDepartment(Department department) {
    return this.isAvailableInDepartment(department.getId());
  }

  /**
   * Get ticket status.
   *
   * @param id the id
   * @return the ticket status
   * @throws KayakoException the kayako exception
   */
  public static TicketStatus get(int id) throws KayakoException {
    return new TicketStatus().populate(get(controller, id));
  }

  /**
   * Gets all.
   *
   * @return the all
   * @throws KayakoException the kayako exception
   */
  public static RawArrayElement getAll() throws KayakoException {
    return getAll(controller);
  }

  // this function will populate the data of the ticket Status instance when supplied with RawArrayElement derived from
  // the xml
  @Override
  public TicketStatus populate(RawArrayElement element) throws KayakoException {
    if (!element.getElementName().equals(objectXmlName)) {
      throw new KayakoException();
    }

    List<RawArrayElement> components = element.getComponents();
    for (RawArrayElement component : components) {
      String elementName = component.getElementName();
      if (!component.isComposite() && component.getContent() == null) {
        break;
      }
      if (elementName.equals("id")) {
        this.setId(Helper.parseInt(component.getContent()));
      } else if (elementName.equals("title")) {
        this.setTitle(component.getContent());
      } else if (elementName.equals("displayorder")) {
        this.setDisplayOrder(Helper.parseInt(component.getContent()));
      } else if (elementName.equals("departmentid")) {
        this.setDepartmentId(Helper.parseInt(component.getContent()));
      } else if (elementName.equals("displayicon")) {
        this.setDisplayIcon(component.getContent());
      } else if (elementName.equals("type")) {
        this.setType(AccessTypeEnum.getEnum(component.getContent()));
      } else if (elementName.equals("statuscolor")) {
        this.setColor(component.getContent());
      } else if (elementName.equals("statusbgcolor")) {
        this.setBackgroundColor(component.getContent());
      } else if (elementName.equals("Status")) {
        this.setStatus(component.getContent());
      } else if (elementName.equals("resetduetime")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setResetDueTime(true);
        } else {
          this.setResetDueTime(false);
        }
      } else if (elementName.equals("displayinmainlist")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setDisplayInMainList(true);
        } else {
          this.setDisplayInMainList(false);
        }
      } else if (elementName.equals("staffvisibilitycustom")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setStaffVisibilityCustom(true);
        } else {
          this.setStaffVisibilityCustom(false);
        }
      } else if (elementName.equals("staffgroupid")) {
        this.staffGroupIds.add(new Integer(component.getContent()));
      } else if (elementName.equals("markasresolved")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setMarkAsResolved(true);
        } else {
          this.setMarkAsResolved(false);
        }
      } else if (elementName.equals("displaycount")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setDisplayCount(true);
        } else {
          this.setDisplayCount(false);
        }
      } else if (elementName.equals("triggersurvey")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setTriggerSurvey(true);
        } else {
          this.setTriggerSurvey(false);
        }
      }
    }
    return this;
  }

  public String toString() {
    return super.toString();
  }
}
