package powerup.systers.com;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.datamodel.StoreItem;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.powerup.PowerUpUtils;


public class StoreActivity extends AppCompatActivity {

    GridView gridView;
    public int storeItemTypeindex = 0;
    public int currentPage = 0;
    int screenWidth, screenHeight;
    ImageView clothImageView, hairImageView, accessoryImageView;
    ImageView leftArrow, rightArrow, hairButton, clothesButton, accessoriesButton;
    List<List<StoreItem>> allDataSet;
    GridAdapter adapter;
    TextView karmaPoints;
    private DatabaseHandler mDbHandler;
    java.lang.reflect.Field photoNameField;
    R.drawable ourRID;
    private Context context;
    String purchaseWarningMessage;
    AlertDialog purchaseWarningDialog;
    Button purchaseWarningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        karmaPoints = (TextView) findViewById(R.id.karma_points);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
        Button mapButton = (Button) findViewById(R.id.map_button);
        context = StoreActivity.this;

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(StoreActivity.this,MapActivity.class));
            }
        });


        ImageView eyeImageView = (ImageView) findViewById(R.id.eye_view);
        ImageView skinImageView = (ImageView) findViewById(R.id.skin_view);
        hairImageView = (ImageView) findViewById(R.id.hair_view);
        clothImageView = (ImageView) findViewById(R.id.dress_view);
        accessoryImageView = (ImageView) findViewById(R.id.acc_view);

        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + getmDbHandler().getAvatarEye();
        ourRID = new R.drawable();

        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        String skinImageName = getResources().getString(R.string.skin);
        skinImageName = skinImageName + getmDbHandler().getAvatarSkin();
        try {
            photoNameField = ourRID.getClass().getField(skinImageName);
            skinImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        setAvatarClothes(getmDbHandler().getAvatarCloth());
        setAvatarHair(getmDbHandler().getAvatarHair());
        setAvatarAccessories(getmDbHandler().getAvatarAccessory());


        leftArrow = (ImageView) findViewById(R.id.left_arrow);
        rightArrow = (ImageView) findViewById(R.id.right_arrow);
        hairButton = (ImageView) findViewById(R.id.hair_button);
        clothesButton = (ImageView) findViewById(R.id.clothes_button);
        accessoriesButton = (ImageView) findViewById(R.id.accessories_button);

        hairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 0;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, 6));
            }
        });

        clothesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 1;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, PowerUpUtils.CLOTHES_IMAGES.length%6));
            }
        });

        accessoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 0;
                storeItemTypeindex = 2;
                adapter.refresh(allDataSet.get(storeItemTypeindex).subList(0, PowerUpUtils.ACCESSORIES_IMAGES.length%6));
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage == 0) {
                    return;
                }
                currentPage--;
                if (currentPage * 6 < allDataSet.get(storeItemTypeindex).size()) {
                    if (allDataSet.get(storeItemTypeindex).size() >= currentPage * 6 + 6) {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * 6, currentPage * 6 + 6));
                    } else {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * 6, allDataSet.get(storeItemTypeindex).size()));
                    }
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                if (currentPage * 6 < allDataSet.get(storeItemTypeindex).size()) {
                    if (allDataSet.get(storeItemTypeindex).size() >= currentPage * 6 + 6) {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * 6, currentPage * 6 + 6));
                    } else {
                        adapter.refresh(allDataSet.get(storeItemTypeindex).subList(currentPage * 6, allDataSet.get(storeItemTypeindex).size()));
                    }
                } else {
                    currentPage--;
                }
            }
        });

        gridView = (GridView) findViewById(R.id.grid_view);
        createDataLists();
        adapter = new GridAdapter(this, allDataSet.get(0).subList(0, 6));
        gridView.setAdapter(adapter);
    }

    public void setAvatarHair(int index){
        getmDbHandler().setAvatarHair(index);
        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

    }

    public void setAvatarClothes(int index){
        getmDbHandler().setAvatarCloth(index);
        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

    public void setAvatarAccessories(int index){
        getmDbHandler().setAvatarAccessory(index);
        String accessoryImageName = getResources().getString(R.string.accessories);
        accessoryImageName = accessoryImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(accessoryImageName);
            accessoryImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

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
        int id = currentPage*6+position;
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

                        TextView itemPoints = (TextView) v.findViewById(R.id.item_points);
                        final int index = calculatePosition(position) + 1;
                        int itemCost = Integer.parseInt(itemPoints.getText().toString());
                        purchaseWarningMessage = getResources().
                                getString(R.string.purchase_warning_message, itemCost);
                        if (storeItemTypeindex == 0) { //hair
                            if (getmDbHandler().getPurchasedHair(index) == 0 && SessionHistory.totalPoints >= itemCost) {
                                itemPurchaser(index, storeItemTypeindex, itemCost);
                            } else if (getmDbHandler().getPurchasedHair(index) == 1) {
                                setAvatarHair(index);   //Update item in view if already purchased
                            }

                        } else if (storeItemTypeindex == 1) { //clothes
                            if (getmDbHandler().getPurchasedClothes(index) == 0 && SessionHistory.totalPoints >= itemCost) {
                                itemPurchaser(index, storeItemTypeindex, itemCost);
                            } else if (getmDbHandler().getPurchasedClothes(index) == 1) {
                                setAvatarClothes(index);    //Update item in view if already purchased
                            }

                        } else if (storeItemTypeindex == 2) { //accessories
                            if (getmDbHandler().getPurchasedAccessories(index) == 0 && SessionHistory.totalPoints >= itemCost) {
                                itemPurchaser(index, storeItemTypeindex, itemCost);
                            } else if (getmDbHandler().getPurchasedAccessories(index) == 1) {
                                setAvatarAccessories(index);    //Update item in view if already purchased
                            }
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
                holder.itemImage.setImageResource(R.drawable.store_tick);
                storeItem.setEnabled(true);
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

    public int getPurchasedStatus(int index) {
        if (storeItemTypeindex == 0) { //hair
            return getmDbHandler().getPurchasedHair(index);
        } else if (storeItemTypeindex == 1) { //clothes
            return getmDbHandler().getPurchasedClothes(index);
        } else if (storeItemTypeindex == 2) { //accessories
            return getmDbHandler().getPurchasedAccessories(index);
        }
        return 0;
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }

    /**
     * @param storeItemTypeindex - To know the required Dialog
     * @param index              - item index ID
     * @desc To carry out the purchase of item and display dialogs.
     */
    public void itemPurchaser(final int index, final int storeItemTypeindex, final int itemCost) {
        dialogMaker(false); //Calling first AlertDialog
        purchaseWarningButton = (purchaseWarningDialog.getButton(AlertDialog.BUTTON_POSITIVE));   //Accessing positive button of dialog
        purchaseWarningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchaseWarningDialog.dismiss();   //Dismissing first dialog
                SessionHistory.totalPoints -= itemCost;   //Subtracting points
                karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
                switch (storeItemTypeindex) {
                    case 0:
                        getmDbHandler().setPurchasedHair(index);
                        setAvatarHair(index);
                        break;
                    case 1:
                        getmDbHandler().setPurchasedClothes(index);
                        setAvatarClothes(index);
                        break;
                    case 2:
                        getmDbHandler().setPurchasedAccessories(index);
                        setAvatarAccessories(index);
                        break;
                }
                dialogMaker(true);  //Calling Second Dialog
                adapter.refresh(adapter.storeItems); //Refreshing adapter
            }
        });
    }

    /**
     * @param secondDialogEnabled - To know the required Dialog
     * @desc Build and Show the required AlertDialog, (1)Warning Dialog or (2)Confirmation Dialog
     */
    public void dialogMaker(boolean secondDialogEnabled) {
        Resources res = getResources();
        if (!secondDialogEnabled) {
            dialogInitializer(
                    res.getString(R.string.purchase_warning_title),
                    purchaseWarningMessage,
                    res.getString(R.string.purchase_warning_confirm_message),
                    res.getString(R.string.purchase_warning_negative_message),
                    false
            );
        } else {
            dialogInitializer(
                    res.getString(R.string.purchase_confirm_dialog_title),
                    res.getString(R.string.purchase_confirm_dialog_message),
                    res.getString(R.string.purchase_confirm_button_message),
                    null, true
            );
        }
    }

    /**
     * @param titleText           - Custom titleText String for dialog
     * @param message             - Custom message String for dialog
     * @param positiveButtonText  - Custom positiveButtonText String for dialog
     * @param negativeButtonText  - Custom negativeButtonText String for dialog
     * @param secondDialogEnabled - Compare b/w (1)Warning Dialog and (2)Confirmation Dialog
     * @desc Purchase warning AlertDialog Initializer
     */
    public void dialogInitializer(String titleText, String message, String positiveButtonText,
                                  String negativeButtonText, final boolean secondDialogEnabled) {

        int titlePadding = context.getResources()
                .getInteger(R.integer.purchase_dialog_title_padding);
        int titleTextSize = context.getResources()
                .getInteger(R.integer.purchase_dialog_title_textSize);
        int buttonTextSize = context.getResources()
                .getInteger(R.integer.purchase_dialog_button_textSize);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.43);
        TextView titleTextView = new TextView(this); //TextView for custom title
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setText(titleText);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setTypeface(titleTextView.getTypeface(), Typeface.BOLD);
        titleTextView.setPadding(0, titlePadding, 0, 0); //Setting custom padding
        titleTextView.setTextColor(getResources().getColor(R.color.powerup_black));
        //Initializing Custom AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
        builder.setMessage("Message");
        builder.setPositiveButton((positiveButtonText), null);
        //If Condition - NegativeButton to show only in (1)Warning Dialog
        if (!secondDialogEnabled) {
            builder.setNegativeButton((negativeButtonText), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
        }
        builder.setCustomTitle(titleTextView);
        ColorDrawable drawable = new ColorDrawable(Color.WHITE);
        drawable.setAlpha(200);
        purchaseWarningDialog = builder.create();  //Generating AlertDialog with builder specifics
        purchaseWarningDialog.show();
        final Window window = purchaseWarningDialog.getWindow();   //Getting Window for dialog
        //Mitigating NullPointerException
        if (window != null) {
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);   //Setting custom dimens
            window.setBackgroundDrawable(drawable);
        }
        TextView messageTextView = (TextView) purchaseWarningDialog.findViewById(android.R.id.message);
        //Mitigating NullPointerException
        if (messageTextView != null) {
            messageTextView.setText(message);
            messageTextView.setGravity(Gravity.CENTER);
        }
        final Button positiveButton = purchaseWarningDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        //If Condition - Text to be enlarged for (2)Confirmation Dialog
        if (secondDialogEnabled) {
            positiveButton.setTextSize(buttonTextSize);
        }
        LinearLayout parent = (LinearLayout) positiveButton.getParent();    //Getting Layout for buttons
        View leftSpacer = parent.getChildAt(1); //Getting ExtraSpace in Layout
        parent.setGravity(Gravity.CENTER_HORIZONTAL);   //Setting button gravity to center
        leftSpacer.setVisibility(View.GONE);    //Eliminating extraSpace in Layout
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}


