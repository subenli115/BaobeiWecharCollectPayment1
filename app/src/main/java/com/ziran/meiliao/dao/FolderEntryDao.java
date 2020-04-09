package com.ziran.meiliao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ziran.meiliao.entry.FolderEntry;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FOLDER_ENTRY".
*/
public class FolderEntryDao extends AbstractDao<FolderEntry, Long> {

    public static final String TABLENAME = "FOLDER_ENTRY";

    /**
     * Properties of entity FolderEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Folder_name = new Property(1, String.class, "folder_name", false, "FOLDER_NAME");
        public final static Property Folder_path = new Property(2, String.class, "folder_path", false, "FOLDER_PATH");
    }


    public FolderEntryDao(DaoConfig config) {
        super(config);
    }
    
    public FolderEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOLDER_ENTRY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"FOLDER_NAME\" TEXT," + // 1: folder_name
                "\"FOLDER_PATH\" TEXT);"); // 2: folder_path
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOLDER_ENTRY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FolderEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String folder_name = entity.getFolder_name();
        if (folder_name != null) {
            stmt.bindString(2, folder_name);
        }
 
        String folder_path = entity.getFolder_path();
        if (folder_path != null) {
            stmt.bindString(3, folder_path);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FolderEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String folder_name = entity.getFolder_name();
        if (folder_name != null) {
            stmt.bindString(2, folder_name);
        }
 
        String folder_path = entity.getFolder_path();
        if (folder_path != null) {
            stmt.bindString(3, folder_path);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FolderEntry readEntity(Cursor cursor, int offset) {
        FolderEntry entity = new FolderEntry( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // folder_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // folder_path
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FolderEntry entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFolder_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFolder_path(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FolderEntry entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FolderEntry entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FolderEntry entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}