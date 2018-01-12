package AllinOne;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JPanel implements Runnable {

private Thread thread;
private JLabel label;
public Timer() {
	label = new JLabel();
	label.setFont(new Font("PLAIN", Font.ITALIC, 45));
	if(thread == null) {
		thread = new Thread(this);
		thread.start();
	}
	add(label);
	
}

@Override
public void run() {
	while(true) {
		Calendar cal = Calendar.getInstance(); //getInstance()로 현재시간 호출
		
		StringBuffer now = new StringBuffer();
		now.append(cal.get(Calendar.MONTH)+1);
		now.append("월 ");
		now.append(cal.get(Calendar.DATE));
		now.append("일 ");
		now.append(cal.get(Calendar.HOUR_OF_DAY));
		now.append(":");
		now.append(cal.get(Calendar.MINUTE));
		now.append(":");
		now.append(cal.get(Calendar.SECOND));
		label.setText(now.toString());
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
}
}