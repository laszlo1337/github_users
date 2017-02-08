package com.android.laszlo.githubusers.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.laszlo.githubusers.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Example:
 * <p>
 * <pre>
 * {@code
 * public FancyActivity extends AppCompatActivity implements EndlessRecyclerOnScrollListener.OnLoadMoreListener {
 *
 *     EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
 *
 *     @Override
 *     protected void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_home);
 *
 *         RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
 *         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
 *         recyclerView.setAdapter(adapter);
 *         endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager, this);
 *         endlessRecyclerOnScrollListener.setVisibleThreshold(10);
 *         recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
 *     }
 *
 *     @Override
 *     public void onLoadMore(int currentPage) {
 *          // load more data
 *     }
 *     @Override
 *     protected void onResume() {
 *         super.onResume();
 *         endlessRecyclerOnScrollListener.reset(0, true);
 *     }
 *   }
 * }
 *
 *
 * </pre>
 *
 * @author Sławomir Onyszko
 */
public final class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private int visibleThreshold = 5;
    private int lastUserID = 1;
    private int firstVisibleItemPosition;
    private int visibleItemCount;
    private int totalItemCount;
    private boolean loading = true;
    private ArrayList<User> users;

    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + visibleThreshold)) {
            if (onLoadMoreListener != null) {


                lastUserID = users.get(users.size()-1).getId();
                onLoadMoreListener.onLoadMore(lastUserID);

                loading = true;
            }
        }
    }

    public void reset(int previousTotal, boolean loading) {
        this.previousTotal = previousTotal;
        this.loading = loading;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public void setDataList(ArrayList<User> users){
        this.users = users;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }
}