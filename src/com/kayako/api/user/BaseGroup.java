package com.kayako.api.user;

import com.kayako.api.rest.KEntity;

public abstract class BaseGroup extends KEntity {
    /**
     * Base Group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Base Group title.
     *
     * @apiField
     * @var string
     */
    protected String title;
    
    /**
     * Gets Group title.
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
     * Gets title.
     *
     * @return the title
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
     * Gets read only?
     *
     * @return readOnly
     */
    public Boolean getReadOnly() {

        return readOnly;
    }
}
