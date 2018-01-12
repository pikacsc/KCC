package AllinOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainCal extends JFrame {
	float usd;
	float jpy;
	float eur;
	String versionNumb = "1.1";
	DTO dto = new DTO();
	Timer timer = new Timer();

	class ExchangePanel extends JPanel {
		public ExchangePanel(JLabel x, JLabel y) {
			setLayout(new GridLayout(1, 2));
			y.setFont(new Font("PLAIN", Font.BOLD, 40));
			add(x);
			add(y);
		}
	}

	class ExLabel extends JLabel {
		public ExLabel(String x) {
			setText(x);
			setOpaque(true);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			setHorizontalAlignment(this.CENTER);
			setFont(new Font("PLAIN", Font.ITALIC, 40));
		}
	}

	class ExchangeView extends JPanel {
		public ExchangeView() throws IOException {

			ExchangeParser exc = new ExchangeParser();
			dto.setUSD((int) exc.getUsdValue());
			dto.setJPY((int) exc.getJpyValue());
			dto.setEUR((int) exc.getEurValue());
			setLayout(new GridLayout(4, 1));
			setBackground(Color.BLUE);
			JPanel exLabelPanel = new JPanel();
			exLabelPanel.setLayout(new GridLayout(1, 2));
			ExLabel exLabel = new ExLabel("실시간 환율 현황");
			// exLabel.setBounds(35, 5, 400, 100);
			exLabelPanel.add(exLabel);
			exLabelPanel.add(timer);
			ExchangePanel exchangeUSD = new ExchangePanel(new JLabel(new ImageIcon("images/USD.png")),
					new JLabel("1$    = " + dto.getUSD() + "원"));
			ExchangePanel exchangeJPY = new ExchangePanel(new JLabel(new ImageIcon("images/JPY.png")),
					new JLabel("100￥ = " + dto.getJPY() + "원"));
			ExchangePanel exchangeEUR = new ExchangePanel(new JLabel(new ImageIcon("images/EUR.png")),
					new JLabel("1€    = " + dto.getEUR() + "원"));
			add(exLabelPanel);
			add(exchangeUSD);
			add(exchangeJPY);
			add(exchangeEUR);
		}

	}

	public MainCal() throws IOException {
		setTitle("한국신용카드 해외결제 수수료 계산기 " + versionNumb);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 1));
		JPanel down = new JPanel();
		add(new ExchangeView());
		add(new CreditCardCal(dto));

		setVisible(true);
		setSize(900, 700);
	}

	public static void main(String[] args) throws IOException {
		new MainCal();
	}

}
