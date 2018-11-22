package inter;
import lexer.*; import symbols.*;

public class Ternary extends Expr {
	Expr expr;
	Expr expr1, expr2;
	Id id;
	Token token;
	
	public Ternary(Id i, Token tok,Expr x, Expr s1, Expr s2) {
		super (tok, null);
		id = i;
		token = tok;
		expr = x;
		expr1 = s1;
		expr2 = s2;
		boolean chk;
				
		if(expr.type != Type.Bool) expr.error("boolean required in ternary: '("+ expr + ")'");
		
		chk = check(id.type, expr1.type);
		if (chk) error("type error: variable '"+id+"' of type "+id.type+" and value of type " +expr1.type);
		
		chk = check(id.type, expr2.type);
		if (chk) error("type error: variable '"+id+"' of type "+id.type+" and value of type " +expr2.type);
		
		type = expr1.type;
		
	}
	
	public Boolean check(Type p1, Type p2) {
		if(p1.equals(p2)) return false;
		else if(p1.equals(Type.Float) && p2.equals(Type.Int)) return false;
		else return true;
	}
	
	public Expr gen (){
		int f = newlabel();
		int a = newlabel();
		Temp temp = new Temp (type);
		this.jumping (0,f);
		emit(temp.toString() + "= "+expr1);
		emit ("goto L" + a);
		emitlabel(f);
		emit(temp.toString()+ "= "+expr2);
		emitlabel(a);
		return temp;
	}
	
	public String toString (){
		return expr.toString();
	}
}