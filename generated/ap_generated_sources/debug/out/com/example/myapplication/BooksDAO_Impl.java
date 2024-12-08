package com.example.myapplication;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BooksDAO_Impl implements BooksDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Books> __insertionAdapterOfBooks;

  private final EntityDeletionOrUpdateAdapter<Books> __deletionAdapterOfBooks;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllBooks;

  public BooksDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBooks = new EntityInsertionAdapter<Books>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Books` (`id`,`title`,`author`,`status`,`borrow_date`,`return_date`,`image`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Books entity) {
        statement.bindLong(1, entity.id);
        if (entity.title == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.title);
        }
        if (entity.author == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.author);
        }
        if (entity.status == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.status);
        }
        if (entity.borrowDate == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.borrowDate);
        }
        if (entity.returnDate == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.returnDate);
        }
        statement.bindLong(7, entity.image);
      }
    };
    this.__deletionAdapterOfBooks = new EntityDeletionOrUpdateAdapter<Books>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Books` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Books entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__preparedStmtOfDeleteAllBooks = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Books";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final Books... books) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBooks.insert(books);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Books books) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBooks.handle(books);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllBooks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllBooks.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllBooks.release(_stmt);
    }
  }

  @Override
  public List<Books> getAllBooks() {
    final String _sql = "SELECT * FROM Books";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfBorrowDate = CursorUtil.getColumnIndexOrThrow(_cursor, "borrow_date");
      final int _cursorIndexOfReturnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "return_date");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final List<Books> _result = new ArrayList<Books>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Books _item;
        _item = new Books();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _item.title = null;
        } else {
          _item.title = _cursor.getString(_cursorIndexOfTitle);
        }
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _item.author = null;
        } else {
          _item.author = _cursor.getString(_cursorIndexOfAuthor);
        }
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _item.status = null;
        } else {
          _item.status = _cursor.getString(_cursorIndexOfStatus);
        }
        if (_cursor.isNull(_cursorIndexOfBorrowDate)) {
          _item.borrowDate = null;
        } else {
          _item.borrowDate = _cursor.getString(_cursorIndexOfBorrowDate);
        }
        if (_cursor.isNull(_cursorIndexOfReturnDate)) {
          _item.returnDate = null;
        } else {
          _item.returnDate = _cursor.getString(_cursorIndexOfReturnDate);
        }
        _item.image = _cursor.getInt(_cursorIndexOfImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
