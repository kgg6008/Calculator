import java.util.Arrays;
import java.util.Stack;

class StringCalculator{
	private String expression;//수식
	private String[]postFix_exp;//후위표현식
	public StringCalculator() {}
	public void setExpression(String expression) {
		this.expression = expression;
		ToPostfix toPostfix = new ToPostfix(expression);
		postFix_exp = toPostfix.returnChanged();//수식을 후위표현식으로
	}
	public String calculateStr() {//계산결과 return하는 메소드
		try {
			if (postFix_exp.length == 1) {
				return postFix_exp[0];
			}//(+-)Infinity를 받는 경우 그대로 반환
		}catch(NullPointerException npe) {
			return null;//오류로 null을 받았을 경우 null반환
		}
		double result = 0.0;
		try {
//			System.out.println(Arrays.toString(postFix_exp));
			Stack<Double>digit= new Stack<Double>();
			for(int i = 0; i < postFix_exp.length; i++) {
				try {
					double number = Double.parseDouble(postFix_exp[i]);
					digit.push(number);//숫자일 경우 stack에 집어넣는다
				}catch(NumberFormatException NE) {//숫자가 아닐 경우
					double tempResult = 0.0;	//임시 저장
					try {
						char operator = postFix_exp[i].trim().charAt(0);
						double topStack = digit.pop();//맨 위의 값
						double nextTopStack = digit.pop();//차위의 값
						switch(operator) {
						case '+': tempResult = nextTopStack + topStack;break;
						case '-': tempResult = nextTopStack - topStack;break;
						case '*': tempResult = nextTopStack * topStack;break;
						case '/': tempResult = nextTopStack / topStack;break;
						}//계산
						digit.push(tempResult);//계산 결과를 stack으로
					}catch(java.lang.StringIndexOutOfBoundsException sie) {
						System.out.println("잘못 입력하셨습니다. 입력값: [" + expression + "]을 확인해 주세요.");
					}//연산자를 중복 입력한 경우 (1***2)

				}catch(Exception e) {
					System.out.println(e.toString() + "in StringCalculator.calculateStr");
				}
			}
			result = digit.pop();//최종 결과는 stack의 마지막값
			return Double.toString(result);//결과 반환
		}catch(java.util.EmptyStackException ESE) {String error = "에러 발생: " + expression + "확인해 주세요.";
		return error;//에러 발생
		}
	}	
}
