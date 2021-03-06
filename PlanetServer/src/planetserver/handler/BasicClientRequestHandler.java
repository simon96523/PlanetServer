package planetserver.handler;

import java.util.List;
import java.util.Properties;

import planetserver.core.PsExtension;
import planetserver.network.PsObject;
import planetserver.room.RoomManager;
import planetserver.session.UserSession;
import planetserver.util.PsConstants;
import planetserver.util.PsEvents;

public class BasicClientRequestHandler 
{
    protected PsExtension _parentExtension;
    protected RoomManager _roomManager;
    protected Properties _properties;

    /**
     * Handles the incoming client request to this specific handler
     * @param command
     * @param sender
     * @param params 
     */
    public void handleClientRequest(String command, UserSession sender, PsObject params)
    {
    }

    protected void send(String cmdName, PsObject params, UserSession recipient)
    {
        PsObject psobj = new PsObject();
        psobj.setString(PsConstants.REQUEST_TYPE, PsEvents.EXTENSION);
        psobj.setString(PsConstants.COMMAND, cmdName);
        psobj.setPsObject(PsConstants.EXTENSION_DATA, params);
        
        recipient.send(psobj);
    }
    
    protected void send(String cmdName, PsObject params, List<UserSession> recipientList)
    {
        PsObject psobj = new PsObject();
        psobj.setString(PsConstants.REQUEST_TYPE, PsEvents.EXTENSION);
        psobj.setString(PsConstants.COMMAND, cmdName);
        psobj.setPsObject(PsConstants.EXTENSION_DATA, params);
       
        for (UserSession recipient : recipientList)
            recipient.send(psobj);
    }

    protected String getSplitCommand(String command)
    {
        //split it on the period
        String[] commandParts = command.split("\\.");
        if (commandParts.length > 1)
        {
            return commandParts[1];
        }
        else
        {
            return command;
        }
    }

    public PsExtension getParentExtension()
    {
        return _parentExtension;
    }
    
    public void setParentExtension(PsExtension extension)
    {
        _parentExtension = extension;
    }
    
    public void setRoomManager(RoomManager roomManager)
    {
        _roomManager = roomManager;
    }

    public Properties getProperties()
    {
        return _properties;
    }

    public void setProperties(Properties properties)
    {
        _properties = properties;
    }        
 }