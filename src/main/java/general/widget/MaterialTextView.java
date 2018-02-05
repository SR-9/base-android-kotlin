package general.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.baseappication.R;

/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 2/5/18
 * ____________________________________
 */

public class MaterialTextView extends AppCompatTextView {

	private float _cornerRadius  = -1f;
	private int _stroke  = 1;
	private int _strokeColor  = Color.GRAY;


	public MaterialTextView(Context context) {
		super(context);
		setupFirstBackground(context, null, 0);
	}

	public MaterialTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupFirstBackground(context, attrs, 0);
	}

	public MaterialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setupFirstBackground(context, attrs, defStyleAttr);
	}

	private void setupFirstBackground(Context context, AttributeSet attrs, int defStyleAttr) {
		if(attrs != null) {
			TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialView, defStyleAttr, 0);
			_cornerRadius = typeArray.getDimensionPixelSize(R.styleable.MaterialView_cornerRadius, -1);
			_stroke = typeArray.getDimensionPixelSize(R.styleable.MaterialView_stroke, 1);
			_strokeColor = typeArray.getColor(R.styleable.MaterialView_strokeColor, Color.GRAY);
			typeArray.recycle();
		}

		setBackground(null);
	}

	@Override
	public void setBackground(Drawable background) {
		if(background instanceof GradientDrawable) {
			super.setBackground(background);
		} else if(background instanceof ColorDrawable) {
			GradientDrawable bg = new GradientDrawable();
			if(_cornerRadius >= 0) {
				bg.setCornerRadius(_cornerRadius);
			}
			bg.setColor(((ColorDrawable) background).getColor());
			bg.setStroke(_stroke, _strokeColor);
			super.setBackground(bg);
		} else {
			GradientDrawable bg = new GradientDrawable();
			if(_cornerRadius >= 0) {
				bg.setCornerRadius(_cornerRadius);
			}
			bg.setStroke(_stroke, _strokeColor);
			super.setBackground(bg);
		}
	}
}
