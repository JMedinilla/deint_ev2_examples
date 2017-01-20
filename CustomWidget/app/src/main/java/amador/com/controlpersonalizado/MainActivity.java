package amador.com.controlpersonalizado;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ColorMixed colorMixed;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorMixed = (ColorMixed)findViewById(R.id.mixed);
        textView = (TextView)findViewById(R.id.txv);
        textView.setText(String.valueOf(colorMixed.getColor()));
        colorMixed.setOnColorChangeListener(new ColorMixed.OnColorChangeListener() {
            @Override
            public void onColorChange(int color) {

                textView.setText(String.valueOf(color));
            }
        });




        }
}
