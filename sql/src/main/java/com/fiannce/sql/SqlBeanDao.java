package com.fiannce.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SQL_BEAN".
*/
public class SqlBeanDao extends AbstractDao<SqlBean, Void> {

    public static final String TABLENAME = "SQL_BEAN";

    /**
     * Properties of entity SqlBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ProductId = new Property(0, String.class, "productId", false, "PRODUCT_ID");
        public final static Property ProductNum = new Property(1, int.class, "productNum", false, "PRODUCT_NUM");
        public final static Property ProductName = new Property(2, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property Url = new Property(3, String.class, "url", false, "URL");
        public final static Property ProductPrice = new Property(4, String.class, "productPrice", false, "PRODUCT_PRICE");
        public final static Property ProductSelected = new Property(5, boolean.class, "productSelected", false, "PRODUCT_SELECTED");
    }


    public SqlBeanDao(DaoConfig config) {
        super(config);
    }
    
    public SqlBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SQL_BEAN\" (" + //
                "\"PRODUCT_ID\" TEXT," + // 0: productId
                "\"PRODUCT_NUM\" INTEGER NOT NULL ," + // 1: productNum
                "\"PRODUCT_NAME\" TEXT," + // 2: productName
                "\"URL\" TEXT," + // 3: url
                "\"PRODUCT_PRICE\" TEXT," + // 4: productPrice
                "\"PRODUCT_SELECTED\" INTEGER NOT NULL );"); // 5: productSelected
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SQL_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SqlBean entity) {
        stmt.clearBindings();
 
        String productId = entity.getProductId();
        if (productId != null) {
            stmt.bindString(1, productId);
        }
        stmt.bindLong(2, entity.getProductNum());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(3, productName);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(4, url);
        }
 
        String productPrice = entity.getProductPrice();
        if (productPrice != null) {
            stmt.bindString(5, productPrice);
        }
        stmt.bindLong(6, entity.getProductSelected() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SqlBean entity) {
        stmt.clearBindings();
 
        String productId = entity.getProductId();
        if (productId != null) {
            stmt.bindString(1, productId);
        }
        stmt.bindLong(2, entity.getProductNum());
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(3, productName);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(4, url);
        }
 
        String productPrice = entity.getProductPrice();
        if (productPrice != null) {
            stmt.bindString(5, productPrice);
        }
        stmt.bindLong(6, entity.getProductSelected() ? 1L: 0L);
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SqlBean readEntity(Cursor cursor, int offset) {
        SqlBean entity = new SqlBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // productId
            cursor.getInt(offset + 1), // productNum
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // productName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // url
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // productPrice
            cursor.getShort(offset + 5) != 0 // productSelected
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SqlBean entity, int offset) {
        entity.setProductId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setProductNum(cursor.getInt(offset + 1));
        entity.setProductName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProductPrice(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProductSelected(cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SqlBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SqlBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(SqlBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
