package org.eclipse.leshan.client.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.client.servers.ServerIdentity;
import org.eclipse.leshan.core.Destroyable;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.model.ResourceModel;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.node.LwM2mResourceInstance;
import org.eclipse.leshan.core.node.ObjectLink;
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ObserveResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;
import org.eclipse.leshan.core.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInference extends BaseInstanceEnabler implements Destroyable {

	private static final Logger LOG = LoggerFactory.getLogger(MyInference.class);

	private static final int StartInference = 0;
	private static final int InferenceState = 1;
	private static final int PeopleDetenction = 2;
	private static final int ObjectTofind = 3;
	private static final int DateTime= 4;
	private static final int Accuracy= 5;
	private static final int Position = 6;

	private final ScheduledExecutorService scheduler;

	private String obj; 




	public MyInference() {
		this.scheduler = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Inference Result"));
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				adjustInference();
			}
		}, 5, 5, TimeUnit.SECONDS);
	}


	@Override
	public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
		LOG.info("Read on Inferenza resource /{}/{}/{}", getModel().id, getId(), resourceId);
		switch (resourceId) {

		case InferenceState:
			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);


				//vettore che rappresenta la finestra temporale che verrà prelevata
				Vector<String> result = new Vector<String>();
				for(int h=start; h<stringhe.size()-1;h++){
					//System.out.println(stringhe.get(h));
					result.add(stringhe.get(h)+"\r");
				}
				return ReadResponse.success(resourceId, result.toString());


			}
			catch (Exception e) {
				// TODO: handle exception

			}
			return null;

		case PeopleDetenction:
			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);



				for(int h=start; h<stringhe.size()-1;h++){
					if(stringhe.get(h).contains("person")) {
						String result = stringhe.get(h);
						return ReadResponse.success(resourceId, result);

					}
				}

			}
			catch (Exception e) {
				// TODO: handle exception

			}
			return null;



		case ObjectTofind:

			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);


				//vettore che rappresenta la finestra temporale che verrà prelevata
				String result = "L'oggetto indicato NON è stato trovato";
				for(int h=start; h<stringhe.size()-1;h++){
					if(stringhe.get(h).contains(obj)) {
						result = "L'oggetto indicato è stato trovato!!";
					}
				}


				return ReadResponse.success(resourceId, result);


			}
			catch (Exception e) {
				// TODO: handle exception

			}




		case DateTime:
			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);


				//la prima voce del vettore contiene la data, quindi prelevo i caratteri richiesti
				if(stringhe.get(start).contains("2021")) {
					return ReadResponse.success(resourceId, stringhe.get(start).substring(15, 35) );
				}
			}
			catch (Exception e) {
				// TODO: handle exception

			}
		case Accuracy:
			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);


				//la prima+1 voce del vettore contiene i dati dell'object, quindi prelevo i caratteri richiesti
				if(stringhe.get(start+1).contains("object")) {
					return ReadResponse.success(resourceId, stringhe.get(start+1).substring(24, 32));
				}
			}
			catch (Exception e) {
				// TODO: handle exception

			}

		case Position:
			try {
				BufferedReader br = new BufferedReader(new FileReader("/home/omen/Scrivania/leshancatrep/leshan-CatRep/output1.log"));
				String line = null;
				Vector<String> stringhe = new Vector<String>(); //vettore che contiene le stringhe del bufferedfile
				while (br.readLine()!=null) { //while che popola il vettore delle stringhe con le stringhe del file
					line = br.readLine();
					stringhe.add(line);
				}

				//prelevo l'attuale data in vari formati 
				Date d = new Date();
				int mese= d.getMonth();		
				int year= d.getYear();
				int ora= d.getHours()-2;
				int min= d.getMinutes();
				int sec= d.getSeconds();

				//stringhe per costruire i vari istanti da catturare
				String data_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-10);
				String data2_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-9);
				String data3_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-8);
				String data4_s = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-7);

				String data_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-5);
				String data2_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-4);
				String data3_f = String.format("%02d", d.getDate())+"/" + "Jul" + "/" + "2021" + " " + String.format("%02d", ora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec-3);

				//interi per l'indici del vettore della risposta
				int start=0;
				int stop=0;


				//for per prelevare la prima stringa da visualizzare (per la finestra temporale)
				for(int i=1; i<stringhe.size();i++){
					if(stringhe.get(i).contains(data_s)||stringhe.get(i).contains(data2_s)||stringhe.get(i).contains(data3_s)||stringhe.get(i).contains(data4_s)){
						start=i;
						break;
					}
				}
				System.out.println("start=" + start);

				//for per prelevare l'ultima stringa		
				for(int f=1; f<stringhe.size();f++){
					if(stringhe.get(f).contains(data_f)||stringhe.get(f).contains(data2_f)||stringhe.get(f).contains(data3_f)){
						stop=f;
						break;
						//System.out.println("stop=" + stop);
					}
				}
				System.out.println("stop=" + stop);


				//la prima+1 voce del vettore contiene i dati dell'object, quindi prelevo i caratteri richiesti
				if(stringhe.get(start+1).contains("object")) {
					return ReadResponse.success(resourceId, stringhe.get(start+1).substring(49,95));
				}
			}
			catch (Exception e) {
				// TODO: handle exception

			}

		default:
			return super.read(identity, resourceId);

		}

	}


	@Override
	public synchronized WriteResponse write(ServerIdentity identity, boolean replace, int resourceId,
			LwM2mResource value) {
		LOG.info("Write on test resource /{}/{}/{}", getModel().id, getId(), resourceId);
		switch (resourceId) {
		case ObjectTofind:
			obj =  (String) value.getValue();
			return WriteResponse.success();
		}
		return null;
	}

	@Override
	public synchronized ExecuteResponse execute(ServerIdentity identity, int resourceId, String params) {
		LOG.info("Execute on Inferenza resource /{}/{}/{}", getModel().id, getId(), resourceId);
		switch (resourceId) {
		case StartInference:
			try {
				Runtime r = Runtime.getRuntime();
				String myScript = "/home/omen/Scrivania/Script2.sh";
				String[] cmdArray = {"gnome-terminal", "-e", myScript + " ; le_exec"};
				r.exec(cmdArray).waitFor();
			} catch (InterruptedException ex){
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		default:
			return super.execute(identity, resourceId, params);
		}
	}




	@Override
	public ObserveResponse observe(ServerIdentity identity) {
		// TODO Auto-generated method stub
		return super.observe(identity);
	}

	private void adjustInference() {
		fireResourcesChange(InferenceState, ObjectTofind, DateTime, Accuracy, Position, PeopleDetenction);
	}




	@Override
	public void destroy() {
		scheduler.shutdown();
	}
}
