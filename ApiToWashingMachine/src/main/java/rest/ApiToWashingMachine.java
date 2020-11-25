package rest;

import org.json.JSONObject;
import util.Util;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Path("/")
public class ApiToWashingMachine {
    @GET
    @Path("ping")
    @Produces("text/plain")
    public String ping() {
        return "ok";
    }

    // API to get state of washing machine
    @POST
    @Path("state")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWashingMachineState(@FormParam("state") String state, @FormParam("error") String error) {
        Util util = new Util();
        String nameStateDir = util.creationDirectoryInCommonDir("/newStates");
        String nameStateFile = nameStateDir + "/state.txt";
        util.createFile(nameStateFile);
        JSONObject object = new JSONObject();
        object.put("time", new Date());
        object.put("state", state);
        object.put("error", error);
        try {
            util.writeToFile(nameStateFile, object.toString(), true);
            return Response.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    // API to send task to washing machine
    @GET
    @Path("gettask")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendTaskToWasingMachine() {
        Util util = new Util();
        String newTaskDir = util.creationDirectoryInCommonDir("/newTasks");
        String newTaskFile = util.getNameOfFileFrDir(newTaskDir, ".txt");
        if(newTaskFile.equals("no tasks")){
            return Response.ok("no tasks").build();
        }
        else {
            String sTask = util.readFrFile(newTaskFile);
            // delete this file from directory
            new File(newTaskFile).delete();
            return Response.ok(sTask).build();
        }
    }
}
