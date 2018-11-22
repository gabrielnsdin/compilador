package inter;
import symbols.*;

public class For extends Stmt{
	Stmt stmt1;
	Expr expr;
	Stmt stmt2;
	Stmt stmt;
	
	public For() {
		stmt1 = null;
		expr = null;
		stmt2 = null;
		stmt = null;
	}
	
	public void init (Set s1, Expr e, Set s2, Stmt s){
	
		stmt1 = s1;
		expr = e;
		stmt2 = s2;
		stmt = s;
		
		if(expr.type != Type.Bool) expr.error("\n\nboolean required in for");
		
		boolean chk = checkInt(s1.id.type);
		if (chk) error("\n\ntype error: variable '"+s1.id+"' of type "+s1.id.type+", should be int");
		
		chk = checkInt(s2.id.type);
		if (chk) error("\n\ntype error: variable '"+s2.id+"' of type "+s2.id.type+", should be int");
		
	}
	
	public Boolean checkInt(Type p1) {
		if(p1.equals(Type.Int)) return false;
		else return true;
	}
	
	public Boolean checkBool(Type p1) {
		if(p1.equals(Type.Bool)) return false;
		else return true;
	}
	
	public void gen(int b , int a) {
		after = a;
		int label = newlabel();
		stmt1.gen(label, a);
		emitlabel(label);
		int label2 = newlabel();
		expr.jumping(label2, a);
		emitlabel(label2);
		stmt.gen(label2, a);
		label2 = newlabel();
		emitlabel(label2);
		stmt2.gen(label2, a);
		emit("goto L" + label);
	}
}