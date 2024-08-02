package ph46328.restapi.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph46328.restapi.assignment.R;
import ph46328.restapi.assignment.activity.ProductDetailActivity;
import ph46328.restapi.assignment.adapter.CardAdapter;
import ph46328.restapi.assignment.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CardAdapter cartAdapter;
    private List<Product> cartList;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance(String param1, String param2) {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        cartRecyclerView = view.findViewById(R.id.recyclerView_cart);

        cartList = ProductDetailActivity.getCart();
        setupCartRecyclerView();

        return view;
    }

    private void setupCartRecyclerView() {
        cartRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 items per row
        cartAdapter = new CardAdapter(getContext(), cartList);
        cartRecyclerView.setAdapter(cartAdapter);
    }
}