package ph46328.restapi.assignment.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ph46328.restapi.assignment.R;
import ph46328.restapi.assignment.databinding.ActivityProductDetailBinding;
import ph46328.restapi.assignment.fragment.CardFragment;
import ph46328.restapi.assignment.model.Product;

public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;
    private static List<Product> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable drawable = getResources().getDrawable(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        if (getIntent() != null) {
            String name = getIntent().getStringExtra("name");
            int price = getIntent().getIntExtra("price", 0);
            String description = getIntent().getStringExtra("description");
            String image = getIntent().getStringExtra("image");

            // Set the product details
            binding.detailProductName.setText(name);
            binding.detailProductPrice.setText("$ " + price);
            binding.detailProductDes.setText(description);
            Glide.with(this).load(image).into(binding.detailProductImg);

            binding.btnAdd.setOnClickListener(v -> {
                Product product = new Product(
                        getIntent().getIntExtra("id", 0),
                        name,
                        price,
                        image,
                        description
                );
                cart.add(product);
                getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new CardFragment())
                        .addToBackStack(null)
                        .commit();
            });
        }

    }

    public static List<Product> getCart() {
        return cart;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}