package com.example.smartplanterproject;

/**
 * The type Planter.
 */
public class Planter {

    private String keyid;
    private User user;
    private String temp;
    private boolean fan;
    private boolean bereathWater;
    private boolean sunLightSensor;
    private boolean airSensor;
    private boolean waterTank;

    /**
     * Instantiates a new Planter.
     *
     * @param keyid          the keyid
     * @param bereathWater   the bereath water
     * @param temp           the temp
     * @param fan            the fan
     * @param sunLightSensor the sun light sensor
     * @param user           the user
     * @param airSensor      the air sensor
     * @param waterTank      the water tank
     */
    public Planter(String keyid,boolean bereathWater,String temp,boolean fan,boolean sunLightSensor,User user,boolean airSensor,boolean waterTank)
    {
        this.keyid = keyid;
        this.fan = fan;
        this.temp = temp;
        this.bereathWater = bereathWater;
        this.sunLightSensor =sunLightSensor;
        this.user = user;
        this.airSensor = airSensor;
        this.waterTank = waterTank;
    }

    /**
     * Instantiates a new Planter.
     */
    public Planter(){}

    /**
     * Instantiates a new Planter.
     *
     * @param waterTank      the water tank
     * @param airSensor      the air sensor
     * @param bereathWater   the bereath water
     * @param fan            the fan
     * @param sunLightSensor the sun light sensor
     * @param temp           the temp
     */
    public Planter(boolean waterTank,boolean airSensor,boolean bereathWater,boolean fan,boolean sunLightSensor,String temp)
    {
        this.fan = fan;
        this.temp = temp;
        this.airSensor = airSensor;
        this.waterTank = waterTank;
        this.bereathWater = bereathWater;
        this.sunLightSensor =sunLightSensor;
    }

    /**
     * Gets air sensor.
     *
     * @return the air sensor
     */
    public boolean getAirSensor() {
        return airSensor;
    }

    /**
     * Gets temp.
     *
     * @return the temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * Gets water empty.
     *
     * @return the water empty
     */
    public boolean getWaterEmpty() {
        return waterTank;
    }

    /**
     * Gets keyid.
     *
     * @return the keyid
     */
    public String getKeyid() {
        return keyid;
    }

    /**
     * Gets is fan.
     *
     * @return the is fan
     */
    public boolean getIsFan() {
        return fan;
    }

    /**
     * Gets is bereath water.
     *
     * @return the is bereath water
     */
    public boolean getIsBereathWater() {
        return bereathWater;
    }

    /**
     * Gets is sun light sensor.
     *
     * @return the is sun light sensor
     */
    public boolean getIsSunLightSensor() {
        return sunLightSensor;
    }

    /**
     * Sets air sensor.
     *
     * @param airSensor the air sensor
     */
    public void setAirSensor(boolean airSensor) {
        this.airSensor = airSensor;
    }

    /**
     * Sets bereath water.
     *
     * @param bereathWater the bereath water
     */
    public void setBereathWater(boolean bereathWater) {
        this.bereathWater = bereathWater;
    }

    /**
     * Sets fan.
     *
     * @param fan the fan
     */
    public void setFan(boolean fan) {
        this.fan = fan;
    }

    /**
     * Sets keyid.
     *
     * @param keyid the keyid
     */
    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    /**
     * Sets sun light sensor.
     *
     * @param sunLightSensor the sun light sensor
     */
    public void setSunLightSensor(boolean sunLightSensor) {
        this.sunLightSensor = sunLightSensor;
    }

    /**
     * Sets water empty.
     *
     * @param waterEmpty the water empty
     */
    public void setWaterEmpty(boolean waterEmpty) {
        this.waterTank = waterEmpty;
    }

    /**
     * Sets temp.
     *
     * @param temp the temp
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }
}


