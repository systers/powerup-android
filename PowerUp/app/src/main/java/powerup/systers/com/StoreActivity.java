package powerup.systers.com;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import powerup.systers.com.data.DataSource;
import powerup.systers.com.data.IDataSource;
import powerup.systers.com.data.SessionHistory;
import powerup.systers.com.data.StoreItem;
import powerup.systers.com.utils.DbResourceUtils;
import powerup.systers.com.utils.InjectionClass;
import powerup.systers.com.utils.PowerUpUtils;

import static powerup.systers.com.utils.PowerUpUtils.MAX_ELEMENTS_PER_SCREEN;


public class StoreActivity extends AppCompatActivity {

    GridView gridView;
    public int storeItemTypeindex = 0;
    public int currentPage = 0;
    int screenWidth, screenHeight;
    ImageView leftArrow, rightArrow, hairButton, clothesButton, accessoriesButton;
    List<List<StoreItem>> allDataSet;
    GridAdapter adapter;
    TextView karmaPoints;
    private DataSource dataSource;
    private DbResourceUtils resourceUtils;
    private ImageView eyeImageView, skinImageView, clothImageView, hairImageView, accessoryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // get the database instance
        dataSource = InjectionClass.provideDataSource(this);
        // getting the screen dimensions
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        // set the karma points
        karmaPoints = findViewById(R.id.karma_points);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));

        // init avatar image views
        eyeImageView = findViewById(R.id.eye_view);
        skinImageView = findViewById(R.id.skin_view);
        hairImageView = findViewById(R.id.hair_view);
        clothImageView = findViewById(R.id.dress_view);
        accessoryImageView = findViewById(R.id.acc_view);

        //  init views
        leftArrow = findViewById(R.id.left_arrow);
        rightArrow = findViewById(R.id.right_arrow);
        hairButton = findViewById(R.id.hair_button);
        clothesButton = findViewById(R.id.clothes_button);
        accessoriesButton = findViewById(R.id.accessories_button);

        // use DbResourceUtil to extract data from database & apply it on avatar
        resourceUtils = new DbResourceUtils(dataSource, this);
        eyeImageView.setImageResource(resourceUtils.getEyeResourceId());
        skinImageView.setImageResource(resourceUtils.getSkinResourceId());
        clothImageView.setImageResource(resourceUtils.getClothResourceId());
        hairImageView.setImageResource(resourceUtils.getHairResourceId());
        accessoryImageView.setImageResource(resourceUtils.getAvatarAccessoriesResourceId());

        // starts the map activity
        Button mapButton = findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(StoreActivity.this,MapActivity.class));
                overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
            }
        });

        hairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 0;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, MAX_ELEMENTS_PER_SCREEN));
                setArrows();
            }
        });

        clothesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 1;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, MAX_ELEMENTS_PER_SCREEN));
                setArrows();
            }
        });

        accessoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 2;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, MAX_ELEMENTS_PER_SCREEN));
                setArrows();
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage--;
                setArrows();
                if (currentPage * MAX_ELEMENTS_PER_SCREEN < allDataSet.get(storeItemTypeindex).size()) {
                    if (allDataSet.get(storeItemTypeindex).size() >= currentPage * MAX_ELEMENTS_PER_SCREEN + MAX_ELEMENTS_PER_SCREEN) {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * MAX_ELEMENTS_PER_SCREEN, currentPage * MAX_ELEMENTS_PER_SCREEN + MAX_ELEMENTS_PER_SCREEN));
                    } else {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * MAX_ELEMENTS_PER_SCREEN, allDataSet.get(storeItemTypeindex).size()));
                    }
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                setArrows();
                if (allDataSet.get(storeItemTypeindex).size() >= currentPage * MAX_ELEMENTS_PER_SCREEN + MAX_ELEMENTS_PER_SCREEN) {
                    adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * MAX_ELEMENTS_PER_SCREEN, currentPage * MAX_ELEMENTS_PER_SCREEN + MAX_ELEMENTS_PER_SCREEN));
                } else {
                    adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * MAX_ELEMENTS_PER_SCREEN, allDataSet.get(storeItemTypeindex).size()));
                }
            }
        });

        gridView = findViewById(R.id.grid_view);
        createDataLists();
        adapter = new GridAdapter(this, allDataSet.get(0).subList(0, MAX_ELEMENTS_PER_SCREEN));
        gridView.setAdapter(adapter);
        setArrows();
    }

    // create store data arraylist & add it in allDataSet array list
    public void createDataLists() {
        allDataSet = new ArrayList<>();

        List<StoreItem> storeHair = new ArrayList<>();
        List<StoreItem> storeClothes = new ArrayList<>();
        List<StoreItem> storeAccessories = new ArrayList<>();

        allDataSet.add(storeHair);
        allDataSet.add(storeClothes);
        allDataSet.add(storeAccessories);

        for (int i = 0; i < PowerUpUtils.HAIR_IMAGES.length; i++) {
            StoreItem item = new StoreItem(PowerUpUtils.HAIR_POINTS_TEXTS[i], PowerUpUtils.HAIR_IMAGES[i]);
            allDataSet.get(0).add(item);
        }
        for (int i = 0; i < PowerUpUtils.CLOTHES_IMAGES.length; i++) {
            StoreItem item = new StoreItem(PowerUpUtils.CLOTHES_POINTS_TEXTS[i], PowerUpUtils.CLOTHES_IMAGES[i]);
            allDataSet.get(1).add(item);
        }
        for (int i = 0; i < PowerUpUtils.ACCESSORIES_IMAGES.length; i++) {
            StoreItem item = new StoreItem(PowerUpUtils.ACCESSORIES_POINTS_TEXTS[i], PowerUpUtils.ACCESSORIES_IMAGES[i]);
            allDataSet.get(2).add(item);
        }
    }

    public int calculatePosition(int position) {
        int id = currentPage * MAX_ELEMENTS_PER_SCREEN +position;
        return id;
    }

    class GridAdapter extends BaseAdapter {

        List<StoreItem> storeItems;
        Context context;

        GridAdapter(Context context, List<StoreItem> list) {
            this.context = context;
            this.storeItems = list;
        }

        public void refresh(List<StoreItem> updatedList) {
            this.storeItems = updatedList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return storeItems.size();
        }

        @Override
        public Object getItem(int position) {
            return storeItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            ImageView itemImage;
            TextView itemPoints;

            ViewHolder(View view) {
                itemImage = (ImageView) view.findViewById(R.id.item_image);
                itemPoints = (TextView) view.findViewById(R.id.item_points);
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View storeItem = convertView;
            ViewHolder holder;
            if (storeItem == null) {
                // inflating the store_selection_view layout
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                storeItem = layoutInflater.inflate(R.layout.store_selection_view, parent, false);

                int itemWidth = (int) ((screenWidth / 85.428f) * 13);
                int itemHeight = (int) ((screenHeight / 51.428f) * 18);
                storeItem.setLayoutParams(new AbsListView.LayoutParams(itemWidth, itemHeight));

                holder = new ViewHolder(storeItem);
                storeItem.setTag(holder);
            } else {
                holder = (ViewHolder) storeItem.getTag();
            }
            storeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.isEnabled()){

                        final TextView itemPoints = (TextView) v.findViewById(R.id.item_points);
                        final int index = calculatePosition(position)+1;
                        if (storeItemTypeindex == 0) { //hair
                            dataSource.setAvatarHair(index);
                            hairImageView.setImageResource(resourceUtils.getHairResourceId());
                            dataSource.getPurchasedHair(index, new IDataSource.LoadIntegerCallback() {
                                @Override
                                public void onResultLoaded(int value) {
                                    if (value == 0) {
                                        final int cost = Integer.parseInt(itemPoints.getText().toString());
                                        showConfirmPurchaseDialog(cost, index);
                                    }
                                    else {
                                        dataSource.setAvatarHair(index);
                                    }
                                }
                            });
                            
                        } else if (storeItemTypeindex == 1) { //clothes
                            dataSource.setAvatarCloth(index);
                            dataSource.getPurchasedClothes(index, new IDataSource.LoadIntegerCallback() {
                                @Override
                                public void onResultLoaded(int value) {
                                    if(value == 0) {
                                        final int cost = Integer.parseInt(itemPoints.getText().toString());
                                        showConfirmPurchaseDialog(cost, index);
                                    }
                                    else {
                                        dataSource.setAvatarCloth(index);
                                    }
                                }
                            });
                        } else if (storeItemTypeindex == 2) { //accessories
                            dataSource.setAvatarAccessory(index);
                            dataSource.getPurchasedAccessories(index, new IDataSource.LoadIntegerCallback() {
                                @Override
                                public void onResultLoaded(int value) {
                                    if (value == 0) {
                                        final int cost = Integer.parseInt(itemPoints.getText().toString());
                                        showConfirmPurchaseDialog(cost, index);
                                    } else {
                                        dataSource.setAvatarAccessory(index);
                                    }
                                }
                            });
                        }
                        adapter.refresh(adapter.storeItems); // will update change the background if any is not available
                    }
                }
            });
            StoreItem temp = storeItems.get(position);
                holder.itemImage.setBackground(context.getResources().getDrawable(temp.imageId));
                holder.itemPoints.setText(temp.points);

            int id = calculatePosition(position)+1;

            if (getPurchasedStatus(id) == 1) { // whatever type is currently opened, it is already bought
                storeItem.setBackground(getResources().getDrawable(R.drawable.sold_item));
                storeItem.setEnabled(true);
                //Testing whether the item matches id (selected)
                if (getSelectedItemId() == id) {
                    holder.itemImage.setImageResource(R.drawable.store_tick);
                } else {
                    holder.itemImage.setImageResource(android.R.color.transparent);
                }
            } else { //not purchased => available/not available
                holder.itemImage.setImageResource(Color.TRANSPARENT);
                if (Integer.parseInt(temp.points) <= SessionHistory.totalPoints) { //can be bought
                    storeItem.setBackground(getResources().getDrawable(R.drawable.buy_item));
                    storeItem.setEnabled(true);

                } else { //can't be bought
                    storeItem.setBackground(getResources().getDrawable(R.drawable.unavailable_item));
                    storeItem.setEnabled(false);
                }
            }
            return storeItem;
        }

    }

    private int getSelectedItemId(){
        final int[] returnValue = new int[1];
        switch (storeItemTypeindex) {
            case 0: //hair
                dataSource.getAvatarHair(new IDataSource.LoadIntegerCallback() {
                    @Override
                    public void onResultLoaded(int value) {
                        returnValue[0] = value;
                    }
                });
                return returnValue[0];
            case 1: //cloth
                dataSource.getAvatarCloth(new IDataSource.LoadIntegerCallback() {
                    @Override
                    public void onResultLoaded(int value) {
                        returnValue[0] = value;
                    }
                });
                return returnValue[0];
            case 2:
                dataSource.getAvatarAccessory(new IDataSource.LoadIntegerCallback() {
                    @Override
                    public void onResultLoaded(int value) {
                        returnValue[0] = value;
                    }
                });
                return returnValue[0];
            default:
                throw new IllegalArgumentException("Invalid store type index");
        }
    }

    private void showConfirmPurchaseDialog(final int cost, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.purchase_confirm_title)
                .setMessage(getString(R.string.purchase_confirm_message, cost));
        builder.setPositiveButton(R.string.purchase_confirm_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SessionHistory.totalPoints -= cost;
                karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
                switch (storeItemTypeindex) {
                    case PowerUpUtils.TYPE_HAIR:
                        dataSource.setPurchasedHair(index);
                        dataSource.setAvatarHair(index);
                        break;
                    case PowerUpUtils.TYPE_CLOTHES:
                        dataSource.setPurchasedClothes(index);
                        dataSource.setAvatarCloth(index);
                        break;
                    case PowerUpUtils.TYPE_ACCESSORIES:
                        dataSource.setPurchasedAccessories(index);
                        dataSource.setAvatarAccessory(index);
                }
                adapter.refresh(adapter.storeItems); // will update change the background if any is not available
                showSuccessPurchaseDialog();
            }
        });
        builder.setNegativeButton(R.string.purchase_confirm_cancel, null);
        AlertDialog dialog = builder.create();
        ColorDrawable drawable = new ColorDrawable(Color.WHITE);
        drawable.setAlpha(200);
        dialog.getWindow().setBackgroundDrawable(drawable);
        dialog.show();
    }

    // creates & displays alert dialog of success purchase
    private void showSuccessPurchaseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.purchase_success_title)
                .setMessage(R.string.purchase_success_message);
        builder.setPositiveButton(R.string.purchase_success_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        ColorDrawable drawable = new ColorDrawable(Color.WHITE);
        drawable.setAlpha(200);
        dialog.getWindow().setBackgroundDrawable(drawable);
        dialog.show();
    }

    public int getPurchasedStatus(int index) {
        final int[] returnValue = new int[1];
        switch(storeItemTypeindex) {
            case 0: dataSource.getPurchasedHair(index, new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    returnValue[0] = value;
                }
            }); // hair
                return returnValue[0];
            case 1: dataSource.getPurchasedClothes(index, new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    returnValue[0] = value;
                }
            }); // clothes
                return returnValue[0];
            case 2: dataSource.getPurchasedAccessories(index, new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    returnValue[0] = value;
                }
            }); // accessories
                return returnValue[0]; // accessories
            default:return 0;
        }
    }


    // set arrows using currentPage & Max element per screen
  public void setArrows() {
        if(currentPage==0){
            leftArrow.setVisibility(View.GONE);
        } else {
            leftArrow.setVisibility(View.VISIBLE);
        }
        if((currentPage+1) * MAX_ELEMENTS_PER_SCREEN >= allDataSet.get(storeItemTypeindex).size()){
            rightArrow.setVisibility(View.GONE);
        } else {
            rightArrow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Goes back to the map when user presses back button
     */
    @Override
    public void onBackPressed(){
        // The flag FLAG_ACTIVITY_CLEAR_TOP checks if an instance of the activity is present and it
        // clears the activities that were created after the found instance of the required activity
        startActivity(new Intent(StoreActivity.this, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataSource.clearInstance();
    }
}




