import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.json.simple.JSONObject;

public class Lotto extends JFrame implements MouseListener, KeyListener {

	MyButton mbtn1 = new MyButton("");
	MyButton mbtn2 = new MyButton("");
	MyButton mbtn3 = new MyButton("");
	MyButton mbtn4 = new MyButton("");
	MyButton mbtn5 = new MyButton("");
	MyButton mbtn6 = new MyButton("");
	MyButton mbtn7 = new MyButton("");
	
	JTextField turn_text = new JTextField();
	
	JLabel title_label = new JLabel("로또 번호 조회");
	JLabel plus_label = new JLabel("+");
	
	JLabel check_label = new JLabel("당첨 확인");
	JButton check_btn = new JButton("확인");
	JButton clear_btn = new JButton("초기화");
	
	JLabel turn_label = new JLabel("");
	JButton result_btn = new JButton("해당 회차로");
	
	JTextField num1_text = new JTextField();
	JTextField num2_text = new JTextField();
	JTextField num3_text = new JTextField();
	JTextField num4_text = new JTextField();
	JTextField num5_text = new JTextField();
	JTextField num6_text = new JTextField();
	
	JLabel my_win = new JLabel("");
	
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource)
				UIManager.put(key, f);
		}
	}
	
	public void init() {
		getContentPane().setLayout(null);
		
		title_label.setBounds(33, 26, 193, 30);
		getContentPane().add(title_label);
		
		mbtn1.setBounds(33, 66, 66, 66);
		getContentPane().add(mbtn1);
		
		mbtn2.setBounds(111, 66, 66, 66);
		getContentPane().add(mbtn2);
		
		mbtn3.setBounds(189, 66, 66, 66);
		getContentPane().add(mbtn3);
		
		mbtn4.setBounds(267, 66, 66, 66);
		getContentPane().add(mbtn4);
		
		mbtn5.setBounds(345, 66, 66, 66);
		getContentPane().add(mbtn5);
		
		mbtn6.setBounds(423, 66, 66, 66);
		getContentPane().add(mbtn6);		
		
		mbtn7.setBounds(517, 66, 66, 66);
		getContentPane().add(mbtn7);
		mbtn7.state = 5;
		mbtn7.repaint();
		
		
		turn_text.setColumns(10);
		turn_text.setBounds(180, 310, 132, 42);
		getContentPane().add(turn_text);
		
		turn_label.setBounds(55, 310, 99, 42);
		getContentPane().add(turn_label);
		
		result_btn.setBounds(335, 310, 171, 42);
		getContentPane().add(result_btn);
		
		plus_label.setBounds(497, 90, 14, 15);
		getContentPane().add(plus_label);
		
		
		num1_text.setColumns(10);
		num1_text.setBounds(43, 192, 46, 46);
		getContentPane().add(num1_text);
		
		num2_text.setColumns(10);
		num2_text.setBounds(101, 192, 46, 46);
		getContentPane().add(num2_text);
		
		num3_text.setColumns(10);
		num3_text.setBounds(159, 192, 46, 46);
		getContentPane().add(num3_text);
		
		num4_text.setColumns(10);
		num4_text.setBounds(217, 192, 46, 46);
		getContentPane().add(num4_text);

		num5_text.setColumns(10);
		num5_text.setBounds(275, 192, 46, 46);
		getContentPane().add(num5_text);
		
		num6_text.setColumns(10);
		num6_text.setBounds(335, 192, 46, 46);
		getContentPane().add(num6_text);
		

		check_btn.setBounds(393, 191, 76, 46);
		getContentPane().add(check_btn);
		
		check_label.setBounds(33, 152, 193, 30);
		getContentPane().add(check_label);
		
		clear_btn.setBounds(481, 191, 93, 46);
		getContentPane().add(clear_btn);
		
		my_win.setBounds(32, 258, 551, 42);
		getContentPane().add(my_win);
				
	}
	
	public void event() {
		result_btn.addMouseListener(this);
		turn_text.addKeyListener(this);
		check_btn.addMouseListener(this);
		clear_btn.addMouseListener(this);
	}
	
	public void printResult() {
		try {
			JSONObject jsonObj = JsonReader.urlToJSON(turn_text.getText());
			if( jsonObj == null )
			{
				turn_label.setText("회차없음");
				clearResult();
			}
			else {
				turn_label.setText(turn_text.getText()+" 회차");
				mbtn1.setText(String.valueOf((jsonObj).get("drwtNo1")));
				mbtn2.setText(String.valueOf((jsonObj).get("drwtNo2")));
				mbtn3.setText(String.valueOf((jsonObj).get("drwtNo3")));
				mbtn4.setText(String.valueOf((jsonObj).get("drwtNo4")));
				mbtn5.setText(String.valueOf((jsonObj).get("drwtNo5")));
				mbtn6.setText(String.valueOf((jsonObj).get("drwtNo6")));
				mbtn7.setText(String.valueOf((jsonObj).get("bnusNo")));				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	
	public void clearResult() {
		mbtn1.setText("");
		mbtn2.setText("");
		mbtn3.setText("");
		mbtn4.setText("");
		mbtn5.setText("");
		mbtn6.setText("");
		mbtn7.setText("");
	}
	
	public void checkNumber() {
		printResult();
		
		String myWinString = "";
		
		int count=0;
		int overlapCount=0;
		boolean num = true;
		
		String[] resultStr = {mbtn1.getText(), mbtn2.getText(), mbtn3.getText(), mbtn4.getText(), 
							  mbtn5.getText(), mbtn6.getText(), mbtn7.getText()};
		String[] numberStr = {num1_text.getText(), num2_text.getText(), num3_text.getText(), 
							  num4_text.getText(), num5_text.getText(), num6_text.getText()};
		
	try {
		
		//유효성 검사
		for(int i=0; i<numberStr.length; i++ ) {
			if((Integer.parseInt(numberStr[i])>=1) && (Integer.parseInt(numberStr[i])<=45)) {
				
				for(int j=0; j<numberStr.length; j++ ) {
					if( Integer.parseInt(numberStr[i]) == Integer.parseInt(numberStr[j])) {
						overlapCount++;
					}
				}
				if(overlapCount>1) {
					myWinString= "중복된 숫자는 입력할 수 없습니다.";
					num = false;
					System.out.println(overlapCount);
					break;
				}else {
					overlapCount =0;
				}
				
			}else {
				myWinString= "1에서 45까지의 숫자만 입력하세요.";
				num = false;
				break;
			}
		}
		

		if(num == true) {
			
			// 맞춘 숫자 카운트
			for ( int i=0; i<6; i++) {
				for ( int j=0; j<6; j++) {
					if((Integer.parseInt(resultStr[i]))==(Integer.parseInt(numberStr[j]))) {
						myWinString += resultStr[i]+", ";
						count++;
						break;
					}
				}
			}
			
			// 마지막 반점 없애기
			myWinString=myWinString.substring(0, myWinString.length()-2);
			
			loops:
				switch(count) {
				case 0:
					myWinString += "일치하는 번호가 없습니다.";
					break;
				case 3:
					myWinString += " : "+count+"개 일치 -> 5등입니다!";
					break;
				case 4:
					myWinString += " : "+count+"개 일치 -> 4등입니다!";
					break;
				case 5:
					for ( int i=0; i<6; i++) {
						if((Integer.parseInt(resultStr[6]))==(Integer.parseInt(numberStr[i]))) {
							myWinString += ", 보너스 "+resultStr[6]+"번 포함 "+
											(count+1)+"개 일치 -> 2등입니다! ";
							break loops;
						}
					}
					myWinString += " : "+count+"개 일치 -> 3등입니다!";
					break;
				case 6:
					myWinString += " : "+count+"개 일치 -> 1등입니다!";
					break;
				default:
					myWinString += " : "+count+"개 일치";
					break;
				}
			}
	
		} catch (Exception e) {
			myWinString= "오류. 다시 확인하세요.";
		}
		
		//결과 출력
		my_win.setText(myWinString);
	}

	public void clearNumber() {
		num1_text.setText("");
		num2_text.setText("");
		num3_text.setText("");
		num4_text.setText("");
		num5_text.setText("");
		num6_text.setText("");
		my_win.setText("");
	}
	
	Lotto() throws Exception {
		super ("로또 번호 조회");
		
		init();
		event();
		
		setVisible(true); // 화면에 보여주는 메서드
		setSize(633, 406); // 화면 크기 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 화면 닫기면 프로그램 종료
	}
	
	public static void main(String[] args) throws Exception{
		String fName="BMDOHYEON_ttf.ttf";
		Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File(fName));
		f1 = f1.deriveFont(20f); // 글자 크기 20으로 지정, float 형식이라서 f 입력
		setUIFont(new FontUIResource(f1)); // 전체 글꼴 지정
		new Lotto();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==result_btn) {
			printResult();				
		}
		if(e.getSource()==check_btn) {
			checkNumber();
		}
		if(e.getSource()==clear_btn) {
			clearNumber();				
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	printResult();
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}