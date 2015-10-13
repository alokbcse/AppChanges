package com.dmbteam.catalogapp.adapter;


import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dmbteam.catalogapp.R;
import com.dmbteam.catalogapp.cmn.Product;
import com.dmbteam.catalogapp.util.ImageOptionsBuilder;
import com.dmbteam.catalogapp.util.ThemeManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.IconPagerAdapter;

public class ItemsPhotoPagerAdapter extends PagerAdapter implements IconPagerAdapter{
	
	/** The Context. */
	private Context mContext;

	/** The Inflater. */
	private LayoutInflater mInflater;

	/** The Adapter data. */
	private List<String> mAdapterData;

	/** The Display image options. */
	private DisplayImageOptions mDisplayImageOptions;

	/** The Image loader. */
	private ImageLoader mImageLoader;
	
	public ItemsPhotoPagerAdapter(Context context, List<String> adapterData) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(this.mContext);

		this.mAdapterData = adapterData;

		this.mDisplayImageOptions = ImageOptionsBuilder
				.buildGeneralImageOptions(false, R.drawable.home_nexus9);
		this.mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getIconResId(int index) {
		int pagerIconAttr = ThemeManager.getIdForSpecificAttribute(mContext,
				R.attr.pager_indicator_icon);

		return pagerIconAttr;
	}

	@Override
	public int getCount() {
		return mAdapterData.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View mainLayout = mInflater.inflate(R.layout.product_image, null);
		
		ImageView mainImageView = (ImageView) mainLayout
				.findViewById(R.id.pager_item_product_image);

		if (Product.isNetworkResource(mAdapterData.get(position))) {
			mImageLoader.displayImage(mAdapterData.get(position),
					mainImageView, mDisplayImageOptions);
		} else {
			mainImageView.setImageDrawable(mContext.getResources().getDrawable(
					Product.getDrawableId(mContext, mAdapterData.get(position))));
		}
		
		container.addView(mainLayout, 0);

		return mainLayout;
	}
	
	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((FrameLayout) view);
	}

}
