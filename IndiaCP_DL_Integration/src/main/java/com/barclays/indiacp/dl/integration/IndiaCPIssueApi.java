package com.barclays.indiacp.dl.integration;

import com.barclays.indiacp.model.IndiaCPIssue;
import com.barclays.indiacp.model.IndiaCPProgram;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ritukedia on 23/12/16.
 */
@Path("indiacpissue")
public interface IndiaCPIssueApi {

    @POST
    @Path("issueCP/{cpProgramId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IndiaCPIssue issueCP(@PathParam("cpProgramId") String cpProgramId, String jsonBody);

    @GET
    @Path("fetchAllCP/{cpProgramId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<IndiaCPIssue> fetchAllCP(@PathParam("cpProgramId") String cpProgramId);

    @GET
    @Path("fetchAllCP")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<IndiaCPIssue> fetchAllCP();

    @GET
    @Path("fetchCP/{cpIssueId}")
    @Produces(MediaType.APPLICATION_JSON)
    public IndiaCPIssue fetchCP(@PathParam("cpIssueId") String cpIssueId);

    @POST
    @Path("addDoc")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public IndiaCPProgram addDoc(IndiaCPDocumentDetails docDetails,
                                 @FormDataParam("file") InputStream uploadedInputStream);


    @Context
    public void setRequest(Request request);
}
