package hu.innobyte.service;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import hu.innobyte.checker.CheckerFactory;
import hu.innobyte.checker.checkers.CheckerInitData;
import hu.innobyte.checker.checkers.CheckerType;
import hu.innobyte.interpreter.SentenceInterpreter;
import hu.innobyte.question.DialogException;
import hu.innobyte.question.Dialogs;
import hu.innobyte.rest.ErrorResponse;
import hu.innobyte.rest.RestRequest;
import hu.innobyte.rest.RestResponse;

@Path("/")
public class VandaService {
	private static final String DIALOGS_FILE = "files//dialogs.xml";
	private static final String WORDS = "files//words.txt";
	
	
	@Context
	private ServletContext application;
	
	@GET
	@Produces("application/html")
	@Path("/info")
	public Response info() {
		String html = "<html>Hello Géza!</html>";
		return Response.status(Status.OK).entity(html).build();
	}
	
	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/question")
	public Response question(RestRequest restRequest) {
		
		JSONObject jsonObject = null;
		
		if (restRequest.isValid()) {
			SentenceInterpreter sentenceInterpreter = SentenceInterpreter.builder()
					.checker(CheckerFactory.createInstance(CheckerType.StringComparator, CheckerInitData.builder().filePath(application.getRealPath("/") + "//" + WORDS).build()))
					.maxDistance(0.95f)
					.build();
			
			String requestText = sentenceInterpreter.normalizeSentence(restRequest.getQueryText());
			
			Dialogs dialogs = Dialogs.loadFromFile(application.getRealPath("/") + "//" + DIALOGS_FILE);
			
			try {
				jsonObject = RestResponse.createOkResponse(dialogs.getAnswer(requestText));
			}
			catch (DialogException e) {
				jsonObject = RestResponse.createErrorResponse(String.format(ErrorResponse.UnknowQuestion.getErrorResponseMessage(),requestText));
			}
			
		} else {
			jsonObject = RestResponse.createErrorResponse(ErrorResponse.InvalidRequest);
		}
		
		return Response.status(Status.OK).entity(jsonObject.toString()).build();
	}
	
}
