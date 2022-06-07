package com.prm392.groupproject;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int CURRENT_BALANCE;
    private static int NUM_OF_WINS = 0;
    private static int NUM_OF_LOSSES = 0;

    @SuppressLint("SetTextI18n")
    public void setBalance(int newBalance, TextView tvAmount) {
        CURRENT_BALANCE = newBalance;
        tvAmount.setText("$" + CURRENT_BALANCE);
    }

    public void setWins(int newWins, TextView tvWins) {
        NUM_OF_WINS = newWins;
        tvWins.setText(String.valueOf(NUM_OF_WINS));
    }

    public void setLosses(int newLosses, TextView tvLosses) {
        NUM_OF_LOSSES = newLosses;
        tvLosses.setText(String.valueOf(NUM_OF_LOSSES));
    }

    private void animateProgression(int progress, SeekBar seekBar) {
        final ObjectAnimator animation = ObjectAnimator.ofInt(seekBar, "progress", 0, progress);
        animation.setDuration(3500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        seekBar.clearAnimation();
    }

    private void hideAllBadges(List<Pet> pets) {
        for (Pet pet : pets) {
            pet.getBadge().setImageDrawable(null);
        }
    }

    private void clearResults(RadioGroup radioGroup, EditText etBetAmount, List<Pet> pets) {
        radioGroup.clearCheck();
        etBetAmount.setText("");
        for (Pet pet : pets) {
            pet.getSeekBar().setProgress(0);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFinishOnTouchOutside(false);

        TextView tvAmount = findViewById(R.id.tvAmount);
        TextView tvWins = findViewById(R.id.tvWins);
        TextView tvLosses = findViewById(R.id.tvLosses);

        int initBalance = getIntent().getIntExtra("depositAmount", 0);
        setBalance(initBalance, tvAmount);
        setWins(NUM_OF_WINS, tvWins);
        setLosses(NUM_OF_LOSSES, tvLosses);


        Button btnStart = findViewById(R.id.btnStart);
        EditText etBetAmount = findViewById(R.id.etBetAmount);

        SeekBar seekBarPet1 = findViewById(R.id.seekBarPet1);
        SeekBar seekBarPet2 = findViewById(R.id.seekBarPet2);
        SeekBar seekBarPet3 = findViewById(R.id.seekBarPet3);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        RadioButton radioButtonPet1 = findViewById(R.id.radioButtonPet1);
        RadioButton radioButtonPet2 = findViewById(R.id.radioButtonPet2);
        RadioButton radioButtonPet3 = findViewById(R.id.radioButtonPet3);

        ImageView pet1Badge = findViewById(R.id.pet1Badge);
        ImageView pet2Badge = findViewById(R.id.pet2Badge);
        ImageView pet3Badge = findViewById(R.id.pet3Badge);


        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet(seekBarPet1, radioButtonPet1, pet1Badge));
        pets.add(new Pet(seekBarPet2, radioButtonPet2, pet2Badge));
        pets.add(new Pet(seekBarPet3, radioButtonPet3, pet3Badge));

        hideAllBadges(pets);

        // Make seekbars unable to be changed when touching
        seekBarPet1.setOnTouchListener((v, event) -> true);
        seekBarPet2.setOnTouchListener((v, event) -> true);
        seekBarPet3.setOnTouchListener((v, event) -> true);

        btnStart.setOnClickListener(v -> {
            btnStart.setEnabled(false);
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
            try {

                if (checkedRadioButtonId == -1) {
                    Toast.makeText(MainActivity.this, "You must select one pet", Toast.LENGTH_SHORT).show();
                    btnStart.setEnabled(true);
                    return;
                }

                String betAmountStr = etBetAmount.getText().toString();
                if (betAmountStr.trim().isEmpty()) {
                    etBetAmount.setError("Please enter bet amount");
                    etBetAmount.requestFocus();
                    btnStart.setEnabled(true);
                    return;
                }
                int betAmount = Integer.parseInt(betAmountStr);
                if (betAmount <= 0) {
                    etBetAmount.setError("Please enter a valid bet amount");
                    etBetAmount.requestFocus();
                    btnStart.setEnabled(true);
                    return;
                }
                if (betAmount > CURRENT_BALANCE) {
                    etBetAmount.setError("You don't have enough money");
                    etBetAmount.requestFocus();
                    btnStart.setEnabled(true);
                    return;
                }

                Collections.shuffle(pets);

                Pet rank1Pet = pets.get(0);
                Pet rank2Pet = pets.get(1);
                Pet rank3Pet = pets.get(2);

                // Animate pets' seekbars progress
                animateProgression(100, findViewById(rank1Pet.getSeekBar().getId()));
                animateProgression(85, findViewById(rank2Pet.getSeekBar().getId()));
                animateProgression(70, findViewById(rank3Pet.getSeekBar().getId()));

                // Show badges
                new Handler().postDelayed(() -> {
                    rank3Pet.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_bronze_medal));
                    rank1Pet.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_trophy));
                    rank2Pet.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_silver_medal));
                }, 3200);


                new Handler().postDelayed(() -> {
                    if (rank1Pet.getRadioButton() == checkedRadioButton) {
                        setBalance(CURRENT_BALANCE + betAmount, tvAmount);
                        setWins(NUM_OF_WINS + 1, tvWins);

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("You won!")
                                .setMessage("Result: + $" + betAmount + "\nYour new balance is $" + CURRENT_BALANCE + ".")
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                    clearResults(radioGroup, etBetAmount, pets);
                                    hideAllBadges(pets);
                                })
                                .setCancelable(false)
                                .show();
                    } else {
                        setBalance(CURRENT_BALANCE - betAmount, tvAmount);
                        setLosses(NUM_OF_LOSSES + 1, tvLosses);
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("You loose!")
                                .setMessage("Result: - $" + betAmount + "\nYour new balance is $" + CURRENT_BALANCE + ".")
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                    clearResults(radioGroup, etBetAmount, pets);
                                    hideAllBadges(pets);
                                    if (CURRENT_BALANCE <= 0) {
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("You are out of money!")
                                                .setMessage("Please deposit more money to continue.")
                                                .setPositiveButton("Deposit", (d, w) -> {
                                                    Intent intent = new Intent(MainActivity.this, DepositActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                })
                                                .setNegativeButton("Quit game", (d, w) -> {
                                                    finish();
                                                    System.exit(0);
                                                })
                                                .setCancelable(false)
                                                .show();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                    btnStart.setEnabled(true);
                }, 4000);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}