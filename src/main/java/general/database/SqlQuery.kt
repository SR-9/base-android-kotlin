package general.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import general.database.SqlHelper
import io.reactivex.Observable

abstract class SqlQuery(context : Context) : SqlHelper(context) {

    open val QUERY_ALL_ROW_MEDIA_TABLE = "select * from media"

    open val COUNT_ALL_ROW_MEDIA_TABLE = "select count(*) from media"

    open fun insertToMediaTable(media : Any) : Boolean {
        val cv : ContentValues = ContentValues()
        return writableDatabase.insert(DB_MEDIA_TABLE, null, cv) > 0
    }

    open fun updateToMediaTable(media : Any) : Boolean {
        val cv : ContentValues = ContentValues()
        val m = ""
        return writableDatabase.update(DB_MEDIA_TABLE, cv, "mediaLink='$m'", null) > 0
    }

    open fun updateOrInsertToMediaTable(media : Any) : Int {
        var rs : Int = 0
        if (updateToMediaTable(media)) {
            rs = 1
        } else if (insertToMediaTable(media)) {
            rs = 2
        }
        return rs
    }

    open fun allRowMediaTableCount() : Long {
        return readableDatabase.compileStatement(COUNT_ALL_ROW_MEDIA_TABLE).simpleQueryForLong()
    }

    open fun selectAllRowMediaTable() : Cursor {
        return readableDatabase.rawQuery(QUERY_ALL_ROW_MEDIA_TABLE, null)
    }

    open fun getRxAllRowMediaTable() : Observable<Cursor> {
        return makeObservableCursor(selectAllRowMediaTable())
    }

}

