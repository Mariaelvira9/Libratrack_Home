package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Pinjam extends AppCompatActivity {

    private TextView bookTitle, bookAuthor;
    private ImageView bookImage;
    private Button likeButton, borrowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinjam_1);

        // Inisialisasi Views
        bookTitle = findViewById(R.id.bookTitle);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookImage = findViewById(R.id.bookCover); // Pastikan ID di XML sama dengan di sini
        likeButton = findViewById(R.id.likeButton);
        borrowButton = findViewById(R.id.borrowButton);

        // Ambil data yang dikirimkan melalui Intent
        String title = getIntent().getStringExtra("book_title");
        String author = getIntent().getStringExtra("book_author");
        int imageResId = getIntent().getIntExtra("book_image", 0); // Gambar akan dikirimkan sebagai resource ID

        // Menampilkan detail buku
        if (title != null && author != null && imageResId != 0) {
            bookTitle.setText(title);
            bookAuthor.setText(author);
            bookImage.setImageResource(imageResId);
        }

        // Listener untuk likeButton
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pinjam.this, "Buku disukai", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener untuk borrowButton
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat AlertDialog konfirmasi Yes/No
                new AlertDialog.Builder(Pinjam.this)
                        .setTitle("Confirmation")
                        .setMessage("Apakah anda yakin ingin meminjam buku ini?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Aksi ketika tombol "Yes" ditekan
                                dialog.dismiss();
                                // Menampilkan toast ketika "Yes" ditekan
                                Toast.makeText(Pinjam.this, "Buku berhasil ditambahkan!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Menutup dialog ketika "No" ditekan
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show(); // Menampilkan AlertDialog
            }
        });
    }
}
