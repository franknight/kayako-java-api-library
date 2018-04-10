package com.kayako.api.ticket;

import com.kayako.api.rest.KEntity;


abstract class TicketAbstract extends KEntity {

  /**
   * Path to icon displayed in GUI for this ticket.
   *
   * @apiField
   * @var string
   */
  protected String displayIcon;

  /**
   * Linked department identifier.
   * <p/>
   * If a ticket Status is linked to a department, it will be visible only under the linked department.
   *
   * @apiField
   * @var int
   */
  protected int departmentId;

  /**
   * Ticket type display order.
   *
   * @apiField
   * @var int
   */
  protected int displayOrder;

  /**
   * Ticket type title.
   *
   * @apiField
   * @var string
   */
  protected String title;

  /**
   * Object identifier.
   *
   * @apiField
   * @var int
   */
  protected int id;

  /**
   * The Read only.
   */
  protected Boolean readOnly = true;

  /**
   * Gets display icon.
   *
   * @return the display icon
   */

  public String getDisplayIcon() {

    return displayIcon;
  }

  /**
   * Sets display icon.
   *
   * @param displayIcon the display icon
   */
  public void setDisplayIcon(String displayIcon) {
    this.displayIcon = displayIcon;
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
   */
  public void setDepartmentId(int departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * Gets display order.
   *
   * @return the display order
   */
  public int getDisplayOrder() {

    return displayOrder;
  }

  /**
   * Sets display order.
   *
   * @param displayOrder the display order
   */
  public void setDisplayOrder(int displayOrder) {
    this.displayOrder = displayOrder;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {

    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {

    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Checks if the object is read only
   *
   * @return readOnly
   */
  public Boolean getReadOnly() {

    return readOnly;
  }
}
