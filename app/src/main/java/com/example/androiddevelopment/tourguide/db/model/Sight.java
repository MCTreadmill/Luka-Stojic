package com.example.androiddevelopment.tourguide.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 29.11.17..
 */

@DatabaseTable(tableName = Sight.TABLE_NAME)
public class Sight {

    public static final String TABLE_NAME = "sights";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_DESCRIPTION   = "description";
    public static final String FIELD_NAME_PHOTO = "photo";
    public static final String FIELD_NAME_ADDRESS = "address";
    public static final String FIELD_NAME_PHONE = "phone";
    public static final String FIELD_NAME_WEBSITE = "website";
    public static final String FIELD_NAME_WORKTIME = "worktime";
    public static final String FIELD_NAME_PRICE = "price";
    public static final String FIELD_NAME_COMMENT = "comments";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    private String mDescription;

    @DatabaseField(columnName = FIELD_NAME_PHOTO)
    private String mPhoto;
    
    @DatabaseField(columnName = FIELD_NAME_ADDRESS)
    private String mAdress;

    @DatabaseField(columnName = FIELD_NAME_PHONE)
    private int mPhone;

    @DatabaseField(columnName = FIELD_NAME_WEBSITE)
    private String mWebsite;

    @DatabaseField(columnName = FIELD_NAME_WORKTIME)
    private int mWorktime;

    @DatabaseField(columnName = FIELD_NAME_PRICE)
    private float mPrice;
    
    @DatabaseField(columnName = FIELD_NAME_COMMENT)
    private String mComment;

    public Sight() {

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getmAdress() {
        return mAdress;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public int getmPhone() {
        return mPhone;
    }

    public void setmPhone(int mPhone) {
        this.mPhone = mPhone;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public int getmWorktime() {
        return mWorktime;
    }

    public void setmWorktime(int mWorktime) {
        this.mWorktime = mWorktime;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    @Override
    public String toString() {
        return mName;
    }
}
