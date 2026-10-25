
package com.gece.companion;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private LinearLayout messages;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(20, 24, 20, 16);
        root.setBackgroundColor(Color.rgb(18, 18, 25));

        TextView title = new TextView(this);
        title.setText("Gece");
        title.setTextSize(30);
        title.setTextColor(Color.rgb(190, 170, 255));
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Buradayım.");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle);

        ScrollView scroll = new ScrollView(this);
        messages = new LinearLayout(this);
        messages.setOrientation(LinearLayout.VERTICAL);
        messages.setPadding(4, 20, 4, 20);
        scroll.addView(messages);

        root.addView(scroll, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));

        input = new EditText(this);
        input.setSingleLine(true);
        input.setHint("Gece'ye bir şey yaz...");
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(Color.GRAY);

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.addView(input, new LinearLayout.LayoutParams(0, -2, 1));

        Button send = new Button(this);
        send.setText("Gönder");
        bottom.addView(send);
        root.addView(bottom);

        send.setOnClickListener(v -> sendMessage());
        input.setOnEditorActionListener((v, actionId, event) -> {
            sendMessage();
            return true;
        });

        setContentView(root);
        addMessage("Gece", "Merhaba. Ben Gece. Henüz başlangıç sürümündeyim.");
    }

    private void sendMessage() {
        String text = input.getText().toString().trim();
        if (text.isEmpty()) return;

        addMessage("Sen", text);
        input.setText("");

        String reply;
        String lower = text.toLowerCase();

        if (lower.contains("merhaba") || lower.contains("selam")) {
            reply = "Selam. Nasılsın?";
        } else if (lower.contains("adın")) {
            reply = "Ben Gece.";
        } else if (lower.contains("nasılsın")) {
            reply = "Buradayım ve seninle konuşmaya hazırım.";
        } else {
            reply = "Seni dinliyorum. Gerçek yapay zekâ motorum henüz eklenmedi.";
        }

        addMessage("Gece", reply);
    }

    private void addMessage(String sender, String text) {
        TextView message = new TextView(this);
        message.setText(sender + "\n" + text);
        message.setTextColor(Color.WHITE);
        message.setTextSize(16);
        message.setPadding(14, 12, 14, 12);
        message.setBackgroundColor(Color.rgb(37, 37, 50));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 6, 0, 6);
        messages.addView(message, params);
    }
  }
