package my.uum;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is to send message and receive message by using BotUsername and BotToken.
 *
 * @author Tan Xue Lee (277407)
 */

public class TelegramBot extends TelegramLongPollingBot {

    SendMessage sendMessage = new SendMessage();

    Map<String, ArrayList<String>> insertData = new HashMap<String, ArrayList<String>>();

    Map<String, String> flow = new HashMap<String, String>();

    DatabaseConnect connect = new DatabaseConnect();

    /**
     * This method is used to get botUsername
     *
     * @return botUsername s277407_A221_bot
     */

    @Override
    public String getBotUsername() {
        return "s277407_A221_bot";
    }

    /**
     * This method is used to get botToken
     *
     * @return botToken 5851360678:AAG6bZ6qd8teayVSVxwhh8C_DPUSszJisJU
     */

    @Override
    public String getBotToken() {
        return "5851360678:AAG6bZ6qd8teayVSVxwhh8C_DPUSszJisJU";
    }

    /**
     * This method is used to send command and receive message from TelegramBot. This program can prompt the user to book a meeting room, cancel booking, and view booking list.
     * Methods in the same class will be called in this method.
     */

    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();

        if (update.getMessage().hasText()) {
            if (!insertData.containsKey(String.valueOf(update.getMessage().getChatId()))){
                insertData.put(String.valueOf(update.getMessage().getChatId()),new ArrayList<String>());
            }

            if (command.equals("/start")) {
                commandStart(String.valueOf(update.getMessage().getChatId()));
            } else
            if (command.equals("/booking")) {
                commandBooking(String.valueOf(update.getMessage().getChatId()));
            } else
            if (command.equals("/cancel")) {
                commandCancel(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "cancel");
            } else
            if (command.equals("/display")) {
                commandDisplay(String.valueOf(update.getMessage().getChatId()));
            } else
            if (command.equals("2") || command.equals("0")) {
                commandQuit(String.valueOf(update.getMessage().getChatId()));
            } else
            if (command.equals("1")) {
                commandProceed(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "name");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("name")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 0: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                            break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(0, update.getMessage().getText());
                            break;
                }

                inputIC(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "ic");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("ic")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 1: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(1, update.getMessage().getText());
                        break;
                }

