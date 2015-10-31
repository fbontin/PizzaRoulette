package com.example.filip.pizzaroulette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String[] PIZZERIAS = {"Kryddan", "Gunnesbo"};

    private static final String[] PIZZAS = {"Vesuvio, Pescatore, Calzone (inbakad), Hawaii, Capricciosa, Tomaso, Bolognese, Marinara, Milano, Pepparoni," +
            " Salami, Mamma mia, Indy, Princess, Barbone, Capris, Reale, Ciao Ciao (familjepizza), Kycklingpizza, Salami special, Calzone special (inbakad), " +
            "Calzone kebab (inbakad), Azteka, La maffia, Quattro stagioni, Charlies special, Siciliana, Cosa nostra, Kebabpizza, Bacon special, Acapulco, Legend, Mexicana, Dallas, Gorgonzola, " +
            "Kebabpizza special, Husets special, Toronto special, Kryddans special, Favoriten, Onlinepizza special, Lund special",
            "Vesuvio, Pescatore, Calzone (inbakad), Hawaii, Capricciosa, Tomaso, Cacciatore, Bolognese, Afrikano, Bombay, Napoli, Marinara, Rimini, Milano, Azteka, Greta, Gorgonzola," +
                    " Kebab, Kyckling, Kung calzone (inbakad), Spinaci, Bon appétit, Mozart, Salami special, Quattro, Mexikana, Siciliana, Chicago, Kebab special, Gunnesbo special, Gunnesbo special 2, Gyrospizza, " +
                    "Gyrospizza special, Mamma mia, Kanon, Lunda special, Zlatans special 1, Zlatans special 2"};

    private static final String[] VEG_PIZZAS = {"Margherita, Funghi, Africana, Smålandsnation, Fyra ostar, Vegetariana, Grekisk special",
            "Margherita, Fungi, Vegetale, Frotti, Prinsessan, Viking, Eldorado"};

    private static final String[] TOPPINGS = {"Bacon, Kebabkött (nötkött), Kyckling, Köttfärs, Köttfärssås, Musslor, Oxfilé, Pepperonikorv, Räkor, Salami, Sardeller, Skinka, Tonfisk",
            "Bacon, Kebabkött (fläskkarré), Kebabkött (nötkött), Kyckling, Köttfärs, Musslor, Nötfärs, Oxfilé, Parmaskinka, Räkor, Salami, Sardeller, Skinka, Skinka (lufttorkad), Tigerräkor, Tonfisk"};

    private static final String[] VEG_TOPPINGS = {"Ananas, Banan, Bearnaisesås, Bearnaisesås I burk, Cayennepeppar, Champinjoner (färska), Citron, Curry, Falafel, Feferoni, Fårost, Gorgonzolaost, Goudaost, " +
            "Gurka, Hamburgerdressing, Hamburgerdressing I burk, Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabsås blandad, Kebabsås blandad I burk, Kebabsås mild, Kebabsås mild I burk, " +
            "Kebabsås stark, Kebabsås stark I burk, Kronärtskocka, Lök, Mozzarellaost, Oliver, Ost, Paprika (färsk), Pommes frites, Sparris, Svartpeppar, Tacokryddmix, Tomater (färska), Tomatsås, " +
            "Tzatzikisås, Tzatzikisås I burk, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg",
            "Ananas, Aubergine, Banan, Bearnaisesås, Bearnaisesås I burk, Champinjoner (färska), Curry, Feferoni  , Fetaost, Gorgonzolaost, Gurka, Hamburgerdressing, Hamburgerdressing I burk, " +
                    "Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabsås blandad, Kebabsås blandad I burk, Kebabsås mild, Kebabsås mild I burk, Kebabsås stark, Kebabsås stark I burk, Kronärtskocka, " +
                    "Körsbärstomater, Lök, Majs, Mozzarellaost, Nachochips, Oliver, Ost, Paprika, Paprika (färsk), Parmesanost, Pesto, Purjolök, Ruccolasallad, Rödlök, Spenat, Svartpeppar, Tacokryddmix, " +
                    "Tomater (färska), Tomater (soltorkade), Tomatsås, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg"};


    private int nbrOfToppings = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String nbr = editText.getText().toString();
        if (nbr.equals("")) {
            Toast.makeText(this, "Please choose the number of toppings you want!", Toast.LENGTH_LONG).show();
            return;
        }
        nbrOfToppings = Integer.parseInt(nbr);
        if (nbrOfToppings > 5) {
            Toast.makeText(this, "Too much toppings, you will get fat.", Toast.LENGTH_LONG).show();
            return;
        }

        boolean vegetarian = ((CheckBox) findViewById(R.id.vegetarian)).isChecked();
        List[] result = getPizzasAndToppings(vegetarian);
        String chosenPizza = randomize(result[0], 1);
        String chosenToppings = randomize(result[1], nbrOfToppings);

        TextView pizzatv = (TextView) findViewById(R.id.pizzaTextView);
        pizzatv.setText("Pizza: " + chosenPizza);
        TextView toppingstv = (TextView) findViewById(R.id.toppingsTextView);
        toppingstv.setText("Toppings: " + chosenToppings);
    }

    private List[] getPizzasAndToppings(boolean vegetarian) {
        Spinner spinner = (Spinner) findViewById(R.id.pizzerias);
        String pizzeria = spinner.getSelectedItem().toString();
        List pizzas = null, toppings = null;

        for (int i = 0; i < PIZZERIAS.length; i++) {
            if (pizzeria.equals(PIZZERIAS[i])) {
                if (vegetarian) {
                    pizzas = parseText(VEG_PIZZAS[i]);
                    toppings = parseText(VEG_TOPPINGS[i]);
                } else {
                    pizzas = parseText(PIZZAS[i] + ", " + VEG_PIZZAS[i]);
                    toppings = parseText(TOPPINGS[i] + ", " + VEG_PIZZAS[i]);
                }
            }
        }
        List[] result = {pizzas, toppings};
        return result;
    }

    private List parseText(String text) {
        List list = Arrays.asList(text.split(", "));
        for (int i = 0; i < 5; i++) {
            Log.d("a", i + ": " + list.get(i));
        }
        return list;
    }

    private String randomize(List list, int n) {
        String result = "";
        Random random = new Random();
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int index = random.nextInt(list.size());

            if (indexes.contains(index)) {
                i--;
            } else {
                indexes.add(index);
                result += list.get(index);
                if (i < n - 1) {
                    result += ", ";
                }
            }
        }
        return result;
    }
}
