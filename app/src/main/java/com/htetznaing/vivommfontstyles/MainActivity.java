package com.htetznaing.vivommfontstyles;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.snatik.storage.Storage;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String fontPath;
    String keyPaht;
    Storage storage;
    Fucker fucker;
    ActivityManager am;
    List<ActivityManager.RunningAppProcessInfo> processes;
    String tagu[] = {"Tagu.itz", "4_key_1516946681000Tagu.itz"};
    String kason[] = {"Kason.itz", "4_key_1516946711000Kason.itz"};
    String nayon[] = {"Nayon.itz", "4_key_1516946738000Nayon.itz"};
    String warso[] = {"Warso.itz", "4_key_1516946765000Warso.itz"};
    String wg[] = {"Wargaung.itz", "4_key_1516946800000Wargaung.itz"};
    String ttl[] = {"Tawthalin.itz", "4_key_1516946968000Tawthalin.itz"};
    String tdg[] = {"Thadingyut.itz", "4_key_1516947017000Thadingyut.itz"};
    String tsm[] = {"Tansaungmone.itz", "4_key_1516947083000Tansaungmone.itz"};
    String nattaw[] = {"Nattaw.itz", "4_key_1516947158000Nattaw.itz"};
    String pyatho[] = {"Pyatho.itz", "4_key_1516947125000Pyatho.itz"};
    String tbd[] = {"Tabodwe.itz", "4_key_1516947191000Tabodwe.itz"};
    String tb[] = {"Tabaung.itz", "4_key_1516947220000Tabaung.itz"};
    String titles[] = {"Tagu", "Kason", "Nayon", "Warso", "Wargaung", "Tawthalin", "Thadingyut", "Tansaungmone", "Nattaw", "Pyatho", "Tabodwe", "Tabaung"};

    ArrayList<Integer> images = new ArrayList<>();
    Adapter adapter;
    ListView listView;
    AdRequest adRequest;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fontPath = Environment.getExternalStorageDirectory() + "/.dwd/c/o/m/b/b/k/t/h/e/m/e/F/";
        keyPaht = Environment.getExternalStorageDirectory() + "/下载/i主题/.Font/cache/";
        storage = new Storage(this);
        createDir();
        fucker = new Fucker();
        am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        images.add(R.drawable.tagu);
        images.add(R.drawable.kason);
        images.add(R.drawable.nayon);
        images.add(R.drawable.warso);
        images.add(R.drawable.wg);
        images.add(R.drawable.ttl);
        images.add(R.drawable.tdg);
        images.add(R.drawable.tsm);
        images.add(R.drawable.nattaw);
        images.add(R.drawable.pyatho);
        images.add(R.drawable.tabodwe);
        images.add(R.drawable.tb);

        adapter = new Adapter(this, images);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Error.class);
                intent.putExtra("title", titles[i]);
                switch (i) {
                    case 0:
                        intent.putExtra("font", tagu[0]);
                        intent.putExtra("key", tagu[1]);
                        break;
                    case 1:
                        intent.putExtra("font", kason[0]);
                        intent.putExtra("key", kason[1]);
                        break;
                    case 2:
                        intent.putExtra("font", nayon[0]);
                        intent.putExtra("key", nayon[1]);
                        break;
                    case 3:
                        intent.putExtra("font", warso[0]);
                        intent.putExtra("key", warso[1]);
                        break;
                    case 4:
                        intent.putExtra("font", wg[0]);
                        intent.putExtra("key", wg[1]);
                        break;
                    case 5:
                        intent.putExtra("font", ttl[0]);
                        intent.putExtra("key", ttl[1]);
                        break;
                    case 6:
                        intent.putExtra("font", tdg[0]);
                        intent.putExtra("key", tdg[1]);
                        break;
                    case 7:
                        intent.putExtra("font", tsm[0]);
                        intent.putExtra("key", tsm[1]);
                        break;
                    case 8:
                        intent.putExtra("font", nattaw[0]);
                        intent.putExtra("key", nattaw[1]);
                        break;
                    case 9:
                        intent.putExtra("font", pyatho[0]);
                        intent.putExtra("key", pyatho[1]);
                        break;
                    case 10:
                        intent.putExtra("font", tbd[0]);
                        intent.putExtra("key", tbd[1]);
                        break;
                    case 11:
                        intent.putExtra("font", tb[0]);
                        intent.putExtra("key", tb[1]);
                        break;
                }

                if (iThemeUpdate()==true) {
                    showAD();
                    startActivity(intent);
                }else{
                    showAD();
                    Toast.makeText(MainActivity.this, "Please update your i Theme!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iThemeUpdate();
        adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2780984156359274/7918277846");
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadAD();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                loadAD();
            }

            @Override
            public void onAdOpened() {
                loadAD();
            }
        });
    }

    public void loadAD(){
        if (!interstitialAd.isLoaded()){
            interstitialAd.loadAd(adRequest);
        }
    }

    public void showAD(){
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            interstitialAd.loadAd(adRequest);
        }
    }

    public boolean createDir() {
        boolean b = false;
        storage.createDirectory(fontPath, false);
        storage.createDirectory(keyPaht, false);
        if (storage.isDirectoryExists(fontPath) == true && storage.isDirectoryExists(keyPaht) == true) {
            b = true;
        }
        return b;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.helps:
                showAD();
                startActivity(new Intent(MainActivity.this, Help.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean iThemeUpdate() {
        boolean b = false;
        String version = null;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.bbk.theme", 0);
            version = packageInfo.versionName;
            version = version.replace(".", "");
            int current = Integer.parseInt(version);
            final int need = 4001;
            if (current >= need) {
                Log.d("iThemeVersion", "Ogay");
                b = true;
            } else {
                b = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Requires!");
                builder.setMessage("You need to update i Theme.\nClick Update now for download i Theme latest version. After downloaded please install!");
                builder.setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showAD();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://github.com/KhunHtetzNaing/Files/releases/download/5/iTheme_Latest.apk"));
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            showAD();
            Toast.makeText(this, "Your phone is not Vivo", Toast.LENGTH_SHORT).show();
        }
        return b;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Attention!")
                .setMessage("Do you want to exit ?")
                .setIcon(R.drawable.icon)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showAD();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showAD();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
