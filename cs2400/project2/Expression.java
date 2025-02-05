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
import java.util.ArrayList;
public class Expression {
    public String[] convertToPostfix(String[] infixExpression) {
        if (!validExpression(infixExpression)) throw new RuntimeException("Invalid expression");
        //array to be returned
        ArrayList<String> postfixExpression=new ArrayList<>();
        //operator stack
        StackInterface<String> operatorStack=new ArrayStack<>();
        for (String token:infixExpression) {
            switch (token) {
                // push open bracket to operator stack
                case "(": case "[": case "{": case "^":
                    operatorStack.push(token); break;
                // add to expression the operators in stack when closed bracket encountered,
                // until corresponding open bracket is found,
                // then remove open bracket from operator stack
                case ")": case "]": case "}":
                    while (!operatorStack.peek().equals("(") &&
                            !operatorStack.peek().equals("[") &&
                            !operatorStack.peek().equals("{"))
                        postfixExpression.add(operatorStack.pop());
                    operatorStack.pop(); break;
                case "*": case "/":
                    //if operator stack is not empty and not an open bracket
                    if (!operatorStack.isEmpty() && (!operatorStack.peek().equals("(") &&
                            !operatorStack.peek().equals("[") &&
                            !operatorStack.peek().equals("{"))) {
                        if (operatorStack.peek().equals("+") || operatorStack.peek().equals("-")) {
                            operatorStack.push(token);
                        } else if (operatorStack.peek().equals("*") || operatorStack.peek().equals("/")) {
                            postfixExpression.add(operatorStack.pop());
                            operatorStack.push(token);
                        }
                    } else {
                        operatorStack.push(token);
                    } break;
                case "+": case "-":
                    if (!operatorStack.isEmpty() && (!operatorStack.peek().equals("(") &&
                            !operatorStack.peek().equals("[") &&
                            !operatorStack.peek().equals("{"))) {
                            postfixExpression.add(operatorStack.pop());
                    }
                    operatorStack.push(token);
                    break;
                default:
                    postfixExpression.add(token);
                    if (!operatorStack.isEmpty()) {
                        if (operatorStack.peek().equals("^")) {
                            postfixExpression.add(operatorStack.pop());
                        }
                    }
            }
        }
        while (!operatorStack.isEmpty()) postfixExpression.add(operatorStack.pop());
        return postfixExpression.toArray(new String[postfixExpression.size()]);

    }
    public int evaluatePostfix (String[] postfixExpression) {
        StackInterface<Integer> valueStack=new ArrayStack<>();
        for (String token:postfixExpression) {
            switch (token) {
                case "+": case "-": case "*": case "/": case "^":
                    int operandTwo=valueStack.pop();
                    int operandOne=valueStack.pop();
                    int result;
                    if (token.equals("+")) result=operandOne+operandTwo;
                    else if (token.equals("-")) result=operandOne-operandTwo;
                    else if (token.equals("*")) result=operandOne*operandTwo;
                    else if (token.equals("/")) result=operandOne/operandTwo;
                    else result=(int)Math.pow(operandOne,operandTwo);
                    valueStack.push(result);

                default:
                    try {
                        int value = Integer.parseInt(token);
                        valueStack.push(value);
                    } catch (NumberFormatException e) {

                    }
            }
        }
        return valueStack.peek();
    }
    private boolean validExpression (String[] infix) {
        // catch unmatched parentheses or invalid operator order
        StackInterface<String> parenthesesStack=new ArrayStack<>();
        boolean previousWasOperator=true;
        boolean previousWasInt=false;
        for (String token:infix) {
            switch (token) {
                case "(": case "[": case "{":
                    parenthesesStack.push(token); break;
                case ")": case "]": case "}":
                    //if parentheses don't match
                    if(parenthesesStack.isEmpty()) return false;
                    if ((token.equals(")") && !(parenthesesStack.peek()).equals("("))
                            || (token.equals("]") && !(parenthesesStack.peek()).equals("["))
                            || (token.equals("}") && !(parenthesesStack.peek()).equals("{")))
                        return false;
                    //if parentheses match
                    parenthesesStack.pop(); break;
                case "+": case "-": case "/": case "*": case "^":
                    if (previousWasOperator) return false;
                    previousWasOperator=true;
                    previousWasInt=false;
                    break;
                default:
                    //check if token is int
                    try {
                        Integer.parseInt(token);
                        previousWasOperator=false;
                        if (previousWasInt) return false;
                        previousWasInt=true;
                    } catch (NumberFormatException e) {
                        return false;
                    }


            }
        }
        // if there are no unclosed brackets and last element is not an operator, return true
        return (parenthesesStack.isEmpty())&&(!previousWasOperator);
    }
}