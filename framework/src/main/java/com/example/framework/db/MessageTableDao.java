package com.example.framework.db;

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
        public final static Property MessageId = new Property(0, Long.class, "messageId", true, "_id");
        public final static Property Message = new Property(1, String.class, "message", false, "MESSAGE");
        public final static Property Time = new Property(2, String.class, "time", false, "TIME");
        public final static Property IsRead = new Property(3, boolean.class, "isRead", false, "IS_READ");
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
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: messageId
                "\"MESSAGE\" TEXT," + // 1: message
                "\"TIME\" TEXT," + // 2: time
                "\"IS_READ\" INTEGER NOT NULL );"); // 3: isRead
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGE_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MessageTable entity) {
        stmt.clearBindings();
 
        Long messageId = entity.getMessageId();
        if (messageId != null) {
            stmt.bindLong(1, messageId);
        }
 
        String message = entity.getMessage();
        if (message != null) {
            stmt.bindString(2, message);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(3, time);
        }
        stmt.bindLong(4, entity.getIsRead() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MessageTable entity) {
        stmt.clearBindings();
 
        Long messageId = entity.getMessageId();
        if (messageId != null) {
            stmt.bindLong(1, messageId);
        }
 
        String message = entity.getMessage();
        if (message != null) {
            stmt.bindString(2, message);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(3, time);
        }
        stmt.bindLong(4, entity.getIsRead() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MessageTable readEntity(Cursor cursor, int offset) {
        MessageTable entity = new MessageTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // messageId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // message
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // time
            cursor.getShort(offset + 3) != 0 // isRead
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MessageTable entity, int offset) {
        entity.setMessageId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMessage(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIsRead(cursor.getShort(offset + 3) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MessageTable entity, long rowId) {
        entity.setMessageId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MessageTable entity) {
        if(entity != null) {
            return entity.getMessageId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MessageTable entity) {
        return entity.getMessageId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
