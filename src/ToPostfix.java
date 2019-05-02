import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ToPostfix extends Stack<String>{
	private Pattern log_p = Pattern.compile("log\\([^()]*\\)");			//log()������ ���ڷ� �ٲ۴�.
	private Pattern tan_p = Pattern.compile("tan\\([^()]*\\)");			//tan()
	private Pattern sin_p = Pattern.compile("sin\\([^()]*\\)");			//sin()
	private Pattern cos_p = Pattern.compile("cos\\([^()]*\\)");			//cos()
	private Pattern pow_p = Pattern.compile("pow\\([^()]*\\)");			//sqrt()
	private Pattern sqrt_p = Pattern.compile("sqrt\\([^()]*\\)");		//pow()
	private Pattern exp_p = Pattern.compile("exp\\([^()]*\\)");			//exp()
	private Pattern pi_p = Pattern.compile("pi");						//pi -> 3.141592
	//	����ǥ�������� �Լ����� ã�Ƴ����̴�.
	private String expression;//�Լ�ó���� ������
	private String origin_exp;//������
	public ToPostfix(String expression) {
		this.expression = expression;
		origin_exp= expression;
	}
	private int priority(String str) {
		if(str.trim().equals("(") || str.trim().equals(")"))
			return 0;
		else if(str.trim().equals("*") || str.trim().equals("/"))
			return 1;
		else if(str.trim().equals("+") || str.trim().equals("-"))
			return 2;
		else
			return -1;
	}//�������� �켱������ ���ϴ� �޼ҵ� ���ڴ� ���� ���̹Ƿ� -1ó��.
	private void ftnToDigit() {//����ǥ�������� ó���� �Լ����� ã�� �޼ҵ�
		expression = expression.replaceAll(" ", "");
		Matcher pi_m = pi_p.matcher(expression);
		Matcher log_m = log_p.matcher(expression);
		Matcher tan_m = tan_p.matcher(expression);
		Matcher sin_m = sin_p.matcher(expression);
		Matcher cos_m = cos_p.matcher(expression);
		Matcher pow_m = pow_p.matcher(expression);
		Matcher sqrt_m = sqrt_p.matcher(expression);
		Matcher exp_m = exp_p.matcher(expression);
		Matcher[] match = {pi_m, log_m, tan_m, sin_m, cos_m, pow_m, sqrt_m, exp_m};
		StringCalculator calc = new StringCalculator();
		for(int i = 0; i < match.length; i++) {
			switch(i) {
			case 0: try {//pi to math.PI
				expression = match[i].replaceAll(Double.toString(Math.PI));
			}catch(Exception e) {System.out.println(e.toString() + "in match_PI");}
			break;
			case 1: try {//log() to math.log
				match[i] = log_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.log(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = log_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_log");}
			break;
			case 2: try {//sin() to Math.sin
				match[i] = tan_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.tan(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = tan_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_tan");}
			break;
			case 3: try {//sin() to Math.sin
				match[i] = sin_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.sin(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = sin_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_sin");}
			break;
			case 4: try {//cos() to Math.cos
				match[i] = cos_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.cos(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = cos_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_cos");}
			break;
			case 5: try {//pow() to Math.pow
				match[i] = pow_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit1, digit2;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit1 = temp.substring(temp.indexOf('(') + 1, temp.indexOf(','));
					digit2 = temp.substring(temp.indexOf(',') + 1, temp.indexOf(')'));
					String result = Double.toString(Math.pow(Double.parseDouble(digit1),Double.parseDouble(digit2)));
					expression = expression.substring(0,m_start) + result + expression.substring(m_end,expression.length());
					match[i] = pow_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_pow");}
			break;
			case 6: try {//sqrt() to Math.sqrt
				match[i] = sqrt_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.sqrt(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = sqrt_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_sqrt");}
			break;
			case 7: try {//exp() to Math.exp
				match[i] = exp_p.matcher(expression);
				while(match[i].find()) {
					String temp = match[i].group(0);
					String digit;
					int m_start = match[i].start();
					int m_end = match[i].end();
					digit = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
					calc.setExpression(digit);
					double changed = Double.parseDouble(calc.calculateStr());
					digit = Double.toString(Math.exp(changed));
					expression = expression.substring(0,m_start) + digit + expression.substring(m_end,expression.length());
					match[i] = exp_p.matcher(expression);
				}
			}catch(Exception e) {System.out.println(e.toString() + "in match_exp");}
			break;
			default:
				System.out.println("error occured in  default");
			}

		}
	}
	private String changeMinus(String str) {//(/-)(*-)(+-)��츦 �ذ��ϱ� ���� ���
		Pattern divisorMinus = Pattern.compile("(\\/\\-[0-9]*\\.[0-9]*|\\/\\-[0-9]*)");
		Pattern multipleMinus = Pattern.compile("(\\*\\-[0-9]*\\.[0-9]*|\\*\\-[0-9]*)");
		Matcher DM_m = divisorMinus.matcher(str);
		Matcher	MM_m = multipleMinus.matcher(str);
		try {
			while(DM_m.find()) {
				int st_i = DM_m.start();
				int en_i = DM_m.end();
				str = str.substring(0, st_i+1) + "(0.0" +
						str.substring(st_i+1, en_i) + ")" + str.substring(en_i, str.length());
				// 2/-7�� ��� 2/(0-7)�� ��ȯ
				DM_m = divisorMinus.matcher(str);
			}
		}catch(Exception e) {System.out.println(e.toString() + "in /- to -");}
		try {
			while(MM_m.find()) {
				int st_i = MM_m.start();
				int en_i = MM_m.end();
				str = str.substring(0, st_i+1) + "(0.0" +
						str.substring(st_i+1, en_i) + ")" + str.substring(en_i, str.length());
				MM_m = multipleMinus.matcher(str);
			}
		}catch(Exception e) {System.out.println(e.toString() + "in *- to -");}
		return str;

	}
	private String[] changePostfix(String str){
		str = str.replaceAll("\\+-", "-");
		str = changeMinus(str);
		str = str.replaceAll("\\(", "( ");
		str = str.replaceAll("\\)", " )");
		str = str.replaceAll("\\-", " - ");
		str = str.replaceAll("\\+", " + ");
		str = str.replaceAll("\\/", " / ");
		str = str.replaceAll("\\*", " * ");
		//�������� ���� �����ڸ� �и��ϱ����� �����߰� �� �и� 
		String[] split_str = str.split(" ");
		ArrayList<String> postFixArray = new ArrayList<String>();
		try {
			for(int i = 0; i < split_str.length; i++) {
				if(priority(split_str[i]) == -1)//������ ��� �ٷ� �߰�
					postFixArray.add(split_str[i]);		
				else if(priority(split_str[i]) == 1 |priority(split_str[i]) == 2) {//*�Ǵ� / �Ǵ� - �Ǵ� +
					while(!isEmpty() && priority(split_str[i]) >= priority(peek()) && priority(peek()) != 0) 
						postFixArray.add(pop());
						//������� �ʰų� �μ������� �����ڰ� �������
					push(split_str[i]);//�� ���ÿ� ����
				}
				else if(split_str[i].trim().equals("("))
					push(split_str[i]);//(����� �ٷ� ����
				else {//)��ȣ�� ������� (���� ��� �ҷ�����
					String topStack = pop();
					while(!topStack.trim().equals("(")) {
						postFixArray.add(topStack);
						topStack = pop();
					}
				}

			}
			while(!isEmpty()) {//���ÿ� �����ִ� ���� ��� ��������
				postFixArray.add(pop());
			}
			String[] changedPostfix = postFixArray.toArray(new String[postFixArray.size()]);//arraylist to array
			System.out.println(Arrays.deepToString(changedPostfix));
			return changedPostfix;
		}
		catch(Exception e) {//error occured
			System.out.println("�߸� �Է��ϼ̽��ϴ�. �Է°�: [" + origin_exp + "]�� Ȯ���� �ּ���.");
			return null;
		}
	}
	public String[] returnChanged() {
		ftnToDigit();//�Լ�ó��

		if(expression.contains("-Infinity")) {
			String negInf[] = {"-Infinity"};
			return negInf;
		}//-Infinity ������ ��� �׳� ����� -Infinity
		else if (expression.contains("Infinity")) {
			String posInf[] = {"Infinity"};
			return posInf;
		}//Infinity ������ ��� �׳� ����� Infinity
		return changePostfix(expression);//����ǥ��� ��ȯ�� array ��ȯ
	}

}
