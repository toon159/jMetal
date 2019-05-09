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
        final String pageAccessToken = "EAAF15SpoHgEBAN7r9jEl3TsuQ69QrjaAqcF8EFQNuocsgNWaSQPLSvjk6ZCvQRw9u0ZAZB911z9V3e1ckWUgblLYpYW6SJnErVVqVkv3Kd4YI0b15THrh8rlxSrZBOZAWTtZAX251ttp170Np9Mbikk0DajwFx95K8mnZAQQQgZCbxmAz0lh6CqZBIyfYoQrWEBps8AZC3mZBSvNhvj9Jl6yA2X";
        final String pageID = "268627897038330";
        final String appID = "411102096137729";
        final String appSecret = "rQeXSyCHem72Nt5CCE8kOUnaVQU";
        FacebookClient fbClient;
        User myUser = null;    //Store references to myr user and page
        Page myPage = null;    //for later use. In this question's context, these
        //references are useless

        Message message = new Message();
        message.setMessage("Process finished");
        IdMessageRecipient recipient = new IdMessageRecipient("2481576578533159");

        try {

            FacebookClient.AccessToken accessToken;
//            accessToken = new DefaultFacebookClient(pageAccessToken, Version.LATEST).obtainAppAccessToken(appID, appSecret);
            fbClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
            myUser = fbClient.fetchObject("me", User.class);
            myPage = fbClient.fetchObject(pageID, Page.class);

//            String conversationId = “t_mid.14XXX.. : XXX...” ;
            String id;
            Connection<Conversation> conversations = fbClient.fetchConnection("me/conversations", Conversation.class);
            for (List<Conversation> conversationPage : conversations) {
                for (Conversation conversation : conversationPage) {
                    id = conversation.getId();   //use this conversation_id
                    Connection<Message> messages = fbClient.fetchConnection(id + "/messages", Message.class, Parameter.with("fields", "message,created_time,from,id"));
                    messages.forEach(s -> s.forEach(k -> System.out.println(k.getFrom() + " " + k.getId() + " " + k.getMessage() + " " + k.getSubject() + " ")));
                }
            }


            System.out.println(conversations);

            SendResponse sr = fbClient.publish("" + "/messages" ,
                    SendResponse.class,
                    Parameter.with("recipient", recipient),
                    Parameter.with("message", message)
                    );


        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }


    }
}
