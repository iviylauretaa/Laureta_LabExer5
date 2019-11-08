package com.example.oct25;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] names, countries ,industries, ceos, des;
    ListView lstcompany;

    int[] logo = {R.drawable.icbc, R.drawable.jpmorgan, R.drawable.china, R.drawable.agricultural, R.drawable.america, R.drawable.apple, R.drawable.pingan, R.drawable.bankofchina, R.drawable.shell, R.drawable.wellsfargo, R.drawable.exxon, R.drawable.atnt, R.drawable.samsung, R.drawable.citi};

    ArrayList<AndroidVersion> details = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TOP GLOBAL COMPANIES");

        names = getResources().getStringArray(R.array.company);
        countries = getResources().getStringArray(R.array.country);
        industries = getResources().getStringArray(R.array.industry);
        ceos = getResources().getStringArray(R.array.ceo);
        des = getResources().getStringArray(R.array.description);

        for(int i = 0; i < names.length; i++){
            details.add(new AndroidVersion(names[i], countries[i], industries[i], ceos[i], logo[i]));
        }

        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item, details);
        lstcompany = findViewById(R.id.cCompany);
        lstcompany.setAdapter(adapter);
        lstcompany.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "AndroidVersion.txt");
        File read = new File(folder, "show.txt");
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            final FileOutputStream show = new FileOutputStream(read);
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            String choice = details.get(i).getName() + "\n"
                    + details.get(i).getCountry() + "\n"
                    + details.get(i).getIndustry() + "\n"
                    + details.get(i).getCeo() + "\n";
            String sChoice = details.get(i).getName() + "\n" + details.get(i).getCountry() + "\n" + details.get(i).getIndustry()
                    + "\n" + details.get(i).getCeo();
            show.write(sChoice.getBytes());
            fos.write(choice.getBytes());
            dialog.setTitle(details.get(i).getName());
            dialog.setIcon(details.get(i).getLogo());

            dialog.setMessage(des[i]);

            dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    try {
                        FileInputStream fin;
                        fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/show.txt"));
                        int i;
                        String str = "";
                        while ((i = fin.read()) != -1) {
                            str += Character.toString((char) i);
                        }
                        fin.close();
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialog.create().show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found.", Toast.LENGTH_LONG ).show();
        } catch (IOException e) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();makeText(this, "Cannot Write...", Toast.LENGTH_LONG ).show();
        }

    }
}
