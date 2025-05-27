package com.example.glpiwebview.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.glpiwebview.api.TicketEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TicketDao_Impl implements TicketDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TicketEntity> __insertionAdapterOfTicketEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTickets;

  public TicketDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTicketEntity = new EntityInsertionAdapter<TicketEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tickets` (`id`,`title`,`dateModified`,`status`,`urgency`,`requesterId`,`requesterName`,`dateCreation`,`closeDate`,`solveDate`,`content`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TicketEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDateModified() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDateModified());
        }
        stmt.bindLong(4, value.getStatus());
        stmt.bindLong(5, value.getUrgency());
        stmt.bindLong(6, value.getRequesterId());
        if (value.getRequesterName() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRequesterName());
        }
        if (value.getDateCreation() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDateCreation());
        }
        if (value.getCloseDate() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getCloseDate());
        }
        if (value.getSolveDate() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSolveDate());
        }
        if (value.getContent() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getContent());
        }
      }
    };
    this.__preparedStmtOfDeleteAllTickets = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tickets";
        return _query;
      }
    };
  }

  @Override
  public Object insertTickets(final List<TicketEntity> tickets,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTicketEntity.insert(tickets);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertTicket(final TicketEntity ticket,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTicketEntity.insert(ticket);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllTickets(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTickets.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllTickets.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllTickets(final Continuation<? super List<TicketEntity>> continuation) {
    final String _sql = "SELECT * FROM tickets ORDER BY dateModified DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TicketEntity>>() {
      @Override
      public List<TicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "dateModified");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfUrgency = CursorUtil.getColumnIndexOrThrow(_cursor, "urgency");
          final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "requesterId");
          final int _cursorIndexOfRequesterName = CursorUtil.getColumnIndexOrThrow(_cursor, "requesterName");
          final int _cursorIndexOfDateCreation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreation");
          final int _cursorIndexOfCloseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "closeDate");
          final int _cursorIndexOfSolveDate = CursorUtil.getColumnIndexOrThrow(_cursor, "solveDate");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final List<TicketEntity> _result = new ArrayList<TicketEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TicketEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDateModified;
            if (_cursor.isNull(_cursorIndexOfDateModified)) {
              _tmpDateModified = null;
            } else {
              _tmpDateModified = _cursor.getString(_cursorIndexOfDateModified);
            }
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            final int _tmpUrgency;
            _tmpUrgency = _cursor.getInt(_cursorIndexOfUrgency);
            final int _tmpRequesterId;
            _tmpRequesterId = _cursor.getInt(_cursorIndexOfRequesterId);
            final String _tmpRequesterName;
            if (_cursor.isNull(_cursorIndexOfRequesterName)) {
              _tmpRequesterName = null;
            } else {
              _tmpRequesterName = _cursor.getString(_cursorIndexOfRequesterName);
            }
            final String _tmpDateCreation;
            if (_cursor.isNull(_cursorIndexOfDateCreation)) {
              _tmpDateCreation = null;
            } else {
              _tmpDateCreation = _cursor.getString(_cursorIndexOfDateCreation);
            }
            final String _tmpCloseDate;
            if (_cursor.isNull(_cursorIndexOfCloseDate)) {
              _tmpCloseDate = null;
            } else {
              _tmpCloseDate = _cursor.getString(_cursorIndexOfCloseDate);
            }
            final String _tmpSolveDate;
            if (_cursor.isNull(_cursorIndexOfSolveDate)) {
              _tmpSolveDate = null;
            } else {
              _tmpSolveDate = _cursor.getString(_cursorIndexOfSolveDate);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _item = new TicketEntity(_tmpId,_tmpTitle,_tmpDateModified,_tmpStatus,_tmpUrgency,_tmpRequesterId,_tmpRequesterName,_tmpDateCreation,_tmpCloseDate,_tmpSolveDate,_tmpContent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getTicketById(final int ticketId,
      final Continuation<? super TicketEntity> continuation) {
    final String _sql = "SELECT * FROM tickets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ticketId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TicketEntity>() {
      @Override
      public TicketEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "dateModified");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfUrgency = CursorUtil.getColumnIndexOrThrow(_cursor, "urgency");
          final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "requesterId");
          final int _cursorIndexOfRequesterName = CursorUtil.getColumnIndexOrThrow(_cursor, "requesterName");
          final int _cursorIndexOfDateCreation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreation");
          final int _cursorIndexOfCloseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "closeDate");
          final int _cursorIndexOfSolveDate = CursorUtil.getColumnIndexOrThrow(_cursor, "solveDate");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final TicketEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDateModified;
            if (_cursor.isNull(_cursorIndexOfDateModified)) {
              _tmpDateModified = null;
            } else {
              _tmpDateModified = _cursor.getString(_cursorIndexOfDateModified);
            }
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            final int _tmpUrgency;
            _tmpUrgency = _cursor.getInt(_cursorIndexOfUrgency);
            final int _tmpRequesterId;
            _tmpRequesterId = _cursor.getInt(_cursorIndexOfRequesterId);
            final String _tmpRequesterName;
            if (_cursor.isNull(_cursorIndexOfRequesterName)) {
              _tmpRequesterName = null;
            } else {
              _tmpRequesterName = _cursor.getString(_cursorIndexOfRequesterName);
            }
            final String _tmpDateCreation;
            if (_cursor.isNull(_cursorIndexOfDateCreation)) {
              _tmpDateCreation = null;
            } else {
              _tmpDateCreation = _cursor.getString(_cursorIndexOfDateCreation);
            }
            final String _tmpCloseDate;
            if (_cursor.isNull(_cursorIndexOfCloseDate)) {
              _tmpCloseDate = null;
            } else {
              _tmpCloseDate = _cursor.getString(_cursorIndexOfCloseDate);
            }
            final String _tmpSolveDate;
            if (_cursor.isNull(_cursorIndexOfSolveDate)) {
              _tmpSolveDate = null;
            } else {
              _tmpSolveDate = _cursor.getString(_cursorIndexOfSolveDate);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _result = new TicketEntity(_tmpId,_tmpTitle,_tmpDateModified,_tmpStatus,_tmpUrgency,_tmpRequesterId,_tmpRequesterName,_tmpDateCreation,_tmpCloseDate,_tmpSolveDate,_tmpContent);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
