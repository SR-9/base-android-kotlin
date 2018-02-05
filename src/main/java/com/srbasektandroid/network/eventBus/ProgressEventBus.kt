package com.srbasektandroid.network.eventBus

/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/14/17
 * ____________________________________
 */

data class ProgressEventBus (
	var downloaded : Long = 0,
    val totalSize : Long = 0
)
