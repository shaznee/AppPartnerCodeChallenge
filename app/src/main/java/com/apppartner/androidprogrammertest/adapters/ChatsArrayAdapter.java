package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{
    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell;

        if (convertView ==  null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cell_chat, parent, false);
            chatCell = new ChatCell();
            chatCell.avatarImageView = (ImageView) convertView.findViewById(R.id.imgAvatar);
            chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
            chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            Typeface typeFaceUserName = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/Jelloween - Machinato.ttf");
            Typeface typeFaceMessage = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/Jelloween - Machinato Light.ttf");
            chatCell.usernameTextView.setTypeface(typeFaceUserName);
            chatCell.messageTextView.setTypeface(typeFaceMessage);
            convertView.setTag(chatCell);
        } else {
            chatCell = (ChatCell) convertView.getTag();
        }

        ChatData chatData = getItem(position);

        Picasso.with(getContext()).load(chatData.avatarURL).into(chatCell.avatarImageView);
        chatCell.usernameTextView.setText(chatData.username);
        chatCell.messageTextView.setText(chatData.message);

        return convertView;
    }

    private static class ChatCell
    {
        ImageView avatarImageView;
        TextView usernameTextView;
        TextView messageTextView;
    }
}