                inputStaffID(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "staffid");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("staffid")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 2: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(2, update.getMessage().getText());
                        break;
                }

                inputPhone(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "phone");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("phone")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 3: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(3, update.getMessage().getText());
                        break;
                }

                inputEmail(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "email");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("email")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 4: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(4, update.getMessage().getText());
                        break;
                }

                inputRoom(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "room");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("room")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 5: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(5, update.getMessage().getText());
                        break;
                }

                inputPurpose(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "purpose");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("purpose")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 6: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(6, update.getMessage().getText());
                        break;
                }

                inputDate(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "date");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("date")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 7: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(7, update.getMessage().getText());
                        break;
                }

                inputTime(String.valueOf(update.getMessage().getChatId()));
                flow.put(String.valueOf(update.getMessage().getChatId()), "time");
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("time")) {
                switch (insertData.get(String.valueOf(update.getMessage().getChatId())).size()) {
                    case 8: insertData.get(String.valueOf(update.getMessage().getChatId())).add(update.getMessage().getText());
                        break;

                    default: insertData.get(String.valueOf(update.getMessage().getChatId())).set(8, update.getMessage().getText());
                        break;
                }

                bookingSuccess(String.valueOf(update.getMessage().getChatId()));
            } else
            if (flow.get(String.valueOf(update.getMessage().getChatId())).equals("cancel")) {
                insertData.get(String.valueOf(update.getMessage().getChatId())).get(1);
                bookingCancel(String.valueOf(update.getMessage().getChatId()));
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
    }

    /**
     * This method is used to set text for Telegram Bot for command '/start'.
     */
    public void commandStart (String chatID) {
        sendMessage.setText("Hi! I'm s277407 A221 bot.\nWelcome to UUM Meeting Room Booking System.\nWe operate as usual from Sunday to Thursday, 8am - 5pm.\n\nYou may choose the options from the Menu:\nClick '/booking' for the booking section.\nClick '/cancel' for the cancel booking (only if you have done booking).\nClick '/display' for booking list.");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot for command '/booking'.
     */
    public void commandBooking (String chatID) {
        sendMessage.setText("Let's proceed with booking section!\n\nImportant note:\nPlease ensure that all information you have entered is correct, no modification provided during the booking process.\n\nAre you sure want to book the meeting room right now?\n\nReply 1: Yes\nReply 2: No");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot for command '/cancel'.
     */

    public void commandCancel (String chatID) {
        sendMessage.setText("Please enter your IC Number to cancel your booking.");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot for command '/display'.
     */

    public void commandDisplay (String chatID) {
        sendMessage.setText("The users booking list has been shown as below:\n\n" + connect.displayList() + "\nYou may click '/start' to go back to Main Menu.\nIf you like to cancel your booking, just click '/cancel' in the Menu.");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot if the user wish to exit the booking section.
     */

    public void commandQuit (String chatID) {
        sendMessage.setText("The booking section has ended.\nPlease click '/start' in the Menu again to back to Main Menu.");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot if the user wish to proceed the booking section, and prompt the user to enter their name.
     */

    public void commandProceed (String chatID) {
        sendMessage.setText("(A) First of all, may I have your name please?\ne.g.: Tan Xue Lee\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter their IC number.
     */

    public void inputIC (String chatID) {
        sendMessage.setText("(B) How about your IC number?\ne.g.: 901225025256\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter their staff ID.
     */

    public void inputStaffID (String chatID) {
        sendMessage.setText("(C) How about your staff ID number?\ne.g.: 278504\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter their phone number.
     */

    public void inputPhone (String chatID) {
        sendMessage.setText("(D) Kindly provide your phone number.\ne.g.: 0194567891\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter their email address.
     */

    public void inputEmail (String chatID) {
        sendMessage.setText("(E) Kindly provide your email address.\ne.g.: xuelee@gmail.com\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user choose type of meeting room.
     */

    public void inputRoom (String chatID) {
        sendMessage.setText("(F) Please choose which meeting rooms that you want to book.\nReply '101' for Small meeting room (accommodate up to 20 people)\nReply '102' for Medium meeting room (accommodate up to 50 people)\nReply '103' for Large meeting room (accommodate up to 100 people)\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter their booking purpose.
     */

    public void inputPurpose (String chatID) {
        sendMessage.setText("(G) Please provide the purpose of booking for the meeting room.\ne.g.: conference\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter booking date.
     */

    public void inputDate (String chatID) {
        sendMessage.setText("(H) Please provide a preferred date for the booking.\nPlease enter with the following format (dd-mm-yyyy):\ne.g.: 31-12-2022\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot to prompt the user enter booking time.
     */

    public void inputTime (String chatID) {
        sendMessage.setText("(I) Please provide a preferred time for the booking.\nPlease enter with the following format (hh:mma):\ne.g.: 09:00AM\n\nReply 0: If you do not wish to proceed with the booking");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot after user has successfully booking, and the data will be saved in database.
     */

    public void bookingSuccess (String chatID) {
        connect.insertBooking(insertData.get(chatID).get(0), insertData.get(chatID).get(1), insertData.get(chatID).get(2), insertData.get(chatID).get(3), insertData.get(chatID).get(4), insertData.get(chatID).get(5), insertData.get(chatID).get(6), insertData.get(chatID).get(7), insertData.get(chatID).get(8));
        sendMessage.setText("Your booking has been recorded, your booking details will be shown as below, thank you!\n\nName: " + insertData.get(chatID).get(0) + "\nIC Number: " + insertData.get(chatID).get(1) + "\nStaff ID: " + insertData.get(chatID).get(2) + "\nPhone Number: " + insertData.get(chatID).get(3) + "\nEmail: " + insertData.get(chatID).get(4) + "\nRoom Type: " + insertData.get(chatID).get(5) + "\nBooking Purpose: " + insertData.get(chatID).get(6) + "\nBooking Date: " + insertData.get(chatID).get(7) + "\nBooking Time: " + insertData.get(chatID).get(8) + "\n\nYou may view the whole booking list through '/display' in the Menu.\nIf you like to cancel your booking, just click '/cancel' in the Menu.");
        sendMessage.setChatId(chatID);
    }

    /**
     * This method is used to set text for Telegram Bot after the user enter their IC number for cancel booking, and the data will be deleted from database.
     */

    public void bookingCancel (String chatID) {
        connect.deleteBooking(insertData.get(chatID).get(1));
        sendMessage.setText("Your booking has been cancelled.\n\nPlease click '/booking' in the Menu if you want to book a meeting room again");
        sendMessage.setChatId(chatID);
    }
}
