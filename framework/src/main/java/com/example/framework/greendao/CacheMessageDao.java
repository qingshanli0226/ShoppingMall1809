package com.example.framework.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CACHE_MESSAGE".
*/
public class CacheMessageDao extends AbstractDao<CacheMessage, Long> {

    public static final String TABLENAME = "CACHE_MESSAGE";

    /**
     * Properties of entity CacheMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Msg = new Property(2, String.class, "msg", false, "MSG");
        public final static Property IsNew = new Property(3, boolean.class, "isNew", false, "IS_NEW");
        public final static Property Time = new Property(4, long.class, "time", false, "TIME");
        public final static Property ReserveOne = new Property(5, String.class, "reserveOne", false, "RESERVE_ONE");
        public final static Property ReserveTwo = new Property(6, String.class, "reserveTwo", false, "RESERVE_TWO");
    }


    public CacheMessageDao(DaoConfig config) {
        super(config);
    }
    
    public CacheMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CACHE_MESSAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"MSG\" TEXT," + // 2: msg
                "\"IS_NEW\" INTEGER NOT NULL ," + // 3: isNew
                "\"TIME\" INTEGER NOT NULL ," + // 4: time
                "\"RESERVE_ONE\" TEXT," + // 5: reserveOne
                "\"RESERVE_TWO\" TEXT);"); // 6: reserveTwo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CACHE_MESSAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CacheMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String msg = entity.getMsg();
        if (msg != null) {
            stmt.bindString(3, msg);
        }
        stmt.bindLong(4, entity.getIsNew() ? 1L: 0L);
        stmt.bindLong(5, entity.getTime());
 
        String reserveOne = entity.getReserveOne();
        if (reserveOne != null) {
            stmt.bindString(6, reserveOne);
        }
 
        String reserveTwo = entity.getReserveTwo();
        if (reserveTwo != null) {
            stmt.bindString(7, reserveTwo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CacheMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String msg = entity.getMsg();
        if (msg != null) {
            stmt.bindString(3, msg);
        }
        stmt.bindLong(4, entity.getIsNew() ? 1L: 0L);
        stmt.bindLong(5, entity.getTime());
 
        String reserveOne = entity.getReserveOne();
        if (reserveOne != null) {
            stmt.bindString(6, reserveOne);
        }
 
        String reserveTwo = entity.getReserveTwo();
        if (reserveTwo != null) {
            stmt.bindString(7, reserveTwo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CacheMessage readEntity(Cursor cursor, int offset) {
        CacheMessage entity = new CacheMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // msg
            cursor.getShort(offset + 3) != 0, // isNew
            cursor.getLong(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // reserveOne
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // reserveTwo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CacheMessage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMsg(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIsNew(cursor.getShort(offset + 3) != 0);
        entity.setTime(cursor.getLong(offset + 4));
        entity.setReserveOne(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setReserveTwo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CacheMessage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CacheMessage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CacheMessage entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
