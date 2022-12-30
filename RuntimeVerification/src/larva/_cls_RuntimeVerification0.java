package larva;


import main.WebsiteAPI;

import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_RuntimeVerification0 implements _callable{

public static PrintWriter pw; 
public static _cls_RuntimeVerification0 root;

public static LinkedHashMap<_cls_RuntimeVerification0,_cls_RuntimeVerification0> _cls_RuntimeVerification0_instances = new LinkedHashMap<_cls_RuntimeVerification0,_cls_RuntimeVerification0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\farad\\Documents\\software testing\\CPS3230-Assignment2\\RuntimeVerification/src/output_RuntimeVerification.txt");

root = new _cls_RuntimeVerification0();
_cls_RuntimeVerification0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_RuntimeVerification0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public int numOfAlerts =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_RuntimeVerification0() {
}

public void initialisation() {
}

public static _cls_RuntimeVerification0 _get_cls_RuntimeVerification0_inst() { synchronized(_cls_RuntimeVerification0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_RuntimeVerification0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_RuntimeVerification0_instances){
_performLogic_User(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_RuntimeVerification0[] a = new _cls_RuntimeVerification0[1];
synchronized(_cls_RuntimeVerification0_instances){
a = _cls_RuntimeVerification0_instances.keySet().toArray(a);}
for (_cls_RuntimeVerification0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_RuntimeVerification0_instances){
_cls_RuntimeVerification0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_User = 43;

public void _performLogic_User(String _info, int... _event) {

_cls_RuntimeVerification0.pw.println("[User]AUTOMATON::> User("+") STATE::>"+ _string_User(_state_id_User, 0));
_cls_RuntimeVerification0.pw.flush();

if (0==1){}
else if (_state_id_User==42){
		if (1==0){}
		else if ((_occurredEvent(_event,96/*createAlert*/)) && (numOfAlerts <6 )){
		numOfAlerts ++;

		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,98/*deleteAlerts*/)) && (numOfAlerts >0 )){
		numOfAlerts =0 ;

		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,98/*deleteAlerts*/)) && (numOfAlerts ==0 )){
		
		_state_id_User = 39;//moving to state badDelete
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,96/*createAlert*/)) && (numOfAlerts ==5 )){
		numOfAlerts ++;

		_state_id_User = 38;//moving to state tooMany
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,94/*viewAlerts*/))){
		
		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,100/*logout*/))){
		
		_state_id_User = 43;//moving to state start
		_goto_User(_info);
		}
}
else if (_state_id_User==43){
		if (1==0){}
		else if ((_occurredEvent(_event,96/*createAlert*/))){
		numOfAlerts ++;

		_state_id_User = 40;//moving to state illegalCreate
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,98/*deleteAlerts*/))){
		numOfAlerts =0 ;

		_state_id_User = 41;//moving to state illegalDelete
		_goto_User(_info);
		}
		else if ((_occurredEvent(_event,92/*goodLogin*/))){
		
		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
}
else if (_state_id_User==40){
		if (1==0){}
		else if ((_occurredEvent(_event,98/*deleteAlerts*/))){
		numOfAlerts =0 ;

		_state_id_User = 41;//moving to state illegalDelete
		_goto_User(_info);
		}
}
else if (_state_id_User==41){
		if (1==0){}
		else if ((_occurredEvent(_event,92/*goodLogin*/))){
		
		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
}
else if (_state_id_User==38){
		if (1==0){}
		else if ((_occurredEvent(_event,98/*deleteAlerts*/))){
		numOfAlerts =0 ;

		_state_id_User = 42;//moving to state normal
		_goto_User(_info);
		}
}
}

public void _goto_User(String _info){
_cls_RuntimeVerification0.pw.println("[User]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_User(_state_id_User, 1));
_cls_RuntimeVerification0.pw.flush();
}

public String _string_User(int _state_id, int _mode){
switch(_state_id){
case 42: if (_mode == 0) return "normal"; else return "normal";
case 40: if (_mode == 0) return "illegalCreate"; else return "!!!SYSTEM REACHED BAD STATE!!! illegalCreate "+new _BadStateExceptionRuntimeVerification().toString()+" ";
case 43: if (_mode == 0) return "start"; else return "start";
case 41: if (_mode == 0) return "illegalDelete"; else return "!!!SYSTEM REACHED BAD STATE!!! illegalDelete "+new _BadStateExceptionRuntimeVerification().toString()+" ";
case 38: if (_mode == 0) return "tooMany"; else return "!!!SYSTEM REACHED BAD STATE!!! tooMany "+new _BadStateExceptionRuntimeVerification().toString()+" ";
case 39: if (_mode == 0) return "badDelete"; else return "!!!SYSTEM REACHED BAD STATE!!! badDelete "+new _BadStateExceptionRuntimeVerification().toString()+" ";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}