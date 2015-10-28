package com.example.filip.pizzaroulette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String KRYDDAN_PIZZAS = "Margherita, Vesuvio, Funghi, Pescatore, Calzone (inbakad), Hawaii, Capricciosa, Tomaso, Bolognese, Marinara, Milano, Pepparoni, Salami, Mamma mia, Africana, Indy, Princess, Barbone, Capris, Smålandsnation, Reale, Ciao Ciao (familjepizza), Kycklingpizza, Salami special, Fyra ostar, Calzone special (inbakad), Calzone kebab (inbakad), Azteka, La maffia, Quattro stagioni, Charlies special, Siciliana, Vegetariana, Cosa nostra, Kebabpizza, Bacon special, Acapulco, Legend, Mexicana, Dallas, Gorgonzola, Kebabpizza special, Husets special, Toronto special, Kryddans special, Grekisk special, Favoriten, Onlinepizza special, Lund special";
    private static final String KRYDDAN_TOPPINGS = "Ananas, Bacon, Banan, Bearnaisesås, Bearnaisesås I burk, Cayennepeppar, Champinjoner (färska), Citron, Curry, Falafel, Feferoni, Fårost, Gorgonzolaost, Goudaost, Gurka, Hamburgerdressing, Hamburgerdressing I burk, Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabkött (nötkött), Kebabsås blandad, Kebabsås blandad I burk, Kebabsås mild, Kebabsås mild I burk, Kebabsås stark, Kebabsås stark I burk, Kronärtskocka, Kyckling, Köttfärs, Köttfärssås, Lök, Mozzarellaost, Musslor, Oliver, Ost, Oxfilé, Paprika (färsk), Pepperonikorv, Pommes frites, Räkor, Salami, Sardeller, Skinka, Sparris, Svartpeppar, Tacokryddmix, Tomater (färska), Tomatsås, Tonfisk, Tzatzikisås, Tzatzikisås I burk, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg";

    private List pizzas;
    private List toppings;
    private int nbrOfToppings = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzas = parseText(KRYDDAN_PIZZAS);
        toppings = parseText(KRYDDAN_TOPPINGS);

    }


    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String nbr = editText.getText().toString();
        if(nbr.equals("")){
            Toast.makeText(this, "Don't leave it empty you STUPID HORSECUNT!", Toast.LENGTH_LONG).show();
            return;
        }
        nbrOfToppings = Integer.parseInt(nbr);
        if(nbrOfToppings > 5) {
            Toast.makeText(this, "Too much toppings, you will get fat.", Toast.LENGTH_LONG).show();
            return;
        }
        String chosenPizza = randomize(pizzas, 1);
        String chosenToppings = randomize(toppings, nbrOfToppings);

        TextView pizzatv = (TextView) findViewById(R.id.pizzaTextView);
        pizzatv.setText("Pizza: " + chosenPizza);
        TextView toppingstv = (TextView) findViewById(R.id.toppingsTextView);
        toppingstv.setText("Toppings: " + chosenToppings);

    }

    private List parseText(String text) {
        //ArrayList<String> pizzas = (ArrayList) Arrays.asList(UNPARSED_PIZZAS.split("\\s*,\\s*"));
        List list = Arrays.asList(text.split(", "));
        for(int i = 0; i < 5; i++){
            Log.d("a",i + ": " + list.get(i));
        }
        return list;
    }

    private String randomize(List list, int n){
        String result = "";
        Random random = new Random();

        for(int i = 0; i < n; i++){
            int index = random.nextInt(list.size());
            result += list.get(index);
            if(i < n - 1){
                result += ", ";
            }
        }
        return result;
    }
}
