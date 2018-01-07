package AllinOne;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DTO {

	private int USD;
	private int JPY;
	private int EUR;
	
	
	private int exchange;
	private int goodsPrice;
	private float brandCommission;
	private float cardCommission;
	private int sumAllCommission;
	private int finalResult;

	public int getUSD() {
		return USD;
	}
	public void setUSD(int uSD) {
		USD = uSD;
	}
	public int getJPY() {
		return JPY;
	}
	public void setJPY(int jPY) {
		JPY = jPY;
	}
	public int getEUR() {
		return EUR;
	}
	public void setEUR(int eUR) {
		EUR = eUR;
	}
	public int getExchange() {
		return exchange;
	}
	public void setExchange(int x) {
		switch(x) {
			case 0:
				this.exchange = this.getUSD();
				break;
			case 1:
				this.exchange = this.getJPY();
				break;
			case 2:
				this.exchange = this.getEUR();
				break;
			default:
				this.exchange = this.getUSD();
		}
	}
	
	
	
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {  //승인금액 : 상품가격 * 환매도율
		goodsPrice = goodsPrice*exchange;
		this.goodsPrice = goodsPrice; //원화
	}
	
	
	public float getBrandCommission() {
		return brandCommission;
	}
	public void setBrandCommission(int x) {
		float brandCommission;
		switch(x) {
			case 0: 
				brandCommission = 0.011f; //visa
				break;
			case 1: 
				brandCommission = 0.01f; //master
				break;
			case 2: 
				brandCommission = 0.014f; //amex
				break;
			case 3: 
				brandCommission = 0; // JCB
				break;
			case 4:
				brandCommission = 0; //Discover
				break;
			case 5: 
				brandCommission = 0; //BC global
				break;
			case 6: 
				brandCommission = 0.008f; //unionpay
				break;
			case 7: 
				brandCommission = 0.01f; //URS
				break;
			case 8: 
				brandCommission = 0.015f; //etc
				break;
			default :
				brandCommission = 0;
				break;
		}
		brandCommission = Math.round(goodsPrice*brandCommission);
		this.brandCommission = brandCommission; //브랜드 수수료
	}
	
	
	public float getCardCommission() {
		return cardCommission;
	}
	public void setCardCommission(int x) {
		float cardCommission;
		switch(x){
		case 0: 
			cardCommission = 0.0018f; //신한
			break;
		case 1: 
			cardCommission = 0.0018f; //현대
			break;
		case 2: 
			cardCommission = 0.002f; //외환
			break;
		case 3: 
			cardCommission = 0.002f; //삼성
			break;
		case 4: 
			cardCommission = 0.002f; //롯데
			break;
		case 5: 
			cardCommission = 0.0025f; //국민
			break;
		case 6: 
			cardCommission = 0.0025f; //씨티
			break;
		case 7: 
			cardCommission = 0.003f; //하나
			break;
		case 8: 
			cardCommission = 0.0035f; //우리
			break;
		case 9: 
			cardCommission = 0.0035f; //농협
			break;
		case 10: 
			cardCommission = 0.0035f; //BC글로벌
			break;
		case 11: 
			cardCommission = 0.005f; //etc
			break;
		default:
			cardCommission = 0;
			break;	
		}
		cardCommission = Math.round(goodsPrice+brandCommission)*cardCommission;
		this.cardCommission = cardCommission;
	}
	

	public float getFinalResult() {
		return finalResult;
	}
	public void setFinalResult() {
		finalResult = goodsPrice+sumAllCommission;
		this.finalResult = finalResult;
	}
	public int getSumAllCommission() {
		return sumAllCommission;
	}
	public void setSumAllCommission() {
		sumAllCommission = (int)brandCommission+(int)cardCommission;
		this.sumAllCommission = sumAllCommission;
	}
	
	
}
