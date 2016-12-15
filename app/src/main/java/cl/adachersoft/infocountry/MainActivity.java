package cl.adachersoft.infocountry;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.adachersoft.infocountry.models.Indicator;
import retrofit2.http.PUT;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final EditText editText = (EditText) findViewById(R.id.countryEt);

        Button button = (Button) findViewById(R.id.buscarBt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String country = editText.getText().toString();
                new GetIndicator().execute(country);

            }
        });

    }

    private class GetIndicator extends BackgroundData {
        @Override
        protected void onPostExecute(Indicator indicator) {
            if (indicator != null) {
               

                String name = String.valueOf(indicator.getName());
                TextView nameTv = (TextView) findViewById(R.id.nameTv);
                nameTv.setText("  PAIS    :  " + name);


                String capital = String.valueOf(indicator.getCapital());
                TextView capitalTv = (TextView) findViewById(R.id.capitalTv);
                capitalTv.setText("  CAPITAL    :  " + capital);


                String region = String.valueOf(indicator.getRegion());
                TextView regionTv = (TextView) findViewById(R.id.regionTv);
                regionTv.setText("  REGION    :  " + region);


                String languajes = "";
                for (String languaje : indicator.getLanguages()) {
                    languajes = languajes + languaje + " ";

                    TextView languajesTV = (TextView) findViewById(R.id.languagesTv);
                    languajesTV.setText("  Lenguajes  :  " + languajes);
                }


                String population = String.valueOf(indicator.getPopulation());
                TextView populationtv = (TextView) findViewById(R.id.populationTV);
                populationtv.setText(" Poblacion   :  " + population);


                Toast.makeText(MainActivity.this, indicator.getName(), Toast.LENGTH_SHORT).show();


            } else {

                Toast.makeText(MainActivity.this, "Pais No encontrado", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
