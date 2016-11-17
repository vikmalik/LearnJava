package com.learnjava.jaxb.history;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TestCallHistory {

	public static final String CALL_HISTORY_FILE= "data/call_history/callhistory.xml";
	
	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		readAndManipulateXML();
	}
	
	private static void readAndManipulateXML() throws JAXBException, IOException{
		CallHistory callHistory;
		ArrayList<Call> callList;
		
		JAXBContext context = JAXBContext.newInstance(CallHistory.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		Unmarshaller um = context.createUnmarshaller();
		callHistory = (CallHistory) um.unmarshal(new FileReader(CALL_HISTORY_FILE));
		
		callList = callHistory.getCallsList();
		
		ArrayList<Call> removedArticle = new ArrayList<Call>();
		
		for (Call callInfo : callList) {
			if (callInfo.getCallId().contains("11")){
				removedArticle.add(callInfo);
			}
		}
		
		for (Call call : removedArticle) {
			callList.remove(call);
		}
		//callHistory.setCallList(callList);
		
		Writer writer =null;
		try{
			writer = new FileWriter(CALL_HISTORY_FILE);
			marshaller.marshal(callHistory, writer);
		}finally{
			writer.close();
		}
		
	}
	
	@SuppressWarnings("unused")
	private static void writeXML()throws JAXBException, IOException{
		CallHistory callHistory = new CallHistory();
		ArrayList<Call> callList = new ArrayList<Call>();
 		Call call;
 		for(int i=1; i<10000; i++){
 			call = new Call();
			call.setCallId("1234"+ i);
			call.setCallerName("Vikas");
			call.setCallStartTime(new Date());
			call.setCallEndTime(new Date());
			call.setCallType(CallType.INCOMING_CALL);
			
			callList.add(call);
 		}
		
		callHistory.setCallList(callList);
		
		JAXBContext context = JAXBContext.newInstance(CallHistory.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//m.marshal(callHistory, System.out);
		
		
		Writer w = null;
		try {
			w = new FileWriter(CALL_HISTORY_FILE);
			m.marshal(callHistory, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
		callHistory = null;
		System.out.println("Writting Finished.... lets read now");
	}
}
