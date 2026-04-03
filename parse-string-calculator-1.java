/**
    # Parse strings that for a simple language as follows :
    # input -> "add(1,3)" output -> 4
    # input -> "sub(1,3)" output -> -2
    # Input -> “add(3,     add(3, 2))” ->  8
    # Input -> “           add(3, sub(3, add(3, 2)))        ” ->  1
    # Input -> “add( bad,2342)” -> return exception “syntax error at position 6 for "bad" in the string”
 */

import java.util.*;
class solution {
    public void evaluate(String expression){
        Stack<Integer> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();
        int ans =0;

        int i=0;
        while(i<expression.length()){
            if(expression.charAt(i) == ' ' || expression.charAt(i) == '(' || expression.charAt(i) == ','){
                i++;
                continue;
            }
            if(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '-'){
                int sign = 1;
                if(expression.charAt(i) == '-'){
                    sign = -1;
                    i++;
                }
                int num = 0;
                while(i<expression.length() && Character.isDigit(expression.charAt(i))){
                    num = num * 10 + (expression.charAt(i) -'0');
                    i++;
                }
                operand.push(num*sign);
                continue;
            }

            if(Character.isLetter(expression.charAt(i))){
                String temp = "";
                while(Character.isLetter(expression.charAt(i))){
                    temp += expression.charAt(i);
                    i++;
                }
                int sign =1;
                if(temp.equals("add")){
                    sign = 1;
                } else if(temp.equals("sub")){
                    sign = -1;
                } else {
                    System.out.println("bad operand");
                    break;
                }
                operator.push(sign);
                continue;
            }

            if(expression.charAt(i) == ')') { //eval;
                int b = operand.pop();
                int a = operand.pop();
                int sign = operator.pop();
                int total = a + (sign * b);
                operand.push(total);
                i++;
                continue;
            }

        }

        System.out.println(operand.pop());

    }

    public static void main(String[] args) {
        solution sol = new solution();
        sol.evaluate("add(1,3)");
        sol.evaluate("sub(1,3)");
        sol.evaluate("add(3, add(3, 2))");
        sol.evaluate("add(3, sub(3, add(3, 2)))");
    }
}