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
_cls_inst._call(thisJoinPoint.getSignature().toString(), 154/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 154/*viewAlerts*/);
}
}
before () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 156/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 156/*createAlert*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 152/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 152/*goodLogin*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 158/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 158/*deleteAlerts*/);
}
}
before () : (call(* *.logout(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_RuntimeVerification0.lock){

_cls_RuntimeVerification0 _cls_inst = _cls_RuntimeVerification0._get_cls_RuntimeVerification0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 160/*logout*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 160/*logout*/);
}
}
}