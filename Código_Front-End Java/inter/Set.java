package inter;
import lexer.*;
import symbols.*;

public class Set extends Stmt {
	public Id id;
	public Expr expr;
	
	public Set(Id i, Expr x) {
		id = i;
		expr = x;
		if(check(id.type, expr.type)) error("type error "+ id.type+ " : "+ expr.type);
	}
	
	/*
	public Type check(Type p1, Type p2) {
		if(Type.numeric(p1) && Type.numeric(p2)) return p2;
		else if (p1 == Type.Bool && p2 == Type.Bool) return p2;
		else return null;
	}
	*/
	
	public Boolean check(Type p1, Type p2) {
		if(p1.equals(p2)) return false;
		else if(p1.equals(Type.Float) && p2.equals(Type.Int)) return false;
		else return true;
	}
	
	public void gen(int b, int a) {
		emit( id.toString() + " = " + expr.gen().toString());
	}
	
	public String toString(){
		return ("Id: "+id+", Expr: "+expr);
	}
}
