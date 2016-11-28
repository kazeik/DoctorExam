package com.dteviot.epubviewer;

import java.io.StringWriter;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xmlpull.v1.XmlSerializer;

import com.dteviot.epubviewer.XmlFilter.InlineImageElementFilter;
import com.dteviot.epubviewer.XmlFilter.RemoveSvgElementFilter;
import com.dteviot.epubviewer.XmlFilter.StyleSheetElementFilter;
import com.dteviot.epubviewer.XmlFilter.XmlSerializerToXmlFilterAdapter;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 * EpubWebView for use with Android 2.3.
 * The 2.3 web view can't handle links in HTML
 * So we need to rebuild the HTML converting
 * any links into in-line data
 */
public class EpubWebView23 extends EpubWebView {

	public EpubWebView23(Context context) {
		super(context);
	}

	public EpubWebView23(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * Do any Web settings specific to the derived class
	 */
	protected void addWebSettings(WebSettings settings) {
		// nothing to do
	}

	/*
	 * @ return Android version appropriate WebViewClient
	 */
	@Override
	protected WebViewClient createWebViewClient() {
		return new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				onPageLoaded();
			}
		};
	}

	/*
	 * Android 2.3 can't fetch linked resources in XHTML, so add use SAX
	 * pipeline to build new XHTML adding images as Data URIs and putting style
	 * sheets in-line
	 */
	@Override
	protected void LoadUri(Uri uri) {
		// default to a fail message
		String xhtml = "<html><head /><body>Failed to convert XHTML</body></html>";
		try {
			// build SAX pipeline to convert XHTML
			// Chain is Reader -> stylesheetFilter -> imageFilter -> Serializer
			XMLFilterImpl stylesheetFilter = new StyleSheetElementFilter(uri,
					getBook());
			XMLFilterImpl svgFilter = new RemoveSvgElementFilter();
			XMLFilterImpl imageFilter = new InlineImageElementFilter(uri,
					getBook());
			svgFilter.setParent(stylesheetFilter);
			imageFilter.setParent(svgFilter);

			StringWriter writer = new StringWriter();
			XmlSerializer serializer = android.util.Xml.newSerializer();
			XmlSerializerToXmlFilterAdapter serializerFilter = new XmlSerializerToXmlFilterAdapter(
					serializer);
			serializerFilter.setParent(imageFilter);

			// convert the XHTML
			serializer.setOutput(writer);
			XmlUtil.parseXmlResource(getBook().fetch(uri).getData(),
					stylesheetFilter, serializerFilter);
			xhtml = writer.toString();
		} catch (Exception e) {
			Log.e(Globals.TAG, "Error generating XML ", e);
			// TODO construct error message HTML
		}
		Log.e("tag", uri.toString() + " | xhtml = " + xhtml);
		// get WebView to show the XHTML
		loadDataWithBaseURL(uri.toString(), xhtml, "application/xhtml+xml",
				"UTF-8", null);
	}

	public void addCSS(String[] settings) {
		// CSS
		String css = "<style type=\"text/css\">\n";

		if (!settings[0].isEmpty()) {
			css = css + "body{color:" + settings[0] + ";}";
			css = css + "a:link{color:" + settings[0] + ";}";
		}

		if (!settings[1].isEmpty())
			css = css + "body {background-color:" + settings[1] + ";}";

		if (!settings[2].isEmpty())
			css = css + "p{font-family:" + settings[2] + ";}";

		if (!settings[3].isEmpty())
			css = css + "p{\n\tfont-size:" + settings[3] + "%\n}\n";

		if (!settings[4].isEmpty())
			css = css + "p{line-height:" + settings[4] + "em;}";

		if (!settings[5].isEmpty())
			css = css + "p{text-align:" + settings[5] + ";}";

		if (!settings[6].isEmpty())
			css = css + "body{margin-left:" + settings[6] + "%;}";

		if (!settings[7].isEmpty())
			css = css + "body{margin-right:" + settings[7] + "%;}";

		css = css + "</style>";

		// for (int i = 0; i < spineElementPaths.length; i++) {
		// String path = spineElementPaths[i].replace("file:///", "");
		// String source = readPage(path);
		//
		// source = source.replace(actualCSS + "</head>", css + "</head>");
		//
		// writePage(path, source);
		// }
		// actualCSS = css;

	}
}
