package AllinOne;

import java.io.IOException;
import java.security.Timestamp;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ExchangeParser  {
	private float usdValue;
	private float eurValue;
	private float jpyValue;
	private float cadValue;
	String url = "http://info.finance.naver.com/marketindex/exchangeList.nhn";

	
	public float getUsdValue() {
		return usdValue;
	}
	
	public float getEurValue() {
		return eurValue;
	}
	
	public float getJpyValue() {
		return jpyValue;
	}
	
	public float getCadValue() {
		return cadValue;
	}
	
	public ExchangeParser() throws IOException {
		String html;
		String text;

		Document usdx = Jsoup.connect(url).get();
		
		Connection.Response response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		Document usdx2 = response.parse();
		html = usdx2.html();
		text = usdx2.text();
		
		String usd = text.substring(94, 102);
		String eur = text.substring(154, 162);
		String jpy = text.substring(213, 219);
		String cad = text.substring(486, 492);
		usdValue = stringtofloat(usd);
		eurValue = stringtofloat(eur);
		jpyValue = stringtofloat(jpy);
		cadValue = stringtofloat(cad);
		
	}
	float stringtofloat(String x) {
		x = x.replace(",","");
		x = x.replace(".","");
		int y = Integer.parseInt(x)*100;
		float value = y*0.0001f;
		return value;
	}
	


}
