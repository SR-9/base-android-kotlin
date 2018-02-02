package general.model

import java.util.*

data class Media(
        var title : String? = "",
        var date : Date ? = null,
        var url : String? = null,
        var isChoose : Boolean = false
)