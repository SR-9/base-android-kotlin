package general.sounds

import android.media.AudioAttributes
import android.media.SoundPool
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import general.mvp.BaseActivity
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable


/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/21/17
 * ____________________________________
 */

class SoundManager(private val context : BaseActivity, sounds : List<Int>) {
	private var soundPool : SoundPool? = null

	init {
		val attributes = AudioAttributes.Builder()
			.setUsage(AudioAttributes.USAGE_GAME)
			.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
			.build()

		soundPool = SoundPool.Builder()
			.setAudioAttributes(attributes)
			.build()
			.apply {
				setOnLoadCompleteListener { _, _, _ ->
				}

				sounds.toObservable()
					.bindUntilEvent(context, ActivityEvent.DESTROY)
					.subscribeBy(
						onError = Throwable::printStackTrace,
						onNext = {
							load(context, it, 1)
						}
					)
			}

	}

	fun play(id : Int) {
		soundPool?.play(id, 1f, 1f, 0, 0, 1.0f)
	}
}