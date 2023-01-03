package aspects;

import main.WebsiteAPI;

import larva.*;
public aspect _asp_RuntimeVerification0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_RuntimeVerification0.initialize();
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 124/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 124/*viewAlerts*/);
}
}
before () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 126/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 126/*createAlert*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 122/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 122/*goodLogin*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 128/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 128/*deleteAlerts*/);
}
}
before () : (call(* *.logout(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 130/*logout*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 130/*logout*/);
}
}
}