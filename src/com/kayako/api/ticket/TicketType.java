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
import com.kayako.api.user.UserGroup;
import com.kayako.api.util.Helper;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Ticket type.
 */
public class TicketType extends TicketAbstract {

  /**
   * The Controller.
   */
  protected static String controller = "/Tickets/TicketType";
  /**
   * The Object xml name.
   */
  protected static String objectXmlName = "tickettype";

  /**
   * Linked department identifier.
   * <p/>
   * If a ticket type is linked to a department, it will be visible only under the linked department.
   *
   * @apiField
   * @var int
   */
  protected int departmentId;

  protected AccessTypeEnum type;

  /**
   * If this ticket type is visible to specific user groups only.
   *
   * @apiField
   * @var bool
   */
  protected boolean userVisibilityCustom;

  /**
   * Identifier of user group this ticket type is visible to.
   *
   * @apiField name =usergroupid
   * @var int[]
   */
  protected List<Integer> userGroupIds = new ArrayList<Integer>();

  /**
   * Linked department.
   *
   * @var Department
   */
  private Department department = null;

  private HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();

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
   * Is user visibility custom.
   *
   * @return the boolean
   */
  public boolean isUserVisibilityCustom() {

    return userVisibilityCustom;
  }

  /**
   * Sets user visibility custom.
   *
   * @param userVisibilityCustom the user visibility custom
   */
  public void setUserVisibilityCustom(boolean userVisibilityCustom) {
    this.userVisibilityCustom = userVisibilityCustom;
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
  public void setType(AccessTypeEnum type) {
    this.type = type;
  }

  public TicketType setReadOnly(boolean readOnly) {
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
    TicketType.objectXmlName = objectXmlName;
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
    TicketType.controller = controller;
  }

  /**
   * Gets user group ids.
   *
   * @return the user group ids
   */
  public List<Integer> getUserGroupIds() {
    return userGroupIds;
  }

  /**
   * Sets user group ids.
   *
   * @param userGroupIds the user group ids
   */
  public void setUserGroupIds(List<Integer> userGroupIds) {
    this.userGroupIds = userGroupIds;
  }

  /**
   * Gets user groups.
   *
   * @return the user groups
   * @throws com.kayako.api.exception.KayakoException the kayako exception
   */
  public Map<Integer, UserGroup> getUserGroups() throws KayakoException {
    return getUserGroups(false);
  }

  /**
   * Gets user groups.
   *
   * @param refresh the refresh
   * @return the user groups
   * @throws KayakoException the kayako exception
   */
  public Map<Integer, UserGroup> getUserGroups(boolean refresh) throws KayakoException {
    for (int userGroupId : this.getUserGroupIds()) {
      if (!userGroups.containsKey(userGroupId) || refresh) {
        userGroups.put(userGroupId, UserGroup.get(userGroupId));
      }
    }
    return userGroups;
  }

  /**
   * Sets user groups.
   *
   * @param userGroups the user groups
   * @return the user groups
   */
  public TicketType setUserGroups(HashMap<Integer, UserGroup> userGroups) {
    this.userGroups = userGroups;
    return this;
  }

  /**
   * Is visible to user group.
   *
   * @param userGroupId the user group id
   * @return the boolean
   */
  public boolean isVisibleToUserGroup(int userGroupId) {
    if (!isUserVisibilityCustom()) {
      return true;
    }
    return this.userGroupIds.contains(userGroupId);
  }

  /**
   * Is visible to user group.
   *
   * @param userGroup the user group
   * @return the boolean
   */
  public boolean isVisibleToUserGroup(UserGroup userGroup) {
    return this.isVisibleToUserGroup(userGroup.getId());
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
   * Get ticket type.
   *
   * @param id the id
   * @return the ticket type
   * @throws KayakoException the kayako exception
   */
  public static TicketType get(int id) throws KayakoException {
    return new TicketType().populate(get(controller, id));
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

  // this function will populate the data of the ticket type instance when supplied with RawArrayElement derived from
  // the xml
  @Override
  public TicketType populate(RawArrayElement element) throws KayakoException {
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
      } else if (elementName.equals("uservisibilitycustom")) {
        if (Helper.parseInt(component.getContent()) == 1) {
          this.setUserVisibilityCustom(true);
        } else {
          this.setUserVisibilityCustom(false);
        }
      } else if (elementName.equals("usergroupid")) {
        this.userGroupIds.add(new Integer(component.getContent()));
      }

    }

    return this;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
