package com.ziran.meiliao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ziran.meiliao.entry.UserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO".
*/
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Sex = new Property(1, String.class, "sex", false, "SEX");
        public final static Property LastUpdateTime = new Property(2, String.class, "lastUpdateTime", false, "LAST_UPDATE_TIME");
        public final static Property HeadImgVersion = new Property(3, int.class, "headImgVersion", false, "HEAD_IMG_VERSION");
        public final static Property UniId = new Property(4, int.class, "uniId", false, "UNI_ID");
        public final static Property Descript = new Property(5, String.class, "descript", false, "DESCRIPT");
        public final static Property Country = new Property(6, String.class, "country", false, "COUNTRY");
        public final static Property Provience = new Property(7, String.class, "provience", false, "PROVIENCE");
        public final static Property City = new Property(8, String.class, "city", false, "CITY");
        public final static Property Identity = new Property(9, String.class, "identity", false, "IDENTITY");
        public final static Property HeadImg = new Property(10, String.class, "headImg", false, "HEAD_IMG");
        public final static Property Email = new Property(11, String.class, "email", false, "EMAIL");
        public final static Property NickName = new Property(12, String.class, "nickName", false, "NICK_NAME");
        public final static Property Age = new Property(13, String.class, "age", false, "AGE");
        public final static Property RealName = new Property(14, String.class, "realName", false, "REAL_NAME");
        public final static Property IdentityImage = new Property(15, String.class, "identityImage", false, "IDENTITY_IMAGE");
        public final static Property IsValidate = new Property(16, boolean.class, "isValidate", false, "IS_VALIDATE");
        public final static Property IsTeacher = new Property(17, boolean.class, "isTeacher", false, "IS_TEACHER");
        public final static Property Phone = new Property(18, String.class, "phone", false, "PHONE");
        public final static Property Career = new Property(19, String.class, "career", false, "CAREER");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SEX\" TEXT," + // 1: sex
                "\"LAST_UPDATE_TIME\" TEXT," + // 2: lastUpdateTime
                "\"HEAD_IMG_VERSION\" INTEGER NOT NULL ," + // 3: headImgVersion
                "\"UNI_ID\" INTEGER NOT NULL ," + // 4: uniId
                "\"DESCRIPT\" TEXT," + // 5: descript
                "\"COUNTRY\" TEXT," + // 6: country
                "\"PROVIENCE\" TEXT," + // 7: provience
                "\"CITY\" TEXT," + // 8: city
                "\"IDENTITY\" TEXT," + // 9: identity
                "\"HEAD_IMG\" TEXT," + // 10: headImg
                "\"EMAIL\" TEXT," + // 11: email
                "\"NICK_NAME\" TEXT," + // 12: nickName
                "\"AGE\" TEXT," + // 13: age
                "\"REAL_NAME\" TEXT," + // 14: realName
                "\"IDENTITY_IMAGE\" TEXT," + // 15: identityImage
                "\"IS_VALIDATE\" INTEGER NOT NULL ," + // 16: isValidate
                "\"IS_TEACHER\" INTEGER NOT NULL ," + // 17: isTeacher
                "\"PHONE\" TEXT," + // 18: phone
                "\"CAREER\" TEXT);"); // 19: career
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(2, sex);
        }
 
        String lastUpdateTime = entity.getLastUpdateTime();
        if (lastUpdateTime != null) {
            stmt.bindString(3, lastUpdateTime);
        }
        stmt.bindLong(4, entity.getHeadImgVersion());
        stmt.bindLong(5, entity.getUniId());
 
        String descript = entity.getDescript();
        if (descript != null) {
            stmt.bindString(6, descript);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(7, country);
        }
 
        String provience = entity.getProvience();
        if (provience != null) {
            stmt.bindString(8, provience);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(9, city);
        }
 
        String identity = entity.getIdentity();
        if (identity != null) {
            stmt.bindString(10, identity);
        }
 
        String headImg = entity.getHeadImg();
        if (headImg != null) {
            stmt.bindString(11, headImg);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(12, email);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(13, nickName);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(14, age);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(15, realName);
        }
 
        String identityImage = entity.getIdentityImage();
        if (identityImage != null) {
            stmt.bindString(16, identityImage);
        }
        stmt.bindLong(17, entity.getIsValidate() ? 1L: 0L);
        stmt.bindLong(18, entity.getIsTeacher() ? 1L: 0L);
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(19, phone);
        }
 
        String career = entity.getCareer();
        if (career != null) {
            stmt.bindString(20, career);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(2, sex);
        }
 
        String lastUpdateTime = entity.getLastUpdateTime();
        if (lastUpdateTime != null) {
            stmt.bindString(3, lastUpdateTime);
        }
        stmt.bindLong(4, entity.getHeadImgVersion());
        stmt.bindLong(5, entity.getUniId());
 
        String descript = entity.getDescript();
        if (descript != null) {
            stmt.bindString(6, descript);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(7, country);
        }
 
        String provience = entity.getProvience();
        if (provience != null) {
            stmt.bindString(8, provience);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(9, city);
        }
 
        String identity = entity.getIdentity();
        if (identity != null) {
            stmt.bindString(10, identity);
        }
 
        String headImg = entity.getHeadImg();
        if (headImg != null) {
            stmt.bindString(11, headImg);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(12, email);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(13, nickName);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(14, age);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(15, realName);
        }
 
        String identityImage = entity.getIdentityImage();
        if (identityImage != null) {
            stmt.bindString(16, identityImage);
        }
        stmt.bindLong(17, entity.getIsValidate() ? 1L: 0L);
        stmt.bindLong(18, entity.getIsTeacher() ? 1L: 0L);
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(19, phone);
        }
 
        String career = entity.getCareer();
        if (career != null) {
            stmt.bindString(20, career);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sex
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastUpdateTime
            cursor.getInt(offset + 3), // headImgVersion
            cursor.getInt(offset + 4), // uniId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // descript
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // country
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // provience
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // city
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // identity
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // headImg
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // email
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // nickName
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // age
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // realName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // identityImage
            cursor.getShort(offset + 16) != 0, // isValidate
            cursor.getShort(offset + 17) != 0, // isTeacher
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // phone
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19) // career
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSex(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastUpdateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHeadImgVersion(cursor.getInt(offset + 3));
        entity.setUniId(cursor.getInt(offset + 4));
        entity.setDescript(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCountry(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setProvience(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCity(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIdentity(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setHeadImg(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setEmail(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setNickName(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setAge(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setRealName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setIdentityImage(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setIsValidate(cursor.getShort(offset + 16) != 0);
        entity.setIsTeacher(cursor.getShort(offset + 17) != 0);
        entity.setPhone(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setCareer(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
