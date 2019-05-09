package org.uma.jmetal.util.f_messenger;

import com.restfb.*;
import com.restfb.exception.FacebookException;
import com.restfb.types.Conversation;
import com.restfb.types.Message;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.SendResponse;

import java.util.List;

public class SendFacebookMessage {

    public static void main(String[] args) {

        /* Variables */
        final String fbAccessToken = "";
        final String pageAccessToken = "EAAF15SpoHgEBAGzXLySdg0TZBAYtf0IYZC6ZBAGeAeDDq4EWZB0031c49ddERmSXIsD7XZBrsabRpyC0TUVKcGiZBWhZANRkXt1V48GMEiqUQhBWkupQItMAvlUIyOZBL0uJsrMOJFyympdimc4gcRJkEflNgqX89rF3Ot4FPzZARVuGkc77HSto8eQEPplU2qbwZD";
        final String pageID = "268627897038330";
        final String appID = "411102096137729";
        final String appSecret = "rQeXSyCHem72Nt5CCE8kOUnaVQU";
        FacebookClient fbClient;
        User myUser = null;    //Store references to myr user and page
        Page myPage = null;    //for later use. In this question's context, these
        //references are useless

        Message message = new Message();
        message.setMessage("Process finished");
        IdMessageRecipient recipient = new IdMessageRecipient("2481269305230553");

        try {

            FacebookClient.AccessToken accessToken;
            accessToken = new DefaultFacebookClient(pageAccessToken, Version.LATEST).obtainAppAccessToken(appID, appSecret);
            fbClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
            myUser = fbClient.fetchObject("me", User.class);
            myPage = fbClient.fetchObject(pageID, Page.class);

//            String conversationId = “t_mid.14XXX.. : XXX...” ;
            String id;
            Connection<Conversation> conversations = fbClient.fetchConnection("me/conversations", Conversation.class);
            for(List<Conversation> conversationPage : conversations) {
                for(Conversation conversation : conversationPage) {
                    id = conversation.getId();
                    Connection<Message> messages = fbClient.fetchConnection(id + "/messages", Message.class, Parameter.with("fields", "message,created_time,from,id"));
                }
            }

            System.out.println(conversations);

            SendResponse sr = fbClient.publish("id" + "/messages" ,
                    SendResponse.class,
                    Parameter.with("recipient", recipient),
                    Parameter.with("message", message)
                    );


        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }


    }
}
