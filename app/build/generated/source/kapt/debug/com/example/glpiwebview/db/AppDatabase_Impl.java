package com.example.glpiwebview.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TicketDao _ticketDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tickets` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `dateModified` TEXT NOT NULL, `status` INTEGER NOT NULL, `urgency` INTEGER NOT NULL, `requesterId` INTEGER NOT NULL, `requesterName` TEXT, `dateCreation` TEXT NOT NULL, `closeDate` TEXT, `solveDate` TEXT, `content` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '044ccf08e28335c6a6e33716ab77492c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `tickets`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTickets = new HashMap<String, TableInfo.Column>(11);
        _columnsTickets.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("dateModified", new TableInfo.Column("dateModified", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("urgency", new TableInfo.Column("urgency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("requesterId", new TableInfo.Column("requesterId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("requesterName", new TableInfo.Column("requesterName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("dateCreation", new TableInfo.Column("dateCreation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("closeDate", new TableInfo.Column("closeDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("solveDate", new TableInfo.Column("solveDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTickets.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTickets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTickets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTickets = new TableInfo("tickets", _columnsTickets, _foreignKeysTickets, _indicesTickets);
        final TableInfo _existingTickets = TableInfo.read(_db, "tickets");
        if (! _infoTickets.equals(_existingTickets)) {
          return new RoomOpenHelper.ValidationResult(false, "tickets(com.example.glpiwebview.api.TicketEntity).\n"
                  + " Expected:\n" + _infoTickets + "\n"
                  + " Found:\n" + _existingTickets);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "044ccf08e28335c6a6e33716ab77492c", "1887892c00a5cd1f9e330d108f920519");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "tickets");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tickets`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TicketDao.class, TicketDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public TicketDao ticketDao() {
    if (_ticketDao != null) {
      return _ticketDao;
    } else {
      synchronized(this) {
        if(_ticketDao == null) {
          _ticketDao = new TicketDao_Impl(this);
        }
        return _ticketDao;
      }
    }
  }
}
