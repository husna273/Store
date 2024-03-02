package com.example.bookstore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RadioGroup membership;
    private double totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etJumlahBukuSHAF = findViewById(R.id.etjumlahbukuSHAF);
        EditText etJumlahBukuSPB = findViewById(R.id.etjumlahbukuSPB);
        EditText etJumlahBukuAzzarine = findViewById(R.id.etjumlahbukuazzarine);
        EditText etJumlahBukuAtoZ = findViewById(R.id.etjumlahbukuAtoZ);

        Button btnSubmit = findViewById(R.id.btnsubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jumlahBukuSHAF = Integer.parseInt(etJumlahBukuSHAF.getText().toString());
                int jumlahBukuSPB = Integer.parseInt(etJumlahBukuSPB.getText().toString());
                int jumlahBukuAzzarine = Integer.parseInt(etJumlahBukuAzzarine.getText().toString());
                int jumlahBukuAtoZ = Integer.parseInt(etJumlahBukuAtoZ.getText().toString());

                totalAmount = (jumlahBukuSHAF * 90000) + (jumlahBukuSPB * 75000) +
                        (jumlahBukuAzzarine * 75000) + (jumlahBukuAtoZ * 80000);


                showReceiptDialog(jumlahBukuSHAF, jumlahBukuSPB, jumlahBukuAzzarine, jumlahBukuAtoZ);
            }
        });
    }

    private void showReceiptDialog(int jumlahBukuSHAF, int jumlahBukuSPB, int jumlahBukuAzzarine,
                                   int jumlahBukuAtoZ) {
        double diskon = 0.0;

        // Hitung total order
        totalAmount = (jumlahBukuSHAF * 90000) + (jumlahBukuSPB * 75000) +
                (jumlahBukuAzzarine * 75000) + (jumlahBukuAtoZ * 80000);

        // jika belanja lebih dari Rp. 200000 maka akan mendapat diskon 5%
        if (totalAmount > 200000) {
            diskon = 0.05 * totalAmount;
        }


        // Hitung total pembayaran setelah diskon
        double totalPembayaran = totalAmount - diskon;

        StringBuilder receipt = new StringBuilder("=== Shopping List ===\n");
        receipt.append("Buku SHAF (Rp. 90.000) x ").append(jumlahBukuSHAF).append("\n");
        receipt.append("Buku SPB (Rp. 75.000) x ").append(jumlahBukuSPB).append("\n");
        receipt.append("Buku Azzarine (Rp. 75.000) x ").append(jumlahBukuAzzarine).append("\n");
        receipt.append("Buku AtoZ (Rp. 80.000) x ").append(jumlahBukuAtoZ).append("\n");

        receipt.append("\nTotal Harga: Rp. ").append(totalAmount).append("\n");

        // Tampilkan diskon jika ada
        if (diskon > 0) {
            receipt.append("Diskon 5%: -Rp. ").append(diskon).append("\n");
        }

        receipt.append("\nTotal Pembayaran: Rp. ").append(totalPembayaran).append("\n\t");
        receipt.append("           =======Thank You======= ");
        receipt.append("    ======Hope To see You Again======");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bon Pembayaran")
                .setMessage(receipt.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
