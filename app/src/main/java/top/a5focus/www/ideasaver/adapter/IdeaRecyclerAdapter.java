package top.a5focus.www.ideasaver.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import top.a5focus.www.ideasaver.R;
import top.a5focus.www.ideasaver.db.Idea;

/**
 * Created by 69133 on 2017/1/6.
 */

public class IdeaRecyclerAdapter extends RecyclerView.Adapter<IdeaRecyclerAdapter.ViewHolder> {


    private List<Idea> mIdeas;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ideaTitle;
        TextView ideaSuggestor;
        TextView idea_location;
        TextView idea_date;

        public ViewHolder(View view) {
            super(view);
            ideaTitle = (TextView) view.findViewById(R.id.idea_title);
            ideaSuggestor = (TextView) view.findViewById(R.id.idea_suggustor);
            idea_location=(TextView)view.findViewById(R.id.idea_location);
            idea_date=(TextView)view.findViewById(R.id.idea_date);

        }

    }

    public IdeaRecyclerAdapter(List<Idea> ideas) {
        mIdeas = ideas;

    }


    @Override
    public int getItemCount() {
        return mIdeas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_recyclerview_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IdeaRecyclerAdapter.ViewHolder holder, int position) {

        Idea idea = mIdeas.get(position);

        holder.ideaTitle.setText(idea.getIdeaTitle());
        holder.ideaSuggestor.setText(idea.getSuggestor());
        holder.idea_location.setText(idea.getCreateLocation());
        holder.idea_date.setText(idea.getCreateTime());
    }
}
