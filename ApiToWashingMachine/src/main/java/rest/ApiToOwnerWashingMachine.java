package rest;

import org.json.JSONObject;
import util.Util;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// API to create task to washing machine on the service
@Path("/")
public class ApiToOwnerWashingMachine {
    @POST
    @Path("settask")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setTaskToWashingMachine(@FormParam("mode") String mode,
                                          @FormParam("temperature") String temperature,
                                          @FormParam("speed") String speed,
                                          @FormParam("time") String time) {
        Map<String, String> object = new HashMap<>();
        object.put("mode", mode);
        object.put("temperature", temperature);
        object.put("speed", speed);
        object.put("time", time);
        JSONObject objectJSON = new JSONObject(object);

        Util util = new Util();
        String newTaskDir = util.creationDirectoryInCommonDir("/newTasks");
        String newTaskFile = util.createNewFileWithNewName(newTaskDir, ".txt");
        try {
            util.writeToFile(newTaskFile, object.toString(), false);
            return Response.ok(objectJSON.toString()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
