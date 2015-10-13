package com.dmbteam.catalogapp.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmbteam.catalogapp.MainActivity;
import com.dmbteam.catalogapp.R;
import com.dmbteam.catalogapp.cart.CartItem;
import com.dmbteam.catalogapp.cart.CartManager;
import com.dmbteam.catalogapp.cmn.Product;
import com.dmbteam.catalogapp.fragment.FragmentCartPreview;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.Utils;

/**
 * The Class CheckoutPreviewAdapter.
 */
public class CheckoutPreviewAdapter extends
		RecyclerView.Adapter<CheckoutPreviewAdapter.ViewHolder> {

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder extends RecyclerView.ViewHolder {

		/** The product container. */
		private View productContainer;
		
		/** The title. */
		private TextView title;
		
		/** The qty. */
		private TextView qty;
		
		/** The price. */
		private TextView price;

		/** The cart preview container. */
		private View cartPreviewContainer;
		
		/** The cart preview delimiter. */
		private View cartPreviewDelimiter;
		
		/** The cart preview total. */
		private TextView cartPreviewTotal;
		
		/** The cart preview name. */
		private EditText cartPreviewName;
		
		/** The cart preview mail. */
		private EditText cartPreviewMail;
		
		/** The cart preview phone. */
		private EditText cartPreviewPhone;
		
		/** The cart preview comment. */
		private EditText cartPreviewComment;
		
		/** The cart preview sent. */
		private TextView cartPreviewSent;

		/**
		 * Instantiates a new view holder.
		 *
		 * @param itemView the item view
		 */
		public ViewHolder(View itemView) {
			super(itemView);

			productContainer = itemView
					.findViewById(R.id.list_item_filter_product_container);
			title = (TextView) itemView
					.findViewById(R.id.list_item_filter_title);
			qty = (TextView) itemView.findViewById(R.id.list_item_filter_qty);
			price = (TextView) itemView
					.findViewById(R.id.list_item_filter_price);

			cartPreviewContainer = itemView
					.findViewById(R.id.list_item_filter_cart_preview);
			cartPreviewDelimiter = itemView
					.findViewById(R.id.list_item_filter_cart_delimiter);
			cartPreviewTotal = (TextView) itemView
					.findViewById(R.id.list_item_filter_total_value);
			cartPreviewName = (EditText) itemView
					.findViewById(R.id.list_item_filter_full_name);
			cartPreviewMail = (EditText) itemView
					.findViewById(R.id.list_item_filter_mail);
			cartPreviewPhone = (EditText) itemView
					.findViewById(R.id.list_item_filter_phone);
			cartPreviewComment = (EditText) itemView
					.findViewById(R.id.list_item_filter_comment);
			cartPreviewSent = (TextView) itemView
					.findViewById(R.id.list_item_filter_sent);
		}
	}

	/** The Context. */
	private Context mContext;
	
	/** The Inflater. */
	private LayoutInflater mInflater;
	
	/** The Adapter data. */
	private List<CartItem> mAdapterData;
	
	/** The is in cart context. */
	private boolean isInCartContext;

	/**
	 * Instantiates a new checkout preview adapter.
	 *
	 * @param context the context
	 * @param isInCartContext the is in cart context
	 */
	public CheckoutPreviewAdapter(Context context, boolean isInCartContext) {
		this(context);
		this.isInCartContext = isInCartContext;
		// this.mAdapterData.add(new CartItem(null));
	}

	/**
	 * Instantiates a new checkout preview adapter.
	 *
	 * @param context the context
	 */
	public CheckoutPreviewAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(this.mContext);
		this.mAdapterData = CartManager.getInstance().getAllItems();
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#getItemCount()
	 */
	@Override
	public int getItemCount() {

		return mAdapterData.size();
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(android.view.ViewGroup, int)
	 */
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View convertView = mInflater.inflate(
				R.layout.list_item_checkout_preview, parent, false);
		ViewHolder holder = new ViewHolder(convertView);

		return holder;
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
	 */
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		CartItem currentCartItem = mAdapterData.get(position);
		Product currentProduct = mAdapterData.get(position).getProduct();

		holder.cartPreviewContainer.setVisibility(View.GONE);

		holder.title.setText(currentCartItem.getProduct().getTitle());
		holder.qty.setText(currentCartItem.getQuantity() + "x");

		double price = 0.0;

		if (currentProduct.getDiscount() > 0.0) {
			price = (currentProduct
					.getDiscountedPrice());
		} else {
			price = (currentProduct.getPrice());
		}

		holder.price.setText(Utils.mFormatter.format(price)
				+ AppSettings.CURRENCY);

		if (isInCartContext && position == getItemCount() - 1) {
			holder.cartPreviewTotal.setText(CartManager.getInstance()
					.getTotal() + AppSettings.CURRENCY);

			holder.cartPreviewContainer.setVisibility(View.VISIBLE);

			int abHeight = ((MainActivity) mContext).getSupportActionBar()
					.getHeight();

			int displayHeight = mContext.getResources().getDisplayMetrics().heightPixels
					- abHeight;

			int cartContainerHeight = Utils.dipsToPixels(mContext, 470);
			int itemsHeight = Utils.dipsToPixels(mContext, 31) * getItemCount();

			int marginDelimiter = displayHeight
					- (cartContainerHeight + itemsHeight);

			if (marginDelimiter > 0) {
				holder.cartPreviewDelimiter
						.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								marginDelimiter));
			}

			holder.cartPreviewSent.setOnClickListener(new SentClickListener(
					holder));

			((MainActivity) mContext).getAbSent().setOnClickListener(
					new SentClickListener(holder));
		}

	}

	/**
	 * The listener interface for receiving sentClick events.
	 * The class that is interested in processing a sentClick
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSentClickListener<code> method. When
	 * the sentClick event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SentClickEvent
	 */
	private class SentClickListener implements OnClickListener {

		/** The holder. */
		ViewHolder holder;

		/**
		 * Instantiates a new sent click listener.
		 *
		 * @param holder the holder
		 */
		public SentClickListener(ViewHolder holder) {
			super();
			this.holder = holder;
		}

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {

			boolean result = false;

			if (holder.cartPreviewName.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewName
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewName.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewName
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewName.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_ok, 0);
			}

			if (holder.cartPreviewMail.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewMail
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewMail.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewMail
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewMail.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_ok, 0);
			}

			if (holder.cartPreviewPhone.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewPhone
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewPhone
						.setCompoundDrawablesWithIntrinsicBounds(0, 0,
								R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewPhone
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewPhone
						.setCompoundDrawablesWithIntrinsicBounds(0, 0,
								R.drawable.ic_checkout_input_ok, 0);

			}

			if (result) {
				Toast.makeText(mContext,
						mContext.getString(R.string.mandatory_fields),
						Toast.LENGTH_LONG).show();

			} else {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
						Uri.fromParts("mailto", AppSettings.MAIL, null));

				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order");
				emailIntent
						.putExtra(
								Intent.EXTRA_TEXT,
								CartManager.getInstance()
										.generateStringForMail(
												mContext,
												holder.cartPreviewName
														.getText().toString(),
												holder.cartPreviewMail
														.getText().toString(),
												holder.cartPreviewPhone
														.getText().toString(),
												holder.cartPreviewComment
														.getText().toString()));

				((MainActivity) mContext).startActivityForResult(
						Intent.createChooser(emailIntent, "Send email..."),
						FragmentCartPreview.MAIL_REQUEST_CODE);

			}

		}

	}

}
