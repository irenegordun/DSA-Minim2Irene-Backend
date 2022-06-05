package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.dao.UserDAO;
import edu.upc.eetac.dsa.dao.impl.UserDAOImpl;
import edu.upc.eetac.dsa.models.Denuncia;
import edu.upc.eetac.dsa.models.LogInCredentials;
import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.SignUpCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.beans.IntrospectionException;
import java.util.List;

@Api(value = "/user", description = "Endpoint to User Service")
@Path("/user")
public class UserService {
    private UserDAO userManager;

    public UserService() {
        this.userManager = UserDAOImpl.getInstance();
    }

    @Path("basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    // sign up NEW user --> OK
    @POST
    @ApiOperation(value = "User sign up", notes = "username + password + email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= User.class),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 406, message = "email already in use"),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userSignUp(SignUpCredentials userCred) {

        User user = new User(userCred.getUsername(), userCred.getPassword(), userCred.getEmail());
        if (user.getName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty())
            return Response.status(500).entity(user).build();

        User namecheck = this.userManager.getUserByName(userCred.getUsername());
        User emailcheck = this.userManager.getUserByEmail(userCred.getEmail());
        if (namecheck != null )
            return Response.status(405).entity(user).build();
        else if (emailcheck != null )
            return Response.status(406).entity(user).build();
        else {
            this.userManager.addUser(user.getName(), user.getPassword(), user.getEmail());
            return Response.status(200).entity(user).build();
        }
    }

    // get ONE particular user --> OK
    @GET
    @ApiOperation(value = "Get a particular User", notes = "username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {

        User user = userManager.getUserByName(username);
        if (user == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<User> entity = new GenericEntity<User>(user) {};
            return Response.status(200).entity(entity).build();
        }
    }

    // get ALL signed up users --> OK
    @GET
    @ApiOperation(value = "Get all signed up Users", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/userList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<User> userList = this.userManager.getAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userList) {};
        return Response.status(201).entity(entity).build();
    }

    // LOGIN user --> OK
    @POST
    @ApiOperation(value = "LogIn User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/logIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logInUser(LogInCredentials userCred) {

        User user = userManager.getUserByName(userCred.getUsername());
        if ((userCred.getUsername().isEmpty()) || (userCred.getPassword().isEmpty()))
            return Response.status(500).build();
        else if (user == null)
            return Response.status(404).build();
        else {
            if (user.getPassword().equals(userCred.getPassword()))
                return Response.status(200).entity(user).build();
            else
                return Response.status(405).build();
        }
    }

    // UPDATE user --> OK
    @PUT
    @ApiOperation(value = "Update User information", notes = "userName, password and email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/update/{oldUsername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("oldUsername") String oldUsername, SignUpCredentials userCred) throws IntrospectionException {

        User oldUser = userManager.getUserByName(oldUsername);
        if ((userCred.getUsername().isEmpty()) || (userCred.getPassword().isEmpty()) || (userCred.getEmail().isEmpty()))
            return Response.status(500).build();
        else if (oldUser == null) {
            return Response.status(404).build();
        } else {
            userManager.updateUser(oldUsername, userCred.getUsername(), userCred.getPassword(), userCred.getEmail());
            return Response.status(200).entity(oldUser).build();
        }
    }

    // DELETE user --> OK
    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{username}")
    public Response deleteUser(@PathParam("username") String username) throws IntrospectionException {

        User user = userManager.getUserByName(username);
        if (user == null) {
            return Response.status(404).build();
        }
        else {
            userManager.deleteUser(username);
            return Response.status(200).entity(user).build();
        }
    }


    // GET ranking by coins --> OK
    @GET
    @ApiOperation(value = "get users by number of coins", notes = "descendent")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/coinRanking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoinRanking() {

        List<User> coinRanking = this.userManager.getCoinRanking();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(coinRanking) {};
        return Response.status(201).entity(entity).build();
    }

    //CODI AFEGIT PEL MINIM 2 IRENE GORDUN
    @POST
    @ApiOperation(value = "Denunciar abus", notes = "Denunciar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    @Path("/denuncia")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response denuncia(Denuncia denuncia) {

        User user = userManager.getUserByName(denuncia.getInformer());

        if (denuncia.getDate().isEmpty() || denuncia.getInformer().isEmpty() || denuncia.getMessage().isEmpty() ) {
            return Response.status(500).build(); //Error
        } else if (user == null) {
            return Response.status(404).build(); //usuari no existeix
        }
        //afegeix info de l'abús
        denuncia.setDate(denuncia.getDate());
        denuncia.setInformer(denuncia.getInformer());
        denuncia.setMessage(denuncia.getMessage());

        return Response.status(200).build(); //Success
    }
}
