public class InfixAddLeftBracket {
 
    private static final String LEFT_PAREN = "(";
    private static final String RIGHT_PAREN = ")";
 
    public static String addLeftBracket(String input) {
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();
        String val = "";//拼接数值
        for (String s : input.trim().split("")) {
            if(s.equals(RIGHT_PAREN)){
                if(!val.equals("")){
                    vals.push(val);
                    val = "";
                }
                String val1 = vals.pop();
                String val2 = vals.pop();
                String result = LEFT_PAREN + val2 + ops.pop() + val1 + RIGHT_PAREN;
                vals.push(result);
            }else if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
                if(!val.equals("")){
                    vals.push(val);
                    val = "";
                }
                ops.push(s);
            }else{
                val += s;
            }
        }
        return vals.pop();
    }
 
    public static void main(String[] args) {
        String str1 = "1+2)*3-4)*5-6)))";
        String str2 = "11+2.1)*3-4)*5-6)))";
        System.out.println(addLeftBracket(str1));
        System.out.println(addLeftBracket(str2));
    }
