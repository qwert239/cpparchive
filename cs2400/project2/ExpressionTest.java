//
//  Name: Choi, James
//  Project: 2
//  Due: 10/21/2024
//  Course: cs-2400-01-f24
//
//  Description:
//      Uses ArrayStack which implements StackInterface to convert infix expressions to postfix expressions
//      and to evaluate the converted postfix expressions
//
public class ExpressionTest {
    public static void main(String[] args) {
        System.out.println("Expression by J. Choi\n");
        Expression exp = new Expression();
        for (String infixExpression:args) {
            String[] splitInfixExpression=infixExpression.split(" ");
            for (String i:splitInfixExpression) {
                System.out.print(i + ' ');
            }
            System.out.print("\n\t");
            String[] postfixExpression=exp.convertToPostfix(splitInfixExpression);
            int postfixEvaluationValue=exp.evaluatePostfix(postfixExpression);
            for (String i:postfixExpression) {
                System.out.print(i+' ');
            }
            System.out.print("= "+postfixEvaluationValue);
            System.out.println();
        }

    }
}
