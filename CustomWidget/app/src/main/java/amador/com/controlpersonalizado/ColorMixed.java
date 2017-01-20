package amador.com.controlpersonalizado;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Dimension;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by usuario on 19/01/17.
 */

public class ColorMixed extends RelativeLayout {

    private Context context;
    private AttributeSet attributeSet;
    private SeekBar r, g, b;
    private View swatch;
    private int  viewBackColor = 0;
    private OnColorChangeListener listener;
    private SeekBar.OnSeekBarChangeListener changeStateListener;


    public void setOnColorChangeListener(OnColorChangeListener listener){

        this.listener = listener;
    }

    public ColorMixed(Context context) {
        super(context);
    }

    public ColorMixed(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1.Se tiene que inflar la vista del componente
        ((Activity)context).getLayoutInflater().inflate(R.layout.mixer,this,true);

        initSeekBarListener();

        //2. Recoger las referencias
        r = (SeekBar)findViewById(R.id.red);

        r.setMax(0xFF);
        g = (SeekBar)findViewById(R.id.green);
        g.setMax(0xFF);

        b = (SeekBar)findViewById(R.id.blue);
        b.setMax(0xFF);
        b.setOnSeekBarChangeListener(changeStateListener);
        r.setOnSeekBarChangeListener(changeStateListener);
        g.setOnSeekBarChangeListener(changeStateListener);
        swatch = findViewById(R.id.swatch);
        if(attrs != null){

            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.ColorMixed);
            int color = typedArray.getColor(R.styleable.ColorMixed_init_color, Color.BLUE);
            setColorSeekBar(color);
            typedArray.recycle();
        }




    }

    private void initSeekBarListener() {

        changeStateListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean ba) {

                //1. Se obtiene el valor de los tres Seek sea cual sea el que lanze el evento
                viewBackColor = Color.rgb(r.getProgress(),g.getProgress(),b.getProgress());
                //2. Modificamos el componente swath
                swatch.setBackgroundColor(viewBackColor);
                //3. Pasamos el evento a los listener
                throwChangeColorListener();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    private void throwChangeColorListener(){

        if(listener != null){
            listener.onColorChange(viewBackColor);
        }
    }

    private void setColorSeekBar(int color) {

        r.setProgress(Color.red(color));
        g.setProgress(Color.green(color));
        b.setProgress(Color.blue(color));
        swatch.setBackgroundColor(color);
    }

    public int getColor() {
        return (Color.rgb(r.getProgress(),g.getProgress(),b.getProgress()));
    }

    public interface OnColorChangeListener {

        void onColorChange(int color);
    }
}
