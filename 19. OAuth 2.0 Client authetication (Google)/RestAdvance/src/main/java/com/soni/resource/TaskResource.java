package com.soni.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import javax.servlet.ServletContext;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.OAuth2FlowGoogleBuilder;
import com.soni.service.oauth2.SimpleOAuthService;
import com.soni.entity.TaskBean;
import com.soni.entity.TaskListBean;
import com.soni.entity.TaskRootBean;
import com.soni.model.AllTaskListsModel;
import com.soni.model.TaskListModel;
import com.soni.model.TaskModel;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * Task resource that returns Google tasks that was queried using a {@link Client}.
 */
@Path("tasks")
public class TaskResource {

    private static final String GOOGLE_TASKS_BASE_URI = "https://www.googleapis.com/tasks/v1/";

    @Context
    private UriInfo uriInfo;
    @Context
    private ServletContext servletContext;

    @GET
    @Produces(value=MediaType.TEXT_HTML)
    public Response getTasks() {
        // check oauth setup
        if (SimpleOAuthService.getClientIdentifier() == null) {
            final URI uri = UriBuilder.fromUri(servletContext.getContextPath())
                    .path("/index.html") //to show "Enter your Client Id and Secret" setup page
                    .build();
            return Response.seeOther(uri).build();
        }
        // check access token
        if (SimpleOAuthService.getAccessToken() == null) {
            return googleAuthRedirect();
        }
        // We have already an access token. Query the data from Google API.
        final Client client = SimpleOAuthService.getFlow().getAuthorizedClient();
        return getTasksResponse(client);
    }

    /**
     * Prepare redirect response to Google Tasks API auth consent request.
     *
     * @return redirect response to Google Tasks API auth consent request
     */
    private Response googleAuthRedirect() {
        final String redirectURI = UriBuilder.fromUri(uriInfo.getBaseUri())
                .path("oauth2/authorize").build().toString();

        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport.googleFlowBuilder(
                SimpleOAuthService.getClientIdentifier(),
                redirectURI,
                "https://www.googleapis.com/auth/tasks.readonly")
                .prompt(OAuth2FlowGoogleBuilder.Prompt.CONSENT).build();

        SimpleOAuthService.setFlow(flow);

        // start the flow
        final String googleAuthURI = flow.start();

        // redirect user to Google Authorization URI.
        return Response.seeOther(UriBuilder.fromUri(googleAuthURI).build()).build();
    }

    /**
     * Queries task data from google.
     *
     * @param client Client configured for authentication with access token.
     * @return Google task data response or redirect to google authorize page response.
     */
    private Response getTasksResponse(final Client client) {
        client.register(JacksonFeature.class);
        client.register(new LoggingFilter(Logger.getLogger("example.client.tasks"), true));

        final WebTarget baseTarget = client.target(GOOGLE_TASKS_BASE_URI);
        final Response response = baseTarget.path("users/@me/lists").request().get();

        final List<TaskListModel> listOfTaskLists;
        switch (response.getStatus()) {
            case 401: //Response.Status.UNAUTHORIZED
                SimpleOAuthService.setAccessToken(null);
                return googleAuthRedirect();
            case 200: //Response.Status.OK
                listOfTaskLists = processTaskLists(baseTarget, response.readEntity(TaskRootBean.class));
                break;
            default:
                listOfTaskLists = null;
        }

        final AllTaskListsModel tasks = new AllTaskListsModel(listOfTaskLists);
        return Response.ok(tasks).build();
    }

    /**
     * Process users task lists and read task details. Collect just
     * @param baseTarget base JAX-RS client target with oauth context configured
     * @param taskRootBean root task bean to be processed
     * @return Detailed list of non-completed tasks or {@code null} if there is no task list available.
     */
    private List<TaskListModel> processTaskLists(final WebTarget baseTarget, final TaskRootBean taskRootBean) {
        final List<TaskListModel> listOfTaskLists = new ArrayList<>();
        for (final TaskListBean taskListBean : taskRootBean.getItems()) {
            final List<TaskModel> taskList = new ArrayList<>();
            final WebTarget listTarget = baseTarget.path("lists/{tasklist}/tasks")
                    .resolveTemplate("tasklist", taskListBean.getId());

            final TaskListBean fullTaskListBean = listTarget.request().get(TaskListBean.class);
            for (final TaskBean taskBean : fullTaskListBean.getTasks()) {
                if (taskBean.getCompleted() == null) {
                    taskList.add(new TaskModel(taskBean.getTitle()));
                }
            }
            listOfTaskLists.add(new TaskListModel(taskListBean.getTitle(), taskList.size() > 0 ? taskList : null));
        }
        return listOfTaskLists.size() > 0 ? listOfTaskLists : null;
    }

}