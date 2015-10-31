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

    private static final String[] PIZZERIAS = {"Kryddan", "Gunnesbo", "Bellini"};

    private static final String[] PIZZAS = {"Vesuvio, Pescatore, Calzone (inbakad), Hawaii, Capricciosa, Tomaso, Bolognese, Marinara, Milano, Pepparoni," +
            " Salami, Mamma mia, Indy, Princess, Barbone, Capris, Reale, Ciao Ciao (familjepizza), Kycklingpizza, Salami special, Calzone special (inbakad), " +
            "Calzone kebab (inbakad), Azteka, La maffia, Quattro stagioni, Charlies special, Siciliana, Cosa nostra, Kebabpizza, Bacon special, Acapulco, Legend, Mexicana, Dallas, Gorgonzola, " +
            "Kebabpizza special, Husets special, Toronto special, Kryddans special, Favoriten, Onlinepizza special, Lund special",
            "Vesuvio, Pescatore, Calzone (inbakad), Hawaii, Capricciosa, Tomaso, Cacciatore, Bolognese, Afrikano, Bombay, Napoli, Marinara, Rimini, Milano, Azteka, Greta, Gorgonzola," +
                    " Kebab, Kyckling, Kung calzone (inbakad), Spinaci, Bon appétit, Mozart, Salami special, Quattro, Mexikana, Siciliana, Chicago, Kebab special, Gunnesbo special, Gunnesbo special 2, Gyrospizza, " +
                    "Gyrospizza special, Mamma mia, Kanon, Lunda special, Zlatans special 1, Zlatans special 2",
            "Vesuvio, Calzone (inbakad), Venezia, Capricciosa, Hawaii, Neptun (halvinbakad), Tomaso, Salami, Bolognese, Italia, Milano, Marinara, Tropicana, Rimini, Toscana, Kung Calzone (inbakad), " +
                    "Prinsessa, Calzone special (inbakad), Sigges special, Cacciatore, Siciliana, Mama mia, Roma, Bacon, Barbone, Bombay, Ciao ciao (inbakad), Florentina (inbakad), Skåne special, " +
                    "Mexicana, Salami special, Husets special (inbakad), Lasse special, La maffia, Azteka, Kebabpizza, Indian special (pikant), Quattro, Favoriten, Bellini special, Legend, Gorgonzola, " +
                    "Deliziosa, Reale, Napoli, Atlantic, Pepperonipizza, Havets special, Dallas, Evin special, Bilal special, Delphi, Lunda special, Kebabpizza special, Olympia, Plankapizza oxfilé, " +
                    "Plankapizza kyckling, Plankapizza kebab, Super de lux (inbakad), Zlatan special, Lady, Bacon mozzarellapizza, Kärlekshandtag, Miss Italy, Americana (small), Elvis (small), " +
                    "Twist (small), Dallas 2 (small)"};

    private static final String[] VEG_PIZZAS = {"Margherita, Funghi, Africana, Smålandsnation, Fyra ostar, Vegetariana, Grekisk special",
            "Margherita, Fungi, Vegetale, Frotti, Prinsessan, Viking, Eldorado",
            "Margherita, Funghi, Afrikana, Green pizza (pikant), El pesto, Vegetariana, Jonas special, Grek special, "};

    private static final String[] TOPPINGS = {"Bacon, Kebabkött (nötkött), Kyckling, Köttfärs, Köttfärssås, Musslor, Oxfilé, Pepperonikorv, Räkor, Salami, Sardeller, Skinka, Tonfisk",
            "Bacon, Kebabkött (fläskkarré), Kebabkött (nötkött), Kyckling, Köttfärs, Musslor, Nötfärs, Oxfilé, Parmaskinka, Räkor, Salami, Sardeller, Skinka, Skinka (lufttorkad), Tigerräkor, Tonfisk",
            "Bacon, Crabfish, Fläskfilé, Kebabkött (nötkött), Kyckling, Köttfärs, Musslor, Oxfilé, Pepperonikorv, Räkor, Salami, Sardeller, Skinka, Tonfisk"};

    private static final String[] VEG_TOPPINGS = {"Ananas, Banan, Bearnaisesås, Bearnaisesås I burk, Cayennepeppar, Champinjoner (färska), Citron, Curry, Falafel, Feferoni, Fårost, Gorgonzolaost, Goudaost, " +
            "Gurka, Hamburgerdressing, Hamburgerdressing I burk, Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabsås blandad, Kebabsås blandad I burk, Kebabsås mild, Kebabsås mild I burk, " +
            "Kebabsås stark, Kebabsås stark I burk, Kronärtskocka, Lök, Mozzarellaost, Oliver, Ost, Paprika (färsk), Pommes frites, Sparris, Svartpeppar, Tacokryddmix, Tomater (färska), Tomatsås, " +
            "Tzatzikisås, Tzatzikisås I burk, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg",
            "Ananas, Aubergine, Banan, Bearnaisesås, Bearnaisesås I burk, Champinjoner (färska), Curry, Feferoni  , Fetaost, Gorgonzolaost, Gurka, Hamburgerdressing, Hamburgerdressing I burk, " +
                    "Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabsås blandad, Kebabsås blandad I burk, Kebabsås mild, Kebabsås mild I burk, Kebabsås stark, Kebabsås stark I burk, Kronärtskocka, " +
                    "Körsbärstomater, Lök, Majs, Mozzarellaost, Nachochips, Oliver, Ost, Paprika, Paprika (färsk), Parmesanost, Pesto, Purjolök, Ruccolasallad, Rödlök, Spenat, Svartpeppar, Tacokryddmix, " +
                    "Tomater (färska), Tomater (soltorkade), Tomatsås, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg",
            "Ananas, Banan, Bearnaisesås, Bearnaisesås I burk, Cayennepeppar, Champinjoner (färska), Curry, Curry (grön), Curry (gul), Feferoni  , Fetaost, Gorgonzolaost, Gorgonzolasås, " +
                    "Gorgonzolasås I burk, Gurka, Hamburgerdressing, Hamburgerdressing I burk, Isbergssallad, Jalapeños, Jordnötter, Kapris, Kebabsås mild, Kebabsås mild I burk, Kebabsås stark, " +
                    "Kebabsås stark I burk, Kronärtskocka, Körsbärstomater, Lök, Majs, Massamancurry, Mozzarellaost, Oliver, Ost, Paprika (färsk), Persilja, Persiljesmör, Persiljesmör I burk, " +
                    "Pesto, Pesto I burk, Pommes frites, Purjolök, Rhode Islandsås, Rhode Islandsås I burk, Ruccolasallad, Rödlök, Sparris, Svartpeppar, Tacokryddmix, Tomater (färska), " +
                    "Tomater (soltorkade), Tomatsås, Tzatzikisås, Tzatzikisås I burk, Vitlök, Vitlök (färsk), Vitlökssås, Vitlökssås I burk, Ägg"};


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
        int nbrOfToppings = Integer.parseInt(nbr);
        if (nbrOfToppings > 10) {
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
                    toppings = parseText(TOPPINGS[i] + ", " + VEG_TOPPINGS[i]);
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
