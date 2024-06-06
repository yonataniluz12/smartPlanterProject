package com.example.smartplanterproject;

/**
 * The type User.
 */
public class User {
    private String name;
    private String id;

    /**
     * Instantiates a new User.
     *
     * @param name the name
     * @param id   the id
     */
    public User (String name,String id){
        this.name =name;
        this.id = id;
    }

    /**
     * Instantiates a new User.
     */
    public User(){}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void  setName(String name){
        this.name =name;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
    }

