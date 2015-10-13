package com.dmbteam.catalogapp.settings;

import static com.dmbteam.catalogapp.util.ThemesManager.APP_THEMES.*;
import com.dmbteam.catalogapp.util.ThemesManager.APP_THEMES;

/**
 * The Class AppSettings.
 */
public class AppSettings {

	/** Theme of the app 
	 * 
	 * Replace the value with any of these:
	 * 
	 * ThemeGreen, ThemeBlue, ThemeOrange, ThemePurple, ThemeDarkBlue, ThemeNeutral, ThemePink
	 * 
	 * */
	public static final APP_THEMES CURRENT_THEME = ThemeGreen;

	/** The Constant CURRENCY. */
	public static final String CURRENCY = "Rs";

	/** The Constant VAT. */
	public static final double VAT = 0.2;
	
	/** Set to true if you want prices to be with commas, set to false otherwise. */
	public static final boolean useCommaForPrices = false;

	/**
	 * The Constant XMLResourcePath. Here you can either have some network xml
	 * resource or local file in assets folder.
	 */
	public static final String XMLResourcePath = "catalog.xml";
	// public static final String XMLResourcePath =
	// "http://dmb-team.com/apps/catalog/catalog_web.xml";

	/**
	 * The Constant applicationId. This is related to parse and push
	 * notifications
	 */
	public static final String applicationId = "eF0Fw1zMplbA6adsy2V3FH1atoL3mEAsoB7sVeAD";

	/** The Constant clientKey. This is related to parse and push notifications */
	public static final String clientKey = "GpHxz1xSBkIAoX8nR03r8MCEsQR293UT1Mi8QwIK";

	/** The Constant CATALOG_NAME. */
	public static final String CATALOG_NAME = "MyValueBox";

	/** The Constant MAIL. */
	public static final String MAIL = "support@myvaluebox.com";

	/** The Constant PHONE. */
	public static final String PHONE = "+91-9412357926";

	/** The Constant SKYPE. */
	public static final String SKYPE = "prashant_cs";

	/** The Constant FACEBOOK. */
	public static final String FACEBOOK = "facebook.com/catalog";
}
