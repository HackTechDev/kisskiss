package cookbook.android.haptic.activity;

import android.app.Activity;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import cookbook.android.haptic.R;


public class MainActivity extends Activity {
	ImageButton imageButton;
	
	
    @Override
    protected void onStart() {
    	super.onStart();
    	setContentView( R.layout.main );
	
        initializeWidgets();
    }
  
    private void initializeWidgets() {
		SeekBar seekBar = (SeekBar) findViewById( R.id.vibrateDuration );
		seekBar.setOnSeekBarChangeListener( new DurationListener() );
				
		ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);
		imageButton.setOnTouchListener( new CustomHapticListener() );
		
    }
    
  
    private class DurationListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged( SeekBar seeker, int progress, boolean fromUser ) {		
			duration = progress;
		}

		@Override
		public void onStartTrackingTouch( SeekBar seeker ) { }

		@Override
		public void onStopTrackingTouch ( SeekBar seeker ) { }
    }
    
    private class CustomHapticListener implements OnTouchListener {

		@Override
		public boolean onTouch( View v, MotionEvent event ) {
			if( event.getAction() == MotionEvent.ACTION_DOWN ){
				Vibrator vibe = ( Vibrator ) getSystemService( VIBRATOR_SERVICE );
				vibe.vibrate( duration );
			}
			return true;
		}    	
    }
    
    private class HapticTouchListener implements OnTouchListener {

    	private final int feedbackType;
    	
    	public HapticTouchListener( int type ) { feedbackType = type; }
    	
    	public int feedbackType() { return feedbackType; }
    	
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if( event.getAction() == MotionEvent.ACTION_DOWN ){
				v.performHapticFeedback( feedbackType(), HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING );
			}
			return true;
		}
    }
    
	private int      duration;
}