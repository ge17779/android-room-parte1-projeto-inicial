package br.com.alura.agenda.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.model.Aluno;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AlunoDAO_Impl implements AlunoDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Aluno> __insertionAdapterOfAluno;

  private final ConversorCalendar __conversorCalendar = new ConversorCalendar();

  private final EntityDeletionOrUpdateAdapter<Aluno> __deletionAdapterOfAluno;

  private final EntityDeletionOrUpdateAdapter<Aluno> __updateAdapterOfAluno;

  public AlunoDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAluno = new EntityInsertionAdapter<Aluno>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Aluno` (`id`,`nome`,`telefone`,`email`,`momentoDeCadastro`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Aluno value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getTelefone() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTelefone());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        final Long _tmp;
        _tmp = __conversorCalendar.paraLong(value.getMomentoDeCadastro());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
      }
    };
    this.__deletionAdapterOfAluno = new EntityDeletionOrUpdateAdapter<Aluno>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Aluno` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Aluno value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAluno = new EntityDeletionOrUpdateAdapter<Aluno>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Aluno` SET `id` = ?,`nome` = ?,`telefone` = ?,`email` = ?,`momentoDeCadastro` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Aluno value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getTelefone() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTelefone());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        final Long _tmp;
        _tmp = __conversorCalendar.paraLong(value.getMomentoDeCadastro());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public void salva(final Aluno aluno) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAluno.insert(aluno);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void remove(final Aluno aluno) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAluno.handle(aluno);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void edita(final Aluno aluno) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAluno.handle(aluno);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Aluno> todos() {
    final String _sql = "SELECT * FROM aluno";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfTelefone = CursorUtil.getColumnIndexOrThrow(_cursor, "telefone");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfMomentoDeCadastro = CursorUtil.getColumnIndexOrThrow(_cursor, "momentoDeCadastro");
      final List<Aluno> _result = new ArrayList<Aluno>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Aluno _item;
        _item = new Aluno();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpNome;
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _tmpNome = null;
        } else {
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
        }
        _item.setNome(_tmpNome);
        final String _tmpTelefone;
        if (_cursor.isNull(_cursorIndexOfTelefone)) {
          _tmpTelefone = null;
        } else {
          _tmpTelefone = _cursor.getString(_cursorIndexOfTelefone);
        }
        _item.setTelefone(_tmpTelefone);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final Calendar _tmpMomentoDeCadastro;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfMomentoDeCadastro)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfMomentoDeCadastro);
        }
        _tmpMomentoDeCadastro = __conversorCalendar.paraCalendar(_tmp);
        _item.setMomentoDeCadastro(_tmpMomentoDeCadastro);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
