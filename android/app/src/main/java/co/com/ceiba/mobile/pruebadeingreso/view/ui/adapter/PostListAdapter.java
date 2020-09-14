package co.com.ceiba.mobile.pruebadeingreso.view.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;

/**
 * Component ID: PostListAdapter.java <br>
 * Description: Adapter to handle the Post's RecyclerView. <br>
 * Author: <a href="mailto:lauragomez.lg247@gmail.com">Laura Gómez</a>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>9/12/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private Context context;

    private List<Post> postList;


    public PostListAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PostListItemBinding binding = PostListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textViewTitle.setText(postList.get(position).getTitle());
        viewHolder.textViewBody.setText(postList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;

        TextView textViewBody;


        ViewHolder(final PostListItemBinding itemView) {
            super(itemView.getRoot());
            this.textViewTitle = itemView.title;
            this.textViewBody = itemView.body;
        }
    }
}
