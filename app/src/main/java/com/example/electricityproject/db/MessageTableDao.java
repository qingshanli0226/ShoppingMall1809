package com.example.electricityproject.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MESSAGE_TABLE".
*/
public class MessageTableDao extends AbstractDao<MessageTable, Long> {

    public static final String TABLENAME = "MESSAGE_TABLE";

    /**
     * Properties of entity MessageTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PayMessage = new Property(1, String.class, "payMessage", false, "PAY_MESSAGE");
        public final static Property Time = new Property(2, long.class, "time", false, "TIME");
        public final static Property IsShow = new Property(3, boolean.class, "isShow", false, "IS_SHOW");
    }


    public MessageTableDao(DaoConfig config) {
        super(config);
    }
    
    public MessageTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGE_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PAY_MESSAGE\" TEXT NOT NULL ," + // 1: payMessage
                "\"TIME\" INTEGER NOT NULL ," + // 2: time
                "\"IS_SHOW\" INTEGER NOT NULL );"); // 3: isShow
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGE_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MessageTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getPayMessage());
        stmt.bindLong(3, entity.getTime());
        stmt.bindLong(4, entity.getIsShow() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MessageTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getPayMessage());
        stmt.bindLong(3, entity.getTime());
        stmt.bindLong(4, entity.getIsShow() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MessageTable readEntity(Cursor cursor, int offset) {
        MessageTable entity = new MessageTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // payMessage
            cursor.getLong(offset + 2), // time
            cursor.getShort(offset + 3) != 0 // isShow
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MessageTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPayMessage(cursor.getString(offset + 1));
        entity.setTime(cursor.getLong(offset + 2));
        entity.setIsShow(cursor.getShort(offset + 3) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MessageTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MessageTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MessageTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
