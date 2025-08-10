package com.ritesh.studentutilityapp;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.ViewHolder> {

    ArrayList<TimetableItem> items;

    public TimetableAdapter(ArrayList<TimetableItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TimetableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableAdapter.ViewHolder holder, int position) {
        TimetableItem item = items.get(position);
        holder.subjectText.setText(item.getSubject());
        holder.dayTimeText.setText(item.getDay() + " - " + item.getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectText, dayTimeText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectText = itemView.findViewById(R.id.subjectText);
            dayTimeText = itemView.findViewById(R.id.dayTimeText);
        }
    }
}
