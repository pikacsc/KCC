package AllinOne;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//1.상품가격 입력
//2.브랜드 선택
//3.카드사 선택
//4.환율 선택
//5.계산버튼 클릭
//6.계산출력
public class CreditCardCal extends JPanel {
	DTO dto;
	int x;
	int y;
	int z;
	int goodsPrice;
	float brandCommission;
	float cardCommission;
	int sumAllCommission;
	int finalResult;
	JLabel exchangeMarkLabel = new JLabel("$");
	String exchangeMark[] = {"$","￥","€"};

	public CreditCardCal(DTO dto) {
		this.dto = dto;
		setLayout(new GridLayout(1, 4));
		add(new BrandPart());
		add(new KoreanCardPart());
		add(new ExchangePart());
		add(new Calculation());
	}

	

	class BrandPart extends JPanel implements ItemListener {
		ImageIcon brandImage[] = { new ImageIcon("images/visa.png"), new ImageIcon("images/master.png"),
				new ImageIcon("images/amex.png"), new ImageIcon("images/jcb.png"), new ImageIcon("images/discover.png"),
				new ImageIcon("images/bc global.png"), new ImageIcon("images/unionpay.png"),
				new ImageIcon("images/urs.png") };
		JRadioButton visa = new JRadioButton("VISA", brandImage[0], true);
		JRadioButton master = new JRadioButton("Master", brandImage[1]);
		JRadioButton amex = new JRadioButton("Amex", brandImage[2]);
		JRadioButton jcb = new JRadioButton("JCB", brandImage[3]);
		JRadioButton discover = new JRadioButton("Discover", brandImage[4]);
		JRadioButton bcglobal = new JRadioButton("BC Global", brandImage[5]);
		JRadioButton union = new JRadioButton("UnionPay", brandImage[6]);
		JRadioButton urs = new JRadioButton("URS", brandImage[7]);
		JRadioButton etc = new JRadioButton("기타");
		ButtonGroup g = new ButtonGroup();
		JRadioButton brands[] = { visa, master, amex, jcb, discover, bcglobal, union, urs, etc };

		BrandPart() {
			setLayout(new GridLayout(10, 1));
			add(new JLabel("브랜드 선택"));

			for (int i = 0; i < brands.length; i++) {
				g.add(brands[i]);
				brands[i].setBorderPainted(true);
				brands[i].addItemListener(this);
				add(brands[i]);
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < brands.length; i++) {
				if (brands[i].isSelected() == true) {
					x = i;
					break;
				}
			}
		}

	}

	class KoreanCardPart extends JPanel implements ItemListener {
		JRadioButton shin = new JRadioButton("신한카드");
		JRadioButton hyon = new JRadioButton("현대카드");
		JRadioButton keb = new JRadioButton("외환카드");
		JRadioButton sam = new JRadioButton("삼성카드");
		JRadioButton lotte = new JRadioButton("롯데카드");
		JRadioButton kook = new JRadioButton("국민카드", true);
		JRadioButton city = new JRadioButton("씨티카드");
		JRadioButton hana = new JRadioButton("하나카드");
		JRadioButton woori = new JRadioButton("우리카드");
		JRadioButton nong = new JRadioButton("농협카드");
		JRadioButton bc = new JRadioButton("BC 글로벌카드");
		JRadioButton etc = new JRadioButton("기타");

		ButtonGroup g = new ButtonGroup();

		JRadioButton cards[] = { shin, hyon, keb, sam, lotte, kook, city, hana, woori, nong, bc, etc };

		KoreanCardPart() {
			setLayout(new GridLayout(13, 1));
			add(new JLabel("카드사 선택"));
			for (int i = 0; i < cards.length; i++) {
				g.add(cards[i]);
				cards[i].addItemListener(this);
				add(cards[i]);
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < cards.length; i++) {
				if (cards[i].isSelected() == true) {
					y = i;
					break;
				}
			}
		}
	}

	class ExchangePart extends JPanel implements ItemListener{
		JRadioButton usd = new JRadioButton("USD", true);
		JRadioButton jpy = new JRadioButton("JPY");
		JRadioButton eur = new JRadioButton("EURO");
		ButtonGroup g = new ButtonGroup();

		JRadioButton exchanges[] = { usd, jpy, eur};
		
		ExchangePart() {
			setLayout(new GridLayout(5, 1));
			add(new JLabel("환율 선택"));
			for (int i = 0; i < exchanges.length; i++) {
				g.add(exchanges[i]);
				exchanges[i].addItemListener(this);
				add(exchanges[i]);
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < exchanges.length; i++) {
				if (exchanges[i].isSelected() == true) {
					exchangeMarkLabel.setText(exchangeMark[i]);
					z = i;
					break;
				}
			}
		}
	}

	class Calculation extends JPanel {
		public Calculation() {
			setLayout(new BorderLayout(20, 20));
			JPanel inputPanel = new JPanel();
			inputPanel.setLayout(new FlowLayout());
			JTextField input = new JTextField(9);
			inputPanel.add(new JLabel("상품가격 입력"));
			inputPanel.add(input);
			inputPanel.add(exchangeMarkLabel);
			add(inputPanel, BorderLayout.NORTH);
			ResultPanel rp = new ResultPanel();
			add(rp, BorderLayout.CENTER);
			JButton activateCal = new JButton("계산");
			activateCal.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					goodsPrice = Integer.parseInt(input.getText());
					dto.setExchange(z);
					dto.setGoodsPrice(goodsPrice);
					goodsPrice = dto.getGoodsPrice();
					dto.setBrandCommission(x);
					brandCommission = dto.getBrandCommission();
					
					dto.setCardCommission(y);
					cardCommission = dto.getCardCommission();
					
					dto.setSumAllCommission();
					sumAllCommission = dto.getSumAllCommission();
					
					dto.setFinalResult();
					finalResult = (int)dto.getFinalResult();
					rp.printLabel();
					}catch(Exception f) {
						f.printStackTrace();
					    JOptionPane.showMessageDialog(null,"숫자만 입력하세요","Message",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			});
			add(activateCal, BorderLayout.SOUTH);

		}

		class ResultPanel extends JPanel {
			JLabel resultPrint[] = {new JLabel("원화환전결과 : " + (int)goodsPrice + "원"),
					new JLabel("브랜드 수수료 : " + (int)brandCommission + "원"),
					new JLabel("카드사수수료 : " + (int)cardCommission + "원"),
					new JLabel("수수료 총합계 : " + (int)sumAllCommission + "원"),
					new JLabel("최종 청구 금액 : " + (int)finalResult + "원")};
					
			public ResultPanel() {
				setLayout(new GridLayout(6, 1, 20, 20));
				for(int i=0;i<resultPrint.length;i++) {
					add(resultPrint[i]);
				}
			}
			
			void printLabel() {
				resultPrint[0].setText("원화환전결과 : " + (int)goodsPrice + "원");
				resultPrint[1].setText("브랜드 수수료 : " + (int)brandCommission + "원");
				resultPrint[2].setText("카드사수수료 : " + (int)cardCommission + "원");
				resultPrint[3].setText("수수료 총합계 : " + (int)sumAllCommission + "원");
				resultPrint[4].setText("최종 청구 금액 : " + (int)finalResult + "원");
			}
			
			
				
			
		}
		
		
		
		
	}

}
