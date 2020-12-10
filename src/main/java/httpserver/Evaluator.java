package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Evaluator {
	
	public static String doEvaluation (String queryString) {
		
		String answer="";
		
		//For debug purposes only
		//System.out.println("# queryString="+queryString);
		
		//Split the query string and store the URL of the files to be processed into a list 
		String[] params = queryString.split("\\&");
		int nFiles=params.length;
		//For debug purposes only		
		//System.out.println("# nFiles="+nFiles);
		
		List<String> urls=new ArrayList<String>();
		for (int i=0;i<nFiles;i++) {
			String[] oneParam=params[i].split("=");
			urls.add(oneParam[1]);
			//For debug purposes only
			//System.out.println("# oneParam[1]="+oneParam[1]);			
		}
		
		
		URL fileUrl=null;
		String readLine=null;
		
		// For each file, store the counter of the 3 features to be evaluated in a HashMap, using Counters class
		for (String strUrl:urls) {
		  try {
			  
			HashMap<String,Counters> fileContent=new HashMap<String,Counters>();
			String polName=null;
			fileUrl=new URI(strUrl).toURL();
			InputStream isFile=fileUrl.openStream();			
			InputStreamReader isrFile=new InputStreamReader(isFile);
			BufferedReader brFile=new BufferedReader(isrFile);			
			
			brFile.readLine();
			while((readLine=brFile.readLine())!=null) {			
			  String[] lineaValues=readLine.split(",");
			  
			  polName=lineaValues[0];
			  if (!fileContent.containsKey(polName)) {
				  fileContent.put(polName, new Counters(0,0,0));
			  }
			  fileContent.get(polName).addRedeWorter(Integer.parseInt(lineaValues[3].trim()));
			  if (lineaValues[2].contains("2013")) {
				 fileContent.get(polName).addRede2013(Integer.parseInt(lineaValues[3].trim()));
			  }
			  if (lineaValues[1].contains("Sicherheit")) {
				  fileContent.get(polName).addRedeSicherheit(Integer.parseInt(lineaValues[3].trim()));
			  }
			  
			}
			
			// Parse the HashMap and identify the winners of each category
			String winnerRede=null;
			int topRede=0;
			String winnerSicherheit=null;
			int topSicherheit=0;
			String winner2013=null;
			int top2013=0;
			for (String politiker:fileContent.keySet()) {
				if (fileContent.get(politiker).getRede2013()>top2013) {
					top2013=fileContent.get(politiker).getRede2013();
					winner2013=politiker;
				}
				if (fileContent.get(politiker).getRedeSicherheit()>topSicherheit) {
					topSicherheit=fileContent.get(politiker).getRedeSicherheit();
					winnerSicherheit=politiker;
				}
				if (topRede==0) {
					topRede=fileContent.get(politiker).getRedeWorter();
				}
				else {
				  if (fileContent.get(politiker).getRedeWorter()<topRede) {
					topRede=fileContent.get(politiker).getRedeWorter();
					winnerRede=politiker;
				  }
				}
				//For debug purposes only
				//System.out.println("# Politiker="+politiker);
				//System.out.println("# Rede2013="+fileContent.get(politiker).getRede2013());
				//System.out.println("# RedeSicherheit="+fileContent.get(politiker).getRedeSicherheit());
				//System.out.println("# RedeWorter="+fileContent.get(politiker).getRedeWorter());
			}
			
			//For debug purposes only
			//System.out.println("# winner2013="+winner2013);
			//System.out.println("# winnerSicherheit="+winnerSicherheit);
			//System.out.println("# winnerRede="+winnerRede);
			
			// Take winners and format the answer as JSON
			if (winner2013!=null) {
				winner2013="\""+winner2013+"\"";
			}
			if (winnerSicherheit!=null) {
				winnerSicherheit="\""+winnerSicherheit+"\"";				
			}
			if (winnerRede!=null) {
				winnerRede="\""+winnerRede+"\"";
			}
			
			answer=answer+"{\"mostSpeeches\":"+winner2013+",\"mostSecurity\":"+winnerSicherheit+",\"leastWordy\":"+winnerRede+"}";
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
					
		return answer;
	}

}
