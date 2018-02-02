package general.database


import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.reactivex.Observable


abstract class SqlHelper @JvmOverloads internal constructor(context : Context, dbName : String = SqlHelper.DATABASE_NAME)
    : SQLiteOpenHelper(context, dbName, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "cplayer.db"
        val DB_MEDIA_TABLE = "media"
        val DB_CREATE_MEDIA_TABLE = "create table if not exists  " + DB_MEDIA_TABLE +
                "( id integer primary key autoincrement, title text, link text, " +
                "thumbnail text, mediaLink text, mdate text, mtype integer, mfrom integer);"
    }

    override fun onCreate(db : SQLiteDatabase) {
        db.execSQL(DB_CREATE_MEDIA_TABLE)
    }

    override fun onUpgrade(db : SQLiteDatabase, oldVersion : Int, newVersion : Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_MEDIA_TABLE)
        onCreate(db)
    }

    fun makeObservableCursor(cursor: Cursor): Observable<Cursor> {
        return Observable.create<Cursor> { e ->
            try {
                while (cursor.moveToNext() && !e.isDisposed) {
                    //Todo: must convert Cusor to Object.
                    e.onNext(cursor)
                }
            } catch (exception: Exception) {
                e.onError(exception)
            } finally {
                cursor.close()
            }
            if (!e.isDisposed) {
                e.onComplete()
            }
        };
    }
}


