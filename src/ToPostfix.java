import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ToPostfix extends Stack<String>{
	private Pattern log_p = Pattern.compile("log\\([^()]*\\)");			//log()형식을 숫자로 바꾼다.
	private Pattern tan_p = Pattern.compile("tan\\([^()]*\\)");			//tan()
	private Pattern sin_p = Pattern.compile("sin\\([^()]*\\)");			//sin()
	private Pattern cos_p = Pattern.compile("cos\\([^()]*\\)");			//cos()
	private Pattern pow_p = Pattern.compile("pow\\([^()]*\\)");			//sqrt()
	private Pattern sqrt_p = Pattern.compile("sqrt\\([^()]*\\)");		//pow()
	private Pattern exp_p = Pattern.compile("exp\\([^()]*\\)");			//exp()
	private Pattern pi_p = Pattern.compile("pi");						//pi -> 3.141592
	//	정규표현식으로 함수들을 찾아낼거이다.
	private String expression;//함수처리된 원래식
	private String origin_exp;//원래식
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
	}//연산자의 우선순위를 정하는 메소드 숫자는 순위 외이므로 -1처리.
	private void ftnToDigit() {//정규표현식으로 처리한 함수들을 찾는 메소드
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
	private String changeMinus(String str) {//(/-)(*-)(+-)경우를 해결하기 위한 방법
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
				// 2/-7의 경우 2/(0-7)로 변환
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
		//공백으로 수와 연산자를 분리하기위해 공백추가 후 분리 
		String[] split_str = str.split(" ");
		ArrayList<String> postFixArray = new ArrayList<String>();
		try {
			for(int i = 0; i < split_str.length; i++) {
				if(priority(split_str[i]) == -1)//숫자일 경우 바로 추가
					postFixArray.add(split_str[i]);		
				else if(priority(split_str[i]) == 1 |priority(split_str[i]) == 2) {//*또는 / 또는 - 또는 +
					while(!isEmpty() && priority(split_str[i]) >= priority(peek()) && priority(peek()) != 0) 
						postFixArray.add(pop());
						//비어있지 않거나 두선순위의 연산자가 있을경우
					push(split_str[i]);//값 스택에 저장
				}
				else if(split_str[i].trim().equals("("))
					push(split_str[i]);//(등장시 바로 스택
				else {//)괄호가 닫힐경우 (까지 모두 불러오기
					String topStack = pop();
					while(!topStack.trim().equals("(")) {
						postFixArray.add(topStack);
						topStack = pop();
					}
				}

			}
			while(!isEmpty()) {//스택에 남아있는 정보 모두 꺼내오기
				postFixArray.add(pop());
			}
			String[] changedPostfix = postFixArray.toArray(new String[postFixArray.size()]);//arraylist to array
			System.out.println(Arrays.deepToString(changedPostfix));
			return changedPostfix;
		}
		catch(Exception e) {//error occured
			System.out.println("잘못 입력하셨습니다. 입력값: [" + origin_exp + "]을 확인해 주세요.");
			return null;
		}
	}
	public String[] returnChanged() {
		ftnToDigit();//함수처리

		if(expression.contains("-Infinity")) {
			String negInf[] = {"-Infinity"};
			return negInf;
		}//-Infinity 포함할 경우 그냥 결과는 -Infinity
		else if (expression.contains("Infinity")) {
			String posInf[] = {"Infinity"};
			return posInf;
		}//Infinity 포함할 경우 그냥 결과는 Infinity
		return changePostfix(expression);//후위표기로 변환된 array 반환
	}

}
